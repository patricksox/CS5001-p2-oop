public class Rabbit extends AbstractItem {
	 private int topup = 0;
	 private int topup2 = 0;
	 private static int limit = 8;
		
		public Rabbit(Grid grid,int xLocation, int yLocation) {
			super();
			this.grid = grid;
			xCoordinate = xLocation;
			yCoordinate = yLocation;
			super.grid.registerItem(xLocation, yLocation, this);
		}

		@Override
		public void process(TimeStep timeStep) {
		
			//check transporter input
			if(check()) {
				if(check2()) {
					update();
				}else {		
					update();
				}
			}else if(check2()) {
				update()	;
			}else{
//				System.out.print("R:"+topup + topup2 + " ");
				super.grid.recordConsumption(topup2 +topup);
				topup = 0;
				 topup2 = 0;
			}

		}
		
		public void update() {
			if (topup + topup2 > limit) {
//				System.out.print("R:"+topup + topup2 + " ");
				super.grid.recordConsumption(limit);
				 topup = 0;
				 topup2 = 0;
			}
			else {
//				System.out.print("R:"+topup + topup2+ " " );
			super.grid.recordConsumption(topup2 +topup);
			 topup = 0;
			 topup2 = 0;
			}
		}
		public boolean check() {	
			//check HorizontalTransporter input
			for(int i= 0;i<grid.grid[0].length;i++) {
				if(grid.grid[xCoordinate][i] != null) {
				if(grid.grid[xCoordinate][i].getClass().equals(HorizontalTransporter.class)) {
					
						if(grid.grid[xCoordinate][i].getStock() == 0) {
							
							return false;
						}
						if(grid.grid[xCoordinate][i].getStock() > 0 && grid.grid[xCoordinate][i].getStock() < 8) {
							//consumer
							this.addToStock(grid.grid[xCoordinate][i].getStock()); 
							topup = grid.grid[xCoordinate][i].getStock();

							//transporter
							super.grid.grid[xCoordinate][i].reduceStock(grid.grid[xCoordinate][i].getStock());
							return false;
						}
						
						if(grid.grid[xCoordinate][i].getStock() >= 8) {
							//consumer
							this.addToStock(8); 
							topup = 8;
							//transporter
							if(grid.grid[xCoordinate][i].getStock() >= 10) {
							super.grid.grid[xCoordinate][i].reduceStock(10);
							}else {
								super.grid.grid[xCoordinate][i].reduceStock(grid.grid[xCoordinate][i].getStock());
							}
							return true;
						}
					}
				}
			}

		return false;
		}
		
		public boolean check2() {
			//check VerticalTransporter input
			for(int j= 0;j<grid.grid.length;j++) {
				if(grid.grid[j][yCoordinate] != null) {
				if( grid.grid[j][yCoordinate].getClass().equals(VerticalTransporter.class)) {
					
					if(grid.grid[j][yCoordinate].getStock() == 0) {
						return false;
					}
					
					if(grid.grid[j][yCoordinate].getStock() >0 && grid.grid[j][yCoordinate].getStock() < 8) {
						//consumer
						if(topup >0) {
							
							topup2 = 8 - topup;
							this.addToStock(topup2); 
							//transporter
							super.grid.grid[j][yCoordinate].reduceStock(grid.grid[j][yCoordinate].getStock());
					}else {
						topup2 = grid.grid[j][yCoordinate].getStock();
						this.addToStock(topup2); 
						//transporter
						super.grid.grid[j][yCoordinate].reduceStock(grid.grid[j][yCoordinate].getStock());
					}
						return true;
					}
					
					if(grid.grid[j][yCoordinate].getStock() >= 8) {
						//consumer
						if(topup >0) {
							
							topup2 = 8 - topup;
							this.addToStock(topup2); 
						}else {
						this.addToStock(8); 
						topup2 = 8;
						}
						//transporter
						super.grid.grid[j][yCoordinate].reduceStock(grid.grid[j][yCoordinate].getStock());
					
						return true;
					}
					
				}
			}
		}
			return false;
		}
		@Override
		public String toString () {
			return "Rabbit"+"(0)";
		}
		
		@Override
		protected int getStock() {
			// TODO Auto-generated method stub
			return grid.stock[xCoordinate][yCoordinate];
			
		}

		@Override
		protected void addToStock(int nutrition) {
			super.grid.addToStockAt(xCoordinate, yCoordinate, nutrition);	
		}

		@Override
		protected void reduceStock(int nutrition) {
			super.grid.reduceStockAt(xCoordinate, yCoordinate, nutrition);	
		}

	}


