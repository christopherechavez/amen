package it2e.echavezz;

import implement.tbl_Sport;

public class Sport {
    public void sport(){
        tbl_Sport s = new tbl_Sport();
        config conf = new config();
        
        boolean isSelected = false;
        
        do{
            s.viewSport();
           
            System.out.println("1. ADD");
            System.out.println("2. UPDATE");
            System.out.println("3. DELETE");
            System.out.println("4. EXIT");

            System.out.print("Enter action: ");
            int action = conf.validateInt();
            
            switch (action) {
                case 1:
                    s.addSport();
                    break;
                case 2:
                    s.viewSport();
                    s.editSport();
                    break;
                case 3:
                    s.viewSport();
                    s.deleteSport();
                    break;
                case 4:
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
