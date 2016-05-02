public class Bicycle {

	private int gear;
	private long speed;

	// In TestBikes.main() we're calling the constructor Bicycle()
	// Where is this constructor method ?
	// Create another constructor method that initializes a bicycle with a
	// minimum and maximum gear. Basically, initialize these two variables:

	 private int minimumGear;
	 private int maximumGear;
	 
	public Bicycle(int gear, long speed){
	  this.gear = gear;
	  this.speed = speed;
	}
    public static Bicycle BicyclewithminimumGear(int minimumGear, long speed){
      return new Bicycle(minimumGear, speed);
    }
    public static Bicycle BicyclewithmaximumGear(int maximumGear, long speed){
      return new Bicycle(maximumGear, speed);
    }
	// Create getter and setter methods for all variables
	// See if you can get your IDE to do it automatically for you 
					// (the cool IDEs do it)
	// Make sure to add the right checks into the setter!

	public void printDescription() {

		System.out.println("\nBike is " + "in gear " + this.gear
				+ " and travelling at a speed of " + this.speed + ". ");
		// What happens when gear and speed are not set?
		// What kind of checks can you put in place to take care of non-?

	}

	final public void hitTheBreaks() {
		System.out.println("Break!");
	}
	
	public int getGear(){
	  return this.gear;
	}
	public void setGear(int gear){
	   this.gear = gear; 
	
	   if(this.gear > maximumGear){
	     System.out.println("Bike execeds gear limit");
	   }
	   if(this.gear < minimumGear){
	     System.out.println("Bike does not reach gear standard");
	   }
	}
	public int getMinGear(){
	      return this.minimumGear;
	}
	public int getMaxGear(){
         return this.maximumGear;
    }
	public void setMaxGear(int maximumgear){
	       this.maximumGear = maximumgear; 
	}
	public void setMinGear(int minimumgear){
         this.minimumGear = minimumgear; 
    }
  
	

}
