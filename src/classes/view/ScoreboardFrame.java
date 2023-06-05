package classes.view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import classes.controller.Config;
import classes.controller.GameUtil;
import classes.model.characters.players.Player.Branch;

public class ScoreboardFrame extends JFrame{

	public ScoreboardFrame() {
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
		this.init();
	}
	
	private void init() {
		ScoreboardPanel scoreboardPanel = new ScoreboardPanel(Branch.ISI, Branch.GM);
		this.add(scoreboardPanel);
		this.setVisible(true);
		GameUtil.task(5, ()->{
			scoreboardPanel.repaint();			
		});
	}
	
	public static void main(String[] args) {
		new ScoreboardFrame();
	}
}
