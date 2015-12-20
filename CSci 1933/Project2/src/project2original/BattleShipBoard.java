package project2original;

import java.util.Scanner;

public class BattleShipBoard {

	public String[][] board = new String[5][5];
	public int m;
	public int n;
	public int movetotal = 0;
	
	
	
	public String[][] getBoard() {
		return board;
	}
	
	
	
	public void printBoard(int m, int n) {
		for(int i=0; i < m; i++){
			for(int j=0; j < n; j++){
				System.out.print(this.board[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	
	
	public void setBoard(int m, int n) {
		this.board = new String[m][n];
		if (m*n <= 16) {
			System.out.println("One ship is hidden on the " + m + " by " + n + " board.");
			setShips(1,m,n);
			setMines(1,m,n);
			setWater(m,n);
		}
		else if (m*n > 16 && m*n <= 36) {
			System.out.println("Two ships are hidden on the " + m + " by " + n + " board.");
			setShips(2,m,n);
			setMines(2,m,n);
			setWater(m,n);
		}
		else {
			System.out.println("Three ships are hidden on the " + m + " by " + n + " board.");
			setShips(3,m,n);
			setMines(3,m,n);
			setWater(m,n);
		}
	}
	
	
	
	public void setShips(int shipnum, int m, int n) {
		int iterator = 0;
		int iterator2 = 0;
		while (iterator < shipnum) {
			double orientation1 = Math.random();
			double r1 = Math.random();
			double r2 = Math.random();
			int x1 = (int) Math.floor(r1*n);
			int y1 = (int) Math.floor(r2*m);
			int shipparts = 0;
			if (this.board[y1][x1] != null) {
				//REDO******
			}
			else {
				if (orientation1 >= .5) {
					iterator2 ++;
					this.board[y1][x1] = "s" + iterator2;
					shipparts ++;
					if ((y1+1 < m) && (this.board[y1+1][x1] == null) && (shipparts <3)){
						this.board[y1+1][x1] = "s" + iterator2;
						shipparts ++;

						}
					if ((y1+2 < m) &&(this.board[y1+2][x1] == null) && (shipparts <3)){
						this.board[y1+2][x1] = "s" + iterator2;
						shipparts ++;

						}
					if ((y1-1 >= 0) && (this.board[y1-1][x1] == null) && (shipparts <3)) {
						this.board[y1-1][x1] = "s" + iterator2;
						shipparts ++;
						}
					if ((y1-2 >= 0) && (this.board[y1-2][x1] == null) && (shipparts <3)){
						this.board[y1-2][x1] = "s" + iterator2;
						shipparts ++;
						}
					else{
						//REDO******
						}
				}
				else {
					iterator2 ++;
					this.board[y1][x1] = "s" + iterator2;
					shipparts ++;

					if ((x1+1 < n)  && (this.board[y1][x1+1] == null) && (shipparts <3)){
						this.board[y1][x1+1] = "s" + iterator2;
						shipparts ++;
					}
					else if ((x1+2 < n) && (this.board[y1][x1+2] == null) && (shipparts <3)){
						this.board[y1][x1+2] = "s" + iterator2;
						shipparts ++;
						}
					else if ((x1-1 >= 0) && (this.board[y1][x1-1] == null) && (shipparts <3)) {
						this.board[y1][x1-1] = "s" + iterator2;
						shipparts ++;
						}
					else if ((x1-2 >= 0) && (this.board[y1][x1-2] == null) && (shipparts <3)){
						this.board[y1][x1-2] = "s" + iterator2;
						shipparts ++;
						}
					else{
						//REDO******
						}
				}	
			}
			if (shipparts == 3){
				for(int i=0; i < m; i++){
					for(int j=0; j < n; j++){
						if (this.board[i][j] != null && this.board[i][j].contains("s" + iterator2)) {
							this.board[i][j] = "s";
						}
					}
				}
			iterator ++;
			}
			else {
				for(int i=0; i < m; i++){
					for(int j=0; j < n; j++){
						if (this.board[i][j] == "s") {
							this.board[i][j] = "s";
						}
						else {
							this.board[i][j] = null;
						}
					}
				}
			}
		}
	}
	
	

	public void setMines(int minenum, int m, int n) {
		int iterator = 0;
		while (iterator < minenum) {
			double r1 = Math.random();
			double r2 = Math.random();
			int x1 = (int) Math.floor(r1*n);
			int y1 = (int) Math.floor(r2*m);
			if (this.board[y1][x1] == null) {
				this.board[y1][x1] = "m";
				iterator ++;
			}
		}
	}
	
	

	public void setWater(int m, int n){
		for(int i=0; i < m; i++){
			for(int j=0; j < n; j++){
				if (this.board[i][j] == null) {
					this.board[i][j] = "w";
				}
			}
		}
	}
	
	
	public static void main(String[] args){
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		System.out.print("Enter the first board dimension: ");
		int m = Integer.valueOf(s.next());
		System.out.print("Enter the second board dimension: ");
		int n = Integer.valueOf(s.next());
		BattleShipBoard b = new BattleShipBoard();
		if (m > 10 || n > 10 || m < 3 || n < 3) {
			System.out.print("Invalid Board Size, must be between 3x3 and 10x10.");
		}
		else {
			b.setBoard(m,n);
		}
		b.printBoard(m,n);
	}
}
