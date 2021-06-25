package coreservlets;

import managedbeans.Projects;

import java.io.File;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

// sir, I only create one table "projects" in this class, I create the other table "user" with the database GUI in IDEA
// Thus, please don't run this class and use the database directly

public class EmbeddedDbCreator {
  private String driver = "org.apache.derby.jdbc.EmbeddedDriver";
  private String protocol = "jdbc:derby:";
  private String username = "someuser";
  private String password = "somepassword";
  private String dbName = "myDatabase_Andy";
  private String tableName = "projects";
  private Properties userInfo;

  public EmbeddedDbCreator() {
    userInfo = new Properties();
    userInfo.put("user", username);
    userInfo.put("password", password);
  }

  public void createDatabase() throws ParseException {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Projects[] projects = {
      new Projects(1, "RRT", "Three types of RRT algorithm", "RRT is a path planning method using in autonomous driving based on sampling.It can quickly search the entire state space (the real world) and is biased towards the unexplored space. It is very similar to trees with lots of limbs stretching out at each time.The main working flow is that we start from the start position and protrude some limbs randomly, once the limb of the tree reaches the destination, the path is found successfully.",
              "algorithm","Python",100.2,simpleDateFormat.parse("2020-1-1"),simpleDateFormat.parse("2020-8-12")),
      new Projects(2, "HIS", "The online hospital system of NEU", "It is an online hospital system of NEU, including six functions (registration, return, visit, prescription, delivery, payment).",
                    "development","Java",80.5,simpleDateFormat.parse("2020-7-1"),simpleDateFormat.parse("2020-3-1")),
      new Projects(3, "APDP", "The data processing of the environment data", "The mechine detecting the concentration of CO2 in the air will generate lots of raw data, I need to process them, deleting those bad data, calculating many values like the average.",
                    "data processing","Python",24.8,simpleDateFormat.parse("2020-11-11"),simpleDateFormat.parse("2020-12-20")),
      new Projects(4, "PSO", "My improvement of PSO algorithm", "I changed some parameters of the function in the original PSO algorithm, like the weight, the number of particles. By the way, the speed of the algorithm is slightly improved after my modification.",
                    "algorithm","Python",325.5,simpleDateFormat.parse("2021-3-20"),simpleDateFormat.parse("2021-5-9")),
    };


    try {
      Class.forName(driver);
      String dbUrl = protocol + dbName + ";create=true";
      Connection connection =
        DriverManager.getConnection(dbUrl, userInfo);
      Statement statement = connection.createStatement();
      String format = "VARCHAR(2000)";

      String tableDescription =
        String.format
          ("CREATE TABLE %s" +
              "(id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)primary key," +
                          " title %s, summary %s, " + "description %s, type %s, language %s, cost DOUBLE, StartDate %s, FinishDate %s)",
        	tableName, format, format, format, format, format, format, format);

      statement.execute(tableDescription);
      String template =
        String.format("INSERT INTO %s (title, summary, description, type, language, cost, startDate, FinishDate) VALUES(?, ?, ?, ?, ?, ?, ?, ?)",
                      tableName);
      PreparedStatement inserter =
        connection.prepareStatement(template);

      SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");

      for(Projects p: projects) {
        inserter.setString(1, p.getTitle());
        inserter.setString(2, p.getSummary());
        inserter.setString(3, p.getDescription());
        inserter.setString(4, p.getType());
        inserter.setString(5, p.getLanguage());
        inserter.setDouble(6, p.getCost());
        inserter.setString(7, simpleDateFormat1.format(p.getStartDate()));
        inserter.setString(8, simpleDateFormat1.format(p.getFinishDate()));
        inserter.executeUpdate();
      }
      inserter.close();
      connection.close();
      System.out.println("Done!");
    } catch (SQLException sqle) {
      System.out.println(sqle);
      // If table already exists, then skip everything else
    } catch (ClassNotFoundException e) {
    	System.out.println("Driver class not found.");
    }
  }



  public void showTable() {
    try {
      String dbUrl = protocol + dbName;
      Connection connection;
      connection = DriverManager.getConnection(dbUrl, userInfo);
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
        String startDate = resultSet.getString("startDate");
        String finishDate = resultSet.getString("finishDate");

        System.out.printf("%s %s %s %s %s %s %s %s %s",
                id, title, summary, description, type, language, cost, startDate, finishDate);
        System.out.println();
      }
      connection.close();
    } catch (SQLException sqle) {
      sqle.printStackTrace();
    }
  }


  public static void main(String[] args) throws ParseException {
    EmbeddedDbCreator tester = new EmbeddedDbCreator();
    tester.createDatabase();
    System.out.println("===================Show Table===================");
    tester.showTable();
  }
}
