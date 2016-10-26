package lifegame;

import javax.swing.JPanel;
import java.awt.Graphics;

/**
 * Created by kuro on 2016/10/26.
 */
public class BoardView extends JPanel {

	int c, r;
	private BoardModel boardModel;
	private boolean[][] cells;

	public BoardView(Settings settings) {
		c = settings.getCols();
		r = settings.getRows();
		boardModel = new BoardModel(c, r);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		for (int i = 0; i < c + 1; i++) {
			g.drawLine(20, 20 * i, 20 * (c + 1), 20 * i);
			g.drawLine(20 * (i + 1), 0, 20 * (i + 1), 20 * r);
		}
	}

	private boolean isAlive(int x, int y) {
		cells = boardModel.getCells();
		return cells[x][y];
	}
}
