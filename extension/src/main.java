public class main {

	public static void main(String[] args) {
		Grid grid = new Grid(6, 6);
        new CornFarmer(grid, 0, 0);
        new RadishFarmer(grid, 5, 5);
        new Rabbit(grid, 0, 5);
        new Beaver(grid, 5, 0);
        new HorizontalTransporter(grid, 0, 2, 10);
        new NearestTransporter(grid, 4, 1, 10);
        new NearestTransporter(grid, 4, 4, 10);

        Game game = new Game(grid);
        game.run(50);
	}
}
