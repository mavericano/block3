import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int [] arr = {21, 8, 4, 17, 13, -96, 7, 9, 2, 5, -40, -97};

        System.out.println(Arrays.toString(Sorter.quickSort(arr, 0, arr.length - 1)));
    }

}

