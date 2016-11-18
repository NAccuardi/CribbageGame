package edu.up.cs301.Cribbage.CribPlayActions;

/**
 * Created by justinplummer on 11/18/16.
 */
public class CribPutInPlay extends CribPlayAction {

    public CribPutInPlay (GamePlayer player)
    {
        super(player);
    }

    public boolean isPutInPlay() {
        return true;
    }
}
