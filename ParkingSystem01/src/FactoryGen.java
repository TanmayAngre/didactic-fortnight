
public class FactoryGen {

	public FactoryGen() {
		// TODO Auto-generated constructor stub
		System.out.println("FactoryGen object created");
	}
	
	public Vehicle getVehicle(String m, String t, String loc){
		if(t.equals("twowheeler")){
			int w = 2;
			return new TwoWheeler(m,loc,t,w);
		}
		if(t.equals("minifour")){
			int w = 4;
			return new TwoWheeler(m,loc,t,w);
		}
		if(t.equals("maxfour")){
			int w = 4;
			return new TwoWheeler(m,loc,t,w);
		}
		return null;
	}

}
