package edu.up.cs301.Cribbage.CribPlayActions;

import edu.up.cs301.Cribbage.CribMoveAction;

/**
 * Created by Nick Accuardi on 10/20/2016.
 */

public class CribGo extends CribMoveAction {

    public CribGo (GamePlayer player)
    {
        super(player);
    }

    public boolean isGo() {
        return true;
    }
}
