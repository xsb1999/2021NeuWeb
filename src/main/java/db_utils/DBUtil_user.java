package db_utils;

import managedbeans.User;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DBUtil_user {

    private static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private static String path = "";
    private static String protocol = "jdbc:derby:";
    private static String username = "someuser";
    private static String password = "somepassword";
    private static String dbName = "myDatabase_Andy";
    private static Properties userInfo = new Properties();
    private static String tableName = "users";

    // get connection to database
    public static Connection getConnection() throws SQLException {
        userInfo.put("user", "someuser");
        userInfo.put("password", "somepassword");
        // The path is in the bin of your tomcat
        // path = F:\apache-tomcat-9.0.31\bin\myDatabase_Andy
        File directory = new File("myDatabase_Andy");
        path =  directory.getAbsolutePath();
        String dbUrl = protocol + path;
        Connection connection = DriverManager.getConnection(dbUrl, userInfo);
        return connection;
    }


    // register
    public static int registerUser(User user, Connection connection){
        try {
            String query = String.format("insert into %s (username, password)" + " values(?, ?)", tableName);
            PreparedStatement inserter =
                    connection.prepareStatement(query);
            inserter.setString(1, user.getUsername());
            inserter.setString(2, user.getPassword());
            inserter.executeUpdate();
            connection.close();
            return 1;

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return 0;
        }
    }

    // get user_id by username and password
    public static int getId(User user, Connection connection){
        int uid = 0;
        try {
            String query = String.format("select user_id from %s" + " where username=? and password=?", tableName);
            PreparedStatement inserter =
                    connection.prepareStatement(query);
            inserter.setString(1, user.getUsername());
            inserter.setString(2, user.getPassword());
            ResultSet resultSet = inserter.executeQuery();
            while(resultSet.next()) {
                int id = resultSet.getInt("user_id");
                uid = id;
            }
            connection.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return uid;
    }

    // login
    public static List<User> userLogin(User user, Connection connection){
        List<User> userList = new ArrayList<>();
        try {
            String query = String.format("select * from %s" + " where username=? and password=?", tableName);
            PreparedStatement inserter =
                    connection.prepareStatement(query);
            inserter.setString(1, user.getUsername());
            inserter.setString(2, user.getPassword());
            ResultSet resultSet = inserter.executeQuery();
            while(resultSet.next()) {
                User user1 = new User();
                int id = resultSet.getInt("user_id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                user1.setUser_id(id);
                user1.setUsername(username);
                user1.setPassword(password);
                userList.add(user1);
            }
            connection.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return userList;
    }


    // find a user by id
    public static User getUserById(int id, Connection connection) throws SQLException {
        User user = new User();
        try {
            String query = String.format("select * from %s" + " where user_id=?", tableName);
            PreparedStatement inserter =
                    connection.prepareStatement(query);
            inserter.setInt(1, id);
            ResultSet resultSet = inserter.executeQuery();
            while(resultSet.next()) {
                int user_id = resultSet.getInt("user_id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String basic_info = resultSet.getString("basic_intro");
                String interests = resultSet.getString("interests");
                String experiences = resultSet.getString("experiences");
                String skills = resultSet.getString("skills");
                String goals = resultSet.getString("goals");

                user.setUser_id(user_id);
                user.setUsername(username);
                user.setPassword(password);
                user.setBasic_intro(basic_info);
                user.setExperiences(experiences);
                user.setInterests(interests);
                user.setSkills(skills);
                user.setGoals(goals);
            }
            connection.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return user;
    }



    // add or update user's detailed information
    public static int add_update_Info(User user, Connection connection){
        try {
            String query = String.format("update %s set basic_intro = ?, " +
                    "interests= ?, experiences= ?, skills= ?, goals= ? where user_id = ?", tableName);
            PreparedStatement inserter =
                    connection.prepareStatement(query);
            inserter.setString(1, user.getBasic_intro());
            inserter.setString(2, user.getInterests());
            inserter.setString(3, user.getExperiences());
            inserter.setString(4, user.getSkills());
            inserter.setString(5, user.getGoals());
            inserter.setDouble(6, user.getUser_id());
            inserter.executeUpdate();
            connection.close();
            return 1;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return 0;
        }
    }














}
