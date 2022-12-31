package vue;

import game.*;

import java.awt.EventQueue;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.*;
import java.awt.event.ActionEvent;

import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.border.CompoundBorder;
import java.awt.Font;
import java.awt.TextField;
import java.awt.Choice;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class UserInterface extends JFrame implements Config {
    static int semaphores = 0;

    static Zone[] zones = new Zone[ZoneName.values().length];
    boolean isValider = false;
    private JPanel contentPane;
    private TextField configPersonnage = new TextField();
    private TextField force;
    private TextField dexterite;
    private TextField resistance;
    private TextField constitution;
    private TextField initiative;
    private Choice choice;
    private Choice strategy;
    private JCheckBox reserviste;
    private Choice programme;
    private TextField nom;

    private JLabel jLabel_f;
    private JLabel jLabel_d;
    private JLabel jLabel_r;
    private JLabel jLabel_c;
    private JLabel jLabel_i;

    private Hashtable<String, int[]> points = new Hashtable<>(NUM_OF_STU);
    private static int REST_POINT = TOTAL_POINT;    // 剩余可以分配的点数
    private static int VALUE_SIZE = 8;              // 学生的信息长度

    private Category getCategory(String str) {
        Category c;
        if (str.matches("Capitaine Gobi")) {
            c = Category.MAITRE_GOBI;
        } else if (str.matches("^Elite \\d+")) {
            c = Category.ETUDIANT_ELITE;
        } else {
            c = Category.ETUDIANT;
        }
        return c;
    }

    private boolean canBeUpdated(int[] point, String key) {
        int[] tmp = {
                point[attribute.get("f")],
                point[attribute.get("d")],
                point[attribute.get("r")],
                point[attribute.get("c")],
                point[attribute.get("i")]
        };
        boolean isCorrect = true;
        for (int i : tmp) {
            if (i < 0 || i > 10) {
                isCorrect = false;
                JOptionPane.showMessageDialog(
                        null, "Mauvaise plage de la dexterite, il faut entre 2 et 10",
                        "Erreur!", JOptionPane.ERROR_MESSAGE);
            }
        }
        return isCorrect;
    }

    private boolean canValid(){
        int[] numOfPeopleInZone = new int[ZoneName.values().length];
        int numOfRes = 0;
        for(int i=0;i<ZoneName.values().length;i++){
            numOfPeopleInZone[i]=0;
        }
        for(int[] i: points.values()){
            numOfPeopleInZone[i[attribute.get("z")]]++;
            numOfRes+= i[attribute.get("isr")];
        }
        boolean ret = true;
        if( numOfRes < NUM_OF_RES)
            ret = false;
        for(int i: numOfPeopleInZone){
            if(i==0){
                ret =false;
                break;
            }
        }
        return ret;
    }

    private void pointsUpdate(int[] point, String key) {
        jLabel_f.setText(Integer.toString(
                point[attribute.get("f")] + INITIAL_VALUES[getCategory(key).ordinal()][0]));
        jLabel_d.setText(Integer.toString(
                point[attribute.get("d")] + INITIAL_VALUES[getCategory(key).ordinal()][0]));
        jLabel_r.setText(Integer.toString(
                point[attribute.get("r")] + INITIAL_VALUES[getCategory(key).ordinal()][0]));
        jLabel_c.setText(Integer.toString(
                point[attribute.get("c")] + INITIAL_VALUES[getCategory(key).ordinal()][1]));
        jLabel_i.setText(Integer.toString(
                point[attribute.get("i")] + INITIAL_VALUES[getCategory(key).ordinal()][0]));
    }

    public UserInterface(Player[] players) {
        for (int i = 0; i < ZoneName.values().length; i++) {
            this.zones[i] = new Zone(ZoneName.values()[i]);
        }
        // 初始化Hashtable points，使每一个数组初始化0
        points.put("Capitaine Gobi", new int[VALUE_SIZE]);
        for (int i = 0; i < NUM_OF_EACH_STU_KIND[Category.ETUDIANT_ELITE.ordinal()]; i++) {
            points.put("Elite " + i, new int[VALUE_SIZE]);
        }
        for (int i = 0; i < NUM_OF_EACH_STU_KIND[Category.ETUDIANT.ordinal()]; i++) {
            points.put("Etudiant " + i, new int[VALUE_SIZE]);
        }
        // 初始化窗口
        this.initialize(players);
    }


    private void initialize(Player[] players) {
        // 主面板
        setForeground(Color.BLACK);
        setTitle("Configuration Equipe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();
        contentPane = new JPanel();
        contentPane.setBorder(new CompoundBorder());
        contentPane.setBackground(Color.CYAN);
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setBounds(100, 100, 1200, 696);
        // config personnage 黄色条目
        configPersonnage.setBackground(Color.YELLOW);
        configPersonnage.setForeground(Color.BLACK);
        configPersonnage.setFont(new Font("Tahoma", Font.ITALIC, 20));
        configPersonnage.setBounds(426, 331, 360, 34);
        contentPane.add(configPersonnage);
        // Capitaine Gobi
        JPanel panelMaitre = new JPanel();
        panelMaitre.setBorder(new LineBorder(new Color(0, 0, 0), 5));
        panelMaitre.setBackground(Color.CYAN);
        panelMaitre.setBounds(79, 102, 61, 122);
        panelMaitre.setLayout(new GridLayout(1, 1, 0, 0)); // politique de placement des composants dans ce panel
        JButton jb1 = new JButton(); // pour representer un personnage, utilisation d'un JButton
        panelMaitre.add(jb1);
        jb1.setForeground(Color.CYAN);
        Image img1 = new ImageIcon("image\\maitre.png").getImage();
        jb1.setIcon(new ImageIcon(img1));
        jb1.addActionListener(new ImageClickListener(
                this.points, new String("Capitaine Gobi")));
        contentPane.add(panelMaitre);
        // 标签 Capitaine Gobi
        JLabel lblNewLabel = new JLabel("Capitaine Gobi");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel.setBounds(51, 75, 128, 24);
        contentPane.add(lblNewLabel);
        // Elites
        JPanel panelElite = new JPanel();
        panelElite.setBorder(new LineBorder(new Color(0, 0, 0), 5));
        panelElite.setBackground(Color.CYAN);
        panelElite.setBounds(354, 102, 274, 122);
        panelElite.setLayout(new GridLayout(1, 4, 0, 0));
        Image img2 = new ImageIcon("image\\elite.png").getImage();
        JButton[] jb = new JButton[NUM_OF_EACH_STU_KIND[Category.ETUDIANT_ELITE.ordinal()]];
        for (int i = 0; i < NUM_OF_EACH_STU_KIND[Category.ETUDIANT_ELITE.ordinal()]; i++) {
            jb[i] = new JButton();
            jb[i].setIcon(new ImageIcon(img2));
            panelElite.add(jb[i]);
            jb[i].addActionListener(new ImageClickListener(
                    this.points, new String("Elite " + i)));
        }
        contentPane.add(panelElite);
        // 标签 Elites
        JLabel lblNewLabel_1 = new JLabel("Les Elites");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel_1.setBounds(449, 77, 83, 21);
        contentPane.add(lblNewLabel_1);
        // Etudiants
        JPanel panelEtu = new JPanel();
        panelEtu.setBorder(new LineBorder(new Color(0, 0, 0), 5));
        panelEtu.setBackground(Color.CYAN);
        panelEtu.setBounds(830, 102, 274, 411);
        panelEtu.setLayout(new GridLayout(4, 4, 2, 0));
        contentPane.add(panelEtu);
        // 标签 Etudiants
        JLabel lblNewLabel_2 = new JLabel("Les Etudiants de base");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel_2.setBounds(885, 76, 192, 20);
        contentPane.add(lblNewLabel_2);
        Image img3 = new ImageIcon("image\\etudiant.png").getImage();
        JButton[] jb2 = new JButton[NUM_OF_EACH_STU_KIND[Category.ETUDIANT.ordinal()]];
        for (int k = 0; k < NUM_OF_EACH_STU_KIND[Category.ETUDIANT.ordinal()]; k++) {
            jb2[k] = new JButton();
            jb2[k].setIcon(new ImageIcon(img3));
            panelEtu.add(jb2[k]);
            jb2[k].addActionListener(new ImageClickListener(
                    this.points, new String("Etudiant " + k)));
        }
        // 玩家名字
        JLabel lblNewLabel_3 = new JLabel("Joueur");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblNewLabel_3.setBounds(254, 24, 76, 37);
        contentPane.add(lblNewLabel_3);

        nom = new TextField();
        nom.setText(players[0].getGamer().toString());
        nom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(nom.getText());
                players[0].setName(nom.getText());
            }
        });
        nom.setFont(new Font("Tahoma", Font.PLAIN, 24));
        nom.setBounds(336, 24, 152, 37);
        contentPane.add(nom);

        // 玩家队伍选择下拉栏
        Programme playerProgramme = Programme.A2I;
        JLabel lblNewLabel_13 = new JLabel("Programme");
        lblNewLabel_13.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblNewLabel_13.setBounds(562, 24, 130, 37);
        contentPane.add(lblNewLabel_13);
        programme = new Choice();
        programme.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                Programme playerProgramme = Programme.values()[programme.getSelectedIndex()];
                System.out.println(playerProgramme);
                //设置玩家队伍

            }
        });
        programme.setFont(new Font("Tahoma", Font.PLAIN, 24));
        programme.setBounds(698, 27, 72, 34);
        for (Programme p : Programme.values()) {
            programme.add(p.toString());
        }
        contentPane.add(programme);

        // 分配点数栏
        JLabel lblNewLabel_4 = new JLabel("Points à distribuer");
        lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblNewLabel_4.setBounds(72, 309, 192, 26);
        contentPane.add(lblNewLabel_4);

        JLabel lblNewLabel_pd = new JLabel(Integer.toString(REST_POINT));
        lblNewLabel_pd.setFont(new Font("Tahoma", Font.PLAIN, 22));
        lblNewLabel_pd.setBounds(297, 304, 61, 37);
        contentPane.add(lblNewLabel_pd);

        // Force
        JLabel lblNewLabel_5 = new JLabel("Force");
        lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblNewLabel_5.setBounds(193, 389, 61, 37);
        contentPane.add(lblNewLabel_5);
        force = new TextField();
        force.setFont(new Font("Tahoma", Font.PLAIN, 24));
        force.setText("0");
        force.setBounds(297, 389, 61, 37);
        contentPane.add(force);

        // 标签
        jLabel_f = new JLabel("0");
        jLabel_f.setFont(new Font("Tahoma", Font.PLAIN, 24));
        jLabel_f.setBounds(370, 389, 30, 37);
        contentPane.add(jLabel_f);

        // Dexterite
        JLabel lblNewLabel_6 = new JLabel("Dextérité");
        lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblNewLabel_6.setBounds(159, 432, 105, 37);
        contentPane.add(lblNewLabel_6);
        dexterite = new TextField();
        dexterite.setFont(new Font("Tahoma", Font.PLAIN, 24));
        dexterite.setText("0");
        dexterite.setBounds(297, 432, 61, 37);
        contentPane.add(dexterite);

        // 标签
        jLabel_d = new JLabel("0");
        jLabel_d.setFont(new Font("Tahoma", Font.PLAIN, 24));
        jLabel_d.setBounds(370, 432, 30, 37);
        contentPane.add(jLabel_d);

        // Resistance
        JLabel lblNewLabel_7 = new JLabel("Résistance");
        lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblNewLabel_7.setBounds(147, 479, 117, 37);
        contentPane.add(lblNewLabel_7);
        resistance = new TextField();
        resistance.setFont(new Font("Tahoma", Font.PLAIN, 24));
        resistance.setText("0");
        resistance.setBounds(297, 475, 61, 37);
        contentPane.add(resistance);

        // 标签
        jLabel_r = new JLabel("0");
        jLabel_r.setFont(new Font("Tahoma", Font.PLAIN, 24));
        jLabel_r.setBounds(370, 475, 30, 37);
        contentPane.add(jLabel_r);

        // Constitution
        JLabel lblNewLabel_8 = new JLabel("Constitution");
        lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblNewLabel_8.setBounds(136, 522, 128, 37);
        contentPane.add(lblNewLabel_8);
        constitution = new TextField();
        constitution.setFont(new Font("Tahoma", Font.PLAIN, 24));
        constitution.setText("0");
        constitution.setBounds(297, 518, 61, 37);
        contentPane.add(constitution);

        // 标签
        jLabel_c = new JLabel("0");
        jLabel_c.setFont(new Font("Tahoma", Font.PLAIN, 24));
        jLabel_c.setBounds(370, 518, 30, 37);
        contentPane.add(jLabel_c);

        // Initiative
        JLabel lblNewLabel_9 = new JLabel("Initiative");
        lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblNewLabel_9.setBounds(171, 569, 93, 29);
        contentPane.add(lblNewLabel_9);
        initiative = new TextField();
        initiative.setFont(new Font("Tahoma", Font.PLAIN, 24));
        initiative.setText("0");
        initiative.setBounds(297, 561, 61, 37);
        contentPane.add(initiative);

        // 标签
        jLabel_i = new JLabel("0");
        jLabel_i.setFont(new Font("Tahoma", Font.PLAIN, 24));
        jLabel_i.setBounds(370, 561, 30, 37);
        contentPane.add(jLabel_i);

        // Affectation 地点
        JLabel lblNewLabel_10 = new JLabel("Affectation");
        lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblNewLabel_10.setBounds(422, 389, 117, 37);
        contentPane.add(lblNewLabel_10);
        choice = new Choice();
        choice.setFont(new Font("Tahoma", Font.PLAIN, 20));
        choice.setBounds(562, 392, 224, 31);
        for (ZoneName z : ZoneName.values()) {
            choice.add(z.toString());
        }
        contentPane.add(choice);

        // type de strategie
        JLabel lblNewLabel_11 = new JLabel("Stratégie");
        lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblNewLabel_11.setBounds(443, 452, 96, 37);
        contentPane.add(lblNewLabel_11);
        strategy = new Choice();
        strategy.setFont(new Font("Tahoma", Font.PLAIN, 20));
        strategy.setBounds(562, 455, 224, 31);
        for (Strategy s : Strategy.values()) {
            strategy.add(s.toString());
        }
        contentPane.add(strategy);

        // Réserviste ?
        reserviste = new JCheckBox("Reserviste");
        reserviste.setBackground(Color.CYAN);
        reserviste.setForeground(Color.BLACK);
        reserviste.setFont(new Font("Tahoma", Font.PLAIN, 24));
        reserviste.setBounds(579, 513, 187, 26);
        contentPane.add(reserviste);

        // ok 按钮
        JButton ok = new JButton("OK");
        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String key = configPersonnage.getText();
                // 更新对应玩家数组
                int[] p = new int[8];
                p[attribute.get("isr")] = reserviste.isSelected() ? 1 : 0;
                p[attribute.get("f")] = Integer.parseInt(force.getText());
                p[attribute.get("c")] = Integer.parseInt(constitution.getText());
                p[attribute.get("d")] = Integer.parseInt(dexterite.getText());
                p[attribute.get("i")] = Integer.parseInt(initiative.getText());
                p[attribute.get("r")] = Integer.parseInt(resistance.getText());
                p[attribute.get("str")] = strategy.getSelectedIndex();
                p[attribute.get("z")] = choice.getSelectedIndex();
                // 判断输入
                if (canBeUpdated(p, key)) {
                    points.put(key, p);
                    // 更新剩余点数
                    REST_POINT = TOTAL_POINT;
                    for (int[] i : points.values()) {
                        REST_POINT -= i[attribute.get("f")] + i[attribute.get("c")]
                                + i[attribute.get("d")] + i[attribute.get("i")]
                                + i[attribute.get("r")];
                    }
                    lblNewLabel_pd.setText(Integer.toString(REST_POINT));
                    // 更新点数
                    pointsUpdate(p, key);
                }
            }
        });
        ok.setBounds(711, 545, 59, 45);
        contentPane.add(ok);
        ok.setFont(new Font("Tahoma", Font.PLAIN, 18));

        // valider 按钮
        JButton validation = new JButton("VALIDER");
        validation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Affichage des caracteristiques du joueur.

                if(canValid()){
                    ArrayList<Student> students = new ArrayList<>();
                    ArrayList<Student> reservists = new ArrayList<>();
                    Set entry = points.entrySet();
                    Iterator<Map.Entry> it = entry.iterator();
                    while (it.hasNext()) {
                        Map.Entry tmp = it.next();
                        int[] value = (int[]) tmp.getValue();
                        Student s = new Student((String) tmp.getKey());
                        s.setCategory(getCategory((String) tmp.getKey()));
                        s.setForce(value[attribute.get("f")]);
                        s.setInitiative(value[attribute.get("i")]);
                        s.setDexterite(value[attribute.get("d")]);
                        s.setResistance(value[attribute.get("r")]);
                        s.setConstitution(value[attribute.get("c")]);
                        s.setStrategy(Strategy.values()[value[attribute.get("str")]]);
                        s.setZoneName(ZoneName.values()[value[attribute.get("z")]]);
                        s.setReservist(value[attribute.get("isr")] != 0);
                        if (s.isReservist()) {
                            reservists.add(s);
                        } else {
                            students.add(s);
                        }
                    }
                    players[0].setName(nom.getText());
                    players[0].setProgramme(Programme.values()[programme.getSelectedIndex()]);
                    players[0].setStudents(students);
                    players[0].setReservists(reservists);
                    System.out.println(players[0]);

                    if (semaphores < 2 || !isValider) {
                        semaphores++;
                        isValider = true;
                    }
                }else{
                    JOptionPane.showMessageDialog(
                            null, "每个地点都要分配至少一个人，并且要有5个预备役",
                            "Erreur!", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        validation.setFont(new Font("Tahoma", Font.PLAIN, 24));
        validation.setBounds(830, 523, 270, 83);
        contentPane.add(validation);
        // 开始游戏
        JButton startGame = new JButton("START");
        startGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(semaphores != 2){
                    JOptionPane.showMessageDialog(
                            null, "WAIT FOR ANOTHER USER",
                            "Erreur!", JOptionPane.ERROR_MESSAGE);
                }else{
                    MainProcess mainProcess = new MainProcess();
                    mainProcess.setZones(zones);
                    mainProcess.setPlayers(players);
                    mainProcess.game();
                    semaphores = 0;
                }
            }
        });
        startGame.setFont(new Font("Tahoma", Font.PLAIN, 24));
        startGame.setBounds(830, 10, 270, 60);
        contentPane.add(startGame);
        // 外观设置
        // panneau configuration
        JPanel panel = new JPanel();
        panel.setBackground(Color.CYAN);
        panel.setBorder(new LineBorder(new Color(0, 0, 0), 5));
        panel.setBounds(51, 345, 769, 261);
        contentPane.add(panel);
    }

    final class ImageClickListener implements ActionListener {
        private Hashtable<String, int[]> liste;
        private String key;

        public ImageClickListener(Hashtable<String, int[]> liste, String key) {
            this.liste = liste;
            this.key = key;
        }

        public void actionPerformed(ActionEvent e) {
            int[] point = liste.get(key);
            configPersonnage.setText(key);
            // 是否为预备役
            reserviste.setSelected(point[attribute.get("isr")] != 0);
            force.setText(Integer.toString(point[attribute.get("f")]));
            dexterite.setText(Integer.toString(point[attribute.get("d")]));
            resistance.setText(Integer.toString(point[attribute.get("r")]));
            constitution.setText(Integer.toString(point[attribute.get("c")]));
            initiative.setText(Integer.toString(point[attribute.get("i")]));
            strategy.select(point[attribute.get("str")]);
            // 地点
            choice.select(point[attribute.get("z")]);
            // 更新点数
            pointsUpdate(point, key);
        }
    }

    public static void main(String[] args) throws InvocationTargetException, InterruptedException, IOException {
        Scanner sc = new Scanner(System.in);
        EventQueue.invokeAndWait(new Runnable() {
            public void run() {
                try {
                    Player a = new Player(Gamers.A);
                    Player b = new Player(Gamers.B);
                    UserInterface uiA = new UserInterface(new Player[]{a, b});
                    UserInterface uiB = new UserInterface(new Player[]{b, a});
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
