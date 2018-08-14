package android.dkh.com.oopsproject.Models;

import android.dkh.com.oopsproject.GameState.PlayState;
import android.dkh.com.oopsproject.MainActivity;
import android.dkh.com.oopsproject.util.Painter;
import android.graphics.Rect;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MyPC on 27/06/2018.
 */

public class Board {

    final int OVAL_WIDTH = 10;
    final int OVAL_HEGHT = 10;
    public static int cellWidth, cellHeight, totalRowQty, totalColQty, rowQty, colQty, startX, startY, endX, endY;
    public static int [][] board;
    public static List<Point> points;
    private Rect rectBoard;
    List<Libes> lines;


    public Board(int cellWidth, int cellHeight, int totalRowQty, int totalColQty, int startX, int startY) {
        points = new ArrayList<>();
        lines =new ArrayList<>();

        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
        this.totalRowQty = totalRowQty;
        this.totalColQty = totalColQty;

        this.startX = startX;
        this.startY = startY;
        this.endX = startX + totalColQty * cellWidth;
        this.endY = startY + totalRowQty * cellHeight;

        this.rowQty = totalRowQty - 2;
        this.colQty = totalColQty - 2;

        this.board = new int[totalRowQty][totalColQty];

        for (int i = 0; i < totalRowQty; i++) {
            for (int j = 0; j < totalColQty; j++)
                this.board[i][j] = -1;
        }
    }

    public void drawBoard(Painter g) {

        startY = (MainActivity.GAME_HEIGHT - (PlayState.rowQty)*cellHeight)/2+100;// +100
        startX = (MainActivity.GAME_WIDTH - (PlayState.colQty)*cellWidth)/2 +90;//+90
        Log.d("XX", startX + "-" +startY);
        for(int i = 0; i < PlayState.colQty; i++) {
            for (int j = 0; j < PlayState.rowQty; j++) {
                points.add(new Point(startX + PlayState.cellWidth * j, startY + PlayState.cellHeight * i,
                                        OVAL_WIDTH, OVAL_HEGHT));
            }
        }

        for (int i = 0; i < points.size(); i++) {
            points.get(i).getRectPoint().right = points.get(i).getRectPoint().left + cellWidth ;
            points.get(i).getRectPoint().bottom = points.get(i).getRectPoint().top + cellHeight;
            g.fillOval(points.get(i).getRectPoint().left,
                        points.get(i).getRectPoint().top,
                    points.get(i).getRectPoint().right - points.get(i).getRectPoint().left,
                    points.get(i).getRectPoint().bottom - points.get(i).getRectPoint().top);
//            g.drawRect(points.get(i).getRectPoint());
//            g.setColor(Color.WHITE);
        }
    }
}

