import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame implements ActionListener
{
   int i, j;                                                                  //Iterators for board locations
   Players p1 = null;                                                         //Player 1 info
   Players p2 = null;                                                         //Player 2 info
   Image lightSquare = new ImageIcon("Images/LightSquare.png").getImage();    //Light square and
   Image darkSquare = new ImageIcon("Images/DarkSquare.png").getImage();      //Dark square for board
   GridLayout layout = new GridLayout(0, 8);                                  //GridLayout to place buttons under board tiles
   JPanel framePanel = new JPanel(layout);                                    //JPanel to contain GUI elements
   JMenuBar menuBar = new JMenuBar();                                         //Menu bar
   JMenu gameMenu = new JMenu("Game");                                        //Menu for general setup
   JMenuItem setPlayerNames = new JMenuItem("Set Player Names");              //MenuItem for setting player names
   JLabel whitePlayerLabel = new JLabel("White");                             //Label for name
   JLabel vsLabel = new JLabel("vs.");                                        //vs Label will stay the same
   JLabel blackPlayerLabel = new JLabel("Black");                             //Label for name
   JButton[][] gameBoardButtons = new JButton[8][8];                          //Array of buttons on board
   
   public GUI(){
      super("Chess");
      
      setSize(600, 700);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      add(framePanel);
      
      for (i = 0; i < 8; i++){
         for (j = 0; j < 8; j++){
            gameBoardButtons[i][j] = new JButton();
         }
      }
      
      setJMenuBar(menuBar);
      menuBar.add(gameMenu);
      gameMenu.add(setPlayerNames);
      
      gameMenu.setMnemonic('G');
      setPlayerNames.addActionListener(this);
      
      for (i = 0; i < 8; i++){
         for (j = 0; j < 8; j++){
            if ((i + j) % 2 == 0){  //Alternates placement of light and dark squares
               gameBoardButtons[i][j].setIcon(new ImageIcon(lightSquare));
            }
            else{
               gameBoardButtons[i][j].setIcon(new ImageIcon(darkSquare));
            }
         }
      }
      
      for (i = 0; i < 8; i++){
         for (j = 0; j < 8; j++){
            framePanel.add(gameBoardButtons[i][j]);
            gameBoardButtons[i][j].addActionListener(this);
         }
      }
      
      framePanel.add(new JScrollPane(whitePlayerLabel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED));
      framePanel.add(vsLabel);
      framePanel.add(new JScrollPane(blackPlayerLabel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED));
      framePanel.add(squareClickedLabel);
   }
   
   /*
   setPlayerNames (74): JMenuItem; sets white and black player names to values in JOptionPane input
   gameBoardButtons (92): JButton Array; If a piece is alive on chosen square it is selected, otherwise the selected piece is moved to the square
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
      else{
         for (i = 0; i < 8; i++){
            for (j = 0; j < 8; j++){
               if (source == gameBoardButtons[i][j]){
                  //Turn taken if button on board is pressed
               }
            }
         }
      }
   }
   
   public static void main(String[] args){
      GUI myFrame = new GUI();
      myFrame.setVisible(true);
   }
}