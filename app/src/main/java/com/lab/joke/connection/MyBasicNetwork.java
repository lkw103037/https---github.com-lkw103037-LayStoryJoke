package com.lab.joke.connection;

import android.os.SystemClock;
import android.text.TextUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ByteArrayPool;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.PoolingByteArrayOutputStream;
import com.lab.joke.Joke;
import com.lab.joke.environment.AppConfig;
import com.lab.joke.util.common.LogUtil;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.cookie.DateUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by luokaiwen on 15/5/17.
 * <p/>
 * 扩展Network，添加对Cookie的支持
 */
public class MyBasicNetwork implements Network {

    public static final String TAG = MyBasicNetwork.class.getSimpleName();

    protected static final boolean DEBUG = AppConfig.INSTANCE.isDebug();

    private static int SLOW_REQUEST_THRESHOLD_MS = 3000;

    private static int DEFAULT_POOL_SIZE = 4096;

    protected final HttpStack mHttpStack;

    protected final ByteArrayPool mPool;

    /**
     * @param httpStack HTTP stack to be used
     */
    public MyBasicNetwork(HttpStack httpStack) {
        // If a pool isn't passed in, then build a small default pool that will give us a lot of
        // benefit and not use too much memory.
        this(httpStack, new ByteArrayPool(DEFAULT_POOL_SIZE));
    }

    /**
     * @param httpStack HTTP stack to be used
     * @param pool      a buffer pool that improves GC performance in copy operations
     */
    public MyBasicNetwork(HttpStack httpStack, ByteArrayPool pool) {
        mHttpStack = httpStack;
        mPool = pool;
    }

    @Override
    public NetworkResponse performRequest(Request<?> request) throws VolleyError {
        long requestStart = SystemClock.elapsedRealtime();
        while (true) {
            HttpResponse httpResponse = null;
            byte[] responseContents = null;
            Map<String, String> responseHeaders = Collections.emptyMap();
            try {
                // Gather headers.
                Map<String, String> headers = new HashMap<String, String>();
                addCacheHeaders(headers, request.getCacheEntry());
                headers.put("Cookie", getCookieStr());
                httpResponse = mHttpStack.performRequest(request, headers);
                StatusLine statusLine = httpResponse.getStatusLine();
                int statusCode = statusLine.getStatusCode();

                responseHeaders = convertHeaders(httpResponse.getAllHeaders());
                // Handle cache validation.
                if (statusCode == HttpStatus.SC_NOT_MODIFIED) {

                    Cache.Entry entry = request.getCacheEntry();
                    if (entry == null) {
                        return new NetworkResponse(HttpStatus.SC_NOT_MODIFIED, null,
                                responseHeaders, true,
                                SystemClock.elapsedRealtime() - requestStart);
                    }

                    // A HTTP 304 response does not have all header fields. We
                    // have to use the header fields from the cache entry plus
                    // the new ones from the response.
                    // http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.3.5
                    entry.responseHeaders.putAll(responseHeaders);
                    return new NetworkResponse(HttpStatus.SC_NOT_MODIFIED, entry.data,
                            entry.responseHeaders, true,
                            SystemClock.elapsedRealtime() - requestStart);
                }

                // Some responses such as 204s do not have content.  We must check.
                if (httpResponse.getEntity() != null) {
                    responseContents = entityToBytes(httpResponse.getEntity());
                } else {
                    // Add 0 byte response as a way of honestly representing a
                    // no-content request.
                    responseContents = new byte[0];
                }

                // if the request is slow, log it.
                long requestLifetime = SystemClock.elapsedRealtime() - requestStart;
                logSlowRequests(requestLifetime, request, responseContents, statusLine);

                if (statusCode < 200 || statusCode > 299) {
                    throw new IOException();
                }
                return new NetworkResponse(statusCode, responseContents, responseHeaders, false,
                        SystemClock.elapsedRealtime() - requestStart);
            } catch (SocketTimeoutException e) {
                attemptRetryOnException("socket", request, new TimeoutError());
            } catch (ConnectTimeoutException e) {
                attemptRetryOnException("connection", request, new TimeoutError());
            } catch (MalformedURLException e) {
                throw new RuntimeException("Bad URL " + request.getUrl(), e);
            } catch (IOException e) {
                int statusCode = 0;
                NetworkResponse networkResponse = null;
                if (httpResponse != null) {
                    statusCode = httpResponse.getStatusLine().getStatusCode();
                } else {
                    throw new NoConnectionError(e);
                }
                if (AppConfig.INSTANCE.isDebug()) {
                    VolleyLog.e("Unexpected response code %d for %s", statusCode, request.getUrl());
                }
                if (responseContents != null) {
                    networkResponse = new NetworkResponse(statusCode, responseContents,
                            responseHeaders, false, SystemClock.elapsedRealtime() - requestStart);
                    if (statusCode == HttpStatus.SC_UNAUTHORIZED ||
                            statusCode == HttpStatus.SC_FORBIDDEN) {
                        attemptRetryOnException("auth",
                                request, new AuthFailureError(networkResponse));
                    } else {
                        // TODO: Only throw ServerError for 5xx status codes.
                        throw new ServerError(networkResponse);
                    }
                } else {
                    throw new NetworkError(networkResponse);
                }
            }
        }
    }

    /**
     * Logs requests that took over SLOW_REQUEST_THRESHOLD_MS to complete.
     */
    private void logSlowRequests(long requestLifetime, Request<?> request,
                                 byte[] responseContents, StatusLine statusLine) {
        if (DEBUG || requestLifetime > SLOW_REQUEST_THRESHOLD_MS) {
            if (AppConfig.INSTANCE.isDebug()) {
                VolleyLog.d("HTTP response for request=<%s> [lifetime=%d], [size=%s], " +
                                "[rc=%d], [retryCount=%s]", request, requestLifetime,
                        responseContents != null ? responseContents.length : "null",
                        statusLine.getStatusCode(), request.getRetryPolicy().getCurrentRetryCount());
            }
        }
    }

    /**
     * Attempts to prepare the request for a retry. If there are no more attempts remaining in the
     * request's retry policy, a timeout exception is thrown.
     *
     * @param request The request to use.
     */
    private static void attemptRetryOnException(String logPrefix, Request<?> request,
                                                VolleyError exception) throws VolleyError {
        RetryPolicy retryPolicy = request.getRetryPolicy();
        int oldTimeout = request.getTimeoutMs();

        try {
            retryPolicy.retry(exception);
        } catch (VolleyError e) {
            request.addMarker(
                    String.format("%s-timeout-giveup [timeout=%s]", logPrefix, oldTimeout));
            throw e;
        }
        request.addMarker(String.format("%s-retry [timeout=%s]", logPrefix, oldTimeout));
    }

    private void addCacheHeaders(Map<String, String> headers, Cache.Entry entry) {
        // If there's no cache entry, we're done.
        if (entry == null) {
            return;
        }

        if (entry.etag != null) {
            headers.put("If-None-Match", entry.etag);
        }

        if (entry.serverDate > 0) {
            Date refTime = new Date(entry.serverDate);
            headers.put("If-Modified-Since", DateUtils.formatDate(refTime));
        }
    }

    protected void logError(String what, String url, long start) {
        long now = SystemClock.elapsedRealtime();
        if (AppConfig.INSTANCE.isDebug()) {
            VolleyLog.v("HTTP ERROR(%s) %d ms to fetch %s", what, (now - start), url);
        }
    }

    /**
     * Reads the contents of HttpEntity into a byte[].
     */
    private byte[] entityToBytes(HttpEntity entity) throws IOException, ServerError {
        PoolingByteArrayOutputStream bytes =
                new PoolingByteArrayOutputStream(mPool, (int) entity.getContentLength());
        byte[] buffer = null;
        try {
            InputStream in = entity.getContent();
            if (in == null) {
                throw new ServerError();
            }
            buffer = mPool.getBuf(1024);
            int count;
            while ((count = in.read(buffer)) != -1) {
                bytes.write(buffer, 0, count);
            }
            return bytes.toByteArray();
        } finally {
            try {
                // Close the InputStream and release the resources by "consuming the content".
                entity.consumeContent();
            } catch (IOException e) {
                // This can happen if there was an exception above that left the entity in
                // an invalid state.
                VolleyLog.v("Error occured when calling consumingContent");
            }
            mPool.returnBuf(buffer);
            bytes.close();
        }
    }

    /**
     * Converts Headers[] to Map<String, String>.
     */
    protected static Map<String, String> convertHeaders(Header[] headers) {

        Map<String, String> result = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);

        for (int i = 0; i < headers.length; i++) {

            String key = headers[i].getName();
            String value = headers[i].getValue();

            // 第一此请求传空,以后存储cookie
            if ("set-cookie".equals(key.toLowerCase().trim()))//Set-Cookie
            {
                // 存储cookie信息到HashMap中
                storeCookie(value);

                //value = getCookieStr();

            } else {

                result.put(key, value);
            }
        }

        if (!TextUtils.isEmpty(Joke.mCookie)) {

            // 设置cookie
            result.put("Set-Cookie", getCookieStr());
        }

        LogUtil.e(TAG, "wwcookie is " + Joke.mCookie);

        return result;
    }

    protected static void storeCookie(String cookieStr) {

        if (cookieStr == null || "".equals(cookieStr)) {
            return;
        }
        StringBuilder keySB = new StringBuilder();
        StringBuilder valueSB = new StringBuilder();
        StringBuilder nowSB = keySB;//相当于指针，当前的char应该填充进的SB
        for (char c : cookieStr.toCharArray()) {
            if (c == ';') {

                String key = keySB.toString().trim();
                String value = valueSB.toString().trim();

                String cookieValue = Joke.mCookieMap.get(key);

                if (null == cookieValue) {

                    Joke.mCookieMap.put(key, value);

                } else {

                    if (!cookieValue.equals(value)) {

                        Joke.mCookieMap.put(key, value);
                    }
                }

                keySB.delete(0, keySB.length());
                valueSB.delete(0, valueSB.length());
                nowSB = keySB;
                continue;
            }
            if (c == '=') {
                nowSB = valueSB;
                continue;
            }
            nowSB.append(c);
        }

        // 最后一个添加
        String key = keySB.toString().trim();
        String value = valueSB.toString().trim();

        String cookieValue = Joke.mCookieMap.get(key);

        if (null == cookieValue) {

            Joke.mCookieMap.put(key, value);

        } else {

            if (!cookieValue.equals(value)) {

                Joke.mCookieMap.put(key, value);
            }
        }
    }

    public static String getCookieStr() {

        String cookie;

        StringBuilder sb = new StringBuilder();
        int i = 0;

        Iterator<Map.Entry<String, String>> iterator = Joke.mCookieMap.entrySet().iterator();

        boolean isResetCookie = false;

        while (iterator.hasNext()) {

            String key = iterator.next().getKey().trim();
            String keyForCompare = key.toLowerCase().trim();

            if ("expires".equals(keyForCompare) || "max-age".equals(keyForCompare) || "path".equals(keyForCompare) || "httponly".equals(keyForCompare)) {
                continue;
            }
            if (i > 0) {
                sb.append("; ");
            }
            LogUtil.e(TAG, "getCookieStr append value is " + Joke.mCookieMap.get(key));
            sb.append(key).append("=").append(Joke.mCookieMap.get(key));
            i++;

            // 如果值没有则从新记录相关cookie参数
            if (!Joke.mCookie.contains(Joke.mCookieMap.get(key).trim())) {

                isResetCookie = true;
            }
        }

        cookie = sb.toString();

        if (isResetCookie) {
            Joke.setCookie(cookie);
            isResetCookie = false;
        }

//        //1.如果是空，则存储cookie，内存和本地都存
//        if (TextUtils.isEmpty(Story.mCookie)) {
//
//            Story.setCookie(cookie);
//        } else {
//            // 如果不为空去除cookie
//            cookie = Story.mCookie;
//        }

        LogUtil.e(TAG, "getCookieStr is " + cookie);
        return cookie;
    }

    public static void clearCookie() {

        Joke.mCookieMap.clear();
    }
}
