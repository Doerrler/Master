package Lab1.src.lab1.S3;

public class GPAcalc {

	public static void main(String[] args) {
		double weight = 0;
		int credit = 0;
		int totalCredit = 0;
		double weightedCredits = 0;
		
		int iterator = 0;
		
		if (args.length == 0)
			System.out.println("No arguments entered");
		else {
			while (iterator < args.length) {
				if (iterator % 2 == 0) {
					switch(args[iterator]) {
						case "a": weight = 4; break;
						case "a-": weight = 3.66; break;
						case "b+": weight = 3.33; break;
						case "b": weight = 3; break;
						case "b-": weight = 2.66; break;
						case "c+": weight = 2.33; break;
						case "c": weight = 2; break;
						case "c-": weight = 1.66; break;
						case "d+": weight = 1.33; break;
						case "d": weight = 1; break;
						case "f": weight = 0; break;
					}
				}
				else {
					credit = Integer.valueOf(args[iterator]);
					totalCredit += credit;
					weightedCredits += credit*weight;
					}
			iterator++;
			}
			System.out.println("GPA is " + weightedCredits/totalCredit);
		}
	}
}