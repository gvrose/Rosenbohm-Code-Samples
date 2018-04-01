import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.io.File;
import java.io.IOException;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.JTextPane;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import javax.swing.JComboBox;

/**
 * this class is the graphical user interface for a risk game
 * @author Joshua
 */
public class RiskGUI
{
   //state constants
   private static final String SETUP_PHASE = "Setup";
   private static final String TRADE_IN_PHASE = "Trade-In";
   private static final String TROOP_PLACE_PHASE = "Troop Placement";
   private static final String ATTACK_PHASE = "Attack";
   private static final String REDEPLOY_PHASE = "Redeployment";
   
   //current phase
   private String _currentPhase;
   
   //total number of players
   private int _numPlayers;
   
   private ActionListener _listener = new RiskButtonListener();
   
   //
   SideBar _sideBar;
   Game _gameLogic;
   
   //Initializing territory buttons
   private RiskButton _afghanistan = new RiskButton(Territory.AFGHANISTAN, _listener);
   private RiskButton _alaska = new RiskButton(Territory.ALASKA, _listener);
   private RiskButton _alberta = new RiskButton(Territory.ALBERTA, _listener);
   private RiskButton _argentina = new RiskButton(Territory.ARGENTINA, _listener);
   private RiskButton _brazil = new RiskButton(Territory.BRAZIL, _listener);
   private RiskButton _centralAmerica = new RiskButton(Territory.
         CENTRAL_AMERICA, _listener);
   private RiskButton _china = new RiskButton(Territory.CHINA, _listener);
   private RiskButton _congo = new RiskButton(Territory.CONGO, _listener);
   private RiskButton _eastAfrica = new RiskButton(Territory.EAST_AFRICA, _listener);
   private RiskButton _easternAustralia = new RiskButton(Territory.
         EASTERN_AUSTRALIA, _listener);
   private RiskButton _easternUS = new RiskButton(Territory.
         EASTERN_UNITED_STATES, _listener);
   private RiskButton _egypt = new RiskButton(Territory.EGYPT, _listener);
   private RiskButton _greatBritain = new RiskButton(Territory.GREAT_BRITAIN, _listener);
   private RiskButton _greenLand = new RiskButton(Territory.GREENLAND, _listener);
   private RiskButton _iceLand = new RiskButton(Territory.ICELAND, _listener);
   private RiskButton _india = new RiskButton(Territory.INDIA, _listener);
   private RiskButton _indonesia = new RiskButton(Territory.INDONESIA, _listener);
   private RiskButton _irkutsk = new RiskButton(Territory.IRKUTSK, _listener);
   private RiskButton _japan = new RiskButton(Territory.JAPAN, _listener);
   private RiskButton _kamchatka = new RiskButton(Territory.KAMCHATKA, _listener);
   private RiskButton _madagascar = new RiskButton(Territory.MADAGASCAR, _listener);
   private RiskButton _middleEast = new RiskButton(Territory.MIDDLE_EAST, _listener);
   private RiskButton _mongolia = new RiskButton(Territory.MONGOLIA, _listener);
   private RiskButton _newGuinea = new RiskButton(Territory.NEW_GUINEA, _listener);
   private RiskButton _northAfrica = new RiskButton(Territory.NORTH_AFRICA, _listener);
   private RiskButton _northernEurope = new RiskButton(Territory.
         NORTHERN_EUROPE, _listener);
   private RiskButton _northweastTerritory = new RiskButton(Territory.
         NORTHWEST_TERRITORY, _listener);
   private RiskButton _ontario = new RiskButton(Territory.ONTARIO, _listener);
   private RiskButton _peru = new RiskButton(Territory.PERU, _listener);
   private RiskButton _quebec = new RiskButton(Territory.QUEBEC, _listener);
   private RiskButton _scandanavia = new RiskButton(Territory.SCANDANAVIA, _listener);
   private RiskButton _siam = new RiskButton(Territory.SIAM, _listener);
   private RiskButton _siberia = new RiskButton(Territory.SIBERIA, _listener);
   private RiskButton _southAfrica = new RiskButton(Territory.SOUTH_AFRICA, _listener);
   private RiskButton _southernEurope = new RiskButton(Territory.
         SOUTHERN_EUROPE, _listener);
   private RiskButton _ukraine = new RiskButton(Territory.UKRAINE, _listener);
   private RiskButton _ural = new RiskButton(Territory.URAL, _listener);
   private RiskButton _venezuela = new RiskButton(Territory.VENEZUELA, _listener);
   private RiskButton _westernAustralia = new RiskButton(Territory.
         WESTERN_AUSTRALIA, _listener);
   private RiskButton _westernEurope = new RiskButton(Territory.WESTERN_EUROPE, _listener);
   private RiskButton _westernUS = new RiskButton(Territory.
         WESTERN_UNITED_STATES, _listener);
   private RiskButton _yakutsk = new RiskButton(Territory.YAKUTSK, _listener);
   
   Territory currentTerritory;
   Territory secondaryTerritory;
 
   /**
    * constructor
    * @param numPlayers
    */
   public RiskGUI(int numPlayers)
   {
      _numPlayers = numPlayers;
      JFrame mainFrame = new JFrame();
      JFrame background = new JFrame();
      Container container = mainFrame.getContentPane();
      GridBagLayout layout;
      GridBagConstraints constraints;
      JLabel board = new JLabel("");
     
      //Initializing and setting up the game
      _gameLogic = new Game();
      _gameLogic.setUpGame(_numPlayers);
      
      //Initializing side bar with the current player
      _sideBar = new SideBar(_gameLogic.getCurrentPlayer());

      
      //setting the backround of the board to the image of the world
      try
      {
         ImageIcon theImage = new ImageIcon(ImageIO.read(new File(
               "RiskBoard.jpg")));
         board.setIcon(theImage);
      }
      catch (IOException e)
      {
         System.err.println("Error: image not found");
      }
      
      //setting the label's bounds to appropriate size.
      board.setBounds(100, 100, 100, 100);
      
      //preparing the components necessary to place the buttons on top of the 
      //back ground image
      layout = new GridBagLayout();
      container.setLayout(layout);
      constraints = new GridBagConstraints();
      
      //setting alaska's position on the map.
      constraints.gridx = 0;
      constraints.gridy = 0;
      constraints.insets = new Insets(75, 50, 0, 0);
      layout.setConstraints(_alaska, constraints);
      container.add(_alaska);
      
      //setting northwest territory's position on the map
      constraints.gridx = 1;
      constraints.gridy = 0;
      constraints.insets = new Insets(75, 62, 0, 0);
      layout.setConstraints(_northweastTerritory, constraints);
      container.add(_northweastTerritory);
      
      //setting greenland's position on the map
      constraints.gridx = 2;
      constraints.gridy = 0;
      constraints.insets = new Insets(60, 180, 0, 0);
      layout.setConstraints(_greenLand, constraints);
      container.add(_greenLand);
      
      //setting siberia's position on the map
      constraints.gridx = 3;
      constraints.gridy = 0;
      constraints.insets = new Insets(75, 400, 0, 0);
      layout.setConstraints(_siberia, constraints);
      container.add(_siberia);
      
      //setting yakutsk's position on the map
      constraints.gridx = 4;
      constraints.gridy = 0;
      constraints.insets = new Insets(80, 60, 0, 0);
      layout.setConstraints(_yakutsk, constraints);
      container.add(_yakutsk);
      
      //setting kamchatka's position on the map
      constraints.gridx = 5;
      constraints.gridy = 0;
      constraints.insets = new Insets(75, 50, 0, 0);
      layout.setConstraints(_kamchatka, constraints);
      container.add(_kamchatka);
      
      //setting alberta's position on the map
      constraints.gridx = 1;
      constraints.gridy = 1;
      constraints.insets = new Insets(40, 70, 0, 0);
      layout.setConstraints(_alberta, constraints);
      container.add(_alberta);
      
      //setting ontario's position on the map
      constraints.gridx = 2;
      constraints.gridy = 1;
      constraints.insets = new Insets(40, 0, 0, 100);
      layout.setConstraints(_ontario, constraints);
      container.add(_ontario);
      
      //setting quebec's position on the map
      constraints.gridx = 2;
      constraints.gridy = 1;
      constraints.insets = new Insets(40, 50, 0, 0);
      layout.setConstraints(_quebec, constraints);
      container.add(_quebec);
      
      //setting iceland's position on the map
      constraints.gridx = 2;
      constraints.gridy = 1;
      constraints.insets = new Insets(37, 160, 0, -182);
      layout.setConstraints(_iceLand, constraints);
      container.add(_iceLand);
      
      //setting scandanavia's position on the map
      constraints.gridx = 3;
      constraints.gridy = 1;
      constraints.insets = new Insets(40, 0, 0, 100);
      layout.setConstraints(_scandanavia, constraints);
      container.add(_scandanavia);
      
      //setting ukraine's position on the map
      constraints.gridx = 3;
      constraints.gridy = 1;
      constraints.insets = new Insets(40, 80, 0, 0);
      layout.setConstraints(_ukraine, constraints);
      container.add(_ukraine);
      
      //setting ural's position on the map
      constraints.gridx = 3;
      constraints.gridy = 1;
      constraints.insets = new Insets(40, 290, 0, 0);
      layout.setConstraints(_ural, constraints);
      container.add(_ural);
      
      //setting irkutsk's position on the map
      constraints.gridx = 4;
      constraints.gridy = 1;
      constraints.insets = new Insets(40, 50, 0, 0);
      layout.setConstraints(_irkutsk, constraints);
      container.add(_irkutsk);
      
      //setting western US' position on the map
      constraints.gridx = 1;
      constraints.gridy = 2;
      constraints.insets = new Insets(90, 70, 0, 0);
      layout.setConstraints(_westernUS, constraints);
      container.add(_westernUS);
      
      //setting eastern US' position on the map
      constraints.gridx = 2;
      constraints.gridy = 2;
      constraints.insets = new Insets(80, 0, 0, 0);
      layout.setConstraints(_easternUS, constraints);
      container.add(_easternUS);
      
      //setting great britain's position on the map
      constraints.gridx = 2;
      constraints.gridy = 2;
      constraints.insets = new Insets(50, 0, 0, -340);
      layout.setConstraints(_greatBritain, constraints);
      container.add(_greatBritain);
      
      //setting northern europe's position on the map
      constraints.gridx = 3;
      constraints.gridy = 2;
      constraints.insets = new Insets(0, 0, 0, 100);
      layout.setConstraints(_northernEurope, constraints);
      container.add(_northernEurope);
      
      //setting afghanistan's position on the map
      constraints.gridx = 3;
      constraints.gridy = 2;
      constraints.insets = new Insets(50, 250, 0, 0);
      layout.setConstraints(_afghanistan, constraints);
      container.add(_afghanistan);
      
      //setting mongolia's position on the map
      constraints.gridx = 4;
      constraints.gridy = 2;
      constraints.insets = new Insets(30, 50, 0, 0);
      layout.setConstraints(_mongolia, constraints);
      container.add(_mongolia);
      
      //setting japan's position on the map
      constraints.gridx = 5;
      constraints.gridy = 2;
      constraints.insets = new Insets(45, 85, 0, 0);
      layout.setConstraints(_japan, constraints);
      container.add(_japan);
      
      //setting central america's position on the map
      constraints.gridx = 0;
      constraints.gridy = 3;
      constraints.insets = new Insets(30, 60, 0, -190);
      layout.setConstraints(_centralAmerica, constraints);
      container.add(_centralAmerica);
      
      //setting western europe's position on the map
      constraints.gridx = 3;
      constraints.gridy = 3;
      constraints.insets = new Insets(60, 0, 0, 230);
      layout.setConstraints(_westernEurope, constraints);
      container.add(_westernEurope);
      
      //setting southern europe's position on the map
      constraints.gridx = 3;
      constraints.gridy = 3;
      constraints.insets = new Insets(20, 0, 0, 60);
      layout.setConstraints(_southernEurope, constraints);
      container.add(_southernEurope);
      
      //setting china's position on the map
      constraints.gridx = 4;
      constraints.gridy = 3;
      constraints.insets = new Insets(0, 0, 20, 0);
      layout.setConstraints(_china, constraints);
      container.add(_china);
      
      //setting venezuela's position on the map
      constraints.gridx = 0;
      constraints.gridy = 4;
      constraints.insets = new Insets(30, 50, 0, -330);
      layout.setConstraints(_venezuela, constraints);
      container.add(_venezuela);
      
      //setting egypt's position on the map
      constraints.gridx = 3;
      constraints.gridy = 4;
      constraints.insets = new Insets(30, 0, -25, 20);
      layout.setConstraints(_egypt, constraints);
      container.add(_egypt);
      
      //setting middle east's position on the map
      constraints.gridx = 3;
      constraints.gridy = 4;
      constraints.insets = new Insets(10, 40, 0, -100);
      layout.setConstraints(_middleEast, constraints);
      container.add(_middleEast);
      
      //setting india's position on the map
      constraints.gridx = 3;
      constraints.gridy = 4;
      constraints.insets = new Insets(0, 50, 30, -310);
      layout.setConstraints(_india, constraints);
      container.add(_india);
      
      //setting peru's position on the map
      constraints.gridx = 1;
      constraints.gridy = 5;
      constraints.insets = new Insets(80, 50, 0, -200);
      layout.setConstraints(_peru, constraints);
      container.add(_peru);
      
      //setting brazil's position on the map
      constraints.gridx = 2;
      constraints.gridy = 5;
      constraints.insets = new Insets(0, 90, 0, 0);
      layout.setConstraints(_brazil, constraints);
      container.add(_brazil);
      
      //setting north africa's position on the map
      constraints.gridx = 3;
      constraints.gridy = 5;
      constraints.insets = new Insets(0, 0, 20, 170);
      layout.setConstraints(_northAfrica, constraints);
      container.add(_northAfrica);
      
      //setting east africa's position on the map
      constraints.gridx = 3;
      constraints.gridy = 5;
      constraints.insets = new Insets(50, 60, 0, 0);
      layout.setConstraints(_eastAfrica, constraints);
      container.add(_eastAfrica);
      
      //setting siam's position on the map
      constraints.gridx = 4;
      constraints.gridy = 5;
      constraints.insets = new Insets(0, 60, 60, 0);
      layout.setConstraints(_siam, constraints);
      container.add(_siam);
      
      //setting argentina's position on the map
      constraints.gridx = 2;
      constraints.gridy = 6;
      constraints.insets = new Insets(50, 20, 0, 0);
      layout.setConstraints(_argentina, constraints);
      container.add(_argentina);

      //setting congo's position on the map
      constraints.gridx = 3;
      constraints.gridy = 6;
      constraints.insets = new Insets(0, 0, 20, 20);
      layout.setConstraints(_congo, constraints);
      container.add(_congo);
      
      //setting indonesia's position on the map
      constraints.gridx = 4;
      constraints.gridy = 6;
      constraints.insets = new Insets(0, 75, 0, 0);
      layout.setConstraints(_indonesia, constraints);
      container.add(_indonesia);
      
      //setting new guinea's position on the map
      constraints.gridx = 5;
      constraints.gridy = 6;
      constraints.insets = new Insets(0, 50, 45, 0);
      layout.setConstraints(_newGuinea, constraints);
      container.add(_newGuinea);
      
      //setting south africa's position on the map
      constraints.gridx = 3;
      constraints.gridy = 7;
      constraints.insets = new Insets(30, 0, 0, 10);
      layout.setConstraints(_southAfrica, constraints);
      container.add(_southAfrica);
      
      //setting madagascar's position on the map
      constraints.gridx = 3;
      constraints.gridy = 7;
      constraints.insets = new Insets(30, 170, -25, 0);
      layout.setConstraints(_madagascar, constraints);
      container.add(_madagascar);
      
      //setting western australia's position on the map
      constraints.gridx = 5;
      constraints.gridy = 7;
      constraints.insets = new Insets(0, 0, -30, 50);
      layout.setConstraints(_westernAustralia, constraints);
      container.add(_westernAustralia);
      
      //setting eastern australia's position on the map
      constraints.gridx = 6;
      constraints.gridy = 7;
      constraints.insets = new Insets(30, 10, -30, 0);
      layout.setConstraints(_easternAustralia, constraints);
      container.add(_easternAustralia);
      
      mainFrame.pack();
      
      
      //adding the buttons to the image 
      board.add(_alaska); 
      board.add(_northweastTerritory); 
      board.add(_greenLand);
      board.add(_siberia);
      board.add(_yakutsk);
      board.add(_kamchatka);
      board.add(_alberta);
      board.add(_ontario);
      board.add(_quebec);
      board.add(_iceLand);
      board.add(_scandanavia);
      board.add(_ukraine);
      board.add(_ural);
      board.add(_irkutsk);
      board.add(_westernUS);
      board.add(_easternUS);
      board.add(_greatBritain);
      board.add(_northernEurope);
      board.add(_afghanistan);
      board.add(_mongolia);
      board.add(_japan);
      board.add(_centralAmerica);
      board.add(_westernEurope);
      board.add(_southernEurope);
      board.add(_china);
      board.add(_venezuela);
      board.add(_egypt);
      board.add(_middleEast);
      board.add(_india);
      board.add(_peru);
      board.add(_brazil);
      board.add(_northAfrica);
      board.add(_eastAfrica);
      board.add(_siam);
      board.add(_argentina);
      board.add(_congo);
      board.add(_indonesia);
      board.add(_newGuinea);
      board.add(_southAfrica);
      board.add(_madagascar);
      board.add(_westernAustralia);
      board.add(_easternAustralia);
      
      //reseting the buttons text because if they initially set to a single digit
      //value before being added to the board the number displayed could not get 
      //larger than a single digit.
      _afghanistan.resetText();
      _alaska.resetText();
      _alberta.resetText();
      _argentina.resetText();
      _brazil.resetText();
      _centralAmerica.resetText();
      _china.resetText();
      _congo.resetText();
      _eastAfrica.resetText();
      _easternAustralia.resetText();
      _easternUS.resetText();
      _egypt.resetText();
      _greatBritain.resetText();
      _greenLand.resetText();
      _iceLand.resetText();
      _india.resetText();
      _indonesia.resetText();
      _irkutsk.resetText();
      _japan.resetText();
      _kamchatka.resetText();
      _madagascar.resetText();
      _middleEast.resetText();
      _mongolia.resetText();
      _northAfrica.resetText();
      _newGuinea.resetText();
      _northernEurope.resetText();
      _northweastTerritory.resetText();
      _ontario.resetText();
      _peru.resetText();
      _quebec.resetText();
      _scandanavia.resetText();
      _siam.resetText();
      _siberia.resetText();
      _southAfrica.resetText();
      _southernEurope.resetText();
      _ukraine.resetText();
      _ural.resetText();
      _venezuela.resetText();
      _westernAustralia.resetText();
      _westernEurope.resetText();
      _westernUS.resetText();
      _yakutsk.resetText();
      
      //placing the board into a JPanel and setting the background's layout to
      //gridbag
      JPanel boardPanel = new JPanel();
      boardPanel.add(board);
      layout = new GridBagLayout();
      constraints = new GridBagConstraints();
      background.setLayout(layout);
      
      //adding the board to the background with the specific constraints
      constraints.fill = GridBagConstraints.HORIZONTAL;
      constraints.gridx = 3;
      background.pack();
      background.add(boardPanel, constraints);
      
      //adding the side bar to the background with the specific constraints
      constraints.fill = GridBagConstraints.VERTICAL;
      constraints.gridx = 1;
      background.add(_sideBar, constraints);
     
      //Initializing the current phase to the setup phase
      _currentPhase = SETUP_PHASE;
      
      //setting the background to the size of the screen and making the whole
      //thing visable
      background.setExtendedState(JFrame.MAXIMIZED_BOTH);
      background.setVisible(true);
    }
   
   
   /**
    * 
    * @author Joshua
    *
    */
   private class RiskButtonListener implements ActionListener
   {
      public void actionPerformed(final ActionEvent event)
      {
         //
         RiskButton territoryButton = (RiskButton)event.getSource();
         int troopCount = territoryButton.getTerritory().getTroopCount();
         
         if (_currentPhase.equals(SETUP_PHASE))
         {
            //
            _gameLogic.placeTroop(territoryButton.getTerritory());
            territoryButton.resetText();
//            _sideBar.setPhase(TROOP_PLACE_PHASE);
//            _state = TROOP_PLACE_PHASE;
         }
         else if (_currentPhase.equals(TRADE_IN_PHASE))
         {
            // TODO
            
         }
         else if (_currentPhase.equals(TROOP_PLACE_PHASE))
         {
            _sideBar.setPhase(ATTACK_PHASE);
         }
         else if (_currentPhase.equals(ATTACK_PHASE))
         {
            //TODO
            if (currentTerritory == null)
            {
               currentTerritory = territoryButton.getTerritory();
            }
           // else if ()
            //{
               
            //}
         }
         // if the current phase is the re-deployment phase
         else 
         {
            //TODO
         }
      }
   }

   
   
   public class SideBar extends JPanel
   {
      // Instance variables
      private Player _currentPlayer;
     // private String _currentPhase;
      
      
      // Phase-dependant instance variables
      private int _troopsToPlace;
      private Player _defendingPlayer;
      private Territory _originTerritory;
      private Territory _destinTerritory;
      
      
//      // The phases of the side bar
//      private static final String SETUP_PHASE = "Setup";
//      private static final String TRADE_IN_PHASE = "Trade-In";
//      private static final String TROOP_PLACE_PHASE = "Troop Placement";
//      private static final String ATTACK_PHASE = "Attack";
//      private static final String REDEPLOY_PHASE = "Redeployment";
      
      private final String[] VALID_PHASES = { SETUP_PHASE, TRADE_IN_PHASE, 
         TROOP_PLACE_PHASE, ATTACK_PHASE, REDEPLOY_PHASE };
      
      private static final int TEXT_PANE_HEIGHT = 40;
      private static final int TEXT_PANE_WIDTH = 30;
      
      
      /**
       * Create the panel.
       */
      public SideBar(Player startingPlayer)
      {
         // Initialize the instance variables
         _currentPlayer = startingPlayer;
         _currentPhase = SETUP_PHASE;
         
         initializeLayout();
      }
      
      
      public void setCurrentPlayer(Player newPlayer)
      {
         _currentPlayer = newPlayer;
      }
      
      
      public Player getCurrentPlayer()
      {
         return _currentPlayer;
      }

      
      /**
       * Changes the sidebar's phase to the given phase name
       * @param phase 
       */
      public void setPhase(String phase)
      {
         if (isValidPhase(phase))
         {
            _currentPhase = phase;
            
            // Switch the cardlayout of the Phase Panel to the given phase
            CardLayout cardLay = (CardLayout) phasePanel.getLayout();
            cardLay.show(phasePanel, _currentPhase);
            
            
            // Set the header to the new phase and the current player
            currentPhaseLbl.setText(_currentPhase);
            currentPlayerLbl.setBackground(_currentPlayer.getColor());
            
            
            
            // If setup phase, set the number of troops remaining to the current
            // player's troops to place
            if (phase.equals(SETUP_PHASE))
            {
               _troopsToPlace = _currentPlayer.getTroopsToPlace();
               
               setupTroopNumLbl.setText(String.valueOf(_troopsToPlace));
            }
            
            
            
            // Else if Trade-in phase, for each card that current player has, 
            // add a checkbox with the territory and unit that card represents
            else if (phase.equals(TRADE_IN_PHASE))
            {
               // Reset the card panel, then add the new cards
               trdinCardPanel.removeAll();
               
               for (Card card : _currentPlayer.getCards())
               {
                  trdinCardPanel.add(new CardCheckBox(card));
               }
            }
            
            
            
            // Else if Troop Placement phase, set the number of troops remaining
            // to the current player's troops to place and set the spinner of
            // troops being placed to a maximum of that
            else if (phase.equals(TROOP_PLACE_PHASE))
            {
               _troopsToPlace = _currentPlayer.getTroopsToPlace();
               
               trpPlTroopNumLbl.setText(String.valueOf(_troopsToPlace));
               trpPlTroopCountCBox.removeAll();
               
               trpPlTerritoryLbl.setText("");
               
               // Add the current amount of troops to the combo box
               for (int i = 0; i < _troopsToPlace; i++)
               {
                  trpPlTroopCountCBox.addItem(i + 1);
               }
            }
            
            
            // Else if Attack phase, set the attacking player label to current
            // player
            else if (phase.equals(ATTACK_PHASE))
            {
               atkAttackPlyrLbl.setOpaque(true);
               atkAttackPlyrLbl.setBackground(_currentPlayer.getColor());
               atkDefendPlyrLbl.setOpaque(false);
               
               atkAttackTerrLbl.setText("");
               atkDefendTerrLbl.setText("");
            }
            
            
            // Else if Redeployment phase, initialize the combo box of troops
            // to zero only
            else if (phase.equals(REDEPLOY_PHASE))
            {
               redpTroopsCBox.removeAll();
               
               redpBeginTerrLbl.setText("");
               redpDestTerrLbl.setText("");
            }
            
         }
         
      }
      
      
      /**
       * Use during attack phase only: Sets the current defending player to the
       * given player, and changes the display accordingly
       * @param player
       */
      public void setDefendingPlayer(Player player)
      {
         if (_currentPhase == ATTACK_PHASE)
         {
            _defendingPlayer = player;
            
            atkDefendPlyrLbl.setBackground(_defendingPlayer.getColor());
         }
      }
      
      /**
       * Use during attack or redeployment phase only: Sets the origin territory 
       * (attacking territory in attack phase, origin territory in redeployment 
       * phase) to the given territory
       * @param territory the new origin territory
       */
      public void setOriginTerritory(Territory territory)
      {
         _originTerritory = territory;
         
         if (_currentPhase == ATTACK_PHASE)
         {
            atkAttackTerrLbl.setText(_originTerritory.name());
         }
         
         else if (_currentPhase == REDEPLOY_PHASE)
         {
            redpBeginTerrLbl.setText(_originTerritory.name());
         }
      }
      
      
      /**
       * Use during attack or redeployment phase only: Sets the destination 
       * territory (defending territory in attack phase, destination territory in 
       * redeployment phase) to the given territory
       * @param territory the new destination territory
       */
      public void setDestinationTerritory(Territory territory)
      {
         _destinTerritory = territory;
         
         if (_currentPhase == ATTACK_PHASE)
         {
            atkDefendTerrLbl.setText(_destinTerritory.name());
         }
         
         else if (_currentPhase == REDEPLOY_PHASE)
         {
            redpDestTerrLbl.setText(_destinTerritory.name());
         }
      }
      
      
      /**
       * Use for setup or troop placement phase only: Updates the troops to place 
       * during the setup or troop placement phases based on the current player
       */
      public void updateTroopsToPlace()
      {
         _troopsToPlace = _currentPlayer.getTroopsToPlace();
         
         
         if (_currentPhase == SETUP_PHASE)
         {
            setupTroopNumLbl.setText(String.valueOf(_troopsToPlace));
         }
         
         else if (_currentPhase == TROOP_PLACE_PHASE)
         {
            trpPlTroopNumLbl.setText(String.valueOf(_troopsToPlace));
            
            trpPlTroopCountCBox.removeAll();
            
            for (int i = 0; i < _troopsToPlace; i++)
            {
               trpPlTroopCountCBox.addItem(i + 1);
            }
         }
      }
      
      
      /**
       * Initializes the sidebar
       */
      private void initializeLayout()
      {
         setLayout(new BorderLayout(0, 0));
         
         // Set up the Current Phase and Player panel. This panel is always active
         // at the top of the sidebar, displaying the current phase of the sidebar
         // (i.e., the current phase of the player's turn), as well as the color
         // of the current player
         currentPhaseandPlayerPanel = new JPanel();
         currentPhaseandPlayerPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
         add(currentPhaseandPlayerPanel, BorderLayout.NORTH);
         
         currentPhaseLbl = new JLabel(_currentPhase);
         currentPhaseandPlayerPanel.add(currentPhaseLbl);
         
         JLabel currentDashLbl = new JLabel("-");
         currentPhaseandPlayerPanel.add(currentDashLbl);
         
         currentPlayerLbl = new JLabel("     ");
         currentPlayerLbl.setOpaque(true);
         currentPlayerLbl.setBackground(_currentPlayer.getColor());
         currentPhaseandPlayerPanel.add(currentPlayerLbl);
         
         
         
         // Set up the Phase panel. This panel uses a CardLayout setup to display
         // the functionality of the current phase of the player's turn
         phasePanel = new JPanel();
         add(phasePanel, BorderLayout.CENTER);
         phasePanel.setLayout(new CardLayout(0, 0));
         
         
         
         // Set up the Setup Phase panel. This panel displays the remaining number
         // of troops the current player has to place during the initial setup of
         // the game of Risk.
         setupPanel = new JPanel();
         phasePanel.add(setupPanel, SETUP_PHASE);
         setupPanel.setLayout(new BoxLayout(setupPanel, BoxLayout.X_AXIS));
         
         JPanel setupPlyrPnl = new JPanel();
         setupPanel.add(setupPlyrPnl);
         
         JLabel setupTroopsRemainLbl = new JLabel("Troops Remaining:");
         setupPlyrPnl.add(setupTroopsRemainLbl);
         
         setupTroopNumLbl = new JLabel(String.valueOf(_currentPlayer.getNumOfTerritories()));
         setupPlyrPnl.add(setupTroopNumLbl);
         
         
         
         // Set up the Trade-In Phase panel. This panel displays a series of
         // checkboxes representing the cards the current player owns, and allows
         // the user to select the cards they want to trade in
         tradeInPanel = new JPanel();
         phasePanel.add(tradeInPanel, TRADE_IN_PHASE);
         tradeInPanel.setLayout(new BorderLayout(0, 0));
         
         JPanel trdinHeaderPanel = new JPanel();
         tradeInPanel.add(trdinHeaderPanel, BorderLayout.NORTH);
         
         JLabel trdinHeaderLbl = new JLabel("Select Cards to Trade In");
         trdinHeaderPanel.add(trdinHeaderLbl);
         
         trdinCardPanel = new JPanel();
         tradeInPanel.add(trdinCardPanel, BorderLayout.CENTER);
         trdinCardPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 5));
         
         JPanel trdinConfirmPanel = new JPanel();
         tradeInPanel.add(trdinConfirmPanel, BorderLayout.SOUTH);
         
         JButton trdinConfirmBtn = new JButton("CONFIRM");
         trdinConfirmBtn.addActionListener(new TradeConfirmListener());
         trdinConfirmPanel.add(trdinConfirmBtn);
         
         
         
         // Set up the Troop Placement Phase panel. This panel displays the number
         // of troops the current player has to place, and uses a combo box to
         // allow the player to determine how many troops to add at the selected
         // territory
         troopPlacePanel = new JPanel();
         phasePanel.add(troopPlacePanel, TROOP_PLACE_PHASE);
         troopPlacePanel.setLayout(new BorderLayout(0, 3));
         
         JPanel trpPlHeaderPanel = new JPanel();
         troopPlacePanel.add(trpPlHeaderPanel, BorderLayout.NORTH);
         
         JLabel trpPlRemainLbl = new JLabel("Troops Remaining:");
         trpPlHeaderPanel.add(trpPlRemainLbl);
         
         trpPlTroopNumLbl = new JLabel("0");
         trpPlHeaderPanel.add(trpPlTroopNumLbl);
         
         JPanel trpPlAddingPanel = new JPanel();
         troopPlacePanel.add(trpPlAddingPanel, BorderLayout.SOUTH);
         trpPlAddingPanel.setLayout(new BoxLayout(trpPlAddingPanel, BoxLayout.Y_AXIS));
         
         JPanel troopAddPanel = new JPanel();
         trpPlAddingPanel.add(troopAddPanel);
         
         JLabel trpPlPlaceLbl = new JLabel("Place");
         troopAddPanel.add(trpPlPlaceLbl);
         
         trpPlTroopCountCBox = new JComboBox<>();
         troopAddPanel.add(trpPlTroopCountCBox);
         
         JLabel trpPlTroopsAtLbl = new JLabel("troops at");
         troopAddPanel.add(trpPlTroopsAtLbl);
         
         trpPlTerritoryLbl = new JLabel("<Territory>");
         troopAddPanel.add(trpPlTerritoryLbl);
         
         JButton trpPlCancelBtn = new JButton("CANCEL");
         trpPlCancelBtn.addActionListener(new TroopPlaceCancelListener());
         trpPlAddingPanel.add(trpPlCancelBtn);
         trpPlCancelBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
         
         JButton trpPlConfirmBtn = new JButton("CONFIRM");
         trpPlConfirmBtn.addActionListener(new TroopPlaceConfirmListener());
         trpPlAddingPanel.add(trpPlConfirmBtn);
         trpPlConfirmBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
         
         
         
         
         // Set up the Attack Phase panel. This panel displays the attack and 
         // defending players and territories, and allows the attacking to confirm
         // their attack or retreat
         attackPanel = new JPanel();
         phasePanel.add(attackPanel, ATTACK_PHASE);
         attackPanel.setLayout(new BorderLayout(0, 0));
         
         JPanel atkStatusPanel = new JPanel();
         attackPanel.add(atkStatusPanel, BorderLayout.NORTH);
         atkStatusPanel.setLayout(new BoxLayout(atkStatusPanel, BoxLayout.Y_AXIS));
         
         JPanel atkPlyrPanel = new JPanel();
         atkStatusPanel.add(atkPlyrPanel);
         atkPlyrPanel.setLayout(new BoxLayout(atkPlyrPanel, BoxLayout.Y_AXIS));
         
         JPanel attkerPanel = new JPanel();
         atkPlyrPanel.add(attkerPanel);
         
         JLabel atkAttackerLbl = new JLabel("Attacker: ");
         attkerPanel.add(atkAttackerLbl);
         
         atkAttackPlyrLbl = new JLabel("     ");
         attkerPanel.add(atkAttackPlyrLbl);
         
         JPanel defdrPanel = new JPanel();
         atkPlyrPanel.add(defdrPanel);
         
         JLabel atkDefenderLbl = new JLabel("Defender: ");
         defdrPanel.add(atkDefenderLbl);
         
         atkDefendPlyrLbl = new JLabel("     ");
         defdrPanel.add(atkDefendPlyrLbl);
         
         JPanel atkTerriPanel = new JPanel();
         atkStatusPanel.add(atkTerriPanel);
         atkTerriPanel.setLayout(new BoxLayout(atkTerriPanel, BoxLayout.Y_AXIS));
         
         JPanel attkerTerrPanel = new JPanel();
         atkTerriPanel.add(attkerTerrPanel);
         
         JLabel atkFromLbl = new JLabel("From: ");
         attkerTerrPanel.add(atkFromLbl);
         
         atkAttackTerrLbl = new JLabel("<Attacking>");
         attkerTerrPanel.add(atkAttackTerrLbl);
         
         JPanel defdrTerrPanel = new JPanel();
         atkTerriPanel.add(defdrTerrPanel);
         
         JLabel atkToLbl = new JLabel("To: ");
         defdrTerrPanel.add(atkToLbl);
         
         atkDefendTerrLbl = new JLabel("<Defending>");
         defdrTerrPanel.add(atkDefendTerrLbl);
         
         JPanel atkButtonPanel = new JPanel();
         attackPanel.add(atkButtonPanel, BorderLayout.SOUTH);
         
         JButton atkAttackBtn = new JButton("ATTACK");
         atkAttackBtn.addActionListener(new AttackAttackListener());
         atkButtonPanel.add(atkAttackBtn);
         
         JButton atkRetreatBtn = new JButton("RETREAT");
         atkRetreatBtn.addActionListener(new AttackRetreatListener());
         atkButtonPanel.add(atkRetreatBtn);
         
         
         
         // Set up the Redeployment Phase panel. This panel displays the 
         // territories the player has selected to transfer troops to and from,
         // as well as a spinner to choose the number of troops transferred
         JPanel redeployPanel = new JPanel();
         phasePanel.add(redeployPanel, REDEPLOY_PHASE);
         redeployPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
         
         JPanel redpTrpPanel = new JPanel();
         redeployPanel.add(redpTrpPanel);
         
         JLabel redpLbl = new JLabel("Transfer");
         redpTrpPanel.add(redpLbl);
         
         redpTroopsCBox = new JComboBox<>();
         redpTrpPanel.add(redpTroopsCBox);
         
         JLabel redpTrpLbl = new JLabel("troops");
         redpTrpPanel.add(redpTrpLbl);
         
         JPanel redpTerrPanel = new JPanel();
         redeployPanel.add(redpTerrPanel);
         redpTerrPanel.setLayout(new BoxLayout(redpTerrPanel, BoxLayout.Y_AXIS));
         
         JPanel redpFrmPanel = new JPanel();
         redpTerrPanel.add(redpFrmPanel);
         
         JLabel redpFromLbl = new JLabel("From: ");
         redpFrmPanel.add(redpFromLbl);
         
         redpBeginTerrLbl = new JLabel("<Source>");
         redpFrmPanel.add(redpBeginTerrLbl);
         
         JPanel redpToPanel = new JPanel();
         redpTerrPanel.add(redpToPanel);
         
         JLabel redpToLbl = new JLabel("To: ");
         redpToPanel.add(redpToLbl);
         
         redpDestTerrLbl = new JLabel("<Destination>");
         redpToPanel.add(redpDestTerrLbl);
         
         JButton redpCancelBtn = new JButton("CANCEL");
         redpCancelBtn.addActionListener(new RedeployCancelListener());
         redeployPanel.add(redpCancelBtn);
         redpCancelBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
         
         JButton redpConfirmBtn = new JButton("CONFIRM");
         redpConfirmBtn.addActionListener(new RedeployConfirmListener());
         redeployPanel.add(redpConfirmBtn);
         redpConfirmBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
         
         JButton redpEndTurnBtn = new JButton("END TURN");
         redpEndTurnBtn.addActionListener(new RedeployEndTurnListener());
         redeployPanel.add(redpEndTurnBtn);
         redpEndTurnBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
         
         JTextPane textPane = new JTextPane();
         textPane.setBackground(new Color(255, 255, 255));
         textPane.setSize(TEXT_PANE_HEIGHT, TEXT_PANE_WIDTH);
         textPane.setEditable(false);
         add(textPane, BorderLayout.SOUTH);

      }
      
      
      private boolean isValidPhase(String phase)
      {
         boolean isValid = false;
         
         for (String validPhase : VALID_PHASES)
         {
            if (phase.equals(validPhase))
            {
               isValid = true;
            }
         }
         
         return isValid;
      }
      
      
      // -----------------------------Action Listeners-----------------------------
      private class TradeConfirmListener implements ActionListener
      {
         @Override
         public void actionPerformed(ActionEvent e)
         {
            // TODO Auto-generated method stub
            
         }
      }
      
      
      private class TroopPlaceCancelListener implements ActionListener
      {

         @Override
         public void actionPerformed(ActionEvent e)
         {
            // TODO Auto-generated method stub
            
         }
         
      }
      
      
      private class TroopPlaceConfirmListener implements ActionListener
      {

         @Override
         public void actionPerformed(ActionEvent e)
         {
            // TODO Auto-generated method stub
            
         }
         
      }
      
      
      private class AttackAttackListener implements ActionListener
      {

         @Override
         public void actionPerformed(ActionEvent e)
         {
            // TODO Auto-generated method stub
            
         }
         
      }
      
      
      private class AttackRetreatListener implements ActionListener
      {

         @Override
         public void actionPerformed(ActionEvent e)
         {
            // TODO Auto-generated method stub
            
         }
         
      }
      
      
      private class RedeployCancelListener implements ActionListener
      {

         @Override
         public void actionPerformed(ActionEvent e)
         {
            // TODO Auto-generated method stub
            
         }
         
      }
      
      
      private class RedeployConfirmListener implements ActionListener
      {

         @Override
         public void actionPerformed(ActionEvent e)
         {
            // TODO Auto-generated method stub
            
         }
         
      }
      
      
      private class RedeployEndTurnListener implements ActionListener
      {

         @Override
         public void actionPerformed(ActionEvent e)
         {
            // TODO Auto-generated method stub
            
         }
         
      }
      
      class CardCheckBox extends JCheckBox
      {
         private Card _theCard;
         
         public CardCheckBox(Card theCard)
         {
            super(theCard.getTerritory().name() + " - " + 
                  theCard.getArmyUnit().name());
            
            _theCard = theCard;  
         }
         
         public Card getCard()
         {
            return _theCard;
         }
         
         /**
          * 
          */
         private static final long serialVersionUID = -3690489599345123916L;
      }
      
      // ----------------------------Mutable Components----------------------------
      
      // Panels
      JPanel phasePanel;
      
      JPanel currentPhaseandPlayerPanel;
      
      JPanel setupPanel;
      
      JPanel tradeInPanel;
      JPanel trdinCardPanel;
      
      JPanel troopPlacePanel;
      
      JPanel attackPanel;
      
      
      // Labels
      JLabel currentPhaseLbl;
      JLabel currentPlayerLbl;
      
      JLabel setupTroopNumLbl;
      
      JLabel trpPlTroopNumLbl;
      JLabel trpPlTerritoryLbl;
      
      JLabel atkAttackPlyrLbl;
      JLabel atkDefendPlyrLbl;
      JLabel atkAttackTerrLbl;
      JLabel atkDefendTerrLbl;
      
      JLabel redpBeginTerrLbl;
      JLabel redpDestTerrLbl;
      
      // Combo Boxes
      JComboBox<Integer> trpPlTroopCountCBox;
      
      JComboBox<Integer> redpTroopsCBox;
   }

}