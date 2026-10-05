package lw03.unguided;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Map<String, Integer> enrollmentMap = new HashMap<>();
        List<String> courseOrder = new ArrayList<>();
        List<String> checkResults = new ArrayList<>();
        
        int rejectedCount = 0;

        InputStream input = Main.class.getResourceAsStream("enrollment.txt");

        Scanner sc1 = new Scanner(input);

        while (sc1.hasNext()) {
            String command = sc1.next();

            if (command.equals("REGISTER")) {
                String courseCode = sc1.next();
                int count = sc1.nextInt();

                if (count <= 0) {
                    rejectedCount++;
                } else {
                    if (enrollmentMap.containsKey(courseCode)) {
                        int currentCount = enrollmentMap.get(courseCode);
                        enrollmentMap.put(courseCode, currentCount + count);
                    } else {
                        enrollmentMap.put(courseCode, count);
                        courseOrder.add(courseCode);
                    }
                }
            } else if (command.equals("WITHDRAW")) {
                String courseCode = sc1.next();
                int count = sc1.nextInt();

                if (count <= 0) {
                    rejectedCount++;
                } else if (!enrollmentMap.containsKey(courseCode)) {
                    rejectedCount++;
                } else {
                    int currentCount = enrollmentMap.get(courseCode);
                    if (currentCount >= count) {
                        enrollmentMap.put(courseCode, currentCount - count);
                    } else {
                        rejectedCount++;
                    }
                }
            } else if (command.equals("CHECK")) {
                String courseCode = sc1.next();

                if (enrollmentMap.containsKey(courseCode)) {
                    int currentCount = enrollmentMap.get(courseCode);
                    checkResults.add(courseCode + ": " + currentCount + " students");
                } else {
                    checkResults.add(courseCode + ": Not found");
                }
            }
        }

        sc1.close();

        System.out.println("===== Enrollment Checks =====");
        for (int i = 0; i < checkResults.size(); i++) {
            System.out.println(checkResults.get(i));
        }

        System.out.println();

        System.out.println("===== Final Enrollment =====");
        for (int i = 0; i < courseOrder.size(); i++) {
            String courseCode = courseOrder.get(i);
            int count = enrollmentMap.get(courseCode);
            System.out.println(courseCode + ": " + count + " students");
        }

        System.out.println();
        System.out.println("Rejected operations: " + rejectedCount);
    }
}