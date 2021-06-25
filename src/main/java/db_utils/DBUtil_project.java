package db_utils;

import managedbeans.Projects;

import java.io.File;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class DBUtil_project {

    private static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private static String path = "";
    private static String protocol = "jdbc:derby:";
    private static String username = "someuser";
    private static String password = "somepassword";
    private static String dbName = "myDatabase_Andy";
    private static Properties userInfo = new Properties();
    private static String tableName = "projects";


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



    public static List<Projects> getAllProjects(Connection connection) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<Projects> projects = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = String.format("SELECT * FROM %s", tableName);
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String summary = resultSet.getString("summary");
                String description = resultSet.getString("description");
                String type = resultSet.getString("type");
                String language = resultSet.getString("language");
                Double cost = resultSet.getDouble("cost");
                java.util.Date startDate = simpleDateFormat.parse(resultSet.getString("startDate"));
                Date finishDate = simpleDateFormat.parse(resultSet.getString("finishDate"));
                projects.add(new Projects(id,title,summary,description,type,language,cost,startDate,finishDate));

            }
            connection.close();
        } catch (SQLException | ParseException sqle) {
            sqle.printStackTrace();
        }
        return projects;
    }



    public static int UpdateProject(Projects project, Connection connection){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String query = String.format("update %s set title = ?, " +
                    "summary= ?, description= ?, type= ?, language= ?, cost= ?, startDate=?, finishDate=?  where id = ?", tableName);
            PreparedStatement inserter =
                    connection.prepareStatement(query);
            inserter.setString(1, project.getTitle());
            inserter.setString(2, project.getSummary());
            inserter.setString(3, project.getDescription());
            inserter.setString(4, project.getType());
            inserter.setString(5, project.getLanguage());
            inserter.setDouble(6, project.getCost());
            inserter.setString(7, simpleDateFormat.format(project.getStartDate()));
            inserter.setString(8, simpleDateFormat.format(project.getFinishDate()));
            inserter.setInt(9, project.getId());
            inserter.executeUpdate();
            connection.close();
            return 1;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return 0;
        }
    }


    public static int addProject(Projects project, Connection connection){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String query = String.format("insert into %s (title, summary, description, type, language, cost, startDate, FinishDate, user_id)" +
                    " values(?, ?, ?, ?, ?, ?, ?, ?, ?)", tableName);
            PreparedStatement inserter =
                    connection.prepareStatement(query);
            inserter.setString(1, project.getTitle());
            inserter.setString(2, project.getSummary());
            inserter.setString(3, project.getDescription());
            inserter.setString(4, project.getType());
            inserter.setString(5, project.getLanguage());
            inserter.setDouble(6, project.getCost());
            inserter.setString(7, simpleDateFormat.format(project.getStartDate()));
            inserter.setString(8, simpleDateFormat.format(project.getFinishDate()));
            inserter.setInt(9, project.getUser_id());
            inserter.executeUpdate();
            connection.close();
            return 1;

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return 0;
        }
    }


    public static List<Projects> getProjectsByUserId(int uid, Connection connection){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<Projects> projects = new ArrayList<>();
        try {
            String query = String.format("SELECT * FROM %s" + " where user_id=?", tableName);
            PreparedStatement inserter =
                    connection.prepareStatement(query);
            inserter.setInt(1, uid);
            ResultSet resultSet = inserter.executeQuery();
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String summary = resultSet.getString("summary");
                String description = resultSet.getString("description");
                String type = resultSet.getString("type");
                String language = resultSet.getString("language");
                Double cost = resultSet.getDouble("cost");
                java.util.Date startDate = simpleDateFormat.parse(resultSet.getString("startDate"));
                Date finishDate = simpleDateFormat.parse(resultSet.getString("finishDate"));
                projects.add(new Projects(id,title,summary,description,type,language,cost,startDate,finishDate));

            }
            connection.close();
        } catch (SQLException | ParseException sqle) {
            sqle.printStackTrace();
        }
        return projects;
    }




}
