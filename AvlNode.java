public class AvlNode{
private Position element;
private AvlNode left;
private AvlNode right;
private AvlNode parent;

AvlNode()
{
	element = null;
	left = null;
	right = null;
	parent = null;
}

AvlNode(Position a)
{
	element = a;
	left = null;
	right = null;
	parent = null;
}

public Position getpos()
{
	return element;
}


public AvlNode getL()
{
	return left;
}

public AvlNode getR()
{
	return right;
}

public AvlNode getP()
{
	return parent;
}

public void setL(AvlNode a)
{
	left=a;
}

public void setR(AvlNode a)
{
	right=a;
}
public void setP(AvlNode a)
{
	parent=a;
}


}