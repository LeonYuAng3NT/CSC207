
public class TestBikes {
    
    public static void main(String args[]){
      Bicycle a1, a2, a3; a1
      = new Bicycle(0,2);;
      a2 = new MountainBike(0,5, SuspensionType.Cheap);;
      a3 = new MoPed(0,3,100);;
      a1.printDescription();;
      a2.printDescription();;
      a3.printDescription();;
    	//Try out your bikes here!
    }
    
}
