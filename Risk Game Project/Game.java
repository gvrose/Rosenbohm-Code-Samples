import java.awt.Color;
import java.util.ArrayList;


/**
 * 
 * 
 *
 */
public class Game 
{
	// Number of cards in a deck, there are 44 in the original version of RISK
	static final int SIZE_OF_DECK = 44;
	static final int NUMBER_OF_TERRITORIES = 42; // Number of territories
	
	// Private variables	
	int _currentTradeInBonus = 0; // The current card trade in bonus
	
	int[] _gameSetUpTroops; // An array to hold the number of set up troops
	int _turnSetUpTroops = 0;

	ArrayList<Player> _playerList = null; // The list of players
	
	Player _currentPlayer = null; // The player whose turn it is currently
	
	boolean _playerEarnedCard = false;
	
	static Deck _theDeck = null; // The game deck
	
	
	
	/**
	 * 
	 * @param args
	 */
	public Game() 
	{
      // Create the deck
      _theDeck = new Deck();
		
		
	    	    
        
        // WAITING FOR GUI COMMUNICATION 
		// Iterate through each player and allow them to place a troop on any territory that
		// they possess until all initial troops are placed
		
		
		// GAME PLAY
        
        // while (list of players size > 1)
		// for each player in the playerlist
			
		// 		TURN SET-UP PHASE
		//		Determine number of troops to place
		//		determine if player wants to/has to trade in (if number of cards >= 5, must trade in)
		// 		(TBD - whether there will be multiple trade-ins)
			
		//		Troops from trade-in
		// 		if (trade in)
		//			# troops to place += TradeInCards(cards to trade in as chosen by player)
			
		//		Troops from territories occupied
		// 		# troops to place = # troops to place + player.getTroopsToPlace();
			
		//		Troops from continent possession bonus
		//		for each continent determine if player possesses it (implementation unsure)
			
		// 		Have player place troops for set up
	        //		while (# troops to place != 0)
	        //			place troop on territory (check if territory is occupied by player)
	        //			increment troop count on that territory
	        
	        
		
	        
	        // 			TRANSFER PHASE
	        //			choose origin territory (make sure it is occupied by player)
	        //			determine number of troops to transfer (max = number of troops on territory - 1)
	        //			choose destination territory (make sure it is occupied by player and a neighboring territory to origin terr)
	        //			transferTroops(origin terr, destination terr, number of troops);
	        
	        //			Determine if player receives card
	        //			if (attack success)
	        //				current player.addCard(theDeck.drawCard());
	        //                              /** if the terriroty attacked is the last territory of the defending player, get all his cards */
        
        // End while
        
        // If there is a winner or if you quit mid game
        // Display closing messages 
		
	}
	
	/**
	 * Sets up the game given the number of players
	 * 
	 * @param numOfPlayers
	 * 
	 * @return the number of total troops still to place (number of loops)
	 */
	public int setUpGame(int numOfPlayers)
	{
	 	   
	   // VARIABLES USED FOR GAME SET UP
      int numberOfTroops_SetUp = 0; // The number of troops that each player has to place during game set up
      Card cardDrawn_SetUp = null;
	   
	   // Constructing the players
      _playerList = new ArrayList<Player>(numOfPlayers);
      Color[] colorList = {Color.red, Color.green, Color.blue, Color.yellow, Color.black, Color.gray};
       
      // For the purpose of setting up the game we need a list of integers corresponding to the list of
      // players that holds the number of troops that the players initially get to place during game set up
      _gameSetUpTroops = new int[numOfPlayers];
       
     
      // Determine number of troops to place for set up (same for each player), depends on number of players
      if (numOfPlayers == 2)
      {
        numberOfTroops_SetUp = 40;
      }
      else if (numOfPlayers == 3)
      {
        numberOfTroops_SetUp = 35;
      }
      else if (numOfPlayers == 4)
      {
        numberOfTroops_SetUp = 30;
      }
      else if (numOfPlayers == 5)
      {
        numberOfTroops_SetUp = 25;
      }
      else if (numOfPlayers == 6)
      {
        numberOfTroops_SetUp = 20;
      }
       
      // Create players and assign colors and initialize number of troops to 
      // for each player to place to be pre-figured out number
      for (int i = 0; i < numOfPlayers; i++)
      {
         _playerList.add(i, new Player(colorList[i]));  
         _gameSetUpTroops[i] = numberOfTroops_SetUp;
      }
      
       
       
      // Randomly assign territories to players by using the deck of cards and
      // automatically put 1 troop in the territory
      for (int i = 0; i < SIZE_OF_DECK; i++)
      {
         // Draw a card
         cardDrawn_SetUp = _theDeck.drawCard();
        
         // While the card is a wild card, return card and draw another
         while(cardDrawn_SetUp.getTerritory() == null)
         {
            _theDeck.returnCard(cardDrawn_SetUp);
            cardDrawn_SetUp = _theDeck.drawCard();
         }
       
         // setOccupant of the current territory on card to be the player at Players[loop index % number of players]
         cardDrawn_SetUp.getTerritory().setOccupant(_playerList.get(i % numOfPlayers));
         
         // Initialize the number of troops in the territory to be 1 and 
         // decrement the number of set up troops for that player
         cardDrawn_SetUp.getTerritory().addTroops(1);
         _gameSetUpTroops[i % numOfPlayers] = _gameSetUpTroops[i % numOfPlayers] - 1;         
         
         // Increment number of territories owned by that player
         _playerList.get(i % numOfPlayers).gainTerritory();
       
         // Return the card to the deck
         _theDeck.returnCard(cardDrawn_SetUp);
      }
      
      // Set the current player to be the first player in the list of players
      _currentPlayer = _playerList.get(0);
      
      
	   // Return the number of total troops left to place
	   return (numberOfTroops_SetUp * numOfPlayers) - NUMBER_OF_TERRITORIES;
	}
	
	
	/**
	 * Sets up the next turn for the next player, does this pull from the trade
	 * in stage? Which comes first? 
	 * 
	 * Option: Don't have GUI keep track of the player but just call set up turn
	 * and have the setUpTurn get the next player...?
	 * 
	 * @param cards the cards to trade-in if the player is trading in, else it 
	 * is null
	 * 
	 * @return the number of troops to place the player has for their turn
	 * 
	 */
	public int setUpTurn(ArrayList<Card> cards)
	{
	   // Get the number of troops to place based on the number of territories
	   // the current player has
	   int troopsToPlace = _currentPlayer.getTroopsToPlace();
	   
	   
	   // If the player is trading in, trade in the cards add the trade in bonus
	   // to the number of troops to place
	   if (!cards.equals(null))
	   {
	      troopsToPlace += tradeInCards(cards);
	   }
	   
	   // Add any continent bonus for the current player to the number of troops
	   // to place
	   troopsToPlace += calculateContinentBonus();
	   
	   // Set the turnSetUpTroops to be the number of troops to place
	   _turnSetUpTroops = troopsToPlace;
	   
	   return troopsToPlace;
	}
	
	
	/**
	 * Returns the number of troops the current player has to place
	 * @return
	 */
	public int getCurrentTroopsToPlace()
	{	   
	   
	   // Add the number of troops for the turn set up and number of game set up 
	   // troops to account for either state of the game being in game set up 
	   // or in turn set up
	   return _turnSetUpTroops + _gameSetUpTroops[_playerList.indexOf(_currentPlayer)];
	}
	
	
	
	/**
	 * Places a single troop in a territory
	 * 
	 * @param destinationTerr the territory to place the troop in
	 * @param gameSetUp indicates whether placing the troop is part of game set up
	 */
	public void placeTroop(Territory destinationTerr, boolean gameSetUp)
	{
	   // The occupant of the territory
	   Player occupant = destinationTerr.getOccupant();
	   
	   // If in the game set up stage, subtract troop placement from set up 
	   // troops
	   if (gameSetUp)
	   {
	      _gameSetUpTroops[_playerList.indexOf(occupant)] = _gameSetUpTroops[_playerList.indexOf(occupant)] - 1;
	   }
	   
	   // Decrement the number of troops to place
	   _turnSetUpTroops -= 1;
	   
	   
	   destinationTerr.addTroops(1);
	   
	}
	
	
	
	/**
	 * Performs a single attack between two territories.
	 * 
	 * @param attackingTerr the attacking territory
	 * @param defendingTerr the defending territory
	 * 
	 * @return a string with a message about rolls and attack
	 */
	public String attack(Territory attackingTerr, Territory defendingTerr)
	{

	   int attackNumOfDice = 3; // Default # attack dice to roll to be 3
	   int defendNumOfDice = 2; // Default # defend dice to roll to be 2
	   
	   // Variables to hold references to occupants involved in attack
	   Player attackingPlayer = attackingTerr.getOccupant();
	   Player defendingPlayer = defendingTerr.getOccupant();
	   
	   ArrayList<Integer> attackRoll = null; // Array of attacking player's roll
      ArrayList<Integer> defendingRoll = null; // Array of defending player's roll
      
      int[] troopLosses = {0,0}; // Number of troops lost to defender
	   
      
      // Determine number of dice to roll for attacking player, always three
      // unless the number of troops on attacking territory is less than 4
      if (attackingTerr.getTroopCount() < 4)
      {
         attackNumOfDice = attackingTerr.getTroopCount() - 1;
      }
      
      // Determine number of dice to roll for defending player, always 2 unless
      // number of troops on defending territory is 1
      if (defendingTerr.getTroopCount() == 1)
      {
         defendNumOfDice = 1;
      }
      
      // Roll the dice
      attackRoll = rollDice(attackNumOfDice);
      defendingRoll = rollDice(defendNumOfDice);
	   
	   // Calculate troop losses
      troopLosses = calcTroopLoss(attackRoll, defendingRoll);
       
      //Adjust troops depending on attack losses
      defendingTerr.removeTroops(troopLosses[0]);
      attackingTerr.removeTroops(troopLosses[1]);
              
      
      
      // If attacking player has conquered defending territory, adjust
      if (defendingTerr.getTroopCount() == 0)
      {
         
         // Change occupancy of defending territory
         defendingTerr.setOccupant(attackingTerr.getOccupant());
         
         // Adjust number of player's territories
         attackingTerr.getOccupant().gainTerritory();
         defendingTerr.getOccupant().loseTerritory();
 
         // Indicate that player gets a card
         _playerEarnedCard = true;
         
         // Move minimum number of troops over from attacking territory
         transferTroops(attackingTerr, defendingTerr, attackNumOfDice);
         
         // Determine if the defending player is completely conquered from game
         if (defendingPlayer.getNumOfTerritories() == 0)
         {
            // Give defending player's cards to attacking player if they have
            // a card
            if (!defendingPlayer.getCards().equals(null))
            {
               for (Card aCard: defendingPlayer.getCards())
               {
                  attackingPlayer.addCard(aCard);
               }
            }
            
            // Remove defending player from list of players
            _playerList.remove(defendingPlayer);
         }
         
      }   
	   
	   
      // Return a string with a message of the outcome	   
	    
	   return "Attack Roll: " + attackRoll + "\nDefending Roll: " 
	         + defendingRoll + "\n\nAttacking Losses: " + troopLosses[1] 
	         + "\nDefensive Losses: " + troopLosses[0];
	}
	
	
	
	/**
	 * Determines if the number of troops to transfer is valid defending on the
	 * parameters
	 * 
	 * @param origin the territory that the transfer originates from
	 * @param numToTransfer the number of troops to transfer
	 * @param endOfTurn true if the transfer is occurring at the end of the turn
	 * 
	 * @return true if the transfer is valid
	 */
	public boolean validNumToTransfer(Territory origin, int numToTransfer, boolean endOfTurn)
	{
	   boolean validTransfer = true;
	   
	   if (endOfTurn && numToTransfer >= origin.getTroopCount())
	   {
	      validTransfer = false;
	   }
	   else
	   {
	      // We know that the number of troops must be greater than 1 since the
	      // attack is valid, so we must check the other cases
	      
	      // If the number to transfer is 0, it is an invalid transfer 
	      if (numToTransfer == 0)
	      {
	         validTransfer = false;
	      }
	      // If the number of troops is 2, then you must transfer 1
	      else if (origin.getTroopCount() == 2 && numToTransfer != 1)
	      {
	         validTransfer = false;
	      }
	      // If the number of troops is 3, then you must transfer 2
	      else if (origin.getTroopCount() == 3 && numToTransfer != 2)
	      {
	         validTransfer = false;
	      }
	      // We know that if the number of troops on the territory is greater 
	      // than 3, the number to transfer must be at least 3
	      else if (origin.getTroopCount() > 3 && numToTransfer < 3)
	      {
	         validTransfer = false;
	      }
	         
	   }
	   
	   return validTransfer;
	   
	   
	}
	
	
	/**
	 * Gets the current player
	 * 
	 * @return the current player
	 */
	public Player getCurrentPlayer()
	{
	   return _currentPlayer;
	}
	
	
	
	/**
	 * Gets the player whose turn it is next (if the game is in the set up state
	 * then it gets the player that needs to place a troop next)
	 * 
	 * @param gameSetUp indicates whether you are in setting up the game step
	 * 
	 * @return the next player (if setting up the game it gives the player to place troop next)
	 */
	public Player getNextPlayer(boolean gameSetUp)
	{
	   
	   // If the current player has won at least one attack, add a card to their
	   // hand of cards
	   if (_playerEarnedCard)
	   {
	      _currentPlayer.addCard(_theDeck.drawCard());
	      
	      // Set flag to false
	      _playerEarnedCard = false;
	      
	   }
	   
	   
	   Player nextPlayer = _playerList.get((_playerList.indexOf(_currentPlayer) + 1) % _playerList.size());
	   
	   
	   // If setting up the game, get next player gives 
	   if (gameSetUp)
	   {
	      while (_gameSetUpTroops[_playerList.indexOf(_currentPlayer)] == 0)
	      {
	         nextPlayer = _playerList.get((_playerList.indexOf(nextPlayer) + 1) % _playerList.size());
	      }
	   }
	   
	   // Set instance variables for player
	   _turnSetUpTroops = 0;
	   _currentPlayer = nextPlayer;
	   
	   return nextPlayer;
	}
	
	
	/**
	 * Determines if the game has a winner 
	 * 
	 * @return true if the game has a winner
	 */
	public boolean hasWinner()
	{
	   return _playerList.size() == 1;
	}
	
	
	/**
	 * Calculates the troop bonus of the _currentPlayer from any continents that
	 * they occupy
	 * 
	 * @return the number of bonus troops for any continent they occupy
	 */
	private int calculateContinentBonus()
	{
	   return Continent.AFRICA.calculateBonus(_currentPlayer) + 
	         Continent.ASIA.calculateBonus(_currentPlayer) + 
	         Continent.AUSTRALIA.calculateBonus(_currentPlayer) + 
	         Continent.EUROPE.calculateBonus(_currentPlayer) + 
	         Continent.NORTH_AMERICA.calculateBonus(_currentPlayer) + 
	         Continent.SOUTH_AMERICA.calculateBonus(_currentPlayer);
	}
	
	
	
	/**
    * Trades in cards from the player's hand. Checks to see if the territory is one
    * occupied by the player, if so increments the troop count on that territory.
    * 
    * (Pre-condition: the cards to be traded in have been determined to be a valid trade in already.)
    * 
    * @param cards a list of three cards to be traded in by the player
    * @return the trade in troop bonus from trading in cards
    */
   private int tradeInCards(ArrayList<Card> cards)
   {
      // Variable to hold the territory associated with card
      Territory cardTerr = null;
      
      // Check if the associated territories of the cards are occupied by player
      for (Card card: cards)
      {
         // Set variable to be the territory associated with card
         cardTerr = card.getTerritory();
         
         // For each card to trade in check to see if the player occupies the territory
         if (cardTerr.getOccupant().equals(_currentPlayer))
         {
            // Increment troop count on territory if occupied by player
            cardTerr.setTroopCount(cardTerr.getTroopCount() + 2);
         }
         
         // Return the card to the deck and discard card from player's hand
         _theDeck.returnCard(card);
         _currentPlayer.discard(card);
            
      }
      
      // Return the current trade in bonus
      return calculateTradeInBonus();
   }
   
   
   /**
    * Calculates the trade-in troop bonus depending on what the last trade in bonus was.
    * 
    * @return the number of troops for the trade-in bonus.
    */
   private int calculateTradeInBonus()
   {
      if (_currentTradeInBonus == 0)
      {
         _currentTradeInBonus += 4;
      }
      else if(_currentTradeInBonus <= 10)
      {
         _currentTradeInBonus += 2;
      }
      else if(_currentTradeInBonus == 12)
      {
         _currentTradeInBonus += 3;
      }
      else // If anything else (>= 15) increment by 5
      {
         _currentTradeInBonus += 5;
      }
      
      return _currentTradeInBonus;
   }
   
   
   /**
    * Given a number of dice to roll (1, 2 or 3 dice), rolls the dice and 
    * assigns a value between 1 and 6 for each dice.
    * 
    * @param numOfDice the number of dice being rolled
    * @return the list of values rolled by the dice (highest --> lowest)
    */
   private ArrayList<Integer> rollDice(int numOfDice)
   {
      // The list of dice rolls
      ArrayList<Integer> diceRolls = new ArrayList<Integer>();
      
      // The value rolled by a die
      Integer dice = null;
      
      // Boolean to track if the die value had been added to list of values
      boolean inserted = false;
      
      // Roll the number of dice indicated by numOfDice parameter
      for (int i = 0; i < numOfDice; i ++)
      {
         // Generate the value of the roll (1 - 6)
         dice = new Integer((int)(Math.random()*6) + 1);
         
         // Set inserted to false for this specific die
         inserted = false;
         
         // Insert the roll value in descending order (highest --> lowest)
         for (int j = 0; j < diceRolls.size() - 1 && !inserted; j++)
         {
            // If value of die is greater than value at index j, insert value
            if (dice >= diceRolls.get(j))
            {
               diceRolls.add(j, dice);
               inserted = true;
            }
         }
         
         // If value hasn't been inserted still, append to end
         if (!inserted)
         {
            diceRolls.add(dice);
         }
      }
      
      return diceRolls;
   }
   
   
   /**
    * Calculates the troop loss for the attacker and defender.
    * 
    * (Pre-condition: we know that the rolls are sorted in descending order for ease of comparison)
    * 
    * @param attackRoll the roll of the attacking player
    * @param defendRoll the roll of the defending player
    * 
    * @return the list of losses for both attacking and defending player 
    * ( index 0 - defending player's loss, index 1 - attacking player's loss)
    */
   private int[] calcTroopLoss(ArrayList<Integer> attackRoll, ArrayList<Integer> defendRoll)
   {
      // Array to hold the number of troop losses to each player (0 - defending player, 1 - attacking player)
      int[] losses = new int[2];
      
      // The number of comparisons between dice is limited by the number of dice rolled by defending player
      for (int i = 0; i < defendRoll.size(); i++)
      {
         // If the attacking roll for comparison is greater than defender's, the defender loses a troop
         if (attackRoll.get(i) > defendRoll.get(i))
         {
            // Increment number of troops lost to defender
            losses[0] = losses[0] + 1;
         }
         else // The defender's value is greater than or equal to attacker, so increment troop loss for attacker
         {
            // Increment number of troops lost to attacker
            losses[1] = losses[1] + 1;
         }
      }
      
      // The number of troops lost
      return losses; 
   }
   
   
   /**
    * Transfers the player's troops from one territory to an adjacent territory.
    * 
    * (Pre-condition: it has already been verified that the territories performing the transfer
    * are neighboring territories)
    * 
    * @param originTerr the territory that the troops originate from
    * @param destinationTerr the territory that the troops are being transfered to
    * @param numOfTroops number of troops to be transfered
    */
   private void transferTroops(Territory originTerr, Territory destinationTerr, int numOfTroops)
   {
      // Adjust troop count in territory of troop origin
      originTerr.removeTroops(numOfTroops);
      
      // Adjust troop count in territory of troop destination
      destinationTerr.addTroops(numOfTroops);
   }
}