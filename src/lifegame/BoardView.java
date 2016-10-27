package lifegame;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Arrays;

/**
 * Created by kuro on 2016/10/26.
 */
public class BoardView extends JPanel implements MouseListener, MouseMotionListener {

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

	private int calculateMousePosition(int x) {
		return x / interval;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {

		int mousePoint[] = {e.getX() / interval, e.getY() / interval};
		boardModel.changeCellState(mousePoint[0], mousePoint[1]);

		oldMousePoint = mousePoint.clone();

		repaint();
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

		if (Arrays.equals(mousePoint,oldMousePoint)) {
			flag = false;
		} else {
			flag = true;
		}

		if (flag) {
			boardModel.changeCellState(mousePoint[0], mousePoint[1]);
			repaint();
		}

		oldMousePoint = mousePoint.clone();
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}
}
