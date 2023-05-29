package classes.model.animation;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import classes.controller.ControllerVueCore;
import classes.model.characters.players.Player.Branch;
import classes.model.characters.fighters.students.Student.Character;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.awt.Image;

public class Animation {

	private ArrayList<Image> imgs;
	private int index;

	private final static HashMap<Branch, String> teamPaths = new HashMap<Branch, String>() {
		{
			// folderPath 开头不加/
			put(Branch.GI, "src/resources/images/animation/Pirate/");
			put(Branch.RT, "src/resources/images/animation/Thief/");
			put(Branch.A2I, "src/resources/images/animation/Barbarian/");
			put(Branch.GM, "src/resources/images/animation/RedHat/");
			put(Branch.ISI, "src/resources/images/animation/Devil/");
			put(Branch.MTE, "src/resources/images/animation/Wizard/");
		}
	};

	public final static HashMap<Character, String> characterPaths = new HashMap<Character, String>() {
		{
			put(Character.Student, "src/resources/images/animation/cStudent/");
			put(Character.Elite, "src/resources/images/animation/cElite/");
			put(Character.Gobi, "src/resources/images/animation/cGobi/");
		}
	};

	public enum Action {
		Dying, Hurt, Kicking, Walking, Attacking, Taunt, Idle
	}

	public enum Direction {
		Left, Right
	}

	private Animation() {
		this.imgs = new ArrayList<>();
		this.index = 0;
	}
	
	public static Animation createAnimation(Object object, Action action) {
		Animation animation = new Animation();
		animation.loadIamges(getActionFolder(object, action));
		return animation;
	}

	public static String getActionFolder(Object object, Action action) {
		String path = null;
		if(object instanceof Branch) {
			path = teamPaths.get((Branch)object);
		}else if(object instanceof Character) {
			path = characterPaths.get((Character)object);
		}
		switch (action) {
		case Dying:
			return path + "Dying";
		case Hurt:
			return path + "Hurt";
		case Kicking:
			return path + "Kicking";
		case Walking:
			return path + "Walking";
		case Attacking:
			return path + "Attacking";
		case Taunt:
			return path + "Taunt";
		case Idle:
			return path + "Idle";
		default:
			return null;
		}
	}

	
	public void loadIamges(String folderPath) {
		File folder = new File(folderPath);

		if (!folder.exists()) {
			System.out.println("文件夹不存在：" + folderPath);
		}

		File[] files = folder.listFiles((dir, name) -> {
			String lowercaseName = name.toLowerCase();
			return lowercaseName.endsWith(".jpg") || lowercaseName.endsWith(".jpeg") || lowercaseName.endsWith(".png")
					|| lowercaseName.endsWith(".gif");
		});

		if (files == null || files.length == 0) {
			System.out.println("文件夹中没有符合条件的图片文件：" + folderPath);
		}

		// 按文件名字排序
		Arrays.sort(files, Comparator.comparing(File::getName));

		for (File file : files) {
			String filePath = file.getAbsolutePath();
			ImageIcon imageIcon = new ImageIcon(filePath);
			imgs.add(imageIcon.getImage());
		}
	}

	public Image getNextFrame() {
		index = (index + 1) % imgs.size();
		return imgs.get(index);
	}

	public void drawNextFrame(JLabel label, int x, int y, Direction direction) {
		// 得到需要绘制的图片
		Image img = this.getNextFrame();
		ImageIcon imageIcon = new ImageIcon(img);

		if (direction == Direction.Left) {
			imageIcon = ControllerVueCore.flipImageHorizontally(imageIcon);
		}

		// 设置Label的图标为绘制的图片
		label.setIcon(imageIcon);
		label.setBounds(x, y, imageIcon.getIconWidth(), imageIcon.getIconHeight());
		label.revalidate();
		label.repaint();
	}

	public void drawNextFrame(JLabel label, int x, int y, int w, int h, Direction direction) {
		// 得到需要绘制的图片
		Image img = this.getNextFrame();
		img = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		// 创建一个新的ImageIcon
		ImageIcon imageIcon = new ImageIcon(img);

		if (direction == Direction.Left) {
			imageIcon = ControllerVueCore.flipImageHorizontally(imageIcon);
		}

		// 设置Label的图标为绘制的图片
		label.setIcon(imageIcon);
		label.setBounds(x, y, w, h);
		label.revalidate();
		label.repaint();
	}

	public ArrayList<Image> getImgs() {
		return imgs;
	}

	public void setImgs(ArrayList<Image> imgs) {
		this.imgs = imgs;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
