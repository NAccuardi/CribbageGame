package edu.up.cs301.Cribbage.CribPlayActions.CribActions;

import edu.up.cs301.Cribbage.CribMoveAction;
import edu.up.cs301.game.GamePlayer;

/**
 * Created by Nick on 11/20/2016.
 */

public class CribAction5 extends CribMoveAction {
    public CribAction5(GamePlayer player)
    {
        super(player);
    }
    public boolean isCribAction5(){return true;}
}
