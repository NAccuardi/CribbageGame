package edu.up.cs301.Cribbage;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import edu.up.cs301.Cribbage.CribPlayActions.CribActions.CribAction1;
import edu.up.cs301.Cribbage.CribPlayActions.CribActions.CribAction2;
import edu.up.cs301.Cribbage.CribPlayActions.CribActions.CribAction3;
import edu.up.cs301.Cribbage.CribPlayActions.CribActions.CribAction4;
import edu.up.cs301.Cribbage.CribPlayActions.CribActions.CribAction5;
import edu.up.cs301.Cribbage.CribPlayActions.CribActions.CribAction6;
import edu.up.cs301.Cribbage.CribPlayActions.CribDeal;
import edu.up.cs301.Cribbage.CribPlayActions.CribGo;
import edu.up.cs301.Cribbage.CribPlayActions.PlayActions.CribPlayAction1;
import edu.up.cs301.Cribbage.CribPlayActions.PlayActions.CribPlayAction2;
import edu.up.cs301.Cribbage.CribPlayActions.PlayActions.CribPlayAction3;
import edu.up.cs301.Cribbage.CribPlayActions.PlayActions.CribPlayAction4;
import edu.up.cs301.Cribbage.CribPlayActions.PlayActions.CribPlayAction5;
import edu.up.cs301.Cribbage.CribPlayActions.PlayActions.CribPlayAction6;
import edu.up.cs301.animation.AnimationSurface;
import edu.up.cs301.animation.Animator;
import edu.up.cs301.card.Card;
import edu.up.cs301.card.Rank;
import edu.up.cs301.card.Suit;
import edu.up.cs301.game.GameHumanPlayer;
import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.R;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.infoMsg.IllegalMoveInfo;
import edu.up.cs301.game.infoMsg.NotYourTurnInfo;

import static edu.up.cs301.game.R.id.cribButtonPos1;

/**
 * Created by Nick Accuardi on 10/20/2016.
 */

public class CribHumanPlayer extends GameHumanPlayer implements Animator,CribPlayer,View.OnClickListener {


    //sizes and locations of card decks and cards, expressed as percentages
    // of the screen height and width
    private final static float CARD_HEIGHT_PERCENT = 40; // height of a card
    private final static float CARD_WIDTH_PERCENT = 17; // width of a card
    private final static float LEFT_BORDER_PERCENT = 4; // width of left border
    private final static float RIGHT_BORDER_PERCENT = 20; // width of right border
    private final static float VERTICAL_BORDER_PERCENT = 4; // width of top/bottom borders
    /*
        Our states and actions go here so we Can send things around.
     */
    CribState humanState;
    CribDeal dealAction;
    CribGo goAction;
    CribAction1 CribState1;
    CribAction2 CribState2;
    CribAction3 CribState3;
    CribAction4 CribState4;
    CribAction5 CribState5;
    CribAction6 CribState6;
    CribPlayAction1 CribPlay1;
    CribPlayAction2 CribPlay2;
    CribPlayAction3 CribPlay3;
    CribPlayAction4 CribPlay4;
    CribPlayAction5 CribPlay5;
    CribPlayAction6 CribPlay6;
    //NEW CRIBBAGE VARIABLES
    //crib button array
    Button cribButtons[] = new Button[6];
    Button playButtons[] = new Button[6];
    ImageView humanHandImages[] = new ImageView[6];
    ImageView humanCribImages[] = new ImageView[4];
    ImageView oppPlayImages[] = new ImageView[4];
    //SCORE textViews
    TextView humanScore;
    TextView oppScore;
    //GO BUTTON
    Button goButton;
    //mainDeck
    ImageView mainDeck;
    Button dealButton;
    private int cribCounter =0;
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

        Log.i("The CribHumanPlayer", "receiving updated state ("+info.getClass()+")");
        if (info instanceof IllegalMoveInfo || info instanceof NotYourTurnInfo) {
            // if we had an out-of-turn or illegal move, flash the screen
            surface.flash(Color.GRAY, 50);
            Log.i("We had illegal move or", "It was not your turn to move.");
            return;
        }
        else if (!(info instanceof CribState)) {
            // otherwise, if it's not a game-state message, ignore
            return;
        }

        // it's a game-state object: update the state. Since we have an animation
        // going, there is no need to explicitly display anything. That will happen
        // at the next animation-tick, which should occur within 1/20 of a second
        this.humanState = (CribState) info;

        if(humanState.cribDeck.size() < 5) {
            for (int i = 0; i < humanState.cribDeck.size(); i++) {
                humanCribImages[i].setImageBitmap(humanState.getCribDeck().lookAtCard(i).getBitmap());
            }
        }


        for(int i =0; i<humanState.handsOfBothPlayers[0].size();i++){
            humanHandImages[i].setImageBitmap(humanState.getHand(playerNum).lookAtCard(i).getBitmap());


        }

        if(humanState.handsOfBothPlayers[0].size()!=6){
            int remainder = 6-humanState.handsOfBothPlayers[0].size();

            switch (remainder){

                case 1:
                    humanHandImages[5].setImageResource(R.drawable.card_blank);
                    break;
                case 2:
                    humanHandImages[4].setImageResource(R.drawable.card_blank);
                    break;
                case 3:
                    humanHandImages[3].setImageResource(R.drawable.card_blank);
                    break;
                case 4:
                    humanHandImages[2].setImageResource(R.drawable.card_blank);
                    break;
                case 5:
                    humanHandImages[1].setImageResource(R.drawable.card_blank);
                    break;
                case 6:
                    humanHandImages[0].setImageResource(R.drawable.card_blank);
                    break;
                default:
                    break;//end switch


            }

        }




        //enabling and dissabling
        if(humanState.getStage()==-1){
            Log.i("we are in stage -1", "brand new game");
        }else if(humanState.getStage()==0){
            Log.i("we are in stage 0", "cut");

        }else if(humanState.getStage()==1){
            Log.i("we are in stage 1", "deal");

            for(int i =0;i<6;i++){
                cribButtons[i].setEnabled(false);
                playButtons[i].setEnabled(false);
            }
            dealButton.setEnabled(true);
            goButton.setEnabled(false);

        }else if(humanState.getStage()== 2){
            Log.i("we are in stage 2", "crib");

            if(humanState.handsOfBothPlayers[0].size()>4) {
                Log.i("hand0size", ""+humanState.handsOfBothPlayers[0].size());
                for (int i = 0; i < 6; i++) {
                    cribButtons[i].setEnabled(false);
                    playButtons[i].setEnabled(false);
                    for (int j = 0; j < humanState.handsOfBothPlayers[0].size(); j++) {
                        cribButtons[j].setEnabled(true);
                        playButtons[j].setEnabled(false);
                    }
                }
            }else{
                for(int i=0;i<6;i++){
                    cribButtons[i].setEnabled(false);
                }
            }




            dealButton.setEnabled(false);
            goButton.setEnabled(false);
        }
        else if(humanState.getStage() == 3)
        {
            Log.i("we are in stage 3", "play");
            for(int i =0;i<6;i++){
                cribButtons[i].setEnabled(false);
                playButtons[i].setEnabled(true);
            }


            dealButton.setEnabled(false);
            goButton.setEnabled(true);//This will be dependent on the cards in the players hands.
        }
        else if(humanState.getStage()==4)
        {
            Log.i("we are in stage 4", "score");
        }
        else{
            Log.i("we are in a bad stage", "The lost stage");
            for(int i =0;i<6;i++){
                cribButtons[i].setEnabled(false);
                playButtons[i].setEnabled(false);
            }
            dealButton.setEnabled(false);
            goButton.setEnabled(false);
        }

        humanScore.setText(allPlayerNames[0]+": "+humanState.getScore(0));
        oppScore.setText(allPlayerNames[1]+": "+humanState.getScore(1));


        //to bold if dealer
        if(humanState.getDealer() ==  0)
        {
            humanScore.setTypeface(null, Typeface.BOLD);
            humanScore.setText(" 'Dealer'- "+ allPlayerNames[0]+": "+humanState.getScore(0));
        }
        else if(humanState.getDealer() == 1)
        {
            oppScore.setTypeface(null, Typeface.BOLD);
            oppScore.setText(" 'Dealer'- "+allPlayerNames[1]+": "+humanState.getScore(1));
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


        cribButtons[0] = (Button)activity.findViewById(cribButtonPos1);
        cribButtons[0].setOnClickListener(this);

        cribButtons[1] = (Button)activity.findViewById(R.id.cribButtonPos2);
        cribButtons[1].setOnClickListener(this);

        cribButtons[2] = (Button)activity.findViewById(R.id.cribButtonPos3);
        cribButtons[2].setOnClickListener(this);

        cribButtons[3] = (Button)activity.findViewById(R.id.cribButtonPos4);
        cribButtons[3].setOnClickListener(this);

        cribButtons[4] = (Button)activity.findViewById(R.id.cribButtonPos5);
        cribButtons[4].setOnClickListener(this);

        cribButtons[5] = (Button)activity.findViewById(R.id.cribButtonPos6);
        cribButtons[5].setOnClickListener(this);

        //playButtons
        playButtons[0] = (Button)activity.findViewById(R.id.playButtonPos1);
        playButtons[0].setOnClickListener(this);



        playButtons[1] = (Button)activity.findViewById(R.id.playButtonPos2);
        playButtons[1].setOnClickListener(this);

        playButtons[2] = (Button)activity.findViewById(R.id.playButtonPos3);
        playButtons[2].setOnClickListener(this);

        playButtons[3] = (Button)activity.findViewById(R.id.playButtonPos4);
        playButtons[3].setOnClickListener(this);

        playButtons[4] = (Button)activity.findViewById(R.id.playButtonPos5);
        playButtons[4].setOnClickListener(this);

        playButtons[5] = (Button)activity.findViewById(R.id.playButtonPos6);
        playButtons[5].setOnClickListener(this);

        //goButton
        this.goButton = (Button)activity.findViewById(R.id.goButton);
        goButton.setOnClickListener(this);

        //dealButton
        this.dealButton = (Button)activity.findViewById(R.id.dealButton);
        dealButton.setOnClickListener(this);

        //hand card Image view creations
        this.humanHandImages[0] = (ImageView)activity.findViewById(R.id.handCardPos1);
        this.humanHandImages[1] = (ImageView)activity.findViewById(R.id.handCardPos2);
        this.humanHandImages[2] = (ImageView)activity.findViewById(R.id.handCardPos3);
        this.humanHandImages[3] = (ImageView)activity.findViewById(R.id.handCardPos4);
        this.humanHandImages[4] = (ImageView)activity.findViewById(R.id.handCardPos5);
        this.humanHandImages[5] = (ImageView)activity.findViewById(R.id.handCardPos6);


        //human crib Image view creations
        this.humanCribImages[0] = (ImageView)activity.findViewById(R.id.cribHumanPos1);
        this.humanCribImages[1] = (ImageView)activity.findViewById(R.id.cribHumanPos2);
        this.humanCribImages[2] = (ImageView)activity.findViewById(R.id.cribHumanPos3);
        this.humanCribImages[3] = (ImageView)activity.findViewById(R.id.cribHumanPos4);

        //main deck Image view creations
        this.mainDeck = (ImageView)activity.findViewById(R.id.mainDeck);

        //opponent crib Image view creations
        this.oppPlayImages[0] = (ImageView)activity.findViewById(R.id.oppCardPos1);
        this.oppPlayImages[1] = (ImageView)activity.findViewById(R.id.oppCardPos2);
        this.oppPlayImages[2] = (ImageView)activity.findViewById(R.id.oppCardPos3);
        this.oppPlayImages[3] = (ImageView)activity.findViewById(R.id.oppCardPos4);

        this.humanScore = (TextView)activity.findViewById(R.id.humanScore);
        this.oppScore = (TextView)activity.findViewById(R.id.oppScore);

    }

    public int backgroundColor() {

        return (Color.LTGRAY);
        //(162,228,232)
    }



    public boolean doQuit() {
        return false;
    }

    public void onTouch(MotionEvent event) {

    }

    //area of
    public void tick(Canvas canvas) {

        Paint paint = new Paint();
        int x = 60;
        int y = 175;
        int rad = 15;

        paint.setColor(Color.BLACK);
        canvas.drawCircle(x,y,rad,paint);
        canvas.drawCircle(x,y+350,rad,paint);

        for(int i = 0; i <= 770; i= i+70)
        {
            canvas.drawCircle(x + i + 70, y, rad + 10, paint);
            canvas.drawCircle(x + i + 70, y + 350, rad + 10, paint);
        }
        for(int i = 0; i <= 770; i= i+70)
        {
            paint.setColor(Color.WHITE);
            canvas.drawCircle(x+i+70,y,rad+5,paint);
            canvas.drawCircle(x+i+70,y+350,rad+5,paint);

//
        }



        paint.setColor(Color.BLACK);
        paint.setTextSize(30);
        canvas.drawText("10",x+45,y+175,paint);
        canvas.drawText("20",x+115,y+175,paint);
        canvas.drawText("30",x+185,y+175,paint);
        canvas.drawText("40",x+255,y+175,paint);
        canvas.drawText("50",x+325,y+175,paint);
        canvas.drawText("60",x+395,y+175,paint);
        canvas.drawText("70",x+465,y+175,paint);
        canvas.drawText("80",x+535,y+175,paint);
        canvas.drawText("90",x+605,y+175,paint);
        canvas.drawText("100",x+665,y+175,paint);
        canvas.drawText("110",x+735,y+175,paint);
        canvas.drawText("120",x+805,y+175,paint);


        canvas.drawCircle(x+915,y,rad,paint);
        canvas.drawCircle(x+915,y+350,rad,paint);

        paint.setColor(Color.WHITE);
        paint.setTextSize(70);
        canvas.drawText("W",x+885,y+185,paint);



        paint.setColor(Color.rgb(162,228,232));

        //humanstate circle fill
        if (humanState.getScore(0) >= 10) {
            canvas.drawCircle(x + 70, y, rad + 5, paint);

        }
        if (humanState.getScore(0) >= 20) {
            canvas.drawCircle(x + 140, y, rad + 5, paint);

        }
        if (humanState.getScore(0) >= 30) {
            canvas.drawCircle(x + 210, y, rad + 5, paint);

        }
        if (humanState.getScore(0) >= 40) {
            canvas.drawCircle(x + 280, y, rad + 5, paint);

        }
        if (humanState.getScore(0) >= 50) {
            canvas.drawCircle(x + 350, y, rad + 5, paint);

        }
        if (humanState.getScore(0) >= 60) {
            canvas.drawCircle(x + 420, y, rad + 5, paint);

        }
        if (humanState.getScore(0) >= 70) {
            canvas.drawCircle(x + 490, y, rad + 5, paint);

        }
        if (humanState.getScore(0) >= 80) {
            canvas.drawCircle(x + 560, y, rad + 5, paint);

        }
        if (humanState.getScore(0) >= 90) {
            canvas.drawCircle(x + 630, y, rad + 5, paint);

        }
        if (humanState.getScore(0) >= 100) {
            canvas.drawCircle(x + 700, y, rad + 5, paint);

        }
        if (humanState.getScore(0) >= 110) {
            canvas.drawCircle(x + 770, y, rad + 5, paint);

        }
        if (humanState.getScore(0) >= 120) {
            canvas.drawCircle(x + 840, y, rad + 5, paint);

        }
        if(humanState.getScore(0) >= 121){
            canvas.drawText("W",x+885,y+185,paint);
        }


        paint.setColor(Color.rgb(37,32,94));

        //oppstate circle fill
        if (humanState.getScore(1) >= 10) {

            canvas.drawCircle(x + 70, y + 350, rad + 5, paint);
        }
        if (humanState.getScore(1) >= 20) {

            canvas.drawCircle(x + 140, y + 350, rad + 5, paint);
        }
        if (humanState.getScore(1) >= 30) {

            canvas.drawCircle(x + 210, y + 350, rad + 5, paint);
        }
        if (humanState.getScore(1) >= 40) {

            canvas.drawCircle(x + 280, y + 350, rad + 5, paint);
        }
        if (humanState.getScore(1) >= 50) {

            canvas.drawCircle(x + 350, y + 350, rad + 5, paint);
        }
        if (humanState.getScore(1) >= 60) {

            canvas.drawCircle(x + 420, y + 350, rad + 5, paint);
        }
        if (humanState.getScore(1) >= 70) {

            canvas.drawCircle(x + 490, y + 350, rad + 5, paint);
        }
        if (humanState.getScore(1) >= 80) {

            canvas.drawCircle(x + 560, y + 350, rad + 5, paint);
        }
        if (humanState.getScore(1) >= 90) {

            canvas.drawCircle(x + 630, y + 350, rad + 5, paint);
        }
        if (humanState.getScore(1) >= 100) {

            canvas.drawCircle(x + 700, y + 350, rad + 5, paint);
        }
        if (humanState.getScore(1) >= 110) {

            canvas.drawCircle(x + 770, y + 350, rad + 5, paint);
        }
        if (humanState.getScore(1) >= 120) {

            canvas.drawCircle(x + 840, y + 350, rad + 5, paint);
        }
        if(humanState.getScore(1) >= 121){

            canvas.drawText("W",x+885,y+185,paint);
        }







    }

    public boolean doPause() {
        return false;
    }

    public int interval() {
        return 30;
    }

    public void onClick(View button) {

        //****************** Crib Buttons go here******************************************************

        if (button == cribButtons[0]) {

            CribState1 = new CribAction1(this);
            Log.i("Button Pressed", "cribButtonPos1");
            game.sendAction(CribState1);



        } else if (button == cribButtons[1]) {
            CribState2 = new CribAction2(this);
            Log.i("Button Pressed", "cribButtonPos2");
            game.sendAction(CribState2);



        } else if (button == cribButtons[2]) {
            CribState3 = new CribAction3(this);
            Log.i("Button Pressed", "cribButtonPos3");
            game.sendAction(CribState3);



        } else if (button == cribButtons[3]) {
            CribState4 = new CribAction4(this);
            Log.i("Button Pressed", "cribButtonPos4");
            game.sendAction(CribState4);



        } else if (button == cribButtons[4]) {
            CribState5 = new CribAction5(this);
            Log.i("Button Pressed", "cribButtonPos5");
            game.sendAction(CribState5);



        } else if (button == cribButtons[5]) {
            CribState6 = new CribAction6(this);
            Log.i("Button Pressed", "cribButtonPos6");
            game.sendAction(CribState6);



        } else //***********************************End Crib Buttons***************************



            //********************************Start PLay buttons**********************************

            if(button == playButtons[0]){
                CribPlay1 = new CribPlayAction1(this);
                Log.i("Button Pressed", "playButtonPos1 ");
                game.sendAction(CribPlay1);


            }else if(button == playButtons[1]){
                CribPlay2 = new CribPlayAction2(this);
                Log.i("Button Pressed", "playButtonPos2 ");
                game.sendAction(CribPlay2);


            }else if(button == playButtons[2]){
                CribPlay3 = new CribPlayAction3(this);
                Log.i("Button Pressed", "playButtonPos3 ");
                game.sendAction(CribPlay3);



            }else if(button == playButtons[3]){
                CribPlay4 = new CribPlayAction4(this);
                Log.i("Button Pressed", "playButtonPos4 ");
                game.sendAction(CribPlay4);



            }else if(button == playButtons[4]){
                CribPlay5 = new CribPlayAction5(this);
                Log.i("Button Pressed", "playButtonPos5 ");
                game.sendAction(CribPlay5);


            }else if(button == playButtons[5]){
                CribPlay6 = new CribPlayAction6(this);
                Log.i("Button Pressed", "playButtonPos6 ");
                game.sendAction(CribPlay6);



            }else//****************************End Play Buttons

                if(button == goButton){
                    goAction = new CribGo(this);
                    Log.i("Button Pressed", "goButton");
                    game.sendAction(goAction);

                }else if(button == dealButton){
                    dealAction = new CribDeal(this);
                    Log.i("Button Pressed", "dealButton");
                    game.sendAction(dealAction);
                }

    }//end of onCLick method
}
