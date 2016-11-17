package edu.up.cs301.Cribbage.CribComputerPlayers;

import edu.up.cs301.Cribbage.CribComputerPlayer;
import edu.up.cs301.Cribbage.CribState;
import edu.up.cs301.game.infoMsg.GameInfo;

/**
 * Created by Nick Accuardi on 10/20/2016.
 */

public class CribComputerComputerBasic extends CribComputerPlayer {

    //most recent state of the game
    private CribState computerPlayerState;

    //
    private int myPlayerId = computerPlayerState.getPlayerId();

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




    }


}
