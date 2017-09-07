package app.android.oyb.com.myapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.ImageView;

import app.android.oyb.com.myapp.R;
import app.android.oyb.com.myapp.manager.PreferenceUtils;
import app.android.oyb.com.myapp.ui.BaseActivity;
import app.android.oyb.com.myapp.utils.Constants;
import app.android.oyb.com.myapp.widget.guide.DropIndicator;
import app.android.oyb.com.myapp.widget.guide.DropViewPager;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 向导页
 * Created by O on 2017/3/3.
 */

public class GuideActivity extends BaseActivity {

    @Bind(R.id.mViewPager)
    DropViewPager mViewPager;
    @Bind(R.id.circleIndicator)
    DropIndicator circleIndicator;

    private static int[] images = new int[]{R.drawable.guide_a, R.drawable.guide_b, R.drawable.guide_c, R.drawable.guide_d};
    private GestureDetector mDetector;
    private PagerAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatuBar();
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);

        initView();
        PreferenceUtils.getInstance().putBoolen(Constants.IS_GUIDE_KEY, false);
    }

    private void initView() {
        mAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return images.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView imageView = new ImageView(getBaseContext());
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setImageResource(images[position]);
                container.addView(imageView);
                return imageView;
            }

        };

        mViewPager.setOffscreenPageLimit(images.length);
        mViewPager.setAdapter(mAdapter);
        circleIndicator.setViewPager(mViewPager);

        mDetector = new GestureDetector(this, new MyGestureListener());

        mViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mDetector.onTouchEvent(event);
            }
        });
    }

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        private int mTouchSlop;

        public MyGestureListener() {
            mTouchSlop = ViewConfiguration.get(GuideActivity.this).getScaledTouchSlop();
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            //  用户按下触摸屏、快速移动后松开
            try {
                if(mViewPager.getCurrentItem() == images.length - 1) {
                    if(e1.getX() - e2.getX() > mTouchSlop && Math.abs(velocityX) > 0) {
                        startActivity(new Intent(GuideActivity.this, MainActivity.class));
                        finish();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }
    }

}
