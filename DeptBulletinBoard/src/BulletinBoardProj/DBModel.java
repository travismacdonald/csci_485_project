package BulletinBoardProj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import BulletinBoardProj.Databases.Confirmed;
import BulletinBoardProj.Databases.Event;
import BulletinBoardProj.Databases.Requested;
import BulletinBoardProj.Databases.User;

public class DBModel {
	
	private Connection con;
    private Statement stmt;
    private ResultSet rs;
    
    private User curUser;
    
    private final String host = "23.229.237.194:3306/";
    private final String dbUser = "p485"; 
    private final String pass = "project485"; 
    
    private final String mainDBName = "485_main";
    private final String mainTableName = "Confirmed";
    private final String reqDBName = "485_reqs";
    private final String reqTableName = "Requested";
    
    private final String sqlConfirmedByDate = "SELECT * FROM Confirmed ORDER BY DATE DESC";
    private final String sqlConfirmedByDept = "SELECT * FROM Confirmed ORDER BY DEPARTMENT ASC";
    private final String sqlConfirmedByFee = "SELECT * FROM Confirmed ORDER BY FEE ASC";
    
    private final String sqlRequestedByDate = "SELECT * FROM Requested ORDER BY DATE DESC";
    private final String sqlRequestedByDept = "SELECT * FROM Requested ORDER BY DEPARTMENT ASC";
    private final String sqlRequestedByFee = "SELECT * FROM Requested ORDER BY FEE ASC";
    
    
    public DBModel() {
    	curUser = new User();
    }
    
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
    
    public User getCurUser() {
    	return curUser;
    }
    
    public boolean loginUser(User user) {
    	// TODO: check if user credentials are correct ( use isValidUser(User) )
    	//       if valid:   return true
    	//       if invalid: return false
    	return false;
    }
    
    public boolean registerUser(User user) {
    	// TODO: ( use userNameIsInDatabase )
    	//       if already in db:
    	//           return false
    	//
    	//       if not:
    	//           add user to DB
    	//           return true
    	return false;
    }
    
    public void grantAdminStatus(User user) {
    	// TODO: check if user is actualy in DB first ( use userNameIsInDatabase )
    	//       then update user admin status in db
    	user.setAdmin(true);
    }
    
    public void revokeAdminStatus(User user) {
    	// TODO: check if user is actually in DB first ( use userNameIsInDatabase )
    	//       then update user admin status in db
    	user.setAdmin(false);
    }
    
    public boolean userIsAdmin() {
    	// TODO: check if user is admin via db
    	return false;
    }
    
    public boolean submitEvent(Event event) {
    	if(!(event.getDate().toLocalDate().isBefore(LocalDate.now()))) {
    		addRequestedEvent(event);
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    public void approveEvent(Event event) {
    	removeRequestedEvent(event);
    	addConfirmedEvent(event);
    }
    
    public void rejectEvent(Event event) {
    	removeRequestedEvent(event);
    }
    
    
    /* helper method */
    private void addConfirmedEvent(Event event) {
    	addEvent(event, true);
    }
    
    /* helper methods */
    private void addRequestedEvent(Event event) {
    	addEvent(event, false);
    }
    
    /* helper methods */
    private void removeConfirmedEvent(Event event) {
    	removeEvent(event, true);
    }
    
    /* helper methods */
    private void removeRequestedEvent(Event event) {
    	removeEvent(event, false);
    }
    
    private void addEvent(Event event, boolean isConfirmed) {
    	final String dbName;
    	final String tableName;
    	final String queryStr;
    	if (isConfirmed) {
    		dbName = mainDBName;
    		tableName = mainTableName;
    	}
    	else {
    		dbName = reqDBName;
    		tableName = reqTableName;
    		event.setId(generateId());
    	}
    	
    	queryStr = "INSERT INTO " + tableName + " VALUES ('" 
    			+ event.getId() + "','" 
    			+ event.getTitle() + "','" 
    			+ event.getDate() + "','" 
    			+ event.getDescription() + "','" 
    			+ event.getLocation() + "'," 
    			+ event.getRoom() + ",'" 
    			+ event.getDepartment() + "'," 
    			+ event.getFee() + ")";
    	executeEventQuery(dbName, queryStr);
    }
    
    private void removeEvent(Event event, boolean isConfirmed) {
    	final String dbName;
    	final String tableName;
    	final String queryStr;
    	if (isConfirmed) {
    		dbName = mainDBName;
    		tableName = mainTableName;
    	}
    	else {
    		dbName = reqDBName;
    		tableName = reqTableName;    		
    	}
    	queryStr = "DELETE FROM " + tableName + " WHERE ID = '" + event.getId() + "'";
    	executeEventQuery(dbName, queryStr);
    }
    
    
    private boolean userNameIsInDatabase(String username) {
    	// TODO: return true if username is in DB
    	return false;
    }
    
    private boolean isValidUser(User user) {
    	// TODO: return true if credentials are valid (name exists in DB and password is correct)
    	return false;
    }
    
    private void executeEventQuery(String dbName, String query) {
    	try {
	    	con = DriverManager.getConnection("jdbc:mysql://" + host + dbName, dbUser, pass);
	        stmt = con.createStatement();
	        stmt.executeUpdate(query);
	        con.close();
	    	
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    private String generateId() {
    	final String alphanum = "abcdef0123456789";
    	Random random = new Random();
    	String id = "";
    	for (int i = 0; i < 12; i++) {
    		id += alphanum.charAt(random.nextInt(12));
    	}
    	return id;
    }
    
    
    
    // Todo: Maybe putting the query on a background thread will improve performance
    // Todo: Add parameter to specify a maximum amount of results from query to avoid overload
    private List<Event> getEvents(String query, boolean isConfirmed) {
    	final List<Event> resultList = new ArrayList<>();
    	try{
    		final String dbName;
    		if (isConfirmed) {
    			dbName = mainDBName;
    		}
    		else {
    			dbName = reqDBName;
    		}
            con = DriverManager.getConnection("jdbc:mysql://" + host + dbName, dbUser, pass);
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
