
public class MountainBike extends Bicycle {
    private int minGear;
    private int maxGear;
	// public MountainBike(int min, int max, SuspensionType t)
	// How can you reuse the constructor of the superclass?
	// You'll need to define a private variable to keep track of SuspensionType
	// don't forget to add getters/setters
    private SuspensionType suspensionType;
    public MountainBike(int minGear, int maxGear, SuspensionType suspType){
      super.setMaxGear(maxGear);
      super.setMinGear(minGear);
      suspensionType = suspType;
      
    }
    
	public void printDescription() {
      System.out.println("\nBike is " + "in gear " + this.gear
          + " and travelling at a speed of " + this.speed + ". ");
	}
	
	public void setGear(int gear){
	  super.setGear(gear);
	}
	
    public void setSuspensionType(SuspensionType susType){
      this.suspensionType = susType;
    }
    
    public SuspensionType getSuspensionType(){
      return this.suspensionType;
    }

}
