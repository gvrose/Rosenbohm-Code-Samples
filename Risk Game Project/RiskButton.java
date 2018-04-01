import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * this class is an extension of the Jbutton class for the purpose of being
 * used as a territory button for the game of Risk.
 * @author Joshua
 *
 */
public class RiskButton extends JButton
{
   // Default serial version
   private static final long serialVersionUID = 1L;
   
   //territory the button is connected to.
   private Territory _theTerritory;
   
   
   /**
    * @param territory the territory of the button 
    */
   public RiskButton(Territory territory, ActionListener listener)
   {
      //calling the super constructor and sending in 00 to intialize
      super("00");
    
      _theTerritory = territory;
      this.addActionListener(listener);
      
      //making the button transparent except for the text
      setBorder(null);
      setBorderPainted(false);
      setContentAreaFilled(false);
      setOpaque(false);
      
   }

   
   /**
    * resets the button too the correct display
    */
   public void resetText()
   {
      //reseting the display of troops and the color of the current occupant
      setText(Integer.toString((_theTerritory.getTroopCount())));
      setForeground(_theTerritory.getOccupant().getColor());
   }
   
   
   /**
    * @return the territory
    */
   public Territory getTerritory()
   {
      return _theTerritory;
   }
}
