package lifegame;

import javax.swing.JPanel;
import java.awt.Graphics;

/**
 * Created by kuro on 2016/10/26.
 */
public class BoardView extends JPanel {

	private int c, r, w, h, interval;
	private BoardModel boardModel;

	public BoardView(Settings settings, BoardModel model) {
		c = settings.getCols();
		r = settings.getRows();
		boardModel = model;

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		w = this.getWidth() - 1;
		h = this.getHeight() - 1;

		interval = widthHeightCheck(w / r, h / c);

		for (int i = 0; i < c + 1; i++) {
			g.drawLine(0, interval * i, interval * r, interval * i); //横線
		}
		for (int i = 0; i < r + 1; i++) {
			g.drawLine(interval * i, 0, interval * i, interval * c); //縦線
		}

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (boardModel.isAlive(i, j)) {
					g.fillRect(fillCellCoordinate(i), fillCellCoordinate(j), interval, interval);
				}
			}
		}
	}

	public int widthHeightCheck(int w, int h) {

		if (w > h) {
			return h;
		} else {
			return w;
		}
	}

	public int fillCellCoordinate(int x) {
		return x * interval;
	}


}
