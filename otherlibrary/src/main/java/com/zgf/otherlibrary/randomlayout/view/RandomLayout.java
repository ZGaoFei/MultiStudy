package com.zgf.otherlibrary.randomlayout.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Random;

public class RandomLayout extends FrameLayout {
    private static final String TAG = "RandomLayout";

    private static final int MIN_LENGTH = 100;
    private static final int MAX_LENGTH = 500;

    private Random random;

    public RandomLayout(@NonNull Context context) {
        super(context);

        init(context);
    }

    public RandomLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public RandomLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    private void init(Context context) {
        random = new Random();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            int length = getRandomLength();
            View child = getChildAt(i);
            ViewGroup.LayoutParams params = child.getLayoutParams();
            params.width = length;
            params.height = length;
            child.setLayoutParams(params);
        }

        setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        int childPaddingLeft = 0;
        int childPaddingTop = 0;

        int h = bottom - top;

        int maxWidth = 0;

        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);

            final int width = child.getMeasuredWidth();
            final int height = child.getMeasuredHeight();

            maxWidth = Math.max(width, maxWidth);

            if (childPaddingTop + height > h) {
                childPaddingLeft += width;
                childPaddingTop = 0;
            }
            child.layout(childPaddingLeft, childPaddingTop, childPaddingLeft + width, childPaddingTop + height);

            childPaddingTop += height;
        }
    }

    private int getRandomLength() {
        return random.nextInt(MAX_LENGTH - MIN_LENGTH) + MIN_LENGTH;
    }
}
