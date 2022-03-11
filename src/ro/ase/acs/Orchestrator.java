package ro.ase.acs;

import ro.ase.acs.utils.DatabaseOperations;

import java.sql.Connection;
import java.sql.SQLException;

public class Orchestrator {
    private DatabaseOperations dbOp;

    public Orchestrator(DatabaseOperations dbOp) {
        this.dbOp = dbOp;
    }

    public void execute() {
        try {
            Connection connection = (Connection) dbOp.createConnection();

            dbOp.createTable(connection);
            dbOp.insertData(connection);
            dbOp.readData(connection);

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
