package it2e.echavezz;

import java.util.Scanner;

public class IT2EEchavezz {
    static Scanner sc = new Scanner(System.in);
    config conf = new config();

    public void addAthlete() {
        
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

    public static void main(String[] args) {
        
        String resp;
        
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
                    test.viewAthlete();
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
    }

   
    public void viewAthlete() {
        String Query = "SELECT * FROM tbl_Athlete";
        String[] Hdr = {"ID", "SPORT", "First Name", "Last Name", "Email", "Position", "Status"};
        String[] Clmn = {"c_athlete", "c_sport", "c_fname", "c_lname", "c_email", "c_position", "c_status"};
        
        conf.viewRecords(Query, Hdr, Clmn);
    }

    public void updateAthlete() {
        System.out.print("Select ID: ");
        int updateID = sc.nextInt();
        
        String sqlUpdate;
        
        System.out.print("Choose what to update: "
                + "\n1. Sport"
                + "\n2. First Name"
                + "\n3. Last Name"
                + "\n4. Email"
                + "\n5. Position"
                + "\n6. Status"
                + "Enter selection: ");
        int updateSelection = sc.nextInt();
        
        switch(updateSelection){
            case 1:
                System.out.print("Enter new Sport: ");
                sc.nextLine();
                String newSport = sc.nextLine();
                
                sqlUpdate = "UPDATE tbl_Athlete set c_sport = ? WHERE c_athlete = ?";
                conf.updateRecord(sqlUpdate, newSport, updateID);
                break;
            case 2:
                System.out.print("Enter new first name: ");
                String newFname = sc.next();
                
                sqlUpdate = "UPDATE tbl_Athlete set c_fname = ? WHERE c_athlete = ?";
                conf.updateRecord(sqlUpdate, newFname, updateID);
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            default:
                System.out.println("Invalid action. Please try again.");
        }
    }

    public void deleteAthlete() {
       System.out.print("Select ID: ");
        int deleteID = sc.nextInt();
        
        String sqlDelete = "DELETE FROM tbl_Athlete WHERE c_athlete = ?";
        conf.deleteRecord(sqlDelete, deleteID);
    }
}