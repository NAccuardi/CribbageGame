package edu.up.cs301.Cribbage.CribComputerPlayers;

import edu.up.cs301.Cribbage.CribComputerPlayer;
import edu.up.cs301.Cribbage.CribState;
import edu.up.cs301.game.infoMsg.GameInfo;

/**
 * Created by Robot Laptop on 11/16/2016.
 */

public class CribComputerComputerAdvanced extends CribComputerPlayer {


    //most recent state of the game
    private CribState computerPlayerState;

    public CribComputerComputerAdvanced(String name) {
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
