package it2e.echavezz;

import java.util.Scanner;

public class IT2EEchavezz {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        Athlete a = new Athlete();
        Sport s = new Sport();
        Event e = new Event();
        
        String resp;
        
        do{
            System.out.println("Sports Event Registration System");
            System.out.println("1. ATHLETE");
            System.out.println("2. SPORT");
            System.out.println("3. EVENT");
            System.out.println("4. EXIT");
            
            System.out.print("Enter action: ");
            int action = conf.validateInt();
            
            switch(action){
                case 1:
                    a.athlete();
                    break;
                case 2:
                    s.sport();
                    break;
                case 3:
                    e.event();
                    break;
                case 4:
                    break;
                default:
                System.out.println("Invalid action. Please try again.");
            }
            
            System.out.println("Exiting...");
            System.out.print("Continue? (yes/no): ");
            resp = sc.next();
                    
        } while(resp.equalsIgnoreCase("yes"));
    }
}