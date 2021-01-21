package lab3_3;

import java.util.Arrays;

public class Sort {
    public static void main(String[] args) {
        int[] array = {7, 6, 2, 4, 120, 10, 4};
        int value;
        int j;
        System.out.println(Arrays.toString(array));
        for (int i = 0; i < array.length; i++) {
            j = i - 1;
            value = array[i];
            while ((j >=0) && (array[j] > value)) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = value;
        }
        System.out.println(Arrays.toString(array));
    }
}
