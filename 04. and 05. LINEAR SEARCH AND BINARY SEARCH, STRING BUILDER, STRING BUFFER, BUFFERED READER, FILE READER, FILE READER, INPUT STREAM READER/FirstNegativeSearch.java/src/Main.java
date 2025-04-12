class FirstNegativeSearch {
        public static int findFirstNegative(int[] arr) {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] < 0) {
                    return i; // Return index of first negative number
                }
            }
            return -1; // No negative number found
        }

        public static void main(String[] args) {
            int[] numbers = {12, 7, 0, -5, 22, -3};
            int index = findFirstNegative(numbers);

            if (index != -1) {
                System.out.println("First negative number found at index: " + index);
            } else {
                System.out.println("No negative number found in the array.");
            }
        }
    }