package android.dkh.com.oopsproject.GameState;

import android.dkh.com.oopsproject.Game.Assets;
import android.dkh.com.oopsproject.util.Painter;
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
        g.drawImage(Assets.bg, 0, 0, 720, 1080);
//        g.drawImage(Assets.btnplay, 200, 400, 150, 150);
//        g.drawImage(Assets.volume, 400, 200, 200, 200);
//        g.drawImage(Assets.level1, 400, 200, 200, 200);
//        g.drawImage(Assets.level2, 400, 200, 200, 200);
//        g.drawImage(Assets.level3, 400, 200, 200, 200);
        g.drawImage(Assets.btnbot, 200, 700, 150, 300);
        g.drawImage(Assets.btnplayer, 200, 400, 150, 300);
    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        return false;
    }

    @Override
    public void load() {
        Assets.bg = Assets.loadBitmap("bgmenu.png", true);
        Assets.btnplayer = Assets.loadBitmap("btnplayer.png", true);
        Assets.btnbot = Assets.loadBitmap("btnbot.png", true);

    }
}
