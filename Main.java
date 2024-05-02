import java.util.Random;
import java.util.Scanner;

        import java.util.Random;
        import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();
    private static final int WIN_COUNT = 3;
    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = 'O';
    private static final char DOT_EMPTY = '*';
    private static int fieldSizeX;
    private static int fieldSizeY;
    private static char[][] field;

    public static void main(String[] args) {
        while (true){
            initialize();
            printField();
            while (true){
                humanTurn();
                printField();
                if(checkState(DOT_HUMAN, "Вы победили!")){
                    break;
                }
                aiTurn();
                printField();
                if(checkState(DOT_AI, "Вы проиграли!")){
                    break;
                }
            }
            System.out.println("Вы желаете сыграть еще раз? Y - да:");
            if(!scanner.next().equalsIgnoreCase("Y")){
                break;
            }
        }
    }

    static void initialize(){
        fieldSizeX = 3;
        fieldSizeY = 3;
        field = new char[fieldSizeX][fieldSizeY];
        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                field[x][y] = DOT_EMPTY;
            }

        }
    }

    static void printField(){
        System.out.print("+");
        for (int x = 0; x < fieldSizeX; x++) {
            System.out.print("-" + (x + 1));
        }
        System.out.println("-");

        for (int x = 0; x < fieldSizeX; x++) {
            System.out.print((x + 1) + "|");
            for (int y = 0; y < fieldSizeY; y++) {
                System.out.print(field[x][y] + "|");
            }
            System.out.println();
        }

        for (int x = 0; x < fieldSizeX * 2 + 2 ; x++) {
            System.out.print("-");
        }
        System.out.println();
    }

    static void humanTurn(){
        int x;
        int y;
        do {
            System.out.println("Введите координаты хода Х и У через пробел:");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;

        }while (!isCellValid(x, y) || !isCellEmpty(x, y));
        field[x][y] = DOT_HUMAN;

    }

    static void aiTurn(){
        int x;
        int y;
        do {
            x = random.nextInt(fieldSizeX);
            y = random.nextInt(fieldSizeY);

        }while ( !isCellEmpty(x, y));
        field[x][y] = DOT_AI;

    }

    static boolean isCellEmpty(int x, int y){
        return field[x][y] == DOT_EMPTY;
    }

    static boolean isCellValid(int x, int y){
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

    static boolean checkDrow(){
        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                if (isCellEmpty(x, y)) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean checkWin(char dot){
        if(field[0][0] == dot && field[0][1] == dot && field[0][2] == dot) return true;
        if(field[1][0] == dot && field[1][1] == dot && field[1][2] == dot) return true;
        if(field[2][0] == dot && field[2][1] == dot && field[2][2] == dot) return true;

        if(field[0][0] == dot && field[1][0] == dot && field[2][0] == dot) return true;
        if(field[0][1] == dot && field[1][1] == dot && field[2][1] == dot) return true;
        if(field[0][2] == dot && field[1][2] == dot && field[2][2] == dot) return true;

        if(field[0][0] == dot && field[1][1] == dot && field[2][2] == dot) return true;
        if(field[0][2] == dot && field[1][1] == dot && field[2][0] == dot) return true;

        return false;
    }

    static boolean checkState(char dot, String s){
        if(checkWin(dot)){
            System.out.println(s);
            return true;
        }
        if(checkDrow()){
            System.out.println("Ничья!");
            return true;
        }
        return false;
    }
}