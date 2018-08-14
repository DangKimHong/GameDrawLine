package android.dkh.com.oopsproject.GameState;


import android.dkh.com.oopsproject.MainActivity;
import android.dkh.com.oopsproject.util.Painter;
import android.view.MotionEvent;

/**
 * Created by MyPC on 19/06/2018.
 */

public abstract class State {
    public void setCurrentState(State newState) {
        MainActivity.sGame.setCurrentState(newState);
    }

    public abstract void init();

    public abstract void update(float delta);

    public abstract void render(Painter g);

    public abstract boolean onTouch(MotionEvent e, int scaledX, int scaledY);

    public void onPause(){}

    public void onResume() {}

    public void load() {}

    public void unload() {}
}