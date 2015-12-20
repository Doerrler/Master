package lab13;

import java.util.List;

public abstract class Node {
	protected Node left;
	protected Node right;
	protected Node parent;
	protected int value;
	
	abstract public void setLeft(Node n);
	
	abstract public void setRight(Node n);
	
	abstract public void setParent(Node n);
	
	abstract public void setValue(int i);
	
	abstract public int count();
	
	abstract public boolean verify();
	
	abstract public List<String> findPath(int number);
	
	public Node getLeft()
	{
		return left;
	}
	
	public Node getRight()
	{
		return right;
		
	}
	
	public int getValue()
	{
		return value;
	}
	
	public int getRightMostValue() {
		if (this.right == null)
			return this.value;
		else return right.getRightMostValue();
	}
	
	public int getLeftMostValue() {
		if (this.left == null)
			return this.value;
		else return left.getLeftMostValue();
	}

	abstract public String toString(int priorDepth);
}
