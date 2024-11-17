package implement;

import it2e.echavezz.config;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class tbl_Event {
    tbl_Sport s = new tbl_Sport();
    tbl_Athlete a = new tbl_Athlete();
    Scanner sc = new Scanner(System.in);
    config conf = new config();
    
    public void addEvent(){
        System.out.print("Event name: ");
        String ename = sc.nextLine();
        
        s.viewSport();
        System.out.print("Sport ID: ");
        int sid = conf.validateInt();
        System.out.print("Date of event [YYYY-MM-DD]: ");
        String edate = sc.nextLine();
        System.out.print("Location of event: ");
        String elocation = sc.nextLine();
        
        a.viewAthlete();
        System.out.print("Athelete ID to assign: ");
        int aid = conf.validateInt();
        System.out.print("Enter status: ");
        String estatus = sc.nextLine();
        
        String sql = "INSERT INTO tbl_Event (e_name, s_id, e_date, e_location, c_athlete, e_status) VALUES (?, ?, ?, ?, ?, ?)";
        conf.addRecord(sql, ename, sid, edate, elocation, aid, estatus);
    }
    
    public void viewEvent(){
        String Query = "SELECT e_id, e_name, s.s_name, e_date, e_location, e_status FROM tbl_Event "
                + "INNER JOIN tbl_Sport s ON tbl_Event.s_id = s.s_id ";
        String Hdr[] = {"ID", "NAME", "SPORT", "DATE", "LOCATION", "STATUS"};
        String Clm[] = {"e_id", "e_name", "s_name", "e_date", "e_location", "e_status"};
        conf.viewRecords(Query, Hdr, Clm);
    }
    
    public void updateEvent(){
        System.out.print("Select ID: ");
        int updateID = conf.validateInt();
        
        while(conf.getSingleValue("SELECT e_id FROM tbl_Event WHERE e_id = ?", updateID) == 0){
            System.out.print("ID doesn't exist, try again: ");
            updateID = conf.validateInt();
        }
        
        String sqlUpdate;
        
        System.out.println("Choose what to update:");
        System.out.println("1. Event name");
        System.out.println("2. Date");
        System.out.println("3. Location");
        System.out.println("4. Status");
        System.out.print("Enter choice: ");
        int updateSelection = conf.validateInt();
        
        switch(updateSelection){
            case 1:
                System.out.print("Enter new event name: ");
                String newName = sc.nextLine();
                
                sqlUpdate = "UPDATE tbl_Event SET e_name = ? WHERE e_id = ?";
                conf.updateRecord(sqlUpdate, newName, updateID);
                break;
            case 2:
                System.out.print("Enter new Date of event [YYYY-MM-DD]: ");
                String newDate = sc.nextLine();
                
                sqlUpdate = "UPDATE tbl_Event SET e_date = ? WHERE e_id = ?";
                conf.updateRecord(sqlUpdate, newDate, updateID);
                break;
            case 3:
                System.out.print("Enter new location of event: ");
                String newLocation = sc.nextLine();
                
                sqlUpdate = "UPDATE tbl_Event SET e_location = ? WHERE e_id = ?";
                conf.updateRecord(sqlUpdate, newLocation, updateID);
                break;
            case 4:
                System.out.print("Enter status: ");
                String newStatus = sc.nextLine();
                
                sqlUpdate = "UPDATE tbl_Event SET e_status = ? WHERE e_id = ?";
                conf.updateRecord(sqlUpdate, newStatus, updateID);
                break;
            default:
                System.out.println("Invalid action. Please try again.");
        }
    }
    
    public void deleteEvent(){
        System.out.print("Select ID: ");
        int deleteID = conf.validateInt();
        
        while(conf.getSingleValue("SELECT e_id FROM tbl_Event WHERE e_id = ?", deleteID) == 0){
            System.out.print("ID doesn't exist, try again: ");
            deleteID = conf.validateInt();
        }
        
        String sqlDelete = "DELETE FROM tbl_Event WHERE e_id = ?";
        conf.deleteRecord(sqlDelete, deleteID);
    }
    
    public void viewSpecific(){
        System.out.print("Select ID: ");
        int getID = conf.validateInt();
        
        while(conf.getSingleValue("SELECT e_id FROM tbl_Event WHERE e_id = ?", getID) == 0){
            System.out.print("ID doesn't exist, try again: ");
            getID = conf.validateInt();
        }
        
        try{
            String Query = "SELECT e_id, e_name, s.s_name, e_date, e_location, c.c_fname, e_status FROM tbl_Event "
                + "INNER JOIN tbl_Sport s ON tbl_Event.s_id = s.s_id INNER JOIN tbl_Athlete c ON tbl_Event.c_athlete = c.c_athlete "
                    + "WHERE e_id = ?";
            
            PreparedStatement findRow = conf.connectDB().prepareStatement(Query);
            findRow.setInt(1, getID);
            
            try (ResultSet result = findRow.executeQuery()) {
                int eid = result.getInt("e_id");
                String ename = result.getString("e_name");
                String esport = result.getString("s_name");
                String edate = result.getString("e_date");
                String elocation = result.getString("e_location");
                String eathlete = result.getString ("c_fname");
                String estatus = result.getString("e_status");
                
                System.out.println("\nEvent Details: ");
                System.out.println("--------------------------------");
                System.out.println("ID: "+eid);
                System.out.println("Event Name: "+ename);
                System.out.println("Sport: "+esport);
                System.out.println("Date: "+edate);
                System.out.println("Location: "+elocation);
                System.out.println("Assigned Athlete: "+eathlete);
                System.out.println("--------------------------------");
                System.out.println("Status: "+estatus);
            }
        } catch(SQLException e){
            System.out.println("Error: "+e.getMessage());
        }
    }
}
