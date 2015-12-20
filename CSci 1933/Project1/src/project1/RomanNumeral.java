package project1;

public class RomanNumeral {

	int numvalue;

	public RomanNumeral(int integerValue) {
		this.numvalue = integerValue;
	}
	
	public static void main(String[] args) {
	}
	
	public String toString(){
	//need UNDEFINED thing
		if ((numvalue > 2500) || (numvalue < 1)){                // Tests Input
			return "-100";
		}
		else {
			
			int thouminus = (numvalue / 1000);                  // If thousands digit is 1000-2000
				String numeralwiththou = "";
			switch(thouminus) {
			case 0: numeralwiththou = ""; break;
			case 1: numeralwiththou = "M"; break;
			case 2: numeralwiththou= "MM"; break;
			}

			int hundplace = numvalue;                          // Working on hundreds place
			if (hundplace > 1000) {
				hundplace = (numvalue - ((numvalue/1000)*1000));
			}
			int hundminus = (hundplace / 100);                 // If hundreds digit is 100-900
				String numeralwithhund = "";
			switch(hundminus) {
			case 0: numeralwithhund = ""; break;
			case 1: numeralwithhund = "C"; break;
			case 2: numeralwithhund = "CC"; break;
			case 3: numeralwithhund = "CCC"; break;
			case 4: numeralwithhund = "CD"; break;
			case 5: numeralwithhund = "D"; break;
			case 6: numeralwithhund = "DC"; break;
			case 7: numeralwithhund = "DCC"; break;
			case 8: numeralwithhund = "DCCC"; break;
			case 9: numeralwithhund = "CM"; break;
			}

			int tensplace = numvalue;                        // Working on the tens place
			if (tensplace > 100) {
				tensplace = (numvalue - ((numvalue/100)*100));
			}
			int tenminus = (tensplace / 10);                 // If tens digit is 10-90
				String numeralwithten = "";
			switch(tenminus) {
			case 0: numeralwithten = ""; break;
			case 1: numeralwithten = "X"; break;
			case 2: numeralwithten = "XX"; break;
			case 3: numeralwithten = "XXX"; break;
			case 4: numeralwithten = "XL"; break;
			case 5: numeralwithten = "L"; break;
			case 6: numeralwithten = "LX"; break;
			case 7: numeralwithten = "LXX"; break;
			case 8: numeralwithten = "LXXX"; break;
			case 9: numeralwithten = "XC"; break;
			}
			
			int fivemultiple = (numvalue % 10)/ 5;            // Final Digit Determination (0-10)
			int fiveminus = (numvalue % 5 + fivemultiple);             
				String numeralwithfive = "";
			if (fivemultiple == 0) {
				switch(fiveminus) {
				case 0: numeralwithfive = ""; break;
				case 4: numeralwithfive = "IV"; break;
				case 3: numeralwithfive = "III"; break;
				case 2: numeralwithfive = "II"; break;
				case 1: numeralwithfive = "I"; break; 
			}
			}
			else {
				switch(fiveminus) {
				case 4: numeralwithfive = "VIII"; break;
				case 5: numeralwithfive = "IX"; break;
				case 1: numeralwithfive = "V"; break;
				case 2: numeralwithfive = "VI"; break;
				case 3: numeralwithfive = "VII"; break;
			}
			}
			return numeralwiththou + numeralwithhund + numeralwithten + numeralwithfive;
	}
	}

	
	public int toInt() {
		return numvalue;
	}
	public int compareTo(RomanNumeral r) {
		if (this.numvalue < r.numvalue) {
			return -1;
		}
		if (this.numvalue > r.numvalue) {
			return 1;
		}
		else return 0;
		}
		
	}

