package edu.up.cs301.Cribbage;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;

/**
 * Created by Robot Laptop on 11/14/2016.
 */

public class CribLocalGame extends LocalGame {
    protected void sendUpdatedStateTo(GamePlayer p) {

    }

    protected boolean canMove(int playerIdx) {
        return false;
    }

    protected String checkIfGameOver() {
        return null;
    }

    protected boolean makeMove(GameAction action) {
        return false;
    }
}
