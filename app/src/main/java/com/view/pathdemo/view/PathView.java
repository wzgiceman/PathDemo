package com.view.pathdemo.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;

/**
 * Created by WZG on 2016/7/21.
 */
public class PathView extends View {
    Paint paint;

    public PathView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(4);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);//两边带圆弧
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        PaintMatr(canvas);
        PaintRect(canvas);
    }


    @Override
    public void startAnimation(Animation animation) {
        super.startAnimation(animation);
    }

    double progress;
    float[] position = new float[2];
    float[] tan = new float[2];


    /**
     * 绘制panth上每一个点的位置
     * 带箭头的进度框
     *
     * @param canvas
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void PaintRect(Canvas canvas) {
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        Path path = new Path();
        RectF rectFarc = new RectF(200, 300, 400, 700);
        path.addRect(rectFarc, Path.Direction.CCW);
        PathMeasure measure = new PathMeasure(path, false);
        progress = progress < 1 ? progress + 0.005 : 0;
        Matrix matrix = new Matrix();
        paint.setColor(Color.YELLOW);
        measure.getPosTan((int) (measure.getLength() * progress), position, tan);
        canvas.drawPath(path, paint);


//        箭头
        paint.setColor(Color.RED);
        Path path1 = new Path();
        Path path2 = new Path();
        path1.moveTo(position[0] - 20, position[1] + 20);
        path1.lineTo(position[0], position[1]);
        path1.lineTo(position[0] + 20, position[1] + 20);
        path1.close();
        float degrees = (float) (Math.atan2(tan[1], tan[0]) * 180.0 / Math.PI);
        matrix.setRotate(degrees + 90, position[0], position[1]);
        path2.addPath(path1, matrix);
        //        进度线
        measure.getSegment(-1000, (int) (measure.getLength() * progress), path2, true);
        paint.setColor(Color.BLUE);
        canvas.drawPath(path2, paint);
    }


    /**
     * 绘制panth上每一个点的位置
     * 带箭头的进度框
     *
     * @param canvas
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void PaintMatr(Canvas canvas) {
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        Path path = new Path();
        path.addCircle(600, 400, 100, Path.Direction.CCW);
        PathMeasure measure = new PathMeasure(path, false);
//        按照比例获取
        progress = progress < 1 ? progress + 0.0005 : 0;
        Matrix matrix = new Matrix();
        paint.setColor(Color.YELLOW);
        measure.getPosTan((int) (measure.getLength() * progress), position, tan);
        canvas.drawPath(path, paint);

//        箭头
        paint.setColor(Color.RED);
        Path path1 = new Path();
        path1.moveTo(position[0] - 20, position[1] + 20);
        path1.lineTo(position[0], position[1]);
        path1.lineTo(position[0] + 20, position[1] + 20);
//        是否闭合，闭合就是三角形了
        path1.close();
        Path path2 = new Path();
        float degrees = (float) (Math.atan2(tan[1], tan[0]) * 180.0 / Math.PI);
        matrix.setRotate(degrees + 90, position[0], position[1]);
        path2.addPath(path1, matrix);
        //        进度线
        measure.getSegment(-1000, (int) (measure.getLength() * progress), path2, true);
        paint.setColor(Color.BLUE);
        canvas.drawPath(path2, paint);
        invalidate();
    }


    /**
     * 绘制简单的pathline
     */
    private void paintSimplePath(Canvas canvas) {
        Path path = new Path();
        path.lineTo(200, 200);
//        移动位置
//        path.moveTo(200, 300);
//        修改最后一个点位置
//        path.setLastPoint(200,300);
        path.lineTo(200, 500);
        path.close();
        canvas.drawPath(path, paint);
    }

    /**
     * 绘制基本图形+长方形*****
     *
     * @param canvas
     */
    private void paintMorePath(Canvas canvas) {
        Path path = new Path();
        Path src = new Path();
        RectF rectF = new RectF(200, 200, 400, 400);
        RectF rectFarc = new RectF(200, 100, 400, 300);
        src.addCircle(300, 200, 50, Path.Direction.CCW);
        path.addRect(rectF, Path.Direction.CCW);
        path.addPath(src);
        path.arcTo(rectFarc, -180, 90);
        canvas.drawPath(path, paint);
    }


    /**
     * 画出xy坐标系
     */
    private void paintXy(Canvas canvas) {
        Path path = new Path();
        path.moveTo(0, 50);
        path.lineTo(0, 50);
        path.lineTo(50, 0);
        path.lineTo(100, 50);
        path.moveTo(50, 0);
        path.lineTo(50, 300);
        path.lineTo(300, 300);
        path.lineTo(250, 250);
        path.moveTo(300, 300);
        path.lineTo(250, 350);
        canvas.drawPath(path, paint);
    }


}
