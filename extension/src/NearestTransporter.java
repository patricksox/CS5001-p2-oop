public class NearestTransporter extends AbstractItem{

	private int Nstock;
	public int load;
//	private int i;
//	private int j;
	private int k = 0;
	private int a = 0;
	private int farmer;
	private int consumer;
	public int distance;
	private int farmerX;
	private int farmerY;
	private int farmer2X;
	private int farmer2Y;
	private int consumerX;
	private int consumerY;
	private int consumer2X;
	private int consumer2Y;
	
	
	public NearestTransporter(Grid grid,int xLocation, int yLocation, int load) {
		this.grid = grid;
		xCoordinate = xLocation;
		yCoordinate = yLocation;
		this.load = load;
		grid.registerItem(xCoordinate, yCoordinate, this);
	}

	@Override
	public void process(TimeStep timeStep) {

		if(check()){
			if(check2()){
			if(grid.getStockAt(farmerX, farmerY)>=load) {
				//transporter
				this.addToStock(load);
//						System.out.print("a");
				//farmer
				grid.grid[farmerX][farmerY].reduceStock(load);
				k =0;
				a =0;
			}else {
				//transporter
				this.addToStock(grid.grid[farmerX][farmerY].getStock());
				//farmer
				grid.grid[farmerX][farmerY].reduceStock(grid.grid[farmerX][farmerY].getStock());
				k =0;
				a =0;
			}
		}
		k =0;
		a =0;
		}else {
			k =0;
			a =0;
		}
		
	}
	
	//check whether the grid have one farmer in nearby
		public boolean check() {

			for(int i= 0;i<grid.grid[0].length;i++) {
					for(int j= 0;j<grid.grid.length;j++) {
						// System.out.print(i);
						if(grid.grid[j][i] != null  ) {

							// System.out.print(i);
							// System.out.print(j);
				if( grid.grid[j][i].getClass().equals(CornFarmer.class) ||  grid.grid[j][i].getClass().equals(RadishFarmer.class)) {
					// System.out.print(yCoordinate);
					// System.out.print(xCoordinate);
					if((Math.abs(i - yCoordinate) + Math.abs(j - xCoordinate) ) < 3 && Math.abs(i - yCoordinate) != 2 && Math.abs(j - xCoordinate)!=2 ){
					if(k == 0){
					farmerX = j;
					farmerY = i;
					k+=1;
					// System.out.print("B:");
					return true;

					}else{
						if((Math.abs(i - yCoordinate) + Math.abs(j - xCoordinate) ) == (Math.abs(farmerY - yCoordinate) + Math.abs(farmerX- xCoordinate) ) ){
						farmer2X = j;
						farmer2Y = i;
					return false;		
						}else{
							farmer2X = j;
							farmer2Y = i;
							return true;
						}					
					}
					
						}
					}
				}
			}
		}
			return false;
		}
		
		//check whether the grid have consumer in nearby
		public boolean check2() {
			
			for(int i= 0;i<grid.grid[0].length;i++) {
					for(int j= 0;j<grid.grid.length;j++) {
						if(grid.grid[j][i] != null  ) {
			if( grid.grid[j][i].getClass().equals(Rabbit.class) ||  grid.grid[j][i].getClass().equals(Beaver.class)){
				if((Math.abs(i - yCoordinate) + Math.abs(j - xCoordinate) ) < 3 && Math.abs(i - yCoordinate) != 2 && Math.abs(j - xCoordinate)!=2 ){
					if(a == 0){
						consumerX = j;
						consumerY = i;
						a+=1;
						// System.out.print("B:");
						return true;
	
						}else{
							if((Math.abs(i - yCoordinate) + Math.abs(j - xCoordinate) ) == (Math.abs(consumerY - yCoordinate) + Math.abs(consumerX- xCoordinate) ) ){
								consumer2X = j;
								consumer2Y = i;
								return false;		
								}else{
									consumer2X = j;
									consumer2Y = i;
									return true;
								}
						}
				}
			}
		
	}
}
			}
			return false;
		}
		

				
	public String toString () {
		return "NT";
//					return "HT" + "(" + Hstock +")";
	}
	@Override
	protected int getStock() {
		// TODO Auto-generated method stub
		return grid.stock[xCoordinate][yCoordinate];
	}

	@Override
	protected void addToStock(int nutrition) {
		// TODO Auto-generated method stub
		Nstock += nutrition;
		super.grid.addToStockAt(xCoordinate, yCoordinate, nutrition);
	}

	@Override
	protected void reduceStock(int nutrition) {
		Nstock -= nutrition;
		super.grid.reduceStockAt(xCoordinate, yCoordinate, nutrition);		
	}


}
