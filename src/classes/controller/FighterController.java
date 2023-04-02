package classes.controller;

import classes.model.battlefields.District;
import classes.model.characters.fighters.strategies.Strategy;
import classes.model.characters.fighters.students.EliteStudent;
import classes.model.characters.fighters.students.GobiMaster;
import classes.model.characters.fighters.students.Student;
import classes.model.characters.players.Player;
import classes.model.game.Game;
import classes.vue.GUI;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.util.ArrayList;

/**
 * Connects the GUI with the fighters.
 */
public class FighterController {

    private final GUI view;

    private final ArrayList<JTextField> characteristicsInput;
    private final JComboBox<District> districtsInput;

    private final JComboBox<Strategy> strategyInput;

    private final JComboBox<Student> fighterInput;

    private final JCheckBox reservistInput;
    private final JButton button, buttonSeeCredits;

    private final TitledBorder borderFighter;

    private final Game game;

    private final Player player;
    private final ArrayList<Student> fighters;

    /**
     * Create a new FighterController.
     *
     * @param view a reference to the main window
     * @param fighterInput a reference to the combobox containing the list of fighters
     * @param characteristicsInput a reference to the fighter's characteristics inputs
     * @param districtsInput a reference to the combobox containing the list of districts
     * @param strategyInput a reference to the combobox containing the list of strategies
     * @param reservistInput a reference to the checkbox to set a fighter as a reservist
     * @param button a reference to the confirm button
     * @param buttonSeeCredits a reference to the button to see the credits
     * @param borderFighter a reference to the titled border container the player's amount of points
     * @param game a reference to the game
     * @param player a reference to a player
     * @see Game
     * @see Player
     * @see #init()
     * @see #setTexts()
     */
    public FighterController(GUI view, JComboBox<Student> fighterInput, ArrayList<JTextField> characteristicsInput, JComboBox<District> districtsInput, JComboBox<Strategy> strategyInput, JCheckBox reservistInput, JButton button, JButton buttonSeeCredits, TitledBorder borderFighter, Game game, Player player) {
        this.view = view;
        this.fighterInput = fighterInput;
        this.characteristicsInput = characteristicsInput;
        this.districtsInput = districtsInput;
        this.strategyInput = strategyInput;
        this.reservistInput = reservistInput;
        this.button = button;
        this.buttonSeeCredits = buttonSeeCredits;
        this.borderFighter = borderFighter;
        this.game = game;
        this.player = player;
        this.fighters = player.getFighters();
        this.init();
        this.setTexts();
    }

    /**
     * Update the list of available fighters.
     */
    public void resetFighters() {
        this.fighters.clear();
        for(District d: this.game.getDistricts()) {
            if (d.getTeam(this.player.getTeam()).size() > 1) {
                this.fighters.addAll(d.getTeam(this.player.getTeam()));
            }
        }
        this.fighters.addAll(this.player.getReservists());
        DefaultComboBoxModel model = new DefaultComboBoxModel<>(this.fighters.toArray());
        this.fighterInput.setModel(model);
    }

    /**
     * Update the texts displayed.
     */
    private void setTexts() {
        Student s = ((Student) this.fighterInput.getSelectedItem());
        assert s != null;
        this.characteristicsInput.get(0).setText(Integer.toString(s.getDexterity()));
        this.characteristicsInput.get(1).setText(Integer.toString(s.getStrength()));
        this.characteristicsInput.get(2).setText(Integer.toString(s.getResistance()));
        this.characteristicsInput.get(3).setText(Integer.toString(s.getConstitution()));
        this.characteristicsInput.get(4).setText(Integer.toString(s.getInitiative()));
        this.strategyInput.getModel().setSelectedItem(s.getStrategy());
        this.districtsInput.getModel().setSelectedItem(this.getFighterDistrict(s));
        this.reservistInput.setSelected(this.player.getReservists().contains(s));
        this.districtsInput.setEnabled(!this.reservistInput.isSelected());
        this.borderFighter.setTitle("Fighter / " + this.player.getPoints() + " credits");
        this.view.repaint();
    }

    /**
     * Get a fighter's district.
     *
     * @param s a fighter
     * @return the fighter's current district
     */
    private District getFighterDistrict(Student s) {
        for(District d: this.game.getDistricts()) {
            if (d.getTeam(this.player.getTeam()).contains(s)) {
                return d;
            }
        }
        return this.game.getDistricts()[0];
    }

    /**
     * Update a fighters characteristics, strategy and district.
     *
     * @see Student
     * @see #setFighterCharacteristics(Student)
     * @see #setFighterDistrict(Student)
     * @see #updatePlayerPoints()
     */
    private void setFighterParams() {
        Student s = ((Student) this.fighterInput.getSelectedItem());
        assert s != null;
        this.setFighterCharacteristics(s);

        s.setStrategy((Strategy) this.strategyInput.getSelectedItem());

        if (this.reservistInput.isSelected() && !this.player.getReservists().contains(s)) {
            if (this.player.getReservists().size() < 5) {
                this.player.getReservists().add(s);
                this.player.getFighters().remove(s);
                for(District d: this.game.getDistricts()) {
                    d.removeFighterTeam(s, this.player.getTeam());
                }
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "Already 5 reservists!");
            }
        } else if (!this.reservistInput.isSelected()) {
            this.player.getReservists().remove(s);
            this.setFighterDistrict(s);
        }
        this.updatePlayerPoints();
    }

    /**
     * Update a fighter's characteristics.
     *
     * @param s a fighter
     * @see Student
     */
    private void setFighterCharacteristics(Student s) {
        try {
            int dexterity = Integer.parseInt(this.characteristicsInput.get(0).getText());
            int strength = Integer.parseInt(this.characteristicsInput.get(1).getText());
            int resistance = Integer.parseInt(this.characteristicsInput.get(2).getText());
            int constitution = Integer.parseInt(this.characteristicsInput.get(3).getText());
            int initiative = Integer.parseInt(this.characteristicsInput.get(4).getText());
            if (this.player.getPoints() - dexterity - strength - resistance - constitution - initiative < 0) {
                JOptionPane.showMessageDialog(new JFrame(), "Not enough points");
                return;
            }
            s.setDexterity(dexterity);
            s.setStrength(strength);
            s.setResistance(resistance);
            s.setConstitution(constitution);
            s.setInitiative(initiative);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(new JFrame(), "Please input a number!");
        }
    }
//f.getDexterity()+f.getStrength()+f.getResistance()+f.getConstitution()+f.getInitiative()
    /**
     * Update the player's points.
     *
     * @see Player
     */
    private void updatePlayerPoints() {
        int totalFightersPoints = 0;
        for(District d: this.game.getDistricts()) {
            totalFightersPoints += d.getTeam(this.player.getTeam()).stream().mapToInt(f -> {
                int defaultAmount = f instanceof EliteStudent ? 9 : f instanceof GobiMaster ? 18 : 0;
                return f.getDexterity()+f.getStrength()+f.getResistance()+f.getConstitution()+f.getInitiative() - defaultAmount;
            }).sum();
        }
        totalFightersPoints += this.player.getReservists().stream().mapToInt(f -> {
            int defaultAmount = f instanceof EliteStudent ? 9 : f instanceof GobiMaster ? 18 : 0;
            return f.getDexterity()+f.getStrength()+f.getResistance()+f.getConstitution()+f.getInitiative() - defaultAmount;
        }).sum();
        this.player.setPoints(Math.min(400, 400-totalFightersPoints));
    }

    /**
     * Send a fighter to the selected district.
     *
     * @param s a fighter
     * @see District
     */
    private void setFighterDistrict(Student s) {
        District d = ((District) this.districtsInput.getSelectedItem());
        assert d != null;
        if (!d.getTeam(this.player.getTeam()).contains(s)) {
            for(District temp: this.game.getDistricts()) {
                temp.removeFighterTeam(s, this.player.getTeam());
            }
            d.addFighterTeam(s, this.player.getTeam());
            this.player.getFighters().remove(s);
        }
    }

    /**
     * Display the total amount of credits for each district.
     * @see Player#checkCreditsDistricts(District[])
     */
    private void displayCredits() {
        JOptionPane.showMessageDialog(new JFrame(), this.player.checkCreditsDistricts(this.game.getDistricts()));
    }

    /**
     * Disable the characteristics inputs.
     */
    public void disableFields() {
        this.characteristicsInput.forEach(c -> c.setEditable(false));
    }

    /**
     * Enable the characteristics inputs.
     */
    public void enableFields() {
        this.characteristicsInput.forEach(c -> c.setEditable(true));
    }

    /**
     * Initialize the view with the default parameters.
     *
     * @see #setTexts()
     * @see #setFighterParams()
     * @see #displayCredits()
     */
    private void init() {
        DefaultComboBoxModel model = new DefaultComboBoxModel<>(this.fighters.toArray());
        this.fighterInput.setModel(model);
        model = new DefaultComboBoxModel<>(this.game.getDistricts());
        this.districtsInput.setModel(model);
        this.button.addActionListener(e -> {
            this.setFighterParams();
            this.setTexts();
            System.out.println(this.player.getName());
            for(District d: this.game.getDistricts()) {
                System.out.println("\t"+d+ " " + d.getTeam(this.player.getTeam()));
            }
        });

        this.buttonSeeCredits.addActionListener(e -> this.displayCredits());

        this.fighterInput.addActionListener(e -> this.setTexts());

        this.reservistInput.addActionListener(e -> this.districtsInput.setEnabled(!this.reservistInput.isSelected()));
    }
}
