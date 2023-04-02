package classes.controller;

import classes.model.characters.players.Branch;
import classes.model.characters.players.Player;

import javax.swing.*;

/**
 * Connects the GUI with the player's model
 */
public class PlayerController {
    private final JTextField nameInput;
    private final JComboBox<Branch> branchInput;
    private final JButton button;
    private final Player player;

    /**
     * Create a new FighterController.
     *
     * @see Player
     * @param nameInput the text field in which the player writes its name
     * @param branchInput the combobox in which the player select its branch
     * @param button the button to add an actionListener to
     * @param player the player itself
     */
    public PlayerController(JTextField nameInput, JComboBox<Branch> branchInput, JButton button, Player player) {
        this.nameInput = nameInput;
        this.branchInput = branchInput;
        this.button = button;
        this.player = player;
        this.init();
    }

    /**
     * Initialize the view with the default parameters
     */
    private void init() {
        this.nameInput.setText(this.player.getName());
        this.branchInput.setSelectedItem(this.player.getBranch());
        this.button.addActionListener(e -> this.setPlayerInfo());
    }

    /**
     * Set the player's name and branch.
     * @see Player
     */
    private void setPlayerInfo() {
        this.player.setName(this.nameInput.getText());
        this.player.setBranch((Branch) this.branchInput.getSelectedItem());
    }


}
