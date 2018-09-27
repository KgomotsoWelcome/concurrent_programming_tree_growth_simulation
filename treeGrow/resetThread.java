public class resetThread extends Thread{
    Tree[] trees;
	Land landscape;
    public resetThread(Tree[]trees,Land landscape)
    {
        this.trees = trees;
        this.landscape = landscape;
    }
    
    public void run()
    {
		for(int t = 0; t < trees.length; t++)
		{
				trees[t].setExt(0.4f);
			
				
		}
	}
}
