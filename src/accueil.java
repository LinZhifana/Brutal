import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class accueil {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					accueil window = new accueil();
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
	public accueil() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 814, 637);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 800, 600);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnAboutButton = new JButton("");
		btnAboutButton.setOpaque(false);
		btnAboutButton.setContentAreaFilled(false);
		btnAboutButton.setBounds(501, 459, 231, 88);
		panel.add(btnAboutButton);
		
		JButton btnContinueButton = new JButton("");
		btnContinueButton.setOpaque(false);
		btnContinueButton.setContentAreaFilled(false);
		btnContinueButton.setBounds(501, 355, 231, 80);
		panel.add(btnContinueButton);
		
		JButton btnStartButton = new JButton("");
		btnStartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				personnalisation.distributer();
				frame.dispose();
			}
		});
		btnStartButton.setOpaque(false);
		btnStartButton.setContentAreaFilled(false);
		btnStartButton.setBounds(501, 238, 231, 88);
		panel.add(btnStartButton);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(accueil.class.getResource("/image/accueil.png")));
		lblNewLabel.setBounds(0, 0, 800, 600);
		panel.add(lblNewLabel);
	}
}
