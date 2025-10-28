package medium.data_structure;

import java.util.HashSet;
import java.util.Scanner;

public class HashSetVerifyPairs {

    /**
     * Sample input
     * 5
     * john tom
     * john mary
     * john tom
     * mary anna
     * mary anna
     *
     * Sample Output
     * 1
     * 2
     * 2
     * 3
     * 3
     *
     *Explanation
     *
     * After taking the first input, you have only one pair: (john,tom)
     * After taking the second input, you have two pairs: (john, tom) and (john, mary)
     * After taking the third input, you still have two unique pairs.
     * After taking the fourth input, you have three unique pairs: (john,tom), (john, mary) and (mary, anna)
     * After taking the fifth input, you still have three unique pairs.
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        String [] pair_left = new String[t];
        String [] pair_right = new String[t];

        for (int i = 0; i < t; i++) {
            pair_left[i] = sc.next();
            pair_right[i] = sc.next();
        }

        HashSet<String> pairs = new HashSet<>();
        for (int i = 0; i < t; i++) {
            String combined = pair_left[i] + " " + pair_right[i];
            pairs.add(combined);
            System.out.println(pairs.size());
        }

    }
}
