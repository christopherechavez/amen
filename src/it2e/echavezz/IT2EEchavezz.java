
package it2e.echavezz;
import java.util.Scanner;
public class IT2EEchavezz {

    
    public void addAthlete(){
        Scanner sc = new Scanner(System.in);
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
  

        String sql = "INSERT INTO tbl_athlete (a_fname, a_lname, a_email, a_position, a_status) VALUES (?, ?, ?, ?, ? )";

        
        conf.addRecord(sql, fname, lname, email, position, status);


    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. ADD");
        System.out.println("2. VIEW");
        System.out.println("3. UPDATE");
        System.out.println("4. DELETE");
        System.out.println("5. EXIT");
        
        System.out.println("Enter action: ");
        int action = sc.nextInt();
        
        switch (action) {
            case 1:
            IT2EEchavezz test = new IT2EEchavezz();
            test.addAthlete();
            break;
            
        }
        
    }
    
}
