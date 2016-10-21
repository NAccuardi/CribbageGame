package edu.up.cs301.Criggabage;

import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

import edu.up.cs301.animation.Animator;
import edu.up.cs301.game.GameHumanPlayer;
import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.infoMsg.GameInfo;

/**
 * Created by Nick Accuardi on 10/20/2016.
 */

public class CribbageHumanPlayer extends GameHumanPlayer implements Animator {


    /**
     * default constructor
     *
     * @param name the name of the player
     */
    public CribbageHumanPlayer(String name) {
        super(name);
    }

    public void setAsGui(GameMainActivity activity) {

    }

    @Override
    public void receiveInfo(GameInfo info) {

    }

    @Override
    public View getTopView() {
        return null;
    }

    public int backgroundColor() {
        return 0;
    }

    public boolean doQuit() {
        return false;
    }

    public void onTouch(MotionEvent event) {

    }

    public void tick(Canvas canvas) {

    }

    public boolean doPause() {
        return false;
    }

    public int interval() {
        return 0;
    }
}
