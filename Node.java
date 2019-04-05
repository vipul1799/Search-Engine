public class Node<X>
{
	private X element;
	private Node<X> next;

	public Node(){
		element=null;next=null;			//constructor
	}

	public Node(X s,Node<X> n)	//constructor
	{
		element = s;
		next = n; 
	}

	public X getElement(){return element;}	
	public Node<X> getNext(){return next;}

	public void setElement(X newElem){element = newElem;}
	public void setNext(Node<X> newNext){next = newNext;}

}