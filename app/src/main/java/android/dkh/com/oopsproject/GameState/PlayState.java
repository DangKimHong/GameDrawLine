package android.dkh.com.oopsproject.GameState;

import android.content.res.Resources;
import android.dkh.com.oopsproject.Game.Assets;
import android.dkh.com.oopsproject.Models.Board;
import android.dkh.com.oopsproject.Models.Square;
import android.dkh.com.oopsproject.Models.Libes;
import android.dkh.com.oopsproject.Models.Point;
import android.dkh.com.oopsproject.util.Painter;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by MyPC on 27/06/2018.
 */

public class PlayState extends State {

    public static int soundclick;
    public static int soundBack;
    public static int soundSquare;

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
    private boolean tempPlayer;
    boolean isDraw;
    private int player;
    public static int scoreA;
    public static int scoreB;
    public static int xA, yA, xB, yB;
    int xSquare = 0;
    int ySquare = 0;
    int xPaint;
    int yPaint;
    int width = 180;
    int height = 180;


    private Rect rectBack;

    public static List<Libes> lines;
    private List<Point> pointtempt;
    public static List<Integer> rectPoint;
    public static List<String> drawline;

    public static List<Square> listSquare;
    public static List<Integer> listDraw;

    @Override
    public void init() {
        soundclick = Assets.loadSound("soundclick.wav");
        soundSquare = Assets.loadSound("soundscore.wav");
        soundBack = Assets.loadSound("soundplay.wav");
        int height = Resources.getSystem().getDisplayMetrics().heightPixels;
        int width = Resources.getSystem().getDisplayMetrics().widthPixels;
        //Log.i("AZ","h: " + height + " - w: " + width);
        temp = 0;
        tempPlayer = true;
        isDraw = true;
        cellHeight = 200;//80;//200
        cellWidth = 200; //80;//200
        colQty = 4; //8;//4
        rowQty = 4;
        player = 0;

        scoreA = 0;
        scoreB = 0;


        lines = new ArrayList<>();
        pointtempt = new ArrayList<>();
        rectPoint = new ArrayList<>();
        drawline = new ArrayList<>();
        listDraw = new ArrayList<>();


        rectBack = new Rect(0, 990, 100, 1190);
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
        //Vẽ background cho Gameplay
        if (temp < 1) {
            g.drawImage(Assets.playbackground, 0, 0, BG_WIDTH, BG_HEIGHT);
            temp++;
        }
        // Vẽ bàn cờ chứa các điểm
        gameBoard = new Board(cellWidth, cellHeight, rowQty, colQty, startX, startY);
        gameBoard.drawBoard(g);

        g.drawString("WHITE", 220, 150);
        g.drawString("BLACK", 400, 150);
        g.drawImage(Assets.back, 0, 990, 100, 100);

        if (MenuState.playWith == 0) {
            if (isGameOver() == false) {

                if (rectPoint.size() == 2) {
                    boolean isDraw = true;
                    for (int i = 0; i < drawline.size(); i++) {
                        String[] split = drawline.get(i).split(",");
                        Log.d("AAA", split[0].trim() + "-" + split[1].trim());
                        if ((split[0].trim().equals(rectPoint.get(0) + "")
                                || split[0].trim().equals(rectPoint.get(1) + ""))
                                && (split[1].trim().equals(rectPoint.get(0) + "")
                                || split[1].trim().equals(rectPoint.get(1) + ""))) {
                            isDraw = false;
                            break;
                        }
                    }
                    if (isDraw) {
                        if (Board.points.get(rectPoint.get(0)).getRectPoint().left
                                == Board.points.get(rectPoint.get(1)).getRectPoint().left
                                || Board.points.get(rectPoint.get(0)).getRectPoint().top
                                == Board.points.get(rectPoint.get(1)).getRectPoint().top) {
                            //Log.d("AA", Board.points.get(rectPoint.get(0)).getRectPoint().left
                            // + "-" + Board.points.get(rectPoint.get(1)).getRectPoint().left
                            // + " - " + pointtempt.get(rectPoint.get(1)).getX1());
                            if (rectPoint.get(0) != rectPoint.get(1)) {
                                if (rectPoint.get(0) - rectPoint.get(1) == 1
                                        || rectPoint.get(0) - rectPoint.get(1) == 4
                                        || rectPoint.get(0) - rectPoint.get(1) == -1
                                        || rectPoint.get(0) - rectPoint.get(1) == -4) {

                                    //convert
                                    yA = rectPoint.get(0) / (colQty);// hàng
                                    xA = rectPoint.get(0) - (yA * colQty);// cột
                                    Log.d("AA", xA + "- " + yA);
                                    yB = rectPoint.get(1) / (colQty);// hàng
                                    xB = rectPoint.get(1) - (yB * colQty);// cột
                                    Log.d("AA", xB + "- " + yB);

                                    if (player == 0) {
                                        g.drawLine(Board.points.get(rectPoint.get(0)).getRectPoint().centerX() - 90,
                                                Board.points.get(rectPoint.get(0)).getRectPoint().centerY() - 90,
                                                Board.points.get(rectPoint.get(1)).getRectPoint().centerX() - 90,
                                                Board.points.get(rectPoint.get(1)).getRectPoint().centerY() - 90);
                                        drawline.add(new String(rectPoint.get(0) + "," + rectPoint.get(1)));
                                        if (checkSquare(g) == true) {
                                            player = 0;
                                        } else {
                                            player = 1;
                                        }
                                    } else if (player == 1) {
                                        g.drawLine1(Board.points.get(rectPoint.get(0)).getRectPoint().centerX() - 90,
                                                Board.points.get(rectPoint.get(0)).getRectPoint().centerY() - 90,
                                                Board.points.get(rectPoint.get(1)).getRectPoint().centerX() - 90,
                                                Board.points.get(rectPoint.get(1)).getRectPoint().centerY() - 90);
                                        drawline.add(new String(rectPoint.get(0) + "," + rectPoint.get(1)));
                                        if (checkSquare(g) == true) {
                                            player = 1;
                                        } else {
                                            player = 0;
                                        }
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
        } else {
//            if (isGameOver() == false) {
//            if (player == 1) {
//                if (rectPoint.size() == 2) {
//
////                    for (int i = 0; i < drawline.size(); i++) {
////                        String[] split = drawline.get(i).split(",");
////                        Log.d("AAA", split[0].trim() + "-" + split[1].trim());
////                        if ((split[0].trim().equals(rectPoint.get(0) + "")
////                                || split[0].trim().equals(rectPoint.get(1) + ""))
////                                && (split[1].trim().equals(rectPoint.get(0) + "")
////                                || split[1].trim().equals(rectPoint.get(1) + ""))) {
////                            isDraw = false;
////                            break;
////                        }
////                    }
//                    if (checkSame(rectPoint.get(0), rectPoint.get(1))) {
//                        if (Board.points.get(rectPoint.get(0)).getRectPoint().left
//                                == Board.points.get(rectPoint.get(1)).getRectPoint().left
//                                || Board.points.get(rectPoint.get(0)).getRectPoint().top
//                                == Board.points.get(rectPoint.get(1)).getRectPoint().top) {
//                            //Log.d("AA", Board.points.get(rectPoint.get(0)).getRectPoint().left
//                            // + "-" + Board.points.get(rectPoint.get(1)).getRectPoint().left
//                            // + " - " + pointtempt.get(rectPoint.get(1)).getX1());
//                            if (rectPoint.get(0) != rectPoint.get(1)) {
//                                if (rectPoint.get(0) - rectPoint.get(1) == 1
//                                        || rectPoint.get(0) - rectPoint.get(1) == 4
//                                        || rectPoint.get(0) - rectPoint.get(1) == -1
//                                        || rectPoint.get(0) - rectPoint.get(1) == -4) {
//
//                                    //convert
//                                    yA = rectPoint.get(0) / (colQty);// hàng
//                                    xA = rectPoint.get(0) - (yA * colQty);// cột
//                                    Log.d("AA", xA + "- " + yA);
//                                    yB = rectPoint.get(1) / (colQty);// hàng
//                                    xB = rectPoint.get(1) - (yB * colQty);// cột
//                                    Log.d("AA", xB + "- " + yB);
//                                    //oneToTwo(rectPoint.get(0), rectPoint.get(1));
//
//                                    g.drawLine1(Board.points.get(rectPoint.get(0)).getRectPoint().centerX() - 90,
//                                            Board.points.get(rectPoint.get(0)).getRectPoint().centerY() - 90,
//                                            Board.points.get(rectPoint.get(1)).getRectPoint().centerX() - 90,
//                                            Board.points.get(rectPoint.get(1)).getRectPoint().centerY() - 90);
//                                    drawline.add(new String(rectPoint.get(0) + "," + rectPoint.get(1)));
//                                    if (checkSquare(g) == true) {
//                                        player = 1;
//                                    }
//                                } else {
//                                    rectPoint.remove(1);
//                                }
//                            } else {
//                                rectPoint.remove(1);
//                            }
//                        }
//                    } else {
//                        Log.d("AAA", "Trung");
//                    }
//                    rectPoint.clear();
//                }
//                player = 0;
//                tempPlayer = false;
//            } else if (player == 0 && !tempPlayer) {
//                //botGame(g);
//                player = 1;
//            }
//        }
            Log.d("BBB", "Bot");
        }
    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        pointtempt = Board.points;

        for (int i = 0; i < pointtempt.size(); i++) {
            Log.d("OnTouch", scaledX + "-" + scaledY + "- size:" + pointtempt.size());
            if (scaledX >= pointtempt.get(i).getRectPoint().left
                    && scaledX <= pointtempt.get(i).getRectPoint().left + pointtempt.get(i).getRectPoint().width()
                    && scaledY >= pointtempt.get(i).getRectPoint().top
                    && scaledY <= pointtempt.get(i).getRectPoint().top + pointtempt.get(i).getRectPoint().height()) {
                rectPoint.add(i);
                Log.d("AA", rectPoint.size() + "size");
                Assets.playSound(soundclick);
                return false;
            }
        }
        if (rectBack.contains(scaledX, scaledY)) {
            Assets.playSound(soundBack);
            setCurrentState(new MenuState());
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
                    Assets.playSound(soundSquare);
                    listDraw.add(j);
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
                    Assets.playSound(soundSquare);
                    listDraw.add(i);
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
                    Assets.playSound(soundSquare);
                    listDraw.add(i);
                    flag = true;
                }
                listSquare.get(j).setRightLine(true);
                Log.d("BB", "J= " + j + ": " + listSquare.get(j).isLeftLine() + "-" +
                        listSquare.get(j).isRightLine() + "-" +
                        listSquare.get(j).isTopLine() + "-" +
                        listSquare.get(j).isBottomLine());
                if (listSquare.get(j).isSquare()) {
                    drawRect(j, g);
                    Assets.playSound(soundSquare);
                    listDraw.add(j);
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
                    Assets.playSound(soundSquare);
                    listDraw.add(i);
                    flag = true;
                }
                listSquare.get(j).setBottomLine(true);
                Log.d("BB", "J= " + j + ": " + listSquare.get(j).isLeftLine() + "-" +
                        listSquare.get(j).isRightLine() + "-" +
                        listSquare.get(j).isTopLine() + "-" +
                        listSquare.get(j).isBottomLine());
                if (listSquare.get(j).isSquare()) {
                    drawRect(j, g);
                    Assets.playSound(soundSquare);
                    listDraw.add(j);
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
                    Assets.playSound(soundSquare);
                    listDraw.add(j);
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
                    Assets.playSound(soundSquare);
                    listDraw.add(i);
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
            scoreA++;
            g.drawRecta(250, 50, 50, 50);
            g.drawString(scoreA + "", 265, 90);


        } else if (player == 1) {
            g.drawRect1(xPaint, yPaint, width, height);
            scoreB++;
            g.drawRecta1(440, 50, 50, 50);
            g.drawString(scoreB + "", 455, 90);
            //g.drawImage(Assets.playing, 455, 170);
        }
    }

    public boolean isGameOver() {
        if (listDraw.size() == ((colQty - 1) * (rowQty - 1))) {
            setCurrentState(new GameOverState());
            return true;
        }
        return false;
    }

//    private void botGame(Painter g) {
//        int minValue;
//        int i, j;
//        Log.d("CC", "Code đến đây rồi nè!" + xA + "-" + xB);
//        if (xA == xB) {
//            // truong hop duong doc
//            minValue = minValue(yA, yB);
//            i = (minValue * (PlayState.colQty - 1)) + xA;
//            j = i - 1;
//
//            if (xA == colQty - 1) {
//                botDrawing(j, g);
//                Log.d("BB", listSquare.get(j).isLeftLine() + "-"
//                        + listSquare.get(j).isTopLine() + "-"
//                        + listSquare.get(j).isRightLine() + "-"
//                        + listSquare.get(j).isBottomLine());
//            } else if (xA == 0) {
//                botDrawing(i, g);
//                Log.d("BB", listSquare.get(i).isLeftLine() + "-"
//                        + listSquare.get(i).isTopLine() + "-"
//                        + listSquare.get(i).isRightLine() + "-"
//                        + listSquare.get(i).isBottomLine());
//            } else {
//                botDrawing(i, g);
//                Log.d("BB", listSquare.get(i).isLeftLine() + "-"
//                        + listSquare.get(i).isTopLine() + "-"
//                        + listSquare.get(i).isRightLine() + "-"
//                        + listSquare.get(i).isBottomLine());
//                botDrawing(j, g);
//                Log.d("BB", listSquare.get(j).isLeftLine() + "-"
//                        + listSquare.get(j).isTopLine() + "-"
//                        + listSquare.get(j).isRightLine() + "-"
//                        + listSquare.get(j).isBottomLine());
////                if (listSquare.get(i).checkLine() != 0) {
////                    // vẽ line còn lại
////                    if (listSquare.get(i).checkLine() == 1) {
////                        Log.d("CC", "Vẽ line");
////                        listSquare.get(i).setBottomLine(true);
////                    }
////                    if (listSquare.get(i).checkLine() == 2) {
////                        Log.d("CC", "Vẽ line");
////                        listSquare.get(i).setBottomLine(true);
////                    }
////                    if (listSquare.get(i).checkLine() == 3) {
////                        Log.d("CC", "Vẽ line");
////                        listSquare.get(i).setBottomLine(true);
////                    }
////                    if (listSquare.get(i).checkLine() == 4) {
////                        Log.d("CC", "Vẽ line");
////                        listSquare.get(i).setBottomLine(true);
////                    }
////                }
////                if (listSquare.get(j).checkLine() != 0) {
////                    //vẽ line còn lại
////                    if (listSquare.get(j).checkLine() == 1) {
////                        Log.d("CC", "Vẽ line");
////                        listSquare.get(j).setBottomLine(true);
////                    }
////                    if (listSquare.get(j).checkLine() == 2) {
////                        Log.d("CC", "Vẽ line");
////                        listSquare.get(j).setBottomLine(true);
////                    }
////                    if (listSquare.get(j).checkLine() == 3) {
////                        Log.d("CC", "Vẽ line");
////                        listSquare.get(j).setBottomLine(true);
////                    }
////                    if (listSquare.get(j).checkLine() == 4) {
////                        Log.d("CC", "Vẽ line");
////                        listSquare.get(j).setBottomLine(true);
////                    }
////                }
//            }
//        } else if (yA == yB) {
//            minValue = minValue(xA, xB);
//            i = yA * (colQty - 1) + minValue;
//            j = (yA - 1) * (colQty - 1) + minValue;
//
//            if (i >= 0 && i < listSquare.size() && j >= 0 && j < listSquare.size()) {
//                botDrawing(i, g);
//                Log.d("BB", listSquare.get(i).isLeftLine() + "-"
//                        + listSquare.get(i).isTopLine() + "-"
//                        + listSquare.get(i).isRightLine() + "-"
//                        + listSquare.get(i).isBottomLine());
//                botDrawing(j, g);
//                Log.d("BB", listSquare.get(j).isLeftLine() + "-"
//                        + listSquare.get(j).isTopLine() + "-"
//                        + listSquare.get(j).isRightLine() + "-"
//                        + listSquare.get(j).isBottomLine());
////                if (listSquare.get(i).checkLine() != 0) {
////                    // vẽ line còn lại
////                    if (listSquare.get(i).checkLine() == 1) {
////                        Log.d("CC", "Vẽ line");
////                        listSquare.get(i).setBottomLine(true);
////                    }
////                    if (listSquare.get(i).checkLine() == 2) {
////                        Log.d("CC", "Vẽ line");
////                        listSquare.get(i).setBottomLine(true);
////                    }
////                    if (listSquare.get(i).checkLine() == 3) {
////                        Log.d("CC", "Vẽ line");
////                        listSquare.get(i).setBottomLine(true);
////                    }
////                    if (listSquare.get(i).checkLine() == 4) {
////                        Log.d("CC", "Vẽ line");
////                        listSquare.get(i).setBottomLine(true);
////                    }
////                }
////                if (listSquare.get(j).checkLine() != 0) {
////                    if (listSquare.get(j).checkLine() == 1) {
////                        Log.d("CC", "Vẽ line");
////                        listSquare.get(j).setBottomLine(true);
////                    }
////                    if (listSquare.get(j).checkLine() == 2) {
////                        Log.d("CC", "Vẽ line");
////                        listSquare.get(j).setBottomLine(true);
////                    }
////                    if (listSquare.get(j).checkLine() == 3) {
////                        Log.d("CC", "Vẽ line");
////                        listSquare.get(j).setBottomLine(true);
////                    }
////                    if (listSquare.get(j).checkLine() == 4) {
////                        Log.d("CC", "Vẽ line");
////                        listSquare.get(j).setBottomLine(true);
////                    }
////                }
//            } else if (i >= listSquare.size()) {
//                botDrawing(j, g);
//                Log.d("BB", listSquare.get(j).isLeftLine() + "-"
//                        + listSquare.get(j).isTopLine() + "-"
//                        + listSquare.get(j).isRightLine() + "-"
//                        + listSquare.get(j).isBottomLine());
////                if (listSquare.get(j).checkLine() != 0) {
////                    if (listSquare.get(j).checkLine() == 1) {
////                        Log.d("CC", "Vẽ line");
////                        listSquare.get(j).setBottomLine(true);
////                    }
////                    if (listSquare.get(j).checkLine() == 2) {
////                        Log.d("CC", "Vẽ line");
////                        listSquare.get(j).setBottomLine(true);
////                    }
////                    if (listSquare.get(j).checkLine() == 3) {
////                        Log.d("CC", "Vẽ line");
////                        listSquare.get(j).setBottomLine(true);
////                    }
////                    if (listSquare.get(j).checkLine() == 4) {
////                        Log.d("CC", "Vẽ line");
////                        listSquare.get(j).setBottomLine(true);
////                    }
////                }
//            } else if (j < 0) {
//                botDrawing(i, g);
//                Log.d("BB", listSquare.get(i).isLeftLine() + "-"
//                        + listSquare.get(i).isTopLine() + "-"
//                        + listSquare.get(i).isRightLine() + "-"
//                        + listSquare.get(i).isBottomLine());
////                if (listSquare.get(i).checkLine() != 0) {
////                    // vẽ line còn lại
////                    if (listSquare.get(i).checkLine() == 1) {
////                        Log.d("CC", "Vẽ line");
////                        listSquare.get(i).setBottomLine(true);
////                    }
////                    if (listSquare.get(i).checkLine() == 2) {
////                        Log.d("CC", "Vẽ line");
////                        listSquare.get(i).setBottomLine(true);
////                    }
////                    if (listSquare.get(i).checkLine() == 3) {
////                        Log.d("CC", "Vẽ line");
////                        listSquare.get(i).setBottomLine(true);
////                    }
////                    if (listSquare.get(i).checkLine() == 4) {
////                        Log.d("CC", "Vẽ line");
////                        listSquare.get(i).setBottomLine(true);
////                    }
////                }
//            }
//        }
//    }

//    private void botDrawing(int a, Painter g) {
//        int x1, y1, x2, y2, point1, point2;
//        ySquare = a / (colQty - 1);// hàng
//        xSquare = a - (ySquare * (colQty - 1));// cột
//        Log.d("AA", listSquare.size() + "list");
//        //Log.d("AAA", "xSquare =" + xSquare + "ySquare= " + ySquare);
//        if (listSquare.get(a).checkLine() != 0) {
//
//
//            if (listSquare.get(a).checkLine() == 1) {
//                x1 = x2 = xSquare;
//                y1 = ySquare;
//                y2 = ySquare + 1;
//                point1 = twoToOne(x1, y1);
//                point2 = twoToOne(x2, y2);
//                if (checkSame(point1, point2)) {
//                    //Log.d("AA", point1 + "-" + point2);
//                    g.drawLine(Board.points.get(point1).getRectPoint().centerX() - 90
//                            , Board.points.get(point1).getRectPoint().centerY() - 90
//                            , Board.points.get(point2).getRectPoint().centerX() - 90
//                            , Board.points.get(point2).getRectPoint().centerY() - 90);
//                    drawline.add(new String(point1 + "," + point2));
//                    listSquare.get(a).setLeftLine(true);
//                }
//            } else {
//                drawRandom(g);
//            }
//        }
//        if (listSquare.get(a).checkLine() == 2) {
//            x1 = xSquare;
//            x2 = xSquare + 1;
//            y1 = ySquare;
//            y2 = ySquare;
//            point1 = twoToOne(x1, y1);
//            point2 = twoToOne(x2, y2);
//            if (checkSame(point1, point2)) {
//                //Log.d("AA", point1 + "-" + point2);
//                g.drawLine(Board.points.get(point1).getRectPoint().centerX() - 90
//                        , Board.points.get(point1).getRectPoint().centerY() - 90
//                        , Board.points.get(point2).getRectPoint().centerX() - 90
//                        , Board.points.get(point2).getRectPoint().centerY() - 90);
//                drawline.add(new String(point1 + "," + point2));
//                listSquare.get(a).setTopLine(true);
//            }
//
//            if (listSquare.get(a).isSquare()) {
//                drawRect(a, g);
//            }
//        } else {
//            drawRandom(g);
//        }
//        if (listSquare.get(a).checkLine() == 3) {
//            x1 = x2 = xSquare + 1;
//            y1 = ySquare;
//            y2 = ySquare + 1;
//            point1 = twoToOne(x1, y1);
//            point2 = twoToOne(x2, y2);
//            if (checkSame(point1, point2)) {
//                //Log.d("AA", point1 + "-" + point2);
//                g.drawLine(Board.points.get(point1).getRectPoint().centerX() - 90
//                        , Board.points.get(point1).getRectPoint().centerY() - 90
//                        , Board.points.get(point2).getRectPoint().centerX() - 90
//                        , Board.points.get(point2).getRectPoint().centerY() - 90);
//                drawline.add(new String(point1 + "," + point2));
//                listSquare.get(a).setRightLine(true);
//            }
//
//            if (listSquare.get(a).isSquare()) {
//                drawRect(a, g);
//            }
//
//        } else {
//            drawRandom(g);
//        }
//        if (listSquare.get(a).checkLine() == 4) {
//            x1 = xSquare + 1;
//            x2 = xSquare;
//            y1 = y2 = ySquare + 1;
//            point1 = twoToOne(x1, y1);
//            point2 = twoToOne(x2, y2);
//            if (checkSame(point1, point2)) {
//                //Log.d("AA", point1 + "-" + point2);
//                g.drawLine(Board.points.get(point1).getRectPoint().centerX() - 90
//                        , Board.points.get(point1).getRectPoint().centerY() - 90
//                        , Board.points.get(point2).getRectPoint().centerX() - 90
//                        , Board.points.get(point2).getRectPoint().centerY() - 90);
//                drawline.add(new String(point1 + "," + point2));
//                listSquare.get(a).setBottomLine(true);
//            }
//
//            if (listSquare.get(a).isSquare()) {
//                drawRect(a, g);
//            }
//
//        } else {
//            drawRandom(g);
//        }
//    }

    private int twoToOne(int x, int y) {
        int a = 0;
        a = x + (y * colQty);
        return a;
    }

//    private void drawRandom(Painter g) {
//        int point1, point2;
//        Random rand = new Random();
//        do {
//            point1 = rand.nextInt(Board.points.size()-1)+1;
//            point2 = rand.nextInt(Board.points.size()-1)+1;
//            Log.d("EE", point1 + "," + point2);
//            yA = point1 / (colQty);// hàng
//            xA = point1 - (yA * colQty);// cột
//            //Log.d("AA", xA + "- " + yA + "đây");
//            yB = point2 / (colQty);// hàng
//            xB = point2 - (yB * colQty);// cột
//        } while (checkSame(point1, point2)
//                && point1 != point2
//                && (point1 - point2) == 1
//                || (point1 - point2) == -1
//                || (point1 - point2) == 4
//                || (point1 - point2) == -4
//                );
////            if ((xA == 3 && xB == 0)
////                    || (xA == 0 && xB == 3)) {
////                Log.d("AA", xA + "- " + yA + "đâyyyy");
////                Log.d("AA", xB + "- " + yB + "đâyyyy");
////            } else {
//        Log.d("AA", isDraw + "");
//        g.drawLine(Board.points.get(point1).getRectPoint().centerX() - 90
//                , Board.points.get(point1).getRectPoint().centerY() - 90
//                , Board.points.get(point2).getRectPoint().centerX() - 90
//                , Board.points.get(point2).getRectPoint().centerY() - 90);
//        drawline.add(new String(point1 + "," + point2));
//        Log.d("ZZ", point1 + "-" + point2);
//    }


    private Libes oneToTwo(int a, int b) {
        Libes line = new Libes();
        yA = a / (colQty);// hàng
        xA = b - (yA * colQty);// cột
        Log.d("AA", xA + "- " + yA);
        yB = rectPoint.get(1) / (colQty);// hàng
        xB = rectPoint.get(1) - (yB * colQty);// cột
        Log.d("AA", xB + "- " + yB);
        line.setX1(xA);
        line.setY1(yA);
        line.setX2(xB);
        line.setY2(yB);
        return line;
    }

    private boolean checkSame(int a, int b) {
        boolean isDraw = true;
        for (int i = 0; i < drawline.size(); i++) {
            String[] split = drawline.get(i).split(",");
            Log.d("AAA", split[0].trim() + "-" + split[1].trim());
            if ((split[0].trim().equals(a + "")
                    || split[0].trim().equals(b + ""))
                    && (split[1].trim().equals(a + "")
                    || split[1].trim().equals(b + ""))) {
                isDraw = false;

                break;
            }
        }
        return isDraw;
    }
}
