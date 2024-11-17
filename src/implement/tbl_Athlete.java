package implement;

import it2e.echavezz.config;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class tbl_Athlete {
    Scanner sc = new Scanner(System.in);
    tbl_Sport s = new tbl_Sport();
    config conf = new config();
    
    public void addAthlete() {
            s.viewSport();
            System.out.print("Sport ID: ");
            int sport = conf.validateInt();
            
            while(conf.getSingleValue("SELECT s_id FROM tbl_Sport WHERE s_id = ?", sport) == 0){
            System.out.print("ID doesn't exist, try again: ");
            sport = conf.validateInt();
            }
            
            System.out.print("First Name: ");
            String fname = sc.next();
            System.out.print("Last Name: ");
            String lname = sc.next();
            System.out.print("Email: ");
            String email = sc.next();
            System.out.print("Position: ");
            sc.nextLine();
            String position = sc.nextLine();
            System.out.print("Status: ");
            String status = sc.next();

            String sql = "INSERT INTO tbl_Athlete ( s_id, c_fname, c_lname, c_email, c_position, c_status) VALUES (?, ?, ?, ?, ?, ?)";
            conf.addRecord(sql, sport, fname, lname, email, position, status);
        }
    
    public void viewAthlete() {
        String Query = "SELECT c_athlete, s.s_name, c_fname, c_status "
                + "FROM tbl_Athlete "
                + "INNER JOIN tbl_Sport s ON tbl_Athlete.s_id = s.s_id";
        String[] Hdr = {"ID", "SPORT", "FIRST NAME", "STATUS"};
        String[] Clmn = {"c_athlete", "s_name", "c_fname", "c_status"};
        
        conf.viewRecords(Query, Hdr, Clmn);
    }
    
    public void updateAthlete() {
        System.out.print("Select ID: ");
        int updateID = conf.validateInt();
        
        while(conf.getSingleValue("SELECT c_athlete FROM tbl_Athlete WHERE c_athlete = ?", updateID) == 0){
            System.out.print("ID doesn't exist, try again: ");
            updateID = conf.validateInt();
        }
        
        String sqlUpdate;
        
        System.out.print("Choose what to update: "
                + "\n1. Sport"
                + "\n2. First Name"
                + "\n3. Last Name"
                + "\n4. Email"
                + "\n5. Position"
                + "\n6. Status"
                + "\nEnter selection: ");
        int updateSelection = conf.validateInt();
        
        switch(updateSelection){
            case 1:
                System.out.print("Enter new Sport ID: ");
                int newSport = conf.validateInt();
                
                while(conf.getSingleValue("SELECT s_id FROM tbl_Sport WHERE s_id = ?", newSport) == 0){
                    System.out.print("ID doesn't exist, try again: ");
                    newSport = conf.validateInt();
                }
                
                sqlUpdate = "UPDATE tbl_Athlete SET s_id = ? WHERE c_athlete = ?";
                conf.updateRecord(sqlUpdate, newSport, updateID);
                break;
            case 2:
                System.out.print("Enter new first name: ");
                String newFname = sc.next();
                
                sqlUpdate = "UPDATE tbl_Athlete set c_fname = ? WHERE c_athlete = ?";
                conf.updateRecord(sqlUpdate, newFname, updateID);
                break;
            case 3:
                System.out.print("Enter new Last Name: ");
                String newLname = sc.next();
                sqlUpdate = "UPDATE tbl_Athlete SET c_lname = ? WHERE c_athlete = ?";
                conf.updateRecord(sqlUpdate, newLname, updateID);
                break;
            case 4:
                System.out.print("Enter new Email: ");
                String newEmail = sc.next();
                sqlUpdate = "UPDATE tbl_Athlete SET c_email = ? WHERE c_athlete = ?";
                conf.updateRecord(sqlUpdate, newEmail, updateID);
                break;
            case 5:
                System.out.print("Enter new Position: ");
                String newPosition = sc.next();
                sqlUpdate = "UPDATE tbl_Athlete SET c_position = ? WHERE c_athlete = ?";
                conf.updateRecord(sqlUpdate, newPosition, updateID);
                break;
            case 6:
                System.out.print("Enter new Status: ");
                String newStatus = sc.next();
                sqlUpdate = "UPDATE tbl_Athlete SET c_status = ? WHERE c_athlete = ?";
                conf.updateRecord(sqlUpdate, newStatus, updateID);
                break;

            default:
                System.out.println("Invalid action. Please try again.");
        }
    }
    
    public void deleteAthlete() {
       System.out.print("Select ID: ");
        int deleteID = conf.validateInt();
        
        while(conf.getSingleValue("SELECT c_athlete FROM tbl_Athlete WHERE c_athlete = ?", deleteID) == 0){
            System.out.print("ID doesn't exist, try again: ");
            deleteID = conf.validateInt();
        }
        
        String sqlDelete = "DELETE FROM tbl_Athlete WHERE c_athlete = ?";
        conf.deleteRecord(sqlDelete, deleteID);
    }
    
    public void viewSpecific(){
        System.out.print("Select ID: ");
        int getID = conf.validateInt();
        
        while(conf.getSingleValue("SELECT c_athlete FROM tbl_Athlete WHERE c_athlete = ?", getID) == 0){
            System.out.print("ID doesn't exist, try again: ");
            getID = conf.validateInt();
        }
        
        try{
            String Query = "SELECT c_athlete, s.s_name, c_fname, c_lname, c_email, c_position, c_status "
                + "FROM tbl_Athlete "
                + "INNER JOIN tbl_Sport s ON tbl_Athlete.s_id = s.s_id WHERE c_athlete = ?";
            
            PreparedStatement findRow = conf.connectDB().prepareStatement(Query);
            findRow.setInt(1, getID);
            
            try (ResultSet result = findRow.executeQuery()) {
                int cid = result.getInt("c_athlete");
                String sname = result.getString("s_name");
                String fname = result.getString("c_fname");
                String lname = result.getString("c_lname");
                String email = result.getString("c_email");
                String position = result.getString ("c_position");
                String status = result.getString("c_status");
                
                System.out.println("\nAthelete Details: ");
                System.out.println("--------------------------------");
                System.out.println("ID: "+cid);
                System.out.println("Registered Sport: "+sname);
                System.out.println("First Name: "+fname);
                System.out.println("Last Name: "+lname);
                System.out.println("Email : "+email);
                System.out.println("Postion: "+position);
                System.out.println("--------------------------------");
                System.out.println("Status: "+status);
            }
        } catch(SQLException e){
            System.out.println("Error: "+e.getMessage());
        }
    }
}
