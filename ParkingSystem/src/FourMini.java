import java.util.Properties;


public class FourMini extends Vehicle {

	private int wheels;

	public FourMini(String model, String pl, String type, int wheels) {
		super(model, pl, type);
		this.wheels = wheels;
		System.out.println("2 wheeler created");
	}

	public int getWheels() {
		return wheels;
	}

	public void setWheels(int wheels) {
		this.wheels = wheels;
	}

	public void display(Properties p){
		//Properties p = new Properties()
		System.out.println("\n----------------------Displaying vehicle information---------------------");
		System.out.println("Model : " + getModel());
		System.out.println("Type : " + getType());
		System.out.println("Wheeler : " + getWheels());
		System.out.println("Slot type required : " + p.getProperty(getType()));
	}
	


}
