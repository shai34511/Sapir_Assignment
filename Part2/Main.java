package Part2;

import java.util.Scanner;


public class Main {

    public static Scanner s = new Scanner(System.in);
    static SelfGame g = new SelfGame();
    static UserGame g2 = new UserGame();
    public static boolean xPlayed = false, yPlayed = false, flag = false, pcPlayed = false, p1Played = false, PCvPC = false;


    public static void main(String[] args) {


        System.out.println("for PC vs PC press 1, \nfor Part2.Player vs PC press 2");   // the main, where we can choose our desired game mode
        int t = s.nextInt();
        if (t == 1) {
            SelfPlayer self1 = new SelfPlayer('x');
            SelfPlayer self2 = new SelfPlayer('o');
            Main.g.setA(self1);
            Main.g.setB(self2);
            PCvPC =true;
            self1.start();
            self2.start();
        } else {
            SelfPlayer vsUser = new SelfPlayer('o');
            UserPlayer user1 = new UserPlayer('x');
            g2.initializeGame();
            System.out.println(g2.printUserBoard());
            Main.g2.setA1(vsUser);
            Main.g2.setB1(user1);
            user1.start();
            vsUser.start();

        }
    }
}
