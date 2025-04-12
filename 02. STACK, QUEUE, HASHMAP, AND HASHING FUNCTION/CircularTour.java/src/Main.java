class CircularTour {

    public static int findStartingPoint(int[] petrol, int[] distance) {
        int n = petrol.length;
        int start = 0;
        int totalSurplus = 0;
        int currSurplus = 0;

        for (int i = 0; i < n; i++) {
            int gain = petrol[i] - distance[i];
            totalSurplus += gain;
            currSurplus += gain;

            if (currSurplus < 0) {
                // Can't start from this segment, move start to next pump
                start = i + 1;
                currSurplus = 0;
            }
        }

        return (totalSurplus >= 0) ? start : -1;
    }

    public static void main(String[] args) {
        int[] petrol = {4, 6, 7, 4};
        int[] distance = {6, 5, 3, 5};

        int startPump = findStartingPoint(petrol, distance);
        if (startPump != -1)
            System.out.println("Start at petrol pump: " + startPump);
        else
            System.out.println("No possible tour");
    }
}
