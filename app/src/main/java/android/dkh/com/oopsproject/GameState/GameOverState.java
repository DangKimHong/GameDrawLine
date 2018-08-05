package android.dkh.com.oopsproject.GameState;

import android.dkh.com.oopsproject.Game.Assets;
import android.dkh.com.oopsproject.until.Painter;
import android.graphics.Rect;
import android.view.MotionEvent;

/**
 * Created by MyPC on 27/06/2018.
 */

public class GameOverState extends State {

    private String playerScore;
    private Rect rectCon;

    public GameOverState(int playerScore) {
        this.playerScore = "" + playerScore;
    }

    public GameOverState() {
    }

    @Override
    public void init() {
        rectCon = new Rect(500, 400, 1100, 600);

    }


    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Painter g) {
        g.drawImage(Assets.gameover, 500, 200, 900, 200 );
        g.drawImage(Assets.btncontinue, 600, 400, 700, 150);
    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        if (rectCon.contains(scaledX, scaledY)){
            setCurrentState(new LoadMenuState());
        }
        return false;
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }
}
