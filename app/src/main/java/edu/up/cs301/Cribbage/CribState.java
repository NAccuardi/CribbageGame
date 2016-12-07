package edu.up.cs301.Cribbage;

import android.util.Log;
import android.widget.ImageView;

import java.lang.reflect.Array;

import edu.up.cs301.card.Card;
import edu.up.cs301.card.Rank;
import edu.up.cs301.card.Suit;
import edu.up.cs301.game.infoMsg.GameState;

import static edu.up.cs301.game.R.id.configTableLayout;
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
    public boolean mustGoBool;
    public int count31;
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
    public boolean canTheCardBePLayed[][] = new boolean[2][4];




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
        mustGoBool = false;
        for(int i =0;i <2; i++){
            for(int j =0; j <4;j++){
                canTheCardBePLayed[i][j]=true;
            }
        }



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
        this.mustGoBool = origState.mustGoBool;

        for(int i =0;i <2; i++){
            for(int j =0; j <4;j++){
                this.canTheCardBePLayed[i][j]=origState.canTheCardBePLayed[i][j];
            }
        }


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
                if (handsOfBothPlayers[0].size() < handsOfBothPlayers[1].size()) {
                    //dealer gets to move
                    return 1;
                } else if (handsOfBothPlayers[0].size() >= handsOfBothPlayers[1].size()){
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
                if (handsOfBothPlayers[0].size() < handsOfBothPlayers[1].size()) {
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
            return 0;
        }
        return 0;
    }

    public void setFirstDealer() {
        Deck temp = new Deck();
        temp.add52().shuffle();
        temp.moveTopCardTo(handsOfBothPlayers[0]);
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
            //who ever is the current dealer needs to be changed to who ever is not the dealer
            if (dealer == 0) {
                dealer = 1;
                return;
            } else {
                dealer = 0;
                return;
            }
    }


    //gets who the dealer is
    public int getDealer() {
        return dealer;
    }

    public void setCount() {
        if (playDeck.size() != 0) {
            tempCount31 = playDeck.lookAtCard(playDeck.size() - 1).getRank().cribValue(1);
            count31 = count31 + tempCount31;

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

            if(cutDeck.size()==0){
                mainDeck.moveTopCardTo(cutDeck);
            }
            //each player has played two cards to the crib
            //we are at the start of the play phase

            Log.i("cutDeck has a card now", cutDeck.toString());
            stage = stagePlay;
        } else if (playDeck.size() == 8) {
            //each player has played all four cards
            //we are now in the scoring phase

            //The scoring shit is straight fucked yo************************************************************


                stage = stageScoreing;



            //delt = false;//delt is reset becuase we can deal again
            //can0Crib = true;
            //can1Crib = true;
            //stage=stageDeal;
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


    //******************************END SETTERS AND GETTERS****************************************


    //returns true if a score is over 121
    public boolean isScore121() {
        if (bothplayersScores[0] >= 121 || bothplayersScores[1] >= 121) {

            return true;
        }
        return false;
    }


    //returns true if a Go action is allowed
   public boolean isGo(int player) {
       if (handsOfBothPlayers[player].size() != 0) {
           for (int i = 0; i < handsOfBothPlayers[player].size(); i++) {
               if ((count31 + handsOfBothPlayers[player].lookAtCard(i).getRank().cribValue(1)) <= 31) {
                   return true;
               }
           }
           return false;
       }
       return false;//player hand is size 0
   }

    public void canCardbePlayed(int player){//This set the cards to either true or false depending on if they can be played.
        for(int i =0; i < 4;i++){
            if ((count31 + handsOfBothPlayers[player].lookAtCard(i).getRank().cribValue(1)) > 31) {

                canTheCardBePLayed[player][i]=false;
            }
            canTheCardBePLayed[player][i]=true;
        }
    }

//If this is true you will need to press the 'GO' Button
    public void mustGo(int player){
       // canCardbePlayed(player);
        if(handsOfBothPlayers[player].size()==0){mustGoBool =true;}
        for(int i =0; i < handsOfBothPlayers[player].size();i++){
            if ((count31 + handsOfBothPlayers[player].lookAtCard(i).getRank().cribValue(1)) <= 31) {

                mustGoBool =false;
            }

        }
        mustGoBool =true;
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
        playDeck.deleteDeck();

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
        if(count31 > 0)
        {
            setDealer();
            Log.i("Dealer has been changed"," ");
        }
        count31=0;
        delt = true;


    }



    public int nicksScoringEndofTurn(Deck cutDeck,Deck playDeck){

        Deck scoringDeck = new Deck(playDeck);
        Deck tempCutDeck = new Deck (cutDeck);
        tempCutDeck.moveTopCardTo(scoringDeck);



        int pairs =0;
        int runs =0;
        int flush =0;
        int flushMatchesCutCard=0;
        int numberOf15Ses =0;
        int nobs =0;

        // check for pairs
        for( int i = 0; i < scoringDeck.size()-1;i++){
            for(int j =1; j <scoringDeck.size();j++){
                if(scoringDeck.lookAtCard(i)==scoringDeck.lookAtCard(j)){
                    pairs++;
                }
            }
        }//End of check for pairs

        //Check for nobs
        for(int i =0; i< scoringDeck.size();i++){
            if(cutDeck.getSuit(0)==scoringDeck.getSuit(i)&&scoringDeck.getRank(i)==Rank.JACK){
                nobs++;
            }
        }

        //Checks for sums of 15
        //If zero off, if 1 one.
        for(int a =0;a <1;a++){
            for(int b = 0;b<1;b++){
                for(int c =0;c<1;c++){
                    for(int d =0; d < 1;d++){
                        for(int e = 0; e <1;e++){
                            if(
                            scoringDeck.runMethod(scoringDeck,a,1)+scoringDeck.runMethod(scoringDeck,b,2)
                                    +scoringDeck.runMethod(scoringDeck,c,3)+scoringDeck.runMethod(scoringDeck,d,4)
                                    +scoringDeck.runMethod(scoringDeck,e,5)==15){
                                numberOf15Ses++;
                            }

                        }
                    }
                }
            }
        }









        return (pairs*2)+(nobs*1)+(flush*4)+(numberOf15Ses*2);
    }

  //  //scoring algorithm for play/go phase
  //  public int scorePlay(Deck playedCards)
  //  {
  //      Deck tempPlayedCards = new Deck(playedCards);
  //      int size = playedCards.size();
  //      Card[] cards = new Card[size];
  //      for(int i = 0; i< size; i++)
  //      {
  //          cards[i] = tempPlayedCards.removeTopCard();
  //      }
  //      int total = playPairs(cards) + play15(cards)+ playRuns(cards);
  //      return total;
  //  }
  //  //scoring 15 during play is dependent on the current card added to the previous cards
  //  //and whether it makes a sum of 15 or not
  //  public int play15(Card[] cards)
  //  {
  //      if(cards.length<2)
  //      {
  //          //Not enough cards have been played yet
  //          return 0;
  //      }
  //      int size = cards.length;
  //      int[] value = new int[size];
  //      for(int i = 0; i < size; i++)
  //      {
  //          value[i] = cards[i].getRank().cribValue(1);
  //      }
  //      int sum = 0;
  //      for(int j = (size-1); j >= 0; j--)//nick changed from size, and j>0
  //      {
  //          sum = sum + value[j];
  //          if(sum == 15)
  //          {
  //              return 2;
  //          }
  //      }
  //      return 0;
  //  }
  //  //looks for pairs during the play phase
  //  public int playPairs(Card[] cards)
  //  {
  //      int score = 0;
  //      int size = cards.length;
  //      if(size<2)
  //      {
  //          //Not enough cards have been played yet
  //          return 0;
  //      }
  //      if(cards[size-1].getRank().value(1) == cards[size-2].getRank().value(1))
  //      {
  //          //a pair has been played
  //          score = 2;
  //          if(size>2)//checking for triples
  //          {
  //              if(cards[size-1].getRank().value(1) == cards[size-3].getRank().value(1))
  //              {
  //                  score = 6;
  //                  if(size>3)//checking for quadrouples
  //                  {
  //                      if(cards[size-1].getRank().value(1) == cards[size-4].getRank().value(1))
  //                      {
  //                          score = 12;
  //                      }
  //                  }
  //              }
//
  //          }
//
  //      }
  //      return score;
  //  }
  //  //looks for runs during the play
  //  public int playRuns(Card[] cards)
  //  {
  //      int score = 0;
  //      int size = cards.length;
  //      if(size < 3)
  //      {
  //          //not enough cards have been played
  //          return 0;
  //      }
  //      if(size >= 5)//the longest run possible is 5
  //      {
  //          int[] rank = new int[5];
  //          for(int j = size; j> size - 5; j--)
  //          {
  //              //get the top 5 cards
  //              rank[j] = cards[j].getRank().value(1);
  //          }
  //          //sort first
  //          for(int x = 0; x<rank.length -1;x++)
  //          {
  //              for(int y = x+1; y<rank.length;y++)
  //              {
  //                  if (rank[x] > rank[y])
  //                  {
  //                      int temp = rank[y];
  //                      rank[y] = rank[x];
  //                      rank[x] = temp;
  //                  }
  //              }
  //          }
  //          for(int a = 1; a<rank.length; a++)
  //          {
  //              int mid = rank[a];
  //              int prev = rank[a-1];
  //              int next = rank[a+1];
  //              int delPrev = mid-prev;
  //              int delNext = next - mid;
  //              if(delNext ==1 && delPrev ==1)
  //              {
  //                  score = score + 3;
  //                  if(a+2 > rank.length)
  //                  {
  //                      continue;
  //                  }
  //                  int nextNext = rank[a+2];
  //                  int delNN = nextNext - next;
  //                  if(delNN == 1 )
  //                  {
  //                      //a four score!
  //                      //3 points have already been awarded for so just need to add one more
  //                      score = score + 1;
  //                      if((a+3) > rank.length)
  //                      {
  //                          continue;
  //                      }
  //                      int nNN = rank[a+3];
  //                      int delNNN = nNN - nextNext;
  //                      if(delNNN == 1)
  //                      {
  //                          //a five score
  //                          //4 points have already been awarded so add one more
  //                          score = score +1;
  //                      }
  //                  }
  //              }
  //          }
  //      }
  //      else//there are less than 5 cards in the play but at least 3
  //      {
  //          int[] rank = new int[size];
  //          for(int i = 0; i < size; i++)
  //          {
  //              rank[i] = cards[i].getRank().value(1);
  //          }
  //          //sort first
  //          for(int x = 0; x<rank.length -1;x++)
  //          {
  //              for(int y = x+1; y<rank.length;y++)
  //              {
  //                  if (rank[x] > rank[y])
  //                  {
  //                      int temp = rank[y];
  //                      rank[y] = rank[x];
  //                      rank[x] = temp;
  //                  }
  //              }
  //          }
  //          for(int a = 1; a<rank.length; a++)
  //          {
  //              int mid = rank[a];
  //              int prev = rank[a-1];
  //              int next = rank[a+1];//This line trys and accesses outside of the rank.
  //              int delPrev = mid-prev;
  //              int delNext = next - mid;
  //              if(delNext ==1 && delPrev ==1)
  //              {
  //                  score = score + 3;
  //                  if(a+2 > rank.length)
  //                  {
  //                      continue;
  //                  }
  //                  int nextNext = rank[a+2];
  //                  int delNN = nextNext - next;
  //                  if(delNN == 1 )
  //                  {
  //                      //a four score!
  //                      //3 points have already been awarded for so just need to add one more
  //                      score = score + 1;
  //                  }
  //              }
  //          }
//
  //      }
  //      return score;
  //  }
//
//
  //  //runs the scoring for player hands algorithm
  //  public int score(Deck playDeck, Deck cutDeck)
  //  {
  //      int total = 0;
  //      //creates a copy that combines the player's deck and the cut Card
  //      Deck tempDeck = new Deck(playDeck);
  //      Deck tempCut = new Deck(cutDeck);
  //      //since tempDeck is a copy remove card can be used and the player's hand
  //      //and the cut deck hand are safe
  //      Card c0 = tempCut.removeTopCard();
  //      Card c1 = tempDeck.removeTopCard();
  //      Card c2 = tempDeck.removeTopCard();
  //      Card c3 = tempDeck.removeTopCard();
  //      Card c4 = tempDeck.removeTopCard();
  //      Card[] cards = new Card[5];
  //      cards[0] = c0;
  //      cards[1] = c1;
  //      cards[2] = c2;
  //      cards[3] = c3;
  //      cards[4] = c4;
//
  //      //some scoring is only done using the players hand
  //      Card[] playersCards = new Card[4];
//
  //      total = pairs(cards) + knobs(tempDeck,tempCut)
  //              + flush(playersCards) + sum15(cards) +runs(cards);
  //      return total;
  //  }
  //  //looks for pairs
  //  //should be complete as of 17NOV
  //  public int pairs(Card[] cards)
  //  {
//
  //      int score = 0;
  //      int testValue;
  //      for(int  i = 0; i < cards.length; i++) {
  //          if (cards[i] != null) {
  //              testValue = cards[i].getRank().value(1);
  //              for (int j = i + 1; j < cards.length; j++) {
  //                  if(cards[j] != null) {
  //                      if (testValue == cards[j].getRank().value(1)) {
  //                          score = score + 2;//adds two points for the pair
  //                      }
  //                  }
  //              }
  //          }
  //      }
  //      return score;
  //  }
//
  //  //looks for runs
  //  public int runs(Card[] cards)
  //  {
  //      int score = 0;
  //      //makes an array of ints equal to the value of the cards
  //      int[] cardVal = new int[cards.length];
  //      //assigns the card values to the cardVal array
  //      for(int i = 0; i < cards.length; i++)
  //      {
  //          cardVal[i] = cards[i].getRank().value(1);
  //      }
  //      //Sorting algo by Plummer someone should check this
  //      //sorts cardVal in ascending order
  //      for(int x = 0; x<cardVal.length -1;x++)
  //      {
  //          for(int y = x+1; y<cardVal.length;y++)
  //          {
  //              if (cardVal[x] > cardVal[y])
  //              {
  //                  int temp = cardVal[y];
  //                  cardVal[y] = cardVal[x];
  //                  cardVal[x] = temp;
  //              }
  //          }
  //      }
  //      /*
  //      Everything above should be correct of 17NOV
  //      Above is used to create a sorted array of
  //      the int values of the cards
  //      */
  //      int prev;
  //      int next;
  //      int cur;
  //      for(int j = 1; j< cardVal.length; j++)
  //      {
  //          prev = cardVal[j-1];
  //          next = cardVal[j+1];
  //          cur = cardVal[j];
  //          int delPrev  = cur-prev;
  //          int delNext = next - cur;
  //          //solves basic runs like 2,3,4
  //          if(delNext == -1)
  //          {
  //              delNext = 1;
  //          }
  //          if(delPrev == -1)
  //          {
  //              delPrev = 1;
  //          }
  //          if(delPrev == 1 && delNext == 1)
  //          {
  //              //a run exists!
  //              score = score + 3;
  //          }
  //          //solves double run when prev is equal to the cur card
  //          if(delPrev == 0 && delNext == 1)
  //          {
  //              if(j+2 <= cardVal.length)
  //              {
  //                  int nextNext = cardVal[j+2];
  //                  int delNextNext = nextNext - next;
  //                  if(delNextNext == 1)
  //                  {
  //                      score = score + 6;
  //                  }
  //              }
  //          }
  //          //solves double run when next is equal to the cur card
  //          if(delPrev == 1 && delNext == 0)
  //          {
  //              if(j+2 <= cardVal.length)
  //              {
  //                  int nextNext = cardVal[j+2];
  //                  int delNextNext = nextNext - next;
  //                  if(delNextNext == 1)
  //                  {
  //                      score = score + 6;
  //                  }
  //              }
  //          }
  //      }
  //      return score;
  //  }
  //  //looks for sums of 15
  //  public int sum15(Card[] cards)
  //  {
  //      //five nested for loop of doom goes here
  //      int score = 0;
  //      int[] value = new int[cards.length];
  //      for(int a = 0; a<value.length;a++)
  //      {
  //          value[a] = cards[a].getRank().cribValue(1);
  //      }
  //      for(int i = 0; i < value.length;i++)
  //      {
  //          for(int j = i+1; j < value.length; j++)
  //          {
  //              int sum = 0;
  //              sum = value[i]+value[j];
  //              if(sum == 15)
  //              {
  //                  score = score + 2;
  //              }
  //              for(int k = j+1; k<value.length; k++)
  //              {
  //                  sum = 0;
  //                  sum = value[i]+value[j]+value[k];
  //                  if(sum == 15)
  //                  {
  //                      score = score + 2;
  //                  }
  //                  for(int l = k+1; l<value.length;l++)
  //                  {
  //                      sum = 0;
  //                      sum = value[i]+value[j]+value[k]+value[l];
  //                      if(sum ==15)
  //                      {
  //                          score = score + 2;
  //                      }
  //                      for(int m = l+1; m<value.length;m++)
  //                      {
  //                          sum = 0;
  //                          sum = value[i]+value[j]+value[k]+value[l] + value[m];
  //                          if(sum == 15)
  //                          {
  //                              score = score + 2;
  //                          }
  //                      }
  //                  }
  //              }
  //          }
  //      }
  //      return score;
  //  }
  //  //looks for flushes
  //  public int flush(Card[] cards)
  //  {
  //      //to score points for a flush all four cards need to be the same suit
  //      for(int i = 1; i < cards.length; i++)
  //      {
  //          if(cards[0].getSuit() == cards[i].getSuit())
  //          {
  //              //the pair of cards are the same suit so move on to the next one
  //              continue;
  //          }
  //          else
  //          {
  //              //if there is ever a pair of cards that are not the same score
  //              //return 0 points. There is never a small flush it is either
  //              //all four cards the same or nothing
  //              return 0;
  //          }
  //      }
  //      //if it makes it to this point then all cards are the same suit and 4 points are returned
  //      return 4;
  //  }
//
  //  public int knobs(Deck played, Deck cut)
  //  {
  //      int size;
  //      int total = 0;
  //      size = played.size();
  //      Card[] playedCards = new Card[size];
  //      Card cutCard = cut.peekAtTopCard();
  //      for(int i = 0; i < size; i++)
  //      {
  //          if(playedCards[i].getRank() == Rank.JACK)
  //          {
  //              //potenial knob has been found
  //              if(playedCards[i].getSuit() == cutCard.getSuit())
  //              {
  //                  //a knob has been found because the players hand
  //                  //contains a jack with the same suit as the top car
  //                  total = total + 1;
  //              }
  //          }
  //      }
  //      return total;
  //  }

}//End of CribState Class
