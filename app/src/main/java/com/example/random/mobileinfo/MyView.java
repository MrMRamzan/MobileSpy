package com.example.random.mobileinfo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.view.View;

public class MyView extends View {

    int[] graphData;
    Paint graphPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    int screenH;
    int screenW;
    int colW;
    int colH;
    int columnCount;

    public MyView(Context context) {
        super(context);

        graphPaint.setColor(Color.MAGENTA);
        graphPaint.setStyle(Paint.Style.FILL);
    }

    public MyView(Context context, int kolki) {
        super(context);

        if (kolki == 0){
            this.setBackgroundResource(R.color.colorPrimary);
        }
        if (kolki == 1){
            this.setBackgroundResource(R.color.colorPrimaryDark);
        }
    }

    @Override
    public void onSizeChanged (int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        screenW = w;
        screenH = h;
    }

    public void drawGraph(int[] graphData){
        this.graphData = graphData;
        columnCount = graphData.length;
        invalidate();
    }
    public void setBackgroundResource (int resid){
        super.setBackgroundResource(resid);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        colW = (screenW - 10) / columnCount;
        int graphStep = 20;
        int columnSpace = 5;

        canvas.drawText("GRAPH", 10, 10,  graphPaint);

        for (int i= 0 ; i < columnCount; i++){
            //draw columns from bottom up
            canvas.drawRect(
                    new Rect(
                            i * colW + 5,
                            screenH - 5 - (graphData[i] * graphStep),
                            i * colW + 5 + colW - columnSpace,
                            screenH - 5
                    ),
                    graphPaint);
        }
    }

}