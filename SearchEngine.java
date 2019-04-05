import java.util.ArrayList;
import java.util.Arrays;
public class SearchEngine{
	InvertedPageIndex invindex;
	SearchEngine()
	{
		invindex = new InvertedPageIndex();
	}

	public void performAction(String actionMessage)
	{
		
		String st[] = actionMessage.split(" ");

		if(st[0].equals("addPage"))
		{
			
			Boolean has = invindex.hasPage(st[1]);		//if page was already there
			if(has)
			{
				System.out.println(st[1] + " is already added");
				return;
			} 

			PageEntry temp = new PageEntry(st[1]);
			invindex.addPage(temp);
		}

		else if(st[0].equals("queryFindPagesWhichContainWord"))
		{
			String strcopy = "" + st[1];
			st[1]=st[1].toLowerCase();
			if(st[1].equals("stacks"))
				{
					st[1]="stack";
				}
				if(st[1].equals("structures"))
				{
					st[1]="structure";
				}
				if(st[1].equals("applications"))
				{
					st[1]="application";
				}

			String[] copyarr = Arrays.copyOfRange(st,1,st.length);

			String ans = "";
			MySet<SearchResult> setresult = new MySet<SearchResult>();
			MySet<PageEntry> set = invindex.getPagesWhichContainWord(st[1]);
			MyLinkedList<PageEntry> temp = set.ll;		
			if(temp.size==0)	//if 0 means no pageentry in set by fn
			{
				ans = "No webpage contains word " + strcopy;
			}
			else
			{
				for(int i=0;i<temp.size;i++)
					{
							PageEntry p = temp.getX(i);
							SearchResult s = new SearchResult(p,p.getRelevanceOfPage(copyarr,false,invindex));
							setresult.addElement(s);
					}
			
						MySort x = new MySort();
						ArrayList<SearchResult> array = x.sortThisList(setresult);
						for(int i=0;i<array.size();i++)
						{
							ans = ans + array.get(i).getPageEntry().name + ", ";
						}
						ans =  ans.substring(0,ans.length()-2);
						
			}

			System.out.println(ans);
			
		}

		else if(st[0].equals("queryFindPositionsOfWordInAPage"))
		{
			String ans = "";
			WordEntry temp=null;
			String strcopy = "" + st[1];
			st[1]=st[1].toLowerCase();
			if(st[1].equals("stacks"))
				{
					st[1]="stack";
				}
				if(st[1].equals("structures"))
				{
					st[1]="structure";
				}
				if(st[1].equals("applications"))
				{
					st[1]="application";
				}

			

			Boolean has = invindex.hasPage(st[2]); 

			if(!has)			//if webpage was not added
			{
				System.out.println("No webpage "+st[2]+" found");
				return;
			}

			try
			{
				temp = invindex.retWord(st[1]);		//if word was not added
			}
			 catch(ObjectException e)
			 {
			 	System.out.println("Webpage "+st[2]+" does not contain word " + strcopy);
			 	return;
			 }

			
			/*if word added*/	
			MyLinkedList<Position> plist =  temp.getAllPositionsForThisWord();
			
			for(int i=0;i<plist.size;i++)
			{
				if(st[2].equals(plist.getX(i).getPageEntry().name))
				{
					ans = ans + plist.getX(i).getWordIndex() + ", ";
				}
			}			/*if pageentry contained the word then its position is added*/

			
			if(ans.length()!=0)
				ans = ans.substring(0,ans.length()-2);
			 else
			 	ans = "Webpage "+st[2]+" does not contain word " + strcopy;		//if wordentry was not in that page

			System.out.println(ans);

		}

		else if(st[0].equals("queryFindPagesWhichContainAllWords"))
		{
			
			MySet<SearchResult> setresult = new MySet<SearchResult>();

			String ans = "";

			for(int i=1;i<st.length;i++)
			{
				st[i]=st[i].toLowerCase();
				if(st[i].equals("stacks"))
				{
					st[i]="stack";
				}
				if(st[i].equals("structures"))
				{
					st[i]="structure";
				}
				if(st[i].equals("applications"))
				{
					st[i]="application";
				}

			}

			String[] copyarr = Arrays.copyOfRange(st,1,st.length);

			MySet<PageEntry> set = invindex.getPagesWhichContainWord(st[1]);
			for(int i=2;i<st.length;i++)
			{
				set = set.intersection(invindex.getPagesWhichContainWord(st[i]));
			}

			MyLinkedList<PageEntry> temp = set.ll;		
			if(temp.size==0)	//if 0 means no pageentry in set by fn
			{
				ans = "No webpage contains these words";
			}
			else
			{
				for(int i=0;i<temp.size;i++)
					{
							PageEntry p = temp.getX(i);
							SearchResult s = new SearchResult(p,p.getRelevanceOfPage(copyarr,false,invindex));
							setresult.addElement(s);
					}
			
						MySort x = new MySort();
						ArrayList<SearchResult> array = x.sortThisList(setresult);
						for(int i=0;i<array.size();i++)
						{
							ans = ans + array.get(i).getPageEntry().name + ", ";
						}
						ans =  ans.substring(0,ans.length()-2);
						
			}

			System.out.println(ans);

		}

		else if(st[0].equals("queryFindPagesWhichContainAnyOfTheseWords"))
		{
			MySet<SearchResult> setresult = new MySet<SearchResult>();

			String ans = "";

			for(int i=1;i<st.length;i++)
			{
				st[i]=st[i].toLowerCase();
				if(st[i].equals("stacks"))
				{
					st[i]="stack";
				}
				if(st[i].equals("structures"))
				{
					st[i]="structure";
				}
				if(st[i].equals("applications"))
				{
					st[i]="application";
				}

			}

			String[] copyarr = Arrays.copyOfRange(st,1,st.length);

			MySet<PageEntry> set = invindex.getPagesWhichContainWord(st[1]);
			for(int i=2;i<st.length;i++)
			{
				set = set.union(invindex.getPagesWhichContainWord(st[i]));
			}

			MyLinkedList<PageEntry> temp = set.ll;		
			if(temp.size==0)	//if 0 means no pageentry in set by fn
			{
				ans = "No webpage contains these words";
			}
			else
			{
				for(int i=0;i<temp.size;i++)
						{
							PageEntry p = temp.getX(i);
							SearchResult s = new SearchResult(p,p.getRelevanceOfPage(copyarr,false,invindex));
							setresult.addElement(s);
						}
			
						MySort x = new MySort();
						ArrayList<SearchResult> array = x.sortThisList(setresult);
						int i;
						for(i=0;i<array.size();i++)
						{
							ans = ans + array.get(i).getPageEntry().name + ", ";
						}
						ans =  ans.substring(0,ans.length()-2);
						
			}

			System.out.println(ans);


		}

		else if(st[0].equals("queryFindPagesWhichContainPhrase"))
		{
			String ans = "";

			MySet<SearchResult> setresult = new MySet<SearchResult>();

			for(int i=1;i<st.length;i++)
			{
				st[i]=st[i].toLowerCase();
				if(st[i].equals("stacks"))
				{
					st[i]="stack";
				}
				if(st[i].equals("structures"))
				{
					st[i]="structure";
				}
				if(st[i].equals("applications"))
				{
					st[i]="application";
				}

			}

			String[] copyarr = Arrays.copyOfRange(st,1,st.length);

			MySet<PageEntry> set = invindex.getPagesWhichContainPhrase(copyarr);
			

			MyLinkedList<PageEntry> temp = set.ll;		
			if(temp.size==0)	//if 0 means no pageentry in set by fn
			{
				ans = "No webpage contains these words";
			}
			else
			{
				for(int i=0;i<temp.size;i++)
						{
							PageEntry p = temp.getX(i);
							SearchResult s = new SearchResult(p,p.getRelevanceOfPage(copyarr,true,invindex));
							setresult.addElement(s);
						}
			
						MySort x = new MySort();
						ArrayList<SearchResult> array = x.sortThisList(setresult);
						int i;
						for(i=0;i<array.size();i++)
						{
							ans = ans + array.get(i).getPageEntry().name + ", ";
						}
						ans =  ans.substring(0,ans.length()-2);
						
			}

			System.out.println(ans);


		}



	}
}