package game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class GameWindow extends JFrame implements ActionListener {

	private GameButton[][] buttons;
	private GameButton resetButton;
	private JLabel statusLabel;
	private static int times_clicked = 0; // times that a button(any button) has been clicked.
	private static String player;
	private GameButton clicked_button;
	private boolean full; // check if board is full.
	private String winner;

	public GameWindow(int size) {
		super("Let's play a game!");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		statusLabel = new JLabel("Current player: 1");

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(size, size));

		buttons = new GameButton[size][];
		for (int i = 0; i < size; i++) {
			buttons[i] = new GameButton[size];
			for (int j = 0; j < size; j++) {
				GameButton button = new GameButton();
				button.addActionListener(this);
				button.setFont(button.getFont().deriveFont(25.0f));
				button.setPreferredSize(new Dimension(100, 100));
				buttonPanel.add(button);
				buttons[i][j] = button;
			}
		}

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(statusLabel, BorderLayout.NORTH);
		getContentPane().add(buttonPanel, BorderLayout.CENTER);

		resetButton = new GameButton("Reset");
		resetButton.addActionListener(this);
		getContentPane().add(resetButton, BorderLayout.SOUTH);

		pack();
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == resetButton) {
			for (int i = 0; i < buttons.length; i++) {
				for (int j = 0; j < buttons[0].length; j++) {
					buttons[i][j].reset(); // i is row, j is column.
				}
			}
			winner = null;
			full = false;
			times_clicked = 0;
			statusLabel.setText("Current player: 1");

		} else {
			for (int i = 0; i < buttons.length; i++) {
				for (int j = 0; j < buttons[0].length; j++) {
					if (buttons[i][j] == e.getSource()) {
						clicked_button = buttons[i][j];
					}
				}
			}

			/* i.e. if e.getSource() is one of the buttons in buttons[][] */
			if (clicked_button != null) {
				times_clicked += 1;
				/*
				 * if no. of times a button is clicked is odd, then player 1 has clicked.
				 * otherwise if even, player 2 has clicked.
				 */
				if ((times_clicked % 2) == 1) {
					player = "1";
				} else if ((times_clicked % 2) == 0) {
					player = "2";
				}
				clicked_button.setSymbol(player);

				/* Checks if one of the players has one by making a 2 by 2 square. */
				for (int row = 0; row < buttons.length - 1; row++) {
					for (int column = 0; column < buttons[0].length - 1; column++) {
						GameButton button_up_left = buttons[row][column];
						GameButton button_up_right = buttons[row][column + 1];
						GameButton button_down_left = buttons[row + 1][column];
						GameButton button_down_right = buttons[row + 1][column + 1];

						if (((button_up_left.getSymbol()) != null) && ((button_up_right.getSymbol()) != null)
								&& ((button_down_left.getSymbol()) != null)
								&& ((button_down_right.getSymbol()) != null)) {

							if ((button_up_left.getSymbol().equals(button_up_right.getSymbol()))
									&& (button_up_right.getSymbol().equals(button_down_right.getSymbol()))
									&& (button_down_right.getSymbol().equals(button_down_left.getSymbol()))
									&& (button_down_left.getSymbol().equals(button_up_left.getSymbol()))) {

								winner = button_up_left.getSymbol();
							}
						}
					}
					if (winner != null) {
						break;
					}
				}

				if (winner != null) {
					statusLabel.setText("Winner is: " + winner);

					// then disable all buttons:
					for (int i = 0; i < buttons.length; i++) {
						for (int j = 0; j < buttons[0].length; j++) {
							buttons[i][j].setEnabled(false);
						}
					}

				} else if (winner == null) {

					/*
					 * Check if board is full with no winner. isEnabled method returns true if
					 * button is enabled.
					 */
					full = true;
					for (int i = 0; i < buttons.length; i++) {
						for (int j = 0; j < buttons[0].length; j++) {
							/*
							 * if there is any button that hasn't been clicked on yet, i.e. if symbol =
							 * null, then board is not full.
							 */
							if (buttons[i][j].getSymbol() == null) {
								full = false;
							
							}
						}
					}

					if (full == false) {
						/*
						 * specifies who the next player is on the status label, i.e. the next turn. the
						 * 'current player' is the next player to play, i.e. to click button.
						 */
						if (player.equals("1")) {
							statusLabel.setText("Current player: 2");
						} else if (player.equals("2")) {
							statusLabel.setText("Current player: 1");
						}
					} else if (full == true) {
						statusLabel.setText("Board is full with no winner");
					}
				}
			}
		}
	}

	/**
	 * Main method -- just creates and displays the window.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new GameWindow(4).setVisible(true);
			}
		});
	}
}
