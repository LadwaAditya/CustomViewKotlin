package com.example.aditya.customeviewkotlinplayground;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Aditya on 30-Mar-17.
 */
public class MyView extends View {

    private final String TAG = MyView.class.getSimpleName();
    private final float SCALE_FACTOR = 1.1F;

    private Paint paintRed;
    private Paint paintGreen;

    private int top = 40;
    private int left = 40;
    private int right;
    private int bottom;

    private int mSize;

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void init(Context context, AttributeSet attributeSet) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.MyView, 0, 0);
        try {
            mSize = a.getInteger(R.styleable.MyView_size, 0);
        } finally {
            a.recycle();
        }
        paintRed = new Paint();
        paintRed.setColor(Color.RED);


        paintGreen = new Paint();
        paintGreen.setColor(Color.GREEN);
        paintGreen.setStyle(Paint.Style.FILL);
        paintGreen.setTextSize(40);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);



        canvas.drawRect(0, 0, mSize, mSize, paintRed);
        canvas.drawText("Rectangle", mSize / 4, mSize / 2, paintGreen);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(MeasureSpec.makeMeasureSpec(mSize, MeasureSpec.AT_MOST), MeasureSpec.makeMeasureSpec(mSize, MeasureSpec.AT_MOST));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mSize = (int) (mSize * SCALE_FACTOR);
                requestLayout();
                invalidate();
                return true;
            case MotionEvent.ACTION_UP:
                mSize = (int) (mSize / SCALE_FACTOR);
                requestLayout();
                invalidate();
                return true;
            default:
                return super.onTouchEvent(event);
        }
    }
}
