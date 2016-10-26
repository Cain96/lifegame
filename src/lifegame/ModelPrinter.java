package lifegame;

/**
 * Created by kuro on 2016/10/14.
 */
public class ModelPrinter implements BoardListener {

	public void updated(BoardModel model) {
		model.printForDebug();
		System.out.println();
	}
}
