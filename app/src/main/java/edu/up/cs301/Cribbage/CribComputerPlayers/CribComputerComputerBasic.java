package edu.up.cs301.Cribbage.CribComputerPlayers;

import android.util.Log;

import edu.up.cs301.Cribbage.CribComputerPlayer;
import edu.up.cs301.Cribbage.CribPlayActions.CribActions.CribAction1;
import edu.up.cs301.Cribbage.CribPlayActions.CribDeal;
import edu.up.cs301.Cribbage.CribPlayActions.CribGo;
import edu.up.cs301.Cribbage.CribPlayActions.PlayActions.CribPlayAction1;
import edu.up.cs301.Cribbage.CribPlayActions.PlayActions.CribPlayAction2;
import edu.up.cs301.Cribbage.CribPlayActions.PlayActions.CribPlayAction3;
import edu.up.cs301.Cribbage.CribPlayActions.PlayActions.CribPlayAction4;
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
    CribPlayAction2 playAct2;
    CribPlayAction3 playAct3;
    CribPlayAction4 playAct4;
    CribGo cribGoAction;
    CribDeal cribDealAction;
    int oppNum;

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
        Log.i("COMP play1hand", computerPlayerState.handsOfBothPlayers[playerNum].toString());
        Log.i("COMP CribDeck", computerPlayerState.cribDeck.toString());

        if(computerPlayerState.getStage()==2 && computerPlayerState.getWhoseTurn()==playerNum &&
                computerPlayerState.handsOfBothPlayers[playerNum].size()>4){

            if(computerPlayerState.canCrib(playerNum))
                cribact1 = new CribAction1(this);
            sleep(1000);
            game.sendAction(cribact1);

        }

        if(computerPlayerState.getStage() == 3 && computerPlayerState.getWhoseTurn() == playerNum){
            if(computerPlayerState.handsOfBothPlayers[playerNum].size() > 0) {

                //playAct1 = new CribPlayAction1(this);
                //sleep(1000);
                //game.sendAction(playAct1);
                //Log.i("COMP hand", computerPlayerState.handsOfBothPlayers[playerNum].toString());
                //Log.i("COMP playdeck", computerPlayerState.playDeck.toString());
                //Log.i("COMP cards played to pd", computerPlayerState.eachPlayerCardsPlayed[playerNum].toString());

                if (computerPlayerState.handsOfBothPlayers[playerNum].size() != 0) {
                    for (int i = 0; i < computerPlayerState.handsOfBothPlayers[playerNum].size(); i++) {
                        if ((computerPlayerState.count31 + computerPlayerState.handsOfBothPlayers[playerNum].lookAtCard(i).getRank().cribValue(1)) <= 31) {
                            //playAct1 = new CribPlayAction1(this);
                            //sleep(1000);
                            //game.sendAction(playAct1);
                            //Log.i("COMP hand", computerPlayerState.handsOfBothPlayers[playerNum].toString());
                            //Log.i("COMP playdeck", computerPlayerState.playDeck.toString());
                            //Log.i("COMP cards played to pd", computerPlayerState.eachPlayerCardsPlayed[playerNum].toString());


                            switch(i){
                                case 0:
                                playAct1 = new CribPlayAction1(this);
                                sleep(1000);
                                game.sendAction(playAct1);
                                    break;

                                case 1:
                                    playAct2 = new CribPlayAction2(this);
                                    sleep(1000);
                                    game.sendAction(playAct2);
                                    break;
                                case 2:
                                    playAct3 = new CribPlayAction3(this);
                                    sleep(1000);
                                    game.sendAction(playAct3);
                                    break;
                                case 3:
                                    playAct4 = new CribPlayAction4(this);
                                    sleep(1000);
                                    game.sendAction(playAct4);
                                    break;
                                default:
                                    break;


                            }


                        }
                    }

                    cribGoAction = new CribGo(this);
                    sleep(1000);
                    game.sendAction(cribGoAction);



//so I can commit
                }

            }
        }
        //computerPlayerState.handsOfBothPlayers[playerNum].size() == 0
        if(computerPlayerState.getDealer() == playerNum && computerPlayerState.getStage() == 4)
        {
            cribDealAction = new CribDeal(this);
            Log.i("Comp Action:","I tried to send a deal action");
            Log.i("before COmP Deal",""+computerPlayerState.getDealer());
            game.sendAction(cribDealAction);
            Log.i("after COmP Deal",""+computerPlayerState.getDealer());
            Log.i("Comp Action:","I SENT a deal action");
        }

    }


}
