package lw02.prelab;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Scanner;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        LinkedList<String[]> transactions = new LinkedList<>();
        LinkedList<String[]> customers = new LinkedList<>();

        InputStream inputStream = Main.class.getResourceAsStream("transactions.txt");
        if (inputStream == null) {
            System.out.println("File transactions.txt tidak ditemukan!");
            return;
        }

        Scanner scanner = new Scanner(inputStream);

        while (scanner.hasNext()) {
            String name = scanner.next();
            String type = scanner.next();
            String amountStr = scanner.next();

            transactions.add(new String[]{name, type, amountStr});

            boolean customerExists = false;
            for (String[] cust : customers) {
                if (cust[0].equals(name)) {
                    customerExists = true;
                    break;
                }
            }

            if (!customerExists) {
                customers.add(new String[]{name, "0"});
            }
        }
        scanner.close();

        Queue<String[]> queue = new LinkedList<>(transactions);
        Stack<String[]> failedTransactions = new Stack<>();

        while (!queue.isEmpty()) {
            String[] trx = queue.poll();
            String name = trx[0];
            String type = trx[1];
            int amount = Integer.parseInt(trx[2]);

            for (String[] cust : customers) {
                if (cust[0].equals(name)) {
                    int currentBalance = Integer.parseInt(cust[1]);

                    if (type.equals("DEPOSIT")) {
                        cust[1] = String.valueOf(currentBalance + amount);
                    } else if (type.equals("WITHDRAW")) {
                        if (amount > currentBalance) {
                            failedTransactions.push(trx);
                        } else {
                            cust[1] = String.valueOf(currentBalance - amount);
                        }
                    }
                    break;
                }
            }
        }

        System.out.println("=== Final Balances ===");
        for (String[] cust : customers) {
            System.out.println(cust[0] + " : " + cust[1]);
        }

        System.out.println("=== Failed Transactions ===");
        while (!failedTransactions.isEmpty()) {
            String[] failed = failedTransactions.pop();
            System.out.println(failed[0] + " " + failed[1] + " " + failed[2]);
        }
    }
}