package ru.yandex.yamblz.ui.fragments;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by dan on 03.08.16.
 */

public class ItemDecorator extends RecyclerView.ItemDecoration {
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        int curLen = parent.getChildCount();
        int strokeWidth = 10;
        View curView;
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);

        for (int i = 0; i < curLen; i++) {
            curView = parent.getChildAt(i);
            if (parent.getChildAdapterPosition(curView) % 2 == 0) {
                c.drawRect(layoutManager.getDecoratedLeft(curView) + strokeWidth / 2, layoutManager.getDecoratedTop(curView) + strokeWidth / 2,
                        layoutManager.getDecoratedRight(curView) - strokeWidth / 2, layoutManager.getDecoratedBottom(curView) - strokeWidth / 2, paint);
            }
        }

    }


}
