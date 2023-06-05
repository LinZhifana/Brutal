package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class other {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void otherWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					other window = new other();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public other() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
<<<<<<< Updated upstream

=======
	
>>>>>>> Stashed changes
}
