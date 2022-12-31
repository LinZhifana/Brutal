package game;

import vue.UserInterface;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainProcess {
    private Player[] players = new Player[Gamers.values().length];
    private Zone[] zones = new Zone[ZoneName.values().length];

    static boolean hasDuplicateElement(String[] arr) {
        for (int i = 0; i < arr.length; i++)
            for (int j = i + 1; j < arr.length; j++)
                if (arr[i] == arr[j])
                    return true;
        return false;
    }

    static int[] checkInput(int lengthMin, int lengthMax, int min, int max, boolean isEmpty) {
        boolean isInputWrong;
        Scanner scanner = new Scanner(System.in);
        String[] userInput;
        do {
            String in = scanner.nextLine();
            userInput = in.split(" |,");
            isInputWrong = false;

            if (isEmpty && userInput.length == 1 && Integer.parseInt(userInput[0]) == -1) {
                return null;
            }

            if (in.trim().isEmpty()) {
                isInputWrong = true;
                continue;
            }
            if (userInput.length < lengthMin || userInput.length > lengthMax) {
                System.out.println("Erreur de longueur du contenu d'entrée! Veuillez ressaisir les données.");
                isInputWrong = true;
                continue;
            }
            if (hasDuplicateElement(userInput)) {
                System.out.println("Vous avez entré un élément en double! Veuillez ressaisir les données.");
                isInputWrong = true;
                continue;
            }
            for (String s : userInput) {
                int tmp = Integer.parseInt(s);
                if (tmp < min || tmp > max) {
                    isInputWrong = true;
                    System.out.println("L'élément que vous avez entré est hors limites! Veuillez ressaisir les données.");
                    break;
                }
            }
        } while (isInputWrong);
        int[] ret = new int[userInput.length];
        for (int i = 0; i < userInput.length; i++) {
            ret[i] = Integer.parseInt(userInput[i]);
        }
        return ret;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public Zone[] getZones() {
        return zones;
    }

    public void setZones(Zone[] zones) {
        this.zones = zones;
    }

    public synchronized void game() {
        boolean gameOver = false;
        int cnt = 1;
        while (!gameOver) {
            System.out.println("========================================");
            System.out.println("            Tour " + cnt + " commence           ");
            System.out.println("========================================");
            //安排每个地点的人员
            for (Zone z : this.zones) {
                if (!z.isOccupied()) {
                    for (Player p : this.players) {
                        System.out.println(p.getGamer().name() + ":");
                        if (p.hasReservists() && cnt != 1) {
                            System.out.println("Entrez -1 pour indiquer que personne n'est choisie");
                            p.assignReservistsToZone(z,true);
                        }
                        if (p.hasStudents() && cnt == 1)
                            p.assignStudentsToZone(z);
                        else {
                            System.out.println("Entrez -1 pour indiquer que personne n'est choisie");
                            p.assignStudentsToZone(z,true);
                        }
                    }
                    System.out.println(z);
                }
            }
            //战斗
            boolean isDone = false;
            System.out.println("La bataille commence...");
            while (!isDone) {
                for (Zone z : this.zones) {
                    if (!z.isOccupied()) {
                        z.fight();
                        if (z.isDone()) {
                            isDone = true;
                            z.setOwner();
                        }
                    }
                }
            }

            // 打印当前场地信息
            for (Zone z : this.zones) {
                System.out.println("||||||||||||||||||||||||||");
                System.out.println("||||||||||||||||||||||||||");
                System.out.println("Informations sur le joueur actuel et le lieu: ");
                System.out.println(z);
            }

            // 回收胜利地区的人员
            System.out.println("++++++++++++++++++++++++++");
            System.out.println("++++++++++++++++++++++++++");
            for (Zone z : this.zones) {
                if (z.isDone() && !z.isOccupied()) {
                    Gamers g = z.getOwner();
                    this.players[g.ordinal()].addZoneNames(z.getZoneName());
                    ArrayList<Student> students = z.getStudents(g);
                    for (Student s : students) {
                        this.players[g.ordinal()].addStudents(s);
                    }
                    z.getStudents(g).clear();
                }
            }
            System.out.println("Récupérez l'étudiant de la zone de victoire...");
            System.out.println("++++++++++++++++++++++++++");
            System.out.println("++++++++++++++++++++++++++");

            // 打印玩家信息
            for (Player p : this.players) {
                System.out.println(p);
            }
            for (Zone z : this.zones) {
                if (z.isDone() && !z.isOccupied()) {
                    Gamers g = z.getOwner();
                    System.out.println("Joueur " + g.name() + " a obtenu " + z.getZoneName().name());
                }
            }
            System.out.println("++++++++++++++++++++++++++");
            System.out.println("++++++++++++++++++++++++++");
            //检测胜利
            for (Player p : this.players) {
                if (p.isWin()) {
                    System.out.println("Joueur " + p.getGamer().name() + " gagne!");
                    gameOver = true;
                    break;
                }
            }
            if (gameOver) {
                continue;
            }

            // 设置驻留人员
            for (Zone z : this.zones) {
                if (z.isDone() && !z.isOccupied()) {
                    Gamers g = z.getOwner();
                    Player p = this.players[g.ordinal()];
                    System.out.println(p);
                    p.assignGarrisonToZone(z);

                    z.setOccupied(true);
                }
            }
            cnt++;
        }
    }

    public static void main(String[] args){
        Zone[] zones = new Zone[5];
        for (int i = 0; i < ZoneName.values().length; i++) {
            zones[i] = new Zone(ZoneName.values()[i]);
            System.out.println(zones[i]);
        }
        ArrayList<Student> students = new ArrayList<>();
        for(int i=0; i<15;i++){
            Student student = new Student(new String("++"+i));
            student.setCategory(Category.ETUDIANT_ELITE);
            student.setReservist(false);
            student.setStrategy(Strategy.OFFENSIVE);
            student.setConstitution(5);
            student.setDexterite(4);
            student.setForce(10);
            student.setInitiative(2);
            students.add(student);
        }
        int k = 0;
        for(int i = 0;i<15;i+=3){
            students.get(i).setZoneName(ZoneName.values()[k]);
            students.get(i+1).setZoneName(ZoneName.values()[k]);
            students.get(i+2).setZoneName(ZoneName.values()[k++]);
        }
        ArrayList<Student> res = new ArrayList<>();
        for(int i=0; i<5;i++){
            Student student = new Student(new String("++"+i));
            student.setCategory(Category.ETUDIANT_ELITE);
            student.setReservist(true);
            student.setStrategy(Strategy.OFFENSIVE);
            student.setConstitution(5);
            student.setDexterite(4);
            student.setForce(10);
            student.setInitiative(2);
            res.add(student);
        }

        ArrayList<Student> studentsB = new ArrayList<>();
        for(int i=0; i<15;i++){
            Student student = new Student(new String("++"+i));
            student.setCategory(Category.ETUDIANT_ELITE);
            student.setReservist(false);
            student.setStrategy(Strategy.OFFENSIVE);
            student.setConstitution(5);
            student.setDexterite(4);
            student.setForce(10);
            student.setInitiative(2);
            studentsB.add(student);
        }
        k = 0;
        for(int i = 0;i<15;i+=3){
            studentsB.get(i).setZoneName(ZoneName.values()[k]);
            studentsB.get(i+1).setZoneName(ZoneName.values()[k]);
            studentsB.get(i+2).setZoneName(ZoneName.values()[k++]);
        }
        ArrayList<Student> resB = new ArrayList<>();
        for(int i=0; i<5;i++){
            Student student = new Student(new String("++"+i));
            student.setCategory(Category.ETUDIANT_ELITE);
            student.setReservist(true);
            student.setStrategy(Strategy.OFFENSIVE);
            student.setConstitution(5);
            student.setDexterite(4);
            student.setForce(10);
            student.setInitiative(2);
            resB.add(student);
        }

        Player playerA= new Player(Gamers.A);
        playerA.setReservists(res);
        playerA.setStudents(students);

        Player playerB = new Player(Gamers.B);
        playerB.setReservists(resB);
        playerB.setStudents(studentsB);

        MainProcess mainProcess = new MainProcess();
        mainProcess.setZones(zones);
        mainProcess.setPlayers(new Player[]{playerA,playerB});
        mainProcess.game();
    }

}

