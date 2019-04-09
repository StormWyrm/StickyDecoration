package com.github.StormWrym.stickydecoration.lib;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StickyItemDecoration extends RecyclerView.ItemDecoration {
    private int mHeight = 60;
    private Paint defaultPaint;
    private Paint stickyPaint;

    public StickyItemDecoration() {
        super();
        defaultPaint = new Paint();
        defaultPaint.setStyle(Paint.Style.FILL);
        defaultPaint.setColor(Color.GREEN);

        stickyPaint = new Paint();
        stickyPaint.setStyle(Paint.Style.FILL);
        stickyPaint.setColor(Color.RED);
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount = parent.getChildCount();//这里不是recyclerView的总数量，而是可见数量
        int left = parent.getLeft();
        int right = parent.getRight();
        int top, bottom;
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            bottom = view.getTop();
            top = bottom - mHeight;
            c.drawRect(left, top, right, bottom,defaultPaint );
        }
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        View child0 = parent.getChildAt(0);

        //如果第一个item的Bottom<=分割线的高度
        if (child0.getBottom() <= mHeight) {
            //随着RecyclerView滑动 分割线的top=固定为0不动,bottom则赋值为child0的bottom值.
            c.drawRect(0, 0, parent.getRight(),child0.getBottom() , stickyPaint);
        } else {
            //固定不动
            c.drawRect(0, 0, parent.getRight(), 60, stickyPaint);
        }
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.top = mHeight;
    }
}
