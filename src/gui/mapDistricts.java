package gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import classes.view.ScoreboardFrame;

import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;


public class mapDistricts {

	private JFrame frame;
	private JProgressBar healthBar_left;
	private JProgressBar healthBar_right;
	private JProgressBar healthBar_left_1;
	private JProgressBar healthBar_right_1;
	private JProgressBar healthBar_left_2;
	private JProgressBar healthBar_right_2;
	private JProgressBar healthBar_left_3;
	private JProgressBar healthBar_right_3;
	private JProgressBar healthBar_left_4;
	private JProgressBar healthBar_right_4;
	private Timer timer;
	private JLabel lblWinnerHalleS;
	private JLabel lblWinnerBDE;
	private JLabel lblWinnerBibli;
	private JLabel lblWinnerQuartierAdmin;
	private JLabel lblWinnerHalleI;
	private String portraitP1;
	private String portraitP2;
	private String portraitP1Huge;
	private String portraitP2Huge;
	private JButton btnHallSportive;
	private JButton btnBDE;
	private JButton btnBibliotheque;
	private JButton btnQuartierAdmin;
	private JButton btnHalleIndustrielles;
	private static int pointP1;
	private static int pointP2;
	private JLabel lblNotifyRight;
	private JLabel lblNotifyLeft;
	private JLabel lblNotifyHalleS;
	private JLabel lblNotifyBDE;
	private JLabel lblNotifyBibli;
	private JLabel lblNotifyQartierAdmin;
	private JLabel lblNotifyHalleI;
	
	/**
	 * Launch the application.
	 */
	public static void startMap() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mapDistricts window = new mapDistricts();
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
	public mapDistricts() {
		initialize();
		timer.start();
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
		
		JButton btnPortrait_right = new JButton("");
		btnPortrait_right.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNotifyRight.setVisible(false);
			}
		});
		btnPortrait_right.setOpaque(false);
		btnPortrait_right.setBorderPainted(false);
		btnPortrait_right.setContentAreaFilled(false);
		
			if(personnalisation.getBranchP2()==branch.A21) {
				portraitP2Huge="/image/portrait/portrait_A21_right.png";
				portraitP2="/portrait_small/portrait_A21_small.png";
			}else if(personnalisation.getBranchP2()==branch.GI) {
				portraitP2Huge="/image/portrait/portrait_GI_right.png";
				portraitP2="/portrait_small/portrait_GI_small.png";
			}else if(personnalisation.getBranchP2()==branch.GM) {
				portraitP2Huge="/image/portrait/portrait_GM_right.png";
				portraitP2="/portrait_small/portrait_GM_small.png";
			}else if(personnalisation.getBranchP2()==branch.ISI) {
				portraitP2Huge="/image/portrait/portrait_ISI_right.png";
				portraitP2="/portrait_small/portrait_ISI_small.png";
			}else if(personnalisation.getBranchP2()==branch.MTE) {
				portraitP2Huge="/image/portrait/portrait_MTE_right.png";
				portraitP2="/portrait_small/portrait_MTE_small.png";
			}else if(personnalisation.getBranchP2()==branch.RT) {
				portraitP2Huge="/image/portrait/portrait_RT_right.png";
				portraitP2="/portrait_small/portrait_RT_small.png";
			}
		
		lblNotifyHalleS = new JLabel("");
		lblNotifyHalleS.setIcon(new ImageIcon(mapDistricts.class.getResource("/image/mapDistrict/! small.png")));
		lblNotifyHalleS.setBounds(136, 0, 87, 86);
		lblNotifyHalleS.setVisible(false);
		
		JPanel panelDistributerTreve = new JPanel();
		panelDistributerTreve.setBounds(100, 75, 600, 450);
		panel.add(panelDistributerTreve);
		panelDistributerTreve.setLayout(null);
		
		JLabel lblDisPortrait = new JLabel("");
		lblDisPortrait.setBounds(206, 10, 92, 90);
		panelDistributerTreve.add(lblDisPortrait);
		
		JLabel lblDistributerBG = new JLabel("");
		lblDistributerBG.setIcon(new ImageIcon(mapDistricts.class.getResource("/image/mapDistrict/distributerTreve/distributerTreve.png")));
		lblDistributerBG.setBounds(0, 0, 600, 450);
		panelDistributerTreve.add(lblDistributerBG);
		panel.add(lblNotifyHalleS);
		panelDistributerTreve.setVisible(false);
		
		lblNotifyHalleI = new JLabel("");
		lblNotifyHalleI.setIcon(new ImageIcon(mapDistricts.class.getResource("/image/mapDistrict/! small.png")));
		lblNotifyHalleI.setBounds(480, 423, 87, 86);
		lblNotifyHalleI.setVisible(false);
		panel.add(lblNotifyHalleI);
		
		lblNotifyQartierAdmin = new JLabel("");
		lblNotifyQartierAdmin.setIcon(new ImageIcon(mapDistricts.class.getResource("/image/mapDistrict/! small.png")));
		lblNotifyQartierAdmin.setBounds(160, 451, 87, 86);
		lblNotifyQartierAdmin.setVisible(false);
		panel.add(lblNotifyQartierAdmin);
		
		lblNotifyBDE = new JLabel("");
		lblNotifyBDE.setIcon(new ImageIcon(mapDistricts.class.getResource("/image/mapDistrict/! small.png")));
		lblNotifyBDE.setBounds(395, 123, 87, 92);
		lblNotifyBDE.setVisible(false);
		panel.add(lblNotifyBDE);
		
		lblNotifyBibli = new JLabel("");
		lblNotifyBibli.setIcon(new ImageIcon(mapDistricts.class.getResource("/image/mapDistrict/! small.png")));
		lblNotifyBibli.setBounds(215, 302, 87, 86);
		lblNotifyBibli.setVisible(false);
		panel.add(lblNotifyBibli);
		
		lblNotifyRight = new JLabel("");
		lblNotifyRight.setIcon(new ImageIcon(mapDistricts.class.getResource("/image/mapDistrict/! .png")));
		lblNotifyRight.setBounds(668, 99, 122, 92);
		lblNotifyRight.setVisible(false);
		panel.add(lblNotifyRight);
		
		
		btnPortrait_right.setIcon(new ImageIcon(mapDistricts.class.getResource(portraitP2Huge)));
		
		btnPortrait_right.setBounds(581, -19, 209, 162);
		panel.add(btnPortrait_right);
		
		
		lblNotifyLeft = new JLabel("");
		lblNotifyLeft.setIcon(new ImageIcon(mapDistricts.class.getResource("/image/mapDistrict/! .png")));
		lblNotifyLeft.setBounds(16, 119, 122, 92);
		lblNotifyLeft.setVisible(false);
		panel.add(lblNotifyLeft);
		
		
		JButton btnPortrait_left = new JButton("");
		btnPortrait_left.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNotifyLeft.setVisible(false);
			}
		});
		btnPortrait_left.setOpaque(false);
		btnPortrait_left.setBorderPainted(false);
		btnPortrait_left.setContentAreaFilled(false);
		if(personnalisation.getBranchP1()==branch.A21) {
			portraitP1Huge="/image/portrait/portrait_A21_left.png";
			portraitP1="/portrait_small/portrait_A21_small.png";
		}else if(personnalisation.getBranchP1()==branch.GI) {
			portraitP1Huge="/image/portrait/portrait_GI_left.png";
			portraitP1="/portrait_small/portrait_GI_small.png";
		}else if(personnalisation.getBranchP1()==branch.GM) {
			portraitP1Huge="/image/portrait/portrait_GM_left.png";
			portraitP1="/portrait_small/portrait_GM_small.png";
		}else if(personnalisation.getBranchP1()==branch.ISI) {
			portraitP1Huge="/image/portrait/portrait_ISI_left.png";
			portraitP1="/portrait_small/portrait_ISI_small.png";
		}else if(personnalisation.getBranchP1()==branch.MTE) {
			portraitP1Huge="/image/portrait/portrait_MTE_left.png";
			portraitP1="/portrait_small/portrait_MTE_small.png";
		}else if(personnalisation.getBranchP1()==branch.RT) {
			portraitP1Huge="/image/portrait/portrait_RT_left.png";
			portraitP1="/portrait_small/portrait_RT_small.png";
		}
		btnPortrait_left.setIcon(new ImageIcon(mapDistricts.class.getResource(portraitP1Huge)));
		
		btnPortrait_left.setBounds(-19, 0, 216, 171);
		panel.add(btnPortrait_left);
		
		healthBar_right_4 = new JProgressBar();
		healthBar_right_4.setValue(90);
		healthBar_right_4.setStringPainted(true);
		healthBar_right_4.setString("75");
		healthBar_right_4.setMinimum(0);
		healthBar_right_4.setMaximum(150);
		healthBar_right_4.setForeground(new Color(30, 144, 255));
		healthBar_right_4.setBounds(650, 473, 80, 20);
		panel.add(healthBar_right_4);
		setHealth(healthBar_right_4,60);
		
		healthBar_left_4 = new JProgressBar();
		healthBar_left_4.setStringPainted(true);
		healthBar_left_4.setMinimum(0);
		healthBar_left_4.setMaximum(150);
		healthBar_left_4.setForeground(new Color(244, 164, 96));
		healthBar_left_4.setBounds(565, 473, 80, 20);
		panel.add(healthBar_left_4);
		setHealth(healthBar_left_4,80);
		
		healthBar_right_3 = new JProgressBar();
		healthBar_right_3.setValue(90);
		healthBar_right_3.setStringPainted(true);
		healthBar_right_3.setString("75");
		healthBar_right_3.setMinimum(0);
		healthBar_right_3.setMaximum(150);
		healthBar_right_3.setForeground(new Color(30, 144, 255));
		healthBar_right_3.setBounds(318, 473, 80, 20);
		panel.add(healthBar_right_3);
		setHealth(healthBar_right_3,70);
		
		healthBar_left_2 = new JProgressBar();
		healthBar_left_2.setStringPainted(true);
		healthBar_left_2.setMinimum(0);
		healthBar_left_2.setMaximum(150);
		healthBar_left_2.setForeground(new Color(244, 164, 96));
		healthBar_left_2.setBounds(292, 359, 80, 20);
		panel.add(healthBar_left_2);
		setHealth(healthBar_left_2,65);
		
		healthBar_right_2 = new JProgressBar();
		healthBar_right_2.setValue(90);
		healthBar_right_2.setStringPainted(true);
		healthBar_right_2.setString("75");
		healthBar_right_2.setMinimum(0);
		healthBar_right_2.setMaximum(150);
		healthBar_right_2.setForeground(new Color(30, 144, 255));
		healthBar_right_2.setBounds(377, 359, 80, 20);
		panel.add(healthBar_right_2);
		setHealth(healthBar_right_2,55);
		
		healthBar_left_3 = new JProgressBar();
		healthBar_left_3.setStringPainted(true);
		healthBar_left_3.setMinimum(0);
		healthBar_left_3.setMaximum(150);
		healthBar_left_3.setForeground(new Color(244, 164, 96));
		healthBar_left_3.setBounds(233, 473, 80, 20);
		panel.add(healthBar_left_3);
		setHealth(healthBar_left_3,105);
		
		healthBar_right_1 = new JProgressBar();
		healthBar_right_1.setValue(90);
		healthBar_right_1.setStringPainted(true);
		healthBar_right_1.setString("75");
		healthBar_right_1.setMinimum(0);
		healthBar_right_1.setMaximum(150);
		healthBar_right_1.setForeground(new Color(30, 144, 255));
		healthBar_right_1.setBounds(565, 171, 80, 20);
		panel.add(healthBar_right_1);
		setHealth(healthBar_right_1,50);
		
		healthBar_left_1 = new JProgressBar();
		healthBar_left_1.setStringPainted(true);
		healthBar_left_1.setMinimum(0);
		healthBar_left_1.setMaximum(150);
		healthBar_left_1.setForeground(new Color(244, 164, 96));
		healthBar_left_1.setBounds(480, 171, 80, 20);
		panel.add(healthBar_left_1);
		setHealth(healthBar_left_1,60);
		
		healthBar_right = new JProgressBar();
		
		healthBar_right.setValue(90);
		healthBar_right.setStringPainted(true);
		healthBar_right.setString("75");
		healthBar_right.setMinimum(0);
		healthBar_right.setMaximum(150);
		healthBar_right.setForeground(new Color(30, 144, 255));
		healthBar_right.setBounds(300, 61, 80, 20);
		panel.add(healthBar_right);
		setHealth(healthBar_right,90);
		
		
		healthBar_left = new JProgressBar();
		healthBar_left.setLocation(215, 61);
		healthBar_left.setSize(80, 20);
		healthBar_left.setMinimum(0);
		healthBar_left.setMaximum(150);
		healthBar_left.setStringPainted(true);
		
		        // 添加血条到窗口
		panel.add(healthBar_left);
		healthBar_left.setForeground(new Color(244, 164, 96)); // 设置颜色
		// 设置初始血量
        
        setHealth(healthBar_left,75);
        
        
        lblWinnerHalleS = new JLabel("");
		lblWinnerHalleS.setBounds(361, 33, 80, 70);
		panel.add(lblWinnerHalleS);
		
		lblWinnerBDE = new JLabel("");
		lblWinnerBDE.setBounds(650, 141, 80, 70);
		panel.add(lblWinnerBDE);
		
		lblWinnerBibli = new JLabel("");
		lblWinnerBibli.setBounds(439, 331, 80, 70);
		panel.add(lblWinnerBibli);
		
		lblWinnerQuartierAdmin = new JLabel("");
		lblWinnerQuartierAdmin.setBounds(377, 451, 80, 70);
		panel.add(lblWinnerQuartierAdmin);
		
		lblWinnerHalleI = new JLabel("");
		lblWinnerHalleI.setBounds(720, 441, 80, 70);
		panel.add(lblWinnerHalleI);
		
		
		
        int delay = 1000; // 1秒
        timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(lblNotifyRight.isVisible()||lblNotifyLeft.isVisible()||lblNotifyHalleS.isVisible()||lblNotifyBDE.isVisible()||lblNotifyBibli.isVisible()||lblNotifyQartierAdmin.isVisible()||lblNotifyHalleI.isVisible()) {
            		int result = JOptionPane.showConfirmDialog(null, "Vous n'avez pas fini d'allouer, êtes-vous sûr de vouloir continuer?", "Attention!", JOptionPane.YES_NO_OPTION);
                    
                    // 处理用户的选择结果
                    if (result == JOptionPane.YES_OPTION) {
                        
                    } else if (result == JOptionPane.NO_OPTION) {
                    	timer.stop();
                    	return;
                    } else if (result == JOptionPane.CANCEL_OPTION) {
                    	timer.stop();
                    	return;
                    }
            	}
                // 减少血条的值
            	setHealthMinus5(healthBar_left);
        		setHealthMinus5(healthBar_right);
        		setHealthMinus5(healthBar_left_1);
        		setHealthMinus5(healthBar_right_1);
        		setHealthMinus5(healthBar_left_2);
        		setHealthMinus5(healthBar_right_2);
        		setHealthMinus5(healthBar_left_3);
        		setHealthMinus5(healthBar_right_3);
        		setHealthMinus5(healthBar_left_4);
        		setHealthMinus5(healthBar_right_4);
            	
            	if(healthBar_left.getValue()==0||healthBar_right.getValue()==0) {
            		timer.stop();
            		if(healthBar_left.getValue()<healthBar_right.getValue()) {
            			lblWinnerHalleS.setIcon(new ImageIcon(mapDistricts.class.getResource(portraitP2)));
            			pointP2++;
            		}else {
            			lblWinnerHalleS.setIcon(new ImageIcon(mapDistricts.class.getResource(portraitP1)));
            			pointP1++;
            		}
            		notifyReservist();
            		lblNotifyHalleS.setVisible(true);
            		btnHallSportive.setEnabled(true);
            		healthBar_left.setVisible(false);
            		healthBar_right.setVisible(false);
            		healthBar_left.setValue(150);
            		healthBar_right.setValue(150);
            		
            	}else if(healthBar_left_1.getValue()!=-1&&healthBar_right_1.getValue()!=-1&&(healthBar_left_1.getValue()==0||healthBar_right_1.getValue()==0)) {
            		timer.stop();
            		if(healthBar_left_1.getValue()<healthBar_right_1.getValue()) {
            			
            			lblWinnerBDE.setIcon(new ImageIcon(mapDistricts.class.getResource(portraitP2)));
            			pointP2++;
            		}else {
            			
            			lblWinnerBDE.setIcon(new ImageIcon(mapDistricts.class.getResource(portraitP1)));
            			pointP1++;
            		}
            		notifyReservist();
            		lblNotifyBDE.setVisible(true);
            		btnBDE.setEnabled(true);
            		healthBar_left_1.setVisible(false);
            		healthBar_right_1.setVisible(false);
            		healthBar_left_1.setValue(150);
            		healthBar_right_1.setValue(150);
            		
            	}else if(healthBar_left_2.getValue()==0||healthBar_right_2.getValue()==0) {
            		timer.stop();
            		if(healthBar_left_2.getValue()<healthBar_right_2.getValue()) {
            			//System.out.println("llllllllllllllll");
            			lblWinnerBibli.setIcon(new ImageIcon(mapDistricts.class.getResource(portraitP2)));
            			pointP2++;
            		}else {
            			//System.out.println("RRRRRRRRRRRRRR");
            			lblWinnerBibli.setIcon(new ImageIcon(mapDistricts.class.getResource(portraitP1)));
            			pointP1++;
            		}
            		notifyReservist();
            		lblNotifyBibli.setVisible(true);
            		btnBibliotheque.setEnabled(true);
            		healthBar_left_2.setVisible(false);
            		healthBar_right_2.setVisible(false);
            		healthBar_left_2.setValue(150);
            		healthBar_right_2.setValue(150);
            		
            	}else if(healthBar_left_3.getValue()==0||healthBar_right_3.getValue()==0) {
            		timer.stop();
            		if(healthBar_left_3.getValue()<healthBar_right_3.getValue()) {
            			lblWinnerQuartierAdmin.setIcon(new ImageIcon(mapDistricts.class.getResource(portraitP2)));
            			pointP2++;
            		}else {
            			lblWinnerQuartierAdmin.setIcon(new ImageIcon(mapDistricts.class.getResource(portraitP1)));
            			pointP1++;
            		}
            		notifyReservist();
            		lblNotifyBibli.setVisible(true);
            		btnQuartierAdmin.setEnabled(true);
            		healthBar_left_3.setVisible(false);
            		healthBar_right_3.setVisible(false);
            		healthBar_left_3.setValue(150);
            		healthBar_right_3.setValue(150);
            		
            	}else if(healthBar_left_4.getValue()==0||healthBar_right_4.getValue()==0) {
            		timer.stop();
            		if(healthBar_left_4.getValue()<healthBar_right_4.getValue()) {
            			lblWinnerHalleI.setIcon(new ImageIcon(mapDistricts.class.getResource(portraitP2)));
            			pointP2++;
            		}else {
            			lblWinnerHalleI.setIcon(new ImageIcon(mapDistricts.class.getResource(portraitP1)));
            			pointP1++;
            		}
            		notifyReservist();
            		lblNotifyHalleI.setVisible(true);
            		btnHalleIndustrielles.setEnabled(true);
            		healthBar_left_4.setVisible(false);
            		healthBar_right_4.setVisible(false);
            		healthBar_left_4.setValue(150);
            		healthBar_right_4.setValue(150);
            		
            	}
            	
            
        		if(pointP1>2||pointP2>2) {
        			frame.dispose();
        			ScoreboardFrame scoreboardFrame = new ScoreboardFrame();
        		}
        			
       
            	
        		
            }
        });
		
		
		btnHallSportive = new JButton("");
		btnHallSportive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNotifyHalleS.setVisible(false);
			}
		});
		btnHallSportive.setBounds(233, 33, 136, 30);
		btnHallSportive.setOpaque(false);
		btnHallSportive.setBorderPainted(false);
		btnHallSportive.setContentAreaFilled(false);
		panel.add(btnHallSportive);
		btnHallSportive.setEnabled(false);
		
		btnBDE = new JButton("");
		btnBDE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNotifyBDE.setVisible(false);
			}
		});
		btnBDE.setOpaque(false);
		btnBDE.setContentAreaFilled(false);
		btnBDE.setBorderPainted(false);
		btnBDE.setBounds(471, 141, 184, 30);
		panel.add(btnBDE);
		btnBDE.setEnabled(false);
		
		btnBibliotheque = new JButton("");
		btnBibliotheque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNotifyBibli.setVisible(false);
			}
		});
		btnBibliotheque.setOpaque(false);
		btnBibliotheque.setContentAreaFilled(false);
		btnBibliotheque.setBorderPainted(false);
		btnBibliotheque.setBounds(305, 331, 136, 30);
		panel.add(btnBibliotheque);
		btnBibliotheque.setEnabled(false);
		
		btnQuartierAdmin = new JButton("");
		btnQuartierAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNotifyQartierAdmin.setVisible(false);
			}
		});
		btnQuartierAdmin.setOpaque(false);
		btnQuartierAdmin.setContentAreaFilled(false);
		btnQuartierAdmin.setBorderPainted(false);
		btnQuartierAdmin.setBounds(233, 441, 184, 30);
		panel.add(btnQuartierAdmin);
		btnQuartierAdmin.setEnabled(false);
		
		btnHalleIndustrielles = new JButton("");
		btnHalleIndustrielles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNotifyHalleI.setVisible(false);
			}
		});
		btnHalleIndustrielles.setOpaque(false);
		btnHalleIndustrielles.setContentAreaFilled(false);
		btnHalleIndustrielles.setBorderPainted(false);
		btnHalleIndustrielles.setBounds(559, 441, 185, 30);
		panel.add(btnHalleIndustrielles);
		btnHalleIndustrielles.setEnabled(false);
		
		JButton btnfight = new JButton("fight");
		btnfight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (timer.isRunning()) {
				    timer.stop();
				}
				timer.start();
				
			}
		});
		btnfight.setBounds(41, 470, 97, 23);
		panel.add(btnfight);
		
		

        
		
		JLabel lblMap = new JLabel("");
		lblMap.setIcon(new ImageIcon(mapDistricts.class.getResource("/image/mapDistrict/mapDistrict.png")));
		lblMap.setBounds(0, 0, 800, 600);
		panel.add(lblMap);
		
		//fight();
	}
	
	// 设置血量
    public void setHealth(JProgressBar healthBar,int health) {
        healthBar.setValue(health);
        healthBar.setString(""+health);
    }
    
    public void setHealthMinus5(JProgressBar healthBar) {
    	int value=healthBar.getValue()-5;
    	healthBar.setValue(value);
        healthBar.setString(""+value);
    }
    
    public void notifyReservist() {
    	lblNotifyLeft.setVisible(true);
    	lblNotifyRight.setVisible(true);
    }
    
    public void resetNotifyReservist() {
    	lblNotifyLeft.setVisible(false);
    	lblNotifyRight.setVisible(false);
    }
}
