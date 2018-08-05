package android.dkh.com.oopsproject.GameState;

import android.dkh.com.oopsproject.Game.Assets;
import android.dkh.com.oopsproject.until.Painter;
import android.graphics.Color;
import android.view.MotionEvent;

/**
 * Created by MyPC on 27/06/2018.
 */

public class LoadPlayState extends State{
    @Override
    public void init() {
        load();
    }

    @Override
    public void update(float delta) {
        unload();
        setCurrentState(new PlayState());
    }


    @Override
    public void render(Painter g) {

    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        return false;
    }

    @Override
    public void load() {
        Assets.playbackground = Assets.loadBitmap("background.png", true);
    }

    @Override
    public void unload() {
        //Assets.unloadBitmap(Assets.menuBackground);
    }

}
