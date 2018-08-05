package android.dkh.com.oopsproject.Models;

import android.dkh.com.oopsproject.until.Painter;
import android.graphics.Rect;

/**
 * Created by MyPC on 27/06/2018.
 */

public class Point {
    int x1;
    int y1;
    int height;
    int width;
    private Rect rectPoint;

    public Point(int x1, int y1) {
        this.x1 = x1;
        this.y1 = y1;
    }
    public Point(int x1, int y1, int width, int height) {
        rectPoint = new Rect(x1, y1, x1+width , y1+height);
}
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Rect getRectPoint() {
        return rectPoint;
    }

    public void setRectPoint(Rect rectPoint) {
        this.rectPoint = rectPoint;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

}
