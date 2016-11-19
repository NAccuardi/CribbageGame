package edu.up.cs301.Cribbage;

import android.util.Log;

import edu.up.cs301.Cribbage.CribPlayActions.CribDeal;
import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;

/**
 * Created by Robot Laptop on 11/14/2016.
 */

public class CribLocalGame extends LocalGame {

    //"Master"
    CribState state;


    /**
     * This is where the "master" class wil be. This will keep the most up to date copy of the game.
     * Nick.
     */
    public CribLocalGame(){
        Log.i("CribLocalGame", "creating game");//This will let us know that this has run correctly.
        state = new CribState();

    }


    protected void sendUpdatedStateTo(GamePlayer player) {

        //If there is no state to send to a player this will just return.
        if(state == null){
            return;
        }

        //If there is a state to send to a player this is where it will be sent.

        /*
            This will make a copy of our state and that is what we will send to the players. Since this is not a perfect information
            game we will want to hide information. Nick 16Nov2016
         */
        CribState cribStateForPlayer = new CribState(state);

        //This is where we will need to deal with hiding information from the other players.


        //This actually sends the copy to the player.
        player.sendInfo(cribStateForPlayer);


    }

    protected boolean canMove(int playerIdx) {
        return false;
    }

    protected String checkIfGameOver() {

        if (state.isScore121()) {//This will check to see if the game is over.

        }//end of if

        return null;
    }

    protected boolean makeMove(GameAction action) {
        return false;
    }


}
