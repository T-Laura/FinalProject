import java.util.LinkedList;

public class Queens extends Pieces
{
   public Queens(int pieceRank, char pieceFile, boolean white) throws UnavailableSquareException{
      super(pieceRank, pieceFile, white);
   }
   
   @Override
   public LinkedList<String> getAvailableMoves(LinkedList<Pieces> otherPieces){
      LinkedList<String> availableMoves = new LinkedList<>();
      int i = 1;
      boolean blocked = false;
      while (i <= 8 - this.getRank()){
         for (Pieces piece : otherPieces){
            if ((piece.getFile() == this.getFile()) && (piece.getRank() == (this.getRank() + i))){
               blocked = true;
               if (piece.getWhitePiece() != this.getWhitePiece()){
                  availableMoves.add(String.valueOf(piece.getFile()) + String.valueOf(piece.getRank()));
               }
            }
         }
         if (!blocked){
            availableMoves.add(String.valueOf(this.getFile()) + String.valueOf(this.getRank() + i));
         }
         i++;
         if (blocked){
            break;
         }
      }
      blocked = false;
      i = 1;
      while (i <= this.getRank() - 1){
         for (Pieces piece : otherPieces){
            if ((piece.getFile() == this.getFile()) && (piece.getRank() == (this.getRank() - i))){
               blocked = true;
               if (piece.getWhitePiece() != this.getWhitePiece()){
                  availableMoves.add(String.valueOf(piece.getFile()) + String.valueOf(piece.getRank()));
               }
            }
         }
         if (!blocked){
            availableMoves.add(String.valueOf(this.getFile()) + String.valueOf(this.getRank() - i));
         }
         i++;
         if (blocked){
            break;
         }
      }
      blocked = false;
      i = 1;
      try{
         while (i <= 8 - Pieces.charFileToInt(this.getFile())){
            for (Pieces piece : otherPieces){
               if ((Pieces.charFileToInt(piece.getFile()) == (Pieces.charFileToInt(this.getFile()) + i)) && (piece.getRank() == this.getRank())){
                  blocked = true;
                  if (piece.getWhitePiece() != this.getWhitePiece()){
                     availableMoves.add(String.valueOf(piece.getFile()) + String.valueOf(piece.getRank()));
                  }
               }
            }
            if (!blocked){
               availableMoves.add(String.valueOf(Pieces.intToCharFile(Pieces.charFileToInt(this.getFile()) + i)) + String.valueOf(this.getRank()));
            }
            i++;
            if (blocked){
               break;
            }
         }
      }
      catch (UnavailableSquareException e){
      }
      blocked = false;
      i = 1;
      try{
         while (i <= Pieces.charFileToInt(this.getFile()) - 1){
            for (Pieces piece : otherPieces){
               if ((Pieces.charFileToInt(piece.getFile()) == (Pieces.charFileToInt(this.getFile()) - i)) && (piece.getRank() == this.getRank())){
                  blocked = true;
                  if (piece.getWhitePiece() != this.getWhitePiece()){
                     availableMoves.add(String.valueOf(piece.getFile()) + String.valueOf(piece.getRank()));
                  }
               }
            }
            if (!blocked){
               availableMoves.add(String.valueOf(Pieces.intToCharFile(Pieces.charFileToInt(this.getFile()) - i)) + String.valueOf(this.getRank()));
            }
            i++;
            if (blocked){
               break;
            }
         }
      }
      catch (UnavailableSquareException e){
      }
      blocked = false;
      i = 1;
      try{
         while ((i <= 8 - Pieces.charFileToInt(this.getFile())) && (i <= 8 - this.getRank())){
            for (Pieces piece : otherPieces){
               if ((Pieces.charFileToInt(piece.getFile()) == (Pieces.charFileToInt(this.getFile()) + i)) && (piece.getRank() == (this.getRank() + i))){
                  blocked = true;
                  if (piece.getWhitePiece() != this.getWhitePiece()){
                     availableMoves.add(String.valueOf(piece.getFile()) + String.valueOf(piece.getRank()));
                  }
               }
            }
            if (!blocked){
               availableMoves.add(String.valueOf(Pieces.intToCharFile(Pieces.charFileToInt(this.getFile()) + i)) + String.valueOf(this.getRank() + i));
            }
            i++;
            if (blocked){
               break;
            }
         }
      }
      catch (UnavailableSquareException e){
      }
      blocked = false;
      i = 1;
      try{
         while ((i <= Pieces.charFileToInt(this.getFile()) - 1) && (i <= 8 - this.getRank())){
            for (Pieces piece : otherPieces){
               if ((Pieces.charFileToInt(piece.getFile()) == (Pieces.charFileToInt(this.getFile()) - i)) && (piece.getRank() == (this.getRank() + i))){
                  blocked = true;
                  if (piece.getWhitePiece() != this.getWhitePiece()){
                     availableMoves.add(String.valueOf(piece.getFile()) + String.valueOf(piece.getRank()));
                  }
               }
            }
            if (!blocked){
               availableMoves.add(String.valueOf(Pieces.intToCharFile(Pieces.charFileToInt(this.getFile()) - i)) + String.valueOf(this.getRank() + i));
            }
            i++;
            if (blocked){
               break;
            }
         }
      }
      catch (UnavailableSquareException e){
      }
      blocked = false;
      i = 1;
      try{
         while ((i <= Pieces.charFileToInt(this.getFile()) - 1) && (i <= this.getRank() - 1)){
            for (Pieces piece : otherPieces){
               if ((Pieces.charFileToInt(piece.getFile()) == (Pieces.charFileToInt(this.getFile()) - i)) && (piece.getRank() == (this.getRank() - i))){
                  blocked = true;
                  if (piece.getWhitePiece() != this.getWhitePiece()){
                     availableMoves.add(String.valueOf(piece.getFile()) + String.valueOf(piece.getRank()));
                  }
               }
            }
            if (!blocked){
               availableMoves.add(String.valueOf(Pieces.intToCharFile(Pieces.charFileToInt(this.getFile()) - i)) + String.valueOf(this.getRank() - i));
            }
            i++;
            if (blocked){
               break;
            }
         }
      }
      catch (UnavailableSquareException e){
      }
      blocked = false;
      i = 1;
      try{
         while ((i <= 8 - Pieces.charFileToInt(this.getFile())) && (i <= this.getRank() - 1)){
            for (Pieces piece : otherPieces){
               if ((Pieces.charFileToInt(piece.getFile()) == (Pieces.charFileToInt(this.getFile()) + i)) && (piece.getRank() == (this.getRank() - i))){
                  blocked = true;
                  if (piece.getWhitePiece() != this.getWhitePiece()){
                     availableMoves.add(String.valueOf(piece.getFile()) + String.valueOf(piece.getRank()));
                  }
               }
            }
            if (!blocked){
               availableMoves.add(String.valueOf(Pieces.intToCharFile(Pieces.charFileToInt(this.getFile()) + i)) + String.valueOf(this.getRank() - i));
            }
            i++;
            if (blocked){
               break;
            }
         }
      }
      catch (UnavailableSquareException e){
      }
      return availableMoves;
   }
}