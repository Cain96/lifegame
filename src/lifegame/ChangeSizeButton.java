package lifegame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Cain96 on 2016/11/16.
 */
public class ChangeSizeButton extends JDialog implements ActionListener {

	private JDialog dialog;

	public ChangeSizeButton(JFrame frame) {

		//ダイアログ画面の設定
		dialog = new JDialog(frame, "盤面サイズ変更", true);
		dialog.setPreferredSize(new Dimension(200, 135)); //希望サイズの指定
		dialog.setMinimumSize(new Dimension(200, 135)); //最小サイズの指定

		JPanel basePanel = new JPanel();
		dialog.setContentPane(basePanel);
		basePanel.setLayout(new BorderLayout());

		JPanel colsPanel = new JPanel();
		colsPanel.setLayout(new BorderLayout());
		//cols入力ボックス
		JLabel colsLabel = new JLabel("横入力");
		colsPanel.add(colsLabel, BorderLayout.NORTH);
		JTextField colsField = new JTextField(5);
		colsPanel.add(colsField, BorderLayout.SOUTH);
		basePanel.add(colsPanel, BorderLayout.NORTH);

		JPanel rowsPanel = new JPanel();
		rowsPanel.setLayout(new BorderLayout());
		//rows入力ボックス
		JLabel rowsLabel = new JLabel("縦入力");
		rowsPanel.add(rowsLabel, BorderLayout.NORTH);
		JTextField rowsField = new JTextField(5);
		rowsPanel.add(rowsField, BorderLayout.SOUTH);
		basePanel.add(rowsPanel, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BorderLayout());
		//okボタン
		JButton buttonOk = new JButton("OK");
		buttonPanel.add(buttonOk, BorderLayout.WEST);
		DialogAction action = new DialogAction(colsField, rowsField, dialog, buttonOk, frame);
		buttonOk.addActionListener(action);

		//キャンセルボタン
		JButton buttonCancel = new JButton("Cancel");
		buttonPanel.add(buttonCancel, BorderLayout.EAST);
		buttonCancel.addActionListener(action);

		basePanel.add(buttonPanel, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//dialog設定
		dialog.setSize(200, 135);
		dialog.setVisible(true);
		dialog.setResizable(false);
	}
}
