public class MyHashTable{
	MyLinkedList<WordEntry>[] table;

	MyHashTable(){
		table = new MyLinkedList[26];
		for(int i=0;i<26;i++)
		{
			table[i] = new MyLinkedList<WordEntry>();
		}
	}



	private int getHashIndex(String str)
	{
		int ans = (int)(str.charAt(0)-'a') % 26;			
		if(ans<0)
		{
			ans = -1 * ans;
		}

		return ans;			//calculates index
	}

	//adds words to ht
	public void addPositionsForWord(WordEntry w)
	{
		int code = getHashIndex(w.str);
		String wstr = w.str;
		WordEntry temp=null;
		Boolean flag=false;
		
		for(int i=0;i<table[code].size;i++)				
		{
			temp = table[code].getX(i);
			if(wstr.equals(temp.str))
			{
				temp.addPositions(w.getAllPositionsForThisWord());		//if word present then add positions to it
				flag=true;
				break;
			}
		}

		if(!flag)
		{
			table[code].addLast(w);		//if not then add new wordentry
		}
	}



	public WordEntry getWord(String s)	//returns wordentry of string
	{
		int code = this.getHashIndex(s);
		String st = "";
		Boolean flag = false;
		WordEntry wtemp=null;
		WordEntry ans=null;
		MyLinkedList<WordEntry> temp = table[code];
		
		for(int i=0;i<temp.size;i++)
		{
			
			wtemp = temp.getX(i);

			st = wtemp.str;
			if(st.equals(s))
			{
				flag=true;
				ans =  wtemp;
				break;
				
			}
		}
			
		
		
	 	return ans;
	}
}



