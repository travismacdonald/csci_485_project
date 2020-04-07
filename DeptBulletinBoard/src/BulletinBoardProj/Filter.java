package BulletinBoardProj;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import BulletinBoardProj.Databases.Confirmed;

public class Filter
{
    static Connection con;
    static Statement stmt;
    static ResultSet rs;
    String host = "23.229.237.194:3306/", user = "p485", pass = "project485", name = "485_main";
    ArrayList<Confirmed> resultList;
    
    public Filter() {}
    
    public ArrayList<Confirmed> ByDate()
    {
       try{
           System.out.println("Attempting to connect to the database . . .");
           con = DriverManager.getConnection("jdbc:mysql://" + host + name, user, pass);
           System.out.println("Connection to database successful!");
           stmt = con.createStatement();
           rs = stmt.executeQuery("SELECT * FROM Confirmed ORDER BY DATE DESC");
           resultList = new ArrayList<>();
           Confirmed c;
           
           while (rs.next()){
        	   c = new Confirmed();
               c.setId(rs.getString(1));
               c.setTitle(rs.getString(2));
               c.setDate(rs.getDate(3));
               c.setDescription(rs.getString(4));
               c.setLocation(rs.getString(5));
               c.setRoom(rs.getInt(6));
               c.setDepartment(rs.getString(7));
               c.setFee(rs.getDouble(8));
               resultList.add(c);
           }
           
           con.close();
       }
       catch(Exception e){
           e.printStackTrace();
       }
       System.out.println("\nSession Complete.");
       return resultList;
    }
    
    public ArrayList<Confirmed> ByDepartment()
    {
        try{
           System.out.println("Attempting to connect to the database . . .");
           con = DriverManager.getConnection("jdbc:mysql://" + host + name, user, pass);
           System.out.println("Connection to database successful!");
           stmt = con.createStatement();
           rs = stmt.executeQuery("SELECT * FROM Confirmed ORDER BY DEPARTMENT ASC");
           resultList = new ArrayList<>();
           Confirmed c;
           
           while (rs.next()){
        	   c = new Confirmed();
               c.setId(rs.getString(1));
               c.setTitle(rs.getString(2));
               c.setDate(rs.getDate(3));
               c.setDescription(rs.getString(4));
               c.setLocation(rs.getString(5));
               c.setRoom(rs.getInt(6));
               c.setDepartment(rs.getString(7));
               c.setFee(rs.getDouble(8));
               resultList.add(c);
           }
           
           con.close();
       }
       catch(Exception e){
           e.printStackTrace();
       }
       System.out.println("\nSession Complete.");
       return resultList;
    }
    
    public ArrayList<Confirmed> ByFee()
    {
        try{
           System.out.println("Attempting to connect to the database . . .");
           con = DriverManager.getConnection("jdbc:mysql://" + host + name, user, pass);
           System.out.println("Connection to database successful!");
           stmt = con.createStatement();
           rs = stmt.executeQuery("SELECT * FROM Confirmed ORDER BY FEE ASC");
           resultList = new ArrayList<>();
           Confirmed c;
           
           while (rs.next()){
        	   c = new Confirmed();
               c.setId(rs.getString(1));
               c.setTitle(rs.getString(2));
               c.setDate(rs.getDate(3));
               c.setDescription(rs.getString(4));
               c.setLocation(rs.getString(5));
               c.setRoom(rs.getInt(6));
               c.setDepartment(rs.getString(7));
               c.setFee(rs.getDouble(8));
               resultList.add(c);
           }
           
           con.close();
       }
       catch(Exception e){
           e.printStackTrace();
       }
       System.out.println("\nSession Complete.");
       return resultList;
    }
}
