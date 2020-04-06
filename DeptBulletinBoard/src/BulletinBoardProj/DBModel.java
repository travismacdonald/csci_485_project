package BulletinBoardProj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import BulletinBoardProj.Databases.Confirmed;
import BulletinBoardProj.Databases.Event;
import BulletinBoardProj.Databases.Requested;

public class DBModel {
	
	private Connection con;
    private Statement stmt;
    private ResultSet rs;
    
    private final String host = "23.229.237.194:3306/";
    private final String user = "p485"; 
    private final String pass = "project485"; 
    private final String name = "485_main";
    
    private final String sqlConfirmedByDate = "SELECT * FROM Confirmed ORDER BY DATE DESC";
    private final String sqlConfirmedByDept = "SELECT * FROM Confirmed ORDER BY DEPARTMENT ASC";
    private final String sqlConfirmedByFee = "SELECT * FROM Confirmed ORDER BY FEE ASC";
    
    private final String sqlRequestedByDate = "SELECT * FROM Requested ORDER BY DATE DESC";
    private final String sqlRequestedByDept = "SELECT * FROM Requested ORDER BY DEPARTMENT ASC";
    private final String sqlRequestedByFee = "SELECT * FROM Requested ORDER BY FEE ASC";
    
    
    public DBModel() {}
    
    public List<Event> getConfirmedEventsByDate() {
        return getEvents(sqlConfirmedByDate, true);
    }
    
    public List<Event> getConfirmedEventsByDept() {
        return getEvents(sqlConfirmedByDept, true);
    }
    
    public List<Event> getConfirmedEventsByFee() {
        return getEvents(sqlConfirmedByFee, true);
    }
    
    public List<Event> getRequestedEventsByDate() {
        return getEvents(sqlRequestedByDate, false);
    }
    
    public List<Event> getRequestedEventsByDept() {
        return getEvents(sqlRequestedByDept, false);
    }
    
    public List<Event> getRequestedEventsByFee() {
        return getEvents(sqlRequestedByFee, false);
    }
    
    // Todo: Maybe putting the query on a background thread will improve performance
    // Todo: Add parameter to specify a maximum amount of results from query to avoid overload
    private List<Event> getEvents(String query, boolean isConfirmed) {
    	final List<Event> resultList = new ArrayList<>();
    	try{
            con = DriverManager.getConnection("jdbc:mysql://" + host + name, user, pass);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                // Todo: kind of a hacky way of dealing with confirmed vs requested
                final Event event;
                if (isConfirmed) {
                	event = new Confirmed();
                }
                else {
                	event = new Requested();
                }
                event.setId(rs.getString(1));
                event.setTitle(rs.getString(2));
                event.setDate(rs.getDate(3));
                event.setDescription(rs.getString(4));
                event.setLocation(rs.getString(5));
                event.setRoom(rs.getInt(6));
                event.setDepartment(rs.getString(7));
                event.setFee(rs.getDouble(8));
                resultList.add(event);
            }
            con.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }
	
}
