public class Grid extends AbstractGrid{
	protected int production;
	protected int consumption;
	protected int xLocation;
	protected int yLocation;
	public Grid(int xCoordinate, int yCoordinate){
		super();
		AbstractItem[][] grid = new AbstractItem[xCoordinate][yCoordinate];
		int[][] stock = new int[xCoordinate][yCoordinate];
		this.grid = grid;
		this.stock = stock;
		xLocation = xCoordinate;
		yLocation = yCoordinate;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return yLocation;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return xLocation;
	}

	@Override
	public void registerItem(int xCoordinate, int yCoordinate, AbstractItem item) {
		// TODO Auto-generated method stub
		grid[xCoordinate][yCoordinate] = item;
		
	}

	@Override
	public AbstractItem getItem(int xCoordinate, int yCoordinate) {
		// TODO Auto-generated method stub
		if (grid[xCoordinate][yCoordinate] == null) {
		return null;
		} else {
		return grid[xCoordinate][yCoordinate];
		}
	}

	@Override
	public int getStockAt(int xCoordinate, int yCoordinate) {
		// TODO Auto-generated method stub
		return stock[xCoordinate][yCoordinate];
	}

	@Override
	public void emptyStockAt(int xCoordinate, int yCoordinate) {
		// TODO Auto-generated method stub
		stock[xCoordinate][yCoordinate] = 0;
	}

	@Override
	public void addToStockAt(int xCoordinate, int yCoordinate, int nutrition) {
		// TODO Auto-generated method stub
		stock[xCoordinate][yCoordinate] += nutrition;
	}

	@Override
	public void reduceStockAt(int xCoordinate, int yCoordinate, int nutrition) {
		// TODO Auto-generated method stub
		stock[xCoordinate][yCoordinate] -= nutrition;
	}

	@Override
	public void setStockAt(int xCoordinate, int yCoordinate, int nutrition) {
		// TODO Auto-generated method stub
		stock[xCoordinate][yCoordinate] = nutrition;
		
	}

	@Override
	public void processItems(TimeStep timeStep) {
		// TODO Auto-generated method stub
		//all farmer found
				for (int i = 0; i < xLocation; i++) {
					for (int j = 0; j < yLocation; j++) {
						if (grid[i][j] != null) {
						if(grid[i][j].getClass().equals(CornFarmer.class)|| grid[i][j].getClass().equals(RadishFarmer.class) )   {
							grid[i][j].process(timeStep);			
						}
					}
				}	
				}
				//all transporter found
					for (int a = 0; a < xLocation; a++) {
						for (int b = 0; b < yLocation; b++) {
							if (grid[a][b] != null ) {
							if(grid[a][b].getClass().equals(HorizontalTransporter.class) || grid[a][b].getClass().equals(VerticalTransporter.class) ) {
//								System.out.print("a");
								grid[a][b].process(timeStep);
							}
//							
						}
					}	
				}
				//all consumer found
					for (int m = 0; m < xLocation; m++) {
						for (int n = 0; n < yLocation; n++) {
							if (grid[m][n] != null) {
							if(grid[m][n].getClass().equals(Beaver.class) || grid[m][n].getClass().equals(Rabbit.class) ) {
							grid[m][n].process(timeStep);
										}
									}
								}
							}		
	}

	@Override
	public void recordProduction(int nutrition) {
		// TODO Auto-generated method stub
		production += nutrition;
		
	}

	@Override
	public int getTotalProduction() {
		// TODO Auto-generated method stub
		return production;
	}

	@Override
	public void recordConsumption(int nutrition) {
		// TODO Auto-generated method stub
		consumption += nutrition;
		
	}

	@Override
	public int getTotalConsumption() {
		// TODO Auto-generated method stub
		return consumption;
	}

}
