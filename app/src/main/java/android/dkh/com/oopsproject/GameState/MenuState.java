package android.dkh.com.oopsproject.GameState;

import android.dkh.com.oopsproject.Game.Assets;
import android.dkh.com.oopsproject.until.Painter;
import android.graphics.Rect;
import android.view.MotionEvent;

/**
 * Created by MyPC on 27/06/2018.
 */

public class MenuState extends State {
    private int xBg, yBg, widthBg, heightBg;
    private int xPlay, yPlay, widthPlay, heightPlay;
    Rect rectPlay = new Rect(500, 400, 1400, 550);
    Rect rectSignin = new Rect(100, 550, 1000, 700);
    Rect rectSignup = new Rect(1100, 550, 2000, 1150);
    @Override
    public void init() {
        xBg = 0; yBg = 0; widthBg = 1920; heightBg = 1080;
        xPlay = 500; yPlay = 400; widthPlay = 900; heightPlay = 150;
    }

    @Override
    public void update(float delta) {
    }


    @Override
    public void render(Painter g) {
        g.drawImage(Assets.bg, xBg, yBg, widthBg, heightBg);
        g.drawImage(Assets.btnplay, xPlay, yPlay, widthPlay,heightPlay);
    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        if(rectPlay.contains(scaledX, scaledY)){
            setCurrentState(new LoadPlayState());
        }

        if(rectSignin.contains(scaledX, scaledY)){

        }
        if(rectSignup.contains(scaledX, scaledY)){

        }
        return false;
    }
}
