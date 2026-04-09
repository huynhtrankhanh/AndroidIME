package com.huynhtrankhanh.androidime;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class RedCircleView extends View {
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public RedCircleView(Context context) {
        super(context);
        init();
    }

    public RedCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float radius = Math.min(getWidth(), getHeight()) * 0.25f;
        canvas.drawCircle(getWidth() / 2f, getHeight() / 2f, radius, paint);
    }
}
