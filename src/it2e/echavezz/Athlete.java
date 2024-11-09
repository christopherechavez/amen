package it2e.echavezz;

import implement.tbl_Athlete;
import java.util.Scanner;

public class Athlete {
    public void athlete(){
        Scanner sc = new Scanner(System.in);
        tbl_Athlete a = new tbl_Athlete();
        config conf = new config();
        
        boolean isSelected = false;
        
        do{
            a.viewAthlete();
           
            System.out.println("1. ADD");
            System.out.println("2. UPDATE");
            System.out.println("3. DELETE");
            System.out.println("4. SELECT");
            System.out.println("5. EXIT");

            System.out.print("Enter action: ");
            int action = conf.validateInt();
            
            switch (action) {
                case 1:
                    a.addAthlete();
                    break;
                case 2:
                    a.viewAthlete();
                    a.updateAthlete();
                    break;
                case 3:
                    a.viewAthlete(); 
                    a.deleteAthlete(); 
                    break;
                case 4:
                    a.viewAthlete();
                    a.viewSpecific();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    isSelected = true;
                    break;
                default:
                    System.out.println("Invalid action. Please try again.");
            }
        } while (!isSelected);

        System.out.println("Thank You!");
    }
}
