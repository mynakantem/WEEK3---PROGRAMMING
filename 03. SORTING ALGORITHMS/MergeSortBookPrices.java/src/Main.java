class MergeSortBookPrices {

    public static void merge(int[] arr, int left, int right) {
        if (left == right) {
            return;
        }

        int mid = (left + right) / 2;

        merge(arr, left, mid);
        merge(arr, mid + 1, right);

        mergeSortedArrays(arr, left, mid, right);
    }

    private static void mergeSortedArrays(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        System.arraycopy(arr, left, leftArr, 0, n1);
        System.arraycopy(arr, mid + 1, rightArr, 0, n2);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }

    public static void printArray(int[] arr) {
        for (int price : arr) {
            System.out.print(price + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] bookPrices = {250, 400, 150, 320, 180, 520};

        System.out.println("Original Book Prices:");
        printArray(bookPrices);

        merge(bookPrices, 0, bookPrices.length - 1);

        System.out.println("Sorted Book Prices:");
        printArray(bookPrices);
    }
}
