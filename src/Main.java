
import classes.model.fight.TurnBased;
import classes.model.game.Game;
//import classes.vue.GUI;
import gui.*;

public class Main {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Missing parameters");
            System.exit(-1);
        } else if (!args[0].equals("demo") && !args[0].equals("game")) {
            System.out.println("Wrong value expected 'demo' or 'game', got '" + args[0] + "'");
            System.exit(-1);
        }

        Game game = new Game(new TurnBased());
        //GUI view = new GUI();
        accueil accueil= new accueil();
        personnalisation per=new personnalisation();
        gui.accueil.startAccueil();
        //PlayerController pc1 = new PlayerController(per.getP1NameInput(), personnalisation.getBranchP1(), view.getP1Button(), game.getP1());
        //PlayerController pc2 = new PlayerController(per.getP2NameInput(), personnalisation.getBranchP2(), view.getP2Button(), game.getP2());
        /*FighterController fc1 = new FighterController(view, view.getP1FighterInput(), view.getCharacteristicsP1Input(), view.getP1DistrictInput(), view.getP1StrategyInput(), view.getP1ReservistInput(), view.getP1ButtonConfirm(), view.getP1ButtonSeeCredits(), view.getP1BorderFighter(), game, game.getP1());
        FighterController fc2 = new FighterController(view, view.getP2FighterInput(), view.getCharacteristicsP2Input(), view.getP2DistrictInput(), view.getP2StrategyInput(), view.getP2ReservistInput(), view.getP2ButtonConfirm(), view.getP2ButtonSeeCredits(), view.getP2BorderFighter(), game, game.getP2());
        GameController gc = new GameController(view, game, fc1, fc2, args[0]);
        view.setVisible(true);*/
    }
}