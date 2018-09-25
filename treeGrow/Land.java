//package treeGrow;

public class Land{
	
	// to do
	// sun exposure data here
	int DimX;
	int DimY;
	float SunExposure[][];

	static float shadefraction = 0.1f; // only this fraction of light is transmitted by a tree

	Land(int dx, int dy) {
		DimX = dx;
		DimY = dy;
		SunExposure = new float [DimY][DimX];
	}

	// return the number of landscape cells in the x dimension
	public synchronized int getDimX() {
		return DimX; 
	}
	
	// return the number of landscape cells in the y dimension
	public synchronized int getDimY() {
		return DimY; 
	}
	
	// Reset the shaded landscape to the same as the initial sun exposed landscape
	// Needs to be done after each growth pass of the simulator
	public synchronized void resetShade() {
		// to do
		for (int i = 0; i<DimY; i++)
		{
			for(int j = 0; j<DimX; j++)
			{
				SunExposure[i][j] = (SunExposure[i][j] + (SunExposure[i][j]*0.9f));
			}
		}
	}
	
	// return the sun exposure of the initial unshaded landscape at position <x,y?
	public synchronized float getFull(int x, int y) {
		// to do
		return SunExposure[y][x];
	}
	
	// set the sun exposure of the initial unshaded landscape at position <x,y> to <val>
	public synchronized void setFull(int x, int y, float val) {
		SunExposure[y][x] = val;
	}
	
	// return the current sun exposure of the shaded landscape at position <x,y>
	public synchronized float getShade(int x, int y) {
		// to do 
		return SunExposure[y][x];
	}
	
	// set the sun exposure of the shaded landscape at position <x,y> to <val>
	public synchronized void setShade(int x, int y, float val){
		// to do
		SunExposure[y][x] = val*shadefraction;
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
				SunExposure[i][j] = SunExposure[i][j]*shadefraction;
			}
		}
	}
}
