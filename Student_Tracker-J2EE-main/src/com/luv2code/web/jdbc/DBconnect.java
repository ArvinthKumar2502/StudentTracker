package com.luv2code.web.jdbc;

import java.sql.*;

public class DBconnect {
    public static Connection getConnection() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class.forName("com.mysql.jdbc.Driver");

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/web_student_tracker", "root", "password");
        //System.out.println("con after");
        return con;
    }

    public static void main(String[] args) {
        Statement myStmt = null;
        ResultSet myRs = null;
        DBconnect db = new DBconnect();
        try {
            System.out.println(getConnection());
            String sql = "select * from student;";
            Connection myConn = getConnection();
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery(sql);
            while (myRs.next()) {

                // retrieve data from result set row
                int id = myRs.getInt("id");
                String firstName = myRs.getString("first_name");
                String lastName = myRs.getString("last_name");
                String email = myRs.getString("email");
                //System.out.println(firstName);
                // create new student object
                Student tempStudent = new Student(id, firstName, lastName, email);

                // add it to the list of students
            }
            // execute query
            myRs = myStmt.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
