package ro.ase.acs;

import ro.ase.acs.utils.DatabaseOperations;

public class Main {
    public static void main(String[] args) throws Exception {
        DatabaseOperations databaseOperationsSql = (DatabaseOperations) Class.forName("ro.ase.acs.sql.SqlDatabase").getDeclaredConstructor().newInstance();
//        DatabaseOperations databaseOperationsNoSql = (DatabaseOperations) Class.forName("ro.ase.acs.nosql.NoSqlDatabase").getDeclaredConstructor().newInstance();

        Orchestrator orchestrator = new Orchestrator(databaseOperationsSql);
        orchestrator.execute();
    }
}
