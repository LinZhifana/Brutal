package gui;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicComboBoxUI;

import classes.model.characters.fighters.studentsSep.Student;
import classes.model.characters.fighters.studentsSep.GobiMaster;
import classes.model.characters.fighters.strategies.Attack;
import classes.model.characters.fighters.strategies.Defense;
import classes.model.characters.fighters.strategies.Random;
import classes.model.characters.fighters.studentsSep.EliteStudent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

public class distributerPointsP2 {

	private JFrame frame;
	private JTextField textFieldNom;

	private static final ArrayList<Student> fightersBibli = new ArrayList<Student>();
	private static final ArrayList<Student> fightersBureauEtu = new ArrayList<Student>();
	private static final ArrayList<Student> fightersAdmin = new ArrayList<Student>();
	private static final ArrayList<Student> fightersHalles = new ArrayList<Student>();
	private static final ArrayList<Student> fightersSportive = new ArrayList<Student>();
	private static final ArrayList<Student> reservists = new ArrayList<Student>();
	
	private JTextField textFieldPH;
	private JTextField textFieldInitiative;
	private JTextField textFieldForce;
	private JTextField textFieldDexterite;
	private JTextField textFieldConstitution;
	private JTextField textFieldResistance;
	
	private JButton btnElite0;
	private JButton btnElite1;
	private JButton btnElite2;
	private JButton btnElite3;
	private JButton btnStudent0;
	private JButton btnStudent1;
	private JButton btnStudent2;
	private JButton btnStudent3;
	private JButton btnStudent4;
	private JButton btnStudent5;
	private JButton btnStudent6;
	private JButton btnStudent7;
	private JButton btnStudent8;
	private JButton btnStudent9;
	private JButton btnStudent10;
	private JButton btnStudent11;
	private JButton btnStudent12;
	private JButton btnStudent13;
	private JButton btnStudent14;
	private ArrayList<JButton> ButtonTodoG,ButtonTodoE,ButtonTodoS,ButtonDone;
	
	private JCheckBox chckbxRester;
	private int creditRest;
	

	

	public int getCreditRest() {
		return creditRest;
	}

	public void setCreditRest(int creditRest) {
		this.creditRest = creditRest;
	}

	public static ArrayList<Student> getFightersBibli() {
		return fightersBibli;
	}

	public static ArrayList<Student> getFightersBureauEtu() {
		return fightersBureauEtu;
	}

	public ArrayList<Student> getFightersAdmin() {
		return fightersAdmin;
	}

	public ArrayList<Student> getFightersHalles() {
		return fightersHalles;
	}

	public ArrayList<Student> getFightersSportive() {
		return fightersSportive;
	}

	public ArrayList<Student> getReservists() {
		return reservists;
	}

	/**
	 * Launch the application.
	 */
	public static void distributer() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					distributerPointsP2 window = new distributerPointsP2();
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
	public distributerPointsP2() {
		
		this.ButtonTodoG = new ArrayList<>();
		this.ButtonTodoE = new ArrayList<>();
		this.ButtonTodoS = new ArrayList<>();
		this.ButtonDone = new ArrayList<>();
		creditRest=400;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("C'est du brutal! "+personnalisation.getNameP2()+"-Veuillez entrer le nom avant de décider quel rôle il est");
		frame.setBounds(100, 100, 814, 637);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 800, 600);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		
		
		textFieldResistance = new JTextField();
		textFieldResistance.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldResistance.setBounds(574, 395, 120, 28);
		textFieldResistance.setFont(new Font("Bauhaus 93", Font.ITALIC, 30));
		textFieldResistance.setOpaque(false); // 设置文本字段为透明
		textFieldResistance.setBorder(BorderFactory.createEmptyBorder()); // 设置边框为空边框
		panel.add(textFieldResistance);
		textFieldResistance.setColumns(10);
		
		textFieldConstitution = new JTextField();
		textFieldConstitution.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldConstitution.setBounds(574, 343, 120, 28);
		textFieldConstitution.setFont(new Font("Bauhaus 93", Font.ITALIC, 30));
		textFieldConstitution.setOpaque(false); // 设置文本字段为透明
		textFieldConstitution.setBorder(BorderFactory.createEmptyBorder()); // 设置边框为空边框
		panel.add(textFieldConstitution);
		textFieldConstitution.setColumns(10);
		
		textFieldDexterite = new JTextField();
		textFieldDexterite.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldDexterite.setBounds(574, 293, 120, 28);
		textFieldDexterite.setFont(new Font("Bauhaus 93", Font.ITALIC, 30));
		textFieldDexterite.setOpaque(false); // 设置文本字段为透明
		textFieldDexterite.setBorder(BorderFactory.createEmptyBorder()); // 设置边框为空边框
		panel.add(textFieldDexterite);
		textFieldDexterite.setColumns(10);
		
		textFieldForce = new JTextField();
		textFieldForce.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldForce.setBounds(574, 245, 120, 28);
		textFieldForce.setFont(new Font("Bauhaus 93", Font.ITALIC, 30));
		textFieldForce.setOpaque(false); // 设置文本字段为透明
		textFieldForce.setBorder(BorderFactory.createEmptyBorder()); // 设置边框为空边框
		panel.add(textFieldForce);
		textFieldForce.setColumns(10);
		
		textFieldInitiative = new JTextField();
		textFieldInitiative.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldInitiative.setBounds(574, 192, 120, 28);
		textFieldInitiative.setFont(new Font("Bauhaus 93", Font.ITALIC, 30));
		textFieldInitiative.setOpaque(false); // 设置文本字段为透明
		textFieldInitiative.setBorder(BorderFactory.createEmptyBorder()); // 设置边框为空边框
		panel.add(textFieldInitiative);
		textFieldInitiative.setColumns(10);
		
		textFieldPH = new JTextField();
		textFieldPH.setEnabled(false);
		textFieldPH.setText("30");
		textFieldPH.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldPH.setBounds(574, 137, 120, 34);
		textFieldPH.setFont(new Font("Bauhaus 93", Font.ITALIC, 30));
		textFieldPH.setOpaque(false); // 设置文本字段为透明
		textFieldPH.setBorder(BorderFactory.createEmptyBorder()); // 设置边框为空边框
		panel.add(textFieldPH);
		textFieldPH.setColumns(10);
		
		JLabel lblCredit = new JLabel("");
		lblCredit.setText(""+this.getCreditRest());
		lblCredit.setHorizontalAlignment(SwingConstants.CENTER);
		lblCredit.setFont(new Font("Bauhaus 93", Font.BOLD, 64));
		lblCredit.setBounds(202, 41, 132, 108);
		panel.add(lblCredit);
		
		textFieldNom = new JTextField();
		textFieldNom.setBounds(426, 52, 127, 42);
		textFieldNom.setFont(new Font("Bauhaus 93", Font.ITALIC, 40));
		textFieldNom.setOpaque(false); // 设置文本字段为透明
		textFieldNom.setBorder(BorderFactory.createEmptyBorder()); // 设置边框为空边框
		panel.add(textFieldNom);
		textFieldNom.setColumns(10);
		
		textFieldConstitution.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                updateValue();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                updateValue();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateValue();
            }
            
            private void updateValue() {
                
                int PH = 0;
                if (textFieldConstitution.getText().matches("\\d+")) {
		            int con = Integer.parseInt(textFieldConstitution.getText());
		            PH = 30+con;
		            textFieldPH.setText(""+PH);
                }else if(textFieldConstitution.getText().isEmpty()) {
                	textFieldPH.setText("30");
                }else {
                	JOptionPane.showMessageDialog(frame, "Veuillez saisir le numéro pour constitution!" );
                }
		            
               
            }
        });
        
		
		JLabel labelPortrait = new JLabel("");
		labelPortrait.setBounds(583, 30, 93, 80);
		panel.add(labelPortrait);
		switch(personnalisation.getBranchP1()) {
        case A21:
        	labelPortrait.setIcon(new ImageIcon(personnalisation.class.getResource("/portrait_small/portrait_A21_small.png")));//a changer
        	labelPortrait.setBounds(590, 30, 93, 80);
          break;
        case GI:
        	labelPortrait.setIcon(new ImageIcon(personnalisation.class.getResource("/portrait_small/portrait_GI_small.png")));//a changer
        	
          break;
        case GM:
        	labelPortrait.setIcon(new ImageIcon(personnalisation.class.getResource("/portrait_small/portrait_GM_small.png")));//a changer
        	
          break;
        case ISI:
        	labelPortrait.setIcon(new ImageIcon(personnalisation.class.getResource("/portrait_small/portrait_ISI_small.png")));//a changer
        	
          break;
        case MTE:
        	labelPortrait.setIcon(new ImageIcon(personnalisation.class.getResource("/portrait_small/portrait_MTE_small.png")));//a changer
        	
          break;
        case RT:
        	labelPortrait.setIcon(new ImageIcon(personnalisation.class.getResource("/portrait_small/portrait_RT_small.png")));//a changer
        	
          break;
        default:
        	labelPortrait.setIcon(new ImageIcon(personnalisation.class.getResource("")));
        	
      }
		
		
		JButton btnGobi = new JButton("");
		
		btnGobi.setIcon(new ImageIcon(distributerPointsP1.class.getResource("/image/fighters/gobimaster.png")));
		btnGobi.setOpaque(false);
		btnGobi.setContentAreaFilled(false);
		btnGobi.setBorderPainted(false);
		btnGobi.setBounds(44, 29, 130, 142);
		panel.add(btnGobi);
		ButtonTodoG.add(btnGobi);
		
		
		btnElite0 = new JButton("");
		btnElite0.setIcon(new ImageIcon(distributerPointsP1.class.getResource("/image/fighters/elitestudent.png")));
		btnElite0.setOpaque(false);
		btnElite0.setBorderPainted(false);
		btnElite0.setContentAreaFilled(false);
		btnElite0.setBounds(25, 190, 87, 102);
		panel.add(btnElite0);
		ButtonTodoE.add(btnElite0);
		
		btnElite1 = new JButton("");
		btnElite1.setIcon(new ImageIcon(distributerPointsP1.class.getResource("/image/fighters/elitestudent.png")));
		btnElite1.setOpaque(false);
		btnElite1.setContentAreaFilled(false);
		btnElite1.setBorderPainted(false);
		btnElite1.setBounds(94, 189, 90, 104);
		panel.add(btnElite1);
		ButtonTodoE.add(btnElite1);
		
		btnElite2 = new JButton("");
		btnElite2.setIcon(new ImageIcon(distributerPointsP1.class.getResource("/image/fighters/elitestudent.png")));
		btnElite2.setOpaque(false);
		btnElite2.setContentAreaFilled(false);
		btnElite2.setBorderPainted(false);
		btnElite2.setBounds(165, 190, 88, 104);
		panel.add(btnElite2);
		ButtonTodoE.add(btnElite2);
		
		btnElite3 = new JButton("");
		btnElite3.setIcon(new ImageIcon(distributerPointsP1.class.getResource("/image/fighters/elitestudent.png")));
		btnElite3.setOpaque(false);
		btnElite3.setContentAreaFilled(false);
		btnElite3.setBorderPainted(false);
		btnElite3.setBounds(234, 190, 93, 104);
		panel.add(btnElite3);
		ButtonTodoE.add(btnElite3);
		
		btnStudent0 = new JButton("");
		btnStudent0.setIcon(new ImageIcon(distributerPointsP1.class.getResource("/image/fighters/student.png")));
		btnStudent0.setOpaque(false);
		btnStudent0.setContentAreaFilled(false);
		btnStudent0.setBorderPainted(false);
		btnStudent0.setBounds(46, 326, 54, 68);
		panel.add(btnStudent0);
		ButtonTodoS.add(btnStudent0);
		
		btnStudent1 = new JButton("");
		btnStudent1.setIcon(new ImageIcon(distributerPointsP1.class.getResource("/image/fighters/student.png")));
		btnStudent1.setOpaque(false);
		btnStudent1.setContentAreaFilled(false);
		btnStudent1.setBorderPainted(false);
		btnStudent1.setBounds(101, 326, 54, 68);
		panel.add(btnStudent1);
		ButtonTodoS.add(btnStudent1);
		
		btnStudent2 = new JButton("");
		btnStudent2.setIcon(new ImageIcon(distributerPointsP1.class.getResource("/image/fighters/student.png")));
		btnStudent2.setOpaque(false);
		btnStudent2.setContentAreaFilled(false);
		btnStudent2.setBorderPainted(false);
		btnStudent2.setBounds(157, 326, 54, 68);
		panel.add(btnStudent2);
		ButtonTodoS.add(btnStudent2);
		
		btnStudent3 = new JButton("");
		btnStudent3.setIcon(new ImageIcon(distributerPointsP1.class.getResource("/image/fighters/student.png")));
		btnStudent3.setOpaque(false);
		btnStudent3.setContentAreaFilled(false);
		btnStudent3.setBorderPainted(false);
		btnStudent3.setBounds(213, 326, 54, 68);
		panel.add(btnStudent3);
		ButtonTodoS.add(btnStudent3);
		
		btnStudent4 = new JButton("");
		btnStudent4.setIcon(new ImageIcon(distributerPointsP1.class.getResource("/image/fighters/student.png")));
		btnStudent4.setOpaque(false);
		btnStudent4.setContentAreaFilled(false);
		btnStudent4.setBorderPainted(false);
		btnStudent4.setBounds(269, 326, 54, 68);
		panel.add(btnStudent4);
		ButtonTodoS.add(btnStudent4);
		
		btnStudent5 = new JButton("");
		btnStudent5.setIcon(new ImageIcon(distributerPointsP1.class.getResource("/image/fighters/student.png")));
		btnStudent5.setOpaque(false);
		btnStudent5.setContentAreaFilled(false);
		btnStudent5.setBorderPainted(false);
		btnStudent5.setBounds(46, 400, 54, 68);
		panel.add(btnStudent5);
		ButtonTodoS.add(btnStudent5);
		
		btnStudent6 = new JButton("");
		btnStudent6.setIcon(new ImageIcon(distributerPointsP1.class.getResource("/image/fighters/student.png")));
		btnStudent6.setOpaque(false);
		btnStudent6.setContentAreaFilled(false);
		btnStudent6.setBorderPainted(false);
		btnStudent6.setBounds(101, 400, 54, 68);
		panel.add(btnStudent6);
		ButtonTodoS.add(btnStudent6);
		
		btnStudent7 = new JButton("");
		btnStudent7.setIcon(new ImageIcon(distributerPointsP1.class.getResource("/image/fighters/student.png")));
		btnStudent7.setOpaque(false);
		btnStudent7.setContentAreaFilled(false);
		btnStudent7.setBorderPainted(false);
		btnStudent7.setBounds(157, 401, 54, 67);
		panel.add(btnStudent7);
		ButtonTodoS.add(btnStudent7);
		
		btnStudent8 = new JButton("");
		btnStudent8.setIcon(new ImageIcon(distributerPointsP1.class.getResource("/image/fighters/student.png")));
		btnStudent8.setOpaque(false);
		btnStudent8.setContentAreaFilled(false);
		btnStudent8.setBorderPainted(false);
		btnStudent8.setBounds(213, 400, 54, 68);
		panel.add(btnStudent8);
		ButtonTodoS.add(btnStudent8);
		
		btnStudent9 = new JButton("");
		btnStudent9.setIcon(new ImageIcon(distributerPointsP1.class.getResource("/image/fighters/student.png")));
		btnStudent9.setOpaque(false);
		btnStudent9.setContentAreaFilled(false);
		btnStudent9.setBorderPainted(false);
		btnStudent9.setBounds(269, 401, 54, 67);
		panel.add(btnStudent9);
		ButtonTodoS.add(btnStudent9);
		
		btnStudent10 = new JButton("");
		btnStudent10.setIcon(new ImageIcon(distributerPointsP1.class.getResource("/image/fighters/student.png")));
		btnStudent10.setOpaque(false);
		btnStudent10.setContentAreaFilled(false);
		btnStudent10.setBorderPainted(false);
		btnStudent10.setBounds(46, 470, 54, 68);
		panel.add(btnStudent10);
		ButtonTodoS.add(btnStudent10);
		
		btnStudent11 = new JButton("");
		btnStudent11.setIcon(new ImageIcon(distributerPointsP1.class.getResource("/image/fighters/student.png")));
		btnStudent11.setOpaque(false);
		btnStudent11.setContentAreaFilled(false);
		btnStudent11.setBorderPainted(false);
		btnStudent11.setBounds(101, 470, 54, 68);
		panel.add(btnStudent11);
		ButtonTodoS.add(btnStudent11);
		
		btnStudent12 = new JButton("");
		btnStudent12.setIcon(new ImageIcon(distributerPointsP1.class.getResource("/image/fighters/student.png")));
		btnStudent12.setOpaque(false);
		btnStudent12.setContentAreaFilled(false);
		btnStudent12.setBorderPainted(false);
		btnStudent12.setBounds(157, 470, 54, 68);
		panel.add(btnStudent12);
		ButtonTodoS.add(btnStudent12);
		
		btnStudent13 = new JButton("");
		btnStudent13.setIcon(new ImageIcon(distributerPointsP1.class.getResource("/image/fighters/student.png")));
		btnStudent13.setOpaque(false);
		btnStudent13.setContentAreaFilled(false);
		btnStudent13.setBorderPainted(false);
		btnStudent13.setBounds(213, 470, 54, 68);
		panel.add(btnStudent13);
		ButtonTodoS.add(btnStudent13);
		
		btnStudent14 = new JButton("");
		btnStudent14.setIcon(new ImageIcon(distributerPointsP1.class.getResource("/image/fighters/student.png")));
		btnStudent14.setOpaque(false);
		btnStudent14.setContentAreaFilled(false);
		btnStudent14.setBorderPainted(false);
		btnStudent14.setBounds(269, 470, 54, 68);
		panel.add(btnStudent14);
		ButtonTodoS.add(btnStudent14);
		
		
		JCheckBox chckbxReserviste = new JCheckBox("");
		chckbxReserviste.setOpaque(false);
		chckbxReserviste.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxReserviste.setIcon(new TransparentCheckBoxIcon(28));
		chckbxReserviste.setBounds(561, 532, 30, 42);
		panel.add(chckbxReserviste);
		
		chckbxRester = new JCheckBox("");
		chckbxRester.setOpaque(false);
		chckbxRester.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxRester.setIcon(new TransparentCheckBoxIcon(28));
		chckbxRester.setBounds(753, 532, 30, 42);
		panel.add(chckbxRester);
		
		JComboBox comboBoxZone = new JComboBox();
		comboBoxZone.setFont(new Font("Bauhaus 93", Font.ITALIC, 13));
		comboBoxZone.setModel(new DefaultComboBoxModel(districtZone.values()));
		comboBoxZone.setOpaque(false);
		comboBoxZone.setBorder(BorderFactory.createEmptyBorder());
		comboBoxZone.setUI(new BasicComboBoxUI() {
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
		comboBoxZone.setBounds(574, 440, 102, 28);
		panel.add(comboBoxZone);
		
		JComboBox comboBoxStrateige = new JComboBox();
		comboBoxStrateige.setFont(new Font("Bauhaus 93", Font.ITALIC, 23));
		comboBoxStrateige.setModel(new DefaultComboBoxModel(studentStrategie.values()));
		comboBoxStrateige.setOpaque(false);
		comboBoxStrateige.setBorder(BorderFactory.createEmptyBorder());
		comboBoxStrateige.setUI(new BasicComboBoxUI() {
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
		comboBoxStrateige.setBounds(574, 491, 102, 28);
		panel.add(comboBoxStrateige);
		
		
		
		
		btnGobi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String NomGot=textFieldNom.getText();
				if(NomGot.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Veuillez entrer le prénom!!!");
				}else {
					String[] parts = NomGot.split(" ");
					GobiMaster gobi;
					if(parts.length > 1) {
						gobi=new GobiMaster(parts[0],parts[1]);
					}else {
						gobi=new GobiMaster(parts[0],"");
					}
					disableOtherButton(btnGobi);
					
					setTextField(gobi.getInitiative(),gobi.getStrength(),gobi.getDexterity(),gobi.getConstitution(),gobi.getResistance());
					
					
					//creer un button Random temp et sa action
					JButton btnRandom = new JButton("");
					btnRandom.setBounds(690, 41, 87, 28);
					btnRandom.setOpaque(false);
					btnRandom.setContentAreaFilled(false);
					btnRandom.setBorderPainted(false);
					panel.add(btnRandom);
					btnRandom.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							setTextField((int)(Math.random() * 9)+2,(int)(Math.random() * 9)+2,(int)(Math.random() * 9)+2,(int)(Math.random() * 21)+10,(int)(Math.random() * 9)+2);
							comboBoxZone.setSelectedIndex((int)(Math.random() * 5));
							comboBoxStrateige.setSelectedIndex((int)(Math.random() * 3));
						}
					});
					
					//creer un button confirm temp et sa action
					JButton btnConfirm = new JButton("");
					btnConfirm.setBounds(686, 78, 93, 33);
					btnConfirm.setOpaque(false);
					btnConfirm.setContentAreaFilled(false);
					btnConfirm.setBorderPainted(false);
					btnConfirm.setEnabled(true);
					
					panel.add(btnConfirm);
					btnConfirm.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							int initiative;
				            int force;
				            int dexterite;
				            int constitution;
				    		int resistance;
				    		if(!chckbxReserviste.isSelected()&& fightersBibli.size()+fightersBureauEtu.size()+fightersAdmin.size()+fightersHalles.size()+fightersSportive.size()>14) {
				    			JOptionPane.showMessageDialog(frame, "Fighters plain!!Veuillez se mettre à reserviste!!" );
				    		}else if(chckbxReserviste.isSelected()&& reservists.size()>4){
				    			JOptionPane.showMessageDialog(frame, "Reserviste plain!!Veuillez se mettre à fighters!!" );
				    		}else {
				    			if (textFieldInitiative.getText().matches("\\d+") && textFieldForce.getText().matches("\\d+") && textFieldDexterite.getText().matches("\\d+")&&textFieldConstitution.getText().matches("\\d+")&&textFieldResistance.getText().matches("\\d+")) {
					            initiative = Integer.parseInt(textFieldInitiative.getText());
					            force = Integer.parseInt(textFieldForce.getText());
					            dexterite = Integer.parseInt(textFieldDexterite.getText());
					            constitution = Integer.parseInt(textFieldConstitution.getText());
					    		resistance = Integer.parseInt(textFieldResistance.getText());
					    		if(initiative>1&&initiative<11&&force>1&&force<11&&dexterite>1&&dexterite<11&&
					    				constitution>9&&constitution<31&&resistance>1&&resistance<11 && 
					    				checkRange(initiative-2,force-2,dexterite-2,constitution-10,resistance-2)) {
					    			gobi.setInitiative(initiative);
					    			gobi.setStrength(force);
					    			gobi.setDexterity(dexterite);
					    			gobi.setConstitution(constitution);
					    			gobi.setResistance(resistance);
					    			setCreditRest(getCreditRest()+18-initiative-force-dexterite-constitution-resistance);
					    			lblCredit.setText(""+getCreditRest());
					    			if(chckbxReserviste.isSelected()) {
					    				reservists.add(gobi);
					    			}else {
					    				if(comboBoxStrateige.getSelectedItem()== studentStrategie.Attack) {
					    					gobi.setStrategy(new Attack());
					    				}else if(comboBoxStrateige.getSelectedItem()== studentStrategie.Defence) {
					    					gobi.setStrategy(new Defense());
					    				}else if(comboBoxStrateige.getSelectedItem()== studentStrategie.Random) {
					    					gobi.setStrategy(new Random());
					    				}
					    				districtZone zone=(districtZone)comboBoxZone.getSelectedItem();
					    				switch(zone) {
					    				case Library:
					    					fightersBibli.add(gobi);
					    					break;
					    				case Student_office:
					    					fightersBureauEtu.add(gobi);
					    					break;
					    				case Administrative:
					    					fightersAdmin.add(gobi);
					    					break;
					    				case Industrial_halls:
					    					fightersHalles.add(gobi);
					    					break;
					    				case Sports_hall:
					    					fightersSportive.add(gobi);
					    					break;
					    				}
					    			}
					    			ButtonDone.add(btnGobi);
					    			ButtonTodoG.remove(btnGobi);
					    			btnGobi.setEnabled(false);
					    			ableTodoButton();
					    			textFieldNom.setText(null);
					    			setTextField(0,0,0,0,0);
					    			panel.remove(btnConfirm);
					    			panel.remove(btnRandom);// 从 JFrame 中移除 JButton
					    			panel.repaint(); 
					    			checkOver();
					    		}else {
					    			JOptionPane.showMessageDialog(frame, "Erreur de plage ou pas de crédit" );
					    		}
					    		
					    		
					        } else {
					        	JOptionPane.showMessageDialog(frame, "Veuillez saisir le numéro!" );
					        }
				    		}
							
							
							
						}
					});
					
					
				}
			}
		});
		
		

	
		
		for(JButton btnNewButton : ButtonTodoE) {
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String NomGot=textFieldNom.getText();
					if(NomGot.isEmpty()) {
						JOptionPane.showMessageDialog(frame, "Veuillez entrer le prénom!!!");
					}else {
						String[] parts = NomGot.split(" ");
						EliteStudent elite;
						if(parts.length > 1) {
							elite=new EliteStudent(parts[0],parts[1]);
						}else {
							elite=new EliteStudent(parts[0],"");
						}
						disableOtherButton(btnNewButton);
						
						setTextField(elite.getInitiative(),elite.getStrength(),elite.getDexterity(),elite.getConstitution(),elite.getResistance());
						
						
						//creer un button Random temp et sa action
						JButton btnRandom = new JButton("");
						btnRandom.setBounds(690, 41, 87, 28);
						btnRandom.setOpaque(false);
						btnRandom.setContentAreaFilled(false);
						btnRandom.setBorderPainted(false);
						panel.add(btnRandom);
						btnRandom.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								
								setTextField((int)(Math.random() * 10)+1,(int)(Math.random() * 10)+1,(int)(Math.random() * 10)+1,(int)(Math.random() * 26)+5,(int)(Math.random() * 10)+1);
								comboBoxZone.setSelectedIndex((int)(Math.random() * 5));
								comboBoxStrateige.setSelectedIndex((int)(Math.random() * 3));
							}
						});
						
						//creer un button confirm temp et sa action
						JButton btnConfirm = new JButton("");
						btnConfirm.setBounds(686, 78, 93, 33);
						btnConfirm.setOpaque(false);
						btnConfirm.setContentAreaFilled(false);
						btnConfirm.setBorderPainted(false);
						
						btnConfirm.setEnabled(true);
						panel.add(btnConfirm);
						btnConfirm.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								int initiative;
					            int force;
					            int dexterite;
					            int constitution;
					    		int resistance;
					    		
					    		if(!chckbxReserviste.isSelected()&& fightersBibli.size()+fightersBureauEtu.size()+fightersAdmin.size()+fightersHalles.size()+fightersSportive.size()>14) {
					    			JOptionPane.showMessageDialog(frame, "Fighters plain!!Veuillez se mettre à reserviste!!" );
					    		}else if(chckbxReserviste.isSelected()&& reservists.size()>4){
					    			JOptionPane.showMessageDialog(frame, "Reserviste plain!!Veuillez se mettre à fighters!!" );
					    		}else {
					    			if (textFieldInitiative.getText().matches("\\d+") && textFieldForce.getText().matches("\\d+") && textFieldDexterite.getText().matches("\\d+")&&textFieldConstitution.getText().matches("\\d+")&&textFieldResistance.getText().matches("\\d+")) {
						            initiative = Integer.parseInt(textFieldInitiative.getText());
						            force = Integer.parseInt(textFieldForce.getText());
						            dexterite = Integer.parseInt(textFieldDexterite.getText());
						            constitution = Integer.parseInt(textFieldConstitution.getText());
						    		resistance = Integer.parseInt(textFieldResistance.getText());
						    		if(initiative>0&&initiative<11&&force>0&&force<11&&dexterite>0&&dexterite<11&&
						    				constitution>4&&constitution<31&&resistance>0&&resistance<11 &&
						    				checkRange(initiative-1,force-1,dexterite-1,constitution-5,resistance-1)) {
						    			elite.setInitiative(initiative);
						    			elite.setStrength(force);
						    			elite.setDexterity(dexterite);
						    			elite.setConstitution(constitution);
						    			elite.setResistance(resistance);
						    			setCreditRest(getCreditRest()-initiative-force-dexterite-constitution-resistance+9);
						    			lblCredit.setText(""+getCreditRest());
						    			if(chckbxReserviste.isSelected()) {
						    				reservists.add(elite);
						    			}else {
						    				
						    				if(comboBoxStrateige.getSelectedItem()== studentStrategie.Attack) {
						    					elite.setStrategy(new Attack());
						    				}else if(comboBoxStrateige.getSelectedItem()== studentStrategie.Defence) {
						    					elite.setStrategy(new Defense());
						    				}else if(comboBoxStrateige.getSelectedItem()== studentStrategie.Random) {
						    					elite.setStrategy(new Random());
						    				}
						    				districtZone zone=(districtZone)comboBoxZone.getSelectedItem();
						    				switch(zone) {
						    				case Library:
						    					fightersBibli.add(elite);
						    					break;
						    				case Student_office:
						    					fightersBureauEtu.add(elite);
						    					break;
						    				case Administrative:
						    					fightersAdmin.add(elite);
						    					break;
						    				case Industrial_halls:
						    					fightersHalles.add(elite);
						    					break;
						    				case Sports_hall:
						    					fightersSportive.add(elite);
						    					break;
						    				}
						    			}
						    			ButtonDone.add(btnNewButton);
						    			ButtonTodoE.remove(btnNewButton);
						    			btnNewButton.setEnabled(false);
						    			ableTodoButton();
						    			textFieldNom.setText(null);
						    			setTextField(0,0,0,0,0);
						    			panel.remove(btnConfirm);
						    			panel.remove(btnRandom);// 从 JFrame 中移除 JButton
						    			panel.repaint(); 
						    			checkOver();
						    		}else {
						    			JOptionPane.showMessageDialog(frame, "Erreur de plage ou pas de crédit" );
						    		}
						    		
						    		
						        } else {
						        	JOptionPane.showMessageDialog(frame, "Veuillez saisir le numéro!" );
						        }
					    		}
								
								
								
							}
						});
						
						
					}
				}
			});
		}
		
		
		for(JButton btnNewButton : ButtonTodoS) {
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String NomGot=textFieldNom.getText();
					if(NomGot.isEmpty()) {
						JOptionPane.showMessageDialog(frame, "Veuillez entrer le prénom!!!");
					}else {
						String[] parts = NomGot.split(" ");
						Student etudiant;
						if(parts.length > 1) {
							etudiant=new Student(parts[0],parts[1]);
						}else {
							etudiant=new Student(parts[0],"");
						}
						disableOtherButton(btnNewButton);
						
						setTextField(etudiant.getInitiative(),etudiant.getStrength(),etudiant.getDexterity(),etudiant.getConstitution(),etudiant.getResistance());
						
						
						//creer un button Random temp et sa action
						JButton btnRandom = new JButton("");
						btnRandom.setBounds(690, 41, 87, 28);
						btnRandom.setOpaque(false);
						btnRandom.setContentAreaFilled(false);
						btnRandom.setBorderPainted(false);
						panel.add(btnRandom);
						btnRandom.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								
								setTextField((int)(Math.random() * 11),(int)(Math.random() * 11),(int)(Math.random() * 11),(int)(Math.random() * 31),(int)(Math.random() * 11));
								comboBoxZone.setSelectedIndex((int)(Math.random() * 5));
								comboBoxStrateige.setSelectedIndex((int)(Math.random() * 3));
							}
						});
						
						//creer un button confirm temp et sa action
						JButton btnConfirm = new JButton("");
						btnConfirm.setBounds(686, 78, 93, 33);
						btnConfirm.setOpaque(false);
						btnConfirm.setContentAreaFilled(false);
						btnConfirm.setBorderPainted(false);
						
						btnConfirm.setEnabled(true);
						panel.add(btnConfirm);
						btnConfirm.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								int initiative;
					            int force;
					            int dexterite;
					            int constitution;
					    		int resistance;
					    		
					    		if(!chckbxReserviste.isSelected()&& fightersBibli.size()+fightersBureauEtu.size()+fightersAdmin.size()+fightersHalles.size()+fightersSportive.size()>14) {
					    			JOptionPane.showMessageDialog(frame, "Fighters plain!!Veuillez se mettre à reserviste!!" );
					    		}else if(chckbxReserviste.isSelected()&& reservists.size()>4){
					    			JOptionPane.showMessageDialog(frame, "Reserviste plain!!Veuillez se mettre à fighters!!" );
					    		}else {
					    			if (textFieldInitiative.getText().matches("\\d+") && textFieldForce.getText().matches("\\d+") && textFieldDexterite.getText().matches("\\d+")&&textFieldConstitution.getText().matches("\\d+")&&textFieldResistance.getText().matches("\\d+")) {
						            initiative = Integer.parseInt(textFieldInitiative.getText());
						            force = Integer.parseInt(textFieldForce.getText());
						            dexterite = Integer.parseInt(textFieldDexterite.getText());
						            constitution = Integer.parseInt(textFieldConstitution.getText());
						    		resistance = Integer.parseInt(textFieldResistance.getText());
						    		if(initiative>-1&&initiative<11&&force>-1&&force<11&&dexterite>-1&&dexterite<11&&
						    				constitution>-1&&constitution<31&&resistance>-1&&resistance<11 &&
						    				checkRange(initiative,force,dexterite,constitution,resistance)) {
						    			etudiant.setInitiative(initiative);
						    			etudiant.setStrength(force);
						    			etudiant.setDexterity(dexterite);
						    			etudiant.setConstitution(constitution);
						    			etudiant.setResistance(resistance);
						    			setCreditRest(getCreditRest()-initiative-force-dexterite-constitution-resistance);
						    			lblCredit.setText(""+getCreditRest());
						    			if(chckbxReserviste.isSelected()) {
						    				reservists.add(etudiant);
						    			}else {
						    				if(comboBoxStrateige.getSelectedItem()== studentStrategie.Attack) {
						    					etudiant.setStrategy(new Attack());
						    				}else if(comboBoxStrateige.getSelectedItem()== studentStrategie.Defence) {
						    					etudiant.setStrategy(new Defense());
						    				}else if(comboBoxStrateige.getSelectedItem()== studentStrategie.Random) {
						    					etudiant.setStrategy(new Random());
						    				}
						    				districtZone zone=(districtZone)comboBoxZone.getSelectedItem();
						    				switch(zone) {
						    				case Library:
						    					fightersBibli.add(etudiant);
						    					break;
						    				case Student_office:
						    					fightersBureauEtu.add(etudiant);
						    					break;
						    				case Administrative:
						    					fightersAdmin.add(etudiant);
						    					break;
						    				case Industrial_halls:
						    					fightersHalles.add(etudiant);
						    					break;
						    				case Sports_hall:
						    					fightersSportive.add(etudiant);
						    					break;
						    				}
						    			}
						    			ButtonDone.add(btnNewButton);
						    			ButtonTodoS.remove(btnNewButton);
						    			btnNewButton.setEnabled(false);
						    			ableTodoButton();
						    			textFieldNom.setText(null);
						    			setTextField(0,0,0,0,0);
						    			panel.remove(btnConfirm);
						    			panel.remove(btnRandom);// 从 JFrame 中移除 JButton
						    			panel.repaint(); 
						    			checkOver();
						    		}else {
						    			JOptionPane.showMessageDialog(frame, "Erreur de plage ou pas de crédit" );
						    		}
						    		
						    		
						        } else {
						        	JOptionPane.showMessageDialog(frame, "Veuillez saisir le numéro!" );
						        }
					    		}
								
								
								
							}
						});
						
						
					}
				}
			});
		}
		
		
		
		
		
		
		JLabel labelBackground = new JLabel("");
		labelBackground.setIcon(new ImageIcon(distributerPointsP1.class.getResource("/image/Distributer.png")));
		labelBackground.setBounds(0, 0, 800, 600);
		panel.add(labelBackground);
		
		
		
		
		
		
	
		
		
		
		
	}
	
	//pour disable tous les autre button
	private boolean disableOtherButton(JButton button) {
		if(ButtonTodoG.size()>0) {
			for (int i = 0; i < ButtonTodoG.size(); i++) {
			JButton otherButton = ButtonTodoG.get(i);
		    if(otherButton.equals(button)) {
		    	continue;
		    }else {
		    	otherButton.setEnabled(false);
		    }
		}
			
		}
			if(ButtonTodoE.size()>0) {
				for (int i = 0; i < ButtonTodoE.size(); i++) {
				JButton otherButton = ButtonTodoE.get(i);
			    if(otherButton.equals(button)) {
			    	continue;
			    }else {
			    	otherButton.setEnabled(false);
			    }
			}
			}
				if(ButtonTodoS.size()>0) {
					for (int i = 0; i < ButtonTodoS.size(); i++) {
					JButton otherButton = ButtonTodoS.get(i);
				    if(otherButton.equals(button)) {
				    	continue;
				    }else {
				    	otherButton.setEnabled(false);
				    }
				}
				}
		
		return true;
		
			
		
	}
	
	//pour activer tous les button
	private boolean ableTodoButton() {
		if(ButtonTodoG.size()>0) {
			for (int i = 0; i < ButtonTodoG.size(); i++) {
			JButton Button = ButtonTodoG.get(i);
		    	Button.setEnabled(true);
		    
		}
		}
			if(ButtonTodoE.size()>0) {
				for (int i = 0; i < ButtonTodoE.size(); i++) {
				JButton Button = ButtonTodoE.get(i);
			    	Button.setEnabled(true);
			    
			}
			}
				if(ButtonTodoS.size()>0) {
					for (int i = 0; i < ButtonTodoS.size(); i++) {
					JButton Button = ButtonTodoS.get(i);
				    	Button.setEnabled(true);
				    
				}
				}
		
		return true;
		
		
		
	}
	//==========================================================>19 original 
	private void checkOver() {
		if(distributerPointsP2.fightersBibli.size()+distributerPointsP2.fightersAdmin.size()+
				distributerPointsP2.fightersBureauEtu.size()+distributerPointsP2.fightersHalles.size()+
				distributerPointsP2.fightersSportive.size()+distributerPointsP2.reservists.size()>0) {
			fightersBibli.sort(Comparator.comparing(Student::getInitiative));
	        Collections.reverse(fightersBibli);
	        fightersAdmin.sort(Comparator.comparing(Student::getInitiative));
	        Collections.reverse(fightersAdmin);
	        fightersBureauEtu.sort(Comparator.comparing(Student::getInitiative));
	        Collections.reverse(fightersBureauEtu);
	        fightersHalles.sort(Comparator.comparing(Student::getInitiative));
	        Collections.reverse(fightersHalles);
	        fightersSportive.sort(Comparator.comparing(Student::getInitiative));
	        Collections.reverse(fightersSportive);
			//classes.view.GameFrame.startFrame();
	        mapDistricts.startMap();
			frame.dispose();
		}
	}
	
	
	private boolean checkRange(int initiative,int force,int dexterite,int constitution,int resistance) {
		return getCreditRest()>=initiative+force+dexterite+constitution+resistance;
	} 
	
	
	private void setTextField(int in,int fo,int de,int co,int re) {
		
		textFieldInitiative.setText("" + in);
		textFieldForce.setText("" + fo);
		textFieldDexterite.setText("" + de);
		textFieldConstitution.setText("" + co);
		textFieldResistance.setText("" + re);
		
	}
	
}
