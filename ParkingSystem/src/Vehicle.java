import java.util.Properties;

abstract class Vehicle {

	private String model;
	private String loc;
	private String type;  //1,2,3...
	
	public Vehicle(String model, String loc, String type) {
		super();
		this.model = model;
		this.loc = loc;
		this.type = type;
		System.out.println("vehicle created");
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public abstract void display(Properties p);
	
}
