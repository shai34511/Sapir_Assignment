package Part2;

public class SelfPlayer extends Player {

    public SelfPlayer(char type) {
        super(type);
    }

    public char getType() {
        return super.getType();
    }

    public synchronized void run() {   // the run method that demonstrates the pc vs pc mode
        Main.g.initializeGame();  // initializing the game
        if (Main.PCvPC) {
            while (!Main.g.gameOver()) {  // every turn chekcing if the game is over
                try {
                    Main.g.pcMakeMove();  // making a move based of the player who's turn is to platy
                    Main.g.getTurn();
                    notifyAll();
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {

            while (!Main.g.gameOver()) {  // same as the above, but for the other player
                try {
                    if (Main.p1Played) {

                        Main.g2.pcMakeMove();
                        Main.g2.getTurnPlayer();
                    } else {
                        Thread.sleep(500);
                    }
                    notify();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}





