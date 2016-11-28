package lifegame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Cain96 on 2016/11/16.
 */


class DialogAction implements ActionListener {
	JTextField cols, rows;
	JDialog dialog;
	JButton ok;
	JFrame owner;

	public DialogAction(JTextField cols, JTextField rows, JDialog dialog, JButton ok, JFrame owner) {
		this.cols = cols;
		this.rows = rows;
		this.dialog = dialog;
		this.ok = ok;
		this.owner = owner;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String numCols, numRows;
		Settings settings = new Settings();

		if (e.getSource() == ok) {
			if (isNumber((numCols = cols.getText())) && isNumber(numRows = rows.getText())) {
				settings.setCols(Integer.parseInt(numCols));
				settings.setRows(Integer.parseInt(numRows));
				dialog.dispose();
				owner.dispose();
				SwingUtilities.invokeLater(new Main(settings));
			}
		} else {
			cols.setText("");
			rows.setText("");
		}
	}

	private boolean isNumber(String number) {
		java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("^[0-9]*$");
		java.util.regex.Matcher matcher = pattern.matcher(number);
		return matcher.matches();
	}
}
