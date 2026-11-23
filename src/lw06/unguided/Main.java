package lw06.unguided;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner read = new Scanner(Main.class.getResourceAsStream("treenumbers.txt"));

        List<Integer> numbers = new ArrayList<>();
        while (read.hasNextInt()) {
            numbers.add(read.nextInt());
        }

        BinarySearchTree bst = new BinarySearchTree();
        int target = 0;

        if (!numbers.isEmpty()) {
            target = numbers.get(numbers.size() - 1);
            for (int i = 0; i < numbers.size() - 1; i++) {
                bst.insert(numbers.get(i));
            }
        }

        System.out.print("Preorder : ");
        bst.preOrder();

        System.out.print("Inorder : ");
        bst.inOrder();

        System.out.print("Postorder: ");
        bst.postOrder();

        System.out.println("Search " + target + ": " + bst.search(target));
        System.out.println("Minimum: " + bst.findMin());
        System.out.println("Maximum : " + bst.findMax());

        read.close();
    }
}