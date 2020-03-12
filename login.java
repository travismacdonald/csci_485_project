package com.company;

import java.sql.*;
import java.util.Scanner;

public class Main {
    static Connection con;
    static Statement stmt;
    static ResultSet rs;
    static Scanner scan = new Scanner(System.in);

    @SuppressWarnings("unused")
    public static void main(String args[]) {
        String host = "23.229.237.194:3306/", user = "p485", pass = "project485", name = "485_users";
        try {
            System.out.println("Attempting to connect to the database . . .");
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://" + host + name, user, pass);
            System.out.println("Connection to database successful!");
            System.out.println("\'L\' to login, \'R\' to register");
            String in = scan.nextLine();
            if(in.equals("R")) {
                register();
            }else{
                login();
            }
            con.close();
        } catch (Exception e) {
            System.out.println("Error connecting to the database.");
            e.printStackTrace();
        }
        System.out.println("\nSession Complete.");
    }

    public static void login() throws SQLException{
//        System.out.println("Please enter email");
//        String email = scan.nextLine();
//        System.out.println("Please enter password");
//        String pass = scan.nextLine();
        String email = "java@test.ca";
        String pass = "qwer123";

        String sql = "SELECT name, pass, admin FROM Users";
        stmt = con.createStatement();
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            if(rs.getString(1).equals(email)){
                System.out.println("User found");
                if(rs.getString(2).equals(pass)){
                    System.out.println("password good");
                }
                else{
                    System.out.println("Password invalid");
                    login();
                }
            }
        }
    }

    public static void register() throws SQLException {
        String email="",pass1="",pass2="";
        String sql = "INSERT INTO Users (name, pass, admin)" + "VALUES (?, ?, ?)";
        String sql2 = "SELECT name, pass, admin FROM Users";
//        System.out.println("Please enter your email");
//        email=scan.nextLine();
//        System.out.println("Please enter your password");
//        pass1=scan.nextLine();
//        System.out.println("Please reenter your password");
//        pass2=scan.nextLine();

        email="java@test.ca";
        pass1=pass2="qwer123";
        stmt = con.createStatement();
        rs = stmt.executeQuery(sql2);
        while (rs.next()) {
            if(rs.getString(1).equals(email)){
                System.out.println("User exists");
                register();
            }
        }

        if(pass1.equals(pass2)){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,email);
            ps.setString(2,pass1);
            ps.setInt(3,0);
            int i = ps.executeUpdate();
        }else{
            System.out.println("Passwords do not match");
            register();
        }
    }
}
