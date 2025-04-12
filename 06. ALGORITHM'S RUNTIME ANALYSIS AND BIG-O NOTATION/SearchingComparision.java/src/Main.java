import java.util.*;

class SearchingComparison {

    public static void main(String[] args) {
        int[] sizes = {1_000, 100_000, 1_000_000};
        Random rand = new Random();

        for (int size : sizes) {
            System.out.println("🔍 Testing with Dataset Size: " + size);

            int[] array = new int[size];
            HashSet<Integer> hashSet = new HashSet<>();
            TreeSet<Integer> treeSet = new TreeSet<>();

            for (int i = 0; i < size; i++) {
                array[i] = i;
                hashSet.add(i);
                treeSet.add(i);
            }

            int target = rand.nextInt(size);

            long startArray = System.nanoTime();
            boolean foundInArray = false;
            for (int val : array) {
                if (val == target) {
                    foundInArray = true;
                    break;
                }
            }
            long endArray = System.nanoTime();

            long startHash = System.nanoTime();
            boolean foundInHash = hashSet.contains(target);
            long endHash = System.nanoTime();

            long startTree = System.nanoTime();
            boolean foundInTree = treeSet.contains(target);
            long endTree = System.nanoTime();

            System.out.printf("Array Search     : Found = %s | Time = %.4f ms%n", foundInArray, (endArray - startArray) / 1_000_000.0);
            System.out.printf("HashSet Search   : Found = %s | Time = %.4f ms%n", foundInHash, (endHash - startHash) / 1_000_000.0);
            System.out.printf("TreeSet Search   : Found = %s | Time = %.4f ms%n", foundInTree, (endTree - startTree) / 1_000_000.0);
            System.out.println("--------------------------------------------------\n");
        }
    }
}
