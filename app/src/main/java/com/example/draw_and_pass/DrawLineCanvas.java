package com.example.draw_and_pass;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DrawLineCanvas extends View {

    private Canvas c;

    private Paint pLine, pBg;
    private Path touchPath;

    private Bitmap b;

    public DrawLineCanvas(Context context) {
        super(context);
    }

    public DrawLineCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);

        pBg = new Paint();
        pBg.setColor(0xff000000);

        pLine = new Paint();
        pLine.setColor(0xff000000);
        pLine.setAntiAlias(true);
        pLine.setStyle(Paint.Style.STROKE);
        pLine.setStrokeWidth(12);

        touchPath = new Path();
    }

    public DrawLineCanvas(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        b = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        c = new Canvas(b);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float touchX = event.getX();
        float touchY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchPath.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                touchPath.lineTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:
                touchPath.lineTo(touchX, touchY);
                c.drawPath(touchPath, pLine);
                touchPath = new Path();
                break;
            default:
                return false;
        }

        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(b, 0, 0, pBg);
        canvas.drawPath(touchPath, pLine);
    }
    public void setColor(int color){
        pLine.setColor(color);
    }
    public void setSize(int size){
        pLine.setStrokeWidth(size);
    }

    public Bitmap getB() {
        return b;
    }
}
