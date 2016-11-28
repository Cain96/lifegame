package lifegame;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Arrays;

/**
 * Created by Cain96 on 2016/10/26.
 */
public class BoardView extends JPanel implements MouseListener, MouseMotionListener, BoardListener {

	private int c, r, w, h, interval;
	private BoardModel boardModel;
	private boolean flag = true;
	private int oldMousePoint[] = new int[2];


	public BoardView(Settings settings, BoardModel model) {
		c = settings.getCols();
		r = settings.getRows();
		boardModel = model;

		this.addMouseListener(this);
		this.addMouseMotionListener(this);

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		w = this.getWidth() - 1;
		h = this.getHeight() - 1;

		interval = widthHeightCheck(w / c, h / r);

		for (int i = 0; i < r + 1; i++) {
			g.drawLine(0, interval * i, interval * c, interval * i); //横線
		}
		for (int i = 0; i < c + 1; i++) {
			g.drawLine(interval * i, 0, interval * i, interval * r); //縦線
		}

		for (int i = 0; i < c; i++) {
			for (int j = 0; j < r; j++) {
				if (boardModel.isAlive(i, j)) {
					g.fillRect(fillCellCoordinate(i), fillCellCoordinate(j), interval, interval);
				}
			}
		}
	}

	private int widthHeightCheck(int w, int h) {

		if (w > h) {
			return h;
		} else {
			return w;
		}
	}

	private int fillCellCoordinate(int x) {
		return x * interval;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {

		int mousePoint[] = {e.getX() / interval, e.getY() / interval};

		if ((0 <= mousePoint[0] && mousePoint[0] < c) && (0 <= mousePoint[1] && mousePoint[1] < r)) {
			boardModel.changeCellState(mousePoint[0], mousePoint[1]);

			oldMousePoint = mousePoint.clone();
			repaint();
		} else {
			return;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {

		int mousePoint[] = {e.getX() / interval, e.getY() / interval};

		if ((0 <= mousePoint[0] && mousePoint[0] < c) && (0 <= mousePoint[1] && mousePoint[1] < r)) {

			if (Arrays.equals(mousePoint, oldMousePoint)) {
				flag = false;
			} else {
				flag = true;
			}

			if (flag) {
				boardModel.changeCellState(mousePoint[0], mousePoint[1]);
				repaint();
			}

			oldMousePoint = mousePoint.clone();
		}else{
			return;
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	@Override
	public void updated(BoardModel m) {
		m.printForDebug();
		System.out.println();
	}
}
