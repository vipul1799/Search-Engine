import java.util.ArrayList;

public class MySort{
	ArrayList<SearchResult> sortThisList(MySet<SearchResult> listOfSortableEntries)
	{
		ArrayList<SearchResult> ans = new ArrayList<SearchResult>();
		MyLinkedList<SearchResult> templist = listOfSortableEntries.ll;
		for(int i=0;i<templist.size;i++)
		{
			ans.add(templist.getX(i));
		}

		int n=templist.size;
		for(int i=0;i<n-1;i++)
		{
			for(int j=0;j<n-i-1;j++)
			{
				if(ans.get(j).compareTo(ans.get(j+1))<0)
				{
					SearchResult temp = ans.get(j+1);
					ans.set(j+1,ans.get(j));
					ans.set(j,temp);
				}
			}
		}

		return ans;

	}

	
}