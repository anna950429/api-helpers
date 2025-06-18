package org.otus.tests.helpers;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlHelper {

  private final Connection connection;

  public SqlHelper() {
    try {
      connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
      initSchema();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  private void initSchema() throws SQLException {
    try (Statement st = connection.createStatement()) {
      st.execute("CREATE TABLE IF NOT EXISTS users(id INT PRIMARY KEY, name VARCHAR(64));");
      st.execute("MERGE INTO users KEY(id) VALUES (1, 'Test user');");
    }
  }

  public List<String> selectUserNames() {
    List<String> result = new ArrayList<>();
    try (Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT name FROM users")) {
      while (rs.next()) {
        result.add(rs.getString("name"));
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return result;
  }
}
