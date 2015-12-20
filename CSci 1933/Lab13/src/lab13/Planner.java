package lab13;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("unused")
public class Planner {

	public static void main(String[] args) throws Exception
	{
		//Setup code: Read data from houses.txt and create list of houses.
		ArrayList<House> houses = new ArrayList<House>();
		Path p = Paths.get(System.getProperty("user.dir"), "houses.txt");
		
		Files.readAllLines(p, Charset.defaultCharset());
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(Files.readAllLines(Paths.get("houses.txt", ""), Charset.defaultCharset()).get(0));
		while(sc.hasNextInt())
		{
			houses.add(new House(sc.nextInt()));
		}
		
		Collections.sort(houses);
		System.out.println(houses.size());
		//Create the model of the streets in Triangletown.
		Juncture root = buildTree(houses);
		//For 6 12 25 38 43 57 62 75, this should print 15.
		//For 6 12 25 38 43 57, this should print 12.
		System.out.println("Debug: the number of Nodes in the tree is "+root.count()+".");
		
		root.number();
		System.out.println("Verification function says: "+root.verify());
		
		System.out.println();
		System.out.println(root.toString(1));
		System.out.println();
	}
		/*
		sc = new Scanner(System.in);
		String line = "";
		do
		{
			System.out.print("Route to house: ");
			line = sc.nextLine();
			
			int value = -1;
			try
			{
				value = Integer.parseInt(line);
			}
			catch(Exception e)
			{
			}
			if(value > 0)
			{
				List<String> path = root.findPath(value);
				System.out.println("\n"+path+"\n");
			}
		}
		while(!line.equals(""));
		}
		*/
		
	//STEP 1:
	public static Juncture buildTree(List<House> houses)
	{
		int n = houses.size();
		int levels = 0;
		while (n >= 2) {
			n = n/2;
			levels++;
		}
		
		ArrayList<Node> currentLevel = new ArrayList<Node>();
		for (int i = 0; i < houses.size(); i = i + 2) {
			Node firstHouse = null;
			Node secondHouse = null;
			
			firstHouse = new House(houses.get(i).getValue());
			if (houses.get(i + 1) != null)
				secondHouse = new House(houses.get(i + 1).getValue());
			
			Juncture newJunct;
			if (secondHouse != null) {
				newJunct = new Juncture(firstHouse, secondHouse);
				newJunct.number();
				firstHouse.setParent(newJunct);
				secondHouse.setParent(newJunct);
			}
			else {
				newJunct = new Juncture(firstHouse, null);
				newJunct.number();
				firstHouse.setParent(newJunct);
			}
			
			currentLevel.add(newJunct);
		}
		
		levels--;
		while (levels != 0) {
			ArrayList<Node> nextLevel = new ArrayList<Node>();
			
			for (int i = 0; i < currentLevel.size(); i = i + 2) {
				Node firstJunct = currentLevel.get(i);
				Node secondJunct = currentLevel.get(i + 1);

				Juncture newJunct = new Juncture(firstJunct, secondJunct);
				newJunct.number();
				firstJunct.setParent(newJunct);
				secondJunct.setParent(newJunct);
				
				nextLevel.add(newJunct);
			}
			currentLevel = nextLevel;
			levels--;
		}
		return (Juncture) currentLevel.get(0);
	}
}
