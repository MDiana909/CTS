package ro.ase.acs.utils;

public interface DatabaseOperations<T> {
    T createConnection();

    void createTable(T dbConnection);

    void insertData(T dbConnection);

    void readData(T dbConnection);
}
