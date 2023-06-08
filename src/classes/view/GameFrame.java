package classes.view;

import javax.swing.*;

import classes.controller.Config;
import classes.controller.GameUtil;
import classes.model.battlefields.District;
import classes.model.characters.fighters.students.Student;
import classes.model.characters.fighters.students.Student.Character;
import classes.model.characters.players.Player.Branch;
import classes.model.characters.players.Player.Team;
import gui.distributerPointsP1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameFrame extends JFrame{

	private ArrayList<District> districts;
	private static int fois=0;
	
	public GameFrame(ArrayList<District> districts) {
		this.districts = districts;
		this.setTitle("War Game");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);// 固定窗体
		// 窗体居中
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension size = new Dimension(Config.FRAME_WIDTH, Config.FRAME_HEIGHT);
		int width = toolkit.getScreenSize().width;
		int height = toolkit.getScreenSize().height;
		this.setBounds((int) (width - size.getWidth()) / 2, (int) (height - size.getHeight()) / 3,
				(int) size.getWidth(), (int) size.getHeight());

		// 初始化游戏内容
		this.init(districts);

	}

	private void init(ArrayList<District> districts) {
		CombatPanel combatPanel = new CombatPanel(districts,Branch.ISI, Branch.GM);
		fois++;
		this.add(combatPanel);
		this.setVisible(true);		
		
		
		
		JButton skipButton = new JButton("skip");
		skipButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				// 连接新窗口
				if(fois>2) {
					ScoreboardFrame scoreboardFrame = new ScoreboardFrame();
				}else {
					distributerPointsP1.distributer();
					
				}
				
			}
		});
		skipButton.setFont(new Font("Bauhaus 93", Font.ITALIC, 40));
		skipButton.setBounds(Config.FRAME_WIDTH/2 - 455/2, Config.FRAME_HEIGHT - 100, 450, 55);
		skipButton.setContentAreaFilled(false);
		skipButton.setBorderPainted(false);
		skipButton.setVisible(true);
		
		JLabel zoneBg = new JLabel();
		zoneBg.setIcon(new ImageIcon(GameUtil.createImage(Config.IMAGE_RESOURCES_PATH +"background/tagBg.png", 450, 55)));
		zoneBg.setBounds(Config.FRAME_WIDTH/2 - 450/2, Config.FRAME_HEIGHT - 100, 450, 55);
		zoneBg.setOpaque(false);
		zoneBg.setVisible(true);
		
		combatPanel.add(skipButton);
		combatPanel.add(zoneBg);
		GameUtil.task(5, ()->{
			combatPanel.repaint();			
		});
	}


	public static void startFrame() {
		String[] districtNames = { "Library", "Student office", "Administrative district", "Industrial halls",
		"Sports hall" };
		ArrayList<District> districts = new ArrayList<District>() {
			{
				add(new District(districtNames[0]));
				add(new District(districtNames[1]));
				add(new District(districtNames[2]));
				add(new District(districtNames[3]));
				add(new District(districtNames[4]));
			}
		};
		
		ArrayList<Student> students1 = new ArrayList<Student>() {
			{
				add(Student.createStudent(Character.Student,"3","001"));
				add(Student.createStudent(Character.Student,"3","002"));
				add(Student.createStudent(Character.Student,"3","003"));
				add(Student.createStudent(Character.Elite,"2","004"));
				add(Student.createStudent(Character.Gobi,"1","005"));

			}
		};
		ArrayList<Student> students2 = new ArrayList<Student>() {
			{
				add(Student.createStudent(Character.Student,"3","201"));
				add(Student.createStudent(Character.Student,"3","202"));
				add(Student.createStudent(Character.Elite,"3","203"));
				add(Student.createStudent(Character.Elite,"2","204"));
				add(Student.createStudent(Character.Gobi,"1","205"));

			}
		};
		
		ArrayList<Student> students3 = new ArrayList<Student>() {
			{
				add(Student.createStudent(Character.Student,"3","001"));
				add(Student.createStudent(Character.Student,"3","002"));
				add(Student.createStudent(Character.Elite,"3","003"));
				add(Student.createStudent(Character.Elite,"2","004"));
				add(Student.createStudent(Character.Gobi,"1","005"));

			}
		};
		ArrayList<Student> students4 = new ArrayList<Student>() {
			{
				add(Student.createStudent(Character.Student,"3","201"));
				add(Student.createStudent(Character.Student,"3","202"));
				add(Student.createStudent(Character.Elite,"3","203"));
				add(Student.createStudent(Character.Elite,"2","204"));
				add(Student.createStudent(Character.Gobi,"1","205"));

			}
		};
		
		ArrayList<Student> students5 = new ArrayList<Student>() {
			{
				add(Student.createStudent(Character.Elite,"2","004"));
				add(Student.createStudent(Character.Gobi,"1","005"));

			}
		};
		ArrayList<Student> students6 = new ArrayList<Student>() {
			{
				add(Student.createStudent(Character.Elite,"2","204"));
				add(Student.createStudent(Character.Gobi,"1","205"));

			}
		};
		
		students1.forEach(s->districts.get(0).addFighterTeam(s, Team.TEAM1));
		students2.forEach(s->districts.get(0).addFighterTeam(s, Team.TEAM2));
		
		students3.forEach(s->districts.get(1).addFighterTeam(s, Team.TEAM1));
		students4.forEach(s->districts.get(1).addFighterTeam(s, Team.TEAM2));
		
		students5.forEach(s->districts.get(2).addFighterTeam(s, Team.TEAM1));
		students6.forEach(s->districts.get(2).addFighterTeam(s, Team.TEAM2));
		
		students5.forEach(s->districts.get(3).addFighterTeam(s, Team.TEAM1));
		students6.forEach(s->districts.get(3).addFighterTeam(s, Team.TEAM2));
		
		students5.forEach(s->districts.get(4).addFighterTeam(s, Team.TEAM1));
		students6.forEach(s->districts.get(4).addFighterTeam(s, Team.TEAM2));
		
		new GameFrame(districts);
	}
}