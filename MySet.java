public class MySet<X>{
	
	MyLinkedList<X> ll;

	public MySet(){
		ll = new MyLinkedList<X>();		//constructor
	}

	public Boolean IsEmpty()
	{
		return ll.isEmpty();		//checks linklist
	}

	public Boolean IsMember(X o)
	{
		
		Node<X> temp=ll.getHead();
		Boolean ans = false;

		for(;temp!=null;)
		{
			if(o.equals(temp.getElement()))			
				{
					ans=true;		//if match found
					break;
				}
				temp=temp.getNext();	//traversal

		}

		return ans;
	}

	public void addElement(X element){
		ll.addLast(element);			//addElements element at last
	}

	public void Delete(X o) throws ObjectException{
		if(this.IsMember(o)==false){throw new ObjectException("X not found"); }	//checks for the element

		else
			ll.removeElement(o);		//linkedlist method


	}

	public MySet<X> union(MySet<X> otherSet){
		//*	If element is present in this but not in a then adds it to a and then outputs a *//

		Node<X> temp = ll.getHead();
		while(temp!=null)
		{
			if(otherSet.IsMember(temp.getElement())==false)	//checks if presen in (a)
				otherSet.addElement(temp.getElement());
			temp=temp.getNext();
		}

		return otherSet;

	} // union

	public MySet<X> intersection(MySet<X> otherSet){
		/*Creates new set by adding elements in both a and this*/
		MySet<X> b = new MySet<X>();		
		Node<X> temp = ll.getHead();
		while(temp!=null)
		{
			if(otherSet.IsMember(temp.getElement())==true)
				b.addElement(temp.getElement());
			temp=temp.getNext();
		}

		return b;

	} //intersection
}  //MySet Complete

