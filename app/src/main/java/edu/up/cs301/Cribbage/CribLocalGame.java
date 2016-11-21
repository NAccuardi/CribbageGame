package edu.up.cs301.Cribbage;

import android.util.Log;

import edu.up.cs301.Cribbage.CribPlayActions.CribActions.CribAction1;
import edu.up.cs301.Cribbage.CribPlayActions.CribActions.CribAction2;
import edu.up.cs301.Cribbage.CribPlayActions.CribActions.CribAction3;
import edu.up.cs301.Cribbage.CribPlayActions.CribActions.CribAction4;
import edu.up.cs301.Cribbage.CribPlayActions.CribActions.CribAction5;
import edu.up.cs301.Cribbage.CribPlayActions.CribActions.CribAction6;
import edu.up.cs301.Cribbage.CribPlayActions.CribDeal;
import edu.up.cs301.Cribbage.CribPlayActions.CribGo;
import edu.up.cs301.Cribbage.CribPlayActions.PlayActions.CribPlayAction1;
import edu.up.cs301.Cribbage.CribPlayActions.PlayActions.CribPlayAction2;
import edu.up.cs301.Cribbage.CribPlayActions.PlayActions.CribPlayAction3;
import edu.up.cs301.Cribbage.CribPlayActions.PlayActions.CribPlayAction4;
import edu.up.cs301.Cribbage.CribPlayActions.PlayActions.CribPlayAction5;
import edu.up.cs301.Cribbage.CribPlayActions.PlayActions.CribPlayAction6;
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
        //return false;
         return true;
       //for(int i =0; i<players.length;i++){
       //    if(getPlayerIdx(players[i])==playerIdx){
       //        state.setTurn(playerIdx);
       //        return true;
       //    }
       //}
       //return false;
    }

    protected String checkIfGameOver() {

        if (state.isScore121()) {//This will check to see if the game is over.
            // we want to display who the winner is in a pop up box.



        }//end of if

        return null;
    }


    //*************This is where we will handle the moves for each player
    protected boolean makeMove(GameAction action) {
        Log.i("Make Move", "entered make move");
        if(action instanceof CribDeal){
            Log.i("Make Move Action: ", "CribDeal");
           state.deal();

        }else if( action instanceof CribGo){
            Log.i("Make Move Action: ", "CribGo");

        }else if(action instanceof CribAction1){
            Log.i("Make Move Action: ", "CribAction1");


        }else if(action instanceof CribAction2){
            Log.i("Make Move Action: ", "CribAction2");

        }else if(action instanceof CribAction3){
            Log.i("Make Move Action: ", "CribAction3");

        }else if(action instanceof CribAction4){
            Log.i("Make Move Action: ", "CribAction4");

        }else if(action instanceof CribAction5){
            Log.i("Make Move Action: ", "CribACtion5");

        }else if(action instanceof CribAction6){
            Log.i("Make Move Action: ", "CribAction6");

        }else if(action instanceof CribPlayAction1){
            Log.i("Make Move Action: ", "CribPLayAction1");

        }else if(action instanceof CribPlayAction2){
            Log.i("Make Move Action: ", "CribPLayAction2");


        }else if(action instanceof CribPlayAction3){
            Log.i("Make Move Action: ", "CribPLayAction3");

        }else if(action instanceof CribPlayAction4){
            Log.i("Make Move Action: ", "CribPLayAction4");

        }else if(action instanceof CribPlayAction5){
            Log.i("Make Move Action: ", "CribPLayAction5");

        }else if(action instanceof CribPlayAction6){
            Log.i("Make Move Action: ", "CribPLayAction6");

        }


        return false;
    }


}//endCriblocalGame
