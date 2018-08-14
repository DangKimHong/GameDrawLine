package android.dkh.com.oopsproject.GameState;

import android.dkh.com.oopsproject.Game.Assets;
import android.dkh.com.oopsproject.util.Painter;
import android.graphics.Rect;
import android.view.MotionEvent;

/**
 * Created by MyPC on 27/06/2018.
 */

public class GameOverState extends State {

    private String playerScore;
    private Rect rectReplay;
    private int xBg, yBg, widthBg, heightBg;
    public static int soundReplay;

    public GameOverState(int playerScore) {
        this.playerScore = "" + playerScore;
    }

    public GameOverState() {
    }

    @Override
    public void init() {
        soundReplay = Assets.loadSound("soundplay.wav");
        xBg = 0; yBg = 0; widthBg = 720; heightBg = 1080;
        rectReplay = new Rect(330, 600, 430, 700);
    }


    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Painter g) {
        g.drawImage(Assets.bg, xBg, yBg, widthBg, heightBg);
        if (PlayState.listDraw.size() == ((PlayState.colQty - 1) * (PlayState.rowQty - 1))) {
            if (PlayState.scoreA > PlayState.scoreB) {
                g.drawStringResult("WHITE WIN", 240, 500);
            } else {
                g.drawStringResult("BLACK WIN", 240, 500);
            }
            g.drawImage(Assets.replay, 330, 600, 100, 90);
        }
    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        if (rectReplay.contains(scaledX, scaledY)){
            Assets.playSound(soundReplay);
            setCurrentState(new PlayState());
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
