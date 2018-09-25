//package treeGrow;

// Trees define a canopy which covers a square area of the landscape
public class Tree{
	
private
	int xpos;	// x-coordinate of center of tree canopy
	int ypos;	// y-coorindate of center of tree canopy
	float ext;	// extent of canopy out in vertical and horizontal from center
	
	static float growfactor = 1000.0f; // divide average sun exposure by this amount to get growth in extent
	
public	
	Tree(int x, int y, float e){
		xpos=x; ypos=y; ext=e;
	}
	
	// return the x-position of the tree center
	synchronized int getX() {
		return xpos;
	}
	
	// return the y-position of the tree center
	synchronized int getY() {
		return ypos;
	}
	
	// return the extent of the tree
	synchronized float getExt() {
		return ext;
	}
	
	// set the extent of the tree to <e>
	synchronized void setExt(float e) {
		ext = e;
	}

	// return the average sunlight for the cells covered by the tree
	synchronized float sunexposure(Land land){
		// to do 
		
		float treeSum = 0;
		int extent = (int)getExt();
		int ytree = getY();
		int xtree = getX();
		
		int count = 0;
		
		for (int i = ytree-extent; i<ytree+extent; i++)
		{
			for (int j = xtree-extent; j<xtree+extent; j++)
			{
				treeSum = treeSum + land.getShade(j,i);
				count++;
			}
		}
		
		float average = treeSum/count;
		return 0.0f; // not correct
	}
	
	// is the tree extent within the provided range [minr, maxr)
	synchronized boolean inrange(float minr, float maxr) {
		return (ext >= minr && ext < maxr);
	}
	
	// grow a tree according to its sun exposure
	synchronized void sungrow(Land land) {
		// to do
		ext = ext + land.getShade(getX(),getY());
	}
}
