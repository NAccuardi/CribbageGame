package edu.up.cs301.Cribbage.CribPlayActions;

import edu.up.cs301.Cribbage.CribMoveAction;
import edu.up.cs301.game.GamePlayer;

/**
 * Created by Nick Accuardi on 10/20/2016.
 */

public class CribGo extends CribMoveAction {
    //Contructor
    public CribGo (GamePlayer player)
    {
        super(player);
    }

    public boolean isGo() {
       return true;
    }
}
