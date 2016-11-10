package edu.up.cs301.Criggabage;

import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.config.GameConfig;

/**
 * Created Nick Accuardi on 10/20/2016.
 * This is our game state
 */

public class CribageMain extends GameMainActivity {

    @Override
    public GameConfig createDefaultConfig() {


return null;
    }//end createDefualtConfig()


    @Override
    public LocalGame createLocalGame() {
        return null;
    }//End createLocalGame()

    //SETTERS AND GETTERS
    //sets a players score
    public void setScore(int player, int score)
    {

    }
    //returns a players score
    public int getScore(int player)
    {
        return 0;
    }
    //sets the hand for a player
    public void setHand(int player, Deck[] hand)
    {

    }
    //gets the hand of a player
    public Deck[] getHand(int player)
    {
        Deck[] hand = new Deck[5];
        return hand;
    }
    //set which players turn it is
    public void setWhoseTurn()
    {

    }
    //returns the int of whose turn it is
    public int getWhoseTurn()
    {
        return 0;
    }
    //sets the dealer
    public void setDealer()
    {

    }
    //gets who the dealer is
    public int getDealer()
    {
        return 0;
    }
    //set the stage of the game
    public void setStage()
    {

    }
    //returns the int value of the stage of the game
    //0=cut,1=deal,2=crib,3=play,4=score
    public int getStage()
    {
        return 0;
    }
    //END SETTERS AND GETTERS
    //returns true if a score is over 121
    public boolean isScore121(int player)
    {
        return false;
    }
    //returns true if a Go action is allowed
    public boolean isGo(int player)
    {
        return false;
    }
    //places six cards in each players hands
    public void deal()
    {

    }
    //cuts the deck, returns a single card
    public Deck cut()
    {
        Deck card = new Deck();
        return card;
    }
    //runs the scoring algortihm
    public int score()
    {
        return 0;
    }
}//End Cribage Main


