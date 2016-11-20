package edu.up.cs301.Cribbage.CribPlayActions.PlayActions;

import edu.up.cs301.Cribbage.CribMoveAction;
import edu.up.cs301.game.GamePlayer;

/**
 * Created by Nick on 11/20/2016.
 */

public class CribPlayAction1 extends CribMoveAction {
    public CribPlayAction1(GamePlayer player)
    {
        super(player);
    }

    public boolean isPlayAction1(){return true;}
}
