package com.github.StormWrym.stickydecoration.lib;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DividerItemDecoration extends RecyclerView.ItemDecoration {
    private Paint mPaint;
    private int leftMargin;
    private int rightMargin;
    private int dividerHeight;

    public DividerItemDecoration() {
        this(0, 0, 5);
    }

    public DividerItemDecoration(int dividerHeight) {
        this(dividerHeight, 0, 0);
    }

    public DividerItemDecoration(int dividerHeight, int dividerColor) {
        this(dividerHeight, dividerColor, 0, 0);
    }

    public DividerItemDecoration(int dividerHeight, int leftMargin, int rightMargin) {
        this(dividerHeight, Color.parseColor("#dddddd"), 0, 0);
    }

    public DividerItemDecoration(int dividerHeight, int dividerColor, int leftMargin, int rightMargin) {
        this.leftMargin = leftMargin;
        this.rightMargin = rightMargin;
        this.dividerHeight = dividerHeight;
        mPaint = new Paint();
        mPaint.setColor(dividerColor);
        mPaint.setStrokeWidth(dividerHeight);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = dividerHeight;//设置偏移量
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
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
}
