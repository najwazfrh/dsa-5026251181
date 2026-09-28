package lw02.unguided;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LinkedList<String[]> requests = new LinkedList<>();
        LinkedList<String[]> books = new LinkedList<>();
        LinkedList<String[]> members = new LinkedList<>();
        LinkedList<String[]> success = new LinkedList<>();

        Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> failed = new Stack<>();

        books.add(new String[]{"Kalkulus", "2"});
        books.add(new String[]{"Fisika", "1"});
        books.add(new String[]{"Statistika", "2"});

        Scanner scanner = new Scanner(Main.class.getResourceAsStream("borrowing.txt"));
        
        while (scanner.hasNext()) {
            String[] req = new String[2];
            req[0] = scanner.next();
            req[1] = scanner.next();
            requests.add(req);

            boolean isMember = false;
            for (String[] member : members) {
                if (member[0].equals(req[0])) {
                    isMember = true;
                    break;
                }
            }

            if(!isMember) {
                String[] newMember = new String[]{req[0], "0"};
                members.add(newMember);
            }
        }

        scanner.close();

        for (String[] req : requests) {
            queue.add(req);
        }

        while(!queue.isEmpty()) {
            String[] req = queue.poll();

            String name = req[0];
            String title = req[1];

            String[] member = null;
            String[] book = null;

            for (String[] m : members) {
                if (m[0].equals(name)) {
                    member = m;
                    break;
                }
            }

            for (String[] b : books) {
                if (b[0].equals(title)) {
                    book = b;
                    break;
                }
            }

            int stock = Integer.parseInt(book[1]);
            int borrowed = Integer.parseInt(member[1]);

            if (stock > 0 && borrowed < 2) {
                stock -= 1;
                borrowed += 1;
                book[1] = String.valueOf(stock);
                member[1] = String.valueOf(borrowed);
                success.add(req);
            } else {
                failed.push(req);
            }
        }
        
        System.out.println("\n=== Successfully Processed Requests ===");
        for (String[] req : success) {
            System.out.println(req[0] + " " + req[1]);
        }

        System.out.println("\n=== Remaining Book Stock ===");
        for (String[] book : books) {
            System.out.println(book[0] + " : " + book[1]);
        }

        System.out.println("\n=== Failed Requests ===");
        while (!failed.isEmpty()) {
            String[] failedReq = failed.pop(); 
            System.out.println(failedReq[0] + " " + failedReq[1]);
        }
    }
}