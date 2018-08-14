package android.dkh.com.oopsproject.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;

/**
 * Created by MyPC on 27/06/2018.
 */

public class Painter {
    private Canvas canvas;
    private Paint paint;
    private Rect srcRect;
    private Rect dstRect;
    private RectF dstRectF;
    private Picture picture;
    private int strokeWidth =3;
    private int strokeOval =80;

    public Painter(Canvas canvas) {
        this.canvas = canvas;
        paint = new Paint();
        srcRect = new Rect();
        dstRect = new Rect();
        dstRectF = new RectF();
    }

    //sets the color of the Painter
    public void setColor(int color) {
        paint.setColor(color);
    }

    //set the font and the size of the text
    public void setFont(Typeface typeface, float textSize) {
        paint.setTypeface(typeface);
        paint.setTextSize(textSize);
    }

    //draws a string at the specified location
    public void drawString(String str, int x, int y) {
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(40);
        canvas.drawText(str, x, y, paint);
    }
    public void drawStringResult(String str, int x, int y) {
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(50);
        paint.setColor(Color.BLUE);
        canvas.drawText(str, x, y, paint);
    }


    public void drawLine(int x1, int y1, int x2, int y2 ){
        Paint p = new Paint();
        p.setStyle(Paint.Style.STROKE);
        p.setColor(Color.WHITE);
        p.setStrokeWidth(strokeWidth);
        canvas.drawLine(x1, y1, x2, y2, p);
    }
    public void drawLine1(int x1, int y1, int x2, int y2 ){
        Paint p = new Paint();
        p.setStyle(Paint.Style.STROKE);
        p.setColor(Color.BLACK);
        p.setStrokeWidth(strokeWidth);
        canvas.drawLine(x1, y1, x2, y2, p);
    }

    //draws a rectangle with the specified location and size
    public void fillRect(int x, int y, int width, int height) {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        canvas.drawRect(x, y, x + width, y + height, paint);
    }
    //draws a rectangle with the same location and size as the
    //Rect parameter
    public void fillRect(Rect rectangle) {
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(rectangle, paint);
    }
    public void drawPoint(float x, float y) {
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPoint(x, y, paint);
    }
    //draws an outline of a rectangle with the specified location and size
    public void drawRect(int x, int y, int width, int height) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        canvas.drawRect(x, y, x + width, y + height, paint);
    }

    public void drawRect1(int x, int y, int width, int height) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        canvas.drawRect(x, y, x + width, y + height, paint);
    }
    //draws an outline of a rectangle with the same location and size of the
    //Rect parameter
    public void drawRect(Rect rectangle) {
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(rectangle, paint);
    }

    //draws an oval with the specified location and size
    public void fillOval(int x, int y, int width, int height) {
        Paint p = new Paint();
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(strokeOval);
        p.setAlpha(0);
        dstRectF.set(x, y, x +width, y + height);
        canvas.drawOval(dstRectF, p);
        paint.setStyle(Paint.Style.FILL);
        dstRectF.set(x, y, x + 20, y + 20);
        paint.setColor(Color.WHITE);
        canvas.drawOval(dstRectF, paint);

    }
    //draws an oval with the same location and size of the Rect argument
    public void fillOval(RectF rectangle) {
        paint.setStyle(Paint.Style.FILL);
        canvas.drawOval(rectangle, paint);
        paint.setColor(Color.WHITE);

    }
    //draws an outline of an oval with the specified location and size
    public void drawOval(int x, int y, int width, int height) {
        paint.setStyle(Paint.Style.STROKE);
        dstRectF.set(x, y, x +width, y + height);
        canvas.drawOval(dstRectF, paint);
    }
    //draws an outline of an oval with the same location and
    // size of the Rect argument
    public void drawOval(RectF rectangle) {
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawOval(rectangle, paint);

    }

    public void drawRecta(int x, int y, int width, int height) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(strokeWidth);
        canvas.drawRect(x, y, x + width, y + height, paint);
    }
    public void drawRecta1(int x, int y, int width, int height) {
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(strokeWidth);
        canvas.drawRect(x, y, x + width, y + height, paint);
    }

    //method for drawing a bitmap (raster) image
    public void drawImage(Bitmap bitmap, int x, int y) {
        canvas.drawBitmap(bitmap, x, y, paint);
    }

    //alternate method for drawing a bitmap that allows you to scale it
    //this method is not recommended because it requires it to be scaled eac time
    //it is drawn. it is better to scale it before hand and use the other method
    public void drawImage(Bitmap bitmap, int x, int y, int width, int height) {
        srcRect.set(0, 0, bitmap.getWidth(), bitmap.getHeight());
        dstRect.set(x, y, x + width, y + height);
        canvas.drawBitmap(bitmap, srcRect, dstRect, paint);
    }
//    public void drawImagePlayer(Bitmap bitmap, int x, int y, int width, int height) {
//        srcRect.set(0, 0, bitmap.getWidth(), bitmap.getHeight());
//        dstRect.set(x, y, x + width, y + height);
//        canvas.drawBitmap(bitmap, srcRect, dstRect, paint);
//    }

}
