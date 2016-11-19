package edu.up.cs301.Cribbage;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import edu.up.cs301.animation.AnimationSurface;
import edu.up.cs301.animation.Animator;
import edu.up.cs301.card.Card;
import edu.up.cs301.game.GameHumanPlayer;
import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.R;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.infoMsg.IllegalMoveInfo;
import edu.up.cs301.game.infoMsg.NotYourTurnInfo;

/**
 * Created by Nick Accuardi on 10/20/2016.
 */

public class CribHumanPlayer extends GameHumanPlayer implements Animator,CribPlayer,View.OnClickListener {


    /*
        This is our humanState. This will only be modified in here. Nick
     */
    protected CribState humanState;


    //NEW CRIBBAGE VARIABLES
    //cardPosition1 variables
    Button cribButtonPos1;
    Button playButtonPos1;
    ImageView handCardPos1;

    //cardPosition2 variables
    Button cribButtonPos2;
    Button playButtonPos2;
    ImageView handCardPos2;

    //cardPosition3 variables
    Button cribButtonPos3;
    Button playButtonPos3;
    ImageView handCardPos3;

    //cardPosition4 variables
    Button cribButtonPos4;
    Button playButtonPos4;
    ImageView handCardPos4;

    //cardPosition5 variables
    Button cribButtonPos5;
    Button playButtonPos5;
    ImageView handCardPos5;

    //cardPosition6 variables
    Button cribButtonPos6;
    Button playButtonPos6;
    ImageView handCardPos6;

    //SCORE textViews
    TextView humanScore;
    TextView oppScore;

    //human crib card positions
    ImageView cribHumanPos1;
    ImageView cribHumanPos2;
    ImageView cribHumanPos3;
    ImageView cirbHumanPos4;

    //opponent crib card positions
    ImageView oppCardPos1;
    ImageView oppCardPos2;
    ImageView oppCardPos3;
    ImageView oppCardPos4;

    //GO BUTTON
    Button goButton;

    //mainDeck
    ImageView mainDeck;

    Button dealButton;

    SurfaceView Fuuck;




    //sizes and locations of card decks and cards, expressed as percentages
	// of the screen height and width
	private final static float CARD_HEIGHT_PERCENT = 40; // height of a card
	private final static float CARD_WIDTH_PERCENT = 17; // width of a card
	private final static float LEFT_BORDER_PERCENT = 4; // width of left border
	private final static float RIGHT_BORDER_PERCENT = 20; // width of right border
	private final static float VERTICAL_BORDER_PERCENT = 4; // width of top/bottom borders

	// our activity
	private Activity myActivity;

	// the amination surface
	private AnimationSurface surface;

	// the background color
	private int backgroundColor;




    public CribHumanPlayer(String name) {
        super(name);
    }

    public View getTopView() {
        return null;
    }


    /**
     * This is where the HumanPlayer will recieve the state that they will play with.
     *
     * @param info
     */
    public void receiveInfo(GameInfo info) {

        Log.i("CribHumanPlayer", "receiving updated state ("+info.getClass()+")");
	if (info instanceof IllegalMoveInfo || info instanceof NotYourTurnInfo) {
		        // if we had an out-of-turn or illegal move, flash the screen
		surface.flash(Color.GREEN, 50);
	}
		else if (!(info instanceof CribState)) {
			// otherwise, if it's not a game-state message, ignore
			return;
		}
		else {
			// it's a game-state object: update the state. Since we have an animation
			// going, there is no need to explicitly display anything. That will happen
			// at the next animation-tick, which should occur within 1/20 of a second
			this.humanState = (CribState) info;
			Log.i("human player", "receiving");
		}



    }

    public void setAsGui(GameMainActivity activity) {
    //remember the activity
		myActivity = activity;

		// Load the layout resource for the new configuration
		activity.setContentView(R.layout.crib_human_player);


		// link the animator (this object) to the animation surface
		surface = (AnimationSurface) myActivity
				.findViewById(R.id.view);
		surface.setAnimator(this);

		// read in the card images
		Card.initImages(activity);

		// if the state is not null, simulate having just received the state so that
		// any state-related processing is done
        if (humanState != null) {
            receiveInfo(humanState);

        }

        //creation of CRIBBAGE buttons and imageviews
        //cribButtons


		cribButtonPos1 = (Button)activity.findViewById(R.id.cribButtonPos1);
		cribButtonPos1.setOnClickListener(this);
        //so i can push
		cribButtonPos2 = (Button)activity.findViewById(R.id.cribButtonPos2);
		cribButtonPos2.setOnClickListener(this);

		cribButtonPos3 = (Button)activity.findViewById(R.id.cribButtonPos3);
		cribButtonPos3.setOnClickListener(this);

		cribButtonPos4 = (Button)activity.findViewById(R.id.cribButtonPos4);
		cribButtonPos4.setOnClickListener(this);

		cribButtonPos5 = (Button)activity.findViewById(R.id.cribButtonPos5);
		cribButtonPos5.setOnClickListener(this);

		cribButtonPos6 = (Button)activity.findViewById(R.id.cribButtonPos6);
		cribButtonPos6.setOnClickListener(this);

		//playButtons
		playButtonPos1 = (Button)activity.findViewById(R.id.playButtonPos1);
		playButtonPos1.setOnClickListener(this);

		playButtonPos2 = (Button)activity.findViewById(R.id.playButtonPos2);
		playButtonPos2.setOnClickListener(this);

		playButtonPos3 = (Button)activity.findViewById(R.id.playButtonPos3);
		playButtonPos3.setOnClickListener(this);

		playButtonPos4 = (Button)activity.findViewById(R.id.playButtonPos4);
		playButtonPos4.setOnClickListener(this);

		playButtonPos5 = (Button)activity.findViewById(R.id.playButtonPos5);
		playButtonPos5.setOnClickListener(this);

		playButtonPos6 = (Button)activity.findViewById(R.id.playButtonPos6);
		playButtonPos6.setOnClickListener(this);

		//goButton
		this.goButton = (Button)activity.findViewById(R.id.goButton);
        goButton.setOnClickListener(this);

        this.dealButton = (Button)activity.findViewById(R.id.dealButton);
        dealButton.setOnClickListener(this);

    }







//    @Override
//    public void receiveInfo(GameInfo info) {
//
//    }

    //@Override
   // public View getTopView() {
      //  return null;
    //}

    public int backgroundColor() {
        return 0;
    }



    public boolean doQuit() {
        return false;
    }

    public void onTouch(MotionEvent event) {

    }

    public void tick(Canvas canvas) {

    }

    public boolean doPause() {
        return false;
    }

    public int interval() {
        return 0;
    }

    public void onClick(View button) {

        //****************** Crib Buttons go here******************************************************
        if(button == cribButtonPos1){


        }else if(button == cribButtonPos2){


        }else if(button == cribButtonPos3){


        }else if(button == cribButtonPos4){


        }else if(button == cribButtonPos5){


        }else if(button == cribButtonPos6){


        }else //***********************************End Crib Buttons***************************



        //********************************Start PLay buttons**********************************

         if(button == playButtonPos1){


        }else if(button == playButtonPos2){


        }else if(button == playButtonPos3){


        }else if(button == playButtonPos4){


        }else if(button == playButtonPos5){


        }else if(button == playButtonPos6){


        }else//****************************End Play Buttons

        if(button == goButton){


        }else if(button == dealButton){


        }

    }//end of onCLick method
}
