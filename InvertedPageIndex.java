public class InvertedPageIndex{
	
MySet<PageEntry> pageset;
MyHashTable hasht;

InvertedPageIndex()
{
	pageset = new MySet<PageEntry>();
	hasht = new MyHashTable();
}

public void addPage(PageEntry p)
{
	

	pageset.addElement(p);						//adds page to set
	PageIndex temp = p.getPageIndex();
	WordEntry tempw = null;
	MyLinkedList<WordEntry> wordlist = temp.getWordEntries();		//gets its linked list of wordentries
	for(int i=0;i<wordlist.size;i++)
	{
		tempw = new WordEntry(wordlist.getX(i).str);		//creates new wordentry for ht
		tempw.addPositions(wordlist.getX(i).getAllPositionsForThisWord());
		hasht.addPositionsForWord(tempw);		//adds created wentry to hashtable- duplication of added words checked in addposforword
	}
}

public MySet<PageEntry> getPagesWhichContainWord(String str)
{
	MySet<PageEntry> ans = new MySet<PageEntry>();
	WordEntry temp = hasht.getWord(str);			//gets wentry from hashtable
	if(temp==null)
		return ans;	//word not present
	MyLinkedList<Position> plist =  temp.getAllPositionsForThisWord();
	for(int i=0;i<plist.size;i++)
	{
		if(!(ans.IsMember(plist.getX(i).getPageEntry())))
			ans.addElement(plist.getX(i).getPageEntry()); //creates set
	}

	return ans;

}

public Boolean hasPage(String s)		//checks if page is added
{
	Boolean flag = false;
	MyLinkedList<PageEntry> temp = pageset.ll;
	for(int i=0;i<temp.size;i++)
	{
		if(s.equals(temp.getX(i).name))
		{
			flag = true;
			break;
		}
	}

	return flag;
}

public WordEntry retWord(String s) throws ObjectException		//gives wordentry of string
{
	 WordEntry temp=null;

	 temp=hasht.getWord(s);
	 if(temp==null)
	 	throw new ObjectException("");

	 return temp;
}

public float idw(String s)
{
	MySet<PageEntry> temp = getPagesWhichContainWord(s);
	float f;
	double g;
	g=(double)(0.0001+pageset.ll.size)/temp.ll.size;
	f=(float)Math.log(g);

	return f;
}

public float idwphrase(String str[])
{
	MySet<PageEntry> temp = getPagesWhichContainPhrase(str);
	float f;
	double g;
	g=(double)(0.0001+pageset.ll.size)/temp.ll.size;
	f=(float)Math.log(g);

	return f;
}

public int countPhrase(PageEntry pag,String str[])
{
		int ans=0;
		PageEntry p = pag;
		WordEntry w = p.getPageIndex().findWEntry(str[0]);
			
		
		MyLinkedList<Position> plist = w.getAllPositionsForThisWord();
		for(int j=0;j<plist.size;j++)
		{
			Boolean flag=true;
			int temppos = plist.getX(j).getReqIndex();
			int k=0;
			for(k=1;k<str.length;k++)
			{
				WordEntry w2= p.getPageIndex().findWEntry(str[k]);
				if(w2==null)
				{
					flag=false;
					break;
				}

				if(!w2.tree.search(temppos+k))
				{
					flag=false;
					break;
				}

			}// k ends
			if(flag)
			{
				ans++;
			}
		}//j ends
		return ans;
}

public MySet<PageEntry> getPagesWhichContainPhrase(String str[])
{
	MySet<PageEntry> ans = new MySet<PageEntry>();
	MySet<PageEntry> temp = getPagesWhichContainWord(str[0]);
	MyLinkedList<PageEntry> linklist = temp.ll;
	for(int i=0;i<linklist.size;i++)
	{
		Boolean flag=false;
		PageEntry p = linklist.getX(i);
		WordEntry w = p.getPageIndex().findWEntry(str[0]);
			
		
		MyLinkedList<Position> plist = w.getAllPositionsForThisWord();
		for(int j=0;j<plist.size;j++)
		{
			int temppos = plist.getX(j).getReqIndex();
			int k=0;
			for(k=1;k<str.length;k++)
			{
				WordEntry w2= p.getPageIndex().findWEntry(str[k]);
				if(w2==null)
				{
					flag=false;
					break;
				}

				if(!w2.tree.search(temppos+k))
				{
					flag=false;
					break;
				}

			}// k ends
			if(k==str.length)
			{
				flag=true;
				break;
			}
		}//j ends
	
		if(flag)
		{
			ans.addElement(p);
		}
	}//i ends

	return ans;
}


}