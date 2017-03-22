package comp3350.budgetapp.tests;


import comp3350.budgetapp.application.Services;

public class TesterMain
{
    public static final String dbName="Budget";

    public static void main()
    {
        startUp();

        AllTests.suite();

        shutDown();
        System.out.println("All done");
    }

    public static void startUp()
    {
        Services.createDataAccess(dbName);
    }

    public static void shutDown()
    {
        Services.closeDataAccess();
    }

}
