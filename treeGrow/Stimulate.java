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
				trees[t].setExt(trees[t].getExt()+0.45f);//trees[t].sunexposure(landscape)/1000);
					//trees[t].sunexposure(landscape);
					//trees[t].sungrow(landscape);
					//landscape.setShade(trees[t].getX(), trees[t].getY(), trees[t].getExt());
					
			}
	}
		
	}
}
