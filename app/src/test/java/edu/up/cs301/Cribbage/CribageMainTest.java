package edu.up.cs301.Cribbage;

import org.junit.Test;

/**
 * Created by Robot Laptop on 11/9/2016.
 */
public class CribageMainTest {
    @Test
    public void createDefaultConfig() throws Exception {

    }

    @Test
    public void createLocalGame() throws Exception {

    }

    /**
     * This will make sure that when the score is 121 the game will end correctly.
     *
     * int player, int score
     */
    @Test
    public void isScore121() throws Exception {

 //       CribMain testMain = new CribMain();
   //     testMain.setPlayerScore(0,125);
     //   boolean test = testMain.isScore121();
       // assertEquals(test, true);
    }

    /**
     * This will make a copy check to make sure our copies of the game state are the same.
     * We will make a sythetic game state and then make a copy of it.
     * Then we will check all of the values to make sure they are equal.
     */
    @Test
    public void makeCopy() throws Exception {
        //CribMain testMain = new CribMain();
//        testMain.score = 100;
  //      testMain.player[0].hand[6H,KS,QH,2C,3S,9D];

//        CribMain copyTest = new CribMain();
  //      copyTest.makeCopy(testMain);
    //    assertSame(testMain,copyTest);
    }


    /**
     * This will check to make sure that anything placed into a player hand is no longer in the deck.
     * It will also check to make we have exactly one copy of each card from the origenal deck someplace in play
     *
     * input Deck mainDeck
     */
    @Test
    public void deal() throws Exception {
        //CribMain testMain = new CribMain();

        //make a testState here.

//        testMain.deal();
  //      Deck[] hand = new Deck[6];
    //    hand = getPlayerHand(0);
      //  int counter = 0;
 //       for(int i; i < mainDeck.size();i++)
   //     {
     //       counter++;
      //  }
        //assertEquals(counter,52-12);

    }


    /**
     * This is very close to the deal insted of checking the players hands we will be checking that whatever is
     * cut from the deck is actually taken out of the deck.
     *
     * Deck mainDeck
     */
    @Test
    public void cut() throws Exception
    {
        //CribMain testMain = new CribMain();
        //testMain.cut();
        //int counter = 0;
        //for(int i; i < mainDeck.size();i++)
        //{
        //    counter++;
        //}
        //assertEquals(counter,52-1);

    }

    /**
     * This will check that the correct amount of point are added to the correct players score.
     *
     * int player, int score
     */
    @Test
public void addScore() throws Exception {

        //CribMain testMain = new CribMain();
        //int firstScore = testMain.getScore();
        //testMain.addScore(0,10);
        //assertNotEquals(firstScore,testMain.getScore());

}

    /**
     * This will check our scoring algorithm is coming up with the correct score based on a players hand.
     *
     * int player, int[] playerHand
     */
    @Test
    public void scoreingAlgo() throws Exception {
        //int score =0;
        //CribMain testMain = new CribMain();
        //testMain.setPlayerHand(0,[3D,3H,5H,5H, 6H])
        //testMain.scoreingAlgo;
        //score = testMain.getScore(0);
        //assertEquals(score,4);
    }

    /**
     * This will check a players hand to see if they have a viable move. If not the GO! method will hapen.
     *
     * int player
     */
    @Test
    public void isGo() throws Exception {


    }


}