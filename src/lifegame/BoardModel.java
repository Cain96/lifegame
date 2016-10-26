package lifegame;

/**
 * Created by kuro on 2016/10/14.
 */
public class BoardModel {
	private int cols;
	private int rows;
	private int oldCounter=0;
	private boolean[][] cells = new boolean[rows][cols];
	private List<BoardListener> listeners;

	public BoardModel(int c, int r) {
		cols = c;
		rows = r;
		cells = new boolean[rows][cols];
		listeners = new ArrayList<BoardListener>();
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

	//一世代更新後fireUpdateを呼び出すmethod
	public void next() {
		int aliveCount = 0;
		boolean[][] nextCells = new boolean[rows][cols];
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[0].length; j++) {
				aliveCount = aliveCounter(i, j);
				nextCells[i][j] = deadOrAliveJudge(i, j, aliveCount);
			}
		}
		for (int i = 0; i < cells.length; i++) {
			cells[i] = nextCells[i].clone();
		}

		fireUpdate();
	}
	//生数カウントmethod
	private int aliveCounter(int x, int y) {
		int alive = 0;
		int near[][] = {
				{-1, -1}, {0, -1}, {1, -1},
				{-1, 0}, {1, 0},
				{-1, 1}, {0, 1}, {1, 1}
		};

		for (int k = 0; k < 8; k++) {
			int x0 = x + near[k][0];
			int y0 = y + near[k][1];

			try {
				if (cells[x0][y0]) {
					alive++;
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				continue;
			}
		}
		return alive;
	}

	//生死決定method
	private boolean deadOrAliveJudge(int i, int j, int aliveCount) {
		if (aliveCount < 2 | 3 < aliveCount) { //2,3以外の時
			if (cells[i][j]) {
				return !cells[i][j];
			} else {
				return cells[i][j];
			}
		} else {
			if (!cells[i][j] && aliveCount == 3) { //死んでいてかつ3の時
				return !cells[i][j];
			} else {
				return cells[i][j];
			}
		}
	}
}
