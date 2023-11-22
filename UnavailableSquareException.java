public class UnavailableSquareException extends Exception
{
   @Override
   public String getMessage(){
      return "Stated space isn't available on board";
   }
}