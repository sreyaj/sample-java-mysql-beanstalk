package com.shippable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Storage {
  private final String dbName = System.getProperty("RDS_DB_NAME"); 
  private final String userName = System.getProperty("RDS_USERNAME"); 
  private final String password = System.getProperty("RDS_PASSWORD"); 
  private final String hostname = System.getProperty("RDS_HOSTNAME");
  private final int port = Integer.parseInt(System.getProperty("RDS_PORT"));
  private final String databaseUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName;

  private Connection conn;

  public Storage() throws Exception {
    Class.forName("com.mysql.jdbc.Driver").newInstance();

    conn = DriverManager.getConnection(databaseUrl, userName, password);

    try (Statement stmt = conn.createStatement()) {
      stmt.executeUpdate("DROP TABLE IF EXISTS scores");
      stmt.executeUpdate("CREATE TABLE scores(score INT)");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
  public void populate() {
    try (Statement stmt = conn.createStatement()) {
      stmt.executeUpdate("INSERT INTO scores(score) VALUES(1234)");
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }

  public Integer getScore() throws SQLException {
    try (Statement stmt = conn.createStatement()) {
      ResultSet rs = stmt.executeQuery("SELECT * FROM scores");
      rs.next();
      return rs.getInt("score");
    }
  }
}
