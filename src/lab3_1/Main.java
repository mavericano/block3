package lab3_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    static Scanner in;
    static  PrintWriter out;

    public static void printAnswer(int ans){
        System.out.println("Индекс последнего вхождения данной подстроки в данную строку равен " + ans);
        out.println("Индекс последнего вхождения данной подстроки в данную строку равен " + ans);
        out.close();
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

    public static int find(String str, String subStr){
        int ps;
        ps = str.lastIndexOf(subStr);
        return ps;
    }

    public static String getStringFromConsole(){
        String input;
        System.out.println("Введите строку");
        input = in.nextLine();
        return input;
    }

    public static String getStringFromFile(){
        String input;
        input = in.nextLine();
        return input;
    }

    public static boolean checkIfValid(){
        boolean ans = in.hasNext();
        in.reset();
        if (!ans) {
            System.out.println("Файл должен содержать две строки");
        }
        return ans;
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
            if (isInaccessible) {
                isInaccessible = !checkIfValid();
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
        String str = "";
        String subStr = "";
        int position;

        System.out.println("Определить номер позиции последнего вхождения строки st1 в строку st2.\nЕсли такого нет, возвратить 0.");
        switch (chooseInput()) {
            case 'C':
                str = getStringFromConsole();
                subStr = getStringFromConsole();
                break;
            case 'F':
                checkInputFile();
                str = getStringFromFile();
                subStr = getStringFromFile();
                break;
        }
        position = find(str, subStr);
        checkOutputFile();
        printAnswer(position);
    }
}
