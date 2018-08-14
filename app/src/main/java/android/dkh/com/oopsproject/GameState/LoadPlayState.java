package android.dkh.com.oopsproject.GameState;

import android.dkh.com.oopsproject.Game.Assets;
import android.dkh.com.oopsproject.util.Painter;
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
        Assets.playing = Assets.loadBitmap("playing.png", true);
        Assets.replay = Assets.loadBitmap("replay.png", true);
        Assets.next = Assets.loadBitmap("next.png", true);
        Assets.back = Assets.loadBitmap("back.png", true);
        Assets.btnpause = Assets.loadBitmap("pause.png", true);

    }

    @Override
    public void unload() {
        //Assets.unloadBitmap(Assets.menuBackground);
    }

}
