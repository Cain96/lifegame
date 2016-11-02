package lifegame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by kuro on 2016/10/27.
 */
public class NewGameButton implements ActionListener {

	private Main main;

	public NewGameButton(Main main) {
		this.main = main;
	}

	private void run() {
		main.run();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		run();
	}
}
