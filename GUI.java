import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame implements ActionListener
{
   int i, j;                                                               //Iterators for board locations
   Players p1 = null;                                                      //Player 1 info
   Players p2 = null;                                                      //Player 2 info
   GridLayout layout = new GridLayout(0, 8);                               //GridLayout to place buttons under board tiles
   JPanel framePanel = new JPanel(layout);                                 //JPanel to contain GUI elements
   JMenuBar menuBar = new JMenuBar();                                      //Menu bar
   JMenu gameMenu = new JMenu("Game");                                     //Menu for general setup
   JMenuItem setPlayerNames = new JMenuItem("Set Player Names");           //MenuItem for setting player names
   JMenuItem resetGame = new JMenuItem("Reset Game");                      //MenuItem for resetting board
   JLabel whitePlayerLabel = new JLabel("White");                          //Label for name
   JLabel vsLabel = new JLabel("vs.");                                     //vs Label will stay the same
   JLabel blackPlayerLabel = new JLabel("Black");                          //Label for name
   Board gameBoard = new Board();                                          //Everything regarding movement of pieces
   
   public GUI(){
      super("Chess");
      
      setSize(600, 700);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      add(framePanel);
      
      setJMenuBar(menuBar);
      menuBar.add(gameMenu);
      gameMenu.add(setPlayerNames);
      gameMenu.add(resetGame);
      
      gameMenu.setMnemonic('G');
      setPlayerNames.addActionListener(this);
      resetGame.addActionListener(this);
      
      for (i = 0; i < 8; i++){
         for (j = 0; j < 8; j++){
            framePanel.add(gameBoard.getButtonsArray()[i][j]);
            
         }
      }
      
      framePanel.add(new JScrollPane(whitePlayerLabel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED));
      framePanel.add(vsLabel);
      framePanel.add(new JScrollPane(blackPlayerLabel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED));
   }
   
   /*
   setPlayerNames (57): JMenuItem; sets white and black player names to values in JOptionPane input
   resetGame (75): calls function Board.resetGame() to set the board back to start
   */
   @Override
   public void actionPerformed(ActionEvent e){
      Object source = e.getSource();
      if (source == setPlayerNames){
         String player1Name = JOptionPane.showInputDialog(null, "Who will play the white pieces? Leave blank for no name.");
         String player2Name = JOptionPane.showInputDialog(null, "Who will play the black pieces? Leave blank for no name.");
         if (player1Name == null || player1Name.equals("")){
            p1 = new Players(true);
         }
         else{
            p1 = new Players(true, player1Name);
         }
         if (player2Name == null || player2Name.equals("")){
            p2 = new Players(false);
         }
         else{
            p2 = new Players(false, player2Name);
         }
         whitePlayerLabel.setText(p1.getName());
         blackPlayerLabel.setText(p2.getName());
      }
      else if (source == resetGame){
         gameBoard.resetGame();
      }
   }
   
   public static void main(String[] args){
      GUI frame = new GUI();
      frame.setResizable(false);
      frame.setVisible(true);
   }
}