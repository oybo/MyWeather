package app.android.oyb.com.myapp.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.widget.TextView;
import java.lang.reflect.Field;
import app.android.oyb.com.myapp.R;

/**
 * Created by O on 2017/3/16.
 */

public class WeatherTemperatureView extends TextView {

    public WeatherTemperatureView(Context context) {
        this(context, null);
    }

    public WeatherTemperatureView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {

    }

    public void setTemperature(String temperature) {
        if (!TextUtils.isEmpty(temperature)) {
            SpannableString spannableString = new SpannableString(temperature);
            char[] cs = temperature.toCharArray();
            int len = cs.length;
            for (int i = 0; i < len; i++) {
                //得到要显示图片的资源
                try {
                    Field field = R.drawable.class.getDeclaredField("nw" + cs[i]);
                    int resId = Integer.parseInt(field.get(null).toString());

                    Bitmap b = BitmapFactory.decodeResource(getResources(), resId);
                    ImageSpan imgSpan = new ImageSpan(getContext(), b);

                    //附加图片
                    spannableString.setSpan(imgSpan, i, i + 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            setText(spannableString);
        }
    }

}
