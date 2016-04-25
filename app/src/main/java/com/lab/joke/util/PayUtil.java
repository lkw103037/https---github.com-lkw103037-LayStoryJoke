package com.lab.joke.util;

/**
 * Created by luokaiwen on 15/7/13.
 * <p/>
 * 支付帮助类
 */
public class PayUtil {

//    private static final String TAG = PayUtil.class.getSimpleName();
//    private Activity mActivity;
//    private Handler mHandler;
//    private AlipayUtil mAlipayUtil;
//    private WXPayInfoResult mWXPayInfoResult;
//    private WXPayReceiver mWXPayReceiver;
//
//    /**
//     * 支付方式 0:支付宝, 1:微信支付
//     */
//    private String mPayWay = WKey.TypePayment.ALIPAY;
//
//    public PayUtil(Activity activity) {
//
//        mActivity = activity;
//        initPay();
//    }
//
//    public void initPay() {
//
//        // 支付宝Handler回调处理
//        mHandler = new Handler() {
//            public void handleMessage(Message msg) {
//                if (1 == msg.what) {
//                    // 支付宝返回结果处理帮助类
//                    AlipayResultUtil result = new AlipayResultUtil((String) msg.obj);
//
//                    // 获取返回的信息
//                    String info = result.getMemo();
//
//                    if (AlipayResultUtil.SUCC.equals(result.getResultStatus())) {
//
//                        // 成功
//                        if (null != mOnPayListener) {
//                            mOnPayListener.onAlipaySucc();
//                        }
//
//                    } else {
//
//                        if (null != mOnPayListener) {
//                            mOnPayListener.onAlipayFail();
//                        }
//
//                        ToastUtil.shortToast(mActivity, info);
//                    }
//                }
//            }
//
//            ;
//        };
//
//        mAlipayUtil = new AlipayUtil(mActivity, mHandler);
//
//        // 微信广播通知
//        mWXPayReceiver = new WXPayReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                super.onReceive(context, intent);
//
//                if (null != mOnPayListener) {
//
//                    int code = intent.getExtras().getInt("1");
//
//                    String result = "微信支付失败";
//
//                    switch (code) {
//                        case BaseResp.ErrCode.ERR_OK:
//
//                            if (null != mOnPayListener) {
//
//                                result = "微信支付成功";
//                                mOnPayListener.onWxpaySucc();
//                            }
//
//                            break;
//
//                        case BaseResp.ErrCode.ERR_USER_CANCEL:
//
//                            if (null != mOnPayListener) {
//
//                                result = "取消微信支付";
//                                mOnPayListener.onWxpayFail();
//                            }
//
//                            break;
//
//                        case BaseResp.ErrCode.ERR_AUTH_DENIED:
//
//                            if (null != mOnPayListener) {
//                                result = "微信支付失败";
//                                mOnPayListener.onWxpayFail();
//                            }
//
//                            break;
//
//                        default:
//
//                            if (null != mOnPayListener) {
//                                result = "微信支付失败";
//                                mOnPayListener.onWxpayFail();
//                            }
//
//                            break;
//                    }
//
//                    Toast.makeText(mActivity, result, Toast.LENGTH_LONG).show();
//                }
//            }
//        };
//    }
//
//    public String getOrderInfo(String orderId, String orderPrice, String name) {
//
//        String orderInfo = mAlipayUtil.getOrderInfo(orderId, orderPrice, name);
//
//        if (TextUtils.isEmpty(orderInfo)) {
//            orderInfo = "";
//        }
//
//        return orderInfo;
//    }
//
//    /**
//     * 注册支付信息
//     */
//    public void registPay() {
//
//        // 注册广播接收器（动态注册）
//        IntentFilter filter = new IntentFilter();
//        filter.addAction(WXPayReceiver.ACTION_WXPAY);
//        mActivity.registerReceiver(mWXPayReceiver, filter);
//    }
//
//    /**
//     * 解绑支付信息
//     */
//    public void unregistPay() {
//
//        mActivity.unregisterReceiver(mWXPayReceiver);
//    }
//
//    /**
//     * 调起微信支付
//     *
//     * @param result 服务端返回的微信支付参数结果
//     */
//    public void doWxpay(Result result) {
//
//        mWXPayInfoResult = (WXPayInfoResult) result;
//
//        String appId = Constant.WX_APP_ID;
//
//        IWXAPI api = WXAPIFactory.createWXAPI(mActivity, appId, false);
//        api.handleIntent(mActivity.getIntent(), new WXPayEntryActivity());
//        api.openWXApp();
//
//        boolean isInstalled = api.isWXAppInstalled();
//
//        if (!isInstalled) {
//
//            if (null != mOnPayListener) {
//                mOnPayListener.onWxpayFail();
//            }
//
//            ToastUtil.shortToast(mActivity, "您没有安装微信，不能进行微信支付");
//            return;
//        }
//
//        boolean isPaySupported = api.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
//
//        if (isPaySupported) {
//            // 发送微信支付
//
//            WXPayInfo payInfo = mWXPayInfoResult.getData();
//
//            PayReq req = new PayReq();
//
//            req.appId = appId;
//            req.partnerId = payInfo.getPartnerId();
//            req.prepayId = payInfo.getPrepayId();
//            req.nonceStr = payInfo.getNonStr();
//            req.timeStamp = payInfo.getTimeStamp();
//            req.packageValue = payInfo.getPackageName();
//            req.sign = payInfo.getSign();
//
//            // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
//            boolean isRegister = api.registerApp(appId);
//            LogUtil.e("", "isRegister:" + isRegister);
//
//            boolean isSend = api.sendReq(req);
//            LogUtil.e("", "isSend:" + isSend);
//        } else {
//
//            if (null != mOnPayListener) {
//                mOnPayListener.onWxpayFail();
//            }
//
//            ToastUtil.shortToast(mActivity, "此版本微信不支持微信支付");
//        }
//    }
//
//    /**
//     * 调起支付宝支付
//     *
//     * @param result 服务器返回的请求字符串的签名字符串
//     */
//    public void doAlipay(Result result) {
//
//        // 拿到签名支付信息，然后调Alipay
//        String sign = ((OrderPaySignResult) result).getData();
//
//        // 调用支付宝支付
//        mAlipayUtil.pay(sign);
//    }
//
//    /**
//     * 获取支付方式
//     *
//     * @return
//     */
//    public String getPayWay() {
//        return mPayWay;
//    }
//
//    /**
//     * 设置支付方式
//     *
//     * @param mPayWay
//     */
//    public void setPayWay(String mPayWay) {
//        this.mPayWay = mPayWay;
//    }
//
//    private OnPayListener mOnPayListener;
//
//    public void setOnPayListener(OnPayListener onPayListener) {
//        mOnPayListener = onPayListener;
//    }
//
//    public interface OnPayListener {
//
//        void onAlipaySucc();
//
//        void onAlipayFail();
//
//        void onWxpaySucc();
//
//        void onWxpayFail();
//    }
}
