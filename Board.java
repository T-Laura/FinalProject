import java.util.LinkedList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import static java.nio.file.StandardOpenOption.*;

public class Board implements ActionListener
{
   private int i, j;                            //Iterators commonly used
   private boolean whitesTurn;                  //Tracker for current turn
   private boolean pieceSelected;               //For determining if a move can be made
   private String selectedTile;                 //For determining valid piece selections
   private Pieces selectedPiece;                //Memory for current selected piece
   private Pawns previousPawn;                  //Memory for previous selected piece if it was a pawn (for En Passant)
   private LinkedList<Pieces> piecesOnBoard;    //LinkedList for containing pieces currently on board
   private Image lightSquare = new ImageIcon("Images/LightSquare.png").getImage();  //Basic square for white spaces
   private Image darkSquare = new ImageIcon("Images/DarkSquare.png").getImage();    //Basic square for black spaces
   private Image[][] pawnImages = {{new ImageIcon("Images/WhitePawnLightSquare.png").getImage(), new ImageIcon("Images/WhitePawnDarkSquare.png").getImage()}
   /*Images for all pawns*/       ,{new ImageIcon("Images/BlackPawnLightSquare.png").getImage(), new ImageIcon("Images/BlackPawnDarkSquare.png").getImage()}};
   private Image[][] bishopImages = {{new ImageIcon("Images/WhiteBishopLightSquare.png").getImage(), new ImageIcon("Images/WhiteBishopDarkSquare.png").getImage()}
   /*Images for all bishops*/       ,{new ImageIcon("Images/BlackBishopLightSquare.png").getImage(), new ImageIcon("Images/BlackBishopDarkSquare.png").getImage()}};
   private Image[][] kingImages = {{new ImageIcon("Images/WhiteKingLightSquare.png").getImage(), new ImageIcon("Images/WhiteKingDarkSquare.png").getImage()}
   /*Images for all kings*/       ,{new ImageIcon("Images/BlackKingLightSquare.png").getImage(), new ImageIcon("Images/BlackKingDarkSquare.png").getImage()}};
   private Image[][] knightImages = {{new ImageIcon("Images/WhiteKnightLightSquare.png").getImage(), new ImageIcon("Images/WhiteKnightDarkSquare.png").getImage()}
   /*Images for all knights*/       ,{new ImageIcon("Images/BlackKnightLightSquare.png").getImage(), new ImageIcon("Images/BlackKnightDarkSquare.png").getImage()}};
   private Image[][] queenImages = {{new ImageIcon("Images/WhiteQueenLightSquare.png").getImage(), new ImageIcon("Images/WhiteQueenDarkSquare.png").getImage()}
   /*Images for all queens*/       ,{new ImageIcon("Images/BlackQueenLightSquare.png").getImage(), new ImageIcon("Images/BlackQueenDarkSquare.png").getImage()}};
   private Image[][] rookImages = {{new ImageIcon("Images/WhiteRookLightSquare.png").getImage(), new ImageIcon("Images/WhiteRookDarkSquare.png").getImage()}
   /*Images for all rooks*/       ,{new ImageIcon("Images/BlackRookLightSquare.png").getImage(), new ImageIcon("Images/BlackRookDarkSquare.png").getImage()}};
   private JButton[][] gameBoardButtons = new JButton[8][8];                                    //Button array for game board
   private JLabel[] namesArray = {new JLabel("White"), new JLabel("vs"), new JLabel("Black")};  //JLabels for player names
   private JTextField notationField = new JTextField();                                         //JTextField to hold notations
   private JButton notationButton = new JButton("Notation Move");                               //JButton for activating notationField
   
   //Instantiates buttons and adds ActionListeners; Also call Board.resetGame()
   public Board(){
      for (i = 0; i < 8; i++){
         for (j = 0; j < 8; j++){
            gameBoardButtons[i][j] = new JButton();
            gameBoardButtons[i][j].addActionListener(this);
         }
      }
      notationButton.addActionListener(this);
      
      this.resetGame();
   }
   
   @Override
   public void actionPerformed(ActionEvent event){
      Object source = event.getSource();
      if (source == notationButton){
         boolean validPiece = false;
         boolean validMove = false;
         for (Pieces piece : piecesOnBoard){
            if (piece.getFile() == notationField.getText().charAt(0) && String.valueOf(piece.getRank()).equals(String.valueOf(notationField.getText().charAt(1))) && whitesTurn == piece.getWhitePiece()){
               validPiece = true;
               if (piece.getAvailableMoves(piecesOnBoard).contains(String.valueOf(notationField.getText().charAt(3)) + String.valueOf(notationField.getText().charAt(4)))){
                  validMove = true;
                  try{
                     selectedTile = String.valueOf(notationField.getText().charAt(0)) + String.valueOf(notationField.getText().charAt(1));
                     pieceSelected = true;
                     selectedPiece = piece;
                     source = gameBoardButtons[8 - Integer.parseInt(String.valueOf(notationField.getText().charAt(4)))][Pieces.charFileToInt(notationField.getText().charAt(3)) - 1];
                     break;
                  }
                  catch (UnavailableSquareException e){
                     JOptionPane.showMessageDialog(null, e.getMessage() + "(" + notationField.getText().charAt(3) + notationField.getText().charAt(4) + ")");
                  }
               }
            }
         }
         if (!validPiece){
            JOptionPane.showMessageDialog(null, "No valid pieces on " + notationField.getText().charAt(0) + notationField.getText().charAt(1));
         }
         else if (!validMove){
            JOptionPane.showMessageDialog(null, "Selected piece can't move to " + notationField.getText().charAt(3) + notationField.getText().charAt(4));
         }
      }
      for (i = 0; i < 8; i++){
         for (j = 0; j < 8; j++){                     //Iterator finds location of button pressed
            if (source == gameBoardButtons[i][j]){    //Space on board is denoted as j+1(as characters 'a' through 'h'), 8-i
               if (!pieceSelected){
                  try{                 //If last button pressed wasn't valid piece
                     selectedTile = String.valueOf(Pieces.intToCharFile(j + 1)) + String.valueOf(8 - i);
                     for (Pieces piece : piecesOnBoard){       //Check all pieces for if they equal the selected tile and are on the team of the player whose turn it is
                        if (((String.valueOf(piece.getFile()) + String.valueOf(piece.getRank())).equals(selectedTile)) && (piece.getWhitePiece() == whitesTurn)){
                           pieceSelected = true;
                           selectedPiece = piece;
                        }
                     }
                  }
                  catch (UnavailableSquareException e){
                     JOptionPane.showMessageDialog(null, e.getMessage());
                  }
               }
               else{                   //If last button pressed was a valid piece
                  try{                 //Check if new button pressed is contained within valid piece moves of previous button
                     if (selectedPiece.getAvailableMoves(piecesOnBoard).contains(String.valueOf(Pieces.intToCharFile(j + 1)) + String.valueOf(8 - i))){
                        for (Pieces piece : piecesOnBoard){    //Check if space piece moved to contained another piece
                           if ((piece.getFile() == Pieces.intToCharFile(j + 1)) && (piece.getRank() == (8 - i))){
                              piecesOnBoard.remove(piece);
                              break;
                           }
                           if (piece instanceof Pawns && selectedPiece instanceof Pawns){    //Check En Passant capture and replace image of pawn with blank tile
                              if (previousPawn == (Pawns)piece && previousPawn.getMovedTwice()){
                                 if (selectedPiece.getWhitePiece()){
                                    if ((piece.getFile() == Pieces.intToCharFile(j + 1)) && (piece.getRank() == ((8 - i) - 1))){    //-1 for white direction
                                       if ((Pieces.charFileToInt(selectedTile.charAt(0)) + Integer.parseInt(String.valueOf(selectedTile.charAt(1)))) % 2 == 0){
                                          gameBoardButtons[8 - piece.getRank()][Pieces.charFileToInt(piece.getFile()) - 1].setIcon(new ImageIcon(lightSquare));
                                       }
                                       else{
                                          gameBoardButtons[8 - piece.getRank()][Pieces.charFileToInt(piece.getFile()) - 1].setIcon(new ImageIcon(darkSquare));
                                       }
                                       piecesOnBoard.remove(piece);
                                       break;
                                    }
                                 }
                                 else{
                                    if ((piece.getFile() == Pieces.intToCharFile(j + 1)) && (piece.getRank() == ((8 - i) + 1))){    //+1 for black direction
                                       if ((Pieces.charFileToInt(selectedTile.charAt(0)) + Integer.parseInt(String.valueOf(selectedTile.charAt(1)))) % 2 == 0){
                                          gameBoardButtons[8 - piece.getRank()][Pieces.charFileToInt(piece.getFile()) - 1].setIcon(new ImageIcon(lightSquare));
                                       }
                                       else{
                                          gameBoardButtons[8 - piece.getRank()][Pieces.charFileToInt(piece.getFile()) - 1].setIcon(new ImageIcon(darkSquare));
                                       }
                                       piecesOnBoard.remove(piece);
                                       break;
                                    }
                                 }
                              }
                           }
                        }
                        int pieceColor;
                        if (whitesTurn){     //assign piece color for Image Arrays
                           pieceColor = 0;
                        }
                        else{
                           pieceColor = 1;
                        }
                        if (selectedPiece instanceof Pawns){      //Move pawn to square
                           if ((i + j) % 2 == 0){
                              gameBoardButtons[i][j].setIcon(new ImageIcon(pawnImages[pieceColor][0]));
                           }
                           else{
                              gameBoardButtons[i][j].setIcon(new ImageIcon(pawnImages[pieceColor][1]));
                           }
                        }
                        else if (selectedPiece instanceof Bishops){     //Move bishop to square
                           if ((i + j) % 2 == 0){
                              gameBoardButtons[i][j].setIcon(new ImageIcon(bishopImages[pieceColor][0]));
                           }
                           else{
                              gameBoardButtons[i][j].setIcon(new ImageIcon(bishopImages[pieceColor][1]));
                           }
                        }
                        else if (selectedPiece instanceof Kings){       //Move king to square
                           if ((i + j) % 2 == 0){
                              gameBoardButtons[i][j].setIcon(new ImageIcon(kingImages[pieceColor][0]));
                           }
                           else{
                              gameBoardButtons[i][j].setIcon(new ImageIcon(kingImages[pieceColor][1]));
                           }
                        }
                        else if (selectedPiece instanceof Knights){     //Move knight to square
                           if ((i + j) % 2 == 0){
                              gameBoardButtons[i][j].setIcon(new ImageIcon(knightImages[pieceColor][0]));
                           }
                           else{
                              gameBoardButtons[i][j].setIcon(new ImageIcon(knightImages[pieceColor][1]));
                           }
                        }
                        else if (selectedPiece instanceof Queens){      //Move queen to square
                           if ((i + j) % 2 == 0){
                              gameBoardButtons[i][j].setIcon(new ImageIcon(queenImages[pieceColor][0]));
                           }
                           else{
                              gameBoardButtons[i][j].setIcon(new ImageIcon(queenImages[pieceColor][1]));
                           }
                        }
                        else if (selectedPiece instanceof Rooks){       //Move rook to square
                           if ((i + j) % 2 == 0){
                              gameBoardButtons[i][j].setIcon(new ImageIcon(rookImages[pieceColor][0]));
                           }
                           else{
                              gameBoardButtons[i][j].setIcon(new ImageIcon(rookImages[pieceColor][1]));
                           }
                        }        //Make old square selection into emplty tile
                        if ((Pieces.charFileToInt(selectedTile.charAt(0)) + Integer.parseInt(String.valueOf(selectedTile.charAt(1)))) % 2 == 0){
                           gameBoardButtons[8 - Integer.parseInt(String.valueOf(selectedTile.charAt(1)))][Pieces.charFileToInt(selectedTile.charAt(0)) - 1].setIcon(new ImageIcon(darkSquare));
                        }
                        else{
                           gameBoardButtons[8 - Integer.parseInt(String.valueOf(selectedTile.charAt(1)))][Pieces.charFileToInt(selectedTile.charAt(0)) - 1].setIcon(new ImageIcon(lightSquare));
                        }
                        int previousRank = selectedPiece.getRank();                       //Check if pawn was moved twice (for En Passant)
                        char previousFile = selectedPiece.getFile();                      //Check if king was moved twice (for Castling)
                        selectedPiece.setPosition(8 - i, Pieces.intToCharFile(j + 1));
                        if (selectedPiece instanceof Pawns){      //Check for En Passant
                           if ((selectedPiece.getRank() - 2) == previousRank || (selectedPiece.getRank() + 2) == previousRank){
                              previousPawn = (Pawns)selectedPiece;
                              previousPawn.setMovedTwice(true);
                           }
                        }
                        if (selectedPiece instanceof Pawns){      //Check for pawn promotions (pawn on first/last rank)
                           previousPawn = (Pawns)selectedPiece;
                           if (previousPawn.checkLastRank()){
                              selectedPiece = new Queens(selectedPiece.getRank(), selectedPiece.getFile(), selectedPiece.getWhitePiece());
                              if ((i + j) % 2 == 0){
                                 gameBoardButtons[i][j].setIcon(new ImageIcon(queenImages[pieceColor][0]));
                              }
                              else{
                                 gameBoardButtons[i][j].setIcon(new ImageIcon(queenImages[pieceColor][1]));
                              }
                           }
                        }
                        if (selectedPiece instanceof Kings){      //Check for Castling
                           if (Pieces.charFileToInt(previousFile) == (Pieces.charFileToInt(selectedPiece.getFile()) - 2)){
                              for (Pieces piece : piecesOnBoard){
                                 if (piece.getFile() == 'h' && (piece instanceof Rooks) && piece.getWhitePiece() == selectedPiece.getWhitePiece()){
                                    piece.setPosition(piece.getRank(), 'f');
                                    if ((i + j) % 2 == 0){
                                       gameBoardButtons[i][j - 1].setIcon(new ImageIcon(rookImages[pieceColor][1]));
                                    }
                                    else{
                                       gameBoardButtons[i][j - 1].setIcon(new ImageIcon(rookImages[pieceColor][0]));
                                    }
                                    if ((i + j) % 2 == 0){
                                       gameBoardButtons[i][j + 1].setIcon(new ImageIcon(darkSquare));
                                    }
                                    else{
                                       gameBoardButtons[i][j + 1].setIcon(new ImageIcon(lightSquare));
                                    }
                                    break;
                                 }
                              }
                           }        //Check long castle
                           else if (Pieces.charFileToInt(previousFile) == (Pieces.charFileToInt(selectedPiece.getFile()) + 2)){
                              for (Pieces piece : piecesOnBoard){
                                 if (piece.getFile() == 'a' && (piece instanceof Rooks)){
                                    piece.setPosition(selectedPiece.getRank(), 'd');
                                    if ((i + j) % 2 == 0){
                                       gameBoardButtons[i][j + 1].setIcon(new ImageIcon(rookImages[pieceColor][1]));
                                    }
                                    else{
                                       gameBoardButtons[i][j + 1].setIcon(new ImageIcon(rookImages[pieceColor][0]));
                                    }
                                    if ((i + j) % 2 == 0){
                                       gameBoardButtons[i][j - 2].setIcon(new ImageIcon(lightSquare));
                                    }
                                    else{
                                       gameBoardButtons[i][j - 2].setIcon(new ImageIcon(darkSquare));
                                    }
                                    break;
                                 }
                              }
                           }
                        }
                        pieceSelected = false;
                        whitesTurn = !whitesTurn;
                        if (previousPawn != null && previousPawn.getWhitePiece() == whitesTurn){      //Reset pawn double moves for next player (for En Passant)
                           previousPawn.setMovedTwice(false);
                        }
                        boolean whiteKingAlive = false;
                        boolean blackKingAlive = false;
                        for (Pieces piece : piecesOnBoard){
                           if (piece instanceof Kings){
                              if (piece.getWhitePiece()){
                                 whiteKingAlive = true;
                              }
                              if (!piece.getWhitePiece()){
                                 blackKingAlive = true;
                              }
                           }
                        }
                        if (!whiteKingAlive){
                           if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Would you like to save game result to a file?", null, JOptionPane.YES_NO_OPTION)){
                              OutputStream output = null;
                              try{
                                 output = new BufferedOutputStream(Files.newOutputStream(Paths.get(JOptionPane.showInputDialog(null, "Enter the filepath for results to be saved to")), WRITE));
                                 output.write((namesArray[0].getText() + " 0 - 1 " + namesArray[2].getText()).getBytes());
                                 output.flush();
                                 output.close();
                              }
                              catch (IOException e){
                                 JOptionPane.showMessageDialog(null, "IO Exception");
                              }
                           }
                           resetGame();
                        }
                        else if (!blackKingAlive){
                           if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Would you like to save game result to a file?", null, JOptionPane.YES_NO_OPTION)){
                              OutputStream output = null;
                              try{
                                 output = new BufferedOutputStream(Files.newOutputStream(Paths.get(JOptionPane.showInputDialog(null, "Enter the filepath for results to be saved to")), WRITE));
                                 output.write((namesArray[0].getText() + " 1 - 0 " + namesArray[2].getText()).getBytes());
                                 output.flush();
                                 output.close();
                              }
                              catch (IOException e){
                                 JOptionPane.showMessageDialog(null, "IO Exception");
                              }
                           }
                           resetGame();
                        }
                     }
                     else{       //Available move was not selected
                        pieceSelected = false;
                        for (Pieces piece : piecesOnBoard){       //Check if new selection is allowed
                           if (((String.valueOf(piece.getFile()) + String.valueOf(piece.getRank())).equals(String.valueOf(Pieces.intToCharFile(j + 1)) + String.valueOf(8 - i))) && (whitesTurn == piece.getWhitePiece())){
                              pieceSelected = true;
                              selectedTile = String.valueOf(Pieces.intToCharFile(j + 1)) + String.valueOf(8 - i);
                              selectedPiece = piece;
                           }
                        }
                     }
                  }
                  catch (UnavailableSquareException e){
                     JOptionPane.showMessageDialog(null, e.getMessage());
                  }
               }
            }
         }
      }
   }
   
   //Sets the initial board state and adds values to LinkedList<Pieces> piecesOnBoard
   public void resetGame(){
      gameBoardButtons[0][0].setIcon(new ImageIcon(rookImages[1][0]));
      gameBoardButtons[0][1].setIcon(new ImageIcon(knightImages[1][1]));
      gameBoardButtons[0][2].setIcon(new ImageIcon(bishopImages[1][0]));
      gameBoardButtons[0][3].setIcon(new ImageIcon(queenImages[1][1]));
      gameBoardButtons[0][4].setIcon(new ImageIcon(kingImages[1][0]));
      gameBoardButtons[0][5].setIcon(new ImageIcon(bishopImages[1][1]));
      gameBoardButtons[0][6].setIcon(new ImageIcon(knightImages[1][0]));
      gameBoardButtons[0][7].setIcon(new ImageIcon(rookImages[1][1]));
      
      for (j = 0; j < 8; j += 2){
         gameBoardButtons[1][j].setIcon(new ImageIcon(pawnImages[1][1]));
         gameBoardButtons[1][j + 1].setIcon(new ImageIcon(pawnImages[1][0]));
      }
      for (i = 2; i < 6; i++){
         for (j = 0; j < 8; j++){
            if ((i + j) % 2 == 0){
               gameBoardButtons[i][j].setIcon(new ImageIcon(lightSquare));
            }
            else{
               gameBoardButtons[i][j].setIcon(new ImageIcon(darkSquare));
            }
         }
      }
      for (j = 0; j < 8; j += 2){
         gameBoardButtons[6][j].setIcon(new ImageIcon(pawnImages[0][0]));
         gameBoardButtons[6][j + 1].setIcon(new ImageIcon(pawnImages[0][1]));
      }
      
      gameBoardButtons[7][0].setIcon(new ImageIcon(rookImages[0][1]));
      gameBoardButtons[7][1].setIcon(new ImageIcon(knightImages[0][0]));
      gameBoardButtons[7][2].setIcon(new ImageIcon(bishopImages[0][1]));
      gameBoardButtons[7][3].setIcon(new ImageIcon(queenImages[0][0]));
      gameBoardButtons[7][4].setIcon(new ImageIcon(kingImages[0][1]));
      gameBoardButtons[7][5].setIcon(new ImageIcon(bishopImages[0][0]));
      gameBoardButtons[7][6].setIcon(new ImageIcon(knightImages[0][1]));
      gameBoardButtons[7][7].setIcon(new ImageIcon(rookImages[0][0]));
      
      piecesOnBoard = new LinkedList<>();
      
      try{
         for (i = 1; i < 9; i++){
            piecesOnBoard.add(new Pawns(7, Pieces.intToCharFile(i), false));
         }
         for (i = 1; i < 9; i++){
            piecesOnBoard.add(new Pawns(2, Pieces.intToCharFile(i), true));
         }
         
         piecesOnBoard.add(new Bishops(8, 'c', false));
         piecesOnBoard.add(new Bishops(8, 'f', false));
         piecesOnBoard.add(new Bishops(1, 'c', true));
         piecesOnBoard.add(new Bishops(1, 'f', true));
         piecesOnBoard.add(new Knights(8, 'b', false));
         piecesOnBoard.add(new Knights(8, 'g', false));
         piecesOnBoard.add(new Knights(1, 'b', true));
         piecesOnBoard.add(new Knights(1, 'g', true));
         piecesOnBoard.add(new Rooks(8, 'a', false));
         piecesOnBoard.add(new Rooks(8, 'h', false));
         piecesOnBoard.add(new Rooks(1, 'a', true));
         piecesOnBoard.add(new Rooks(1, 'h', true));
         piecesOnBoard.add(new Queens(8, 'd', false));
         piecesOnBoard.add(new Queens(1, 'd', true));
         piecesOnBoard.add(new Kings(8, 'e', false));
         piecesOnBoard.add(new Kings(1, 'e', true));
      }
      catch (UnavailableSquareException e){
      }
      
      whitesTurn = true;
      pieceSelected = false;
   }
   
   //For sending buttons to JFrame
   public JButton[][] getButtonsArray(){
      return gameBoardButtons;
   }
   
   public JLabel[] getNameLabels(){
      return namesArray;
   }
   
   public JTextField getNotationField(){
      return notationField;
   }
   
   public JButton getNotationButton(){
      return notationButton;
   }
}