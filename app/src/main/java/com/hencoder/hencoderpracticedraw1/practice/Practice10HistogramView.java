package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Practice10HistogramView extends View {

    private static final String TAG = "Practice10HistogramView";
    public Practice10HistogramView(Context context) {
        this(context, null);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mAndroidVerRatio.put("Froyo", 0.3f);
        mAndroidVerRatio.put("GB", 1.0f);
        mAndroidVerRatio.put("ICS", 1.0f);
        mAndroidVerRatio.put("JB", 10f);
        mAndroidVerRatio.put("KitKat", 21.9f);
        mAndroidVerRatio.put("L", 32.9f);
        mAndroidVerRatio.put("M", 30.7f);
    }
    Map<String, Float> mAndroidVerRatio = new LinkedHashMap<>();
    private float mHeightRatio = 0.6f;
    private float mWidthRatio = 0.75f;
    Paint mPaint = new Paint();

    private float mRectMaxHeightRatio = 0.9f; //最高矩形占表格高度的比
    private float mRatioOfOffset = 0.2f; //间隔占矩形宽的占比

    RectF mRectF = new RectF(); //通用变量
    Rect mRect = new Rect(); //通用变量

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画直方图

        int height = getHeight();
        int width = getWidth();

        float tableWidth = width * mWidthRatio;
        float tableHeight = height * mHeightRatio;
        float top = height * 0.1f; //表格距离顶部的距离
        float left = (width - tableWidth) /2;

        //画表格的左竖线
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(2);
        canvas.drawLine(left, top, left, top + tableHeight, mPaint);
        //画表格的下横线
        canvas.drawLine(left, top + tableHeight, left + tableWidth, top + tableHeight, mPaint);

        //画矩形
        float maxVerRatio = findMaxRatio(mAndroidVerRatio); // 找出占比最大的 占比 ,以此作为参考
        int size = mAndroidVerRatio.size();
        float rectWidth = tableWidth / (size + (size + 1) * mRatioOfOffset); //计算矩形宽 所有矩形的宽+所有的间距 = 表宽
        float offsetWidth = rectWidth * mRatioOfOffset; //矩形间间隔的距离
        Set<Map.Entry<String, Float>> entries = mAndroidVerRatio.entrySet();
        Iterator<Map.Entry<String, Float>> iterator = entries.iterator();
        for (int i = 0; i < size; i++) {
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setColor(Color.GREEN);
            float rectLeft = left + (i+1) * offsetWidth + i * rectWidth;
            Map.Entry<String, Float> entry = iterator.next();
            float verRatio = entry.getValue();
            Log.i(TAG,"verRatio : " + verRatio);
            float rectHeight =  (verRatio/maxVerRatio) * mRectMaxHeightRatio * tableHeight;
            float rectTop = top + (tableHeight-rectHeight);
            mRectF.set(rectLeft, rectTop, rectLeft + rectWidth, rectTop + rectHeight);
            canvas.drawRect(mRectF,mPaint);


            //绘制文子 文子在矩形的下方居中显示
            mPaint.setColor(Color.WHITE);
            mPaint.setTextSize(30);
            String text = entry.getKey();
            mPaint.getTextBounds(text,0,text.length(),mRect);
            int textWidth = mRect.width();
            int textHeight = mRect.height();
            float textLeft = rectLeft + rectWidth*0.5f  - textWidth * 0.5f; //从矩形中间往左 二分之一 textWidth
            canvas.drawText(text,textLeft,top + tableHeight + textHeight,mPaint);
        }

        //画 “直方图”三个字
        mPaint.setTextSize(50);
        canvas.drawText("直方图",0.4f*width,0.9f*height,mPaint);
    }

    private float findMaxRatio(Map<String, Float> androidVerRatio) {
        if (null == androidVerRatio) {
            return 0;
        }
        float maxRatio = 0;
        Set<Map.Entry<String, Float>> entries = androidVerRatio.entrySet();
        for (Map.Entry<String, Float> entry : entries) {
            Float value = entry.getValue();
            if (value > maxRatio) {
                maxRatio = value;
            }
        }
        return maxRatio;
    }
}
