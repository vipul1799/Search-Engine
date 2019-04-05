public class SearchResult implements Comparable<SearchResult>
{
	private PageEntry pag;
	private float rel;
	SearchResult(PageEntry p,float r)
	{
		pag=p;
		rel=r;
	}
	public PageEntry getPageEntry()
	{
		return pag;
	}

	public float getRelevance()
	{
		return rel;
	}

	public int compareTo(SearchResult otherObject)
	{
		if(this.rel==otherObject.getRelevance())
			return 0;
		else if(this.rel>otherObject.getRelevance())
			return 1;
		else
			return -1;
	}
}