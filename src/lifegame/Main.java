package lifegame;

/**
 * Created by kuro on 2016/10/13.
 */
public class Main {
	private static Settings settings;

	public static void main (String args[]){
		settings = new Settings();

		BoardModel model = new BoardModel(settings.getCols(), settings.getRows());
		model.addlistener(new ModelPrinter());

		model.changeCellState(1,1);
		model.changeCellState(2,2);
		model.changeCellState(0,3);
		model.changeCellState(1,3);
		model.changeCellState(2,3);
		model.changeCellState(4,4);
		model.changeCellState(4,4);

		for(int i=0; i<30; ++i) {
			model.next();
		}
	}
}
