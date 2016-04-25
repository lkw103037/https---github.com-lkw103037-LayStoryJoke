package com.lab.joke.util;

/**
 * Created by luokaiwen on 15/4/30.
 * <p/>
 * 分享帮助类
 */
public class ShareUtil {

    private static final String TAG = ShareUtil.class.getSimpleName();

    public static final String WEIBO = "0";
    public static final String WX_CIRCLE = "1";
    public static final String QZONE = "2";
    public static final String WX = "3";
    public static final String QQ = "4";

//    private static ShareInfo mShareInfo;
//
//    private Context mContext;
//
//    public ShareUtil(Context context, ShareInfo shareInfo) {
//        mContext = context;
//    }
//
//    public static OnShareListener mOnShareListener;
//
//    public static void setOnShareListener(OnShareListener onShareListener) {
//        mOnShareListener = onShareListener;
//    }
//
//    /**
//     * 获取分享服务
//     */
//    public static UMSocialService getSocialService() {
//        return UMServiceFactory.getUMSocialService("com.umeng.share", RequestType.SOCIAL);
//    }
//
//    /**
//     * 分享到新浪微博
//     */
//    public static void shareSina(final Context context, ShareInfo shareInfo) {
//
//        mShareInfo = shareInfo;
//
//        LogUtil.e(TAG, "shareInfo weibo:" + shareInfo);
//        // 新浪微博
//        //getSocialService().getConfig().setSinaCallbackUrl("http://sns.whalecloud.com/sina2/callback");
//        getSocialService().getConfig().setSsoHandler(new SinaSsoHandler());
//
//        SinaShareContent sinaShareContent = new SinaShareContent();
//        //sinaShareContent.setTitle(mShareInfo.getTitle());
//        sinaShareContent.setShareContent(mShareInfo.getText() + mShareInfo.getUrl());
//        sinaShareContent.setTargetUrl(mShareInfo.getUrl());
//        sinaShareContent.setShareImage(handleImageUrl(context, mShareInfo.getImage()));
//        getSocialService().setShareMedia(sinaShareContent);
//
//        //TODO 更新友盟分享SDK后 添加图片分享不成功 等待友盟回复 sinaShareContent.setShareImage(umImage);
//        getSocialService().postShare(context, SHARE_MEDIA.SINA, new SocializeListeners.SnsPostListener() {
//
//            @Override
//            public void onStart() {
//
//                if (null != mOnShareListener) {
//                    mOnShareListener.onShareStart(WEIBO);
//                }
//            }
//
//            @Override
//            public void onComplete(SHARE_MEDIA share_media, int i, SocializeEntity socializeEntity) {
//
//                LogUtil.e(TAG, "i is:" + i + " socializeEntity:" + socializeEntity.toString());
//
//                if (null != mOnShareListener) {
//
//                    if (i == 200) {
//                        mOnShareListener.onShareSucc(WEIBO);
//                    } else {
//                        mOnShareListener.onShareFail(WEIBO);
//                    }
//                }
//            }
//        });
//    }
//
//    /**
//     * 分享到QQzone
//     */
//    public static void shareQQzone(final Context context, ShareInfo shareInfo) {
//
//        mShareInfo = shareInfo;
//        LogUtil.e(TAG, "shareInfo qzone:" + shareInfo);
//        // QQ空间
////        QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler((Activity) context, "1104434134", "aPC6wuXBuy1cxtQ2");
////        qZoneSsoHandler.addToSocialSDK();
//        getSocialService().getConfig().setSsoHandler(new QZoneSsoHandler((Activity) context, "1104434134", "aPC6wuXBuy1cxtQ2"));
//        QZoneShareContent qzone = new QZoneShareContent();
//        //qzone.setTitle(mShareInfo.getTitle());
//        qzone.setShareContent(mShareInfo.getText());
//        qzone.setTargetUrl(mShareInfo.getUrl());
//        qzone.setShareImage(handleImageUrl(context, mShareInfo.getImage()));
//
//        UMSocialService socialService = getSocialService();
//        socialService.setShareMedia(qzone);
//        socialService.getConfig().closeToast();
//        socialService.directShare(context, SHARE_MEDIA.QZONE, new SocializeListeners.SnsPostListener() {
//            @Override
//            public void onStart() {
//
//                if (null != mOnShareListener) {
//                    mOnShareListener.onShareStart(QZONE);
//                }
//            }
//
//            @Override
//            public void onComplete(SHARE_MEDIA share_media, int i, SocializeEntity socializeEntity) {
//
//                LogUtil.e(TAG, "i is:" + i + " socializeEntity:" + socializeEntity.toString());
//
//                if (null != mOnShareListener) {
//                    if (i == StatusCode.ST_CODE_SUCCESSED) {
//                        mOnShareListener.onShareSucc(QZONE);
//                        ToastUtil.shortToast(context, "分享成功");
//                    } else {
//                        mOnShareListener.onShareFail(QZONE);
//                    }
//                }
//            }
//        });
//    }
//
//    /**
//     * 分享到QQ好友
//     */
//    public static void shareQQ(final Context context, ShareInfo shareInfo) {
//        mShareInfo = shareInfo;
//        LogUtil.e(TAG, "shareInfo qq:" + shareInfo);
//        UMQQSsoHandler uMQQSsoHandler = new UMQQSsoHandler((Activity) context, "1103263639", "SpEhxdqDqs9dMpiA");
//        uMQQSsoHandler.addToSocialSDK();
//
//        QQShareContent qqContent = new QQShareContent();
//        //qqContent.setTitle(mShareInfo.getTitle());
//        qqContent.setShareContent(mShareInfo.getText());
//        qqContent.setTargetUrl(mShareInfo.getUrl());
//        qqContent.setShareImage(handleImageUrl(context, mShareInfo.getImage()));
//
//        getSocialService().setShareMedia(qqContent);
//
//        getSocialService().postShare(context, SHARE_MEDIA.QQ, new SocializeListeners.SnsPostListener() {
//            @Override
//            public void onStart() {
//
//                if (null != mOnShareListener) {
//                    mOnShareListener.onShareStart(QQ);
//                }
//            }
//
//            @Override
//            public void onComplete(SHARE_MEDIA share_media, int i, SocializeEntity socializeEntity) {
//
//                LogUtil.e(TAG, "i is:" + i + " socializeEntity:" + socializeEntity.toString());
//
//                if (null != mOnShareListener) {
//                    if (i == 200) {
//                        mOnShareListener.onShareSucc(QQ);
//                    } else {
//                        mOnShareListener.onShareFail(QQ);
//                    }
//                }
//            }
//        });
//    }
//
//    /**
//     * 分享到微信好友
//     */
//    public static void shareWeiXin(final Context context, ShareInfo shareInfo) {
//        mShareInfo = shareInfo;
//        LogUtil.e(TAG, "shareInfo wechat:" + shareInfo);
//        // 微信
//        UMWXHandler uMWXHandler = new UMWXHandler(context, "wx6b65a024f0a4e70c", "5cf62b61879335b063892d70572f2239");
//        uMWXHandler.addToSocialSDK();
//
//        WeiXinShareContent weixinContent = new WeiXinShareContent();
//        //weixinContent.setTitle(mShareInfo.getTitle());
//        weixinContent.setShareContent(mShareInfo.getText());
//        weixinContent.setTargetUrl(mShareInfo.getUrl());
//        weixinContent.setShareImage(handleImageUrl(context, mShareInfo.getImage()));
//
//        getSocialService().setShareMedia(weixinContent);
//
//        getSocialService().postShare(context, SHARE_MEDIA.WEIXIN, new SocializeListeners.SnsPostListener() {
//            @Override
//            public void onStart() {
//
//                if (null != mOnShareListener) {
//                    mOnShareListener.onShareStart(WX);
//                }
//            }
//
//            @Override
//            public void onComplete(SHARE_MEDIA share_media, int i, SocializeEntity socializeEntity) {
//
//                LogUtil.e(TAG, "i is:" + i + " socializeEntity:" + socializeEntity.toString());
//
//                if (null != mOnShareListener) {
//                    if (i == 200) {
//                        mOnShareListener.onShareSucc(WX);
//                    } else {
//                        mOnShareListener.onShareFail(WX);
//                    }
//                }
//            }
//        });
//    }
//
//    /**
//     * 分享到微信朋友圈
//     */
//    public static void shareWXCircle(final Context context, ShareInfo shareInfo) {
//
//        mShareInfo = shareInfo;
//        LogUtil.e(TAG, "shareInfo wechatCircle:" + shareInfo);
//        // 微信朋友圈
//        UMWXHandler uMWXCircleHandler = new UMWXHandler(context, "wx6b65a024f0a4e70c", "5cf62b61879335b063892d70572f2239");
//        uMWXCircleHandler.setToCircle(true);
//        uMWXCircleHandler.addToSocialSDK();
//
//        CircleShareContent circleMedia = new CircleShareContent();
//        circleMedia.setTitle(mShareInfo.getText());// 不能去掉
//        circleMedia.setShareContent(mShareInfo.getText());
//        circleMedia.setTargetUrl(mShareInfo.getUrl());
//        circleMedia.setShareImage(handleImageUrl(context, mShareInfo.getImage()));
//
//        getSocialService().setShareMedia(circleMedia);
//        getSocialService().postShare(context, SHARE_MEDIA.WEIXIN_CIRCLE, new SocializeListeners.SnsPostListener() {
//            @Override
//            public void onStart() {
//
//                if (null != mOnShareListener) {
//                    mOnShareListener.onShareStart(WX_CIRCLE);
//                }
//            }
//
//            @Override
//            public void onComplete(SHARE_MEDIA share_media, int i, SocializeEntity socializeEntity) {
//
//                LogUtil.e(TAG, "i is:" + i + " socializeEntity:" + socializeEntity.toString());
//
//                if (null != mOnShareListener) {
//                    if (i == 200) {
//                        mOnShareListener.onShareSucc(WX_CIRCLE);
//                    } else {
//                        mOnShareListener.onShareFail(WX_CIRCLE);
//                    }
//                }
//            }
//        });
//    }
//
//    public static UMImage handleImageUrl(Context context, String image) {
//
//        if (TextUtils.isEmpty(image)) {
//            return null;
//        }
//
//        if (!image.contains(ShareInfoUtil.IMAGE_HEAD)) {
//            image = ShareInfoUtil.IMAGE_HEAD + image;
//        }
//        LogUtil.e(TAG, "image:" + image);
//
//        return new UMImage(context, image);
//    }
//
//    public interface OnShareListener {
//
//        void onShareStart(String type);
//
//        void onShareSucc(String type);
//
//        void onShareFail(String type);
//    }
}
