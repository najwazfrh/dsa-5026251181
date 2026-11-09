package lw04.unguided;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(Solution.class.getResourceAsStream("cards.txt"));

        int n = scanner.nextInt();
        int[] cards = new int[n];
        for (int i = 0; i < n; i++) {
            cards[i] = scanner.nextInt();
        }

        int m = scanner.nextInt();
        int[] targets = new int[m];
        for (int i = 0; i < m; i++) {
            targets[i] = scanner.nextInt();
        }
        scanner.close();

        int passes = 2;
        for (int i = 0; i < passes && i < n - 1; i++) {
            int idx = i;
            for (int j = i + 1; j < n; j++) {
                if (cards[j] < cards[idx]) {
                    idx = j;
                }
            }
            int temp = cards[idx];
            cards[idx] = cards[i];
            cards[i] = temp;
        }

        for (int i = 0; i < n; i++) {
            System.out.print(cards[i] + (i == n - 1 ? "" : " "));
        }
        System.out.println();

        for (int i = 0; i < m; i++) {
            int target = targets[i];
            int foundIndex = -1;

            for (int j = 0; j < n; j++) {
                if (cards[j] == target) {
                    foundIndex = j;
                    break;
                }
            }

            if (foundIndex != -1) {
                System.out.println(target + ": FOUND at index " + foundIndex);
            } else {
                System.out.println(target + ": NOT FOUND");
            }
        }
    }
}