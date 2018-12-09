public class HorizontalTransporter extends AbstractItem{
	private int Hstock;
	public int load;
	private int i;
	private int j;
	private int k = 0;
	private int m = 0;
	private int farmer;
	private int consumer;
	private int farmer2;
	private int consumer2;

	public HorizontalTransporter(Grid grid,int xLocation, int yLocation, int load) {	
		this.grid = grid;
		xCoordinate = xLocation;
		yCoordinate = yLocation;
		this.load = load;
		grid.registerItem(xCoordinate, yCoordinate, this);
//		System.out.print("b");
	}
	
	@Override
	public void process(TimeStep timeStep) {
		if(k ==1  || m ==1 ) {
			k =0;
			m =0;
		}
//		System.out.print("b");
		if (check()) {
//			System.out.print(" HorizontalTransporter working");
			if(grid.getStockAt(xCoordinate, farmer)>=load) {
				//transporter
				this.addToStock(load);
//				System.out.print("a");
				//farmer
				grid.grid[xCoordinate][farmer].reduceStock(load);
				
			}else {
				//transporter
				this.addToStock(grid.grid[xCoordinate][farmer].getStock());
				//farmer
				grid.grid[xCoordinate][farmer].reduceStock(grid.grid[xCoordinate][farmer].getStock());
			}
			
		}else {
//			System.out.print("HorizontalTransporter not working");
		}
		
		if (check2()) {
			if(grid.getStockAt(xCoordinate, farmer2)>=load) {
				//transporter
				this.addToStock(load);
				//farmer
				grid.grid[xCoordinate][farmer2].reduceStock(load);
				
			}else {
				//transporter
				this.addToStock(grid.grid[xCoordinate][farmer2].getStock());
				//farmer
				grid.grid[xCoordinate][farmer2].reduceStock(grid.grid[xCoordinate][farmer2].getStock());
			}
			
		}else {
		}
		
	}
	
	public boolean check() {
		// If the position of the horizontal transporter is correct, and there are farmers on one side and consumers on the other side, return true， otherwise return false
		if(yCoordinate != grid.grid[0].length && yCoordinate != 0 ) {
			for(i= 0;i<yCoordinate;i++) {
				if(grid.grid[xCoordinate][i] != null) {
				if(grid.grid[xCoordinate][i].getClass().equals(CornFarmer.class) || grid.grid[xCoordinate][i].getClass().equals(RadishFarmer.class)) {
					for(j=yCoordinate; j<grid.grid[0].length;j++) {
						if(grid.grid[xCoordinate][j] != null) {
						if(grid.grid[xCoordinate][j].getClass().equals(Rabbit.class)|| grid.grid[xCoordinate][j].getClass().equals(Beaver.class)) {
							farmer = i;
							consumer = j;
							m += 1;
							}
						
					}	
				}
				}
				
				
			}
		}
			if(m != 0) {
				return true;	
			}else {
				return false;
			}
		}else {
		return false;
		}
	}
	
	public boolean check2() {
//		System.out.print("run" );
		if(yCoordinate != grid.grid[0].length && yCoordinate != 0 ) {
			for(i= 0;i<yCoordinate;i++) {
				if(grid.grid[xCoordinate][i] != null) {
					if(grid.grid[xCoordinate][i].getClass().equals(Rabbit.class) ||grid.grid[xCoordinate][i].getClass().equals(Beaver.class)) {
						for(j=yCoordinate; j<grid.grid[0].length;j++) {
							if(grid.grid[xCoordinate][j] != null) {
							if(grid.grid[xCoordinate][j].getClass().equals(CornFarmer.class) || grid.grid[xCoordinate][j].getClass().equals(RadishFarmer.class)) {
								farmer2 = j;
								consumer2 = i;
								k += 1;								
							}
						
					}	
				}
				}
				
				
			}
		}
			if(k != 0) {
				return true;	
			}else {
				return false;
			}
		}else {
		return false;
		}
	}
	
	
	public int getLoad () {
		return load;
	}

	@Override
	public String toString () {
		return "HT";
//		return "HT" + "(" + Hstock +")";
	}
	
	
	@Override
	protected int getStock() {
		// TODO Auto-generated method stub
		return grid.stock[xCoordinate][yCoordinate];
	}

	@Override
	protected void addToStock(int nutrition) {
		// TODO Auto-generated method stub
		Hstock += nutrition;
		super.grid.addToStockAt(xCoordinate, yCoordinate, nutrition);
	}

	@Override
	protected void reduceStock(int nutrition) {
		Hstock -= nutrition;
		super.grid.reduceStockAt(xCoordinate, yCoordinate, nutrition);		
	}


}
