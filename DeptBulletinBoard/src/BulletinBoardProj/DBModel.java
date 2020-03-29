package BulletinBoardProj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import BulletinBoardProj.Databases.Confirmed;

public class DBModel {
	
	private Connection con;
    private Statement stmt;
    private ResultSet rs;
    
    String host = "23.229.237.194:3306/";
    String user = "p485"; 
    String pass = "project485"; 
    String name = "485_main";
    
    String sqlConfirmedByDate = "SELECT * FROM Confirmed ORDER BY DATE DESC";
    String sqlConfirmedByDept = "SELECT * FROM Confirmed ORDER BY DEPARTMENT ASC";
    String sqlConfirmedByFee = "SELECT * FROM Confirmed ORDER BY FEE ASC";
    
    public DBModel() {}
    
    public List<Confirmed> getEventsByDate() {
        return getEvents(sqlConfirmedByDate);
    }
    
    public List<Confirmed> getEventsByDept() {
        return getEvents(sqlConfirmedByDept);
    }
    
    public List<Confirmed> getEventsByFee() {
        return getEvents(sqlConfirmedByFee);
    }
    
    // Todo: Maybe putting the query on a background thread will improve performance
    // Todo: Add parameter to specify a maximum amount of results from query
    private List<Confirmed> getEvents(String query) {
    	final List<Confirmed> resultList = new ArrayList<>();
    	try{
            System.out.println("Attempting to connect to the database . . .");
            con = DriverManager.getConnection("jdbc:mysql://" + host + name, user, pass);
            System.out.println("Connection to database successful!");
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            
            Confirmed confirmed;
            while (rs.next()) {
         	    confirmed = new Confirmed();
                confirmed.setId(rs.getString(1));
                confirmed.setTitle(rs.getString(2));
                confirmed.setDate(rs.getDate(3));
                confirmed.setDescription(rs.getString(4));
                confirmed.setLocation(rs.getString(5));
                confirmed.setRoom(rs.getInt(6));
                confirmed.setDepartment(rs.getString(7));
                confirmed.setFee(rs.getDouble(8));
                resultList.add(confirmed);
            }
            con.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        System.out.println("\nSession Complete.");
        return resultList;
    }
	
}
