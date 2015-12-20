package lab0.P6;

public class Sum100 {
	 public static void main(String[] args){
		 
		 int sum = 0;
		 
		 int count = 1;
		 while (count <= 100) {
			 sum += count;
			 count++;
		 }
		 System.out.println("The sum is "+sum+".");
		 System.out.println("The average is "+(double)sum/100+".");
	 }
}