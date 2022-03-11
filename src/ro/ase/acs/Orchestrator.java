package ro.ase.acs;

import ro.ase.acs.utils.DatabaseOperations;

import java.sql.Connection;
import java.sql.SQLException;

public class Orchestrator {
    private DatabaseOperations databaseOperations;

    public Orchestrator(DatabaseOperations databaseOperations) {
        this.databaseOperations = databaseOperations;
    }

    public void execute() {
        try {
            Connection connection = (Connection) databaseOperations.createConnection();

            databaseOperations.createTable(connection);
            databaseOperations.insertData(connection);
            databaseOperations.readData(connection);

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
