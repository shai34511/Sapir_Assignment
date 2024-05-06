package Part2;

public class Cell {

    private int x;
    private int y;

    private char value;

    public Cell (int x, int y){
        this.x = x;
        this.y = y;
        this.value = ' ';
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public Cell() {
        this.value = ' ';
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Part2.Cell{" +
                "x=" + x +
                ", y=" + y +
                ", value=" + value +
                '}';
    }
}
