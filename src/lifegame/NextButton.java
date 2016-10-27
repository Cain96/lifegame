package lifegame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by kuro on 2016/10/27.
 */
public class NextButton implements ActionListener {

	private BoardView boardView;
	private BoardModel boardModel;

	public NextButton(BoardView view, BoardModel model) {
		boardModel = model;
		boardView = view;
	}

	public void next() {
		boardModel.next();
	}

	public void repaint() {
		boardView.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		next();
		repaint();
	}
}
