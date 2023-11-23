import java.util.LinkedList;

public class Pawns extends Pieces
{
   private boolean movedTwice = false;
   
   public Pawns(int pieceRank, char pieceFile, boolean white) throws UnavailableSquareException{
      super(pieceRank, pieceFile, white);
   }
   
   @Override
   public LinkedList<String> getAvailableMoves(LinkedList<Pieces> otherPieces){
      LinkedList<String> availableMoves = new LinkedList<>();
      int pawnDirection = -1;
      boolean forwardMovesStopped = false;
      if (this.getWhitePiece()){
         pawnDirection = 1;
      }
      //Check space ahead
      if ((this.getRank() == 8) || (this.getRank() == 1)){
         forwardMovesStopped = true;
      }
      for (Pieces piece : otherPieces){
         if ((piece.getFile() == this.getFile()) && (piece.getRank() == (this.getRank() + pawnDirection))){
            forwardMovesStopped = true;
            break;
         }
      }
      if (!forwardMovesStopped){
         availableMoves.add(String.valueOf(this.getFile()) + String.valueOf(this.getRank() + pawnDirection));
      }
      //Check two spaces ahead
      if (((this.getRank() == 2 && this.getWhitePiece()) || (this.getRank() == 7 && !this.getWhitePiece())) && !forwardMovesStopped){
         for (Pieces piece : otherPieces){
            if ((piece.getFile() == this.getFile()) && (piece.getRank() == (this.getRank() + (pawnDirection * 2)))){
               forwardMovesStopped = true;
               break;
            }
         }
         if (!forwardMovesStopped){
            availableMoves.add(String.valueOf(this.getFile()) + String.valueOf(this.getRank() + (pawnDirection * 2)));
         }
      }
      //Check for captures
      for (Pieces piece : otherPieces){
         if (piece instanceof Pawns){
            Pawns pawn = (Pawns)piece;
            try{
               if ((Pieces.charFileToInt(pawn.getFile()) == (Pieces.charFileToInt(this.getFile()) - 1)) && (pawn.getRank() == this.getRank()) && (pawn.getWhitePiece() != this.getWhitePiece()) && pawn.getMovedTwice()){
                  if (!(availableMoves.contains(String.valueOf(pawn.getFile()) + String.valueOf(this.getRank() + pawnDirection)))){
                     availableMoves.add(String.valueOf(pawn.getFile()) + String.valueOf(this.getRank() + pawnDirection));
                  }
               }
            }
            catch (UnavailableSquareException error){
            }
            try{
               if ((Pieces.charFileToInt(pawn.getFile()) == (Pieces.charFileToInt(this.getFile()) + 1)) && (pawn.getRank() == this.getRank()) && (pawn.getWhitePiece() != this.getWhitePiece()) && pawn.getMovedTwice()){
                  if (!(availableMoves.contains(String.valueOf(pawn.getFile()) + String.valueOf(this.getRank() + pawnDirection)))){
                     availableMoves.add(String.valueOf(pawn.getFile()) + String.valueOf(this.getRank() + pawnDirection));
                  }
               }
            }
            catch (UnavailableSquareException error){
            }
         }
         try{
            if (Pieces.charFileToInt(piece.getFile()) == (Pieces.charFileToInt(this.getFile()) - 1) && piece.getRank() == (this.getRank() + pawnDirection) && piece.getWhitePiece() != this.getWhitePiece()){
               if (!(availableMoves.contains(String.valueOf(piece.getFile()) + String.valueOf(piece.getRank())))){
                  availableMoves.add(String.valueOf(piece.getFile()) + String.valueOf(piece.getRank()));
               }
            }
         }
         catch (UnavailableSquareException error){
         }
         try{
            if (Pieces.charFileToInt(piece.getFile()) == (Pieces.charFileToInt(this.getFile()) + 1) && piece.getRank() == (this.getRank() + pawnDirection) && piece.getWhitePiece() != this.getWhitePiece()){
               if (!(availableMoves.contains(String.valueOf(piece.getFile()) + String.valueOf(piece.getRank())))){
                  availableMoves.add(String.valueOf(piece.getFile()) + String.valueOf(piece.getRank()));
               }
            }
         }
         catch (UnavailableSquareException error){
         }
      }
      return availableMoves;
   }
   
   public boolean checkLastRank(){
      if ((this.getRank() == 8) || (this.getRank() == 1)){
         return true;
      }
      else{
         return false;
      }
   }
   
   public void setMovedTwice(boolean bool){
      movedTwice = bool;
   }
   
   public boolean getMovedTwice(){
      return movedTwice;
   }
}