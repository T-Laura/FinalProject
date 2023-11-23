import java.util.LinkedList;

public class Knights extends Pieces
{
   public Knights(int pieceRank, char pieceFile, boolean white) throws UnavailableSquareException{
      super(pieceRank, pieceFile, white);
   }
   
   @Override
   public LinkedList<String> getAvailableMoves(LinkedList<Pieces> otherPieces){
      LinkedList<String> availableMoves = new LinkedList<>();
      boolean blocked;
      try{
         blocked = false;
         if (((this.getRank() + 2) <= 8) && ((Pieces.charFileToInt(this.getFile()) - 1) >= 1)){
            for (Pieces piece : otherPieces){
               if ((this.getRank() + 2) == piece.getRank() && (Pieces.charFileToInt(this.getFile()) - 1) == Pieces.charFileToInt(piece.getFile()) && this.getWhitePiece() == piece.getWhitePiece()){
                  blocked = true;
               }
            }
            if (!blocked){
               availableMoves.add(String.valueOf(Pieces.intToCharFile(Pieces.charFileToInt(this.getFile()) - 1)) + String.valueOf(this.getRank() + 2));
            }
         }
         blocked = false;
         if (((this.getRank() + 2) <= 8) && ((Pieces.charFileToInt(this.getFile()) + 1) <= 8)){
            for (Pieces piece : otherPieces){
               if ((this.getRank() + 2) == piece.getRank() && (Pieces.charFileToInt(this.getFile()) + 1) == Pieces.charFileToInt(piece.getFile()) && this.getWhitePiece() == piece.getWhitePiece()){
                  blocked = true;
               }
            }
            if (!blocked){
               availableMoves.add(String.valueOf(Pieces.intToCharFile(Pieces.charFileToInt(this.getFile()) + 1)) + String.valueOf(this.getRank() + 2));
            }
         }
         blocked = false;
         if (((this.getRank() + 1) <= 8) && ((Pieces.charFileToInt(this.getFile()) + 2) <= 8)){
            for (Pieces piece : otherPieces){
               if ((this.getRank() + 1) == piece.getRank() && (Pieces.charFileToInt(this.getFile()) + 2) == Pieces.charFileToInt(piece.getFile()) && this.getWhitePiece() == piece.getWhitePiece()){
                  blocked = true;
               }
            }
            if (!blocked){
               availableMoves.add(String.valueOf(Pieces.intToCharFile(Pieces.charFileToInt(this.getFile()) + 2)) + String.valueOf(this.getRank() + 1));
            }
         }
         blocked = false;
         if (((this.getRank() - 1) >= 1) && ((Pieces.charFileToInt(this.getFile()) + 2) <= 8)){
            for (Pieces piece : otherPieces){
               if ((this.getRank() - 1) == piece.getRank() && (Pieces.charFileToInt(this.getFile()) + 2) == Pieces.charFileToInt(piece.getFile()) && this.getWhitePiece() == piece.getWhitePiece()){
                  blocked = true;
               }
            }
            if (!blocked){
               availableMoves.add(String.valueOf(Pieces.intToCharFile(Pieces.charFileToInt(this.getFile()) + 2)) + String.valueOf(this.getRank() - 1));
            }
         }
         blocked = false;
         if (((this.getRank() - 2) >= 1) && ((Pieces.charFileToInt(this.getFile()) + 1) <= 8)){
            for (Pieces piece : otherPieces){
               if ((this.getRank() - 2) == piece.getRank() && (Pieces.charFileToInt(this.getFile()) + 1) == Pieces.charFileToInt(piece.getFile()) && this.getWhitePiece() == piece.getWhitePiece()){
                  blocked = true;
               }
            }
            if (!blocked){
               availableMoves.add(String.valueOf(Pieces.intToCharFile(Pieces.charFileToInt(this.getFile()) + 1)) + String.valueOf(this.getRank() - 2));
            }
         }
         blocked = false;
         if (((this.getRank() - 2) >= 1) && ((Pieces.charFileToInt(this.getFile()) - 1) >= 1)){
            for (Pieces piece : otherPieces){
               if ((this.getRank() - 2) == piece.getRank() && (Pieces.charFileToInt(this.getFile()) - 1) == Pieces.charFileToInt(piece.getFile()) && this.getWhitePiece() == piece.getWhitePiece()){
                  blocked = true;
               }
            }
            if (!blocked){
               availableMoves.add(String.valueOf(Pieces.intToCharFile(Pieces.charFileToInt(this.getFile()) - 1)) + String.valueOf(this.getRank() - 2));
            }
         }
         blocked = false;
         if (((this.getRank() - 1) >= 1) && ((Pieces.charFileToInt(this.getFile()) - 2) >= 1)){
            for (Pieces piece : otherPieces){
               if ((this.getRank() - 1) == piece.getRank() && (Pieces.charFileToInt(this.getFile()) - 2) == Pieces.charFileToInt(piece.getFile()) && this.getWhitePiece() == piece.getWhitePiece()){
                  blocked = true;
               }
            }
            if (!blocked){
               availableMoves.add(String.valueOf(Pieces.intToCharFile(Pieces.charFileToInt(this.getFile()) - 2)) + String.valueOf(this.getRank() - 1));
            }
         }
         blocked = false;
         if (((this.getRank() + 1) <= 8) && ((Pieces.charFileToInt(this.getFile()) - 2) >= 1)){
            for (Pieces piece : otherPieces){
               if ((this.getRank() + 1) == piece.getRank() && (Pieces.charFileToInt(this.getFile()) - 2) == Pieces.charFileToInt(piece.getFile()) && this.getWhitePiece() == piece.getWhitePiece()){
                  blocked = true;
               }
            }
            if (!blocked){
               availableMoves.add(String.valueOf(Pieces.intToCharFile(Pieces.charFileToInt(this.getFile()) - 2)) + String.valueOf(this.getRank() + 1));
            }
         }
      }
      catch (UnavailableSquareException e){
      }
      return availableMoves;
   }
}