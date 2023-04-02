package classes.controller;

import classes.model.battlefields.District;
import classes.model.characters.players.Team;
import classes.model.game.Game;
import classes.vue.GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Connects the GUI with the game
 */
public class GameController {

    private final GUI view;

    private final JButton buttonPlay;

    private final Game game;

    private final FighterController fc1, fc2;

    private boolean isFirstRound;

    private String mode;

    /**
     * Create a new GameController.
     *
     * @see GUI
     * @see Game
     * @see FighterController
     * @param view a reference to the main window
     * @param game a reference to the game
     * @param fc1 a reference to the FighterController of Player 1
     * @param fc2 a reference to the FighterController of Player 2
     * @param mode the game mode either "demo" or "game"
     */
    public GameController(GUI view, Game game, FighterController fc1, FighterController fc2, String mode) {
        this.view = view;
        this.buttonPlay = view.getButtonPlay();
        this.game = game;
        this.fc1 = fc1;
        this.fc2 = fc2;
        this.mode = mode;
        this.isFirstRound = true;
        this.init();
    }

    /**
     * Add the actionListener to the play button.
     */
    private void init() {
        this.buttonPlay.addActionListener(e -> this.play());
    }

    /**
     * Display both teams for each district.
     */
    private void printFightersToConsole() {
        for(District d: this.game.getDistricts()) {
            System.out.println("\n" + d);
            System.out.println("\t" + this.game.getP1().getName() + ": " + d.getTeam(this.game.getP1().getTeam()));
            System.out.println("\t" + this.game.getP2().getName() + ": " + d.getTeam(this.game.getP2().getTeam()));
        }
    }

    /**
     * Inform the players that it is the "halftime".
     */
    private void halfTime() {
        printFightersToConsole();
        JOptionPane.showMessageDialog(new JFrame(), "!!!HALFTIME!!!");
    }

    /**
     * Check for the first round if the fight is ready according to the game mode.
     * For "demo", check that there is at least one fighter in both teams for every district.
     * For "demo", check that both players had set 5 reservists and assign a district to all the other fighters.
     *
     * @see District
     * @see FighterController#enableFields()
     * @see FighterController#disableFields()
     * @return a boolean indicating if the game can start
     */
    private boolean isFightReady() {
        if (this.mode.equals("demo")) {
            if (this.isFirstRound) {
                for(District d: this.game.getDistricts()) {
                    if (d.getTeam(this.game.getP1().getTeam()).isEmpty() || d.getTeam(this.game.getP2().getTeam()).isEmpty()) {
                        this.isFirstRound = true;
                        this.fc1.enableFields();
                        this.fc2.enableFields();
                        JOptionPane.showMessageDialog(new JFrame(), "Some districts are empty!!");
                        return false;
                    }
                }
            }
        } else if (this.mode.equals("game")) {
            if(this.isFirstRound) {
                int countFighters = 0;
                for (District d: this.game.getDistricts()) {
                    countFighters += d.getTeam(this.game.getP1().getTeam()).size();
                    countFighters += d.getTeam(this.game.getP2().getTeam()).size();
                }
                if (countFighters < 30) {
                    JOptionPane.showMessageDialog(new Frame(), "Please assign a district to every fighters");
                    this.isFirstRound = true;
                    this.fc1.enableFields();
                    this.fc2.enableFields();
                    return false;
                } else if (this.game.getP1().getReservists().size() < 5 || this.game.getP2().getReservists().size() < 5) {
                    JOptionPane.showMessageDialog(new Frame(), "Both players should start with 5 reservists");
                    this.isFirstRound = true;
                    this.fc1.enableFields();
                    this.fc2.enableFields();
                    return false;
                }
            }
        }

        this.fc1.disableFields();
        this.fc2.disableFields();
        this.isFirstRound = false;
        return true;
    }

    /**
     * The game loop. If the game is over the score of both players is displayed, the window is closed right after.
     *
     * @see #isFightReady()
     * @see Game#checkWin()
     * @see FighterController#resetFighters()
     * @see #halfTime()
     */
    private void play() {
        if (!this.isFightReady()) {
            return;
        }

        if(this.game.checkWin()) {
            JOptionPane.showMessageDialog(new Frame(), this.game.getP1().getName() + " " + this.game.getScoreP1() + " - " + this.game.getScoreP2() + " " + this.game.getP2().getName());
            this.view.close();
            return;
        }

        while(!this.game.checkWin()) {
            District[] activeDistricts = this.game.getActiveDistricts();
            for(District d: activeDistricts) {
                this.game.getFightingStyle().fight(d.getTeam(this.game.getP1().getTeam()), d.getTeam(this.game.getP2().getTeam()));
                if (d.isFightOver() != Team.NONE) {
                    this.fc1.resetFighters();
                    this.fc2.resetFighters();
                    this.halfTime();
                    return;
                }
            }
        }
    }
}
