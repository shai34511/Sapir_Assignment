//shai susana 208067950, yinon levy 312219512, ziv rahamim 311303622
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
