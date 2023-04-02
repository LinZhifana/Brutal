package classes.vue;

import classes.model.battlefields.District;
import classes.model.characters.fighters.strategies.Attack;
import classes.model.characters.fighters.strategies.Defense;
import classes.model.characters.fighters.strategies.Random;
import classes.model.characters.fighters.strategies.Strategy;
import classes.model.characters.fighters.students.Student;
import classes.model.characters.players.Branch;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * A Graphical User Interface representing the game "C'est du brutal".
 *
 * @see JFrame
 * @version 1.0
 */
public class GUI extends JFrame {
    private JPanel contentPane;
    private final int windowWidth = 700;
    private final int windowHeight = 700;

    private final ArrayList<JTextField> characteristicsP1Input, characteristicsP2Input;

    private final String[] characteristicsNames;
    private final Strategy[] strategies;
    private JTextField p1NameInput, p2NameInput;
    private JComboBox<Branch> p1BranchInput, p2BranchInput;
    private JComboBox<District> p1DistrictInput, p2DistrictInput;
    private JComboBox<Strategy> p1StrategyInput, p2StrategyInput;
    private JComboBox<Student> p1FighterInput, p2FighterInput;
    private JCheckBox p1ReservistInput, p2ReservistInput;
    private JButton p1Button, p2Button, p1ButtonConfirm, p2ButtonConfirm, p1ButtonSeeCredits, p2ButtonSeeCredits, buttonPlay;

    private TitledBorder p1BorderFighter, p2BorderFighter;

    /**
     * Create a new window.
     * @see #initWindow()
     */
    public GUI() {
        this.characteristicsP1Input = new ArrayList<>();
        this.characteristicsP2Input = new ArrayList<>();
        this.characteristicsNames = new String[]{"Dexterity", "Strength", "Resistance", "Constitution", "Initiative"};
        this.strategies = new Strategy[]{new Attack(), new Defense(), new Random()};
        this.initWindow();
    }

    /**
     * Initialize the default window.
     *
     * @see #createP1Section()
     * @see #createP2Section()
     */
    private void initWindow() {
        setTitle("C'est du brutal!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(windowWidth, windowHeight));
        setLocationRelativeTo(null);
        this.contentPane = new JPanel();
        this.contentPane.setBorder(new EmptyBorder(10, 20, 10, 20));
        setContentPane(this.contentPane);
        this.contentPane.setLayout(new BorderLayout());
        this.contentPane.add(this.createP1Section(), BorderLayout.WEST);
        this.contentPane.add(this.createP2Section(), BorderLayout.EAST);
        this.buttonPlay = GUI.createButton(100, 30, "PLAY");
        this.contentPane.add(this.buttonPlay, BorderLayout.SOUTH);
    }

    /**
     * Create the player 1 (left) section.
     *
     * @see #createP1InputSection()
     * @see #createP1Fighters()
     * @see #createP1FighterInitSection()
     * @return a JPanel
     */
    private JPanel createP1Section() {
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
        container.add(this.createP1InputSection());
        container.add(this.createP1Fighters());
        container.add(this.createP1FighterInitSection());
        return container;
    }

    /**
     * Create the player 2 (right) section.
     *
     * @see #createP2InputSection()
     * @see #createP2Fighters()
     * @see #createP2FighterInitSection()
     * @return a JPanel
     */
    private JPanel createP2Section() {
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
        container.add(this.createP2InputSection());
        container.add(this.createP2Fighters());
        container.add(this.createP2FighterInitSection());
        return container;
    }

    /**
     * Create the player 1 input section.
     *
     * @return a JPanel
     */
    private JPanel createP1InputSection() {
        LineBorder border = new LineBorder(new Color(0,0,0), 1, true);
        JPanel container = new JPanel();
        JPanel containerInputs = new JPanel();
        this.p1NameInput = GUI.createTextField(100, 30);
        this.p1BranchInput = GUI.createComboBox(100, 30, Branch.values());
        this.p1Button = GUI.createButton(100, 30, "CONFIRM");
        container.setBorder(new TitledBorder(border, "Player"));
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
        containerInputs.add(this.p1NameInput);
        containerInputs.add(this.p1BranchInput);
        container.add(containerInputs);
        container.add(this.p1Button);
        container.setSize(new Dimension(220, 90));
        return container;
    }

    /**
     * Create the player 2 input section.
     *
     * @return a JPanel
     */
    private JPanel createP2InputSection() {
        LineBorder border = new LineBorder(new Color(0,0,0), 1, true);
        JPanel container = new JPanel();
        JPanel containerInputs = new JPanel();
        this.p2NameInput = GUI.createTextField(100, 30);
        this.p2BranchInput = GUI.createComboBox(100, 30, Branch.values());
        this.p2Button = GUI.createButton(100, 30, "CONFIRM");
        container.setBorder(new TitledBorder(border, "Player"));
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
        containerInputs.add(this.p2NameInput);
        containerInputs.add(this.p2BranchInput);
        container.add(containerInputs);
        container.add(this.p2Button);
        container.setSize(new Dimension(220, 90));
        return container;
    }

    /**
     * Create the player 1 section to initialize a fighter.
     *
     * @return a JPanel
     */
    private JPanel createP1FighterInitSection() {
        LineBorder border = new LineBorder(new Color(0,0,0), 1, true);
        JPanel container = new JPanel();
        JPanel containerReservist = new JPanel();
        containerReservist.setLayout(new BorderLayout());
        this.p1BorderFighter = new TitledBorder(border, "Fighter");
        container.setBorder(this.p1BorderFighter);
        container.setSize(new Dimension(220, 30*this.characteristicsNames.length+10*this.characteristicsNames.length*2+30*2+10));
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
        for(String text: this.characteristicsNames) {
            JPanel containerChara = new JPanel();
            containerChara.setLayout(new BorderLayout());
            JLabel label = new JLabel(text);
            label.setSize(new Dimension(60, 30));
            JTextField textField = GUI.createTextField(40, 30);
            this.characteristicsP1Input.add(textField);
            containerChara.add(label, BorderLayout.WEST);
            containerChara.add(textField, BorderLayout.EAST);
            container.add(containerChara);
            container.add(Box.createRigidArea(new Dimension(0, 10)));
        }
        this.p1DistrictInput = GUI.createComboBox(100, 30, new District[]{});
        this.p1StrategyInput = GUI.createComboBox(100, 30, this.strategies);
        this.p1ReservistInput = GUI.createCheckBox(100, 30, "reservist");
        containerReservist.add(this.p1ReservistInput, BorderLayout.WEST);
        container.add(this.p1DistrictInput);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(this.p1StrategyInput);
        container.add(containerReservist);
        container.setVisible(true);
        return container;
    }

    /**
     * Create the player 2 section to initialize a fighter.
     *
     * @return a JPanel
     */
    private JPanel createP2FighterInitSection() {
        LineBorder border = new LineBorder(new Color(0,0,0), 1, true);
        JPanel container = new JPanel();
        JPanel containerReservist = new JPanel();
        containerReservist.setLayout(new BorderLayout());
        this.p2BorderFighter = new TitledBorder(border, "Fighter");
        container.setBorder(this.p2BorderFighter);
        container.setSize(new Dimension(220, 30*this.characteristicsNames.length+10*this.characteristicsNames.length*2+30*2+10));
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
        for(String text: this.characteristicsNames) {
            JPanel containerChara = new JPanel();
            containerChara.setLayout(new BorderLayout());
            JLabel label = new JLabel(text);
            label.setSize(new Dimension(60, 30));
            JTextField textField = GUI.createTextField(40, 30);
            this.characteristicsP2Input.add(textField);
            containerChara.add(label, BorderLayout.WEST);
            containerChara.add(textField, BorderLayout.EAST);
            container.add(containerChara);
            container.add(Box.createRigidArea(new Dimension(0, 10)));
        }
        this.p2DistrictInput = GUI.createComboBox(100, 30, new District[]{});
        this.p2StrategyInput = GUI.createComboBox(100, 30, this.strategies);
        this.p2ReservistInput = GUI.createCheckBox(100, 30, "reservist");
        containerReservist.add(this.p2ReservistInput, BorderLayout.WEST);
        container.add(this.p2DistrictInput);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(this.p2StrategyInput);
        container.add(containerReservist);
        container.setVisible(true);
        return container;
    }

    /**
     * Create the player 1 section to manage its fighters.
     *
     * @return a JPanel
     */
    private JPanel createP1Fighters() {
        LineBorder border = new LineBorder(new Color(0,0,0), 1, true);
        JPanel container = new JPanel();
        JPanel containerBottom = new JPanel();
        containerBottom.setLayout(new FlowLayout());
        container.setBorder(new TitledBorder(border, "Fighters"));
        container.setSize(new Dimension(100, 90));
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
        this.p1FighterInput = new JComboBox<>();
        this.p1ButtonSeeCredits = GUI.createButton(100, 30, "see credits");
        this.p1ButtonConfirm = GUI.createButton(100, 30, "CONFIRM");
        containerBottom.add(this.p1ButtonSeeCredits);
        containerBottom.add(this.p1ButtonConfirm);
        container.add(containerBottom);
        container.add(this.p1FighterInput);
        container.add(containerBottom);
        return container;
    }

    /**
     * Create the player 2 section to manage its fighters.
     *
     * @return a JPanel
     */
    private JPanel createP2Fighters() {
        LineBorder border = new LineBorder(new Color(0,0,0), 1, true);
        JPanel container = new JPanel();
        JPanel containerBottom = new JPanel();
        containerBottom.setLayout(new FlowLayout());
        container.setBorder(new TitledBorder(border, "Fighters"));
        container.setSize(new Dimension(100, 90));
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
        this.p2FighterInput = new JComboBox<>();
        this.p2ButtonSeeCredits = GUI.createButton(100, 30, "see credits");
        this.p2ButtonConfirm = GUI.createButton(100, 30, "CONFIRM");
        containerBottom.add(this.p2ButtonSeeCredits);
        containerBottom.add(this.p2ButtonConfirm);
        container.add(containerBottom);
        container.add(this.p2FighterInput);
        container.add(containerBottom);
        return container;
    }

    /**
     * Close the window.
     */
    public void close() {
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    /**
     * Create a JCheckBox.
     *
     * @see JCheckBox
     * @param width the width of the checkbox
     * @param height the height of the checkbox
     * @param text the message to display
     * @return a JCheckBox with the specified parameters
     */
    public static JCheckBox createCheckBox(int width, int height, String text) {
        JCheckBox checkBox = new JCheckBox(text);
        checkBox.setPreferredSize(new Dimension(width, height));
        return checkBox;
    }

    /**
     * Create a JButton.
     *
     * @see JButton
     * @param width the width of the button
     * @param height the height of the button
     * @param text the message to display
     * @return a JButton with the specified parameters
     */
    public static JButton createButton(int width, int height, String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(width, height));
        return button;
    }

    /**
     * Create a JTextField.
     *
     * @see JTextField
     * @param width the width of the text field
     * @param height the height of the button
     * @return a JTextField with the specified size
     */
    public static JTextField createTextField(int width, int height) {
        JTextField field = new JTextField();
        field.setPreferredSize(new Dimension(width, height));
        return field;
    }

    /**
     * Create a JComboBox.
     *
     * @see JComboBox
     * @param width the width of the button
     * @param height the height of the button
     * @param choices a list of choices
     * @param <T> any type as an array
     * @return a JComboBox with the specified parameters
     */
    public static <T> JComboBox<T> createComboBox(int width, int height, T[] choices) {
        JComboBox<T> comboBox = new JComboBox<>(choices);
        comboBox.setPreferredSize(new Dimension(width, height));
        return comboBox;
    }

    public ArrayList<JTextField> getCharacteristicsP1Input() {
        return characteristicsP1Input;
    }

    public ArrayList<JTextField> getCharacteristicsP2Input() {
        return characteristicsP2Input;
    }

    public JTextField getP1NameInput() {
        return p1NameInput;
    }

    public JTextField getP2NameInput() {
        return p2NameInput;
    }

    public JComboBox<Branch> getP1BranchInput() {
        return p1BranchInput;
    }

    public JComboBox<Branch> getP2BranchInput() {
        return p2BranchInput;
    }

    public JComboBox<District> getP1DistrictInput() {
        return p1DistrictInput;
    }

    public JComboBox<Strategy> getP1StrategyInput() {
        return p1StrategyInput;
    }

    public JComboBox<Student> getP1FighterInput() {
        return p1FighterInput;
    }

    public JComboBox<District> getP2DistrictInput() {
        return p2DistrictInput;
    }

    public JComboBox<Strategy> getP2StrategyInput() {
        return p2StrategyInput;
    }

    public JComboBox<Student> getP2FighterInput() {
        return p2FighterInput;
    }

    public JCheckBox getP1ReservistInput() {
        return p1ReservistInput;
    }

    public JCheckBox getP2ReservistInput() {
        return p2ReservistInput;
    }

    public JButton getP1Button() {
        return p1Button;
    }

    public JButton getP2Button() {
        return p2Button;
    }

    public JButton getP1ButtonConfirm() {
        return p1ButtonConfirm;
    }

    public JButton getP2ButtonConfirm() {
        return p2ButtonConfirm;
    }

    public JButton getP1ButtonSeeCredits() {
        return p1ButtonSeeCredits;
    }

    public JButton getP2ButtonSeeCredits() {
        return p2ButtonSeeCredits;
    }

    public TitledBorder getP1BorderFighter() {
        return p1BorderFighter;
    }

    public TitledBorder getP2BorderFighter() {
        return p2BorderFighter;
    }

    public JButton getButtonPlay() {
        return buttonPlay;
    }
}
