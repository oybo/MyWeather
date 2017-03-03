package app.android.oyb.com.myapp.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import app.android.oyb.com.myapp.R;
import app.android.oyb.com.myapp.manager.Commons;
import app.android.oyb.com.myapp.manager.PreferenceUtils;

/** 欢迎页
 * Created by O on 2017/3/2.
 */

public class WelcomeActivity extends Activity {

    private static Handler mHandler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        goMain();
    }

    private void goMain() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(PreferenceUtils.getInstance().getBoolean(Commons.IS_GUIDE_KEY, true)) {
                    startActivity(new Intent(WelcomeActivity.this, GuideActivity.class));
                } else {
                    startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                }
                finish();
            }
        }, 1200);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();

    }
}
