package lab0.P6;

public class FivesFrom500 {
	 public static void main(String[] args){
		 int count = 500;
		 while (count > 0) {
			 if (count % 5 == 0) {
				 System.out.println(count);
			 }
			 count--;
		 }
	 }
}