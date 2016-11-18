package edu.up.cs301.Cribbage.CribPlayActions;

/**
 * Created by justinplummer on 11/18/16.
 */
public class CribPutInCrib extends CribPlayAction{

    public CribPutInCrib (GamePlayer player)
    {
        super(player);
    }

    public boolean isPutInCrib() {
        return true;
    }
}
