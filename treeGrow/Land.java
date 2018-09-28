//package treeGrow;

public class Land{
	
	// to do
	// sun exposure data here
	int DimX;
	int DimY;
	float SunExposure[][];
	float shade[][];

	static float shadefraction = 0.1f; // only this fraction of light is transmitted by a tree

	Land(int dx, int dy) {
		DimX = dx;
		DimY = dy;
		SunExposure = new float [DimY][DimX];
		shade = new float [DimY][DimX];
	}

	// return the number of landscape cells in the x dimension
	public int getDimX() {
		return DimX; 
	}
	
	// return the number of landscape cells in the y dimension
	public int getDimY() {
		return DimY; 
	}
	
	// Reset the shaded landscape to the same as the initial sun exposed landscape
	// Needs to be done after each growth pass of the simulator
	public void resetShade() {
		// to do
		shade = SunExposure;
	}
	
	// return the sun exposure of the initial unshaded landscape at position <x,y?
	public float getFull(int x, int y) {
		// to do
		if (y<DimY && x<DimX && x>0 && y>0){
			return SunExposure[y][x];
		}else {
			return 0.0f;
			}
	}
	
	// set the sun exposure of the initial unshaded landscape at position <x,y> to <val>
	public void setFull(int x, int y, float val) {
		if (y<DimY && x<DimX && x>0 && y>0){
			SunExposure[y][x] = val;
		}
	}
	
	// return the current sun exposure of the shaded landscape at position <x,y>
	public float getShade(int x, int y) {
		// to do 
		if (y<DimY && x<DimX && x>0 && y>0){
			return shade[y][x];
		}else {
			return 0.0f;
			}
	}
	
	// set the sun exposure of the shaded landscape at position <x,y> to <val>
	public void setShade(int x, int y, float val){
		// to do
		if (y<DimY && x<DimX && x>0 && y>0){
		shade[y][x] = val;
	}
	}
	
	// reduce the sun exposure of the shaded landscape to 10% of the original
	// within the extent of <tree>
	public synchronized void shadow(Tree tree){
		// to do
		int extent = Math.round(tree.getExt());
		int ytree = tree.getY();
		int xtree = tree.getX();
		
		for (int i = ytree-extent; i<ytree+extent; i++)
		{
			for (int j = xtree-extent; j<xtree+extent; j++)
			{
				if (i<DimY && j<DimX && i>0 && j>0)
				{
					setShade(xtree, ytree, getShade(xtree,ytree)*shadefraction);
				}
			}
		}
	}
}
