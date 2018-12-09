public class CornFarmer extends AbstractItem {
	protected int Cstock;
	private int secondFarmer;
	public int distance;


	public CornFarmer(Grid grid,int xLocation, int yLocation) {
		super();
		this.grid = grid;
		xCoordinate = xLocation;
		yCoordinate = yLocation;
		super.grid.registerItem(xLocation, yLocation, this);
	}
	
	@Override
	public String toString () {
		return "Corn"+"(" +this.getStock()+")";
	}
	
	@Override
	public void process(TimeStep timeStep) {
		if(timeStep.getValue() % 4 ==0 && check()) {
			addToStock(25);
			super.grid.recordProduction(25);
		}
		
	}
	
	//check whether the grid have two consumer in two distance
	public boolean check() {
		//check horizontal left
		for(int i= 0;i<yCoordinate;i++) {
			if(grid.grid[xCoordinate][i] != null) {
				if(grid.grid[xCoordinate][i].getClass().equals(CornFarmer.class) || grid.grid[xCoordinate][i].getClass().equals(RadishFarmer.class)) {
					secondFarmer = i;
					distance = yCoordinate - secondFarmer;
					if (Math.abs(distance)<=2) {
						return false;
					}
				}
			
			}
		}
		
		//check horizontal right
		for(int i= yCoordinate+1;i<grid.grid[0].length;i++) {
			if(grid.grid[xCoordinate][i] != null) {
				if(grid.grid[xCoordinate][i].getClass().equals(CornFarmer.class) || grid.grid[xCoordinate][i].getClass().equals(RadishFarmer.class)) {
					secondFarmer = i;
					distance = yCoordinate - secondFarmer;
					if (Math.abs(distance)<=2) {
						return false;
					}
				}
			
			}
		}
		
		//check vertical top
		for(int i= 0;i<xCoordinate;i++) {
			if(grid.grid[i][yCoordinate] != null) {
			if(grid.grid[i][yCoordinate].getClass().equals(CornFarmer.class) || grid.grid[i][yCoordinate].getClass().equals(RadishFarmer.class)) {
				secondFarmer = i;
				distance = xCoordinate - secondFarmer;
				if (Math.abs(distance)<=2) {
					return false;
				}		
			}
			}
		}
		
		//check vertical down
		for(int i=xCoordinate+1; i<grid.grid.length;i++) {
		if(grid.grid[i][yCoordinate] != null) {
		if(grid.grid[i][yCoordinate].getClass().equals(CornFarmer.class)||grid.grid[i][yCoordinate].getClass().equals(RadishFarmer.class)) {
			secondFarmer = i;
			distance = xCoordinate - secondFarmer;
			if (Math.abs(distance)<=2) {
				return false;
			}		
		}
	}
	}
		
		return true;
	}
	@Override
	protected int getStock() {
		return Cstock;
	}

	@Override
	protected void addToStock(int nutrition) {
		Cstock += nutrition;
		super.grid.addToStockAt(xCoordinate, yCoordinate, nutrition);

	}

	@Override
	protected void reduceStock(int nutrition) {
		Cstock -= nutrition;
		super.grid.reduceStockAt(xCoordinate, yCoordinate, nutrition);
	}

}
