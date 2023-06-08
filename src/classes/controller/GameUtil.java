package classes.controller;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

import classes.model.animation.Animation.Direction;
import classes.model.characters.players.Player.Branch;
import classes.model.myInterface.ITimer;

public class GameUtil {

	// 获得Image 路径 src/...
	public static Image createImage(String path) {
		return new ImageIcon(path).getImage();
	}

	// 按比例获得Image
	public static Image createImage(String path, int width, int height) {
		return new ImageIcon(path).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
	}

	// 定时器任务
	public static void task(long period, ITimer t) {
		java.util.Timer timer = new Timer();
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				// 当结束开关打开时，清除所有定时器
				if (Config.IS_TIMER_STOP) {
					timer.cancel();
					return;
				}
				t.run();
			}
		};
		timer.schedule(timerTask, 0, period);
	}
	
	// 水平翻转
	public static ImageIcon flipImageHorizontally(ImageIcon imageIcon) {
		Image originalImage = imageIcon.getImage();
		int w = originalImage.getWidth(null);
		int h = originalImage.getHeight(null);
		BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D graphics2d = img.createGraphics();
		graphics2d.drawImage(originalImage, w, 0, 0, h, 0, 0, w, h, null);
		graphics2d.dispose();
		return new ImageIcon(img);
	}
	
	// 获得玩家Image
	public static Image createPlayerImage(Branch branch, int width, int height, Direction direction) {
		String path = Config.IMAGE_RESOURCES_PATH + "portrait/";
		switch(branch) {
		case ISI:
			path=path+"portrait_ISI.png";
			break;
		case A2I:
			path=path+"portrait_A2I.png";
			break;
		case GI:
			path=path+"portrait_GI.png";
			break;
		case GM:
			path=path+"portrait_GM.png";
			break;
		case MTE:
			path=path+"portrait_MTE.png";
			break;
		case RT:
			path=path+"portrait_RT.png";
			break;
		default:
			path = null;
		}
		
		Image image = createImage(path, width, height);
		if(direction == Direction.Left) {
			image = flipImageHorizontally(new ImageIcon(image)).getImage();
		}
		return image;
	}
}
