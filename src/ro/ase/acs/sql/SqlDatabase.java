package ro.ase.acs.sql;

import ro.ase.acs.utils.DatabaseOperations;

import java.sql.*;

public class SqlDatabase implements DatabaseOperations<Connection> {


    @Override
    public Connection createConnection() {
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connection;
    }

    @Override
    public void createTable(Connection connection) {
        try {
            String sqlDrop = "DROP TABLE IF EXISTS employees";
            String sqlCreate = "CREATE TABLE employees(id INTEGER PRIMARY KEY,"
                    + "name TEXT, address TEXT, salary REAL)";

            Statement statement = null;

            statement = connection.createStatement();

            statement.executeUpdate(sqlDrop);
            statement.executeUpdate(sqlCreate);
            statement.close();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertData(Connection connection) {
        try {
            String sqlInsert = "INSERT INTO employees VALUES(1, 'Popescu Ion', 'Bucharest', 4000)";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlInsert);
            statement.close();

            String sqlInsertWithParams = "INSERT INTO employees VALUES (?,?,?,?)";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sqlInsertWithParams);
            preparedStatement.setInt(1, 2);
            preparedStatement.setString(2, "Ionescu Vasile");
            preparedStatement.setString(3, "Brasov");
            preparedStatement.setDouble(4, 4500);
            preparedStatement.executeUpdate();
            preparedStatement.close();

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readData(Connection connection) {
        try {
            String sqlSelect = "SELECT * FROM employees";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sqlSelect);
            while (rs.next()) {
                int id = rs.getInt("id");
                System.out.println("id: " + id);
                String name = rs.getString(2);
                System.out.println("name: " + name);
                String address = rs.getString("address");
                System.out.println("address: " + address);
                double salary = rs.getDouble("salary");
                System.out.println("salary: " + salary);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
