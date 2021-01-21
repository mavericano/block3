package lab3_3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static Scanner in = new Scanner(System.in);

    static PrintWriter out = new PrintWriter(System.out);

    final static int MAX_MATRIX_ORDER = 15;
    final static int MIN_MATRIX_ORDER = 2;

    final static String OUTPUT_PATH_REQUEST = "Введите абсолютный путь к файлу (при возможности файл будет создан)";
    final static String INPUT_PATH_REQUEST = "Введите абсолютный путь к файлу";
    final static String OUTPUT_PATH_ERROR = "Файл не найден и не может быть создан. Введите корректный путь";
    final static String INPUT_PATH_ERROR = "Файл не найден. Введите корректный путь к файлу";
    final static String ORDER_INPUT_ERROR = "Введите натуральное число больше " + MIN_MATRIX_ORDER + " и меньше " + MAX_MATRIX_ORDER;
    final static String INVALID_FILE_MESSAGE = "Введённый вами файл содержит некорректные данные. Введите путь к файлу с корректными данными.";
    final static String GET_MATRIX_ORDER_MESSAGE = "Введите n";
    final static String CONSOLE_CHOICE = "Вы выбрали ввод из консоли.";
    static final String FILE_CHOICE = "Вы выбрали ввод из файла.";
    static final String INVALID_COMMAND = "Некорректная комманда";
    static final String INFO = "Расставить строки данной матрицы в порядке возрастания элементов побочной диагонали.";
    final static String ANSWER_DESCRIPTION = "Отсортированная матрица: ";
    final static char C = 'C';
    final static char F = 'F';
    final static String CHOOSE_INPUT_MESSAGE = "Выберите метод ввода (" + C + " для консоли, " + F + " для файла)";

    public static void printAnswer(int[][] answer){
        System.out.println(ANSWER_DESCRIPTION);
        out.println(ANSWER_DESCRIPTION);
        for (int j = 0; j < answer.length; j++){
            System.out.println(Arrays.toString(answer[j]));
            out.println(Arrays.toString(answer[j]));
        }
        out.flush();
        out.close();
    }
    
    public static void checkOutputFile(){
        in = new Scanner(System.in);
        boolean isInaccessible;
        String location;
        System.out.println(OUTPUT_PATH_REQUEST);
        do {
            isInaccessible = false;
            location = in.nextLine();
            try {
                out = new PrintWriter(new File(location));
            } catch (Exception e) {
                isInaccessible = true;
                System.out.println(OUTPUT_PATH_ERROR);
            }
        } while (isInaccessible);
    }

    public static int[][] sortMatrix(int[][] matrix ){
        int n = matrix.length;
        int [] diagonal = new int[n];
        int j, value;

        for (int i = 0; i < n; i++)
            diagonal[i] = matrix[i][n - i - 1];

        for (int i = 0; i < diagonal.length; i++) {
            j = i - 1;
            value = diagonal[i];
            int[] row = matrix[i];
            while ((j >=0) && (diagonal[j] > value)) {
                diagonal[j + 1] = diagonal[j];
                matrix[j + 1] = matrix[j];
                j--;

            }
            diagonal[j + 1] = value;
            matrix[j +1] = row;
        }

        return matrix;
    }

    public static int getElementFromConsole(int i, int j){
        int element = 0;
        boolean isIncorrect;
        Scanner tmp = new Scanner(System.in);

        do{
            isIncorrect = false;
            System.out.println("Введите элемент в " + (i + 1) + " строке и " + (j + 1) + " столбце из консоли ");
            try {
                element = Integer.parseInt(tmp.nextLine());
            } catch (NumberFormatException nfe) {
                System.out.println("Введите целое число");
                isIncorrect = true;
            }
        } while (isIncorrect);

        return element;
    }

    public static int [][] getMatrixFromFile(int n){ //@Deprecated
        int [][] matrix = new int[n][n];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++){
                try {
                    matrix[i][j] = Integer.parseInt(in.nextLine());
                } catch (Exception e) {
                    System.out.println("Элемент в " + (i + 1) + " строке и " + (j + 1) + " столбце введен неверно");
                    matrix[i][j] = getElementFromConsole(i, j);
                }
            }
        return matrix;
    }

    public static int [][] getMatrixFromConsole(int n){
        int [][] matrix = new int[n][n];

        String input;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++){
                do {
                    System.out.println("Введите элемент матрицы в строке " + (i + 1) + " и столбце " + (j + 1));
                    input = in.nextLine();
                } while (checkInputData(input, false));
                matrix[i][j] = Integer.parseInt(input);
            }
        return matrix;
    }

    public static boolean checkInputData(String inp, boolean isInRange){
        int n = 0;
        boolean isIncorrect = false;
        try {
            n = Integer.parseInt(inp);
        } catch (Exception e){
            isIncorrect = true;
        }
        if (isInRange) {
            if ((n < MIN_MATRIX_ORDER) || (n > MAX_MATRIX_ORDER)) {
                System.out.println(ORDER_INPUT_ERROR);
                isIncorrect = true;
            }
        }
        return isIncorrect;
    }

    public static void checkInputFile(){
        boolean isInaccessible;
        String location;
        System.out.println(INPUT_PATH_REQUEST);
        do {
            isInaccessible = false;
            //location = in.nextLine();
            in = new Scanner(System.in);
            location = in.nextLine();
            try {
                in = new Scanner(new File(location));
            } catch (FileNotFoundException fnfe) {
                isInaccessible = true;
                System.out.println(INPUT_PATH_ERROR);
            }
        } while (isInaccessible);
    }

    public static int getMatrixOrderFromConsole(){
        String n;

        do {
            System.out.println(GET_MATRIX_ORDER_MESSAGE);
            n = in.nextLine();
        } while (checkInputData(n, true));
        return Integer.parseInt(n);
    }

    public static int getMatrixOrderFromFile(){
        String n;
        boolean isIncorrect;

        do {
            checkInputFile();
            n = in.nextLine();
            isIncorrect = checkInputData(n, true);
            if (isIncorrect){
                System.out.println(INVALID_FILE_MESSAGE);
            }
        } while (isIncorrect);
        return Integer.parseInt(n);
    }

    public static char chooseInput(){
        System.out.println(CHOOSE_INPUT_MESSAGE);
        boolean isIncorrect;
        char chosenInput;
        do {
            isIncorrect = false;
            chosenInput = in.nextLine().charAt(0);
            switch (chosenInput) {
                case C:
                    System.out.println(CONSOLE_CHOICE);
                    break;
                case F:
                    System.out.println(FILE_CHOICE);
                    break;
                default:
                    System.out.println(INVALID_COMMAND);
                    isIncorrect = true;
            }
        } while (isIncorrect);
        return chosenInput;
    }

    public static void main(String[] args) {
        in = new Scanner(System.in);
        int n;
        int [][] matrix = null;
        System.out.println(INFO);
        switch (chooseInput()) {
            case C:
                n = getMatrixOrderFromConsole();
                matrix = getMatrixFromConsole(n);
                break;
            case F:
                n = getMatrixOrderFromFile();
                matrix = getMatrixFromFile(n);
                break;
        }
        checkOutputFile();
        printAnswer(sortMatrix(matrix));
    }
}