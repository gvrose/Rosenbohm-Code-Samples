/**
 * A Territory is a piece of land that is controlled by a player and contains a 
 * number of troops. A Territory can also return a list of its neighboring 
 * Territories.
 */
public enum Territory
{
   
   // North America's territories
   
   ALASKA
   {
      public Territory[] getNeighbors()
      {
         return new Territory[] {ALBERTA, KAMCHATKA, NORTHWEST_TERRITORY};
      }
   },
   
   ALBERTA
   {
      public Territory[] getNeighbors()
      {
         return new Territory[] {ALASKA, NORTHWEST_TERRITORY, ONTARIO,
               WESTERN_UNITED_STATES};
      }
   }, 
   
   CENTRAL_AMERICA
   {
      public Territory[] getNeighbors()
      {
         return new Territory[] {EASTERN_UNITED_STATES, VENEZUELA, 
               WESTERN_UNITED_STATES};
      }
   }, 
   
   EASTERN_UNITED_STATES
   {
      public Territory[] getNeighbors()
      {
         return new Territory[] {CENTRAL_AMERICA, ONTARIO, QUEBEC,
               WESTERN_UNITED_STATES};
      }
   }, 
   
   GREENLAND
   {
      public Territory[] getNeighbors()
      {
         return new Territory[] {ICELAND, NORTHWEST_TERRITORY, ONTARIO, QUEBEC};
      }
   }, 
   
   NORTHWEST_TERRITORY
   {
      public Territory[] getNeighbors()
      {
         return new Territory[] {ALASKA, ALBERTA, GREENLAND, ONTARIO};
      }
   }, 
   
   ONTARIO
   {
      public Territory[] getNeighbors()
      {
         return new Territory[] {ALBERTA, EASTERN_UNITED_STATES, GREENLAND, 
               NORTHWEST_TERRITORY, QUEBEC, WESTERN_UNITED_STATES};
      }
   }, 
   
   QUEBEC
   {
      public Territory[] getNeighbors()
      {
         return new Territory[] {EASTERN_UNITED_STATES, GREENLAND, ONTARIO};
      }
   }, 
   
   WESTERN_UNITED_STATES
   {
      public Territory[] getNeighbors()
      {
         return new Territory[] {ALBERTA, CENTRAL_AMERICA, 
               EASTERN_UNITED_STATES, ONTARIO};
      }
   },
   
   // South America's territories
   
   ARGENTINA
   {
      public Territory[] getNeighbors()
      {
         return new Territory[] {BRAZIL, PERU};
      }
   }, 
   
   BRAZIL
   {
      public Territory[] getNeighbors()
      {
         return new Territory[] {ARGENTINA, NORTH_AFRICA, PERU, VENEZUELA};
      }
   }, 
   
   PERU
   {
      public Territory[] getNeighbors()
      {
         return new Territory[] {ARGENTINA, BRAZIL, VENEZUELA};
      }
   }, 
   
   VENEZUELA
   {
      public Territory[] getNeighbors()
      {
         return new Territory[] {BRAZIL, CENTRAL_AMERICA, PERU};
      }
   },
   
   // Europe's territories 
   
   GREAT_BRITAIN
   {
      public Territory[] getNeighbors()
      {
         return new Territory[] {ICELAND, NORTHERN_EUROPE, SCANDANAVIA, 
               WESTERN_EUROPE};
      }
   }, 
   
   ICELAND
   {
      public Territory[] getNeighbors()
      {
         return new Territory[] {GREAT_BRITAIN, GREENLAND, SCANDANAVIA};
      }
   }, 
   
   NORTHERN_EUROPE
   {
      public Territory[] getNeighbors()
      {
         return new Territory[] {GREAT_BRITAIN, SCANDANAVIA, SOUTHERN_EUROPE, 
               UKRAINE, WESTERN_EUROPE};
      }
   }, 
   
   SCANDANAVIA
   {
      public Territory[] getNeighbors()
      {
         return new Territory[] {GREAT_BRITAIN, ICELAND, NORTHERN_EUROPE,
               WESTERN_EUROPE};
      }
   }, 
   
   SOUTHERN_EUROPE
   {
      public Territory[] getNeighbors()
      {
         return new Territory[] {EGYPT, MIDDLE_EAST, NORTH_AFRICA, 
               NORTHERN_EUROPE, UKRAINE, WESTERN_EUROPE};
      }
   },
   
   UKRAINE
   {
      public Territory[] getNeighbors()
      {
         return new Territory[] {AFGHANISTAN, MIDDLE_EAST, NORTHERN_EUROPE,
               SCANDANAVIA, SOUTHERN_EUROPE, URAL};
      }
   }, 
   
   WESTERN_EUROPE
   {
      public Territory[] getNeighbors()
      {
         return new Territory[] {GREAT_BRITAIN, NORTH_AFRICA, NORTHERN_EUROPE,
               SOUTHERN_EUROPE};
      }
   },
   
   // Africa
   CONGO
   {
      public Territory[] getNeighbors()
      {
         return new Territory[] {EAST_AFRICA, EGYPT, NORTH_AFRICA, 
               SOUTH_AFRICA};
      }
   }, 
   
   EAST_AFRICA
   {
      public Territory[] getNeighbors()
      {
         return new Territory[] {CONGO, EGYPT, MADAGASCAR, MIDDLE_EAST, 
               NORTH_AFRICA, SOUTH_AFRICA};
      }
   }, 
   
   EGYPT
   {
      public Territory[] getNeighbors()
      {
         return new Territory[] {EAST_AFRICA, MIDDLE_EAST, NORTH_AFRICA,
               SOUTHERN_EUROPE};
      }
   }, 
   
   MADAGASCAR
   {
      public Territory[] getNeighbors()
      {
         return new Territory[] {EAST_AFRICA, SOUTH_AFRICA};
      }
   }, 
   
   NORTH_AFRICA
   {
      public Territory[] getNeighbors()
      {
         return new Territory[] {BRAZIL, CONGO, EAST_AFRICA, EGYPT, 
               SOUTHERN_EUROPE, WESTERN_EUROPE};
      }
   }, 
   
   SOUTH_AFRICA
   {
      public Territory[] getNeighbors()
      {
         return new Territory[] {CONGO, EAST_AFRICA, MADAGASCAR};
      }
   },
   
   // Asia
   AFGHANISTAN
   {
      public Territory[] getNeighbors()
      {
         return new Territory[] {CHINA, INDIA, MIDDLE_EAST, UKRAINE, URAL};
      }
   }, 
   
   CHINA {
      public Territory[] getNeighbors()
      {
         return new Territory[] {AFGHANISTAN, INDIA, MONGOLIA, SIBERIA, SIAM,
               URAL};
      }
   }, 
   
   INDIA
   {
      public Territory[] getNeighbors()
      {
         return new Territory[] {AFGHANISTAN, CHINA, MIDDLE_EAST, SIAM};
      }
   }, 
   
   IRKUTSK
   {
      public Territory[] getNeighbors()
      {
         return new Territory[] {KAMCHATKA, MONGOLIA, SIBERIA, YAKUTSK};
      }
   }, 
   
   JAPAN
   {
      public Territory[] getNeighbors()
      {
         return new Territory[] {KAMCHATKA, MONGOLIA};
      }
   }, 
   
   KAMCHATKA
   {
      public Territory[] getNeighbors()
      {
         return new Territory[] {ALASKA, IRKUTSK, JAPAN, MONGOLIA, YAKUTSK};
      }
   }, 
   
   MIDDLE_EAST
   {
      public Territory[] getNeighbors()
      {
         return new Territory[] {AFGHANISTAN, EAST_AFRICA, EGYPT, INDIA, 
               SOUTHERN_EUROPE, UKRAINE};
      }
   }, 
   
   MONGOLIA
   {
      public Territory[] getNeighbors()
      {
         return new Territory[] {CHINA, IRKUTSK, JAPAN, KAMCHATKA, SIBERIA};
      }
   }, 
   
   SIAM
   {
      public Territory[] getNeighbors()
      {
         return new Territory[] {CHINA, INDIA, INDONESIA};
      }
   }, 
   
   SIBERIA
   {
      public Territory[] getNeighbors()
      {
         return new Territory[] {CHINA, IRKUTSK, MONGOLIA, URAL, YAKUTSK};
      }
   }, 
   
   URAL
   {
      public Territory[] getNeighbors()
      {
         return new Territory[] {AFGHANISTAN, CHINA, SIBERIA, UKRAINE};
      }
   }, 
   
   YAKUTSK
   {
      public Territory[] getNeighbors()
      {
         return new Territory[] {IRKUTSK, KAMCHATKA, SIBERIA};
      }
   },
   
   // Australia's territories
   
   EASTERN_AUSTRALIA
   {
      public Territory[] getNeighbors()
      {
         return new Territory[] {NEW_GUINEA, WESTERN_AUSTRALIA};
      }
   }, 
   
   INDONESIA
   {
      public Territory[] getNeighbors()
      {
         return new Territory[] {NEW_GUINEA, WESTERN_AUSTRALIA, SIAM};
      }
   }, 
   
   NEW_GUINEA
   {
      public Territory[] getNeighbors()
      {
         return new Territory[] {EASTERN_AUSTRALIA, INDONESIA, 
               WESTERN_AUSTRALIA};
      }
   }, 
   
   WESTERN_AUSTRALIA
   {
      public Territory[] getNeighbors()
      {
         return new Territory[] {EASTERN_AUSTRALIA, INDONESIA, NEW_GUINEA};
      }
   };
   
   
   
   // private attributes
   
   private Player _occupant;        // the player occupying the territory
   private int _troopCount;         // the # of troops on the territory
   
   
   // Constructors 
   
   
   /**
    * Constructs a territory with no occupant and 0 troops.
    */
   private Territory()
   {
      _occupant = null;
      _troopCount = 0;
   }
   
   
   // Accessors
   
   
   /**
    * Returns the player who currently occupies this territory.
    * 
    * @return the player who currently occupies this territory
    */
   public Player getOccupant()
   {
      return _occupant;
   }
   
   
   /**
    * Returns true if the specified player owns this territory.
    * 
    * @param player the specified player
    * @return true if the specified player owns this territory
    */
   public boolean playerOwnsTerritory(Player player)
   {
      return _occupant == player;
   }
   
   
   /**
    * Returns the number of troops on this territory.
    * 
    * @return the number of troops on this territory
    */
   public int getTroopCount()
   {
      return _troopCount;
   }
   
   
   /**
    * Returns an array of this territory's neighboring territories.
    * 
    * @return an array of this territory's neighboring territories
    */
   public Territory[] getNeighbors()
   {
      return new Territory[0]; // the default returns an empty array
   }
   
   
   /**
    * Returns true if the specified other territory is a neighbor of this 
    * territory.
    * 
    * @param potentialNeighbor the specified other territory
    * @return true if the specified other territory is a neighbor of this 
    *    territory
    */
   public boolean isNeighbor(Territory potentialNeighbor)
   {
      boolean isNeighbor = false;
      Territory[] neighbors = this.getNeighbors();
      
      // iterate through this territory's neighbors and compare 
      for (int i = 0; i < neighbors.length && !isNeighbor; i++)
      {
         if (neighbors[i] == potentialNeighbor)
         {
            isNeighbor = true; 
         }
      }
      
      return isNeighbor;
   }
   
   
   /**
    * Returns true if the specified player can attack from this territory (i.e.
    * the player owns this territory and has sufficient troops to leave at least
    * one behind).
    * 
    * @param player the specified player
    * @return true if the specified player can attack from this territory 
    */
   public boolean validAttackFrom(Player player)
   {
      return playerOwnsTerritory(player) && getTroopCount() > 1;
   }
   
   
   /**
    * Returns true if this territory can attack the specified target territory
    * (i.e. the territories have different owners and are neighbors).
    * 
    * @param target the specified target territory
    * @return true if this territory can attack the specified target territory
    */
   public boolean validAttackTo(Territory target)
   {
      return !this.playerOwnsTerritory(target.getOccupant())
            && this.isNeighbor(target);
   }
   
   
   /**
    * Returns true if the specified player can transfer a specified quantity of
    * troops from this territory (i.e. the player owns this territory and there
    * are sufficient troops to leave behind at least one).
    * 
    * @param player the specified player
    * @param quantity the specified quantity
    * @return true if the specified player can transfer a specified quantity of
    *    troops from this territory
    */
   public boolean validTransferFrom(Player player, int quantity)
   {
      return playerOwnsTerritory(player) && getTroopCount() - quantity >= 1;
   }
   
   
   /**
    * Returns true if troops can be transfered from this territory to the 
    * specified destination territory (i.e. the territories are owned by the 
    * same player and are neighbors). 
    * 
    * @param destination the specified other territory
    * @return true if troops can be transfered from this territory to the 
    *    specified other territory
    */
   public boolean validTransferTo(Territory destination)
   {
      return this.playerOwnsTerritory(destination.getOccupant()) 
            && this.isNeighbor(destination);
   }
   
   
   // Mutators
   
   
   /**
    * Sets the occupant of this territory to the specified player.
    * 
    * @param newOccupant the new occupant
    */
   public void setOccupant(Player newOccupant)
   {
      _occupant = newOccupant;
   }
   
   
   /**
    * Sets the number of troops on this territory to the specified value.
    * 
    * @param newCount the new number of troops
    */
   public void setTroopCount(int newCount)
   {
      _troopCount = newCount;
   }
   
   
   /**
    * Adds the specified quantity of troops to this territory.
    * 
    * @param quantity the specified quantity of troops
    */
   public void addTroops(int quantity)
   {
      _troopCount += quantity;
   }
   
   
   /**
    * Removes the specified quantity of troops from this territory.
    * 
    * @param quantity the specified quantity of troops
    * @throws IllegalArgumentException if the quantity to remove is greater than
    *    the current troop count
    */
   public void removeTroops(int quantity)
   {
      if (quantity > _troopCount)
      {
         throw new IllegalArgumentException("Cannot remove more troops than"
               + "are currently on this Territory.");
      }
      _troopCount -= quantity;
   }
}
