package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class accueil {

	private JFrame frame;
	private static int positionAbout=0;

	/**
	 * Launch the application.
	 */
	public static void startAccueil() {
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
		frame = new JFrame("C'est du brutal!");
		frame.setBounds(100, 100, 814, 637);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 800, 600);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panelAbout = new JPanel();
		panelAbout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		panelAbout.setBounds(32, 30, 730, 540);
		panel.add(panelAbout);
		panelAbout.setOpaque(false);
		panelAbout.setLayout(null);
		
		JButton btnNext = new JButton("");
		
		btnNext.setOpaque(false);
		btnNext.setBorderPainted(false);
		btnNext.setContentAreaFilled(false);
		btnNext.setBounds(518, 31, 33, 39);
		panelAbout.add(btnNext);
		
		JButton btnFront = new JButton("");
		
		btnFront.setOpaque(false);
		btnFront.setContentAreaFilled(false);
		btnFront.setBorderPainted(false);
		btnFront.setBounds(175, 31, 33, 39);
		panelAbout.add(btnFront);
		
		JButton btnCompris = new JButton("");
		
		btnCompris.setOpaque(false);
		btnCompris.setContentAreaFilled(false);
		btnCompris.setBorderPainted(false);
		btnCompris.setBounds(258, 418, 210, 47);
		btnCompris.setEnabled(false);
		panelAbout.add(btnCompris);
		
		JLabel lblAbout = new JLabel("");
		lblAbout.setIcon(new ImageIcon(accueil.class.getResource("/image/about/FrameAbout1.png")));
		lblAbout.setBounds(30, 0, 690, 540);
		panelAbout.add(lblAbout);
		panelAbout.setVisible(false);
		
		JButton btnAboutButton = new JButton("");
		btnAboutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//positionAbout=0;
				panelAbout.setVisible(true);
			}
		});
		btnAboutButton.setOpaque(false);
		btnAboutButton.setBorderPainted(false);
		btnAboutButton.setContentAreaFilled(false);
		btnAboutButton.setBounds(501, 459, 231, 88);
		panel.add(btnAboutButton);
		
		JButton btnContinueButton = new JButton("");
		btnContinueButton.setOpaque(false);
		btnContinueButton.setBorderPainted(false);
		btnContinueButton.setContentAreaFilled(false);
		btnContinueButton.setBounds(501, 355, 231, 80);
		panel.add(btnContinueButton);
		
		JButton btnStartButton = new JButton("");
		btnStartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				personnalisation.personnaliser();
				frame.dispose();
			}
		});
		btnStartButton.setOpaque(false);
		btnStartButton.setBorderPainted(false);
		btnStartButton.setContentAreaFilled(false);
		btnStartButton.setBounds(501, 238, 231, 88);
		panel.add(btnStartButton);
		
		btnFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(positionAbout) {
				case 0:
					//btnFront.setEnabled(false);
					
					break;
				case 1:
					lblAbout.setIcon(new ImageIcon(accueil.class.getResource("/image/about/FrameAbout1.png")));
					positionAbout--;
					break;
				case 2:
					lblAbout.setIcon(new ImageIcon(accueil.class.getResource("/image/about/FrameAbout2.png")));
					positionAbout--;
					break;
				case 3:
					lblAbout.setIcon(new ImageIcon(accueil.class.getResource("/image/about/FrameAbout3.png")));
					positionAbout--;
					break;
				case 4:
					lblAbout.setIcon(new ImageIcon(accueil.class.getResource("/image/about/FrameAbout4.png")));
					btnCompris.setEnabled(false);
					positionAbout--;
					btnNext.setEnabled(true);
					break;
				}
			}
		});
		
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(positionAbout) {
				case 0:
					lblAbout.setIcon(new ImageIcon(accueil.class.getResource("/image/about/FrameAbout2.png")));
					positionAbout++;
					btnFront.setEnabled(true);
					break;
				case 1:
					lblAbout.setIcon(new ImageIcon(accueil.class.getResource("/image/about/FrameAbout3.png")));
					positionAbout++;
					break;
				case 2:
					lblAbout.setIcon(new ImageIcon(accueil.class.getResource("/image/about/FrameAbout4.png")));
					positionAbout++;
					break;
				case 3:
					lblAbout.setIcon(new ImageIcon(accueil.class.getResource("/image/about/FrameAbout5.png")));
					positionAbout++;
					btnCompris.setEnabled(true);
					break;
				case 4:
					//btnNext.setEnabled(false);
					break;
				}
				
				
			}
		});
		
		
		btnCompris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				positionAbout=0;
				lblAbout.setIcon(new ImageIcon(accueil.class.getResource("/image/about/FrameAbout1.png")));
				panelAbout.setVisible(false);
			}
		});
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(accueil.class.getResource("/image/accueil.png")));
		lblNewLabel.setBounds(0, 0, 800, 600);
		panel.add(lblNewLabel);
		
		
		
	}
	
	
}
