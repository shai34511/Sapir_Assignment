//shai susana 208067950, yinon levy 312219512, ziv rahamim 311303622
package Part2;

import java.util.ArrayList;
import java.util.Random;

public class UserGame extends Game {

    private SelfPlayer a1;
    private UserPlayer b1;

    public void setA1(SelfPlayer a1) {
        this.a1 = a1;
    }

    public void setB1(UserPlayer b1) {
        this.b1 = b1;
    }

    public synchronized void pcMakeMove() throws InterruptedException {  // function with the same purpose of the last pcMakeMove, but with tweaks for the player vs pc mode
        while (Main.flag) {
            wait();
        }
        Main.flag = true;
        ArrayList<Cell> c = getFreeCells();
        Random rnd = new Random();
        int rnd1 = (int) (Math.random() * c.size());
        Cell cell = new Cell();
        cell.setX(c.get(rnd1).getX());
        cell.setY(c.get(rnd1).getY());
        if (Main.p1Played) {
            cell.setValue(a1.getType());
        }
        if (a1.getType() == getTurn()) {
            Main.p1Played = true;
            Main.pcPlayed = false;
        } else {
            Main.pcPlayed = true;
            Main.p1Played = false;
        }

        if (cell.getValue() != ' ') {
            Main.g2.setCellOnGameBoard(cell.getX(), cell.getY(), cell);
            System.out.println(Main.g2.printBoard());
            if (Main.g2.gameOver()) {
                System.out.println("O won");
                System.exit(0);
            }
        }
        Main.flag = false;

    }

    public synchronized void userMakeMove() throws InterruptedException {  // a function that demonstrates the moves made by the user
        while (Main.flag) {
            wait();
        }
        Main.flag = true;
        Main.g2.printUserBoard();
        System.out.println("Please choose where to place your mark (by indexes");  // entering the desired coordinates we wanna place our move
        System.out.println("Enter the row you'd like to place in");
        int row = Main.s.nextInt();
        System.out.println("Please enter the column you'd like to place in ");
        int col = Main.s.nextInt();

        while (Main.g2.getGameBoard()[row][col].getValue() != ' ' && row >= 0 && row <= 2 && col >= 0 && col <= 2) {  // checking whether its avilable
            System.out.println("wrong input, please enter again");
            System.out.println("Enter the row you'd like to place in");
            row = Main.s.nextInt();
            System.out.println("Please enter the column you'd like to place in ");
            col = Main.s.nextInt();
        }

        Cell cell = new Cell(row, col);
        cell.setValue(b1.getType());
        Main.g2.setCellOnGameBoard(row, col, cell); // setting the cell to the correct player sign
        Main.p1Played = true;
        Main.pcPlayed = false;  // flipping the security booleans
        if (cell.getValue() != ' ') {
            Main.g2.setCellOnGameBoard(cell.getX(), cell.getY(), cell);
            System.out.println(Main.g2.printBoard());
            Main.flag = false;  // flipping the last security boolean that releases the sync
            if (gameOver()) {  // checking whether the game is over
                System.out.println("X won");
                System.exit(0);
            }
        }
    }
}