package comp3350.budgetapp.persistence;

public class DataAccessFactory {

    public static DataAccess getDataAccess(String dbName){
        return new DataAccessObject(dbName);
    }

    public static DataAccess getDataAccess(String dbName, boolean useless){
        return new DataAccessObject(dbName);
    }
}
