package Part2;

import java.util.ArrayList;

public abstract class Game {

    private Cell[][] gameBoard;

    public Game() {
        gameBoard = new Cell[3][3];
    }

    public boolean gameOver() {   // a function that checks if the game is over

        if (getSpecCell(0, 0) == getSpecCell(0, 1) && getSpecCell(0, 0) == getSpecCell(0, 2) && getSpecCell(0, 0) != ' ') {
            return true;
        }
        if (getSpecCell(1, 0) == getSpecCell(1, 1) && getSpecCell(1, 0) == getSpecCell(1, 2) && getSpecCell(1, 0) != ' ') {
            return true;
        }
        if (getSpecCell(2, 0) == getSpecCell(2, 1) && getSpecCell(2, 0) == getSpecCell(2, 2) && getSpecCell(2, 0) != ' ') {
            return true;
        }
        if (getSpecCell(0, 2) == getSpecCell(1, 1) && getSpecCell(0, 2) == getSpecCell(2, 0) && getSpecCell(2, 0) != ' ') {
            return true;
        }
        if (getSpecCell(0, 0) == getSpecCell(1, 0) && getSpecCell(1, 0) == getSpecCell(2, 0) && getSpecCell(0, 0) != ' ') {
            return true;
        }
        if (getSpecCell(0, 1) == getSpecCell(1, 1) && getSpecCell(0, 1) == getSpecCell(2, 1) && getSpecCell(0, 1) != ' ') {
            return true;
        }
        if (getSpecCell(0, 2) == getSpecCell(1, 2) && getSpecCell(0, 2) == getSpecCell(2, 2) && getSpecCell(0, 2) != ' ') {
            return true;
        }
        if (getSpecCell(0, 0) == getSpecCell(1, 1) && getSpecCell(0, 0) == getSpecCell(2, 2) && getSpecCell(0, 0) != ' ') {
            return true;
        }
        if (getFreeCells().size() == 0) {
            System.out.println("Board is full g2");
            return true;
        }
        return false;
    }

    public char getSpecCell(int x, int y){
        return gameBoard[x][y].getValue();
    }   // getter into a specific cell based off coordinates
    public String printBoard() {  // function that prints the board
        String s = "";
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                s +="["+ gameBoard[i][j].getValue()+ "] ";
            }
            s+="\n";
        }
        return s;
    }

    public String printUserBoard() {  // printing the board with coordinates in order to help the user choose his next move
        String s = "";
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                s +=   "[" + i + "]" + "[" + j + "] ";
                if(j!=2){
                    s+= " || ";
                }
            }
            s+="\n";
        }
        return s;
    }

    public ArrayList<Cell> getFreeCells() {           // a function that returns all of the avilable cells of the board
        ArrayList<Cell> freeCells = new ArrayList();
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                if (gameBoard[i][j].getValue() == ' ') {
                    Cell c = new Cell(i, j);
                    freeCells.add(c);
                }
            }
        }
        return freeCells;
    }

    public void initializeGame() {                   // a function that initializes the game
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                Cell c = new Cell();
                c.setX(i);
                c.setY(j);
                gameBoard[i][j] = c;
            }
        }
    }
    public char getTurn() {  // a funtion that determines whos turn is it
        if (Main.xPlayed) {
            return 'o';
        } else  {

            return 'x';
        }

    }
    public char getTurnPlayer(){ // a function that determines whos turn it is based of the player vs pc mode
        if (Main.p1Played) {
            return 'o';
        } else  {

            return 'x';
        }
    }

    public Cell[][] getGameBoard() {
        return gameBoard;
    }


    public void setCellOnGameBoard(int x, int y, Cell c) {

        gameBoard[x][y] = c;
    }
}
