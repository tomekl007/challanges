package complimentary;

import java.util.HashMap;

public class ComplimentaryPairs {

    public static int findKComplementary(int[] inputArray, int sum) {
        int count = 0;
        HashMap<Integer, Integer> occurrences = createMapWithOccurrences(inputArray);

        for (Integer key :  occurrences.keySet()) {
            int neededToBeComplementary = sum - key;
            if (occurrences.containsKey(neededToBeComplementary)) {
                count += occurrences.get(key) * occurrences.get(neededToBeComplementary);
            }
        }

        return count;
    }

    private static HashMap<Integer, Integer> createMapWithOccurrences(int[] inputArray) {
        HashMap<Integer, Integer> occurrences = new HashMap<>();
        for (int i : inputArray) {
            if (occurrences.get(i) == null) occurrences.put(i, 1);
            else occurrences.put(i, occurrences.get(i) + 1);
        }
        return occurrences;
    }
}
