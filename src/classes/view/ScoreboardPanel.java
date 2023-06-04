package classes.view;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.JPanel;

import classes.controller.Config;
import classes.model.animation.Animation;
import classes.model.animation.Animation.Action;
import classes.model.animation.Animation.Direction;
import classes.model.battlefields.District;
import classes.model.characters.players.Player.Branch;
import classes.controller.GameUtil;

public class ScoreboardPanel extends JPanel {

	// 缓存元素
	private Image image;
	// 玩家
	private JLabel winnerLabel;
	private JLabel loserLabel;
	private final static int IMAGE_WIDTH = 120;
	private final static int IMAGE_HEIGHT = 120;
	private final static int ADJUSTMENT = 24;
	// 胜利标签
	private JLabel victoryLabel;
	private ImageIcon victoryImageIcon;
	private final static int VICTORY_WIDTH = 300;
	private final static int VICTORY_HEIGHT = 225;
	// 背景颜色
	private final static int BACKGROUND_COLOR = 0xF3DEBA;
	private final static int STAGE_COLOR = 0x675D50;
	private final static int STAGE_HEIGHT = Config.FRAME_HEIGHT * 2 / 3;
	// 动画
	private Animation winnerWalking;
	private Animation loserWalking;
	private Animation winnerKicking;
	private Animation loserDying;

	private final static int DELAY_TIME = 10;

	public ScoreboardPanel(Branch winner, Branch loser) {
		setLayout(null);

		winnerLabel = new JLabel();
		Image winnerImage = GameUtil.createPlayerImage(winner, IMAGE_WIDTH, IMAGE_HEIGHT, Direction.Right);
		winnerLabel.setIcon(new ImageIcon(winnerImage));
		winnerLabel.setBounds(IMAGE_WIDTH, STAGE_HEIGHT - IMAGE_HEIGHT + ADJUSTMENT, IMAGE_WIDTH, IMAGE_HEIGHT);
		winnerLabel.setOpaque(false);
		this.add(winnerLabel);

		loserLabel = new JLabel();
		Image loserImage = GameUtil.createPlayerImage(loser, IMAGE_WIDTH, IMAGE_HEIGHT, Direction.Left);
		loserLabel.setIcon(new ImageIcon(loserImage));
		loserLabel.setBounds(Config.FRAME_WIDTH - IMAGE_WIDTH * 2, STAGE_HEIGHT - IMAGE_HEIGHT + ADJUSTMENT,
				IMAGE_WIDTH, IMAGE_HEIGHT);
		loserLabel.setOpaque(false);
		this.add(loserLabel);

		winnerWalking = Animation.createAnimation(winner, Action.Walking);
		loserWalking = Animation.createAnimation(loser, Action.Walking);
		winnerKicking = Animation.createAnimation(winner, Action.Kicking);
		loserDying = Animation.createAnimation(loser, Action.Dying);

		victoryLabel = new JLabel();
		Image victoryImage = GameUtil.createImage(Config.IMAGE_RESOURCES_PATH + "background/victory.png", VICTORY_WIDTH,
				VICTORY_HEIGHT);
		victoryImageIcon = new ImageIcon(victoryImage);
		victoryLabel.setIcon(createTransparentIcon(victoryImageIcon, 0));
		victoryLabel.setBounds(Config.FRAME_WIDTH / 2 - VICTORY_WIDTH + IMAGE_WIDTH,
				STAGE_HEIGHT - IMAGE_HEIGHT - VICTORY_HEIGHT, VICTORY_WIDTH, VICTORY_HEIGHT);
		victoryLabel.setOpaque(false);
		this.add(victoryLabel);

		Timer actionTimer = actionTimer();
		actionTimer.start();
	}

	private void drawBufferedImage() {
		image = createImage(this.getWidth(), this.getHeight());
		Graphics g = image.getGraphics();
		// 绘制背景
		g.setColor(new Color(BACKGROUND_COLOR));
		g.fillRect(0, 0, Config.FRAME_WIDTH, STAGE_HEIGHT);

		g.setColor(new Color(STAGE_COLOR));
		g.fillRect(0, STAGE_HEIGHT, Config.FRAME_WIDTH, Config.FRAME_HEIGHT - STAGE_HEIGHT);
	}

	public void paint(Graphics g) {
		super.paint(g);
		drawBufferedImage();
		g.drawImage(image, 0, 0, this);
		super.paintComponents(g);
	}

	private Timer actionTimer() {
		return new Timer(DELAY_TIME, new ActionListener() {

			private int cnt = 0;
			private final int kickFrame = 11;
			private final int dyingFrame = 14;
			
			private float alpha = 0;
			// 阶段标志
			private boolean isCollised = false;
			private boolean isAppeared = false;

			@Override
			public void actionPerformed(ActionEvent e) {
				Point loserPos = loserLabel.getLocation();
				Point winnerPos = winnerLabel.getLocation();

				// 计算标签的边界
				Rectangle loserBounds = new Rectangle(loserPos.x, loserPos.y, IMAGE_WIDTH - 55, IMAGE_HEIGHT);
				Rectangle winnerBounds = new Rectangle(winnerPos.x, winnerPos.y, IMAGE_WIDTH - 55, IMAGE_HEIGHT);

				if (!isCollised) {
					loserWalking.drawNextFrame(loserLabel, loserPos.x - 4, loserPos.y, IMAGE_WIDTH, IMAGE_HEIGHT,
							Direction.Left);
					winnerWalking.drawNextFrame(winnerLabel, winnerPos.x + 4, winnerPos.y, IMAGE_WIDTH, IMAGE_HEIGHT,
							Direction.Right);
					if (loserBounds.intersects(winnerBounds)) {
						isCollised = true;
						System.out.println("Collision occurred!");
					}
				}

				if (isCollised && !isAppeared) {
					if (cnt < kickFrame)
						winnerKicking.drawNextFrame(winnerLabel, winnerPos.x, winnerPos.y, IMAGE_WIDTH, IMAGE_HEIGHT,
								Direction.Right);
					if (cnt < dyingFrame)
						loserDying.drawNextFrame(loserLabel, loserPos.x, loserPos.y, IMAGE_WIDTH, IMAGE_HEIGHT,
								Direction.Left);
					cnt++;
					if (cnt > dyingFrame) {
						isAppeared = true;
						System.out.println("Appearing!");
					}
				}

				if (isAppeared) {
					alpha = 0.02f + 1.5f * alpha;
					if (alpha >= 1.0f) {
						alpha = 1.0f;
						((Timer) e.getSource()).stop();
						System.out.println("finished");
					}
					victoryLabel.setIcon(createTransparentIcon(victoryImageIcon, alpha));
				}
			}
		});
	}
	
	private static Icon createTransparentIcon(Icon icon, float alpha) {
		int width = icon.getIconWidth();
		int height = icon.getIconHeight();
		// 创建 BufferedImage，并设置为透明
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		// 获取 BufferedImage 的 Graphics2D 对象
		Graphics2D g2d = bufferedImage.createGraphics();
		// 设置透明度
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		// 绘制图像到 BufferedImage
		icon.paintIcon(null, g2d, 0, 0);
		g2d.dispose();
		return new ImageIcon(bufferedImage);
	}
}
