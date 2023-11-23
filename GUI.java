import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import static java.nio.file.StandardOpenOption.*;

public class GUI extends JFrame implements ActionListener
{
   int i, j;                                                         //Iterators for board locations
   Players p1 = null;                                                //Player 1 info
   Players p2 = null;                                                //Player 2 info
   GridLayout layout = new GridLayout(0, 8);                         //GridLayout to place buttons under board tiles
   JPanel framePanel = new JPanel(layout);                           //JPanel to contain GUI elements
   JMenuBar menuBar = new JMenuBar();                                //Menu bar
   JMenu gameMenu = new JMenu("Game");                               //Menu for general setup
   JMenu piecesMenu = new JMenu("Pieces");                           //Menu for piece info
   JMenu alternateMovesMenu = new JMenu("Other moves");              //Menu for info on other piece moves
   JMenuItem changeNames = new JMenuItem("Change Player Names");     //MenuItem for changing player names
   JMenuItem endGame = new JMenuItem("End Current Game");            //MenuItem for declaring winner early
   JMenuItem pawnInfo = new JMenuItem("Pawns");                      //MenuItem for info on pawns
   JMenuItem rookInfo = new JMenuItem("Rooks");                      //MenuItem for info on rooks
   JMenuItem knightInfo = new JMenuItem("Knights");                  //MenuItem for info on knights
   JMenuItem bishopInfo = new JMenuItem("Bishops");                  //MenuItem for info on bishops
   JMenuItem queenInfo = new JMenuItem("Queens");                    //MenuItem for info on queens
   JMenuItem kingInfo = new JMenuItem("Kings");                      //MenuItem for info on kings
   JMenuItem enPassantInfo = new JMenuItem("En Passant");            //MenuItem for info on en passant captures
   JMenuItem castlingInfo = new JMenuItem("Castling");               //MenuItem for info on castling
   Board gameBoard = new Board();                                    //Everything regarding movement of pieces
   
   public GUI(){
      super("Chess");
      
      setSize(600, 700);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      add(framePanel);
      
      setJMenuBar(menuBar);
      menuBar.add(gameMenu);
      gameMenu.add(changeNames);
      gameMenu.add(endGame);
      menuBar.add(piecesMenu);
      piecesMenu.add(pawnInfo);
      piecesMenu.add(rookInfo);
      piecesMenu.add(knightInfo);
      piecesMenu.add(bishopInfo);
      piecesMenu.add(queenInfo);
      piecesMenu.add(kingInfo);
      menuBar.add(alternateMovesMenu);
      alternateMovesMenu.add(enPassantInfo);
      alternateMovesMenu.add(castlingInfo);
      
      gameMenu.setMnemonic('G');
      changeNames.addActionListener(this);
      endGame.addActionListener(this);
      piecesMenu.setMnemonic('P');
      pawnInfo.addActionListener(this);
      rookInfo.addActionListener(this);
      knightInfo.addActionListener(this);
      bishopInfo.addActionListener(this);
      queenInfo.addActionListener(this);
      kingInfo.addActionListener(this);
      enPassantInfo.addActionListener(this);
      castlingInfo.addActionListener(this);
      
      for (i = 0; i < 8; i++){
         for (j = 0; j < 8; j++){
            framePanel.add(gameBoard.getButtonsArray()[i][j]);
            
         }
      }
      
      framePanel.add(new JScrollPane(gameBoard.getNameLabels()[0], ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED));
      framePanel.add(gameBoard.getNameLabels()[1]);
      framePanel.add(new JScrollPane(gameBoard.getNameLabels()[2], ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED));
      framePanel.add(gameBoard.getNotationField());
      gameBoard.getNotationField().setToolTipText("Enter notations similar to a1,h8 where the first pair is where the piece starts");
      framePanel.add(gameBoard.getNotationButton());
      
   }
   
   /*
   changeNames (): sets white and black player names through JOptionPanes
   endGame (): asks if results should be saved to file and calls Board.resetGame()
   pawnInfo (): shows JOptionPane with pawn info
   rookInfo (): shows JOptionPane with rook info
   knightInfo (): shows JOptionPane with knight info
   bishopInfo (): shows JOptionPane with bishop info
   queenInfo (): shows JOptionPane with queen info
   kingInfo (): shows JOptionPane with king info
   */
   @Override
   public void actionPerformed(ActionEvent event){
      Object source = event.getSource();
      if (source == changeNames){
         String player1Name = JOptionPane.showInputDialog(null, "Who is playing the white pieces? Leave blank for no name");
         if (player1Name.equals("") || player1Name == null){
            p1 = new Players(true);
         }
         else{
            p1 = new Players(true, player1Name);
         }
         String player2Name = JOptionPane.showInputDialog(null, "Who is playing the black pieces? Leave blank for no name");
         if (player2Name.equals("") || player2Name == null){
            p2 = new Players(false);
         }
         else{
            p2 = new Players(false, player2Name);
         }
         gameBoard.getNameLabels()[0].setText(p1.getName());
         gameBoard.getNameLabels()[2].setText(p2.getName());
      }
      else if (source == endGame){
         if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Would you like to save game result to a file?", null, JOptionPane.YES_NO_OPTION)){
            OutputStream output = null;
            try{
               output = new BufferedOutputStream(Files.newOutputStream(Paths.get(JOptionPane.showInputDialog(null, "Enter the filepath for results to be saved to")), WRITE));
               int optionChosen = JOptionPane.showConfirmDialog(null, "Press yes if white won, no if black won, or cancel if game was a draw", null, JOptionPane.YES_NO_CANCEL_OPTION);
               switch (optionChosen){
                  case (int)JOptionPane.YES_OPTION:
                     output.write((gameBoard.getNameLabels()[0].getText() + " 1 - 0 " + gameBoard.getNameLabels()[2].getText()).getBytes());
                     break;
                  case (int)JOptionPane.NO_OPTION:
                     output.write((gameBoard.getNameLabels()[0].getText() + " 0 - 1 " + gameBoard.getNameLabels()[2].getText()).getBytes());
                     break;
                  default:
                     output.write((gameBoard.getNameLabels()[0].getText() + " 0.5 - 0.5 " + gameBoard.getNameLabels()[2].getText()).getBytes());
                     break;
               }
               output.flush();
               output.close();
            }
            catch (IOException e){
               JOptionPane.showMessageDialog(null, "IO Exception");
            }
         }
         gameBoard.resetGame();
      }
      else if (source == pawnInfo){
         JOptionPane.showMessageDialog(null, "The pawns (found on the 2nd and 7th rows) can only move forwads one space at a time, but they can move twice on their first turn.\nPawns can capture one space forwards diagonally.");
      }
      else if (source == rookInfo){
         JOptionPane.showMessageDialog(null, "The rooks (found in the 1st and 8th columns) can move to any space in the same row or column as them as long as they aren't blocked.");
      }
      else if (source == knightInfo){
         JOptionPane.showMessageDialog(null, "The knights (found in the 2nd and 7th columns) can move 2 spaces vertically and 1 space horizontally,\nor 2 spaces horizontally and 1 space vertically (like the shape of an L). Knights can jump over other pieces.");
      }
      else if (source == bishopInfo){
         JOptionPane.showMessageDialog(null, "The bishops (found in the 3rd and 6th columns) can move to any space in the same diagonal as them as long as they aren't blocked.\n(Bishops will be on their starting color all game)");
      }
      else if (source == queenInfo){
         JOptionPane.showMessageDialog(null, "The queen (found in the 4th column) can move to any space in the same row, column, or diagonal as long as the path isn't blocked.\nThe queen is the most powerful piece on the board.");
      }
      else if (source == kingInfo){
         JOptionPane.showMessageDialog(null, "The king (found in the 5th column) can move to any of the 8 adjacent squares. If your king is taken you will lose the game.");
      }
      else if (source == enPassantInfo){
         JOptionPane.showMessageDialog(null, "A pawn that moved two spaces can be captured by another pawn on the square it passed through.\nThis can only occur if the pawn moved two spaces the previous turn.");
      }
      else if (source == castlingInfo){
         JOptionPane.showMessageDialog(null, "The king can move two spaces and place the rook it moves towards on the other side of it (called castling).\nThis can only happen if neither the king nor the rook moved towards have moved for the entirity of the current game.");
      }
   }
   
   public static void main(String[] args){
      GUI frame = new GUI();
      frame.setResizable(false);
      frame.setVisible(true);
   }
}