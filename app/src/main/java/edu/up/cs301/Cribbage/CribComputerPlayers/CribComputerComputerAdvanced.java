package edu.up.cs301.Cribbage.CribComputerPlayers;

import android.drm.DrmStore;

import java.util.ArrayList;

import edu.up.cs301.Cribbage.CribComputerPlayer;
import edu.up.cs301.Cribbage.CribMoveAction;
import edu.up.cs301.Cribbage.CribPlayActions.CribActions.CribAction1;
import edu.up.cs301.Cribbage.CribPlayActions.CribActions.CribAction2;
import edu.up.cs301.Cribbage.CribPlayActions.CribActions.CribAction3;
import edu.up.cs301.Cribbage.CribPlayActions.CribActions.CribAction4;
import edu.up.cs301.Cribbage.CribPlayActions.CribActions.CribAction5;
import edu.up.cs301.Cribbage.CribPlayActions.CribActions.CribAction6;
import edu.up.cs301.Cribbage.CribState;
import edu.up.cs301.Cribbage.Deck;
import edu.up.cs301.card.Card;
import edu.up.cs301.game.infoMsg.GameInfo;

/**
 * Created by Robot Laptop on 11/16/2016.
 */

public class CribComputerComputerAdvanced extends CribComputerPlayer {


    //most recent state of the game
    private CribState computerPlayerState;
    private CribMoveAction[] cribActions = new CribMoveAction[6];
    private CribAction1 cribact1;
    private CribAction2 cribact2;
    private CribAction3 cribact3;
    private CribAction4 cribact4;
    private CribAction5 cribact5;
    private CribAction6 cribact6;
    private int[] cardsToCrib = new int[2];




    public CribComputerComputerAdvanced(String name) {
        super(name);
    }


    @Override
    protected void receiveInfo(GameInfo info) {
        cribActions[0] = cribact1;
        cribActions[1] = cribact2;
        cribActions[2] = cribact3;
        cribActions[3] = cribact4;
        cribActions[4] = cribact5;
        cribActions[5] = cribact6;
        cardsToCrib[0] = 0;
        cardsToCrib[1] = 0;


        // if we don't have a game-state, ignore
        if (!(info instanceof CribState)) {
            return;
        }

        // update our state variable
        computerPlayerState = (CribState) info;

        //if i'm the dealer I want to play good cards to the crib
        if(computerPlayerState.getDealer() == playerNum)
        {
            //I'm the dealer!
            Deck temp  = new Deck(computerPlayerState.handsOfBothPlayers[playerNum]);
            Card[] cards = new Card[temp.size()];
            int testValue;
            if(temp.size() == 6)//have not checked for pairs
            {
                for(int i = 0; i<temp.size(); i++)
                {
                    if (cards[i] != null) {
                        testValue = cards[i].getRank().value(1);
                        for (int j = i + 1; j < cards.length; j++) {
                            if(cards[j] != null) {
                                if (testValue == cards[j].getRank().value(1)) {
                                    //send crib actions coresponding to the i and j values
                                    cardsToCrib[0] = i;
                                    cardsToCrib[1] = j;
                                    game.sendAction(cribActions[i]);
                                }
                            }
                        }
                    }
                }
                //if it does not find something it just plays a random action
                game.sendAction(cribActions[0]);
            }
            if(temp.size() == 5)//have already played one card
            {
                game.sendAction(cribActions[cardsToCrib[1]-1]);
            }

        }




    }



}
