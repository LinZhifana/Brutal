package Brutal;

import static Brutal.Strategy.aleatoire;

public class Basestudent {
    private boolean isReserviste;
    private int creditsECTS;
    private int resistance; //防御
    private int force;
    private int dexterite; //敏捷
    private int constitution; //体质
    private int initiative; //主动性（决定行动顺序）
    private Strategy strategy;

    //Constructor
    public Basestudent() {
        this.isReserviste = false;
        this.creditsECTS = 30+this.constitution;
        this.resistance = 0;
        this.force = 0;
        this.dexterite = 0;
        this.constitution = 0;
        this.initiative = 0;
        this.strategy = Strategy.aleatoire;
    }

    public int attack(Basestudent student){

        return 0;
    }
    public int cure(Basestudent student){

        return 0;
    }

    public int[] executerStrategy(){

        return new int[0];
    }



    //getter and setter
    public boolean isReserviste() {
        return isReserviste;
    }

    public int getCreditsECTS() {
        return creditsECTS;
    }

    public int getResistance() {
        return resistance;
    }

    public int getForce() {
        return force;
    }

    public int getDexterite() {
        return dexterite;
    }

    public int getConstitution() {
        return constitution;
    }

    public int getInitiative() {
        return initiative;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setReserviste(boolean reserviste) {
        isReserviste = reserviste;
    }

    public void setCreditsECTS(int creditsECTS) {
        this.creditsECTS = creditsECTS;
    }

    public void setResistance(int resistance) {
        this.resistance = resistance;
    }

    public void setForce(int force) {if (force>=0 && force<=10){
        this.force = force;
    }
    else {System.out.println("Wrong range of value, the number should be between 0 and 10");}


    }

    public void setDexterite(int dexterite) {
        if (dexterite>=0 && dexterite<=10){
            this.dexterite = dexterite;
        }
        else {System.out.println("Wrong range of value, the number should be between 0 and 10");}


    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

   // public void setStrategy(String strategy)


}
