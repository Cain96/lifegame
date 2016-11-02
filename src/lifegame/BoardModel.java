package lifegame;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kuro on 2016/10/14.
 */
public class BoardModel {

	private int cols;
	private int rows;
	private int oldCounter = 0;

	private boolean[][] cells;


	private List<BoardListener> listeners;
	private CellsListHelper cellsListHelper;

	public BoardModel(int c, int r) {
		cols = c;
		rows = r;
		cells = new boolean[rows][cols];
		listeners = new ArrayList<BoardListener>();
		cellsListHelper = new CellsListHelper(new Settings());
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
	public void fireUpdate() {
		for (BoardListener listener : listeners) {
			listener.updated(this);
		}
	}


	//一世代更新後fireUpdateを呼び出すmethod
	public void next() {
		int aliveCount = 0;
		boolean[][] nextCells = new boolean[rows][cols];

		addOldCells();

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

	//oldCellsに格納するmethod
	private void addOldCells() {
		if (oldCounter < 32) {
			cellsListHelper.addList(cells);
			oldCounter++;
		} else {
			cellsListHelper.removeList();
			cellsListHelper.addList(cells);
			oldCounter++;
		}
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

			if ((0 <= x0 && x0 < cols) && (0 <= y0 && y0 < rows)) {
				if (cells[x0][y0]) {
					alive++;
				}
			} else {
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

	//巻き戻しmethod
	public void undo() {
		if (cellsListHelper.returnListSize() != 0) {
			cells = cellsListHelper.getList();
			fireUpdate();
		} else {
			System.out.println("Not Found");
		}
	}

	//巻き戻し可能か否かmethod
	public boolean isUndoable() {
		if (cellsListHelper.returnListSize() < 1) {
			return false;
		} else {
			return true;
		}
	}

	//生死判断method
	public boolean isAlive(int x, int y) {
		return cells[y][x];
	}
}
