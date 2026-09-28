package lw02.prelab;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LinkedList<String[]> transactions = new LinkedList<>();
        LinkedList<String[]> customers = new LinkedList<>();

        Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> failed = new Stack<>();

        Scanner scanner = new Scanner(Main.class.getResourceAsStream("transactions.txt"));

        while (scanner.hasNext()) {
            String[] transaction = new String[3];
            transaction[0] = scanner.next();
            transaction[1] = scanner.next();
            transaction[2] = scanner.next();
            transactions.add(transaction);
        }

        scanner.close();

        queue.addAll(transactions);

        while(!queue.isEmpty()) {
            String[] transaction = queue.poll();

            String name = transaction[0];
            String type = transaction[1];
            int amount = Integer.parseInt(transaction[2]);

            String[] customer = null;

            for(String[] data : customers) {
                if(data[0].equals(name)) {
                    customer = data;
                    break;
                }
            }

            if(customer == null) {
                customer = new String[]{name, "0"};
                customers.add(customer);
            }

            int balance = Integer.parseInt(customer[1]);

            if(type.equals("DEPOSIT")) {
                balance += amount;
                customer[1] = String.valueOf(balance);
            } else if(type.equals("WITHDRAW")) {
                if(amount <= balance) {
                    balance -= amount;
                    customer[1] = String.valueOf(balance);
                } else {
                    failed.push(transaction);  
                }
            }
        }

        System.out.println("\n=== Final Balances ===");
        for (String[] cust : customers) {
            System.out.println(cust[0] + " : " + cust[1]);
        }

        System.out.println("\n=== Failed Transactions ===");
        while (!failed.isEmpty()) {
            String[] failedTrx = failed.pop(); 
            System.out.println(failedTrx[0] + " " + failedTrx[1] + " " + failedTrx[2]);
        }
    }
}