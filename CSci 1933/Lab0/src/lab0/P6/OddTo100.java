package lab0.P6;

public class OddTo100 {
	 public static void main(String[] args){
		 int count = 1;
		 while (count <= 100) {
			 if (count % 2 == 1) {
				 System.out.println(count);
			 }
			 count++;
		 }
	 }
}