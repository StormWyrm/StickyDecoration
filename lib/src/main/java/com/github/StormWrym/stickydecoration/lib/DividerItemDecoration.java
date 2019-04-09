package com.github.StormWrym.stickydecoration.lib;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DividerItemDecoration extends RecyclerView.ItemDecoration {
    public static final int HORIZONTAL = LinearLayout.HORIZONTAL;
    public static final int VERTICAL = LinearLayout.VERTICAL;

    private Paint mPaint;
    private int leftMargin;
    private int rightMargin;
    private int dividerHeight;
    private int mOrientation;//方向

    public DividerItemDecoration() {
        this(5);
    }

    public DividerItemDecoration(int dividerHeight) {
        this(dividerHeight, Color.parseColor("#dddddd"));
    }

    public DividerItemDecoration(int dividerHeight, int dividerColor) {
        this(dividerHeight, dividerColor,VERTICAL);
    }

    public DividerItemDecoration(int dividerHeight, int dividerColor,int orientation) {
        this.leftMargin = leftMargin;
        this.rightMargin = rightMargin;
        this.dividerHeight = dividerHeight;
        if (orientation != HORIZONTAL && orientation != VERTICAL) {
            throw new IllegalArgumentException(
                    "Invalid orientation. It should be either HORIZONTAL or VERTICAL");
        }
        this.mOrientation = orientation;
        mPaint = new Paint();
        mPaint.setColor(dividerColor);
        mPaint.setStrokeWidth(dividerHeight);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        //设置偏移量
        if (mOrientation == VERTICAL) {
            outRect.set(0, 0, 0, dividerHeight);
        } else {
            outRect.set(0, 0, dividerHeight, 0);
        }
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (mOrientation == VERTICAL)
            drawVertical(c, parent);
        else
            drawHorizontal(c, parent);
    }

    public void setOrientation(int orientation) {
        if (orientation != HORIZONTAL && orientation != VERTICAL) {
            throw new IllegalArgumentException(
                    "Invalid orientation. It should be either HORIZONTAL or VERTICAL");
        }
        this.mOrientation = orientation;
    }

    private void drawVertical(@NonNull Canvas c, @NonNull RecyclerView parent) {
        final int left = parent.getLeft() + leftMargin;
        final int right = parent.getRight() + rightMargin;
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            int top = view.getBottom();
            int bottom = top + dividerHeight;
            c.drawRect(left, top, right, bottom, mPaint);
        }
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        int top = parent.getTop() + leftMargin;
        int bottom = parent.getBottom() + rightMargin;
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            int left = view.getRight();
            int right = left + dividerHeight;
            c.drawRect(left, top, right, bottom, mPaint);
        }
    }
}
