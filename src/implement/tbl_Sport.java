package implement;

import it2e.echavezz.config;
import java.util.Scanner;

public class tbl_Sport {
    Scanner sc = new Scanner(System.in);
    config conf = new config();
    
    public void addSport(){
        System.out.print("Sport Name: ");
        String name = sc.nextLine();
        
        String sql = "INSERT INTO tbl_Sport (s_name) VALUES (?)";
        conf.addRecord(sql, name);
    }
    
    public void viewSport(){
        String Query = "SELECT * FROM tbl_Sport";
        String[] Hdr = {"ID", "SPORT"};
        String[] Clmn = {"s_id", "s_name"};
        
        conf.viewRecords(Query, Hdr, Clmn);
    }
    
    public void editSport(){
        System.out.print("Enter ID: ");
        int updateID = conf.validateInt();
        
        while(conf.getSingleValue("SELECT s_id FROM tbl_Sport WHERE s_id = ?", updateID) == 0){
            System.out.print("ID doesn't exist, try again: ");
            updateID = conf.validateInt();
        }
        
        System.out.print("Enter New Sport Name: ");
        String name = sc.nextLine();
        
        String sqlUpdate = "UPDATE tbl_Sport set s_name = ? WHERE s_id = ?";
        conf.updateRecord(sqlUpdate, name, updateID);
    }
    
    public void deleteSport(){
        System.out.print("Enter ID: ");
        int deleteID = conf.validateInt();
        
        while(conf.getSingleValue("SELECT s_id FROM tbl_Sport WHERE s_id = ?", deleteID) == 0){
            System.out.print("ID doesn't exist, try again: ");
            deleteID = conf.validateInt();
        }
        
        String sqlDelete = "DELETE FROM tbl_Sport WHERE s_id = ?";
        conf.deleteRecord(sqlDelete, deleteID);
    }
}
