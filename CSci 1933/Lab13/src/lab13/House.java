package lab13;

import java.util.List;
import java.util.ArrayList;

public class House extends Node implements Comparable<House>{

	
	public House(int value)
	{
		this.value = value;	
	}
	
	@Override
	public void setLeft(Node n)
	{
		return;
	}

	@Override
	public void setRight(Node n)
	{
		return;
	}
	
	public void setParent(Node n){
		parent = n;
	}

	@Override
	public void setValue(int i)
	{
		return; //Value is set at instantiation.
	}

	@Override
	public int compareTo(House arg0)
	{
		return new Integer(this.value).compareTo(arg0.value);
	}
	
	//Step 5:
	@Override
	public String toString(int depth)
	{
		return "" + this.value;
	}

	@Override
	public int count()
	{
		return 1;
	}
	
	@Override
	public boolean verify()
	{
		return true;
	}
	
	//Step 3:
	@Override
	public List<String> findPath(int number)
	{
		List<String> list = new ArrayList<String>();
		if (number == this.value)
			list.add(0, "Done");
		return list;
	}
		
//		List<String> list = new ArrayList();
//		
//		Node current = this;
//		while (this.parent != null && this.parent.getValue() < number)
//			current = this.parent;
//		
//		while (current.getLeft() != null) {
//			if (number <= current.getValue())
//				current = this.left;
//			if (number > current.getValue())
//				current = this.right;
//		}
//		
//		if (current.getValue() == number)
//			list.add("Done");
//		return list;
	
}
