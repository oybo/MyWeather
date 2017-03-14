package app.android.oyb.com.myapp.widget;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

/**
 * Created by yungcs on 2016/1/7.
 */
public class MyNestedScrollView extends NestedScrollView {

    private int downX;
    private int downY;
    private int mTouchSlop;

    public MyNestedScrollView(Context context) {
        super(context);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        init();
    }

    public MyNestedScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        init();
    }

    public MyNestedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        init();
    }

    private void init() {
        // 设置ScrollVIew不会因为输入框问题闪动
        smoothScrollTo(0, 0);
        setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
        setFocusable(true);
        setFocusableInTouchMode(true);
//        setOnTouchListener(new OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                v.requestFocusFromTouch();
//                return false;
//            }
//        });
    }

//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent e) {
//        int action = e.getAction();
//        switch (action) {
//            case MotionEvent.ACTION_DOWN:
//                downX = (int) e.getRawX();
//                downY = (int) e.getRawY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                int moveY = (int) e.getRawY();
//                if (Math.abs(moveY - downY) > mTouchSlop) {
//                    return true;
//                }
//        }
//        return super.onInterceptTouchEvent(e);
//    }
}