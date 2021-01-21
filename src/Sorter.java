import java.util.Arrays;

public class Sorter {

    private static void swap(int[] array, int ind1, int ind2) {
        int tmp = array[ind1];
        array[ind1] = array[ind2];
        array[ind2] = tmp;
    }

    private static int getMax(int [] array){
        int max = array[0];
        for (int i = 1; i < array.length; i++){
            if (array[i] > max){
                max = array[i];
            }
        }
        return max;
    }

    public static int [] enhancedBubbleSort(int [] array) {
        int n = array.length;
        boolean needIteration = true;

        while (needIteration) {
            needIteration = false;
            for (int i = 1; i < array.length; i++) {
                if (array[i] < array[i - 1]) {
                    swap(array, i, i-1);
                    needIteration = true;
                }
            }
        }
        return array;
    }

    public static int [] selectionSort(int [] array) {
        for (int left = 0; left < array.length; left++) {
            int minInd = left;
            for (int i = left; i < array.length; i++) {
                if (array[i] < array[minInd]) {
                    minInd = i;
                }
            }
            swap(array, left, minInd);
        }
        return array;
    }

    public static int [] insertionSort(int [] array) {
        for (int i = 0; i < array.length; i++) {
            int j = i - 1;
            int value = array[i];
            while ((j >=0) && (array[j] > value)) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = value;
        }
        return array;
    }

    public static int [] shakerSort(int [] array) {
        int leftLimit = 0;
        int rightLimit = array.length - 1;

        while (leftLimit <= rightLimit) {
            for (int i = leftLimit; i < rightLimit; i++) {
                if (array[i] > array[i + 1]) {
                    swap(array, i + 1, i);
                }
            }
            rightLimit--;
            for (int i = rightLimit; i > leftLimit; i--) {
                if (array[i] < array[i - 1]) {
                    swap(array, i - 1, i);
                }
            }
            leftLimit++;
        }
        return array;
    }

    public static int [] radixSort(int [] array){
        int length = array.length;
        int digitPlace = 1;
        int result [] = new int [length];
        int largestNum = getMax(array);
        int counter = array.length - 1;

        while (largestNum / digitPlace > 0){
            int baskets [] = new int [10];
            for (int i = 0; i < 10; i++){
                baskets[i] = 0;
            }
            for (int i = 0; i < length; i++) {
                baskets[(array[i] / digitPlace) % 10]++;
            }
            for (int i = 1; i < 10; i++) {
                baskets[i] += baskets[i - 1];
            }
            for (int i = counter; i > -1; i--) {
                result[baskets[(array[i] / digitPlace) % 10 ] - 1] = array[i];
                baskets[(array[i] / digitPlace) % 10 ]--;
            }
            digitPlace += 10;
        }
        return array;
    }

    public static int [] quickSort(int [] array, int low, int high){
        if (array.length == 0)
            return array;

        if (low >= high)
            return array;

        int middle = low + (high - low) / 2;
        int pivot = array[middle];

        int i = low, j = high;
        while (i <= j) {
            while (array[i] < pivot) {
                i++;
            }

            while (array[j] > pivot) {
                j--;
            }

            if (i <= j) {
                swap(array, i, j);
                i++;
                j--;
            }
        }

        if (low < j)
            quickSort(array, low, j);

        if (high > i)
            quickSort(array, i, high);
        return array;
    }
    /*
    * MAIN FOR DEBUGGING *
    public static void main(String[] args) {
        int [] arr = {21, 8, 4, 17, 13, -96, 7, 9, 2, 5, -40, -97};
        //comment to test
        System.out.println(Arrays.toString(quickSort(arr, 0, arr.length - 1)));
    }*/

}
