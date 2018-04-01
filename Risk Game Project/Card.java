
/**
 * A Card used in the game of Risk. Each card has an associated territory and 
 * a troop denomination.
 */
public class Card
{
   /**
    * The constant set of card army-units.
    */
   public enum ArmyUnit {INFANTRY, CAVALRY, ARTILLERY, WILD};
   
   
   // private attributes
   
   
   private Territory _territory;    // the territory on the card
   private ArmyUnit _unit;          // the army unit on the card
   
   
   // Constructors
   

   /**
    * Constructs a card with a specified territory and army unit.
    * 
    * @param territory the territory on the card
    * @param armyUnit the army unit on the card
    */
   public Card(Territory territory, ArmyUnit armyUnit)
   {
      _territory = territory;
      _unit = armyUnit;
   }
   
   
   // Accessors
   
   
   /**
    * Returns the territory name on this card.
    * 
    * @return the territory name on this card
    */
   public Territory getTerritory()
   {
      return _territory;
   }
   
   
   /**
    * Returns the army unit on this card.
    * 
    * @return the army unit on this card
    */
   public ArmyUnit getArmyUnit()
   {
      return _unit;
   }
}
