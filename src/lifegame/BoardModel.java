package lifegame;

/**
 * Created by kuro on 2016/10/14.
 */
public class BoardModel {
	private int cols;
	private int rows;
	private boolean[][] cells;

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

	public void printForDebug() {
		for (int i = 0; i < cells.length; i++){
			for(int j = 0; j < cells[0].length; j++){
				if(!cells[i][j]){
					System.out.print(".");
				}else{
					System.out.print("*");
				}
			}
			System.out.println();
		}
	}
}
