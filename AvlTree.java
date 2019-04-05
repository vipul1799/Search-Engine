public class AvlTree{
	AvlNode root;

	AvlTree()
	{
		root = null;
	}

	AvlTree(AvlNode a)
	{
		root=a;
	}

	public AvlNode getRoot()
	{
		return root;
	}

	public Boolean isEmpty()
	{
		return root==null;
	}

	public int height(AvlNode a)
	{
		if(a==null)
			return -1;
		else
			return 1+Math.max(height(a.getL()),height(a.getR()));
	}

	public int balance(AvlNode a)
	{
		

		return height(a.getL())-height(a.getR());
	}

	public AvlNode rotateL(AvlNode a)
	{
		AvlNode b = a.getR();
		b.setP(a.getP());
		a.setR(b.getL());
		b.setL(a);
		a.setP(b);
		if(a.getR()!=null)
		{
			a.getR().setP(a);
		}
		return b;
	}

	public AvlNode rotateR(AvlNode a)
	{
		AvlNode b = a.getL();
		b.setP(a.getP());
		a.setL(b.getR());
		b.setR(a);
		a.setP(b);
		if(a.getL()!=null)
		{
			a.getL().setP(a);
		}
		return b;
	}

	public Boolean search(int x)
	{
		Boolean ans = false;
		AvlNode tempnode = root;
		
		while(tempnode!=null)
		{
			
			if(x==tempnode.getpos().getReqIndex())
				{
					ans = true;
					break;
				}
			
			if(x<=tempnode.getpos().getReqIndex())
			{
				tempnode = tempnode.getL();
			}
			else
				{
					tempnode = tempnode.getR();
				}
		}
		return ans;
	}

	public void insert(AvlNode a)
	{
		if(root==null)
			{
				root=a;
				return;
			}

		int temp = a.getpos().getWordIndex();

		AvlNode tempnode = root;
		AvlNode tempins = null;
 
		while(tempnode!=null)
		{
			if(temp<=tempnode.getpos().getWordIndex())
			{
				tempins = tempnode;
				tempnode = tempnode.getL();
			}
			else
				{
					tempins = tempnode;
					tempnode = tempnode.getR();
				}
		}

		if(temp<=tempins.getpos().getWordIndex())
		{
			tempins.setL(a);
			a.setP(tempins);
		}
		else
			{
				tempins.setR(a);
				a.setP(tempins);
			}

		tempnode=tempins.getP();

		while(tempnode!=null)
		{
			
			if(tempnode.getL()!=null)
			{
				if(balance(tempnode)>1 && temp<=tempnode.getL().getpos().getWordIndex())
						{
							AvlNode temppar = tempnode.getP();
							if(temppar==null)
								{
									root=rotateR(tempnode);
									break;

								}

								AvlNode z =rotateR(tempnode);

							if(temp<=temppar.getL().getpos().getWordIndex())
								temppar.setL(z);
							else 
								temppar.setR(z);

							tempnode = z;

						}

				else if(balance(tempnode)>1 && temp>tempnode.getL().getpos().getWordIndex())
						{
							tempnode.setL(rotateL(tempnode.getL()));

							AvlNode temppar = tempnode.getP();
							if(temppar==null)
								{
									root=rotateR(tempnode);
									break;

								}

								AvlNode z =rotateR(tempnode);

							if(temp<=temppar.getL().getpos().getWordIndex())
								temppar.setL(z);
							else 
								temppar.setR(z);

							tempnode = z;


						}
			}

				 if(tempnode.getR()!=null)
			{
				if(balance(tempnode)<-1 && temp<=tempnode.getR().getpos().getWordIndex())
						{
							tempnode.setR(rotateR(tempnode.getR()));

							AvlNode temppar = tempnode.getP();
							if(temppar==null)
								{
									root=rotateL(tempnode);
									break;

								}

								AvlNode z =rotateL(tempnode);
							if(temp<=temppar.getL().getpos().getWordIndex())
								temppar.setL(z);
							else 
								temppar.setR(z);

							tempnode = z;
						}

				else if(balance(tempnode)<-1 && temp>tempnode.getR().getpos().getWordIndex())
						{
							AvlNode temppar = tempnode.getP();
							if(temppar==null)
								{
									root=rotateL(tempnode);
									break;

								}
							AvlNode z =rotateL(tempnode);
							if(temp<=temppar.getL().getpos().getWordIndex())
								temppar.setL(z);
							else 
								temppar.setR(z);

							tempnode = z;
						}
			}
			tempnode = tempnode.getP();
		}



	}

	public void printtree()
	{
		if(root==null)
			return;
		
		if(root.getL()!=null)
		{AvlTree a = new AvlTree(root.getL());
		
				a.printtree();}
		System.out.print(root.getpos().getWordIndex() + " ,");
		
		if(root.getR()!=null)
		{AvlTree b = new AvlTree(root.getR());
		
				b.printtree();}
		

		return;

	}
}
