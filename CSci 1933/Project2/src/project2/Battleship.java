package project2;

import java.util.Scanner;

public class Battleship {
	
	public boolean gameNotOver = true;
	public static String[][] board;
	
	
	public static void main(String[] args){
		int attempts = 0;
		boolean AreThereShips = false;
		
		System.out.println("Welcome to Battleship. Coordinates are from (0,0) to (n-1,m-1).");
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
			board = b.getBoard();
			AreThereShips = true;
			System.out.println("Would you like to use the Test Mode? (yes/no):"); 
			String answer = s.next();
			if (answer.contains("y")) {
				b.printBoard(m,n);
				System.out.println();
			}
		}
		
		while(AreThereShips == true){  
			System.out.println("Enter coordinates of missle target (with a space) 'x y': ");
			int My = s.nextInt();
			int Mx = s.nextInt();
			if (Mx >= n || My >= m){
				System.out.println("Coordinates not in gameboard. Try again.");
			}
			else {
				if (board[Mx][My].equals("s")){
					System.out.println("Hit!");
					board[Mx][My] = "h";
					attempts++;
					System.out.println("Total score: " + attempts);
					System.out.println();
				}
				else if (board[Mx][My].equals("w")){
					boolean shipvery = false;
					if (Mx + 1 < n && board[Mx+1][My].equals("s") && shipvery == false) {
						System.out.println("A miss, but very close.");
						shipvery = true;
					}
					if (Mx - 1 >= 0 && board[Mx-1][My].equals("s") && shipvery == false) {
						System.out.println("A miss, but very close.");
						shipvery = true;
					}
					if (My + 1 > m && board[Mx][My+1].equals("s") && shipvery == false) {
						System.out.println("A miss, but very close.");
						shipvery = true;
					}
					if (My - 1 >= 0 && board[Mx][My-1].equals("s") && shipvery == false) {
						System.out.println("A miss, but very close.");
						shipvery = true;
					}
					if(Mx + 2 < n && board[Mx+2][My].equals("s") && shipvery == false) { 
						System.out.println("A miss, but close.");
						shipvery = true;
					}
					if(Mx - 2 >= 0 && board[Mx-2][My].equals("s") && shipvery == false) { 
						System.out.println("A miss, but close.");
						shipvery = true;
					}
					if(My + 2 < m && board[Mx][My+2].equals("s") && shipvery == false) { 
						System.out.println("A miss, but close.");
						shipvery = true;
					}
					if(My - 2 >= 0 && board[Mx][My-2].equals("s")) { 
						System.out.println("A miss, but close.");
						shipvery = true;
					}
					board[Mx][My] = "m";
					attempts++;
					System.out.println("Total score: "+attempts);
					System.out.println();
				}

			else if (board[Mx][My].equals("h")||board[Mx][My].equals("m")){
				System.out.println("Redundant strike and/or you hit a mine.");
				attempts+=2;
				System.out.println("Total score: "+attempts);
				System.out.println();
			}
			AreThereShips = false;
			for(int i = 0; i < board.length; i++){
				for(int j = 0; j < board[i].length; j++){
					if(board[i][j].equals("s")){
						AreThereShips = true;
						}
				}
			}
			if (AreThereShips == false) {
				System.out.println("YOU WIN! Final score:" + attempts);
				System.out.println("Play again? (y/n):");
				String answer2 = s.next();
				if (answer2.contains("y")) {
					Battleship.main(args);
				}
			}
				
			}
		}
	}

}