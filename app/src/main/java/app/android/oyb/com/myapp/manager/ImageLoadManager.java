package app.android.oyb.com.myapp.manager;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import app.android.oyb.com.myapp.R;

/**
 * 图片加载类
 * Created by O on 2017/2/24.
 *  禁止内存缓存：
    .skipMemoryCache(true)
    清除内存缓存：
    １) 必须在UI线程中调用 Glide.get(context).clearMemory();
    禁止磁盘缓存：
    .diskCacheStrategy(DiskCacheStrategy.NONE)
    必须在后台线程中调用，建议同时clearMemory()
    Glide.get(applicationContext).clearDiskCache();
    优先加载
     .priority(Priority.HIGH)
    后加载
    .priority(Priority.LOW)
 *
 */

public class ImageLoadManager {

    public static void setImage(Context context, String url, ImageView imageView) {
        setImage(context, url, imageView, R.mipmap.ic_launcher);
    }

    public static void setImage(Context context, String url, ImageView imageView, int errorResouceId) {
        if(!TextUtils.isEmpty(url) && null != imageView) {
            Glide.with(context).load(url).error(errorResouceId).into(imageView);
        }
    }

    public static void setImage(Context context, int souceId, ImageView imageView) {
        setImage(context, souceId, imageView, R.mipmap.ic_launcher);
    }

    public static void setImage(Context context, int souceId, ImageView imageView, int errorResouceId) {
        if(null != imageView) {
            Glide.with(context).load(souceId).error(errorResouceId).into(imageView);
        }
    }

}
