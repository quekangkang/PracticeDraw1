package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice8DrawArcView extends View {
    Paint mPaint = new Paint();

    public Practice8DrawArcView(Context context) {
        super(context);
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    RectF mRectF = new RectF();
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawArc() 方法画弧形和扇形
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
        mRectF.set(400,200, 700,500);
        canvas.drawArc(mRectF,-180,60,false,mPaint);
        mPaint.setStyle(Paint.Style.FILL);
        mRectF.set(410,190,710,490);
        canvas.drawArc(mRectF,-120,100,true,mPaint);
        mRectF.set(400, 200, 700, 500);
        canvas.drawArc(mRectF,20,140,false,mPaint);
    }
}
