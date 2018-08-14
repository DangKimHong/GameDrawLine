package android.dkh.com.oopsproject.GameState;

import android.dkh.com.oopsproject.Game.Assets;
import android.dkh.com.oopsproject.util.Painter;
import android.graphics.Rect;
import android.view.MotionEvent;

/**
 * Created by MyPC on 27/06/2018.
 */

public class MenuState extends State {
    public static int playWith;
    public static int soundPlaybtn;
    public static int flag;
    private int xBg, yBg, widthBg, heightBg;
    private int xPlay, yPlay, widthPlay, heightPlay;
    private int xVol, yVol, widthVol, heightVol;
    private int xMute, yMute, widthMute, heightMute;
    Rect rectPlay = new Rect(280, 400, 430, 540);
    Rect rectPlayer = new Rect(220, 300, 470, 510);
    Rect rectBot = new Rect(220, 500, 470, 710);
//    Rect rectVol = new Rect(400, 850, 480, 930);
//    Rect rectMute = new Rect(400, 850, 480, 830);

    @Override
    public void init() {
        soundPlaybtn = Assets.loadSound("soundplay.wav");
        flag = 0;
        xBg = 0;
        yBg = 0;
        widthBg = 720;
        heightBg = 1080;
        xPlay = 280;
        yPlay = 400;
        widthPlay = 150;
        heightPlay = 140;
        xVol = 400;
        yVol = 850;
        widthVol = 80;
        heightVol = 80;
        playWith = -1;
    }

    @Override
    public void update(float delta) {
    }


    @Override
    public void render(Painter g) {
        g.drawImage(Assets.bg, xBg, yBg, widthBg, heightBg);
        g.drawImage(Assets.btnplayer, 220, 300, 250, 210);
        g.drawImage(Assets.btnbot, 220, 500, 250, 210);
//        g.drawImage(Assets.btnplay, xPlay, yPlay, widthPlay, heightPlay);
//        g.drawString("SOUND", 200, 900);
//        if (flag == 1){
//            g.drawImage(Assets.mute, xVol, yVol, widthVol, heightVol);
//
//        }else if (flag == 0){
//            g.drawImage(Assets.volume, xVol, yVol, widthVol, heightVol);
//            g.drawRect(rectVol);
//        }
    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        if (rectPlayer.contains(scaledX, scaledY)) {
            Assets.playSound(soundPlaybtn);
            setCurrentState(new LoadPlayState());
            playWith = 0;
        }
        if (rectBot.contains(scaledX, scaledY)) {
            Assets.playSound(soundPlaybtn);
            setCurrentState(new LoadPlayState());
            playWith = 1;
        }


//        if (rectVol.contains(scaledX, scaledY)){
//            Assets.playSound(soundPlaybtn);
//            flag = 1;
//        }else  {
//            flag = 0;
//        }
        return false;
    }
}
