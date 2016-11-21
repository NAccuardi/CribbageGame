package edu.up.cs301.Cribbage;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;

/**
 * Created by Robot Laptop on 11/16/2016.
 */

public abstract class CribMoveAction extends GameAction {

    /**
     * Constructor for CribMoveAction
     *
     * @param player the player making the move
     */
    public CribMoveAction(GamePlayer player)
    {
        // invoke superclass constructor to set source
        super(player);
    }

    /**
     * @return
     * 		whether the move was a deal
     */
   // public boolean isDeal() {
   // 	return false;
   // }

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

    public boolean isCribAction1(){return false;}
    public boolean isCribAction2(){return false;}
    public boolean isCribAction3(){return false;}
    public boolean isCribAction4(){return false;}
    public boolean isCribAction5(){return false;}
    public boolean isCribAction6(){return false;}


    public boolean isPlayAction1(){return false;}
    public boolean isPlayAction2(){return false;}
    public boolean isPlayAction3(){return false;}
    public boolean isPlayAction4(){return false;}
    public boolean isPlayAction5(){return false;}
    public boolean isPlayAction6(){return false;}


}
