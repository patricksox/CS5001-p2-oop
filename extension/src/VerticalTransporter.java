public class VerticalTransporter extends AbstractItem{
	private int Vstock;
	private int load;
	private int k = 0;
	private int m = 0;
	private int farmer;
	private int consumer;
	private int farmer2;
	private int consumer2;
	private int count = 1;
	private int count2 = 1;
	
	public VerticalTransporter(Grid grid,int xLocation, int yLocation, int load) {
		this.grid = grid;
		xCoordinate = xLocation;
		yCoordinate = yLocation;
		this.load = load;
		grid.registerItem(xLocation, yLocation, this);
	}
	@Override
	public void process(TimeStep timeStep) {
		if(k ==1  || m ==1 || count ==2 || count2 ==2) {
			k =0;
			m =0;
			count = 0;
			count2 = 0;
		}
		// TODO Auto-generated method stub

		if (check()) {
		if(grid.getStockAt(farmer, yCoordinate)>=load) {
			//transporter
				this.addToStock(load);
				//farmer
				super.grid.grid[farmer][yCoordinate].reduceStock(load);
//				System.out.print(grid.getStockAt(farmer, yCoordinate));
			}else {
				if(grid.getStockAt(farmer, yCoordinate)<load) {
//					System.out.print(grid.grid[farmer][yCoordinate].getStock());
					//transporter
					this.addToStock(grid.grid[farmer][yCoordinate].getStock());
					//farmer
					grid.grid[farmer][yCoordinate].reduceStock(grid.grid[farmer][yCoordinate].getStock());
				
				}
			}	
		}
		if(check2()){
			if(grid.getStockAt(farmer2, yCoordinate)>=load ) {
//				System.out.print("a");
				//transporter
				this.addToStock(load);
//				System.out.print(load);
				//farmer
				super.grid.grid[farmer2][yCoordinate].reduceStock(load);
				
			
			}else {
//				System.out.print("b");
				if(grid.getStockAt(farmer2, yCoordinate)<load ){
					//transporter
					this.addToStock(grid.grid[farmer2][yCoordinate].getStock());
					//farmer
					grid.grid[farmer2][yCoordinate].reduceStock(grid.grid[farmer2][yCoordinate].getStock());
//					System.out.print("1");
				
				}
			}
		}
		else {

//			System.out.print("VerticalTransporter not working");
		}
	}

	public boolean check() {
//		System.out.print("run" );
		// If the position of the vertical transporter is correct, and there are farmers on one side and consumers on the other side, return true， otherwise return false
		if(xCoordinate != grid.grid.length && xCoordinate != 0 ) {
			for(int i= 0;i<xCoordinate;i++) {
				if(grid.grid[i][yCoordinate] != null ) {
				if(grid.grid[i][yCoordinate].getClass().equals(CornFarmer.class) || grid.grid[i][yCoordinate].getClass().equals(RadishFarmer.class)) {
					for(int j=xCoordinate; j<grid.grid.length;j++) {
						if(grid.grid[j][yCoordinate] != null) {
						if(grid.grid[j][yCoordinate].getClass().equals(Rabbit.class)||grid.grid[j][yCoordinate].getClass().equals(Beaver.class)) {
							farmer = i;
							consumer = j;
							k += 1;
//							break;
//							System.out.print("run" );
						}
					}
					}		
				}	
			}
			}
			if(k != 0) {
				return true;
			}
		}
		return false;
		
	}
	
	public boolean check2() {
//		System.out.print("run" );
		if(xCoordinate != grid.grid.length && xCoordinate != 0 ) {
			for(int i= 0;i<xCoordinate;i++) {
				if(grid.grid[i][yCoordinate] != null ) {
		// If the position of the vertical transporter is correct, and there are farmers on one side and consumers on the other side, return true， otherwise return false
		if(grid.grid[i][yCoordinate].getClass().equals(Rabbit.class) ||grid.grid[i][yCoordinate].getClass().equals(Beaver.class)) {
			for(int j=xCoordinate; j<grid.grid.length;j++) {
				if(grid.grid[j][yCoordinate] != null) {
				if(grid.grid[j][yCoordinate].getClass().equals(CornFarmer.class) || grid.grid[j][yCoordinate].getClass().equals(RadishFarmer.class)) {
					farmer2 = j;
					consumer2 = i;
					m += 1;
				
				}
				}
				}		
			}	
		}
		}
		if(m!= 0) {
			return true;
		}
		}
		return false;
	}
	
	@Override
	public String toString () {
		return "VT";
	}
	
	@Override
	protected int getStock() {
		// TODO Auto-generated method stub
//		if()
		return grid.stock[xCoordinate][yCoordinate];
	}

	@Override
	protected void addToStock(int nutrition) {
		// TODO Auto-generated method stub
		Vstock += nutrition;
		super.grid.addToStockAt(xCoordinate, yCoordinate, nutrition);
	}

	@Override
	protected void reduceStock(int nutrition) {
		Vstock -= nutrition;
		super.grid.reduceStockAt(xCoordinate, yCoordinate, nutrition);		
	}

}
