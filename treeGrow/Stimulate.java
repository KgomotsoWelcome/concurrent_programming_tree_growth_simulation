public class Stimulate extends Thread implements Runnable{
	
    Tree[] trees;
	Land landscape;
	
    public Stimulate(Tree[]trees,Land landscape)
    {
        this.trees = trees;
        this.landscape = landscape;
    }
    
    public void run()
    {
		while(true)
		{
			for(int t = trees.length-1; t > 0; t--)
			{
				trees[t].sungrow(landscape);
			
			}
		}
		
	}
}
