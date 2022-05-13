package tictactoe;
import java.util.Scanner;

public class Main {
    public static void printField(char[][] field) {
        //printing battlefield
        System.out.println("---------");
        for (int i = 0; i < field.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < field[i].length; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public static int findX(String nextTurn) {
        int nextX = 0;
        switch (nextTurn.charAt(0)) {
            case '1':
                nextX = 1;
                break;
            case '2':
                nextX = 2;
                break;
            case '3':
                nextX = 3;
                break;
            default:
                nextX = 0;
                break;
        }
        return nextX;
    }

    public static int findY(String nextTurn) {
        int nextY = 0;
        switch (nextTurn.charAt(2)) {
            case '1':
                nextY = 1;
                break;
            case '2':
                nextY = 2;
                break;
            case '3':
                nextY = 3;
                break;
            default:
                nextY = 0;
                break;
        }
        return nextY;
    }

    public static boolean areInRange(int nextX, int nextY) {
        boolean wrongInput = true;
        if ((nextX > 0 && nextX < 4) && (nextY > 0 && nextY < 4)) {
            wrongInput = false;
        } else {
            System.out.println("Coordinates should be from 1 to 3!");
        }
        return wrongInput;
    }

    public static boolean xWon(char[][] field) {
        boolean xWin = false;
        //checking if X wins in diagonally
        for (int i = 0; i < field.length; i++) {
            if (field[0][0] == 'X' && field[1][1] == 'X' && field[2][2] == 'X') {
                xWin = true;
            }
            if (field[0][2] == 'X' && field[1][1] == 'X' && field[2][0] == 'X') {
                xWin = true;
            }
        }
        //checking if X wins in a rows and columns
        for (int i = 0; i < field.length; i++) {
            if (field[i][0] == 'X' && field[i][1] == 'X' && field[i][2] == 'X') {
                xWin = true;
            }
            if (field[0][i] == 'X' && field[1][i] == 'X' && field[2][i] == 'X') {
                xWin = true;
            }
        }
        return xWin;
    }

    public static boolean oWon(char[][] field) {
        boolean oWin = false;
        //checking if X or O wins in diagonally
        for (int i = 0; i < field.length; i++) {
            if (field[0][0] == 'O' && field[1][1] == 'O' && field[2][2] == 'O') {
                oWin = true;
            }
            if (field[0][2] == 'O' && field[1][1] == 'O' && field[2][0] == 'O') {
                oWin = true;
            }
        }
        //checking if X or O wins in a rows and columns
        for (int i = 0; i < field.length; i++) {
            if (field[i][0] == 'O' && field[i][1] == 'O' && field[i][2] == 'O') {
                oWin = true;
            }
            if (field[0][i] == 'O' && field[1][i] == 'O' && field[2][i] == 'O') {
                oWin = true;
            }
        }
        return oWin;
    }

    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        boolean xWin = false;
        boolean oWin = false;
        char[][] field = new char[3][3]; //battle field
        int nextX = 1;
        int nextY = 1;
        String nextTurn = "";
        String gameState = "";

        //preparing initial battlefield array
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = '_';
            }
        }
        //printing initial battlefield
        printField(field);

        //game loop until win or draw
        boolean gameContinue = true;
        int selectXO = 0;
        while (gameContinue) {
            boolean wrongInput = true;
            while (wrongInput) {
                //asking for the move
                System.out.println("Enter the coordinates:");
                nextTurn = scanner.nextLine();
                //check if coordinates are numbers and are between 1 and 3 inclusive
                if (nextTurn.matches("\\d \\d")){
                    nextX = findX(nextTurn);
                    nextY = findY(nextTurn);
                    wrongInput = areInRange(nextX, nextY);
                } else {
                    System.out.println("You should enter numbers!");
                }
                if (!wrongInput) {
                    if (field[nextX - 1][nextY - 1] == 'X' || field[nextX - 1][nextY - 1] == 'O') {
                        System.out.println("This cell is occupied! Choose another one!");
                        wrongInput = true;
                    }
                }
            }
            if (selectXO % 2 == 0) {
                field[nextX - 1][nextY - 1] = 'X';
            } else {
                field[nextX - 1][nextY - 1] = 'O';
            }
            selectXO++;

            //printing new battlefield
            printField(field);

            //checking if X or O wins
            xWin = xWon(field);
            oWin = oWon(field);
            if (selectXO == 9) {
                gameState = "Draw";
                gameContinue = false;
            }
            if (xWin) {
                gameState = "X wins";
                gameContinue = false;
            }
            if (oWin) {
                gameState = "O wins";
                gameContinue = false;
            }
        }
        System.out.println(gameState);
    }
}
