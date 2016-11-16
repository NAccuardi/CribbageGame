package edu.up.cs301.Cribbage;

import android.app.Activity;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

import edu.up.cs301.animation.AnimationSurface;
import edu.up.cs301.animation.Animator;
import edu.up.cs301.card.Card;
import edu.up.cs301.game.GameHumanPlayer;
import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.R;
import edu.up.cs301.game.infoMsg.GameInfo;

/**
 * Created by Nick Accuardi on 10/20/2016.
 */

public class CribHumanPlayer extends GameHumanPlayer implements Animator {


    /**
     * default constructor
     *
     * @param name the name of the player
     */

    //sizes and locations of card decks and cards, expressed as percentages
	// of the screen height and width
	private final static float CARD_HEIGHT_PERCENT = 40; // height of a card
	private final static float CARD_WIDTH_PERCENT = 17; // width of a card
	private final static float LEFT_BORDER_PERCENT = 4; // width of left border
	private final static float RIGHT_BORDER_PERCENT = 20; // width of right border
	private final static float VERTICAL_BORDER_PERCENT = 4; // width of top/bottom borders

	// our game state
	protected CribState state;

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

    public void receiveInfo(GameInfo info) {

    }

    public void setAsGui(GameMainActivity activity) {
    //remember the activity
		myActivity = activity;

		// Load the layout resource for the new configuration
		activity.setContentView(R.layout.crib_human_player);

		// link the animator (this object) to the animation surface
		surface = (AnimationSurface) myActivity
				.findViewById(R.id.animation_surface);
		surface.setAnimator(this);

		// read in the card images
		Card.initImages(activity);

		// if the state is not null, simulate having just received the state so that
		// any state-related processing is done
        if (state != null) {
            receiveInfo(state);

        }
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
}
