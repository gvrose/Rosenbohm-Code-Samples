import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JTextPane;

import java.awt.CardLayout;
import java.awt.FlowLayout;

import javax.swing.JLabel;

import java.awt.Color;

import javax.swing.SwingConstants;
import javax.swing.BoxLayout;

import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JSpinner;

import java.awt.GridLayout;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.JTextArea;

import java.awt.Font;

import javax.swing.JCheckBox;

import net.miginfocom.swing.MigLayout;

import javax.swing.JButton;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EtchedBorder;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;


public class SideBar extends JPanel
{
   // Instance variables
   private Player _currentPlayer;
   private String _currentPhase;
   
   
   // Phase-dependant instance variables
   private int _troopsToPlace;
   private Player _defendingPlayer;
   private Territory _originTerritory;
   private Territory _destinTerritory;
   
   
   // The phases of the side bar
   private static final String SETUP_PHASE = "Setup";
   private static final String TRADE_IN_PHASE = "Trade-In";
   private static final String TROOP_PLACE_PHASE = "Troop Placement";
   private static final String ATTACK_PHASE = "Attack";
   private static final String REDEPLOY_PHASE = "Redeployment";
   
   private static final String[] VALID_PHASES = { SETUP_PHASE, TRADE_IN_PHASE, 
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
         textPane.setText("");
         updateHand();
         
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
   
   
   public void setMessage(String message)
   {
      textPane.setText(message);
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
    * Use at any phase: udpates the display of the current player's hand
    */
   public void updateHand()
   {
      cardPanel.removeAll();
      
      if (_currentPlayer.getCards().size() > 0)
      {
         for (Card card : _currentPlayer.getCards())
         {
            JLabel cardLbl = new JLabel(card.getTerritory().name() + " - " + 
                  card.getArmyUnit().name());
            cardLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            cardPanel.add(cardLbl);
         }
      }
      
      else
      {
         JLabel noCards = new JLabel("NONE");
         noCards.setAlignmentX(Component.CENTER_ALIGNMENT);
         
         cardPanel.add(noCards);
         
      }
   }
   
   
   /**
    * Initializes the sidebar
    */
   private void initializeLayout()
   {
      setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
      
      // Set up the Current Phase and Player panel. This panel is always active
      // at the top of the sidebar, displaying the current phase of the sidebar
      // (i.e., the current phase of the player's turn), as well as the color
      // of the current player
      currentPhaseandPlayerPanel = new JPanel();
      currentPhaseandPlayerPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
      add(currentPhaseandPlayerPanel);
      
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
      add(phasePanel);
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
      
      
      
      
      // Set up the text pane, which shows the players messages when needed
      // And their current hand
      JPanel cardAndTextPanel = new JPanel();
      add(cardAndTextPanel);
      cardAndTextPanel.setLayout(new BoxLayout(cardAndTextPanel, BoxLayout.PAGE_AXIS));
      
      JPanel cardHeaderPnl = new JPanel();
      cardAndTextPanel.add(cardHeaderPnl);
      
      JLabel cardHeldLbl = new JLabel("Hand:");
      cardHeaderPnl.add(cardHeldLbl);
      
      cardPanel = new JPanel();
      cardAndTextPanel.add(cardPanel);
      cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
      
      textPane = new JTextPane();
      cardAndTextPanel.add(textPane);
      textPane.setBackground(new Color(255, 255, 255));
      textPane.setSize(TEXT_PANE_HEIGHT, TEXT_PANE_WIDTH);
      textPane.setEditable(false);

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
   
   
   // ---------------------------Action Listeners-------------------------------
   private class TradeConfirmListener implements ActionListener
   {
      @Override
      public void actionPerformed(ActionEvent e)
      {
         ArrayList<Card> theCards = new ArrayList<>();
         
         // Get list of selected cards
         for (Component component : trdinCardPanel.getComponents())
         {
            CardCheckBox cBox = (CardCheckBox) component;
            
            if (cBox.isSelected())
            {
               theCards.add(cBox.getCard());
            }
         }
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
   
   
   // ----------------------------Card Check Box--------------------------------
   
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
   
   JPanel cardPanel;
   
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
   
   
   // The text Pane
   JTextPane textPane;
}
