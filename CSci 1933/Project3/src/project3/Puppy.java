package project3; 

public class Puppy {
	private int cuteness;
	private String name;
	
	public int getCuteness(){
		return cuteness;
	}
	
	public String getName(){
		return name;
	}
	
	public Puppy(Puppy p){
		if(p!=null){
			name = p.getName();
			cuteness = p.getCuteness();
		}
	}
	public Puppy(String n, int c){
		name = n;
		cuteness = c;
	}
	
	public String toString(){
		return name;
	}
	
	public boolean equals(Object p){
		return (p instanceof Puppy && this.equals((Puppy)p));
	}
	
	public boolean equals(Puppy p){
		return (p.getName().equals(name)&&p.getCuteness()==cuteness);
	}

}
