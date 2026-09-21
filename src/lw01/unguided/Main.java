package lw01.unguided;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(Main.class.getResourceAsStream("rentals.txt"));
        
        int count = scanner.nextInt();
        Rental[] rentals = new Rental[count];
        int[] unitsArray = new int[count];
        
        for (int i = 0; i < count; i++) {
            String type = scanner.next();
            String id = scanner.next();
            int days = scanner.nextInt();
            int units = scanner.nextInt();
            
            if (type.equals("LAPTOP")) {
                rentals[i] = new LaptopRental(id, days);
            } else if (type.equals("PROJECTOR")) {
                rentals[i] = new ProjectorRental(id, days);
            }
            
            unitsArray[i] = units;
        }
        scanner.close();
        
        for (int i = 0; i < count; i++) {
            String originalSummary = rentals[i].summary(); 
            int totalCharge = rentals[i].calculateCharge(unitsArray[i]); 
            
            String correctedSummary = originalSummary.replace(
                String.valueOf(rentals[i].calculateCharge()), 
                String.valueOf(totalCharge)
            );
            
            System.out.println(correctedSummary);
        }
    }
}