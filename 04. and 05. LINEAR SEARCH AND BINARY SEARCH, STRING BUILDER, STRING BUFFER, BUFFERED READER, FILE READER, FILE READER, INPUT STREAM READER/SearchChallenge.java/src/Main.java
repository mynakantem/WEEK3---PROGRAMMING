import java.util.Arrays;

class SearchChallenge {

    public static int findFirstMissingPositive(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            if (arr[i] <= 0 || arr[i] > n) {
                arr[i] = n + 1;
            }
        }

        for (int i = 0; i < n; i++) {
            int num = Math.abs(arr[i]);
            if (num <= n && arr[num - 1] > 0) {
                arr[num - 1] = -arr[num - 1];
            }
        }

        for (int i = 0; i < n; i++) {
            if (arr[i] > 0) {
                return i + 1;
            }
        }

        return n + 1;
    }

    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {3, 4, -1, 1};

        int firstMissingPositive = findFirstMissingPositive(arr);
        System.out.println("The first missing positive integer is: " + firstMissingPositive);

        Arrays.sort(arr);

        int target = 4;
        int index = binarySearch(arr, target);
        if (index != -1) {
            System.out.println("The target " + target + " is found at index: " + index);
        } else {
            System.out.println("The target " + target + " is not found in the array.");
        }
    }
}
