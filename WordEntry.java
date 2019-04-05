public class WordEntry{
	String str;
	Float freq;
	MyLinkedList<Position> poslist;
	AvlTree tree;

	WordEntry(String s)
	{
		str=s;
		poslist = new MyLinkedList<Position>();
		freq=(float)0.0;
		tree = new AvlTree();
	}

	public void addPosition(Position position)
	{
		poslist.addLast(position); //adds at back of ll
		 AvlNode temp = new AvlNode(position);
		 tree.insert(temp);
	}

	public void addPositions(MyLinkedList<Position> positions)
	{
		for(int i=0;i<positions.size;i++)
		{
			Position temp = positions.getX(i);
			poslist.addLast(temp);
			// AvlNode a = new AvlNode(temp);
			// tree.insert(a);
		}
	}

	public MyLinkedList<Position> getAllPositionsForThisWord()
	{
		return poslist;
	}

	public float getTermFrequency()
	{
		return freq;
	}
}