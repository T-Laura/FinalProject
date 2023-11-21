import java.util.LinkedList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Board implements ActionListener
{
   private int i, j;
   private boolean whitesTurn;
   private boolean pieceSelected;
   private String selectedTile;
   private Pieces selectedPiece;
   private Pawns previousPawn;
   private LinkedList<Pieces> piecesOnBoard;
   private Image lightSquare = new ImageIcon("Images/LightSquare.png").getImage();
   private Image darkSquare = new ImageIcon("Images/DarkSquare.png").getImage();
   private Image[][] pawnImages = {{new ImageIcon("Images/WhitePawnLightSquare.png").getImage(), new ImageIcon("Images/WhitePawnDarkSquare.png").getImage()}
                                  ,{new ImageIcon("Images/BlackPawnLightSquare.png").getImage(), new ImageIcon("Images/BlackPawnDarkSquare.png").getImage()}};
   private Image[][] bishopImages = {{new ImageIcon("Images/WhiteBishopLightSquare.png").getImage(), new ImageIcon("Images/WhiteBishopDarkSquare.png").getImage()}
                                    ,{new ImageIcon("Images/BlackBishopLightSquare.png").getImage(), new ImageIcon("Images/BlackBishopDarkSquare.png").getImage()}};
   private Image[][] queenImages = {{new ImageIcon("Images/WhiteQueenLightSquare.png").getImage(), new ImageIcon("Imgaes/WhiteQueenDarkSquare.png").getImage()}
                                   ,{new ImageIcon("Images/BlackQueenLightSquare.png").getImage(), new ImageIcon("Images/BlackQueenDarkSquare.png").getImage()}};
   private Image[][] rookImages = {{new ImageIcon("Images/WhiteRookLightSquare.png").getImage(), new ImageIcon("Images/WhiteRookDarkSquare.png").getImage()}
                                  ,{new ImageIcon("Images/BlackRookLightSquare.png").getImage(), new ImageIcon("Images/BlackRookDarkSquare.png").getImage()}};
   private JButton[][] gameBoardButtons = new JButton[8][8];
   
   public Board(){
      for (i = 0; i < 8; i++){
         for (j = 0; j < 8; j++){
            gameBoardButtons[i][j] = new JButton();
            gameBoardButtons[i][j].addActionListener(this);
         }
      }
      
      this.resetGame();
   }
   
   @Override
   public void actionPerformed(ActionEvent e){
      Object source = e.getSource();
      for (i = 0; i < 8; i++){
         for (j = 0; j < 8; j++){
            if (source == gameBoardButtons[i][j]){
               if (!pieceSelected){
                  try{
                     selectedTile = String.valueOf(Pieces.intToCharFile(j + 1)) + String.valueOf(8 - i);
                     for (Pieces piece : piecesOnBoard){
                        if (((String.valueOf(piece.getFile()) + String.valueOf(piece.getRank())).equals(selectedTile)) && (piece.getWhitePiece() == whitesTurn)){
                           pieceSelected = true;
                           selectedPiece = piece;
                        }
                     }
                  }
                  catch (UnavailableSquareException ex){
                     JOptionPane.showMessageDialog(null, ex.getMessage());
                  }
               }
               else{
                  try{
                     if (selectedPiece.getAvailableMoves(piecesOnBoard).contains(String.valueOf(Pieces.intToCharFile(j + 1)) + String.valueOf(8 - i))){
                        for (Pieces piece : piecesOnBoard){
                           if ((piece.getFile() == Pieces.intToCharFile(j + 1)) && (piece.getRank() == (8 - i))){
                              piecesOnBoard.remove(piece);
                              break;
                           }
                           if (piece instanceof Pawns && selectedPiece instanceof Pawns){
                              if (previousPawn == (Pawns)piece && previousPawn.getMovedTwice()){
                                 if (selectedPiece.getWhitePiece()){
                                    if ((piece.getFile() == Pieces.intToCharFile(j + 1)) && (piece.getRank() == ((8 - i) - 1))){
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
                                    if ((piece.getFile() == Pieces.intToCharFile(j + 1)) && (piece.getRank() == ((8 - i) + 1))){
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
                        if (whitesTurn){
                           pieceColor = 0;
                        }
                        else{
                           pieceColor = 1;
                        }
                        int pieceType;
                        if (selectedPiece instanceof Pawns){
                           if ((i + j) % 2 == 0){
                              gameBoardButtons[i][j].setIcon(new ImageIcon(pawnImages[pieceColor][0]));
                           }
                           else{
                              gameBoardButtons[i][j].setIcon(new ImageIcon(pawnImages[pieceColor][1]));
                           }
                        }
                        else if (selectedPiece instanceof Bishops){
                           if ((i + j) % 2 == 0){
                              gameBoardButtons[i][j].setIcon(new ImageIcon(bishopImages[pieceColor][0]));
                           }
                           else{
                              gameBoardButtons[i][j].setIcon(new ImageIcon(bishopImages[pieceColor][1]));
                           }
                        }
                        else if (selectedPiece instanceof Queens){
                           if ((i + j) % 2 == 0){
                              gameBoardButtons[i][j].setIcon(new ImageIcon(queenImages[pieceColor][0]));
                           }
                           else{
                              gameBoardButtons[i][j].setIcon(new ImageIcon(queenImages[pieceColor][1]));
                           }
                        }
                        else if (selectedPiece instanceof Rooks){
                           if ((i + j) % 2 == 0){
                              gameBoardButtons[i][j].setIcon(new ImageIcon(rookImages[pieceColor][0]));
                           }
                           else{
                              gameBoardButtons[i][j].setIcon(new ImageIcon(rookImages[pieceColor][1]));
                           }
                        }
                        if ((Pieces.charFileToInt(selectedTile.charAt(0)) + Integer.parseInt(String.valueOf(selectedTile.charAt(1)))) % 2 == 0){
                           gameBoardButtons[8 - Integer.parseInt(String.valueOf(selectedTile.charAt(1)))][Pieces.charFileToInt(selectedTile.charAt(0)) - 1].setIcon(new ImageIcon(darkSquare));
                        }
                        else{
                           gameBoardButtons[8 - Integer.parseInt(String.valueOf(selectedTile.charAt(1)))][Pieces.charFileToInt(selectedTile.charAt(0)) - 1].setIcon(new ImageIcon(lightSquare));
                        }
                        int previousRank = selectedPiece.getRank();
                        selectedPiece.setPosition(8 - i, Pieces.intToCharFile(j + 1));
                        if (selectedPiece instanceof Pawns){
                           if ((selectedPiece.getRank() - 2) == previousRank || (selectedPiece.getRank() + 2) == previousRank){
                              previousPawn = (Pawns)selectedPiece;
                              previousPawn.setMovedTwice(true);
                           }
                        }
                        if (selectedPiece instanceof Pawns){
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
                        pieceSelected = false;
                        whitesTurn = !whitesTurn;
                        if (previousPawn != null && previousPawn.getWhitePiece() == whitesTurn){
                           previousPawn.setMovedTwice(false);
                        }
                     }
                     else{
                        pieceSelected = false;
                        for (Pieces piece : piecesOnBoard){
                           if (((String.valueOf(piece.getFile()) + String.valueOf(piece.getRank())).equals(String.valueOf(Pieces.intToCharFile(j + 1)) + String.valueOf(8 - i))) && (whitesTurn == piece.getWhitePiece())){
                              pieceSelected = true;
                              selectedTile = String.valueOf(Pieces.intToCharFile(j + 1)) + String.valueOf(8 - i);
                              selectedPiece = piece;
                           }
                        }
                     }
                  }
                  catch (UnavailableSquareException ex){
                     JOptionPane.showMessageDialog(null, ex.getMessage());
                  }
               }
            }
         }
      }
   }
   
   public void resetGame(){
      gameBoardButtons[0][0].setIcon(new ImageIcon(rookImages[1][0]));
      gameBoardButtons[0][1].setIcon(new ImageIcon(darkSquare));
      gameBoardButtons[0][2].setIcon(new ImageIcon(bishopImages[1][0]));
      gameBoardButtons[0][3].setIcon(new ImageIcon(queenImages[1][1]));
      gameBoardButtons[0][4].setIcon(new ImageIcon(lightSquare));
      gameBoardButtons[0][5].setIcon(new ImageIcon(bishopImages[1][1]));
      gameBoardButtons[0][6].setIcon(new ImageIcon(lightSquare));
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
      gameBoardButtons[7][1].setIcon(new ImageIcon(lightSquare));
      gameBoardButtons[7][2].setIcon(new ImageIcon(bishopImages[0][1]));
      gameBoardButtons[7][3].setIcon(new ImageIcon(queenImages[0][0]));
      gameBoardButtons[7][4].setIcon(new ImageIcon(darkSquare));
      gameBoardButtons[7][5].setIcon(new ImageIcon(bishopImages[0][0]));
      gameBoardButtons[7][6].setIcon(new ImageIcon(darkSquare));
      gameBoardButtons[7][7].setIcon(new ImageIcon(rookImages[0][0]));
      
      piecesOnBoard = new LinkedList<>();
      
      try{
         for (i = 1; i < 9; i++){
            piecesOnBoard.add(new Pawns(7, Pieces.intToCharFile(i), false)); //[0] - [7]
         }
         for (i = 1; i < 9; i++){
            piecesOnBoard.add(new Pawns(2, Pieces.intToCharFile(i), true)); //[8] - [15]
         }
         piecesOnBoard.add(new Rooks(8, 'a', false));
         piecesOnBoard.add(new Bishops(8, 'c', false));
         piecesOnBoard.add(new Queens(8, 'd', false));
         piecesOnBoard.add(new Bishops(8, 'f', false));
         piecesOnBoard.add(new Rooks(8, 'h', false));
         piecesOnBoard.add(new Rooks(1, 'a', true));
         piecesOnBoard.add(new Bishops(1, 'c', true));
         piecesOnBoard.add(new Queens(1, 'd', true));
         piecesOnBoard.add(new Bishops(1, 'f', true));
         piecesOnBoard.add(new Rooks(1, 'h', true));
      }
      catch (UnavailableSquareException e){
      }
      
      whitesTurn = true;
      pieceSelected = false;
   }
   
   public JButton[][] getButtonsArray(){
      return gameBoardButtons;
   }
}