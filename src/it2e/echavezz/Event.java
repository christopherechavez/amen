package it2e.echavezz;

import implement.tbl_Event;

public class Event {
    public void event(){
        tbl_Event e = new tbl_Event();
        config conf = new config();
        
        boolean isSelected = false;
        
        do{
            e.viewEvent();
           
            System.out.println("1. ADD");
            System.out.println("2. UPDATE");
            System.out.println("3. DELETE");
            System.out.println("4. SELECT");
            System.out.println("5. EXIT");

            System.out.print("Enter action: ");
            int action = conf.validateInt();
            
            switch (action) {
                case 1:
                    e.addEvent();
                    break;
                case 2:
                    e.viewEvent();
                    e.updateEvent();
                    break;
                case 3:
                    e.viewEvent();
                    e.deleteEvent();
                    break;
                case 4:
                    e.viewEvent();
                    e.viewSpecific();
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