package lifegame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by kuro on 2016/10/27.
 */
public class UndoButton implements ActionListener {

	private BoardView boardView;
	private BoardModel boardModel;

	public UndoButton(BoardView view, BoardModel model) {
		boardModel = model;
		boardView = view;
	}

	public void undo() {
		boardModel.undo();
	}

	public void repaint() {
		boardView.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		undo();
		repaint();
	}
}
