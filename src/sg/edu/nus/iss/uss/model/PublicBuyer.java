package sg.edu.nus.iss.uss.model;

public class PublicBuyer implements IBuyer {
	
	public final static String PUBLIC_NAME = "PUBLIC";

	@Override
	public String getName() {
		
		return PUBLIC_NAME;
	}

}
