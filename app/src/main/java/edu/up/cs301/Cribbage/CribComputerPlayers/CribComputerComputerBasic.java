package edu.up.cs301.Cribbage.CribComputerPlayers;

import android.util.Log;

import edu.up.cs301.Cribbage.CribComputerPlayer;
import edu.up.cs301.Cribbage.CribPlayActions.CribActions.CribAction1;
import edu.up.cs301.Cribbage.CribPlayActions.CribDeal;
import edu.up.cs301.Cribbage.CribPlayActions.PlayActions.CribPlayAction1;
import edu.up.cs301.Cribbage.CribState;
import edu.up.cs301.game.infoMsg.GameInfo;

/**
 * Created by Nick Accuardi on 10/20/2016.
 */

public class CribComputerComputerBasic extends CribComputerPlayer {

    //most recent state of the game
    private CribState computerPlayerState;
    CribAction1 cribact1;
    CribPlayAction1 playAct1;

    //
//    private int myPlayerId = computerPlayerState.getPlayerId();

    /**
     * constructor
     *
     * @param name the player's name (e.g., "John")
     */
    public CribComputerComputerBasic(String name) {
        super(name);
    }


    @Override
    protected void receiveInfo(GameInfo info) {

        // if we don't have a game-state, ignore
        if (!(info instanceof CribState)) {
            return;
        }

        // update our state variable
        computerPlayerState = (CribState) info;
        Log.i("COMP play1hand", computerPlayerState.handsOfBothPlayers[1].toString());
        Log.i("COMP CribDeck", computerPlayerState.cribDeck.toString());
       //if(computerPlayerState.getDealer() == computerPlayerState.getPlayerId())
       //{
       //   game.sendAction(new CribDeal(this));
       //}
       //computerPlayerState.getHand(computerPlayerState.getPlayerId());

        if(computerPlayerState.getStage()==2 && computerPlayerState.getWhoseTurn()==playerNum &&
                computerPlayerState.handsOfBothPlayers[1].size()>4){

            if(computerPlayerState.canCrib(playerNum))
                cribact1 = new CribAction1(this);
            sleep(1000);
            game.sendAction(cribact1);

        }

        if(computerPlayerState.getStage() == 3 && computerPlayerState.getWhoseTurn() == playerNum){
            if(computerPlayerState.handsOfBothPlayers[playerNum].size() > 0) {

                playAct1 = new CribPlayAction1(this);
                sleep(1000);
                game.sendAction(playAct1);
                Log.i("COMP hand", computerPlayerState.handsOfBothPlayers[playerNum].toString());
                Log.i("COMP playdeck", computerPlayerState.playDeck.toString());
                Log.i("COMP cards played to pd", computerPlayerState.eachPlayerCardsPlayed[playerNum].toString());
            }
        }

    }


}
