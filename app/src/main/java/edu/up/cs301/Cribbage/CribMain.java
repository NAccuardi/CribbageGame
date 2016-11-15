package edu.up.cs301.Cribbage;

import android.graphics.Color;

import java.util.ArrayList;

import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.config.GameConfig;
import edu.up.cs301.game.config.GamePlayerType;
//import edu.up.cs301.slapjack.SJComputerPlayer;
//import edu.up.cs301.slapjack.SJHumanPlayer;


/**
 * Created Nick Accuardi on 10/20/2016.
 * This is our game main
 */

public class CribMain extends GameMainActivity {



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

        return new CribLocalGame();
    }//End createLocalGame()

}//End Cribage Main


