package com.whapprapphck.view.cutomviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.whapprapphck.R;

/**
 * Created by Tikho on 22.12.2016.
 */

public class PercentView extends View {

    public PercentView (Context context) {
        super(context);
        init();
    }
    public PercentView (Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public PercentView (Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }
    private void init() {
        /*borderPaint = new Paint();
        // border
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setColor(getContext().getResources().getColor(R.color.colorPercentView));
        borderPaint.setStrokeWidth(7);*/
        //borderPaint.setStrokeMiter(7);

        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(10);
        bgpaint = new Paint();
        bgpaint.setColor(getContext().getResources().getColor(R.color.colorGreen));
        bgpaint.setAntiAlias(true);
        bgpaint.setStyle(Paint.Style.FILL);
        bgpaint.setStrokeWidth(10);
        rect = new RectF();
        //rect2 = new RectF();
    }
    //Paint borderPaint;
    Paint paint;
    Paint bgpaint;
    RectF rect;
    //RectF rect2;
    float percentage = 0;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //draw background circle anyway
        int left = 0;
        int width = getWidth();
        int top = 0;
        rect.set(left, top, left+width, top + width);
        //rect2.set(left, top, left+width, top + width);
        // border
        //canvas.drawArc(rect2, -90, 360, true, borderPaint);

        canvas.drawArc(rect, -90, 360, true, bgpaint);
        if(percentage!=0) {
            canvas.drawArc(rect, -90, (360*percentage), true, paint);
        }
    }
    public void setPercentage(float percentage) {
        this.percentage = percentage / 100;
        invalidate();
    }
}
