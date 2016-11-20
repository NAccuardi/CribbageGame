package edu.up.cs301.Cribbage;

import java.lang.reflect.Array;

import edu.up.cs301.card.Card;
import edu.up.cs301.card.Rank;
import edu.up.cs301.game.infoMsg.GameState;

/**
 * Created by Robot Laptop on 11/14/2016.
 * and some useless humans named
 * Nick, Justin, and Logan
 */

public class CribState extends GameState {

    private int score0;//player 0's score
    private int score1;//player 1's score
    private int dealer;//id of the player who is the dealer
    private int whoseTurn;//id of the player who is allowed to play
    private int stage;//int value of the stage of gameplay
    private boolean winner;//becomes true when a player wins
    private boolean go;//becomes true when a player must press the go button
    private int playerId;//determines who is playing
    private Deck mainDeck = new Deck();//holds 52 cards
    private Deck player0Hand = new Deck();//is player 0's hand
    private Deck player0ToPlay = new Deck();//tracks the cards that player 0 puts into play
    private Deck player1Hand = new Deck();//is player 1's hand
    private Deck player1ToPlay = new Deck();//tracks the cards that player 1 puts into play
    private Deck cribDeck = new Deck();//contains the cards that players have put in crib
    private Deck playDeck = new Deck();//contains the cards that players have player
    private Deck cutDeck = new Deck();//contains the card that was cut from deck



    public CribState(){
        score0 = 0;
        score1 = 0;
        dealer = getDealer();
        whoseTurn = getWhoseTurn();
        stage = -1;
        winner = false;
        go = false;
        playerId = getPlayerId();
        mainDeck.add52();


        for(int i =0; i<7;i++){//This will give us a truly random deck everytime we run the constuctor
            mainDeck.shuffle();
        }

        player0Hand = null;
        player1Hand = null;
        cribDeck = null;
        playDeck = null;
        cutDeck = null;
        player0ToPlay = null;
        player1ToPlay = null;


    }//End of OG Constructor

    /**
     * This is what will accept the state when it is sent back to the local game
     * @param origState
     */

    //*******************************need to update all of this down below.***********Nick 16Nov2016
    public CribState(CribState origState){
        score0 = 0;
        score1 = 0;
        dealer = getDealer();
        whoseTurn = getWhoseTurn();
        stage = getStage();
        winner = false;
        go = false;
        playerId = getPlayerId();
        mainDeck.add52();


        for(int i =0; i<7;i++){//This will give us a truly random deck everytime we run the constuctor
            mainDeck.shuffle();
        }

        player0Hand = null;
        player1Hand = null;
        cribDeck = null;
        playDeck = null;
        cutDeck = null;
        player0ToPlay = null;
        player1ToPlay = null;


    }//End of overloaded contructor

    //********************SETTERS AND GETTERS************************************************
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
        return playerId;
    }


    public void thisIsInTheCribState(){}

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
        if(getStage()==0)
        {
            //the game has already been going and it is on to another round
            //who ever is the current dealer needs to be changed to who ever is not the dealer
            if(dealer == 0)
            {
                dealer = 1;
                return;
            }
            else
            {
                dealer = 0;
                return;
            }
        }
        else {
            //snag two cards from the deck
            //assign one to each player
            //compare values
            //player with lower card is assign dealer
            mainDeck.moveTopCardTo(player0Hand);
            mainDeck.shuffle();
            mainDeck.moveTopCardTo(player1Hand);
            int play0 = player0Hand.peekAtTopCard().getRank().value(1);
            int play1 = player1Hand.peekAtTopCard().getRank().value(1);
            if (play0 > play1) {
                dealer = 0;
                player0Hand.moveTopCardTo(mainDeck);
                player1Hand.moveTopCardTo(mainDeck);
                mainDeck.shuffle();
                return;
            } else if (play1 > play0) {
                dealer = 1;
                player0Hand.moveTopCardTo(mainDeck);
                player1Hand.moveTopCardTo(mainDeck);
                mainDeck.shuffle();
                return;
            } else {
                player0Hand.moveTopCardTo(mainDeck);
                player1Hand.moveTopCardTo(mainDeck);
                mainDeck.shuffle();
                setDealer();//recursion for the tie
            }
            return;
        }
    }

    //gets who the dealer is
    public int getDealer()
    {
        return dealer;
    }



    //set the stage of the game
    //0=cut,1=deal,2=crib,3=play,4=score
    public void setStage()
    {
        //stage is initialized to -1 to indicate that a new game has been started
        //and a dealer needs to be set based on a cut of the deck
        //once that has succusefuully been completed stage is never reset to -1
        if(player0Hand == null && player1Hand ==null && cutDeck == null)
        {
            //we are in the cut phase of the round
            stage = 0;
            return;
        }
        else if(player0Hand.size() < 6 && player1Hand.size() < 6 && cutDeck != null)
        {
            //there is a card in the cut deck
            //there may be some cards in the players hands but they do not have a full hand yet
            stage = 1;
            return;
        }
        else if(player0Hand.size() == 6 && player1Hand.size() == 6)
        {
            //each player has a full hand and has not played any cards to the crib
            //we are at the start of the crib phase
            stage = 2;
            return;
        }
        else if(player0Hand.size() == 4 && player1Hand.size() == 4 && cribDeck.size() == 4)
        {
            //each player has played two cards to the crib
            //we are at the start of the play phase
            stage = 3;
            return;
        }
        else if(playDeck.size() == 8)
        {
            //each player has played all four cards
            //we are now in the scoring phase
            stage = 4;
            return;
        }
        else
        {
            //all rounds have been gone through
            //it is now time to start back at the cut for the round
            stage = 0;
        }
    }

    //returns the int value of the stage of the game
    //0=cut,1=deal,2=crib,3=play,4=score
    public int getStage()
    {

        return stage;
    }


    //******************************8END SETTERS AND GETTERS****************************************


    //returns true if a score is over 121
    public boolean isScore121()
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
        mainDeck.shuffle();
        int handSize = 6;
        for(int i = 0; i<handSize; i++)
        {
            mainDeck.moveTopCardTo(player0Hand);
            mainDeck.moveTopCardTo(player1Hand);
        }
    }


    //cuts the deck and moves the card to the cutDeck
    public void cut()
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
        for(int  i = 0; i < cards.length; i++)
        {
            testValue = cards[i].getRank().value(1);
            for(int	j = i+1; j < cards.length; j++)
            {
                if(testValue == cards[j].getRank().value(1))
                {
                    score = score + 2;//adds two points for the pair
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
