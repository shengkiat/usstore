package sg.edu.nus.iss.uss.model;

public class ConsolePrinter implements IPrinter {

	@Override
	public void print(String line) {
		System.out.println(line);
	}

}
