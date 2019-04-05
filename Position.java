public class Position{
	private PageEntry page;
	private int pos;
	private int rpos;

	Position(PageEntry p, int wordIndex,int reqIndex){
		page=p;
		pos=wordIndex;
		rpos=reqIndex;
	}

	public PageEntry getPageEntry()
	{
		return page;
	}

	public int getWordIndex()
	{
		return pos;
	}

	public int getReqIndex()
	{
		return rpos;
	}
}