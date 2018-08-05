package android.dkh.com.oopsproject.Models;

import android.dkh.com.oopsproject.GameState.State;
import android.dkh.com.oopsproject.until.Painter;
import android.view.MotionEvent;

/**
 * Created by MyPC on 27/06/2018.
 */

public class Line{
    Point point1;
    Point point2;
    boolean status;

    public Line(Point point1, Point point2, boolean status) {
        this.point1 = point1;
        this.point2 = point2;
        this.status = status;
    }

    public Point getPoint1() {
        return point1;
    }

    public void setPoint1(Point point1) {
        this.point1 = point1;
    }

    public Point getPoint2() {
        return point2;
    }

    public void setPoint2(Point point2) {
        this.point2 = point2;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
