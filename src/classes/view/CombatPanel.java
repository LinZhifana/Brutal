package classes.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import classes.controller.Config;
import classes.controller.GameUtil;
import classes.model.animation.Animation;
import classes.model.animation.Animation.Action;
import classes.model.animation.Animation.Direction;
import classes.model.battlefields.District;
import classes.model.battlefields.District.DistrictName;
import classes.model.characters.fighters.strategies.Attack;
import classes.model.characters.fighters.strategies.Defense;
import classes.model.characters.fighters.strategies.Strategy;
import classes.model.characters.fighters.students.Student;
import classes.model.characters.fighters.students.Student.Character;
import classes.model.characters.players.Player.Branch;
import classes.model.characters.players.Player.Team;
import classes.model.fight.FightMessages;

public class CombatPanel extends JPanel {
	// 缓存元素
	private Image image;

	// 导航栏
	private JLabel zoneJLabel;
	private JButton leftButton;
	private JButton rightButton;

	// 玩家图像
	private JLabel player1;
	private JLabel player2;
	
	// 士兵
	private ArrayList<JLabel> fighters1 = new ArrayList<>();
	private ArrayList<JLabel> fighters2 = new ArrayList<>();
	
	// 士兵动作消息
	private FightMessages fightMessages; 
	private ArrayList<FightMessages> fightMessagesSet;
	// 背景
	private Image backgroundImage;
	private int index = 0;
	
	// 导航栏尺寸
	private final static int NAV_LABEL_WIDTH = 450;
	private final static int NAV_HEIGHT = 55;
	private final static int NAV_PADDING_TOP = Config.FRAME_WIDTH / 20;
	private final static int NAV_COLOR = 0xEDC6B1;
	// 玩家头像大小
	private final static int PORTRAIT_SIZE = 100;
	private final static int PORTRAIT_PADDING_TOP = 
			Config.FRAME_WIDTH / 40 + NAV_PADDING_TOP + NAV_HEIGHT;
	private final static int PORTRAIT_COLOR = 0xE8AB63;
	
	// 士兵尺寸
	private final static int FIGHTER_HEIGHT = 114;
	private final static int FIGHTER_WIDTH = 161;
	private final static int FIGHTER_TEAM_PADDING_TOP = 
			PORTRAIT_PADDING_TOP + PORTRAIT_SIZE + Config.FRAME_WIDTH / 80;
	
	// 延时
	private final static int DELAY_TIME = 100;

	// 初始动画
	private HashMap<Character, HashMap<Action, Animation>> animations = new HashMap<Character, HashMap<Action, Animation>>() {
		{
			put(Character.Gobi, new HashMap<Action, Animation>() {
				{
					put(Action.Attacking, Animation.createAnimation(Character.Gobi, Action.Attacking));
					put(Action.Taunt, Animation.createAnimation(Character.Gobi, Action.Taunt));
					put(Action.Hurt, Animation.createAnimation(Character.Gobi, Action.Hurt));
					put(Action.Dying, Animation.createAnimation(Character.Gobi, Action.Dying));
					put(Action.Idle, Animation.createAnimation(Character.Gobi, Action.Idle));
					put(Action.Walking, Animation.createAnimation(Character.Gobi, Action.Walking));
				}
			});

			put(Character.Elite, new HashMap<Action, Animation>() {
				{
					put(Action.Attacking, Animation.createAnimation(Character.Elite, Action.Attacking));
					put(Action.Taunt, Animation.createAnimation(Character.Elite, Action.Taunt));
					put(Action.Hurt, Animation.createAnimation(Character.Elite, Action.Hurt));
					put(Action.Dying, Animation.createAnimation(Character.Elite, Action.Dying));
					put(Action.Idle, Animation.createAnimation(Character.Elite, Action.Idle));
					put(Action.Walking, Animation.createAnimation(Character.Elite, Action.Walking));
				}
			});

			put(Character.Student, new HashMap<Action, Animation>() {
				{
					put(Action.Attacking, Animation.createAnimation(Character.Student, Action.Attacking));
					put(Action.Taunt, Animation.createAnimation(Character.Student, Action.Taunt));
					put(Action.Hurt, Animation.createAnimation(Character.Student, Action.Hurt));
					put(Action.Dying, Animation.createAnimation(Character.Student, Action.Dying));
					put(Action.Idle, Animation.createAnimation(Character.Student, Action.Idle));
					put(Action.Walking, Animation.createAnimation(Character.Student, Action.Walking));
				}
			});
		}
	};
	
	public CombatPanel(ArrayList<District> districts, Branch player1Branch, Branch player2Branch) {
		setLayout(null);
		
		backgroundImage = createBackgroundImage(DistrictName.Library);
		
		JLabel zoneBg = new JLabel();
		zoneBg.setIcon(new ImageIcon(GameUtil.createImage(Config.IMAGE_RESOURCES_PATH +"background/tagBg.png", NAV_LABEL_WIDTH, NAV_HEIGHT)));
		zoneBg.setBounds(Config.FRAME_WIDTH / 2 - NAV_LABEL_WIDTH / 2, 
				NAV_PADDING_TOP, NAV_LABEL_WIDTH,
				NAV_HEIGHT);
		zoneBg.setOpaque(false);
		zoneBg.setVisible(true);
		// 导航栏
		zoneJLabel = new JLabel(districts.get(0).getName(), JLabel.CENTER);
		zoneJLabel.setBounds(Config.FRAME_WIDTH / 2 - NAV_LABEL_WIDTH / 2, 
				NAV_PADDING_TOP, NAV_LABEL_WIDTH,
				NAV_HEIGHT);
		//zoneJLabel.setBackground(new Color(NAV_COLOR));
		zoneJLabel.setFont(new Font("Bauhaus 93", Font.ITALIC, 40));
		zoneJLabel.setOpaque(false);
		zoneJLabel.setVisible(true);
		this.add(zoneJLabel);
		this.add(zoneBg);
		
		Image navButtonImage = GameUtil.createImage(
				"src/resources/images/background/navButton.png",
				NAV_HEIGHT, NAV_HEIGHT);
		// 左		
		leftButton = new JButton();
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				index--;
				if(index < 0) {
					index = DistrictName.values().length - 1;
				}
				
				District district = districts.get(index);
				fightMessages = fightMessagesSet.get(index);
				// 更换背景
				zoneJLabel.setText(district.getName());
				backgroundImage = createBackgroundImage(
						DistrictName.values()[index]);
				System.out.println("Left : " + index);
				
				// 更换士兵
				removeAllFighters();
				addFighters(district.getTeam(Team.TEAM1), Team.TEAM1);
				addFighters(district.getTeam(Team.TEAM2), Team.TEAM2);
				
				fight();
			}
		});
		leftButton.setIcon(new ImageIcon(navButtonImage));
		leftButton.setBounds(Config.FRAME_WIDTH / 2 - NAV_LABEL_WIDTH / 2 - NAV_HEIGHT - 20,
				NAV_PADDING_TOP, NAV_HEIGHT, NAV_HEIGHT);
		leftButton.setContentAreaFilled(false);
		leftButton.setBorderPainted(false);
		leftButton.setFocusPainted(false);
		leftButton.setVisible(true);
		this.add(leftButton);
		// 右
		rightButton = new JButton();
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				index++;
				index = index % DistrictName.values().length;
				
				District district = districts.get(index);
				fightMessages = fightMessagesSet.get(index);
				// 更换背景
				zoneJLabel.setText(district.getName());
				backgroundImage = createBackgroundImage(
						DistrictName.values()[index]);
				System.out.println("Right : " + index);
				
				// 更换士兵
				removeAllFighters();
				addFighters(district.getTeam(Team.TEAM1), Team.TEAM1);
				addFighters(district.getTeam(Team.TEAM2), Team.TEAM2);
				
				
				
				fight();
			}
		});
		rightButton.setBounds(Config.FRAME_WIDTH / 2 + NAV_LABEL_WIDTH / 2 + 20, 
				NAV_PADDING_TOP, NAV_HEIGHT, NAV_HEIGHT);
		rightButton.setIcon(GameUtil.flipImageHorizontally(
				new ImageIcon(navButtonImage)) ) ;
		rightButton.setContentAreaFilled(false);
		rightButton.setBorderPainted(false);
		rightButton.setFocusPainted(false);
		rightButton.setVisible(true);
		this.add(rightButton);
		// 玩家头像
		
		JLabel player1Bg = new JLabel();
		player1Bg.setIcon(new ImageIcon(GameUtil.createImage(Config.IMAGE_RESOURCES_PATH +"background/playerBg.png", PORTRAIT_SIZE, PORTRAIT_SIZE)));
		player1Bg.setBounds(Config.FRAME_WIDTH / 5 - PORTRAIT_SIZE, PORTRAIT_PADDING_TOP, 
				PORTRAIT_SIZE, PORTRAIT_SIZE);
		player1Bg.setOpaque(false);
		player1Bg.setVisible(true);
		
		player1 = new JLabel();
		player1.setBounds(Config.FRAME_WIDTH / 5 - PORTRAIT_SIZE, PORTRAIT_PADDING_TOP, 
				PORTRAIT_SIZE, PORTRAIT_SIZE);
		player1.setIcon(new ImageIcon(
				GameUtil.createPlayerImage(player1Branch,
						PORTRAIT_SIZE, PORTRAIT_SIZE, Direction.Right))
				);
		//player1.setBackground(new Color(PORTRAIT_COLOR));
		player1.setOpaque(false);
		player1.setVisible(true);
		this.add(player1);
		this.add(player1Bg);
		
		JLabel player2Bg = new JLabel();
		player2Bg.setIcon(new ImageIcon(GameUtil.createImage(Config.IMAGE_RESOURCES_PATH +"background/playerBg.png", PORTRAIT_SIZE, PORTRAIT_SIZE)));
		player2Bg.setBounds(Config.FRAME_WIDTH / 5 * 4, PORTRAIT_PADDING_TOP, 
				PORTRAIT_SIZE, PORTRAIT_SIZE);
		player2Bg.setOpaque(false);
		player2Bg.setVisible(true);
		
		player2 = new JLabel();
		player2.setBounds(Config.FRAME_WIDTH / 5 * 4, PORTRAIT_PADDING_TOP, 
				PORTRAIT_SIZE, PORTRAIT_SIZE);
		player2.setIcon(new ImageIcon(
				GameUtil.createPlayerImage(player2Branch,
						PORTRAIT_SIZE, PORTRAIT_SIZE, Direction.Left))
				);
		//player2.setBackground(new Color(PORTRAIT_COLOR));
		player2.setOpaque(false);
		player2.setVisible(true);
		this.add(player2);
		this.add(player2Bg);
		
		
		
		
		// 添加士兵图标
		addFighters(districts.get(0).getTeam(Team.TEAM1), Team.TEAM1);
		addFighters(districts.get(0).getTeam(Team.TEAM2), Team.TEAM2);		
		// 战斗
		initFighterMessage();
		fightMessages = fightMessagesSet.get(0);
		fight();

		this.setVisible(true);	
		
	}
	
	private void initFighterMessage() {
		this.fightMessagesSet = new ArrayList<FightMessages>() {{
				FightMessages tmp1 = new FightMessages();
				tmp1.addFightMessages(100, 200, Character.Student, Character.Student, new Attack(), false);
//				tmp1.addFightMessages(204, 200, Character.Gobi, Character.Student, new Defense(), false);
//				tmp1.addFightMessages(103, 200, Character.Elite, Character.Student, new Attack(), true);
//				tmp1.addFightMessages(203, 103, Character.Elite, Character.Elite, new Attack(), true);
				
				FightMessages tmp2 = new FightMessages();
				tmp2.addFightMessages(100, 201, Character.Student, Character.Elite, new Attack(), true);
//				tmp2.addFightMessages(101, 102, Character.Elite, Character.Student, new Defense(), false);
//				tmp2.addFightMessages(103, 203, Character.Student, Character.Student, new Attack(), false);
				
				FightMessages tmp3 = new FightMessages();
				tmp3.addFightMessages(100, 201, Character.Student, Character.Elite, new Attack(), true);
				tmp3.addFightMessages(101, 102, Character.Elite, Character.Student, new Defense(), false);
				tmp3.addFightMessages(103, 203, Character.Student, Character.Student, new Attack(), false);
				
				FightMessages tmp4 = new FightMessages();
				tmp4.addFightMessages(100, 201, Character.Student, Character.Elite, new Attack(), true);
				tmp4.addFightMessages(101, 102, Character.Elite, Character.Student, new Defense(), false);
				tmp4.addFightMessages(103, 203, Character.Student, Character.Student, new Attack(), false);
				
				FightMessages tmp5 = new FightMessages();
				tmp5.addFightMessages(100, 201, Character.Student, Character.Elite, new Attack(), true);
				tmp5.addFightMessages(101, 102, Character.Elite, Character.Student, new Defense(), false);
				tmp5.addFightMessages(103, 203, Character.Student, Character.Student, new Attack(), false);
				
				add(tmp1);add(tmp2);add(tmp3);add(tmp4);add(tmp5);
		}};
		
		

	}

	private void drawBufferedImage() {
		image = createImage(this.getWidth(), this.getHeight());
		Graphics g = image.getGraphics();
		g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		drawBufferedImage();
		g.drawImage(image, 0, 0, this);
		super.paintComponents(g);
	}
	
	private Image createBackgroundImage(DistrictName districtName) {
		String path = Config.IMAGE_RESOURCES_PATH + "background/";
		switch(districtName) {
		case Library:
			path = path + "library.jpg";
			break;
		case StudentOffice:
			path = path + "bde.jpg";
			break;
		case Admin:
			path = path + "admin.jpg";
			break;
		case Industry:
			path = path + "factory.jpg";
			break;
		case Sport:
			path = path + "gym.jpg";
			break;
		default:
			path = null;
		}
		return GameUtil.createImage(path);
	}
	
	private JLabel createFighterLabel(Student student, Direction direction) {
		String pathString = Config.IMAGE_RESOURCES_PATH + "fighters/";
		switch (student.getCharacter()) {
		case Gobi:
			pathString = pathString + "gobi.png";
			break;
		case Elite:
			pathString = pathString + "elite.png";
			break;
		case Student:
			pathString = pathString + "student.png";
			break;
		default:
			break;
		}
		Image image = GameUtil.createImage(pathString, FIGHTER_WIDTH, FIGHTER_HEIGHT);
		ImageIcon imageIcon = direction == Direction.Left
				? GameUtil.flipImageHorizontally(new ImageIcon(image))
				: new ImageIcon(image);
		JLabel lbl = new JLabel();
		lbl.setIcon(imageIcon);
		lbl.setOpaque(false);
		return lbl;
	}
	
	private void addFighters(ArrayList<Student> students, Team team) {
		int cnt = 0;
		Direction direction = Team.TEAM1 == team? Direction.Left : Direction.Right;
		int x = Direction.Right == direction? - 20 : Config.FRAME_WIDTH - FIGHTER_WIDTH;
		int y = FIGHTER_TEAM_PADDING_TOP;
		for(Student student:students) {
			if(cnt % 5 == 0) {
				x = direction == Direction.Right? x + 100:x - 100;
				y = FIGHTER_TEAM_PADDING_TOP;
			}else{
				y += 105;
			}
			cnt++;
			JLabel tmpJLabel = createFighterLabel(student, direction);
			tmpJLabel.setBounds(x, y, FIGHTER_WIDTH, FIGHTER_WIDTH);
			switch (team) {
			case TEAM1:
				fighters1.add(tmpJLabel);
				break;
			case TEAM2:
				fighters2.add(tmpJLabel);
				break;
			default:
				break;
			}
			this.add(tmpJLabel);
		}
	}
	
	private void removeAllFighters() {
		fighters1.forEach(f->this.remove(f));
		fighters2.forEach(f->this.remove(f));
	}
	
	private ActionListener trigger = new ActionListener() {
	
		@Override
		public void actionPerformed(ActionEvent e) {
			
			fightMessages.next();
			fight();
		}
	};
	
	private void fight() {
		System.out.println(fightMessages);
		if(fightMessages.hasNext()) {
			JLabel actor = fightMessages.getActorTeam() == Team.TEAM1 
					? fighters1.get(fightMessages.getActorIndex())
					: fighters2.get(fightMessages.getActorIndex());

			JLabel recipient = fightMessages.getRecipientTeam() == Team.TEAM1
					? fighters1.get(fightMessages.getRecipientIndex())
					: fighters2.get(fightMessages.getRecipientIndex());
			
			Character actorCharacter = fightMessages.getActorCharacter();
			Character recipientCharacter = fightMessages.getRecipientCharacter();
			Strategy strategy = fightMessages.getStrategie();
			Boolean isDead = fightMessages.isDead();

			Timer actionTimer = actionTimer(actor, recipient, actorCharacter, recipientCharacter, strategy, isDead);
			actionTimer.start();
			System.out.println(actor);
			System.out.println(actionTimer);
			actionTimer = null;
		}else {
			System.out.println("All fights completed");
		}
	}
	
	private Timer actionTimer(JLabel actor, JLabel recipient, Character actorCharacter, Character recipientCharacter,
			Strategy strategy, Boolean isDead) {
		return new Timer(DELAY_TIME, new ActionListener() {
			int cnt = 0;

			Animation walkAnimation = animations.get(actorCharacter).get(Action.Walking);
			Animation attackAnimation = animations.get(actorCharacter).get(Action.Attacking);
			Animation hurtAnimation = animations.get(recipientCharacter).get(Action.Hurt);
			Animation healAnimation = animations.get(actorCharacter).get(Action.Taunt);
			Animation rIdleAnimation = animations.get(recipientCharacter).get(Action.Idle);
			Animation aIdleAnimation = animations.get(actorCharacter).get(Action.Idle);
			Animation dyingAnimation = animations.get(recipientCharacter).get(Action.Dying);
			
			Point aPoint = actor.getLocation();
			Point rPoint = recipient.getLocation();

			int targetX = aPoint.x < Config.FRAME_WIDTH / 2 ? rPoint.x - FIGHTER_WIDTH / 3 * 2 : rPoint.x + FIGHTER_WIDTH / 3 * 2;
			int targetY = rPoint.y + 25;
			// 出发和目的坐标
			
			Point source = actor.getLocation();
			Point target = new Point(targetX, targetY);
			// 方向
			Direction aDirection = aPoint.x < Config.FRAME_WIDTH / 2 ? Direction.Right : Direction.Left;
			Direction rDirection = aPoint.x == rPoint.x ? aDirection
					: (aDirection.equals(Direction.Right) ? Direction.Left : Direction.Right);

			int speedX = 8;
			int speedY = 8;

			Boolean isArrived = false;
			Boolean isBack = false;
			int attackFrame = 11;
			int hurtStartFrame = 3;
			int healFrame = 17;
			int dyingFrame = 14;

			public void actionPerformed(ActionEvent e) {				
				// 走到目的地
				if (!isArrived) {
					if(!moveRectangleToTarget(target, aPoint, speedX, speedY))
						walkAnimation.drawNextFrame(actor, aPoint.x, aPoint.y, FIGHTER_WIDTH, FIGHTER_HEIGHT, aDirection);
					else {
						isArrived = true;
						walkAnimation.setIndex(0);
						System.out.println("arrive");
					}
				}

				if (isArrived && !isBack) {
					// 攻击策略
					if (strategy instanceof Attack) {
						if (cnt < attackFrame) {
							attackAnimation.drawNextFrame(actor, aPoint.x, aPoint.y, FIGHTER_WIDTH, FIGHTER_HEIGHT,
									aDirection);
						}
						if (!isDead && cnt > hurtStartFrame && cnt <= hurtStartFrame + attackFrame) {
							hurtAnimation.drawNextFrame(recipient, rPoint.x, rPoint.y, FIGHTER_WIDTH, FIGHTER_HEIGHT,
									rDirection);
						} else if (isDead && cnt > hurtStartFrame && cnt <= dyingFrame + attackFrame) {
							dyingAnimation.drawNextFrame(recipient, rPoint.x, rPoint.y, FIGHTER_WIDTH, FIGHTER_HEIGHT,
									rDirection);
						} else {
							rIdleAnimation.drawNextFrame(recipient, rPoint.x, rPoint.y, FIGHTER_WIDTH, FIGHTER_HEIGHT,
									rDirection);
						}
					// 防御策略
					} else if (strategy instanceof Defense) {
						// 方向互换
						healAnimation.drawNextFrame(actor, aPoint.x, aPoint.y, FIGHTER_WIDTH, FIGHTER_HEIGHT, rDirection);
						if(!isDead)
							rIdleAnimation.drawNextFrame(recipient, rPoint.x, rPoint.y, FIGHTER_WIDTH, FIGHTER_HEIGHT, aDirection);
					}
					cnt++;
					if (cnt > Math.max(healFrame, attackFrame + hurtStartFrame)) {
						isBack = true;
						attackAnimation.setIndex(0);
						hurtAnimation.setIndex(0);
						rIdleAnimation.setIndex(0);
						healAnimation.setIndex(0);
						dyingAnimation.setIndex(0);
						System.out.println("act");
					}
						
				}
				// 返回
				if (isBack ) {
					if(!moveRectangleToTarget(source, aPoint, speedX, speedY))
						walkAnimation.drawNextFrame(actor, aPoint.x, aPoint.y, FIGHTER_WIDTH, FIGHTER_HEIGHT, rDirection);
					else {
						aIdleAnimation.drawNextFrame(actor, aPoint.x, aPoint.y, FIGHTER_WIDTH, FIGHTER_HEIGHT, aDirection);
						walkAnimation.setIndex(0);
						aIdleAnimation.setIndex(0);
						System.out.println("back");
						((Timer) e.getSource()).stop();
						trigger.actionPerformed(e);
						
					}
				}
			}
		});
	}
	
	private boolean moveRectangleToTarget(Point target, Point point, int speedX, int speedY) {		
		// 计算当前位置与目标位置之间的距离
		double distance = point.distance(target);
		// 如果距离小于等于一定值，则停止动画
		if (distance <= 10) {
			point.x = target.x;
			point.y = target.y;
			return true;
		} else {
			// 根据移动步长调整矩形位置
			if (point.x < target.x) {
				point.x += speedX;
			} else {
				point.x -= speedX;
			}

			if (Math.abs(point.y - target.y) > speedY) {
				point.y = point.y < target.y ? point.y + speedY : point.y - speedY;
			}
			return false;
		}
	}
}
