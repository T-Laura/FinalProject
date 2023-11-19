public abstract class Pieces     //Parent class for King, Queen, Rook, Knight, Bishop, Pawn
{
   private int rank;
   private char file;
   private boolean whitePiece;
   private boolean alive;
   
   public Pieces(int pieceRank, char pieceFile, boolean white) throws UnavailableSquareException{
      if (pieceRank < 1 || pieceRank > 8){
         throw new UnavailableSquareException();
      }
      Pieces.charFileToInt(pieceFile);
      rank = pieceRank;
      file = pieceFile;
      whitePiece = white;
   }
   
   public abstract String[] getAvailableMoves(String[] friendlyPiecesOnBoard, String[] enemyPiecesOnBoard);
   
   public void setPosition(int pieceRank, char pieceFile) throws UnavailableSquareException{
      if (pieceRank < 1 || pieceRank > 8){
         throw new UnavailableSquareException();
      }
      Pieces.charFileToInt(pieceFile);
      rank = pieceRank;
      file = pieceFile;
   }
   
   public void setPieceAliveStatus(boolean living){
      alive = living;
   }
   
   public int getRank(){
      return rank;
   }
   
   public char getFile(){
      return file;
   }
   
   public boolean getPieceAliveStatus(){
      return alive;
   }
   
   public static char intToCharFile(int intFile) throws UnavailableSquareException{
      char charFile;
      switch (intFile){
         case 1:
            charFile = 'a';
            break;
         case 2:
            charFile = 'b';
            break;
         case 3:
            charFile = 'c';
            break;
         case 4:
            charFile = 'd';
            break;
         case 5:
            charFile = 'e';
            break;
         case 6:
            charFile = 'f';
            break;
         case 7:
            charFile = 'g';
            break;
         case 8:
            charFile = 'h';
            break;
         default:
            throw new UnavailableSquareException();
      }
      return charFile;
   }
   
   public static int charFileToInt(char charFile) throws UnavailableSquareException{
      int intFile;
      switch (charFile){
         case 'a':
            intFile = 1;
            break;
         case 'b':
            intFile = 2;
            break;
         case 'c':
            intFile = 3;
            break;
         case 'd':
            intFile = 4;
            break;
         case 'e':
            intFile = 5;
            break;
         case 'f':
            intFile = 6;
            break;
         case 'g':
            intFile = 7;
            break;
         case 'h':
            intFile = 8;
            break;
         default:
            throw new UnavailableSquareException();
      }
      return intFile;
   }
}