package ro.ase.acs;

import ro.ase.acs.sql.SqlDatabase;

public class Main {
    public static void main(String[] args) {
        Orchestrator orchestrator = new Orchestrator(new  SqlDatabase());
        orchestrator.execute();
    }
}
