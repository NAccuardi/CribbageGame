package edu.up.cs301.Cribbage;

import android.graphics.Color;

import java.util.ArrayList;

import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.config.GameConfig;
import edu.up.cs301.game.config.GamePlayerType;
import edu.up.cs301.slapjack.SJComputerPlayer;
import edu.up.cs301.slapjack.SJHumanPlayer;


/**
 * Created Nick Accuardi on 10/20/2016.
 * This is our game state
 */

public class CribMain extends GameMainActivity {

    private int score0;//player 0's score
    private int score1;//player 1's score
    private int dealer;//id of the player who is the dealer
    private int whoseTurn;//id of the player who is allowed to play
    private boolean winner;//becomes true when a player wins
    private boolean go;//becomes true when a player must go
    private int playerId;//determines who is playing
    private Deck mainDeck = new Deck();//holds 52 cards
    private Deck player0Hand = new Deck();//is player 0's hand
    private Deck player0ToPlay = new Deck();//tracks the cards that player 0puts into play
    private Deck player1Hand = new Deck();//is player 1's hand
    private Deck player1ToPlay = new Deck();//tracks the cards that player 1 puts into play
    private Deck cribDeck = new Deck();//contains the cards that players have put in crib
    private Deck playDeck = new Deck();//contains the cards that players have player
    private Deck cutDeck = new Deck();//contains the card that was cut from deck



    public static final int PORT_NUMBER = 4752;

    @Override
    public GameConfig createDefaultConfig()
    {

        // Define the allowed player types
        ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();

        playerTypes.add(new GamePlayerType("human player One") {
            public GamePlayer createPlayer(String name) {
                return new CribHumanPlayer(name);
            }});
        playerTypes.add(new GamePlayerType("human player Two") {
            public GamePlayer createPlayer(String name) {
                return new CribHumanPlayer(name);
            }
        });
        playerTypes.add(new GamePlayerType("computer player (Dumb AI)") {
            public GamePlayer createPlayer(String name) {
                return new CribComputerPlayer(name);
            }
        });
        playerTypes.add(new GamePlayerType("computer player (Smart AI)") {
            public GamePlayer createPlayer(String name) {
                return new CribComputerPlayer(name);
            }
        });

        // Create a game configuration class for Cribbage

        //***Need to go over this later***
        GameConfig defaultConfig = new GameConfig(playerTypes, 2, 2, "Cribbage", PORT_NUMBER);

        // Add the default players
        defaultConfig.addPlayer("Human", 0);
        defaultConfig.addPlayer("Computer", 2);

        // Set the initial information for the remote player
        defaultConfig.setRemoteData("Guest", "", 1);

        return defaultConfig;
    }//end createDefualtConfig()


    @Override
    public LocalGame createLocalGame() {

        return null;
    }//End createLocalGame()

    public void CribageMain()
    {
        score0 = 0;
        score1 = 0;
        dealer = getDealer();
        whoseTurn = getWhoseTurn();
        winner = false;
        go = false;
        playerId = getPlayerId();
        mainDeck.add52();
        player0Hand = null;
        player1Hand = null;
        cribDeck = null;
        playDeck = null;
        cutDeck = null;
        player0ToPlay = null;
        player1Hand = null;
    }
    //SETTERS AND GETTERS
    //sets a players score
    public void setScore(int player, int score)
    {
        if(player == 0)
        {
            score0 = score0 + score;
        }
        else if(player == 1)
        {
            score1 = score1 + score;
        }
        else
        {
            return;
        }
    }
    //returns a players score
    public int getScore(int player)
    {
        if(player == 0)
        {
            return score0;
        }
        else if(player == 1)
        {
            return score1;
        }
        else
        {
            return 0;
        }
    }
    //sets the players id
    public void setPlayerId()
    {

    }
    //gets the player id
    public int getPlayerId()
    {
        return 0;
    }
    //sets the hand for a player
    public void setHand(int player)
    {

    }
    //gets the hand of a player
    public Deck getHand(int player)
    {
        if(player == 0)
        {
            return player0Hand;
        }
        else if(player == 1)
        {
            return player1Hand;
        }
        else
        {
            return null;
        }
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
        int handSize = 6;
        for(int i = 0; i<handSize; i++)
        {
            mainDeck.moveTopCardTo(player0Hand);
            mainDeck.moveTopCardTo(player1Hand);
        }
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

