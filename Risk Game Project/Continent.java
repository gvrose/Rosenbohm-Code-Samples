/**
 * A Continent is a grouping of Territories, and it has a bonus for occupying
 * all of its Territories.
 */
public enum Continent
{
   /**
    * The continent of Africa. Has a bonus of 3.
    */
   AFRICA(new Territory[] {Territory.CONGO, Territory.EAST_AFRICA, 
         Territory.EGYPT, Territory.MADAGASCAR, Territory.NORTH_AFRICA, 
         Territory.SOUTH_AFRICA},
         3), // has a bonus of 3
   
   /**
    * The continent of Asia. Has a bonus of 7.
    */
   ASIA(new Territory[] {Territory.AFGHANISTAN, Territory.CHINA, 
         Territory.INDIA, Territory.IRKUTSK, Territory.JAPAN, 
         Territory.KAMCHATKA, Territory.MIDDLE_EAST, Territory.MONGOLIA, 
         Territory.SIAM, Territory.SIBERIA, Territory.URAL, Territory.YAKUTSK},
         7), // has a bonus of 7
   
   /**
    * The continent of Australia. Has a bonus of 2.
    */
   AUSTRALIA(new Territory[] {Territory.EASTERN_AUSTRALIA, Territory.INDONESIA, 
         Territory.NEW_GUINEA, Territory.WESTERN_AUSTRALIA},
         2), // has a bonus of 2
   
   /**
    * The continent of Europe. Has a bonus of 5.
    */
   EUROPE(new Territory[] {Territory.GREAT_BRITAIN, Territory.ICELAND, 
         Territory.NORTHERN_EUROPE, Territory.SCANDANAVIA, 
         Territory.SOUTHERN_EUROPE, Territory.UKRAINE, 
         Territory.WESTERN_EUROPE},
         5), // has a bonus of 5
   
   /**
    * The continent of North America. Has a bonus of 5.
    */
   NORTH_AMERICA(new Territory[] {Territory.ALASKA, Territory.ALBERTA, 
         Territory.CENTRAL_AMERICA, Territory.EASTERN_UNITED_STATES, 
         Territory.GREENLAND, Territory.NORTHWEST_TERRITORY, Territory.ONTARIO, 
         Territory.QUEBEC, Territory.WESTERN_UNITED_STATES}, 
         5), // has a bonus of 5
   
   /**
    * The continent of South America. Has a bonus of 2. 
    */
   SOUTH_AMERICA(new Territory[] {Territory.ARGENTINA, Territory.BRAZIL, 
         Territory.PERU, Territory.VENEZUELA}, 
         2); // has a bonus of 2
   
   
   
   // private attributes
   
   
   private Territory[] _territories;   // the continent's contained territories
   private int _bonus;                 // the continent's bonus
   
   
   // Constructors
   
   
   /**
    * Constructs a continent with a specified array of territories that it
    * contains and a bonus.
    * 
    * @param territories the array of territories this continent contains
    * @param bonus this continent's bonus
    */
   private Continent(Territory[] territories, int bonus)
   {
      _territories = territories;
      _bonus = bonus;
   }
   

   // Accessors
   
   
   /**
    * Returns the bonus that a specified player gets from this continent.
    * 
    * @param playerToCheck the specified player
    * 
    * @return the bonus that the player receives
    */
   public int calculateBonus(Player playerToCheck)
   {
      int bonusToGive = 0;
      boolean playerControlsCont = true;
      
      // iterate through each territory and compare the occupant to the player
      for (int i = 0; i < _territories.length && playerControlsCont; i++)
      {
         // if they don't occupy a territory, set flag to false 
         if (! _territories[i].getOccupant().equals(playerToCheck))
         {
            playerControlsCont = false;
         }
      }
      
      // only give the bonus to the player if they control the continent
      if (playerControlsCont)
      {
         bonusToGive += _bonus;
      }
      return bonusToGive;
   }
}
