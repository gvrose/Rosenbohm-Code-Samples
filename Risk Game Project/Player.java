import java.awt.Color;
import java.util.ArrayList;

public class Player
{
   // private attributes
   
   private Color _color;            // the player's color
   private ArrayList<Card> _cards;  // the player's list of cards
   private int _numOfTerritories;   // the player's # of territories occupied
   
   
   // Constructors
   
   public Player(Color color)
   {
      _color = color;
   }
   
   
   // Accessors
   
   /**
    * Returns the color of this player.
    * 
    * @return the color of this player
    */
   public Color getColor()
   {
      return _color;
   }
   
   
   /**
    * Returns the list of this player's cards.
    * 
    * @return the list of this player's cards
    */
   public ArrayList<Card> getCards()
   {
      return _cards;
   }
   
   
   /**
    * Returns true if this player can trade in cards from their current hand.
    * 
    * @return true if this player can trade in cards from their current hand
    */
   public boolean hasTradeIn()
   {
      boolean hasTradeIn = false;
      int infantryQty = 0;
      int cavalryQty = 0;
      int artilleryQty = 0;
      
      // a player must have at least 3 cards to be able to trade in 
      if (_cards.size() >= 3)
      {
         // get a count of the ArmyUnits on the player's cards
         for (Card card: getCards())
         {
            switch (card.getArmyUnit())
            {
               case INFANTRY:
                  infantryQty++;
                  break;
                  
               case CAVALRY:
                  cavalryQty++;
                  break;
                  
               case ARTILLERY:
                  artilleryQty++;
                  break;
                  
               case WILD:
                  infantryQty++;
                  cavalryQty++;
                  artilleryQty++;
                  break;
                  
               default:
                  break;
            }
         }
         
         // a valid trade is one of each card type, or 3 of one type
         if ((infantryQty >=1 && cavalryQty >=1 && artilleryQty >=1)
               || infantryQty >=3
               || cavalryQty >=3
               || artilleryQty >=3)
         {
            hasTradeIn = true;
         }
      }
      return hasTradeIn;
   }
   
   
   /**
    * Returns true if this player must trade in cards from their current hand.
    * 
    * @return true if this player must trade in cards from their current hand
    */
   public boolean mustTradeIn()
   {
      return _cards.size() >= 5;
   }
   
   
   /**
    * Returns the number of territories occupied by this player.
    * 
    * @return the number of territories occupied by this player
    */
   public int getNumOfTerritories()
   {
      return _numOfTerritories;
   }
   
   
   /**
    * Returns the number of troops this player may currently place.
    * 
    * @return the number of troops this player may currently place
    */
   public int getTroopsToPlace()
   {
      int troopQty = 3; // the minimum # of troops
      
      /* player gets 1 troop per every 3 territories controlled; therefore the 
       * minimum # of territories to increase base troopQty is 12
       */
      if (_numOfTerritories >= 12) 
      {
         troopQty = _numOfTerritories / 3;
      }
      
      return troopQty;
   }
   
   
   // Mutators
   
   /**
    * Increases the number of territories this player occupies by one.
    */
   public void gainTerritory()
   {
      _numOfTerritories++;
   }
   
   /**
    * Decreases the number of territories this player occupies by one.
    */
   public void loseTerritory()
   {
      _numOfTerritories--;
   }
   
   
   /**
    * Adds the specified card to this player's list of cards.
    * 
    * @param newCard the new card to add
    */
   public void addCard(Card newCard)
   {
      _cards.add(newCard);
   }
   
   
   /**
    * Retrieves and removes the specified card from this player's list of cards.
    * 
    * @param cardToRemove the card to remove
    * @return the card that was removed
    */
   public Card discard(Card cardToRemove)
   {
      return _cards.remove(_cards.indexOf(cardToRemove));
   }
}