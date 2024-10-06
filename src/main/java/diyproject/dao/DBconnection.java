package diyproject.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import diyproject.exception.DBexception;

public class DBconnection {

  private static String HOST = "localhost";
  private static String PASSWORD = "DiyProject!";
  private static int PORT = 3306;
  private static String SCHEMA = "diyproject";
  private static String USER = "diyproject";

  public static Connection getConnection() {
    String uri = String.format("jdbc:mysql://%s:%d/%s?user=%s&password=%s", HOST, PORT, SCHEMA,
        USER, PASSWORD);

    try {
      Connection conn = DriverManager.getConnection(uri);
      System.out.println("Connection to schema '" + SCHEMA + "' is successful :D");
      return conn;
    } catch (SQLException e) {
      System.out.println("Unable to get connection at " + uri);
      throw new DBexception("Unable to get connection at \" + uri");
    }
  }
}
