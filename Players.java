public class Players
{
   private String name;
   private boolean whitePieces;
   
   public Players(boolean playsWhite){       //Overloaded Constructor
      if (playsWhite){
         name = "White";
         whitePieces = true;
      }
      else{
         name = "Black";
         whitePieces = false;
      }
   }
   
   public Players(boolean playsWhite, String playerName){      //Overloaded Constructor
      if (playsWhite){
         whitePieces = true;
      }
      else{
         whitePieces = false;
      }
      name = playerName;
   }
   
   public void setName(String playerName){
      name = playerName;
   }
   
   public void setPlaysWhite(boolean playsWhite){
      whitePieces = playsWhite;
      if (name.equals("White") || name.equals("Black")){
         if (playsWhite){
         name = "White";
      }
      else{
         name = "Black";
      }
      }
   }
   
   public String getName(){
      return name;
   }
   
   public boolean getPlaysWhite(){
      return whitePieces;
   }
}