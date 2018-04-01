import java.util.LinkedList;
import java.util.Random;

/**
 * A Deck is a ordered collection of Cards. Cards can be drawn from the top
 * of the deck, and returned to the bottom of the deck.
 */
public class Deck
{
   private static int NUM_OF_CARDS = 44;   // 42 territory cards + 2 wild
   
   // private attributes
   
   private Card[] _completeDeck;            // the complete deck of 44 cards
   private LinkedList<Card> _currentDeck;   // the current deck/pile
   
   
   // Constructors
   
   /**
    * Constructs a deck of 44 Risk cards (42 territory cards + 2 wild).
    */
   public Deck()
   {
      buildDeck();      // builds the complete deck of cards to use
      shuffleDeck();    // shuffles the complete deck 
      createPile();     // creates a new 'pile' containing all the cards
   }
   
   
   //Accessors
   
   /**
    * Returns true if this deck is empty.
    * 
    * @return true if this deck is empty
    */
   public boolean isEmpty()
   {
      return _currentDeck.isEmpty();
   }
   
   
   // Mutators
   
   /**
    * Retrieves and removes the top card from this deck.
    * 
    * @return the top card in this deck
    */
   public Card drawCard()
   {
      return _currentDeck.removeFirst();
   }
   
   
   /**
    * Adds the specified card to the bottom of this deck.
    * 
    * @param cardReturned the card to return to this deck
    */
   public void returnCard(Card cardReturned)
   {
      _currentDeck.addLast(cardReturned);
   }
   
   
   // private methods
   
   /**
    * Builds the deck of 42 territory cards + 2 wild cards.
    */
   private void buildDeck()
   {
      _completeDeck = new Card[NUM_OF_CARDS];
      
      // get an array with each territory name
      Territory[] listOfTerrNames = Territory.values(); 
      
      // add to the deck one card for each territory
      for (int i = 0; i < listOfTerrNames.length; i++)
      {
         // give territory cards an army unit, cycling between the 3 non-wilds
         Card.ArmyUnit armyUnit;
         if (i % 3 == 0)
         {
             armyUnit = Card.ArmyUnit.INFANTRY;
         }
         else if (i % 3 == 1)
         {
            armyUnit = Card.ArmyUnit.CAVALRY;
         }
         else
         {
            armyUnit = Card.ArmyUnit.ARTILLERY;
         }
         
         // create the new card and add it in the deck
         _completeDeck[i] = new Card(listOfTerrNames[i], armyUnit); 
      }
      
      // add two wilds as the last two cards in the array
      _completeDeck[NUM_OF_CARDS -2] = new Card(null, Card.ArmyUnit.WILD);
      _completeDeck[NUM_OF_CARDS -1] = new Card(null, Card.ArmyUnit.WILD);
   }
   
   /**
    * Shuffles the complete deck of cards in preparation for creating a pile.
    * 
    * This algorithm is modern version of the Fisher-Yates shuffle, introduced 
    * by Richard Durstenfeld in 1964.  
    */
   private void shuffleDeck()
   {
      /* The Algorithm:
       * 
       * for i from length-1 down to 1 
       *    pick a random number j where 0 <= j <= i 
       *    swap array[j] and array[i]
       */
      
      Random rNG = new Random(); // the random number generator
      
      for (int i = NUM_OF_CARDS -1; i > 0; i--)
      {  
         // generate random # from 0 (inclusive ) to i+1 (exclusive)
         int j = rNG.nextInt(i+1);
         
         // swap _completeDeck[j] and _completeDeck[i]
         Card temp = _completeDeck[j];
         _completeDeck[j] = _completeDeck[i];
         _completeDeck[i] = temp;
      }
   }
   
   
   /**
    * Creates a pile of cards from the complete deck.
    */
   private void createPile()
   {
      _currentDeck = new LinkedList<>();
      for (Card card: _completeDeck)
      {
         _currentDeck.add(card);
      }
   }
}
