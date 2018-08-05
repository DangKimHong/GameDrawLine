package android.dkh.com.oopsproject.GameState;

import android.dkh.com.oopsproject.Game.Assets;
import android.dkh.com.oopsproject.until.Painter;
import android.view.MotionEvent;

/**
 * Created by MyPC on 27/06/2018.
 */

public class LoadMenuState extends State {

    @Override
    public void init() {
        load();
    }

    @Override
    public void update(float delta) {
        setCurrentState(new MenuState());
    }


    @Override
    public void render(Painter g) {
//        g.drawImage(Assets.playbackground, 0, 0, 1920, 1080);
//        g.drawImage(Assets.btnplay, 500, 400, 900,150);
//        g.drawImage(Assets.btnSignin, 100, 550, 900,150);
//        g.drawImage(Assets.btnSignup, 900, 550, 900,150);
    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        return false;
    }

    @Override
    public void load() {
        Assets.bg = Assets.loadBitmap("bg.png", true);
        Assets.btnplay = Assets.loadBitmap("btnplay.png", true);
    }
}
