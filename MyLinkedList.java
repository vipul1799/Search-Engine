public class MyLinkedList<X>{

 protected Node<X> head;
 protected Node<X> tail;
 public int size ;

 public Node<X> getHead(){return head;}
 public Node<X> getTail(){return tail;}

 public MyLinkedList() {size=0;head=null;tail = null;} // constructs an initially empty list
 // access methods
 public int getSize( ) { return size; } 
 public Boolean isEmpty( ) { return size == 0; } 
 public X first( ) { // returns (but does not remove) the first element
 if (isEmpty( )) return null;
 return head.getElement( );
 }

 public void setTail(Node<X> temp){tail=temp;}
  public void setHead(Node<X> temp){head=temp;} 
 

  public X last() { // returns (but does not remove) the last element
if (isEmpty( )) return null;
 return tail.getElement( );
 } // update methods
 

 public void addFirst(X e) { // adds element e to the front of the list
 head = new Node<X>(e, this.head); // create and link a new node
 if (size == 0)
   tail = head; // special case: new node becomes tail also
 size++;
} 
 
 public void addLast(X e) { // adds element e to the end of the list
	 

	 
 Node<X> newest = new Node<X>(e, null); // node will eventually be the tail
 if (isEmpty( ))
   head = newest; // special case: previously empty list
 else
   tail.setNext(newest); // new node after existing tail
 tail = newest; // new node becomes the tail
 size++;
 }  

 public Node<X> retElement(int i) throws ObjectException{
 	Node<X> temp=null;



 	if(i<size && i>=0){
 		temp = head;
 		for(int j=0;j<i;j++)
 		{
 			
 			temp=temp.getNext();		//traversal LinkedList
 		}
 		
	}
	else
		throw new ObjectException("Index of array out of bounds");		//Error

	return temp; 
 }

 public X getX(int i){
 	Node<X> temp=null;

 	if(size==0)
 		return null;

 	if(i<size && i>=0){
 		temp = head;
 		for(int j=0;j<i;j++)
 		{
 			
 			temp=temp.getNext();		//traversal LinkedList
 		}
 		
	}
	return temp.getElement(); 
 }

 public X removeFirst( ) { // removes and returns the first element
 if (isEmpty( )) return null; // nothing to remove
 X answer = head.getElement( );
 head = head.getNext( ); // will become null if list had only one node
 size--;
 if (size == 0)
 tail = null; // special case as list is now empty
 return answer;
 } 

 public void removeElement(X o){
 if (isEmpty( )) return; // nothing to remove

 if(o.equals(head.getElement())){removeFirst();return;}	//as removefirst is present so sepearte case

 Node<X> curr = head;
 Node<X> prev = head;		//

while(curr!=null)
{
	curr=curr.getNext();		//traversal
	if(o.equals(curr.getElement()))		//if o is found
	{
		prev.setNext(curr.getNext());	//set pointer of prev to next of curr
		size--;
		if(prev.getNext()==null)		//if curr is at tail
			tail=prev;
		break;
	}

	prev=curr;

}
return;

}//remEle complete

  }  //LinkedList complete
