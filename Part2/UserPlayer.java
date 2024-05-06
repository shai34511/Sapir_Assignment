package Part2;

public class UserPlayer extends Player {

    public UserPlayer(char type) {
        super(type);
    }

    public char getType() {
        return super.getType();
    }

    public synchronized void run() { // the run method in case we chose the player vs pc mode
        while (!Main.g2.gameOver()) {
            try {
                if (!Main.p1Played) {
                    Main.g2.userMakeMove();
                    Main.g2.getTurnPlayer();
                    notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}



