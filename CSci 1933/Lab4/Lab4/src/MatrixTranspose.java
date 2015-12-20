import java.util.Arrays;


public class MatrixTranspose {

	public static void main(String[] args) {
		int m[][] = {{1,2,3},{4,5,6}};
		int k[][] = matrixTranspose(m);
		for (int i = 0; i < k.length; i ++)
			System.out.println(Arrays.toString(k[i]));
	}
	
	public static int[][] matrixTranspose(int[][]m) {
		int rows = m.length;
		int columns = m[0].length;
		int[][]newarray = new int[columns][rows];
		for (int i = 0; i < columns; i ++) {
			for (int j = 0; j < rows; j ++) {
				newarray[i][j] = m[j][i];
			}
		}
		return newarray;
		
	}
}
