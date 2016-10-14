package lifegame;

/**
 * Created by kuro on 2016/10/14.
 */
public class BoardModel {
	private int cols;
	private int rows;

	public BoardModel(int c, int r) {
		cols = c;
		rows = r;
	}

	public int getCols() {
		return cols;
	}

	public int getRows() {
		return rows;
	}
}
