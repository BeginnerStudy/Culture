package com.practice.culture.noUse;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ViewFlipper;

public class Flipper extends ViewFlipper {
    private Paint paint;


    public Flipper(Context context) {
        super(context);
    }

    public Flipper(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }
    private void init(){
        paint=new Paint();
        paint.setAlpha(0);
        paint.setAntiAlias(true);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(50);
        paint.setTextSize(60);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText("Press Start",100,100,paint);



    }
}
