package edu.up.cs301.Cribbage.CribPlayActions.PlayActions;

import edu.up.cs301.Cribbage.CribMoveAction;
import edu.up.cs301.game.GamePlayer;

/**
 * Created by Nick on 11/20/2016.
 */

public class CribPlayAction5 extends CribMoveAction {
    public CribPlayAction5(GamePlayer player)
    {
        super(player);
    }
    public boolean isPlayAction5(){return true;}
}
