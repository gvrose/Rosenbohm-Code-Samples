import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StartScreen
{
   private static final int MAX_PLAYERS = 6;
   private static final int MIN_PLAYERS = 2;
   
   private JFrame _theWindow;
   private int _numPlayers;
   
   private RiskGUI theGame;
   
   /**
    * Launch the application.
    */
   public static void main(String[] args)
   {
      EventQueue.invokeLater(new Runnable()
      {
         public void run()
         {
            try
            {
               StartScreen window = new StartScreen();
               window._theWindow.setVisible(true);
            }
            catch (Exception e)
            {
               e.printStackTrace();
            }
         }
      }                    );
   }

   /**
    * Create the application.
    */
   public StartScreen()
   {
      initialize();
   }

   /**
    * Initialize the contents of the frame.
    */
   private void initialize()
   {
      _numPlayers = MIN_PLAYERS;
      
      _theWindow = new JFrame();
      _theWindow.setTitle("Risk");
      _theWindow.setBounds(100, 100, 450, 300);
      _theWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      JLabel _title = new JLabel("RISK");
      _title.setHorizontalAlignment(SwingConstants.CENTER);
      _title.setAlignmentX(Component.CENTER_ALIGNMENT);
      
      JPanel _buttonPanel = new JPanel();
      _buttonPanel.setLayout(new CardLayout(0, 0));
      _theWindow.getContentPane().setLayout(new BorderLayout(0, 0));
      _theWindow.getContentPane().add(_title, BorderLayout.CENTER);
      _theWindow.getContentPane().add(_buttonPanel, BorderLayout.EAST);
      
      JPanel _startButtonPanel = new JPanel();
      _buttonPanel.add(_startButtonPanel, "name_867430939904099");
      _startButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
      
      JButton _startButton = new JButton("START");
      _startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
      _startButtonPanel.add(_startButton);
      _startButton.addActionListener(new ActionListener() 
      {
         public void actionPerformed(ActionEvent e) 
         {
            CardLayout bpLayout = (CardLayout) _buttonPanel.getLayout();
            bpLayout.next(_buttonPanel);
         }
      });
      
      JPanel _gameSettingPanel = new JPanel();
      _buttonPanel.add(_gameSettingPanel, "MAIN_MENU");
      _gameSettingPanel.setLayout(new GridLayout(0, 1, 0, 0));
      
      JPanel panel = new JPanel();
      _gameSettingPanel.add(panel);
      
      JPanel panel_1 = new JPanel();
      _gameSettingPanel.add(panel_1);
      
      JPanel _numPlayersPanel = new JPanel();
      _numPlayersPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
      _gameSettingPanel.add(_numPlayersPanel);
      
      JLabel _numPlyIndication = new JLabel("Number of Players:");
      _numPlyIndication.setAlignmentX(Component.CENTER_ALIGNMENT);
      
      JLabel _numPlayersLBL = new JLabel(String.valueOf(_numPlayers));
      _numPlayersLBL.setAlignmentX(Component.CENTER_ALIGNMENT);
      _numPlayersLBL.setVerticalAlignment(SwingConstants.TOP);
      _numPlayersPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
      _numPlayersPanel.add(_numPlyIndication);
      _numPlayersPanel.add(_numPlayersLBL);
      
      JPanel _PlayerButtonPanel = new JPanel();
      _PlayerButtonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
      _PlayerButtonPanel.setAlignmentY(Component.TOP_ALIGNMENT);
      _gameSettingPanel.add(_PlayerButtonPanel);
      
      JButton _subtractPlayerButton = new JButton("-");
      _subtractPlayerButton.addActionListener(new ActionListener() 
      {
         public void actionPerformed(ActionEvent arg0) 
         {
            if (_numPlayers > MIN_PLAYERS)
            {
               _numPlayers--;
               _numPlayersLBL.setText(String.valueOf(_numPlayers));
            }
         }
      });
      _PlayerButtonPanel.add(_subtractPlayerButton);
      
      JButton _addPlayerButton = new JButton("+");
      _addPlayerButton.addActionListener(new ActionListener() 
      {
         public void actionPerformed(ActionEvent arg0) 
         {
            if (_numPlayers < MAX_PLAYERS)
            {
               _numPlayers++;
               _numPlayersLBL.setText(String.valueOf(_numPlayers));
            }
         }
      });
      _PlayerButtonPanel.add(_addPlayerButton);
      
      JPanel _bgnButtonPanel = new JPanel();
      _gameSettingPanel.add(_bgnButtonPanel);
      
      JButton _beginButton = new JButton("BEGIN");
      _beginButton.addActionListener(new ActionListener() 
      {
         public void actionPerformed(ActionEvent arg0) 
         {
            _theWindow.setVisible(false);
            theGame = new RiskGUI(_numPlayers);
         }
      });
      _bgnButtonPanel.add(_beginButton);
      
      JPanel panel_3 = new JPanel();
      _gameSettingPanel.add(panel_3);
      
      JPanel panel_4 = new JPanel();
      _gameSettingPanel.add(panel_4);
   }

}
