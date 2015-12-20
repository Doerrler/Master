package lab6;

import java.util.Arrays;

public class ArrayClone {

	public static int[] clone(int[] a) {
		int[] b = new int[a.length];
		for (int i=0; i<a.length; i++){
			b[i] = a[i];
		}
		return b;
	}
	
	public static double[][] make2DArray(int row, int column) {
		double[][] array = new double[row][column];
		for (int i=0; i < row; i++) {
			int k = i;
			for (int j=0; j < column; j++) {
				array[i][j] = k;
				k++;
			}
		}
		return array;
	}
	
	public static int sumDigits(int n) {
		int sum = 0;
		while (n != 0) {
			sum += n % 10;
			n = n/10;
		}
		return sum;
	}
	
//	public static void main(String[] args) {
//		int[] array1 = {1,2,3};
//		System.out.println(clone(array1)[0]);
//		System.out.println(clone(array1)[1]);
//		System.out.println(clone(array1)[2]);
//		System.out.println(Arrays.equals(clone(array1),array1));
//	}

//	public static void main(String[] args) {
//		double[][] test;
//		test = make2DArray(4,3);
//		for (int i=0; i < test.length; i++) {
//			System.out.print("row " + i + ":");
//			for (int j=0; j < test[i].length; j++) {
//				System.out.print(" " + (int)test[i][j]);
//			}
//			System.out.print("\n");
//		}
//	}
	
//	public static void main(String[] args) {
//		int testNum = 365;
//		System.out.println(sumDigits(testNum));
//	}
}