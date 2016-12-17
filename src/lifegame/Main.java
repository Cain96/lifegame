package lifegame;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

/**
 * Created by Cain96 on 2016/10/13.
 */
public class Main implements Runnable {
	public Settings settings;

	public Main(Settings settings) {
		this.settings = settings;
	}

	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Main(new Settings()));
	}

	public void run() {
		BoardModel model = new BoardModel(settings.getRows(), settings.getCols());
		model.addlistener(new BoardView(settings, model));

		//ウィンドウの作成
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setTitle("Lifegame");

		//ウィンドウ内部のベースパネルの作成
		JPanel base = new JPanel();
		frame.setContentPane(base);
		frame.setPreferredSize(new Dimension(420, 480)); //希望サイズの指定
		frame.setMinimumSize(new Dimension(420, 480)); //最小サイズの指定

		base.setLayout(new BorderLayout());
		BoardView view = new BoardView(settings, model);
		base.add(view, BorderLayout.CENTER); //baseの中心にviewを配置

		//ボタンパネルの作成
		JPanel buttonPanel = new JPanel();
		base.add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new FlowLayout());

		//nextButton作成
		JButton nextButton = new JButton();
		nextButton.setText("Next");
		buttonPanel.add(nextButton);
		NextButton buttonNext = new NextButton(view, model);
		nextButton.addActionListener(buttonNext);

		//undoButton作成
		JButton undoButton = new JButton();
		undoButton.setText("Undo");
		buttonPanel.add(undoButton);
		UndoButton buttonUndo = new UndoButton(view, model);
		undoButton.addActionListener(buttonUndo);

		//newGameButton作成
		JButton newGameButton = new JButton();
		newGameButton.setText("New Game");
		buttonPanel.add(newGameButton);
		NewGameButton buttonNew = new NewGameButton(new Main(settings));
		newGameButton.addActionListener(buttonNew);
		undoButton.setEnabled(false); //初期状態の設定

		model.addlistener(new BoardListener() { //paintをする度にundoボタンの仕様可否を検討
			@Override
			public void updated(BoardModel m) {
				undoButton.setEnabled(m.isUndoable());
			}
		});

		//changeSizeButton作成
		JButton changeSizeButton = new JButton();
		changeSizeButton.setText("Change Board Size");
		buttonPanel.add(changeSizeButton);
		ChangeSizeButton buttonChangeSize = new ChangeSizeButton(frame);
		changeSizeButton.addActionListener(buttonChangeSize);

		frame.pack();
		frame.setVisible(true);
	}
}
