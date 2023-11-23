import java.util.LinkedList;

public class Kings extends Pieces
{
   public Kings(int pieceRank, char pieceFile, boolean white) throws UnavailableSquareException{
      super(pieceRank, pieceFile, white);
   }
   
   @Override
   public LinkedList<String> getAvailableMoves(LinkedList<Pieces> otherPieces){
      LinkedList<String> availableMoves = new LinkedList<>();
      boolean blocked = false;
      boolean thisInList = false;
      if (otherPieces.contains(this)){
         otherPieces.remove(this);
         thisInList = true;
      }
      if (this.getRank() + 1 <= 8){
         for (Pieces piece : otherPieces){
            if ((piece.getFile() == this.getFile()) && (piece.getRank() == (this.getRank() + 1)) && (piece.getWhitePiece() == this.getWhitePiece())){
               blocked = true;
               break;
            }
         }
         if (!blocked){
            availableMoves.add(String.valueOf(this.getFile()) + String.valueOf(this.getRank() + 1));
         }
      }
      blocked = false;
      if (this.getRank() - 1 >= 1){
         for (Pieces piece : otherPieces){
            if ((piece.getFile() == this.getFile()) && (piece.getRank() == (this.getRank() - 1)) && (piece.getWhitePiece() == this.getWhitePiece())){
               blocked = true;
               break;
            }
         }
         if (!blocked){
            availableMoves.add(String.valueOf(this.getFile()) + String.valueOf(this.getRank() - 1));
         }
      }
      blocked = false;
      try{
         if (Pieces.charFileToInt(this.getFile()) + 1 <= 8){
            for (Pieces piece : otherPieces){
               if ((piece.getFile() == Pieces.intToCharFile(Pieces.charFileToInt(this.getFile()) + 1)) && (piece.getRank() == this.getRank()) && (piece.getWhitePiece() == this.getWhitePiece())){
                  blocked = true;
                  break;
               }
            }
            if (!blocked){
               availableMoves.add(String.valueOf(Pieces.intToCharFile(Pieces.charFileToInt(this.getFile()) + 1)) + String.valueOf(this.getRank()));
            }
         }
      }
      catch (UnavailableSquareException e){
      }
      blocked = false;
      try{
         if (Pieces.charFileToInt(this.getFile()) - 1 >= 1){
            for (Pieces piece : otherPieces){
               if ((piece.getFile() == Pieces.intToCharFile(Pieces.charFileToInt(this.getFile()) - 1)) && (piece.getRank() == this.getRank()) && (piece.getWhitePiece() == this.getWhitePiece())){
                  blocked = true;
                  break;
               }
            }
            if (!blocked){
               availableMoves.add(String.valueOf(Pieces.intToCharFile(Pieces.charFileToInt(this.getFile()) - 1)) + String.valueOf(this.getRank()));
            }
         }
      }
      catch (UnavailableSquareException e){
      }
      blocked = false;
      try{
         if (Pieces.charFileToInt(this.getFile()) + 1 <= 8 && this.getRank() + 1 <= 8){
            for (Pieces piece : otherPieces){
               if ((piece.getFile() == Pieces.intToCharFile(Pieces.charFileToInt(this.getFile()) + 1)) && (piece.getRank() == (this.getRank() + 1)) && (piece.getWhitePiece() == this.getWhitePiece())){
                  blocked = true;
                  break;
               }
            }
            if (!blocked){
               availableMoves.add(String.valueOf(Pieces.intToCharFile(Pieces.charFileToInt(this.getFile()) + 1)) + String.valueOf(this.getRank() + 1));
            }
         }
      }
      catch (UnavailableSquareException e){
      }
      blocked = false;
      try{
         if (Pieces.charFileToInt(this.getFile()) - 1 >= 1 && this.getRank() + 1 <= 8){
            for (Pieces piece : otherPieces){
               if ((piece.getFile() == Pieces.intToCharFile(Pieces.charFileToInt(this.getFile()) - 1)) && (piece.getRank() == (this.getRank() + 1)) && (piece.getWhitePiece() == this.getWhitePiece())){
                  blocked = true;
                  break;
               }
            }
            if (!blocked){
               availableMoves.add(String.valueOf(Pieces.intToCharFile(Pieces.charFileToInt(this.getFile()) - 1)) + String.valueOf(this.getRank() + 1));
            }
         }
      }
      catch (UnavailableSquareException e){
      }
      blocked = false;
      try{
         if (Pieces.charFileToInt(this.getFile()) - 1 >= 1 && this.getRank() - 1 >= 1){
            for (Pieces piece : otherPieces){
               if ((piece.getFile() == Pieces.intToCharFile(Pieces.charFileToInt(this.getFile()) - 1)) && (piece.getRank() == (this.getRank() - 1)) && (piece.getWhitePiece() == this.getWhitePiece())){
                  blocked = true;
                  break;
               }
            }
            if (!blocked){
               availableMoves.add(String.valueOf(Pieces.intToCharFile(Pieces.charFileToInt(this.getFile()) - 1)) + String.valueOf(this.getRank() - 1));
            }
         }
      }
      catch (UnavailableSquareException e){
      }
      blocked = false;
      try{
         if (Pieces.charFileToInt(this.getFile()) + 1 <= 8 && this.getRank() - 1 >= 1){
            for (Pieces piece : otherPieces){
               if ((piece.getFile() == Pieces.intToCharFile(Pieces.charFileToInt(this.getFile()) + 1)) && (piece.getRank() == (this.getRank() - 1)) && (piece.getWhitePiece() == this.getWhitePiece())){
                  blocked = true;
                  break;
               }
            }
            if (!blocked){
               availableMoves.add(String.valueOf(Pieces.intToCharFile(Pieces.charFileToInt(this.getFile()) + 1)) + String.valueOf(this.getRank() - 1));
            }
         }
      }
      catch (UnavailableSquareException e){
      }
      blocked = false;
      Rooks r1 = null;
      Rooks r2 = null;
      if (this.getNotMoved()){
         for (Pieces piece : otherPieces){
            if (piece instanceof Rooks && piece.getWhitePiece() == this.getWhitePiece() && piece.getNotMoved() && piece.getFile() == 'h'){
               r1 = (Rooks)piece;
               otherPieces.remove(piece);
               break;
            }
         }
         for (Pieces piece : otherPieces){
            if (piece instanceof Rooks && piece.getWhitePiece() == this.getWhitePiece() && piece.getNotMoved() && piece.getFile() == 'a'){
               r2 = (Rooks)piece;
               otherPieces.remove(piece);
               break;
            }
         }
         if (r1 != null && availableMoves.contains("f" + String.valueOf(this.getRank()))){
            for (Pieces piece : otherPieces){
               if (((piece.getFile() == 'f' || piece.getFile() == 'g') && (piece.getRank() == this.getRank())) || (piece.getWhitePiece() != this.getWhitePiece() && piece.getAvailableMoves(otherPieces).contains("g" + this.getRank()))){
                  blocked = true;
               }
            }
            if (!blocked){
               availableMoves.add(String.valueOf('g') + String.valueOf(this.getRank()));
            }
         }
         if (r2 != null && availableMoves.contains("d" + String.valueOf(this.getRank()))){
            for (Pieces piece : otherPieces){
               if (((piece.getFile() == 'b' || piece.getFile() == 'c' || piece.getFile() == 'd') && (piece.getRank() == this.getRank())) || (piece.getWhitePiece() != this.getWhitePiece() && piece.getAvailableMoves(otherPieces).contains("c" + this.getRank()))){
                  blocked = true;
               }
            }
            if (!blocked){
               availableMoves.add(String.valueOf('c') + String.valueOf(this.getRank()));
            }
         }
      }
      if (r1 != null){
         otherPieces.add(r1);
      }
      if (r2 != null){
         otherPieces.add(r2);
      }
      if (thisInList){
         otherPieces.add(this);
      }
      return availableMoves;
   }
}