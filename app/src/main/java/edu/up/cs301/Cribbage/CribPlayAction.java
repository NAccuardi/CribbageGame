package edu.up.cs301.Cribbage;

/**
 * Created by Robot Laptop on 11/16/2016.
 */

public abstract class CribMoveAction extends GameAction{

    /**
     * Constructor for CribMoveAction
     *
     * @param player the player making the move
     */
    public CribPlayAction(GamePlayer player)
    {
        // invoke superclass constructor to set source
        super(player);
    }

    /**
     * @return
     * 		whether the move was a deal
     */
    public boolean isDeal() {
    	return false;
    }

    /**
     * @return
     * 		whether the move was a put a card in crib
     */
    public boolean isPutInCrib() {
        return false;
    }

    /**
     * @return
     * 		whether the move was a put a card in play
     */
    public boolean isPutInPlay() {
        return false;
    }

    /**
     * @return
     * 		whether the move was a Go action
     */
    public boolean isGo() {
        return false;
    }
}
