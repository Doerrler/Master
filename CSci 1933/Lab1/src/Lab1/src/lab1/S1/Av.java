package Lab1.src.lab1.S1;

public class Av {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		double sum = 0;
		int iterator = 0;
		
		if (args.length == 0)
			System.out.println("No arguments entered");
		else {
			while (iterator < args.length) {
				sum += Double.valueOf(args[iterator]);
				iterator++;
			}
			System.out.println("Average of " + args.length + " value(s) is: " + sum/args.length);
		}
	}
}
