import java.io.*;
import java.util.*;
public class PageEntry{
	String name;
	PageIndex page;
	int notstop;
	PageEntry(String pageName){
		page = new PageIndex();
		name = pageName;
		notstop=0;
		try
		{
			FileInputStream fstream = new FileInputStream(pageName);
			Scanner s = new Scanner(fstream);
			
			String scanned="";
			String totString="";
			WordEntry tempword = null;
			Position temppost = new Position(this,1,1);			//create position to be stored

			while(s.hasNextLine())
			{
				scanned=s.nextLine();
				totString=totString+scanned+" ";
			}
			
				totString=totString.toLowerCase();
				
				String[] arrOfStr = totString.split("[\"\'\\{\\}<>\\[\\]=().,;?#\\-:! ]+");

/*above is the reading,replacing of string and then splitting*/
			
			for(int i=0;i<arrOfStr.length;i++)
			{
					

				if(!(arrOfStr[i].equals("a") || arrOfStr[i].equals("an") || arrOfStr[i].equals("the") || arrOfStr[i].equals("they") || arrOfStr[i].equals("these") || arrOfStr[i].equals("this") || arrOfStr[i].equals("for") || arrOfStr[i].equals("is") || arrOfStr[i].equals("are") || arrOfStr[i].equals("was") || arrOfStr[i].equals("of") || arrOfStr[i].equals("or") || arrOfStr[i].equals("and") || arrOfStr[i].equals("does") || arrOfStr[i].equals("will") || arrOfStr[i].equals("whose")))
					notstop++;
	
			} //if ends

				
			for(int i=0,reqpos=1;i<arrOfStr.length;i++)
			{
				
				

				if(!(arrOfStr[i].equals("a") || arrOfStr[i].equals("an") || arrOfStr[i].equals("the") || arrOfStr[i].equals("they") || arrOfStr[i].equals("these") || arrOfStr[i].equals("this") || arrOfStr[i].equals("for") || arrOfStr[i].equals("is") || arrOfStr[i].equals("are") || arrOfStr[i].equals("was") || arrOfStr[i].equals("of") || arrOfStr[i].equals("or") || arrOfStr[i].equals("and") || arrOfStr[i].equals("does") || arrOfStr[i].equals("will") || arrOfStr[i].equals("whose")))
				//a, an, the, they, these, this, for, is, are, was, of,or, and, does, will, whose
				{
					/*adds word in pindex then finds it and change freq*/
					temppost = new Position(this,i+1,reqpos);
					if(arrOfStr[i].equals("stacks"))
					{
						page.addPositionForWord("stack",temppost);
						tempword = page.findWEntry("stack");
						tempword.freq = (float)tempword.poslist.size/notstop;

					}
					else if (arrOfStr[i].equals("structures"))
					{
						page.addPositionForWord("structure",temppost);
						tempword = page.findWEntry("structure");
						tempword.freq = (float)tempword.poslist.size/notstop;
					}
					else if(arrOfStr[i].equals("applications"))
					{
						page.addPositionForWord("application",temppost);
						tempword = page.findWEntry("application");
						tempword.freq = (float)tempword.poslist.size/notstop;
					}
					else
					{
						page.addPositionForWord(arrOfStr[i],temppost);
						tempword = page.findWEntry(arrOfStr[i]);
						tempword.freq = (float)tempword.poslist.size/notstop;
					}

					reqpos++;
					
				} //if ends



			} //for ends

			
		}	//try ends
			catch(Exception e){e.printStackTrace();}
		}
	

	public PageIndex getPageIndex()
	{
		return page;
	}

	public float getRelevanceOfPage(String str[ ], boolean doTheseWordsRepresentAPhrase,InvertedPageIndex ipi)
	{
		float f=(float)0.0;
		float temp;


		if(doTheseWordsRepresentAPhrase)
		{
			int m=ipi.countPhrase(this,str);
			int j=notstop - m*(str.length);
			f=(float)m/j;
			f=f*ipi.idwphrase(str);

		}
		else
		{
			WordEntry a = null;
			for(int i=0;i<str.length;i++)
			{
				a=page.findWEntry(str[i]);
				if(a==null)
				{}
				else
					{
						temp = a.getTermFrequency()*ipi.idw(str[i]);
						f=f+temp;
					}
			}
		}
		return f;
	}



}