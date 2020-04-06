package BulletinBoardProj.Databases;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Database485 {
	private Statement stmt;
	private Connection con;
	final String host = "23.229.237.194:3306/", user = "p485", password = "project485";
	
	public Database485() {}
	
	/*
	// Confirmed
	public Database485(String title, String description, String location, String department, Date date, int room, int fee) {
		this.title = title;
		this.description = description;
		this.location = location;
		this.department = department;
		this.date = date;
		this.room = room;
		this.fee = fee;
	}
	
	// Requested
	public Database485(String title, String description, String location, String department, Date date, int fee) {
		this.title = title;
		this.description = description;
		this.location = location;
		this.department = department;
		this.date = date;
		this.fee = fee;
	}
	
	// Users
	public Database485(String name, String pass, boolean admin) {
		this.name = name;
		this.pass = pass;
		this.admin = admin;
	}
	*/
	
	public void connect() throws SQLException {
		String name = "";
		try {
			if (con == null) {
				if (this instanceof Confirmed) {
					name = "485_main";
				} else if (this instanceof Requested) {
					name = "485_reqs";
				} else if (this instanceof User) {
					name = "485_users";
				}
				con = DriverManager.getConnection("jdbc:mysql://" + this.host + name, this.user, this.password);
			}
		} catch (SQLException e) {
			throw new SQLException("Connection failed in Database485");
		}
	}
	
	public Connection close() {
		try {
			if (con != null && !con.isClosed()) {
				con.close();
				con = null;
			}
		} catch (SQLException e) {
			con = null;
		}
		return con;
	}
	
	public void createStatement() throws SQLException {
		try {
			stmt = con.createStatement();
		} catch(SQLException e) {
			throw new SQLException("createStatement error in Database485");
		}
	}
	
	public ResultSet executeQuery(String query) throws SQLException {
		createStatement();
		return stmt.executeQuery(query);
	}
	
	public void addToDatabase() throws SQLException {
		if (this instanceof Confirmed) {
			System.out.println("Confirmed");
			stmt = con.createStatement();
		} else if (this instanceof Requested) {
			System.out.println("Requested");
		} else if (this instanceof User) {
			System.out.println("Users");
		}
	}
	
	public void removeFromDatabase() throws SQLException {
		if (this instanceof Confirmed) {
			System.out.println("Confirmed");
		} else if (this instanceof Requested) {
			System.out.println("Requested");
		} else if (this instanceof User) {
			System.out.println("Users");
		}
	}
	
	
}
