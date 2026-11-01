package lw04.prelab;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(Solution.class.getResourceAsStream("branches.txt"));

        int n = scanner.nextInt();
        int[] branches = new int[n];
        for (int i = 0; i < n; i++) {
            branches[i] = scanner.nextInt();
        }

        int m = scanner.nextInt();
        int[] targets = new int[m];
        for (int i = 0; i < m; i++) {
            targets[i] = scanner.nextInt();
        }
        scanner.close();

        int passes = 3;
        for (int i = 1; i <= passes && i < n; i++) {
            int key = branches[i];
            int j = i - 1;
            while (j >= 0 && branches[j] < key) {
                branches[j + 1] = branches[j];
                j--;
            }
            branches[j + 1] = key;
        }

        for (int i = 0; i < n; i++) {
            System.out.print(branches[i] + (i == n - 1 ? "" : " "));
        }
        System.out.println();

        for (int i = 0; i < m; i++) {
            int target = targets[i];
            int foundIndex = -1;

            for (int j = 0; j < n; j++) {
                if (branches[j] == target) {
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