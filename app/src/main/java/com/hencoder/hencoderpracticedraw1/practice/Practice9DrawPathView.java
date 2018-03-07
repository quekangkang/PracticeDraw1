package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice9DrawPathView extends View {

    Paint mPaint = new Paint();
    public Practice9DrawPathView(Context context) {
        super(context);
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    Path mPath = new Path();
    RectF mRectF = new RectF();
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawPath() 方法画心形
        mPaint.setColor(Color.BLACK);
        mRectF.set(400, 400, 500,500);
        mPath.addArc(mRectF,-225,225);
        mRectF.set(500,400,600,500);
        mPath.arcTo(mRectF,-180,225,false);
        mPath.lineTo(500,590);
        canvas.drawPath(mPath, mPaint);
    }
}
