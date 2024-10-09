package it2e.echavezz;

import java.util.Scanner;

public class IT2EEchavezz {

    public void addAthlete() {
        try (Scanner sc = new Scanner(System.in)) {
            config conf = new config();
            System.out.println("Sport");
            String sport = sc.next();
            System.out.print("First Name: ");
            String fname = sc.next();
            System.out.print("Last Name: ");
            String lname = sc.next();
            System.out.print("Email: ");
            String email = sc.next();
            System.out.print("Position: ");
            String position = sc.next();
            System.out.println("Status: ");
            String status = sc.next();

            String sql = "INSERT INTO tbl_Athlete ( c_sport, c_fname, c_lname, c_email, c_position, c_status) VALUES (?, ?, ?, ?, ?, ?)";
            conf.addRecord(sql, sport, fname, lname, email, position, status);
        }
    }
    
    static String resp;
    config conf = new config();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        

        do {
            System.out.println("1. ADD");
            System.out.println("2. VIEW");
            System.out.println("3. UPDATE");
            System.out.println("4. DELETE");
            System.out.println("5. EXIT");

            System.out.print("Enter action: ");
            int action = sc.nextInt();
            IT2EEchavezz test = new IT2EEchavezz(); 

            switch (action) {
                case 1:
                    test.addAthlete();
                    break;
                case 2:
                    test.viewAthlete(); 
                    break;
                case 3:
                    test.updateAthlete();
                    break;
                case 4:
                    test.viewAthlete(); 
                    test.deleteAthlete(); 
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid action. Please try again.");
            }

            System.out.print("Continue? (yes/no): ");
            resp = sc.next();

        } while (resp.equalsIgnoreCase("yes"));

        System.out.println("Thank You!");
        sc.close();
    }

   
    public void viewAthlete() {
        String Query = "SELECT * FROM tbl_Athlete";
        String[] Hdr = {"ID", "SPORT", "First Name", "Last Name", "Email", "Position", "Status"};
        String[] Clmn = {"c_athlete", "c_sport", "c_fname", "c_lname", "c_email", "c_position", "c_status"};
        
        conf.viewRecords(Query, Hdr, Clmn);
    }

    public void updateAthlete() {
        
    }

    public void deleteAthlete() {
       
    }

    public void viewCAthlete() {
        
    }
}