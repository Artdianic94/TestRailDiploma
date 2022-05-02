package database;

import utilities.PropertiesManager;

import java.sql.*;

public class DataBaseUtils {

    public Connection connect;
    Statement statement;
    String email;
    String password;
    String myNickname;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getMyNickname() {
        return myNickname;
    }

    public DataBaseUtils() {
    }

    public DataBaseUtils(String email, String password, String myNickname) {
        this.email = email;
        this.password = password;
        this.myNickname = myNickname;
    }

    public void connect() {
        PropertiesManager propertiesManager = new PropertiesManager();
        String databaseUrl = propertiesManager.get("MYSQL_URL");
        try {
            connect = DriverManager
                    .getConnection(databaseUrl);
            statement = connect.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ResultSet selectFrom(String tableName) {
        try {
            return statement
                    .executeQuery(String.format("SELECT * FROM %s;", tableName));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public DataBaseUtils getDataFromDB(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            email = resultSet.getString("Email");
            password = resultSet.getString("Password");
            myNickname = resultSet.getString("MyNickname");
        }
        return new DataBaseUtils(email, password, myNickname);
    }

    public void close() {
        try {

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception ignored) {
        }
    }
}
