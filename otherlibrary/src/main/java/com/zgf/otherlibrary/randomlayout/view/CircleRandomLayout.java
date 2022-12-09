package com.zgf.otherlibrary.randomlayout.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Scroller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * TODO：
 * 1、去重复
 * 2、确定布局宽度，高度为屏幕高度
 * 3、超过一屏可横向滑动
 */
public class CircleRandomLayout extends FrameLayout {
    private static final String TAG = "RandomLayout";

    private static final int MIN_LENGTH = 100;
    private static final int MAX_LENGTH = 500;

    private Random random;

    private Map<View, Rect> map;

    private int screenWidth;
    private int screenHeight;

    private Scroller scroller;
    private float mXDown;
    private float mXMove;
    private float mXLastMove;

    // 判断最小的移动像素
    private int mTouchSlop;

    // 界面可滚动的左边界
    private int leftBorder = 0;

    // 界面可滚动的右边界
    private int rightBorder;

    public CircleRandomLayout(@NonNull Context context) {
        super(context);

        init(context);
    }

    public CircleRandomLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public CircleRandomLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    private void init(Context context) {
        random = new Random();
        map = new HashMap<>();

        scroller = new Scroller(context);

        ViewConfiguration configuration = ViewConfiguration.get(getContext());
        // 获取TouchSlop的值
        mTouchSlop = configuration.getScaledPagingTouchSlop();

        getScreenSize();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            int size = getRandomSize();
            View child = getChildAt(i);
            ViewGroup.LayoutParams params = child.getLayoutParams();
            params.width = size;
            params.height = size;
            child.setLayoutParams(params);

            Rect rect = checkPosition(size);
            map.put(child, rect);

            width = Math.max(rect.right, width);
            // height = Math.max(rect.bottom, height);
        }

        rightBorder = width;
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            Rect rect = map.get(child);
            if (rect != null) {
                child.layout(rect.left, rect.top, rect.right, rect.bottom);
            }
        }
    }

    @Override
    public void computeScroll() {
        super.computeScroll();

        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            invalidate();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mXDown = ev.getRawX();
                mXLastMove = mXDown;
                break;
            case MotionEvent.ACTION_MOVE:
                mXMove = ev.getRawX();
                float diff = Math.abs(mXMove - mXDown);
                mXLastMove = mXMove;
                if (diff > mTouchSlop) {
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                mXMove = event.getRawX();
                int scrolledX = (int) (mXLastMove - mXMove);
                // 检查边界
                if (getScrollX() + scrolledX < leftBorder) {
                    scrollTo(leftBorder, 0);
                    return true;
                } else if (getScrollX() + scrolledX > rightBorder) {
                    scrollTo(rightBorder, 0);
                    return true;
                }

                scrollBy(scrolledX, 0);
                mXLastMove = mXMove;
                break;
            case MotionEvent.ACTION_UP:
//                int targetIndex = (getScrollX() + getWidth() / 2) / getWidth();
//                int dx = targetIndex * getWidth() - getScrollX();
//                scroller.startScroll(getScrollX(), 0, dx, 0);
//                invalidate();
                break;
        }
        return super.onTouchEvent(event);
    }

    private int getRandomSize() {
        return random.nextInt(MAX_LENGTH - MIN_LENGTH) + MIN_LENGTH;
    }

    private void getScreenSize() {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels;
    }

    private int getRandomPositionX() {
        return random.nextInt(screenWidth);
    }

    private int getRandomPositionY() {
        return random.nextInt(screenHeight);
    }

    private Rect checkPosition(int size) {
        int px = getRandomPositionX();
        int py = getRandomPositionY();
        int halfSize = size / 2;
        Rect rect;
        while ((rect = checkIsRepeat(px, py, size)) != null) {
            px += rect.right - px;
            // py += rect.bottom - py;
        }
        return new Rect(px - halfSize, py - halfSize, px + halfSize, py + halfSize);
    }

    private Rect checkIsRepeat(int x, int y, int size) {
        for (View view : map.keySet()) {
            Rect r = map.get(view);
            if (r == null) {
                continue;
            }
            int halfSize = size / 2;
            Rect bigRect = new Rect(r.left - halfSize, r.top - halfSize, r.right + halfSize, r.bottom + halfSize);
            if (bigRect.contains(x, y)) {
                return bigRect;
            }
        }
        return null;
    }

    // ========================
    private Rect getRandomRect(int size) {
        int x = getRandomPositionX();
        int y = getRandomPositionY();
        int halfSize = size / 2;
        Rect rect = new Rect(x - halfSize, y - halfSize, x + halfSize, y + halfSize);
        return rect;
    }

    private void checkRect(Rect rect) {
        for (View view : map.keySet()) {
            Rect r = map.get(view);
            if (r == null) {
                continue;
            }
            boolean repeat = isRepeat(rect, r);
            if (repeat) {
                int offset = getOffset(rect, r);
                rect.offset(offset, 0);
            }
        }
    }

    private int getOffset(Rect one, Rect two) {
        int offset = 0;
        if (two.left > one.left && two.left < one.right) {
            offset = one.right - two.left;
        } else if (two.left < one.left && two.right > one.left) {
            offset = two.right - two.left + one.right - two.right;
        } else if (two.left > one.left && two.right < one.right) {
            offset = two.right - two.left + one.right - two.right;
        } else if (two.left < one.left && two.right > one.right) {
            offset = one.right - two.left;
        }
        return offset;
    }

    private boolean isRepeat(Rect one, Rect two) {
        return (((two.left > one.left && two.left < one.right) || (two.left < one.left && two.right > one.left))
                &&
                ((two.top > one.top && two.top < one.bottom) || (two.top < one.top && two.bottom > one.top)))
                ||
                (((two.left > one.left && two.right < one.right) || (two.left < one.left && two.right > one.right))
                &&
                ((two.top > one.top && two.bottom < one.bottom) || (two.top < one.top && two.bottom > one.bottom)));
    }
}
