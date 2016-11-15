package edu.up.cs301.Cribbage;

import edu.up.cs301.card.Card;
import edu.up.cs301.card.Rank;
import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.infoMsg.GameInfo;

/**
 * Created by Nick Accuardi on 10/20/2016.
 */

public class CribComputerPlayer extends GameComputerPlayer {

    //most recent state of the game
    private CribState savedState;

    //
    private int myPlayerId = savedState.getPlayerId();

    /**
     * constructor
     *
     * @param name the player's name (e.g., "John")
     */
    public CribComputerPlayer(String name) {
        super(name);
    }


    @Override
    protected void receiveInfo(GameInfo info) {

        // if we don't have a game-state, ignore
        if (!(info instanceof CribState)) {
            return;
        }

        // update our state variable
        savedState = (CribState) info;




    }


}
