package android.dkh.com.oopsproject.Models;

/**
 * Created by MyPC on 30/07/2018.
 */

public class Square {
    private boolean leftLine;
    private boolean rightLine;
    private boolean bottomLine;
    private boolean topLine;


    public Square() {
        this.leftLine = false;
        this.rightLine = false;
        this.bottomLine = false;
        this.topLine = false;

    }

    public boolean isLeftLine() {
        return leftLine;
    }

    public void setLeftLine(boolean leftLine) {
        this.leftLine = leftLine;
    }

    public boolean isRightLine() {
        return rightLine;
    }

    public void setRightLine(boolean rightLine) {
        this.rightLine = rightLine;
    }

    public boolean isBottomLine() {
        return bottomLine;
    }

    public void setBottomLine(boolean bottomLine) {
        this.bottomLine = bottomLine;
    }

    public boolean isTopLine() {
        return topLine;
    }

    public void setTopLine(boolean topLine) {
        this.topLine = topLine;
    }

    public boolean isSquare() {
        if (this.topLine && this.rightLine && this.bottomLine && this.leftLine)
            return true;
        return false;
    }

    public int checkLine() {
        if (!this.leftLine && this.topLine && this.rightLine && this.bottomLine) {
            return 1;
        }

        if (this.leftLine && !this.topLine && this.rightLine && this.bottomLine) {
            return 2;
        }

        if (this.leftLine && this.topLine && !this.rightLine && this.bottomLine) {
            return 3;
        }

        if (this.leftLine && this.topLine && this.rightLine && !this.bottomLine) {
            return 4;
        }
        return 0;
    }
}
