package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.plaf.basic.BasicComboBoxUI;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class personnalisation {

	private JFrame frame;
	private JTextField textFieldP1;
	private JTextField textFieldP2;
	private boolean confirmedP1=false;
	private boolean confirmedP2=false;
	private static String nameP1;
	private static String nameP2;
	private static branch branchP1;
	private static branch branchP2;
	public static branch getBranchP2() {
		return branchP2;
	}

	public static void setBranchP2(branch branchP2) {
		personnalisation.branchP2 = branchP2;
	}

	public static String getNameP1() {
		return nameP1;
	}

	public static void setNameP1(String nameP1) {
		personnalisation.nameP1 = nameP1;
	}

	public static String getNameP2() {
		return nameP2;
	}

	public static void setNameP2(String nameP2) {
		personnalisation.nameP2 = nameP2;
	}
	
	public JTextField getP1NameInput() {
        return textFieldP1;
    }
	
	public JTextField getP2NameInput() {
        return textFieldP2;
    }

	/**
	 * Launch the application.
	 */
	public static void personnaliser() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					personnalisation window = new personnalisation();
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
	public personnalisation() {
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
		
		textFieldP1 = new JTextField();
		textFieldP1.setFont(new Font("Bauhaus 93", Font.ITALIC, 31));
		textFieldP1.setOpaque(false); // 设置文本字段为透明
		textFieldP1.setBorder(BorderFactory.createEmptyBorder()); // 设置边框为空边框
		
		JButton btnConfirm1 = new JButton("");
		btnConfirm1.setOpaque(false);
		btnConfirm1.setBorderPainted(false);
		btnConfirm1.setContentAreaFilled(false);
		btnConfirm1.setBounds(105, 475, 169, 56);
		panel.add(btnConfirm1);
		
		JButton btnConfirm2 = new JButton("");
		
		btnConfirm2.setOpaque(false);
		btnConfirm2.setBorderPainted(false);
		btnConfirm2.setContentAreaFilled(false);
		btnConfirm2.setBounds(493, 475, 169, 56);
		panel.add(btnConfirm2);
		
		
		JLabel portraitP2 = new JLabel("");
		portraitP2.setIcon(new ImageIcon(personnalisation.class.getResource("/image/portrait/portrait_A21_right.png")));
		portraitP2.setBounds(493, 37, 203, 169);
		panel.add(portraitP2);
		
		JLabel portraitP1 = new JLabel("");
		portraitP1.setIcon(new ImageIcon(personnalisation.class.getResource("/image/portrait/portrait_A21_left.png")));
		portraitP1.setBounds(63, 23, 225, 192);
		panel.add(portraitP1);
		
		JComboBox comboBoxMetierP2 = new JComboBox();
		comboBoxMetierP2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				branch selectedValueP2 = (branch) comboBoxMetierP2.getSelectedItem();
                
                switch(selectedValueP2) {
                case A21:
                	portraitP2.setIcon(new ImageIcon(personnalisation.class.getResource("/image/portrait/portrait_A21_right.png")));//a changer
                	portraitP2.setBounds(493, 37, 203, 169);
                  break;
                case GI:
                	portraitP2.setIcon(new ImageIcon(personnalisation.class.getResource("/image/portrait/portrait_GI_right.png")));//a changer
                	portraitP2.setBounds(465, 37, 203, 169);
                  break;
                case GM:
                	portraitP2.setIcon(new ImageIcon(personnalisation.class.getResource("/image/portrait/portrait_GM_right.png")));//a changer
                	portraitP2.setBounds(465, 37, 203, 169);
                  break;
                case ISI:
                	portraitP2.setIcon(new ImageIcon(personnalisation.class.getResource("/image/portrait/portrait_ISI_right.png")));//a changer
                	portraitP2.setBounds(465, 37, 203, 169);
                  break;
                case MTE:
                	portraitP2.setIcon(new ImageIcon(personnalisation.class.getResource("/image/portrait/portrait_MTE_right.png")));//a changer
                	portraitP2.setBounds(465, 37, 203, 169);
                  break;
                case RT:
                	portraitP2.setIcon(new ImageIcon(personnalisation.class.getResource("/image/portrait/portrait_RT_right.png")));//a changer
                	portraitP2.setBounds(465, 37, 203, 169);
                  break;
                default:
                	portraitP2.setIcon(new ImageIcon(personnalisation.class.getResource("")));
                	portraitP2.setBounds(493, 37, 203, 169);
              }
			}
		});
		comboBoxMetierP2.setFont(new Font("Bauhaus 93", Font.ITALIC, 31));
		comboBoxMetierP2.setModel(new DefaultComboBoxModel(branch.values()));
		comboBoxMetierP2.setOpaque(false);
		comboBoxMetierP2.setBorder(BorderFactory.createEmptyBorder());
		comboBoxMetierP2.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                return new JButton() {
                    @Override
                    public int getWidth() {
                        return 0;
                    }
                };
            }
        });
		comboBoxMetierP2.setBounds(519, 376, 137, 47);
		panel.add(comboBoxMetierP2);
		
		JComboBox comboBoxMetierP1 = new JComboBox();
		//changer le portrait
		comboBoxMetierP1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				branch selectedValueP1 = (branch) comboBoxMetierP1.getSelectedItem();
                
                switch(selectedValueP1) {
                case A21:
                	portraitP1.setIcon(new ImageIcon(personnalisation.class.getResource("/image/portrait/portrait_A21_left.png")));//a changer
            		portraitP1.setBounds(63, 23, 225, 192);
                  break;
                case GI:
                	portraitP1.setIcon(new ImageIcon(personnalisation.class.getResource("/image/portrait/portrait_GI_left.png")));//a changer
            		portraitP1.setBounds(83, 23, 225, 192);
                  break;
                case GM:
                	portraitP1.setIcon(new ImageIcon(personnalisation.class.getResource("/image/portrait/portrait_GM_left.png")));//a changer
            		portraitP1.setBounds(83, 23, 225, 192);
                  break;
                case ISI:
                	portraitP1.setIcon(new ImageIcon(personnalisation.class.getResource("/image/portrait/portrait_ISI_left.png")));//a changer
            		portraitP1.setBounds(83, 23, 225, 192);
                  break;
                case MTE:
                	portraitP1.setIcon(new ImageIcon(personnalisation.class.getResource("/image/portrait/portrait_MTE_left.png")));//a changer
            		portraitP1.setBounds(83, 23, 225, 192);
                  break;
                case RT:
                	portraitP1.setIcon(new ImageIcon(personnalisation.class.getResource("/image/portrait/portrait_RT_left.png")));//a changer
            		portraitP1.setBounds(83, 23, 225, 192);
                  break;
                default:
                	portraitP1.setIcon(new ImageIcon(personnalisation.class.getResource("")));
            		portraitP1.setBounds(83, 23, 225, 192);
              }
			}
		});
		
		comboBoxMetierP1.setFont(new Font("Bauhaus 93", Font.ITALIC, 31));
		comboBoxMetierP1.setModel(new DefaultComboBoxModel(branch.values()));
		comboBoxMetierP1.setOpaque(false);
		comboBoxMetierP1.setBorder(BorderFactory.createEmptyBorder());
		
		
		//UIManager.put("ComboBox.background", Color.YELLOW); // 背景颜色
		//UIManager.put("ComboBox.selectionBackground", Color.GREEN); // 选中项背景颜色
		
		comboBoxMetierP1.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                return new JButton() {
                    @Override
                    public int getWidth() {
                        return 0;
                    }
                };
            }
        });
		
		comboBoxMetierP1.setBounds(120, 376, 131, 47);
		panel.add(comboBoxMetierP1);
		
	
		
		textFieldP2 = new JTextField();
		textFieldP2.setFont(new Font("Bauhaus 93", Font.ITALIC, 31));
		textFieldP2.setOpaque(false); // 设置文本字段为透明
		textFieldP2.setBorder(BorderFactory.createEmptyBorder()); // 设置边框为空边框
		textFieldP2.setBounds(530, 268, 154, 49);
		panel.add(textFieldP2);
		textFieldP2.setColumns(10);
		textFieldP1.setBounds(120, 268, 154, 49);
		panel.add(textFieldP1);
		textFieldP1.setColumns(10);
		
		//confirmer qu'il y a un nom et set variable
		btnConfirm1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String txt=textFieldP1.getText();
				if(txt.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Nom est vide!!!!" );
				}else {
					setNameP1(txt);
					setBranchP1((branch)comboBoxMetierP1.getSelectedItem());
					
					confirmedP1=true;
					JOptionPane.showMessageDialog(frame, "votre nom: "+nameP1+"\nvotre branche:"+getBranchP1());
					if(confirmedP1 && confirmedP2) {
						distributerPointsP1.distributer();
						frame.dispose();
					}
				}
				
			}
		});
		btnConfirm2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String txt=textFieldP2.getText();
				if(txt.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Nom est vide!!!!" );
				}else {
					setNameP2(txt);
					
					setBranchP2((branch)comboBoxMetierP2.getSelectedItem());
					confirmedP2=true;
					JOptionPane.showMessageDialog(frame, "votre nom: "+nameP2+"\nvotre branche:"+branchP2);
					if(confirmedP1&&confirmedP2) {
						distributerPointsP1.distributer();
						frame.dispose();
					}
				}
			}
		});
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(personnalisation.class.getResource("/image/attribute.png")));
		label.setBounds(0, 0, 800, 600);
		panel.add(label);
	}

	public static branch getBranchP1() {
		return branchP1;
	}

	public static void setBranchP1(branch branchP1) {
		personnalisation.branchP1 = branchP1;
	}

	
}
