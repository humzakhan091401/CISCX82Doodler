package com.example.ciscx82doodler;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DoodleView extends View {
    private Paint paint;
    private Path path;
    private int brushColor = 0xFF000000; // Default: Black
    private float brushSize = 10f;

    public DoodleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(brushColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(brushSize);
        paint.setAntiAlias(true);

        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(x, y);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        invalidate(); // Redraw the view
        return true;
    }

    public void clearCanvas() {
        path.reset();
        invalidate();
    }

    public void setBrushColor(int color) {
        brushColor = color;
        paint.setColor(brushColor);
    }

    public void increaseBrushSize() {
        brushSize += 5f;
        paint.setStrokeWidth(brushSize);
    }

    public void decreaseBrushSize() {
        brushSize = Math.max(5f, brushSize - 5f); // Prevent brush size from becoming too small
        paint.setStrokeWidth(brushSize);
    }
}
