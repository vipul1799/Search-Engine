public class PageIndex{
	MyLinkedList<WordEntry> index;

	PageIndex()
	{
		index = new MyLinkedList<WordEntry>();
	}

	public Boolean hasString(String stri)						//checks if pageindex has that string
	{
		Boolean flag=false;
		Node<WordEntry> temp=index.getHead();
		while(temp!=null)
		{
			if(stri.equals(temp.getElement().str))
				{
					flag=true;	
					break;
				}
			temp=temp.getNext();
		}
		return flag;
	}

	public WordEntry findWEntry(String stri) //returns wordentry of string
	{
		WordEntry entry = null;
		Node<WordEntry> temp=index.getHead();
		while(temp!=null)
		{
			if(stri.equals(temp.getElement().str))
				{
					entry = temp.getElement();	
					break;
				}
			temp=temp.getNext();
		}
		return entry;
	}

	public void addPositionForWord(String stri, Position p)			//adds string to pageindex
	{
		Boolean flag=false;
		Node<WordEntry> temp=index.getHead();
		while(temp!=null)
		{
			if(stri.equals(temp.getElement().str))
				{
					flag=true;	
					break;
				}
			temp=temp.getNext();
		}
		if(flag)		//if word already present
		{
			temp.getElement().addPosition(p);
		}
		else    			//if word not already present
		{
			WordEntry entry = new WordEntry(stri);
			entry.addPosition(p);
			index.addLast(entry);
		}
	}

	public MyLinkedList<WordEntry> getWordEntries()		//returns the index
	{
		return index;
	}
}