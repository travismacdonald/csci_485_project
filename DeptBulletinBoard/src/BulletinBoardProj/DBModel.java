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
import BulletinBoardProj.Databases.Database485;
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
    private final String userDBName = "485_users";
    private final String userTableName = "Users";
    
    private final String sqlConfirmedByDate = "SELECT * FROM Confirmed ORDER BY DATE DESC";
    private final String sqlConfirmedByDept = "SELECT * FROM Confirmed ORDER BY DEPARTMENT ASC";
    private final String sqlConfirmedByFee = "SELECT * FROM Confirmed ORDER BY FEE ASC";
    
    private final String sqlRequestedByDate = "SELECT * FROM Requested ORDER BY DATE DESC";
    private final String sqlRequestedByDept = "SELECT * FROM Requested ORDER BY DEPARTMENT ASC";
    private final String sqlRequestedByFee = "SELECT * FROM Requested ORDER BY FEE ASC";
    
    
    public DBModel() {
    	curUser = new User();
    }
    
    public List<Event> getConfirmedEventsByDate(Database485 obj) {
    	return getEvents(sqlConfirmedByDate, obj);
    }
    
    public List<Event> getConfirmedEventsByDept(Database485 obj) {
        return getEvents(sqlConfirmedByDept, obj);
    }
    
    public List<Event> getConfirmedEventsByFee(Database485 obj) {
        return getEvents(sqlConfirmedByFee, obj);
    }
    
    public List<Event> getRequestedEventsByDate(Database485 obj) {
        return getEvents(sqlRequestedByDate, obj);
    }
    
    public List<Event> getRequestedEventsByDept(Database485 obj) {
        return getEvents(sqlRequestedByDept, obj);
    }
    
    public List<Event> getRequestedEventsByFee(Database485 obj) {
        return getEvents(sqlRequestedByFee, obj);
    }
    
    public User getCurUser() {
    	return curUser;
    }
    
    public boolean loginUser(User user) {
    	if (isValidUser(user)) {
    		// todo: set admin status
    		user.setAdmin(this.isUserAdmin(user));
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    public boolean registerUser(User user) {
    	if (userNameIsInDatabase(user.getName())) {
    		return false;
    	}
    	else {
    		addUser(user);
    		return true;
    	}
    }
    
    public boolean grantAdminStatus(User user) {
    	if (this.userNameIsInDatabase(user.getName())) {
	    	final String queryStr = 
	    			"UPDATE " + userTableName +
	    			" SET ADMIN = 1 " +
	    			" WHERE NAME = '" + user.getName() + "'";
	    	executeDBUpdate(userDBName, queryStr);
	    	user.setAdmin(true);
	    	return true;
    	}
    	return false;
    }
    
    public void revokeAdminStatus(User user) {
    	if (this.userNameIsInDatabase(user.getName())) {
	    	final String queryStr = 
	    			"UPDATE " + userTableName +
	    			" SET ADMIN = 0 " +
	    			" WHERE NAME = '" + user.getName() + "'";
	    	executeDBUpdate(userDBName, queryStr);
	    	user.setAdmin(true);
    	}
    	user.setAdmin(false);
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
    @SuppressWarnings("unused")
	private void removeConfirmedEvent(Event event) {
    	removeEvent(event, true);
    }
    
    /* helper methods */
    private void removeRequestedEvent(Event event) {
    	removeEvent(event, false);
    }
    
    private void addUser(User user) {
    	final String queryStr = 
    			"INSERT INTO " + userTableName + " VALUES ('" 
    			+ user.getName() + "', '" 
    			+ user.getPass() + "', " 
    			+ "0" + ")"; // 0 == not admin
    	executeDBUpdate(userDBName, queryStr);
    }
    
    @SuppressWarnings("unused")
	private void removeUser(User user) {
    	final String queryStr = 
    			"DELETE FROM " + userTableName + 
    			" WHERE NAME = '" + user.getName() + "'";
    	executeDBUpdate(userDBName, queryStr);
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
    	executeDBUpdate(dbName, queryStr);
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
    	executeDBUpdate(dbName, queryStr);
    }
    
    private boolean userNameIsInDatabase(String username) {
    	final String query = 
    			"SELECT * FROM Users " + 
    	        "WHERE NAME = '" + username + "' ";
    	try {
    		con = DriverManager.getConnection("jdbc:mysql://" + host + userDBName, dbUser, pass);
    		stmt = con.createStatement();
    		rs = stmt.executeQuery(query);
    		
    		// Will be true if user is in DB
    		boolean isValid = rs.next();
    		con.close();
    		return isValid;
    	} 
    	catch(Exception e) {
            e.printStackTrace();
        }
    	return false;
    }
    
    private boolean isValidUser(User user) {
    	final String query = 
    			"SELECT * FROM Users " + 
    	        "WHERE NAME = '" + user.getName() + "' " +
    			"AND PASS = '" + user.getPass() + "'";
    	try {
    		con = DriverManager.getConnection("jdbc:mysql://" + host + userDBName, dbUser, pass);
    		stmt = con.createStatement();
    		rs = stmt.executeQuery(query);
    		
    		// Will be true if user is in DB
    		boolean isValid = rs.next();
    		con.close();
    		return isValid;
    	} 
    	catch(Exception e) {
            e.printStackTrace();
        }
    	return false;
    }
    
    private boolean isUserAdmin(User user) {
    	final String query = 
    			"SELECT * FROM Users " + 
    	        "WHERE NAME = '" + user.getName() + "' " +
    	        "AND ADMIN = 1";
    	try {
    		con = DriverManager.getConnection("jdbc:mysql://" + host + userDBName, dbUser, pass);
    		stmt = con.createStatement();
    		rs = stmt.executeQuery(query);
    		
    		// Will be true if user is Admin in DB
    		boolean isAdmin = rs.next();
    		con.close();
    		return isAdmin;
    	} 
    	catch(Exception e) {
            e.printStackTrace();
        }
    	return false;
    }
    
    private void executeDBUpdate(String dbName, String query) {
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
    private List<Event> getEvents(String query, Database485 obj) {
	final List<Event> resultList = new ArrayList<>();
	try {
		rs = obj.executeQuery(query);
		while (rs.next()) {
			final Event event;
			if (obj instanceof Confirmed) {
				event = new Confirmed();
			} else {
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
	} catch (Exception e) {
		e.printStackTrace();
	}
	return resultList;
    }
	
}
