package windchill;

public class MyTempConverter {
  public static long windChill(int speed, int temperature){
    // Yes, because this method does not depends on the the instance variable 
    // that MyTempConverter creates
      if (speed < 5)
      throw new BadInputException("Windchill not valid if speed < 5");
      double newTemp =
      35.74 + 0.6215*temperature
      - 35.75 * Math.pow(speed, 0.16)
      + 0.4275 * temperature * Math.pow(speed,0.16);
      return Math.round(newTemp);
      }
  public static void main(String[] args) {
    System.out.println(MyTempConverter.windChill(10, 30));
    }
 
}
