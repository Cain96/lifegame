package lifegame;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cain96 on 2016/10/25.
 */
public class CellsListHelper {
	public List<boolean[][]> oldCellsList = new ArrayList<boolean[][]>();
	public boolean[][] oldCell;
	private int c, r;

	public CellsListHelper(Settings settings) {
		c = settings.getCols();
		r = settings.getRows();
	}

	//Listへの追加method
	public void addList(boolean[][] cell) {
		copyCells(cell);
		oldCellsList.add(oldCell);
	}

	//Listの値の削除method
	public void removeList() {
		oldCellsList.remove(0);
	}

	//cellのDeepCopyMethod
	private void copyCells(boolean[][] cell) {
		oldCell = new boolean[r][c];
		for (int i = 0; i < cell[0].length; i++) {
			for (int j = 0; j < cell.length; j++) {
				oldCell[j][i] = cell[j][i];
			}
		}
	}

	//Listの値の取得method
	public boolean[][] getList() {
		boolean[][] cells = new boolean[c][r];
		cells = oldCellsList.get(oldCellsList.size() - 1);
		oldCellsList.remove(oldCellsList.size() - 1);
		return cells;
	}

	//Listのサイズを返すmethod
	public int returnListSize() {
		return oldCellsList.size();
	}
}
