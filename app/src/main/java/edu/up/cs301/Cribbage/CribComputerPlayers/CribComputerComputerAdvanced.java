package edu.up.cs301.Cribbage.CribComputerPlayers;

import android.drm.DrmStore;
import android.util.Log;

import java.util.ArrayList;

import edu.up.cs301.Cribbage.CribComputerPlayer;
import edu.up.cs301.Cribbage.CribMoveAction;
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
    private CribAction1 cribact1;
    private CribAction2 cribact2;
    private CribAction3 cribact3;
    private CribAction4 cribact4;
    private CribAction5 cribact5;
    private CribAction6 cribact6;
    CribPlayAction1 playAct1;
    CribPlayAction2 playAct2;
    CribPlayAction3 playAct3;
    CribPlayAction4 playAct4;
    CribGo cribGoAction;
    CribDeal cribDealAction;
    private int oppNum;
    private int[] cardsToCrib = new int[2];
    private int[] cardsToNotCrib = new int[2];

    public CribComputerComputerAdvanced(String name) {
        super(name);
    }


    @Override
    protected void receiveInfo(GameInfo info) {
        // if we don't have a game-state, ignore
        if (!(info instanceof CribState)) {
            return;
        }
        //set up the opp's player number
        if(playerNum == 1)
        {
            oppNum = 0;
        }
        else
        {
            oppNum = 1;
        }

        // update our state variable
        computerPlayerState = (CribState) info;

        if(computerPlayerState.getStage()==2 && computerPlayerState.getWhoseTurn()==playerNum &&
                computerPlayerState.handsOfBothPlayers[playerNum].size()>4)
        {
            ////////////////////////////////////////////////////////////////////////////////////////
            //attempting algorithm of inteligence
            if(computerPlayerState.getDealer() == playerNum)
            {
                //I'm the dealer!
                Deck temp  = new Deck(computerPlayerState.handsOfBothPlayers[playerNum]);
                int testValue;
                if(temp.size() == 6)//have not checked for pairs
                {
                    for(int i = 0; i<temp.size(); i++)
                    {
                            testValue = temp.lookAtCard(i).getRank().value(1);
                            for (int j = i + 1; j < temp.size(); j++) {
                                    if (testValue == temp.lookAtCard(j).getRank().value(1)) {
                                        //send crib actions coresponding to the i and j values
                                        cardsToCrib[0] = i;
                                        cardsToCrib[1] = j;
                                    }

                            }

                    }
                    Log.i("value of cards crib:", ""+ cardsToCrib[0]);
                    if(cardsToCrib[0] == 0)
                    {
                        cribact1 = new CribAction1(this);
                        game.sendAction(cribact1);
                    }
                    else if(cardsToCrib[0] == 1)
                    {
                        cribact2 = new CribAction2(this);
                        game.sendAction(cribact2);
                    }
                    else if(cardsToCrib[0] == 2)
                    {
                        cribact3 = new CribAction3(this);
                        game.sendAction(cribact3);
                    }
                    else if(cardsToCrib[0] == 3)
                    {
                        cribact4 = new CribAction4(this);
                        game.sendAction(cribact4);
                    }
                    else if(cardsToCrib[0] == 4)
                    {
                        cribact5 = new CribAction5(this);
                        game.sendAction(cribact5);
                    }
                    else if(cardsToCrib[0] == 5)
                    {
                        cribact6 = new CribAction6(this);
                        game.sendAction(cribact6);
                    }
                    else
                    {
                        cribact1 = new CribAction1(this);
                        game.sendAction(cribact1);
                    }
                }
                if(temp.size() == 5)//have already played one card
                {
                    //Log.i("COMP cribed random card","");
                    if(cardsToCrib[1] == 1)
                    {
                        cribact1 = new CribAction1(this);
                        game.sendAction(cribact1);
                    }
                    else if(cardsToCrib[1] == 2)
                    {
                        cribact2 = new CribAction2(this);
                        game.sendAction(cribact2);
                    }
                    else if(cardsToCrib[1] == 3)
                    {
                        cribact3 = new CribAction3(this);
                        game.sendAction(cribact3);
                    }
                    else if(cardsToCrib[1] == 4)
                    {
                        cribact4 = new CribAction4(this);
                        game.sendAction(cribact4);
                    }
                    else if(cardsToCrib[1] == 5)
                    {
                        cribact5 = new CribAction5(this);
                        game.sendAction(cribact5);
                    }
                    else
                    {
                        cribact1 = new CribAction1(this);
                        game.sendAction(cribact1);
                    }
                }

            }
            //end inteligent algorithm
            /////////////////////////////////////////////////////////////////////////////////////////

            /////////////////////////////////////////////////////////////////////////////////////////
            //A algorithm that purposely does not play pairs if the dealer
            if(computerPlayerState.getDealer() != playerNum)
            {
                //I'm the dealer!
                Deck temp  = new Deck(computerPlayerState.handsOfBothPlayers[playerNum]);
                int testValue;
                if(temp.size() == 6)//have not checked for pairs
                {
                    for(int i = 0; i<temp.size(); i++)
                    {
                        testValue = temp.lookAtCard(i).getRank().value(1);
                        for (int j = i + 1; j < temp.size(); j++) {
                            if (testValue == temp.lookAtCard(j).getRank().value(1)) {
                                //send crib actions coresponding to the i and j values
                                cardsToNotCrib[0] = i;
                                cardsToNotCrib[1] = j;
                            }

                        }

                    }
                    //Log.i("value of cards crib:", ""+ cardsToCrib[0]);
                    if(cardsToNotCrib[0] != 0)
                    {
                        cribact1 = new CribAction1(this);
                        game.sendAction(cribact1);
                    }
                    else if(cardsToNotCrib[0] != 1)
                    {
                        cribact2 = new CribAction2(this);
                        game.sendAction(cribact2);
                    }
                    else if(cardsToNotCrib[0] != 2)
                    {
                        cribact3 = new CribAction3(this);
                        game.sendAction(cribact3);
                    }
                    else if(cardsToNotCrib[0] != 3)
                    {
                        cribact4 = new CribAction4(this);
                        game.sendAction(cribact4);
                    }
                    else if(cardsToNotCrib[0] != 4)
                    {
                        cribact5 = new CribAction5(this);
                        game.sendAction(cribact5);
                    }
                    else if(cardsToNotCrib[0] != 5)
                    {
                        cribact6 = new CribAction6(this);
                        game.sendAction(cribact6);
                    }
                    else
                    {
                        cribact1 = new CribAction1(this);
                        game.sendAction(cribact1);
                    }
                }
                if(temp.size() == 5)//have already played one card
                {
                    //Log.i("COMP cribed random card","");
                    if(cardsToNotCrib[1] != 1 && cardsToNotCrib[0] != 1)
                    {
                        cribact1 = new CribAction1(this);
                        game.sendAction(cribact1);
                    }
                    else if(cardsToNotCrib[1] != 2 && cardsToNotCrib[0] != 2)
                    {
                        cribact2 = new CribAction2(this);
                        game.sendAction(cribact2);
                    }
                    else if(cardsToNotCrib[1] != 3 && cardsToNotCrib[0] != 3)
                    {
                        cribact3 = new CribAction3(this);
                        game.sendAction(cribact3);
                    }
                    else if(cardsToNotCrib[1] != 4 && cardsToNotCrib[0] != 4)
                    {
                        cribact4 = new CribAction4(this);
                        game.sendAction(cribact4);
                    }
                    else if(cardsToNotCrib[1] != 5 && cardsToNotCrib[0] != 1)
                    {
                        cribact5 = new CribAction5(this);
                        game.sendAction(cribact5);
                    }
                }
            }
            else
            {
                cribact1 = new CribAction1(this);
                game.sendAction(cribact1);
            }
            //end of not dealer algorithm
            ////////////////////////////////////////////////////////////////////////////////////////
        }

        if(computerPlayerState.getStage() == 3 && computerPlayerState.getWhoseTurn() == playerNum){
            if(computerPlayerState.handsOfBothPlayers[playerNum].size() > 0) {
                if (computerPlayerState.handsOfBothPlayers[playerNum].size() != 0) {
                    for (int i = 0; i < computerPlayerState.handsOfBothPlayers[playerNum].size(); i++) {
                        if ((computerPlayerState.count31 + computerPlayerState.handsOfBothPlayers[playerNum].lookAtCard(i).getRank().cribValue(1)) <= 31) {
                            switch(i){
                                case 0:
                                    playAct1 = new CribPlayAction1(this);
                                    sleep(10);
                                    game.sendAction(playAct1);
                                    break;

                                case 1:
                                    playAct2 = new CribPlayAction2(this);
                                    sleep(10);
                                    game.sendAction(playAct2);
                                    break;
                                case 2:
                                    playAct3 = new CribPlayAction3(this);
                                    sleep(10);
                                    game.sendAction(playAct3);
                                    break;
                                case 3:
                                    playAct4 = new CribPlayAction4(this);
                                    sleep(10);
                                    game.sendAction(playAct4);
                                    break;
                                default:
                                    break;


                            }


                        }
                    }

                    cribGoAction = new CribGo(this);
                    //sleep(1000);
                    game.sendAction(cribGoAction);




                }

            }
        }
        //computerPlayerState.handsOfBothPlayers[playerNum].size() == 0
        if(computerPlayerState.getDealer() == playerNum && computerPlayerState.getStage() == 4)
        {
            cribDealAction = new CribDeal(this);
            Log.i("Comp Action:","I tried to send a deal action");
            game.sendAction(cribDealAction);
            Log.i("Comp Action:","I SENT a deal action");
        }
    }//end receive info method
}//end advanced Computer player
