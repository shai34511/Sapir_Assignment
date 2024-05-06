package Part2;

import java.util.ArrayList;
import java.util.Random;

public class SelfGame extends Game {

    private SelfPlayer a;
    private SelfPlayer b;

    public SelfGame() {

    }

    public void setA(SelfPlayer a) {
        this.a = a;
    }

    public void setB(SelfPlayer b) {
        this.b = b;
    }


    public synchronized void pcMakeMove() throws InterruptedException { // a function that making a move form the pc based of a boolean
        while (Main.flag) {
            wait();
        }
        Main.flag = true;
        if ((!Main.xPlayed && a.getType() == 'x') || (!Main.yPlayed && b.getType() == 'o')) {  // another condition to check if the correct player is trying to plAY
            ArrayList<Cell> c = getFreeCells();
            Random rnd = new Random();
            int rnd1 = (int) (Math.random() * c.size());
            Cell cell = new Cell();
            cell.setX(c.get(rnd1).getX());
            cell.setY(c.get(rnd1).getY());
            if((!Main.xPlayed && a.getType() == 'x')){  // making the move
                cell.setValue('x');
            } else {
                cell.setValue('o');
            }
            if (a.getType() == getTurn()) {   // flipping the turn and the security booleans
                Main.xPlayed = true;
                Main.yPlayed = false;
            } else {
                Main.yPlayed = true;
                Main.xPlayed = false;
            }

            if(cell.getValue() != ' ') {
                Main.g.setCellOnGameBoard(cell.getX(), cell.getY(), cell);
                System.out.println(Main.g.printBoard());
                if(Main.g.gameOver()){    // checking whether the game is over, if so, announcing the winner and exiting the program
                    System.exit(0);
                }
            }

        }
        Main.flag = false;  // flipping the main boolean key
    }


}
