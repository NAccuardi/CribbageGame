package edu.up.cs301.Cribbage.CribPlayActions;

import edu.up.cs301.Cribbage.CribMoveAction;
import edu.up.cs301.game.GamePlayer;

/**
 * Created by justinplummer on 11/18/16.
 */
public class CribPutInCrib extends CribMoveAction{

    public CribPutInCrib (GamePlayer player)
    {
        super(player);
    }

    public boolean isPutInCrib() {
        return true;
    }
}
