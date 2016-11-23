package edu.up.cs301.Cribbage;

import android.util.Log;
import android.widget.ImageView;

import java.lang.reflect.Array;

import edu.up.cs301.card.Card;
import edu.up.cs301.card.Rank;
import edu.up.cs301.card.Suit;
import edu.up.cs301.game.infoMsg.GameState;

import static edu.up.cs301.game.R.id.mainDeck;

/**
 * Created by Robot Laptop on 11/14/2016.
 * and some useless humans named
 * Nick, Justin, and Logan
 */

public class CribState extends GameState {

    //private int score0;//player 0's score
    //private int score1;//player 1's score
    private int dealer;//id of the player who is the dealer
    private int whoseTurn;//id of the player who is allowed to play
    private int stage;//int value of the stage of gameplay
    private boolean winner;//becomes true when a player wins
    private boolean go;//becomes true when a player must press the go button
    public boolean delt;//false if round has not been delt, is turned true in deal(), reset to false in setStage()
    public boolean can0Crib;
    public boolean can1Crib;
    int count31;
    int tempCount31;


    //*****************These are all of our decks*********************************
    //These are intialised her but set in the constructor

    public Deck mainDeck;// = new Deck();//holds 52 cards

    //public Deck player0Hand;// = new Deck();//is player 0's hand
    // public Deck player0ToPlay;// = new Deck();//tracks the cards that player 0 puts into play
    //public Deck player1Hand;// = new Deck();//is player 1's hand
    //public Deck player1ToPlay;// = new Deck();//tracks the cards that player 1 puts into play

    public Deck cribDeck;// = new Deck();//contains the cards that players have put in crib
    public Deck playDeck;// = new Deck();//contains the cards that players have player
    public Deck cutDeck;// = new Deck();//contains the card that was cut from deck
    public Deck handsOfBothPlayers[] = new Deck[2];
    public Deck eachPlayerCardsPlayed[] = new Deck[2];
    public int bothplayersScores[] = new int[2];


    public CribState() {
        mainDeck = new Deck();

        cribDeck = new Deck();
        playDeck = new Deck();
        cutDeck = new Deck();
        handsOfBothPlayers = new Deck[2];
        handsOfBothPlayers[0] = new Deck();
        handsOfBothPlayers[1] = new Deck();
        eachPlayerCardsPlayed[0] = new Deck();
        eachPlayerCardsPlayed[1] = new Deck();
        bothplayersScores[0] = 0;
        bothplayersScores[1]=0;
        count31 = 0;
        tempCount31 = 0;

        //score0 = 0;
        //score1 = 0;
        dealer = 0;
        delt = false;
        can0Crib = true;
        can1Crib = true;
        //setFirstDealer();
        Log.i("The dealer is player", "" + dealer);
        whoseTurn = getWhoseTurn();
        stage = 0;
        winner = false;
        go = false;

        mainDeck.add52();
        Log.i("what is in the maindeck", mainDeck.toString() + mainDeck.size());


        for (int i = 0; i < 7; i++) {//This will give us a truly random deck everytime we run the constuctor
            mainDeck.shuffle();
        }
        Log.i("the maindeck Order", mainDeck.toString());


        deal();//This is only here for debugging purposes.
        Log.i("What is in player0Hand", handsOfBothPlayers[0].toString() + handsOfBothPlayers[0].size());
        Log.i("What is in player1Hand", handsOfBothPlayers[1].toString() + handsOfBothPlayers[1].size());
        Log.i("the maindeck Order", mainDeck.toString() + mainDeck.size());


        //setStage();


    }//End of OG Constructor

    /**
     * This is what will accept the state when it is sent back to the local game
     *
     * @param origState
     */

    //*******************************need to update all of this down below.***********Nick 16Nov2016
    public CribState(CribState origState) {
        //this.score0 = origState.getScore(0);
        //this.score1 = origState.getScore(1);
        this.dealer = origState.getDealer();
        this.whoseTurn = origState.getWhoseTurn();
        this.stage = origState.getStage();
        this.winner = origState.isScore121();
        this.go = origState.isGo(origState.getWhoseTurn());
        this.mainDeck = origState.mainDeck;
        this.cribDeck = origState.cribDeck;
        this.playDeck = origState.playDeck;
        this.cutDeck = origState.cutDeck;
        this.handsOfBothPlayers[0] = origState.handsOfBothPlayers[0];
        this.handsOfBothPlayers[1] = origState.handsOfBothPlayers[1];
        this.eachPlayerCardsPlayed[0] = origState.eachPlayerCardsPlayed[0];
        this.eachPlayerCardsPlayed[1] = origState.eachPlayerCardsPlayed[1];
        this.count31 = origState.count31;
        this.tempCount31 = origState.tempCount31;
        this.bothplayersScores[0]= origState.bothplayersScores[0];
        this.bothplayersScores[1]=origState.bothplayersScores[1];


    }//End of overloaded contructor

    //********************SETTERS AND GETTERS************************************************
    //sets a players score
    public void setScore(int player, int score) {
        if (player == 0) {
            bothplayersScores[0] = bothplayersScores[0] + score;
        } else if (player == 1) {
            bothplayersScores[1] = bothplayersScores[1] + score;
        } else {
            return;
        }
    }

    //returns a players score
    public int getScore(int player) {
        if (player == 0) {
            return bothplayersScores[0];
        } else if (player == 1) {
            return bothplayersScores[1];
        } else {
            return 0;
        }
    }

    //This is a method Nick made so he can see if he is looking into the cribstate.
    public void thisIsInTheCribState() {
    }


    /**
     * This is so we can manually set a players hand for testing purposes.
     *
     * @param player
     */
    public void setHand(int player) {

    }


    //gets the hand of a player
    public Deck getHand(int player) {
        if (player == 0) {
            return handsOfBothPlayers[0];
        } else if (player == 1) {
            return handsOfBothPlayers[1];
        } else {
            return null;
        }
    }


    public Deck getCribDeck() {
        return cribDeck;
    }

    public Deck getPlayDeck() {
        return playDeck;
    }

    public boolean canCrib(int playeridx) {
        if (playeridx == 0) {
            if (handsOfBothPlayers[0].size() <= 4) {
                can0Crib = false;
                return false;
            }
        } else if (playeridx == 1) {
            if (handsOfBothPlayers[1].size() <= 4) {
                can1Crib = false;
                return false;
            }
        }
        return true;
    }


    //set which players turn it is
    public void setTurn(int initTurn) {
        whoseTurn = initTurn;
    }


    //returns the int of whose turn it is
    public int getWhoseTurn() {
        if (stage == 1) {
            //deal stage
            return dealer;

        } else if (stage == 2) {
            //crib stage
            //Nick is trying somethin*************************

            if (dealer == 0) {
                if (handsOfBothPlayers[0].size() > handsOfBothPlayers[1].size()) {
                    //dealer gets to move
                    return 0;
                } else {
                    //non dealer gets to move
                    return 1;
                }
            }
            if (dealer == 1) {
                if (handsOfBothPlayers[0].size() > handsOfBothPlayers[1].size()) {
                    //dealer gets to move
                    return 1;
                } else {
                    //non dealer gets to move
                    return 0;
                }
            }


            //Nick is trying something************************
            return -1;
        } else if (stage == 3) {
            //play action
            if (dealer == 0) {
                if (handsOfBothPlayers[0].size() > handsOfBothPlayers[1].size()) {
                    //dealer gets to move
                    return 0;
                } else {
                    //non dealer gets to move
                    return 1;
                }
            }
            if (dealer == 1) {
                if (handsOfBothPlayers[0].size() > handsOfBothPlayers[1].size()) {
                    //dealer gets to move
                    return 1;
                } else {
                    //non dealer gets to move
                    return 0;
                }
            }
            return -1;
        } else if (stage == 4) {
            //score phase neither player needs to play
            return 0;//NICK IS AMAZING AT BUG HUNTING!
        }
        return 0;
    }

    public void setFirstDealer() {
        Deck temp = new Deck();
        temp.add52().shuffle();
        temp.moveTopCardTo(handsOfBothPlayers[0]);
        //mainDeck.shuffle();//Nick- No need to shuffle here. The deck is already shuffled.
        temp.moveTopCardTo(handsOfBothPlayers[1]);
        int play0 = handsOfBothPlayers[0].peekAtTopCard().getRank().value(1);
        int play1 = handsOfBothPlayers[1].peekAtTopCard().getRank().value(1);
        Log.i("The human players cutis", handsOfBothPlayers[0].toString());
        handsOfBothPlayers[0].removeTopCard();
        handsOfBothPlayers[1].removeTopCard();
        if (play0 > play1) {
            dealer = 0;
        } else if (play1 > play0) {
            dealer = 1;
        } else {
            setFirstDealer();//recursion for the tie

        }
    }

    //sets the dealer to the other player
    public void setDealer() {
        if (getStage() == 0) {
            //the game has already been going and it is on to another round
            //who ever is the current dealer needs to be changed to who ever is not the dealer
            if (dealer == 0) {
                dealer = 1;
                return;
            } else {
                dealer = 0;
                return;
            }
        }
    }


    //gets who the dealer is
    public int getDealer() {
        return dealer;
    }

    public void setCount() {
        if (playDeck.size() != 0) {
            Log.i("Pre add count 31", "" + count31);
            tempCount31 = playDeck.lookAtCard(playDeck.size() - 1).getRank().cribValue(1);
            Log.i("Value of card", "" + playDeck.lookAtCard(playDeck.size() - 1).shortName() + " "
                    + playDeck.lookAtCard(playDeck.size() - 1).getRank().cribValue(1));
            count31 = count31 + tempCount31;
            Log.i("after add count 31", "" + count31);

        }


    }//setCount

    public int getCount() {
        return count31;


    }


    //set the stage of the game
    //0=cut,1=deal,2=crib,3=play,4=score
    public void setStage() {
        //stage is initialized to -1 to indicate that a new game has been started
        //and a dealer needs to be set based on a cut of the deck
        //once that has succusefuully been completed stage is never reset to -1


        int stageCut = 0;
        int stageDeal = 1;
        int stageCrib = 2;
        int stagePlay = 3;
        int stageScoreing = 4;


        if (handsOfBothPlayers[0].size() == 0 && handsOfBothPlayers[1].size() == 0 && cutDeck.size() == 0) {
            //we are in the cut phase of the round
            Log.i("this is the first stage", "setStage: 0");
            stage = stageCut;
        } else if (handsOfBothPlayers[0].size() < 6 && handsOfBothPlayers[1].size() < 6 && cutDeck.size() != 0 && playDeck.size() == 0) {
            //there is a card in the cut deck
            //there may be some cards in the players hands but they do not have a full hand yet
            stage = stageDeal;
        } else if (4 < handsOfBothPlayers[0].size() && handsOfBothPlayers[0].size() <= 6 &&
                4 < handsOfBothPlayers[1].size() && handsOfBothPlayers[1].size() <= 6) {
            //we are in the crib Phase
            stage = stageCrib;
        } else if (cribDeck.size() == 4 && playDeck.size() < 8)//player1Hand.size() <= 4 &&
        {
            //each player has played two cards to the crib
            //we are at the start of the play phase
            mainDeck.moveTopCardTo(cutDeck);
            Log.i("cutDeck has a card now", cutDeck.toString());
            stage = stagePlay;
        } else if (playDeck.size() == 8) {
            //each player has played all four cards
            //we are now in the scoring phase
            bothplayersScores[0] += score(eachPlayerCardsPlayed[0],cutDeck);
            bothplayersScores[1] += score(eachPlayerCardsPlayed[1],cutDeck);
            bothplayersScores[dealer] += score(cribDeck,cutDeck);
            stage = stageScoreing;
            //delt = false;//delt is reset becuase we can deal again
            //can0Crib = true;
            //can1Crib = true;
            stage=1;
        } else {
            //all rounds have been gone through
            //it is now time to start back at the cut for the round
            stage = 0;
            Log.i("this is the stage turn", "around point");
            delt = false;//delt is reset becuase we can deal again
            can0Crib = true;
            can1Crib = true;
        }
    }

    //returns the int value of the stage of the game
    //0=cut,1=deal,2=crib,3=play,4=score
    public int getStage() {
        return stage;
    }


    //******************************8END SETTERS AND GETTERS****************************************


    //returns true if a score is over 121
    public boolean isScore121() {
        if (bothplayersScores[0] >= 121 || bothplayersScores[1] >= 121) {

            return true;
        }
        return false;
    }


    //returns true if a Go action is allowed
    public boolean isGo(int player) {
        //Nick has psuedo code mocked up for this in the slack.


        if (handsOfBothPlayers[player].size() != 0) {
            for (int i = 0; i < handsOfBothPlayers[player].size(); i++) {
                if ((count31 + handsOfBothPlayers[player].lookAtCard(i).getRank().cribValue(1)) <= 31) {
                    return false;
                }
            }
            count31 = 0;
            bothplayersScores[1-player]++;
            return true;
        }
        return false;
    }

    //places six cards in each players hands


    public void deal()
    {
        //mainDeck.shuffle();//Disabled for debugging ease
        Log.i("this is in the deal", "size predelte: "+mainDeck.size());

        mainDeck.deleteDeck();//nullify the current state of the main deck

        Log.i("this is in the deal", "size postdelte/preadd52: "+mainDeck.size());


        //make sure there is a new deck for the next round and really shuffle it good
        mainDeck.add52().shuffle().shuffle().shuffle();


        Log.i("this is in the deal", "size postdd52: "+mainDeck.size());
        Log.i("size play1hand: "+handsOfBothPlayers[0].size(), "sizePlay2Hand PreDel: "+handsOfBothPlayers[1].size());
        Log.i("play1hand: "+handsOfBothPlayers[0].toString(), "Play2Hand PreDel: "+handsOfBothPlayers[1].toString());

        /*This will make sure the remainder of the decks have been cleared. If we are doing a deal
            we want to reseteverything back to an empty state.
        */
        handsOfBothPlayers[0].deleteDeck();//remove all the cards from the players hands
        handsOfBothPlayers[1].deleteDeck();//remove all the cards from the players hands
        cribDeck.deleteDeck();
        eachPlayerCardsPlayed[0].deleteDeck();
        eachPlayerCardsPlayed[1].deleteDeck();
        cutDeck.deleteDeck();

        Log.i("size play1hand: "+handsOfBothPlayers[0].size(), "sizePlay2Hand postDel: "+handsOfBothPlayers[1].size());
        Log.i("play1hand: "+handsOfBothPlayers[0].toString(), "Play2Hand postDel: "+handsOfBothPlayers[1].toString());
        int handSize = 6;
        for(int i = 0; i<handSize; i++)
        {
            mainDeck.moveTopCardTo(handsOfBothPlayers[0]);//Changed this from player0hand
            mainDeck.moveTopCardTo(handsOfBothPlayers[1]);//changed from player1hand
        }Log.i("size play1hand: "+handsOfBothPlayers[0].size(), "sizePlay2Hand postDeal: "+handsOfBothPlayers[1].size());
        Log.i("play1hand: "+handsOfBothPlayers[0].toString(), "Play2Hand postDeal: "+handsOfBothPlayers[1].toString());
        setStage();
        Log.i("this is in the deal", "size post setstage: "+mainDeck.size());
        delt = true;


    }


    //cuts the deck and moves the card to the cutDeck
    public void cutForTopCardDuringPlay()
    {
        mainDeck.shuffle();
        mainDeck.moveTopCardTo(cutDeck);
    }

    //runs the scoring algorithm
    public int score(Deck playDeck, Deck cutDeck)
    {
        int total = 0;
        //creates a copy that combines the player's deck and the cut Card
        Deck tempDeck = new Deck(playDeck);
        cutDeck.moveTopCardTo(tempDeck);
        //since tempDeck is a copy remove card can be used and the player's hand
        //and the cut deck hand are safe
        Card c0 = tempDeck.removeTopCard();
        Card c1 = tempDeck.removeTopCard();
        Card c2 = tempDeck.removeTopCard();
        Card c3 = tempDeck.removeTopCard();
        Card c4 = tempDeck.removeTopCard();
        Card[] cards = new Card[5];
        cards[0] = c0;
        cards[1] = c1;
        cards[2] = c2;
        cards[3] = c3;
        cards[4] = c4;

        total = pairs(cards);
        //+runs(cards)+sum15(cards)+flush(cards);
        //other methods that still need to be implemented before they can contribute to the score
        return total;

        
    }
    //looks for pairs
    //should be complete as of 17NOV
    private int pairs(Card[] cards)
    {

        int score = 0;
        int testValue;
        for(int  i = 0; i < cards.length; i++) {
            if (cards[i] != null) {
                testValue = cards[i].getRank().value(1);
                for (int j = i + 1; j < cards.length; j++) {
                    if(cards[j] != null) {
                        if (testValue == cards[j].getRank().value(1)) {
                            score = score + 2;//adds two points for the pair
                        }
                    }
                }
            }
        }
        return score;
    }

    //looks for runs
    private int runs(Card[] cards)
    {
        int score = 0;
        //makes an array of ints equal to the value of the cards
        int[] cardVal = new int[cards.length];
        //assigns the card values to the cardVal array
        for(int i = 0; i < cards.length; i++)
        {
            cardVal[i] = cards[i].getRank().value(1);
        }
        //Sorting algo by Plummer someone should check this
        //sorts cardVal in ascending order
        for(int x = 0; x<cardVal.length -1;x++)
        {
            for(int y = x+1; y<cardVal.length;y++)
            {
                if (cardVal[x] > cardVal[y])
                {
                    int temp = cardVal[y];
                    cardVal[y] = cardVal[x];
                    cardVal[x] = temp;
                }
            }
        }
        /*
        Everything above should be correct of 17NOV
        Above is used to create a sorted array of
        the int values of the cards
        */
        int prev;
        int next;
        int cur;
        for(int j = 1; j< cardVal.length; j++)
        {
            prev = cardVal[j-1];
            next = cardVal[j+1];
            cur = cardVal[j];
            int delPrev  = cur-prev;
            int delNext = next - cur;
            //solves basic runs like 2,3,4
            if(delPrev == 1 && delNext == 1)
            {
                //a run exists!
                score = score + 3;
            }
            //solves double run when prev is equal to the cur card
            if(delPrev == 0 && delNext == 1)
            {
                if(j+2 <= cardVal.length)
                {
                    int nextNext = cardVal[j+2];
                    int delNextNext = nextNext - next;
                    if(delNextNext == 1)
                    {
                        score = score + 6;
                    }
                }
            }
            //solves double run when next is equal to the cur card
            if(delPrev == 1 && delNext == 0)
            {
                if(j+2 <= cardVal.length)
                {
                    int nextNext = cardVal[j+2];
                    int delNextNext = nextNext - next;
                    if(delNextNext == 1)
                    {
                        score = score + 6;
                    }
                }
            }
        }
        return score;
    }
    //looks for sums of 15
    private int sum15(Card[] cards)
    {
        //five nested for loop of doom goes here
        int score = 0;
        return score;
    }
    //looks for flushes
    private int flush(Card[] cards)
    {
        //will need an array of char using the shortName method from Card object
        int score = 0;
        return score;
    }
}//End of CribState Class
