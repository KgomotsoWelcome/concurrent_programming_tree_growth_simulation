public class Stimulate extends Thread{
    Tree[] trees;
	Land landscape;
    public Stimulate(Tree[]trees,Land landscape)
    {
        this.trees = trees;
        this.landscape = landscape;
    }
    
    public void run()
    {
        float minh = 0.0f;
		float maxh = 2.0f;
		for(int layer = 0; layer <= 10; layer++) {
			for(int t = 0; t < trees.length; t++){
				//int rt = rndorder.get(t); 
				if(trees[t].inrange(minh,maxh)) { // only render trees in current band
					trees[t].sunexposure(landscape);
					//landscape.setShade(trees[t].getX(), trees[t].getY(), trees[t].getExt());
					//trees[t].sungrow(landscape);
				}
			}
			minh = maxh;  // next band of trees
			maxh += 2.0f;
		}
    }
    
}
