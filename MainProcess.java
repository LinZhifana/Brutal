package game;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;

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

    static int[] checkInput(int lengthMin, int lengthMax, int min, int max) {
        boolean isInputWrong;
        Scanner scanner = new Scanner(System.in);
        String[] userInput;
        do {
            String in = scanner.nextLine();
            userInput = in.split(" |,");
            isInputWrong = false;
            if (in.trim().isEmpty()) {
                isInputWrong = true;
                continue;
            }
            if (userInput.length < lengthMin || userInput.length > lengthMax) {
                System.out.println("输入长度错误");
                isInputWrong = true;
                continue;
            }
            if (hasDuplicateElement(userInput)) {
                System.out.println("有重复元素");
                isInputWrong = true;
                continue;
            }
            for (String s : userInput) {
                int tmp = Integer.parseInt(s);
                if (tmp < min || tmp > max) {
                    isInputWrong = true;
                    System.out.println("输入越界");
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

    public MainProcess() {
        //初始化场地
        for (int i = 0; i < ZoneName.values().length; i++) {
            zones[i] = new Zone(ZoneName.values()[i]);
        }
        //初始化玩家，分配学生，分配预备队员
        for (int i = 0; i < Gamers.values().length; i++) {
            StringBuffer str = new StringBuffer("Programme : \n");
            for (Programme p : Programme.values()) {
                str.append(p.name() + "(" + p.ordinal() + ") ");
            }
            System.out.println(str.toString());
            System.out.println("请输入你要选择的队伍 : ");
            int[] p = checkInput(1, 1, 0, Programme.values().length - 1);
            players[i] = new Player(Gamers.values()[i], Programme.values()[p[0]]);
            players[i].setReservists();
        }

    }

    public void game(){
        boolean gameOver = false;
        int cnt = 1;
        while(gameOver){
            System.out.println("第"+cnt+"回合开始");
            //安排每个地点的人员
            for (Zone z : this.zones) {
                if (!z.isOccupied()) {
                    for (Player p : this.players) {
                        if (p.hasStudents())
                            p.assignStudentsToZone(z);
                        if (p.hasReservists() && cnt!=1 )
                            p.assignReservistsToZone(z);
                    }
                }
            }
            //战斗
            boolean isDone = false;
            while (!isDone) {
                for (Zone z : this.zones) {
                    if(!z.isOccupied()){
                        z.fight();
                        isDone = z.isDone();
                    }
                }
            }
            // 回收胜利地区的人员
            for (Zone z : this.zones) {
                if (z.isDone()) {
                    Gamers g = z.whoWin();
                    this.players[g.ordinal()].addZoneNames(z.getZoneName());
                    ArrayList<Student> students = z.getStudents(g);
                    for (Student s : students) {
                        this.players[g.ordinal()].addStudents(s);
                    }
                    z.getStudents(g).clear();
                }
            }
            // 设置驻留人员
            for (Zone z : this.zones) {
                if (z.isDone() || !z.isOccupied()) {
                    Gamers g = z.whoWin();
                    this.players[g.ordinal()].assignStudentsToZone(z);
                    z.setOccupied();
                }
            }
            //检测胜利
            for (Player p : this.players){
                if (p.isWin()){
                    System.out.println(p.getGamer().name() + "胜利！");
                    gameOver = true;
                }
            }

            cnt++;
        }
    }

    public static void main(String[] args){
        MainProcess m = new MainProcess();
        m.game();
    }
}
