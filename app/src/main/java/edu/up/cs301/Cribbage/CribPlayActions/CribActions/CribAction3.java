package edu.up.cs301.Cribbage.CribPlayActions.CribActions;

import edu.up.cs301.Cribbage.CribMoveAction;
import edu.up.cs301.game.GamePlayer;

/**
 * Created by Nick on 11/20/2016.
 */

public class CribAction3 extends CribMoveAction {
    public CribAction3(GamePlayer player)
    {
        super(player);
    }
    public boolean isCribAction3(){return true;}
}
