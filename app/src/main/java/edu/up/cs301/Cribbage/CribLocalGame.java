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

import static edu.up.cs301.game.R.id.handCardPos1;
import static java.lang.Math.abs;

/**
 * Created by Robot Laptop on 11/14/2016.
 *
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
        return playerIdx != 1- state.getWhoseTurn();
    }

    protected String checkIfGameOver() {

        if (state.isScore121()) {//This will check to see if the game is over.
            // we want to display who the winner is in a pop up box.



        }//end of if

        return null;
    }


    //*************This is where we will handle the moves for each player
    protected boolean makeMove(GameAction action) {
        state.mustGo(state.getWhoseTurn());
        Log.i("Make Move", "entered make move");
        if(action instanceof CribDeal){
            Log.i("Make Move Action: ", "CribDeal");

            state.setScore(0,state.nicksScoringEndofTurn(state.cutDeck,state.eachPlayerCardsPlayed[0]));
            state.setScore(1,state.nicksScoringEndofTurn(state.cutDeck,state.eachPlayerCardsPlayed[1]));
            state.setScore(state.getWhoseTurn(),state.nicksScoringEndofTurn(state.cutDeck,state.eachPlayerCardsPlayed[state.getWhoseTurn()]));

            state.setDealer();

            if(!state.delt) {
                state.deal();
                state.setStage();
                //state.count31=0;

            }
            //state.deal();
        Log.i("Player 0 hand is",state.getHand(0).toString());
            return true;


        }else if(action instanceof CribGo){
            state.count31=0;
            state.bothplayersScores[abs(state.getWhoseTurn()-1)]++;
            state.mustGoBool=false;
            state.setTurn(state.getWhoseTurn());
            Log.i("Make Move Action: ", "CribGo");
            return true;


        }else if(action instanceof CribAction1){
            Log.i("Make Move Action: ", "CribAction1");


            //Log.i("cribDeck Premove: ", state.cribDeck.toString());
            Log.i("playerhand0: ", state.handsOfBothPlayers[0].toString());
            Log.i("handsofbothplayers[0]", state.handsOfBothPlayers[0].toString());
            Log.i("handsofbothplayers[get]", state.handsOfBothPlayers[state.getWhoseTurn()].toString());

            state.handsOfBothPlayers[state.getWhoseTurn()].moveSpecificCard(0,state.cribDeck);
            Log.i("cribDeck Postmove: ", state.cribDeck.toString());
            Log.i("we are in stage*******", ""+state.getStage());


            state.setStage();
            Log.i("we are in stage*******", ""+state.getStage());
            

            return true;


        }else if(action instanceof CribAction2){
            Log.i("Make Move Action: ", "CribAction2");
            state.handsOfBothPlayers[state.getWhoseTurn()].moveSpecificCard(1,state.cribDeck);
            Log.i("cribDeck Postmove: ", state.cribDeck.toString());
            Log.i("we are in stage", ""+state.getStage());

            state.setStage();
            return true;
        }else if(action instanceof CribAction3){
            Log.i("Make Move Action: ", "CribAction3");
            state.handsOfBothPlayers[state.getWhoseTurn()].moveSpecificCard(2,state.cribDeck);
            Log.i("cribDeck Postmove: ", state.cribDeck.toString());
            Log.i("we are in stage", ""+state.getStage());

            state.setStage();
            return true;
        }else if(action instanceof CribAction4){
            Log.i("Make Move Action: ", "CribAction4");
            state.handsOfBothPlayers[state.getWhoseTurn()].moveSpecificCard(3,state.cribDeck);
            Log.i("cribDeck Postmove: ", state.cribDeck.toString());
            Log.i("we are in stage", ""+state.getStage());

            state.setStage();
            return true;
        }else if(action instanceof CribAction5){
            Log.i("Make Move Action: ", "CribACtion5");
            state.handsOfBothPlayers[state.getWhoseTurn()].moveSpecificCard(4,state.cribDeck);
            Log.i("cribDeck Postmove: ", state.cribDeck.toString());
            Log.i("we are in stage", ""+state.getStage());

            state.setStage();
            return true;
        }else if(action instanceof CribAction6){
            Log.i("Make Move Action: ", "CribAction6");
            state.handsOfBothPlayers[state.getWhoseTurn()].moveSpecificCard(5,state.cribDeck);
            Log.i("cribDeck Postmove: ", state.cribDeck.toString());
            Log.i("we are in stage", ""+state.getStage());

            state.setStage();
            return true;
        }else



        if(action instanceof CribPlayAction1){

//            if(state.isGo(state.getWhoseTurn()))
//            {
//                if(state.getWhoseTurn() == 0)
//                {
//                    state.setTurn(1);
//                    //state.setScore(1,1);
//                }
//                if(state.getWhoseTurn() == 1)
//                {
//                    state.setTurn(0);
//                    //state.setScore(0,1);
//                }
//                return true;
//            }


            Log.i("Make Move Action: ", "CribPLayAction1");
            state.playDeck.add(state.handsOfBothPlayers[state.getWhoseTurn()].lookAtCard(0));
            state.handsOfBothPlayers[state.getWhoseTurn()].moveSpecificCard(0,state.eachPlayerCardsPlayed[state.getWhoseTurn()]);

            //score the play deck to see if the player gets points
            //state.setScore(state.getWhoseTurn(),state.scorePlay(state.playDeck));

            Log.i("playDeck", state.playDeck.toString());
            Log.i("each player cards play",state.getWhoseTurn()+  state.eachPlayerCardsPlayed[state.getWhoseTurn()].toString());

            state.setCount();
            state.setStage();
            return true;
        }else if(action instanceof CribPlayAction2){
            Log.i("Make Move Action: ", "CribPLayAction2");

            state.playDeck.add(state.handsOfBothPlayers[state.getWhoseTurn()].lookAtCard(1));
            state.handsOfBothPlayers[state.getWhoseTurn()].moveSpecificCard(1,state.eachPlayerCardsPlayed[state.getWhoseTurn()]);

            //score the play deck to see if the player gets points
            //state.setScore(state.getWhoseTurn(),state.scorePlay(state.playDeck));

            Log.i("playDeck", state.playDeck.toString());
            Log.i("each player cards play",state.getWhoseTurn()+  state.eachPlayerCardsPlayed[state.getWhoseTurn()].toString());
            state.setCount();
            state.setStage();
            return true;

        }else if(action instanceof CribPlayAction3){
            Log.i("Make Move Action: ", "CribPLayAction3");

            state.playDeck.add(state.handsOfBothPlayers[state.getWhoseTurn()].lookAtCard(2));
            state.handsOfBothPlayers[state.getWhoseTurn()].moveSpecificCard(2,state.eachPlayerCardsPlayed[state.getWhoseTurn()]);

            //score the play deck to see if the player gets points
            //state.setScore(state.getWhoseTurn(),state.scorePlay(state.playDeck));

            Log.i("playDeck", state.playDeck.toString());
            Log.i("each player cards play",state.getWhoseTurn()+  state.eachPlayerCardsPlayed[state.getWhoseTurn()].toString());
            state.setCount();
            state.setStage();
            return true;
        }else if(action instanceof CribPlayAction4){
            Log.i("Make Move Action: ", "CribPLayAction4");

            state.playDeck.add(state.handsOfBothPlayers[state.getWhoseTurn()].lookAtCard(3));
            state.handsOfBothPlayers[state.getWhoseTurn()].moveSpecificCard(3,state.eachPlayerCardsPlayed[state.getWhoseTurn()]);

            //score the play deck to see if the player gets points
            //state.setScore(state.getWhoseTurn(),state.scorePlay(state.playDeck));

            Log.i("playDeck", state.playDeck.toString());
            Log.i("each player cards play",state.getWhoseTurn()+  state.eachPlayerCardsPlayed[state.getWhoseTurn()].toString());
            state.setCount();
            state.setStage();
            return true;
        }else if(action instanceof CribPlayAction5){
            Log.i("Make Move Action: ", "CribPLayAction5");

            state.playDeck.add(state.handsOfBothPlayers[state.getWhoseTurn()].lookAtCard(4));
            state.handsOfBothPlayers[state.getWhoseTurn()].moveSpecificCard(4,state.eachPlayerCardsPlayed[state.getWhoseTurn()]);

            //score the play deck to see if the player gets points
            //state.setScore(state.getWhoseTurn(),state.scorePlay(state.playDeck));

            Log.i("playDeck", state.playDeck.toString());
            Log.i("each player cards play",state.getWhoseTurn()+ state.eachPlayerCardsPlayed[state.getWhoseTurn()].toString());

            state.setCount();
            state.setStage();
            return true;
        }else if(action instanceof CribPlayAction6){
            Log.i("Make Move Action: ", "CribPLayAction6");

            state.playDeck.add(state.handsOfBothPlayers[state.getWhoseTurn()].lookAtCard(5));
            state.handsOfBothPlayers[state.getWhoseTurn()].moveSpecificCard(5,state.eachPlayerCardsPlayed[state.getWhoseTurn()]);

            //score the play deck to see if the player gets points
            //state.setScore(state.getWhoseTurn(),state.scorePlay(state.playDeck));

            Log.i("playDeck", state.playDeck.toString());
            Log.i("each player cards play",state.getWhoseTurn()+  state.eachPlayerCardsPlayed[state.getWhoseTurn()].toString());

            state.setCount();
            state.setStage();
            return true;
        }


        return false;
    }


}//endCriblocalGame
