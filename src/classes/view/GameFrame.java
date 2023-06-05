package classes.view;

import javax.swing.*;

import classes.controller.Config;
import classes.controller.GameUtil;
import classes.model.battlefields.District;
import classes.model.characters.fighters.students.Student;
import classes.model.characters.fighters.students.Student.Character;
import classes.model.characters.fighters.studentsSep.*;

import classes.model.characters.players.Player.Branch;
import classes.model.characters.players.Player.Team;
import gui.distributerPointsP1;
import gui.distributerPointsP2;

import java.awt.*;
import java.util.ArrayList;

public class GameFrame extends JFrame{

	public GameFrame(ArrayList<District> districts) {
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
		//ScoreboardPanel scoreboardPanel = new ScoreboardPanel(Branch.ISI, Branch.GM);
		//this.add(scoreboardPanel);
		this.add(combatPanel);
		this.setVisible(true);		
		
		GameUtil.task(5, ()->{
			combatPanel.repaint();
			//scoreboardPanel.repaint();
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
		
		ArrayList<Student> students1 = new ArrayList<Student>(); 
		for(classes.model.characters.fighters.studentsSep.Student stu : distributerPointsP1.getFightersBibli()){
			if(stu instanceof GobiMaster) {
				students1.add(Student.createStudent(Character.Gobi,stu.getFirstname(),stu.getLastName()));
			}else if(stu instanceof EliteStudent) {
				students1.add(Student.createStudent(Character.Elite,stu.getFirstname(),stu.getLastName()));
			}else {
				students1.add(Student.createStudent(Character.Student,stu.getFirstname(),stu.getLastName()));
			}
			
				}
		ArrayList<Student> students2 = new ArrayList<Student>() ;
		for(classes.model.characters.fighters.studentsSep.Student stu : distributerPointsP2.getFightersBibli()){
			if(stu instanceof GobiMaster) {
				students2.add(Student.createStudent(Character.Gobi,stu.getFirstname(),stu.getLastName()));
			}else if(stu instanceof EliteStudent) {
				students2.add(Student.createStudent(Character.Elite,stu.getFirstname(),stu.getLastName()));
			}else {
				students2.add(Student.createStudent(Character.Student,stu.getFirstname(),stu.getLastName()));
			}
			
				}
		
		ArrayList<Student> students3 = new ArrayList<Student>();
		for(classes.model.characters.fighters.studentsSep.Student stu : distributerPointsP1.getFightersBureauEtu()){
			if(stu instanceof GobiMaster) {
				students3.add(Student.createStudent(Character.Gobi,stu.getFirstname(),stu.getLastName()));
			}else if(stu instanceof EliteStudent) {
				students3.add(Student.createStudent(Character.Elite,stu.getFirstname(),stu.getLastName()));
			}else {
				students3.add(Student.createStudent(Character.Student,stu.getFirstname(),stu.getLastName()));
			}
			
				}
		ArrayList<Student> students4 = new ArrayList<Student>();
		for(classes.model.characters.fighters.studentsSep.Student stu : distributerPointsP2.getFightersBureauEtu()){
			if(stu instanceof GobiMaster) {
				students4.add(Student.createStudent(Character.Gobi,stu.getFirstname(),stu.getLastName()));
			}else if(stu instanceof EliteStudent) {
				students4.add(Student.createStudent(Character.Elite,stu.getFirstname(),stu.getLastName()));
			}else {
				students4.add(Student.createStudent(Character.Student,stu.getFirstname(),stu.getLastName()));
			}
			
				}
		
		/*studentsP1Lib.forEach(s->districts.get(0).addFighterTeam(s, Team.TEAM1));
		studentsP2Lib.forEach(s->districts.get(0).addFighterTeam(s, Team.TEAM2));
		
		studentsP1Bur.forEach(s->districts.get(1).addFighterTeam(s, Team.TEAM1));
		studentsP2Bur.forEach(s->districts.get(1).addFighterTeam(s, Team.TEAM2));*/
		
		
		new GameFrame(districts);
	}
}