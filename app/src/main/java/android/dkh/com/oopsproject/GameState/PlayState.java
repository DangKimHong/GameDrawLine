package android.dkh.com.oopsproject.GameState;

import android.dkh.com.oopsproject.Game.Assets;
import android.dkh.com.oopsproject.Models.Board;
import android.dkh.com.oopsproject.Models.Square;
import android.dkh.com.oopsproject.Models.Libes;
import android.dkh.com.oopsproject.Models.Point;
import android.dkh.com.oopsproject.until.Painter;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MyPC on 27/06/2018.
 */

public class PlayState extends State {

    final int BG_HEIGHT = 1080;
    final int BG_WIDTH = 720;
    final int PADDING = 20;

    public static int cellWidth;
    public static int cellHeight;
    public static int colQty;
    public static int rowQty;
    public static int startX, startY;
    private Board gameBoard;

    private int temp;
    private int player;
    public static int xA, yA, xB, yB;
    int xSquare = 0;
    int ySquare = 0;
    int xPaint;
    int yPaint;
    int width = 180;
    int height = 180;

    public static List<Libes> lines;
    private List<Point> pointtempt;
    public static List<Integer> rectPoint;
    private List<String> drawline;

    private List<Square> listSquare;

    @Override
    public void init() {
        temp = 0;
        cellHeight = 200;//80;
        cellWidth = 200; //80;
        colQty = 4; //8;
        rowQty = 4;
        player = 0;

        lines = new ArrayList<>();
        pointtempt = new ArrayList<>();
        rectPoint = new ArrayList<>();
        drawline = new ArrayList<>();

        // Khoi tao du lieu cho mang hinh vuong
        listSquare = new ArrayList<Square>();
        for (int i = 0; i < colQty - 1; i++) {
            for (int j = 0; j < rowQty - 1; j++) {
                Square obj = new Square();
                listSquare.add(obj);
            }
        }
        player = (player + 1) % 2;
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Painter g) {
        if (temp < 1) {
            g.drawImage(Assets.playbackground, 0, 0, BG_WIDTH, BG_HEIGHT);
            temp++;
        }

        gameBoard = new Board(cellWidth, cellHeight, rowQty, colQty, startX, startY);
        gameBoard.drawBoard(g);

        //if (isGameOver()) {
            if (rectPoint.size() == 2) {
                boolean isDraw = true;

                for (int i = 0; i < drawline.size(); i++) {
                    String[] split = drawline.get(i).split(",");
                    Log.d("AAA", split[0].trim() + "-" + split[1].trim());
                    if ((split[0].trim().equals(rectPoint.get(0) + "") || split[0].trim().equals(rectPoint.get(1) + ""))
                            && (split[1].trim().equals(rectPoint.get(0) + "") || split[1].trim().equals(rectPoint.get(1) + ""))) {
                        isDraw = false;
                        break;
                    }
                }
                if (isDraw) {
                    if (Board.points.get(rectPoint.get(0)).getRectPoint().left == Board.points.get(rectPoint.get(1)).getRectPoint().left
                            || Board.points.get(rectPoint.get(0)).getRectPoint().top == Board.points.get(rectPoint.get(1)).getRectPoint().top) {
                        //Log.d("AA", Board.points.get(rectPoint.get(0)).getRectPoint().left + "-" + Board.points.get(rectPoint.get(1)).getRectPoint().left + " - " + pointtempt.get(rectPoint.get(1)).getX1());
                        if (rectPoint.get(0) != rectPoint.get(1)) {
                            if (rectPoint.get(0) - rectPoint.get(1) == 1 || rectPoint.get(0) - rectPoint.get(1) == 4
                                    || rectPoint.get(0) - rectPoint.get(1) == -1 || rectPoint.get(0) - rectPoint.get(1) == -4) {

                                //convert
                                yA = rectPoint.get(0) / (colQty);// hàng
                                xA = rectPoint.get(0) - (yA * colQty);// cột
                                Log.d("AA", xA + "- " + yA);
                                yB = rectPoint.get(1) / (colQty);// hàng
                                xB = rectPoint.get(1) - (yB * colQty);// cột
                                Log.d("AA", xB + "- " + yB);

                                if (player == 0) {
                                    g.drawLine(Board.points.get(rectPoint.get(0)).getRectPoint().centerX() - 65,
                                            Board.points.get(rectPoint.get(0)).getRectPoint().centerY() - 40,
                                            Board.points.get(rectPoint.get(1)).getRectPoint().centerX() - 65,
                                            Board.points.get(rectPoint.get(1)).getRectPoint().centerY() - 40);
                                    drawline.add(new String(rectPoint.get(0) + "," + rectPoint.get(1)));
                                    //checkSquare(g);
                                    if (checkSquare(g) == true) {
                                        player = 0;
                                    } else
                                        player = 1;
                                } else if (player == 1) {
                                    g.drawLine1(Board.points.get(rectPoint.get(0)).getRectPoint().centerX() - 65,
                                            Board.points.get(rectPoint.get(0)).getRectPoint().centerY() - 40,
                                            Board.points.get(rectPoint.get(1)).getRectPoint().centerX() - 65,
                                            Board.points.get(rectPoint.get(1)).getRectPoint().centerY() - 40);
                                    drawline.add(new String(rectPoint.get(0) + "," + rectPoint.get(1)));

                                    //checkSquare(g);
                                    if (checkSquare(g) == true) {
                                        player = 1;
                                    } else
                                        player = 0;
                                } else {
                                    rectPoint.remove(1);
                                }
                            } else {
                                rectPoint.remove(1);
                            }
                        }
                    } else {
                        Log.d("AAA", "Trung");
                    }
                    rectPoint.clear();
                }
            }
        }
    //}

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        pointtempt = Board.points;
        for (int i = 0; i < pointtempt.size(); i++) {
            //Log.d("Aa", scaledX + "-" + scaledY + "- size:" + pointtempt.size());
            if (scaledX >= pointtempt.get(i).getRectPoint().left
                    && scaledX <= pointtempt.get(i).getRectPoint().left + pointtempt.get(i).getRectPoint().width()
                    && scaledY >= pointtempt.get(i).getRectPoint().top
                    && scaledY <= pointtempt.get(i).getRectPoint().top + pointtempt.get(i).getRectPoint().height()) {
                rectPoint.add(i);
                //Log.d("AAA", rectPoint.size() + "");
                return false;
            }
        }
        return false;
    }

    /**
     * Kiem tra hinh vuong da duoc ve 4line hay chua?
     */
    private boolean checkSquare(Painter g) {
        boolean flag = false;
        int minValue;
        int i, j;

        if (xA == xB) {

            // truong hop duong doc
            minValue = minValue(yA, yB);
            i = (minValue * (PlayState.colQty - 1)) + xA;
            j = i - 1;
            if (xA == colQty - 1) {
                // mép phải
                listSquare.get(j).setRightLine(true);
                Log.d("BB", "J= " + j + ": " + listSquare.get(j).isLeftLine() + "-" +
                        listSquare.get(j).isRightLine() + "-" +
                        listSquare.get(j).isTopLine() + "-" +
                        listSquare.get(j).isBottomLine());
                if (listSquare.get(j).isSquare()) {
                    drawRect(j, g);
                    flag = true;
                }
            } else if (xA == 0) {
                listSquare.get(i).setLeftLine(true);
                Log.d("BB", "I= " + i + ": " + listSquare.get(i).isLeftLine() + "-" +
                        listSquare.get(i).isRightLine() + "-" +
                        listSquare.get(i).isTopLine() + "-" +
                        listSquare.get(i).isBottomLine());
                if (listSquare.get(i).isSquare()) {
                    drawRect(i, g);
                    flag = true;
                }
            } else {
                listSquare.get(i).setLeftLine(true);
                Log.d("BB", "I= " + i + ": " + listSquare.get(i).isLeftLine() + "-" +
                        listSquare.get(i).isRightLine() + "-" +
                        listSquare.get(i).isTopLine() + "-" +
                        listSquare.get(i).isBottomLine());
                if (listSquare.get(i).isSquare()) {
                    drawRect(i, g);
                    flag = true;
                }
                listSquare.get(j).setRightLine(true);
                Log.d("BB", "J= " + j + ": " + listSquare.get(j).isLeftLine() + "-" +
                        listSquare.get(j).isRightLine() + "-" +
                        listSquare.get(j).isTopLine() + "-" +
                        listSquare.get(j).isBottomLine());
                if (listSquare.get(j).isSquare()) {
                    drawRect(j, g);
                    flag = true;
                }
            }
        } else if (yA == yB) {
            minValue = minValue(xA, xB);
            i = yA * (colQty - 1) + minValue;
            j = (yA - 1) * (colQty - 1) + minValue;

            if (i >= 0 && i < listSquare.size() && j >= 0 && j < listSquare.size()) {
                listSquare.get(i).setTopLine(true);
                Log.d("BB", "I= " + i + ": " + listSquare.get(i).isLeftLine() + "-" +
                        listSquare.get(i).isRightLine() + "-" +
                        listSquare.get(i).isTopLine() + "-" +
                        listSquare.get(i).isBottomLine());
                if (listSquare.get(i).isSquare()) {
                    drawRect(i, g);
                    flag = true;
                }
                listSquare.get(j).setBottomLine(true);
                Log.d("BB", "J= " + j + ": " + listSquare.get(j).isLeftLine() + "-" +
                        listSquare.get(j).isRightLine() + "-" +
                        listSquare.get(j).isTopLine() + "-" +
                        listSquare.get(j).isBottomLine());
                if (listSquare.get(j).isSquare()) {
                    drawRect(j, g);
                    flag = true;
                }
            } else if (i >= listSquare.size()) {
                listSquare.get(j).setBottomLine(true);
                Log.d("BB", "J= " + j + ": " + listSquare.get(j).isLeftLine() + "-" +
                        listSquare.get(j).isRightLine() + "-" +
                        listSquare.get(j).isTopLine() + "-" +
                        listSquare.get(j).isBottomLine());
                if (listSquare.get(j).isSquare()) {
                    drawRect(j, g);
                    flag = true;
                }
            } else if (j < 0) {
                listSquare.get(i).setTopLine(true);
                Log.d("BB", "I= " + i + ": " + listSquare.get(i).isLeftLine() + "-" +
                        listSquare.get(i).isRightLine() + "-" +
                        listSquare.get(i).isTopLine() + "-" +
                        listSquare.get(i).isBottomLine());
                if (listSquare.get(i).isSquare()) {
                    drawRect(i, g);
                    flag = true;
                }
            }
        }
        return flag;
    }

    private int minValue(int a, int b) {
        if (a > b) {
            return b;
        }
        return a;
    }

    private void drawRect(int a, Painter g) {
        ySquare = a / (colQty - 1);// hàng
        xSquare = a - (ySquare * (colQty - 1));// cột

        xPaint = Board.startX + xSquare * cellWidth + PADDING;
        yPaint = Board.startY + ySquare * cellHeight + PADDING;

        if (player == 0) {
            g.drawRect(xPaint, yPaint, width, height);
        } else if (player == 1) {
            g.drawRect1(xPaint, yPaint, width, height);
        }
    }

    private boolean checkWin(int player){
//        if (Board.board[0][0] == player && Board.board[0][1] == player && Board.board[0][2] == player)
//            return true;
//
//        if (Board.board[1][0] == player && Board.board[1][1] == player && Board.board[1][2] == player)
//            return true;
//
//        if (Board.board[2][0] == player && Board.board[2][1] == player && Board.board[2][2] == player)
//            return true;
//
//        if (Board.board[0][0] == player && Board.board[1][0] == player && Board.board[2][0] == player)
//            return true;
//
//        if (Board.board[0][1] == player && Board.board[1][1] == player && Board.board[2][1] == player)
//            return true;
//
//        if (Board.board[0][2] == player && Board.board[1][2] == player && Board.board[2][2] == player)
//            return true;
//
//        if (Board.board[0][0] == player && Board.board[1][1] == player && Board.board[2][2] == player)
//            return true;
//
//        if (Board.board[0][2] == player && Board.board[1][1] == player && Board.board[2][0] == player)
//            return true;
        return false;
    }
    // kiểm tra game đã kết thúc hay chưa?
    private boolean isGameOver(){
        if (checkWin(0) || checkWin(1)){
            return  true;
        }
        int count = 0;
        for (int i = 0; i < rowQty; i++) {
            for (int j = 0; j < colQty; j++) {
                if (Board.board[i][j] == -1) count++;
            }
        }
        if (count == 0){
            return true;//trò chơi kết thúc
        }
        //chưa thắng hoặc còn vị trí để đi=>game chưa kết thúc
        return false;
    }
}
