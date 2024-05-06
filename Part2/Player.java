package Part2;

public abstract class Player extends Thread {

    private final char type;

    public Player(char type) {
        this.type = type;
    }


    public char getType() {
        return type;
    }
}
