package lifegame;

/**
 * Created by kuro on 2016/10/14.
 */
public class BoardModel {
	private int cols;
	private int rows;
	private int oldCounter=0;
	private boolean[][] cells = new boolean[rows][cols];

	public BoardModel(int c, int r) {
		cols = c;
		rows = r;
		cells = new boolean[rows][cols];
	}

	public int getCols() {
		return cols;
	}

	public int getRows() {
		return rows;
	}

	//Debug用盤面の状態を出力するmethod
	public void printForDebug() {
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[0].length; j++) {
				if (!cells[i][j]) {
					System.out.print(".");
				} else {
					System.out.print("*");
				}
			}
			System.out.println();
		}
	}

	//指定されたセルの状態を変更するmethod
	public void changeCellState(int x, int y) {
			addOldCells();
		cells[y][x] = !cells[y][x];
		fireUpdate();
	}

	//listenersに要素を追加するmethod
	public void addlistener(BoardListener listener) {
		listeners.add(listener);
	}

	//listenerのupdateを行うためのmethod
	private void fireUpdate() {
		for (BoardListener listener : listeners) {
			listener.updated(this);
		}
	}
}
