package edu.up.cs301.Cribbage;

import android.util.Log;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;

/**
 * Created by Robot Laptop on 11/14/2016.
 */

public class CribLocalGame extends LocalGame {

    //"Master"
    CribState cribState;

    /**
     * This is where the "master" class wil be. This will keep the most up to date copy of the game.
     * Nick.
     */
    public CribLocalGame(){
        Log.i("CribLocalGame", "creating game");//This will let us know that this has run correctly.
        cribState = new CribState();
    }
    protected void sendUpdatedStateTo(GamePlayer player) {

        //If there is no state to send to a player this will just return.
        if(cribState == null){
            return;
        }

        //If there is a state to send to a player this is where it will be sent.

    }

    protected boolean canMove(int playerIdx) {
        return false;
    }

    protected String checkIfGameOver() {

        if (cribState.isScore121()) {//This will check to see if the game is over.

        }//end of if

        return null;
    }

    protected boolean makeMove(GameAction action) {
        return false;
    }


}
