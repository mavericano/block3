package lab3_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {

    static Scanner in;
    static PrintWriter out;
    static final int MIN_VALUE = 3;
    static final int MAX_VALUE = 1000000;

    public static void printAnswer(TreeSet<Integer> ans, int n){
        System.out.println("Множество простых чисел до " + n + " равно " + ans);
        out.println("Множество простых чисел до " + n + " равно " + ans);
        out.close();
    }

    public static TreeSet<Integer> findPrimes(int n){
        TreeSet<Integer> all = new TreeSet<>();
        TreeSet<Integer> primes = new TreeSet<>();

        for (int i = 2; i < n; i++){
            all.add(i);
        }

        for (int i = 2; i < n; i++){
            if (all.contains(i)) {
                primes.add(i);
                for(int j = i; j < n; j+= i){
                    all.remove(j);
                }
            }
        }
        return primes;
    }

    public static void checkOutputFile(){
        in = new Scanner(System.in);
        boolean isInaccessible;
        String location;
        System.out.println("Введите абсолютный путь к файлу (при возможности файл будет создан)");
        //C:\Users\haiku\Desktop\output4.txt
        do {
            isInaccessible = false;
            location = in.nextLine();
            try {
                out = new PrintWriter(new File(location));
            } catch (Exception e) {
                isInaccessible = true;
                System.out.println("Файл не найден и не может быть создан. Введите корректный путь");
            }
        } while (isInaccessible);
    }

    public static int getInputDataFromFile(){
        String n;
        boolean isIncorrect;

        do {
            checkInputFile();
            n = in.nextLine();
            isIncorrect = !checkInputData(n);
            if (isIncorrect){
                System.out.println("Введённый вами файл содержит некорректные данные. Введите путь к файлу с корректными данными.");
            }
        } while (isIncorrect);
        return Integer.parseInt(n);
    }

    public static boolean checkInputData(String inp){
        int n = 0;
        boolean isIncorrect = false;
        try {
            n = Integer.parseInt(inp);
        } catch (Exception e){
            isIncorrect = true;
        }
        if ((n < MIN_VALUE) || (n > MAX_VALUE)) {
            System.out.println("Введите натуральное число больше " + MIN_VALUE + " и меньше " + MAX_VALUE);
            isIncorrect = true;
        }
        return isIncorrect;
    }

    public static int getInputDataFromConsole(){
        String n;

        do {
            System.out.println("Введите n");
            n = in.nextLine();
        } while (checkInputData(n));
        return Integer.parseInt(n);
    }

    public static void checkInputFile(){
        boolean isInaccessible;
        String location;
        System.out.println("Введите абсолютный путь к файлу");
        do {
            isInaccessible = false;
            location = in.nextLine();
            location = in.nextLine();
            try {
                in = new Scanner(new File(location));
            } catch (FileNotFoundException fnfe) {
                isInaccessible = true;
                System.out.println("Файл не найден. Введите корректный путь к файлу");
            }
        } while (isInaccessible);
    }

    public static char chooseInput(){
        System.out.println("Выберите метод ввода (C для консоли, F для файла)");
        boolean isIncorrect;
        char chosenInput;
        do {
            isIncorrect = false;
            chosenInput = in.nextLine().charAt(0);
            switch (chosenInput) {
                case 'C':
                    System.out.println("Вы выбрали ввод из консоли.");
                    break;
                case 'F':
                    System.out.println("Вы выбрали ввод из файла.");
                    break;
                default:
                    System.out.println("Некорректная комманда");
                    isIncorrect = true;
            }
        } while (isIncorrect);
        return chosenInput;
    }

    public static void main(String[] args) {
        in = new Scanner(System.in);
        int n = 0;
        System.out.println("Uslovie");
        switch (chooseInput()) {
            case 'C':
                n = getInputDataFromConsole();
                break;
            case 'F':
                n = getInputDataFromFile();
                break;
        }
        checkOutputFile();
        printAnswer(findPrimes(n), n);
    }
}
