public class Beaver extends AbstractItem {
	
	protected int Bstore;
	private int remain;
	private int topup = 0;
	private int topup2 = 0;
	private int topup3 = 0;
	private int topup4 = 0;
	private static int limit = 5;
	private int vx ;

	public Beaver(Grid grid,int xLocation, int yLocation) {
		super();
		this.grid = grid;
		xCoordinate = xLocation;
		yCoordinate = yLocation;
		super.grid.registerItem(xLocation, yLocation, this);
	}

	@Override
	public void process(TimeStep timeStep) {
		
			if(check()) {
					if(check2()) {
								if(check4()) {
									update();
										}else {
											update();
											}	
										}else {
											update();
										}
			}else if(check2()){
					if(check4()) {
						update();
					}else{
						update();
						}
			}else if(check4()) {
				update();
			}
			else {
				check3();
				update();
			}
		
	}
	
	public void update() {
		if (topup + topup2  +topup3 + topup4> limit) {
//			System.out.print("B:"+topup + topup2 + topup3+ topup4);
			super.grid.recordConsumption(limit);
			topup = 0;
			topup2 = 0;
			topup3 = 0;
			topup4 = 0;
		}else {
//			System.out.print("B:"+topup + topup2 + topup3+ topup4);
			super.grid.recordConsumption(topup + topup2 + topup3+ topup4);
		topup = 0;
		topup2 = 0;
		topup3 = 0;
		topup4 = 0;
		}
	}
	//check HorizontalTransporter input	
	public boolean check() {	
		for(int i= 0;i<grid.grid[0].length;i++) {
			if(grid.grid[xCoordinate][i] != null) {
			if(grid.grid[xCoordinate][i].getClass().equals(HorizontalTransporter.class)) {
					if(grid.grid[xCoordinate][i].getStock() == 0) {
						return false;
					}
					
					if(grid.grid[xCoordinate][i].getStock() > 0 && grid.grid[xCoordinate][i].getStock() < 5) {
						//consumer
						this.addToStock(grid.grid[xCoordinate][i].getStock()); 
						topup = grid.grid[xCoordinate][i].getStock();
						//check consumer store
						if(Bstore >=0 && Bstore <=5-grid.grid[xCoordinate][i].getStock()) {
							this.addToStock(Bstore);
							topup = Bstore + grid.grid[xCoordinate][i].getStock();
							Bstore  = 0;							
						}
						if(Bstore > 5-grid.grid[xCoordinate][i].getStock()) {
							this.addToStock(5-grid.grid[xCoordinate][i].getStock());
							topup = 5;
							Bstore -= 5-grid.grid[xCoordinate][i].getStock();
						}
						//transporter
						super.grid.grid[xCoordinate][i].reduceStock(grid.grid[xCoordinate][i].getStock());
						return true;
						
					}
					
					if(grid.grid[xCoordinate][i].getStock() >= 5) {
						
						//consumer
						this.addToStock(5); 
						topup = 5;
						remain = grid.grid[xCoordinate][i].getStock() - 5;
						//check consumer store
						if(remain > 0 && Bstore >=0 && Bstore <50) {
							Bstore  += remain;	
						}
						if(Bstore >=50) {
							Bstore = 50;
						}
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
	
	//check VerticalTransporter input
	public boolean check2() {	
				for(int j= 0;j<grid.grid.length;j++) {
					if(grid.grid[j][yCoordinate] != null) {
					if( grid.grid[j][yCoordinate].getClass().equals(VerticalTransporter.class)) {
						vx = j;

						if(grid.grid[j][yCoordinate].getStock() == 0) {	
							return false;
						}
						
						if(grid.grid[j][yCoordinate].getStock()>0 && grid.grid[j][yCoordinate].getStock() < 5) {
							//consumer
							this.addToStock(grid.grid[j][yCoordinate].getStock()); 
							topup2 = grid.grid[j][yCoordinate].getStock();
							if(topup >0 && topup2 + topup >limit ) {
								topup2 = limit - topup;
							}
							
							//check consumer store
							if(Bstore >=0 && Bstore <=5-grid.grid[j][yCoordinate].getStock()) {
								this.addToStock(Bstore);
								
								if(topup >0 && topup2 + topup + Bstore>limit ) {
									topup2 = limit - topup;
									this.reduceStock(topup2 + topup + Bstore-limit);
									Bstore = Bstore+topup2 + topup- limit;
								}else {
									topup2 = Bstore + grid.grid[j][yCoordinate].getStock();
									Bstore  = 0;
								}
								
							}
							
							if(Bstore > 5-grid.grid[j][yCoordinate].getStock()) {
								this.addToStock(5-grid.grid[j][yCoordinate].getStock());
								
								if(topup >0 && topup2 + topup + 5-grid.grid[j][yCoordinate].getStock()>limit ) {
									topup2 = limit - topup;
									this.reduceStock(topup2 + topup + 5-grid.grid[j][yCoordinate].getStock()-limit);
								}else {
								topup2 = 5;
								Bstore -= 5-grid.grid[j][yCoordinate].getStock();
								}
							}
							//transporter
							super.grid.grid[j][yCoordinate].reduceStock(grid.grid[j][yCoordinate].getStock());
							return true;
						}
						
						if(grid.grid[j][yCoordinate].getStock() >= 5) {
							//consumer
							this.addToStock(5); 
							topup2 = 5;
							remain = grid.grid[j][yCoordinate].getStock() - 5;
							if(topup >0 && topup2 + topup >limit ) {
								topup2 = limit - topup;
								remain = grid.grid[j][yCoordinate].getStock() - topup2;
							}
							//check consumer store
							if(remain > 0 && Bstore >=0 && Bstore <50) {
								Bstore  += remain;									
							}	
							if(Bstore >=50) {
								Bstore = 50;
							}
							//transporter
							if(grid.grid[j][yCoordinate].getStock() >= 10) {
							super.grid.grid[j][yCoordinate].reduceStock(10);
							}else {
								super.grid.grid[j][yCoordinate].reduceStock(grid.grid[j][yCoordinate].getStock());
							}
							return true;
						}
					}
					}
				}
				return false;
	}

	//check VerticalTransporter input
		public boolean check4() {	
			if(grid.grid[vx][yCoordinate] != null) {
					for(int j= vx +1;j<grid.grid.length;j++) {
						if(grid.grid[j][yCoordinate] != null) {
						if( grid.grid[j][yCoordinate].getClass().equals(VerticalTransporter.class)) {

							if(grid.grid[j][yCoordinate].getStock() == 0) {	
								return false;
							}
							if(grid.grid[j][yCoordinate].getStock()>0 && grid.grid[j][yCoordinate].getStock() < 5) {
								//consumer
								this.addToStock(grid.grid[j][yCoordinate].getStock()); 
								topup4 = grid.grid[j][yCoordinate].getStock();
								if(topup4 + topup2 + topup >limit ) {
									topup4 = limit - topup - topup2;
								}
								//check consumer store
								if(Bstore >=0 && Bstore <=5-grid.grid[j][yCoordinate].getStock()) {
									this.addToStock(Bstore);
									
									if(topup4 + topup2 + topup + Bstore>limit ) {
										topup4 = limit - topup - topup2;
										this.reduceStock(topup4 =topup2 + topup + Bstore-limit);
										Bstore = Bstore+topup2 + topup + topup4 - limit;
									}else {
										topup4 = Bstore + grid.grid[j][yCoordinate].getStock();
										Bstore  = 0;
									}
								}
								
								if(Bstore > 5-grid.grid[j][yCoordinate].getStock()) {
									this.addToStock(5-grid.grid[j][yCoordinate].getStock());
									
									if(topup4 + topup2 + topup + 5-grid.grid[j][yCoordinate].getStock()>limit ) {
										topup4 = limit - topup- topup2;
										this.reduceStock(topup4 + topup2 + topup + 5-grid.grid[j][yCoordinate].getStock()-limit);
									}else {
									topup4 = 5;
									Bstore -= 5-grid.grid[j][yCoordinate].getStock();
									}
								}
								//transporter
								super.grid.grid[j][yCoordinate].reduceStock(grid.grid[j][yCoordinate].getStock());
								return true;
							}
							
							if(grid.grid[j][yCoordinate].getStock() >= 5) {
								//consumer
								this.addToStock(5); 
								topup4 = 5;
								remain = grid.grid[j][yCoordinate].getStock() - 5;
								if(topup4 + topup2 + topup >limit ) {
									topup4 = limit - topup - topup2;
									remain = grid.grid[j][yCoordinate].getStock() - topup4;
								}
								//check consumer store
								if(remain > 0 && Bstore >=0 && Bstore <50) {
									Bstore  += remain;										
								}	
								if(Bstore >=50) {
									Bstore = 50;
								}
								//transporter
								if(grid.grid[j][yCoordinate].getStock() >= 10) {
								super.grid.grid[j][yCoordinate].reduceStock(10);
								}else {
									super.grid.grid[j][yCoordinate].reduceStock(grid.grid[j][yCoordinate].getStock());
								}
								return true;
							}
						}
						}
					}
			
			}
					return false;
		}
	public boolean check3(){	
		//consumer
		//check consumer store
			if(Bstore >=0 && Bstore<5) {
				this.addToStock(Bstore); 
				topup3 = Bstore;
				Bstore = 0;		
				return true;
			}
			
			if(Bstore >=5) {				
				this.addToStock(5);
				topup3 = 5;
				Bstore -= 5;
				return true;
			}	
		return false;
	}
	
	
	
	@Override
	public String toString () {
		return "Beaver"+"(" +Bstore+")";
	}
	
	@Override
	protected int getStock() {
		// TODO Auto-generated method stub
		return super.grid.stock[xCoordinate][yCoordinate];
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
