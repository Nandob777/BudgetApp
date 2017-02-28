package comp3350.budgetapp.application;

/**
 * Created by mardelmaduro on 2017-02-27.
 */
import comp3350.budgetapp.persistence.DBMSstub.DBManSys;

public class Services {
    private static DBManSys dataAccessService = null;

    public static DBManSys createDataAccess(String dbName)
    {
        if (dataAccessService == null)
        {
            dataAccessService = new DBManSys();
            dataAccessService.loadDB();
        }
        return dataAccessService;
    }

    public static DBManSys getDataAccess(String dbName)
    {
        if (dataAccessService == null)
        {
            System.out.println("Connection to data access has not been established.");
            System.exit(1);
        }
        return dataAccessService;
    }

    public static void closeDataAccess()
    {
        if (dataAccessService != null)
        {
            dataAccessService.close();
        }
        dataAccessService = null;
    }
}
