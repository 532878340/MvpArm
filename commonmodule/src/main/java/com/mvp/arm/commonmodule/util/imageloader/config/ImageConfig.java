package com.mvp.arm.commonmodule.util.imageloader.config;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mvp.arm.commonmodule.util.ScreenUtils;
import com.mvp.arm.commonmodule.util.imageloader.utils.ImageUtils;

import java.io.File;

/**
 * Description:加载配置
 * @author Zijin
 * @date 2017/8/1
 */

public class ImageConfig {
    private Context mContext;
    private String mUrl;
    private View mTarget;
    private boolean mAsBitmap;
    private String mFilePath;
    private boolean mIsGif;
    private float mThumbnail;
    private File mFile;
    private int mResId;
    private String mContentProvider;
    private String mRawPath;
    private String mAssertspath;
    private int mErrorResId;
    private int mPlaceHolderResId;
    private int mWidth;
    private int mHeight;
    private int mPriorityMode;
    private int mScaleMode;
    private BitmapListener mBitmapListener;
    private DiskCacheStrategy mDiskCacheStrategy;

    public ImageConfig(ConfigBuilder builder) {
        this.mContext = builder.context;
        this.mUrl = builder.url;
        this.mTarget = builder.target;
        this.mAsBitmap = builder.asBitmap;
        this.mFilePath = builder.filePath;
        this.mIsGif = builder.isGif;
        this.mThumbnail = builder.thumbnail;
        this.mFile = builder.file;
        this.mResId = builder.resId;
        this.mContentProvider = builder.contentProvider;
        this.mRawPath = builder.rawPath;
        this.mAssertspath = builder.assertspath;
        this.mErrorResId = builder.errorResId;
        this.mPlaceHolderResId = builder.placeHolderResId;
        this.mPriorityMode = builder.priorityMode;
        this.mScaleMode = builder.scaleMode;
        this.mBitmapListener = builder.bitmapListener;
        this.mDiskCacheStrategy = builder.diskCacheStrategy;
    }

    public Context getContext() {
        return mContext;
    }

    public String getUrl() {
        return mUrl;
    }

    public View getTarget() {
        return mTarget;
    }

    public boolean isAsBitmap() {
        return mAsBitmap;
    }

    public String getFilePath() {
        return mFilePath;
    }

    public boolean isGif() {
        return mIsGif;
    }

    public float getThumbnail() {
        return mThumbnail;
    }

    public File getFile() {
        return mFile;
    }

    public int getResId() {
        return mResId;
    }

    public String getContentProvider() {
        return mContentProvider;
    }

    public String getRawPath() {
        return mRawPath;
    }

    public String getAssertspath() {
        return mAssertspath;
    }

    public int getErrorResId() {
        return mErrorResId;
    }

    public int getPlaceHolderResId() {
        return mPlaceHolderResId;
    }

    public int getWidth(){
        if(mWidth <= 0){
            if(mTarget != null){
                mWidth = mTarget.getMeasuredWidth();
            }

            if(mWidth <= 0){
                mWidth = ScreenUtils.getScreenWidth(mContext);
            }
        }
        return mWidth;
    }

    public int getHeight(){
        if(mHeight <= 0){
            if(mTarget != null){
                mHeight = mTarget.getMeasuredHeight();
            }

            if(mHeight <= 0){
                mHeight = ScreenUtils.getScreenHeight(mContext);
            }
        }
        return mHeight;
    }

    public int getPriorityMode() {
        return mPriorityMode;
    }

    public int getScaleMode() {
        return mScaleMode;
    }

    public BitmapListener getBitmapListener() {
        return mBitmapListener;
    }

    public DiskCacheStrategy getDiskCacheStrategy(){
        return mDiskCacheStrategy;
    }

    /**
     * 是否显示占位图
     * 只有在图片源为网络图片,并且图片没有缓存到本地时,才给显示placeholder
     */
    public boolean shouldSetPlaceHolder(){
        return mPlaceHolderResId > 0 &&
                !TextUtils.isEmpty(mUrl) &&
                !GlobalConfig.getLoader().isCached(mUrl);
    }

    public void show() {
        GlobalConfig.getLoader().load(this);
    }

    public interface BitmapListener {
        /**
         * 加载成功
         */
        void onSuccess(Bitmap bitmap);

        /**
         * 加载失败
         */
        void onFail();
    }

    public static class ConfigBuilder {
        /**
         * 图片源
         * 类型	SCHEME	示例
         * 远程图片	            http://, https://	HttpURLConnection 或者参考 使用其他网络加载方案
         * 本地文件	            file://	FileInputStream
         * Content provider	    content://	ContentResolver
         * asset目录下的资源	    asset://	AssetManager
         * res目录下的资源	        res://	Resources.openRawResource
         * Uri中指定图片数据	    data:mime/type;base64,	数据类型必须符合 rfc2397规定 (仅支持 UTF-8)
         */

        //上下文
        private Context context;
        //图片路径
        private String url;
        //目标视图
        private View target;
        //错误图
        private int errorResId;
        //只获取bitmap
        private boolean asBitmap;
        //文件路径
        private String filePath;
        private boolean isGif;                  //是否为gif
        private float thumbnail;                //缩略图比例
        private File file;                      //文件
        private int resId;                      //资源id
        private String contentProvider;         //内容提供者
        private String rawPath;                 //raw资源
        private String assertspath;             //assert资源
        private int placeHolderResId;           //占位图
        private int priorityMode;     //加载优先级
        private int width;                      //宽
        private int height;                     //高
        private int scaleMode;            //缩放模式

        private BitmapListener bitmapListener;  //图片加载监听
        private DiskCacheStrategy diskCacheStrategy;//磁盘存储策略

        public ConfigBuilder(Context context) {
            this.context = context;
        }

        /**
         * 缩略图
         */
        public ConfigBuilder thumbnail(float thumbnail) {
            this.thumbnail = thumbnail;
            return this;
        }

        /**
         * url
         */
        public ConfigBuilder url(String url) {
            this.url = url;
            if (url.contains("gif")) {
                isGif = true;
            }
            return this;
        }

        /**
         * error图
         */
        public ConfigBuilder error(int errorResId) {
            this.errorResId = errorResId;
            return this;
        }

        /**
         * 加载SD卡资源
         */
        public ConfigBuilder file(String filePath) {
            if (filePath.startsWith("file:")) {
                this.filePath = filePath;
                return this;
            }

            if (!new File(filePath).exists()) {
                Log.e("imageloader", "文件不存在");
                return this;
            }

            this.filePath = filePath;
            if (filePath.contains("gif")) {
                isGif = true;
            }
            return this;
        }

        /**
         * 加载SD卡资源
         */
        public ConfigBuilder file(File file) {
            this.file = file;
            return this;
        }

        /**
         * 加载drawable资源
         */
        public ConfigBuilder res(int resId) {
            this.resId = resId;
            return this;
        }

        /**
         * 加载ContentProvider资源
         */
        public ConfigBuilder content(String contentProvider) {
            if (contentProvider.startsWith("content:")) {
                this.contentProvider = contentProvider;
                return this;
            }

            if (contentProvider.contains("gif")) {
                isGif = true;
            }

            return this;
        }

        /**
         * 加载raw资源
         */
        public ConfigBuilder raw(String rawPath) {
            this.rawPath = rawPath;
            if (rawPath.contains("gif")) {
                isGif = true;
            }

            return this;
        }

        /**
         * 加载asserts资源
         */
        public ConfigBuilder asserts(String assertspath) {
            this.assertspath = assertspath;
            if (assertspath.contains("gif")) {
                isGif = true;
            }

            return this;
        }

        /**
         * 占位图
         */
        public ConfigBuilder placeHolder(int placeHolderResId) {
            this.placeHolderResId = placeHolderResId;
            return this;
        }

        /**
         * 加载优先级
         */
        public ConfigBuilder priority(int priorityMode){
            this.priorityMode = priorityMode;
            return this;
        }

        /**
         * 设置缩放模式
         */
        public ConfigBuilder scale(int scaleMode){
            this.scaleMode = scaleMode;
            return this;
        }

        /**
         * 设置缓存策略
         */
        public ConfigBuilder setDiskCacheStrategy(DiskCacheStrategy diskCacheStrategy){
            this.diskCacheStrategy = diskCacheStrategy;
            return this;
        }

        public void into(View targetView) {
            this.target = targetView;
            new ImageConfig(this).show();
        }

        /**
         * 仅Bitmap回调
         */
        public void asBitmap(BitmapListener bitmapListener) {
            this.bitmapListener = ImageUtils.getBitmapListenerProxy(bitmapListener);
            this.asBitmap = true;
            new ImageConfig(this).show();
        }
    }
}
