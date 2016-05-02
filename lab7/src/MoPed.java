
public class MoPed  extends Bicycle{
  private int gear;
  private long speed;
  private float electricCharge;

  public MoPed(int gear, long speed, float electricCharge) {
    super(gear, speed);
    // TODO Auto-generated constructor stub
  }

  public void setElectricCharge(float electricCharge){
    this.electricCharge = electricCharge;
  }
}
