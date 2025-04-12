import java.util.Arrays;
import java.util.Random;

class SearchComparison {

    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) return i;
        }
        return -1;
    }

    public static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) return mid;
            else if (arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    public static int[] generateRandomArray(int size, int bound) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(bound);
        }
        return arr;
    }

    public static void measureSearchPerformance(int size) {
        int[] arr = generateRandomArray(size, size * 10);
        int target = arr[size - 1];

        long start = System.nanoTime();
        linearSearch(arr, target);
        long end = System.nanoTime();
        double linearTimeMs = (end - start) / 1_000_000.0;

        Arrays.sort(arr);
        start = System.nanoTime();
        binarySearch(arr, target);
        end = System.nanoTime();
        double binaryTimeMs = (end - start) / 1_000_000.0;

        System.out.printf("Dataset Size: %,d%n", size);
        System.out.printf("Linear Search Time: %.4f ms%n", linearTimeMs);
        System.out.printf("Binary Search Time (after sort): %.4f ms%n", binaryTimeMs);
        System.out.println("-----------------------------------------");
    }

    public static void main(String[] args) {
        int[] sizes = {1000, 10000, 1000000};

        for (int size : sizes) {
            measureSearchPerformance(size);
        }
    }
}
