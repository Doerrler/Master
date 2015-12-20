package lab0.P1;

/*
 * Lab 0 Part 1
 */
public class Puppy {
	
	public String name;
	public int weight;
	boolean hasSword;
	boolean isKnight;
	
	public Puppy(String name, int weight) {
		this.name = name;
		this.weight = weight;
		this.hasSword = false;
		this.isKnight = false;
	}
	
	public void makeKnight() {
		this.hasSword = true;
		this.isKnight = false;
	}
 }
