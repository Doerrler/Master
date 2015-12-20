package lab13;

import java.util.List;

public class Juncture extends Node {
	
	public Juncture()
	{
		value = -1;
	}
	
	public Juncture(Node l, Node r) {
		left = l;
		right = r;
	}
	
	public void setLeft(Node n)
	{
		left = n;
	}
	
	public void setRight(Node n)
	{
		right = n;
	}
	
	public void setParent(Node n){
		parent = n;
	}
	
	public void setValue(int i)
	{
		value = i;
	}

	@Override
	public int count()
	{
		int leftCount = 0;
		int rightCount = 0;
		if(left != null)
		{
			leftCount = left.count();
		}
		if(right != null)
		{
			rightCount = right.count();
		}
		return leftCount + rightCount + 1;
	}
	
	@Override
	public boolean verify()
	{
		if(left != null && (!left.verify() || left.getValue() > this.getValue()))
		{
			return false;
		}
		if(right != null && (!right.verify() || right.getValue() <= this.getValue()))
		{
			return false;			
		}
		
		return true;
			
	}
	//STEP 2:
	public void number()
	{
		this.value = (int) (left.getRightMostValue() + right.getLeftMostValue()) / 2;
	}

	//Step 4:
	@Override
	public List<String> findPath(int number)
	{
		Node current;
		List<String> list;
		if (number <= value) {
			current = left;
			list = current.findPath(number);
			list.add(0, "Left");
		}
		else {
			current = right;
			list = current.findPath(number);
			list.add(0, "Right");
		}
		return list;
	}
	
	//Step 5:
	public String toString(int calledJunctureDepth)
	{
		int depth = calledJunctureDepth;
		String spacing = "";
		for (int i = 1; i <= depth; i++)
			spacing = spacing + "   ";
		
		String toReturn = this.value + "";
		toReturn = toReturn + "\n" + spacing + "Left: " + this.getLeft().toString(depth + 1);
		toReturn = toReturn + "\n" + spacing + "Right: " + this.getRight().toString(depth + 1);
		return toReturn;
	}
}
