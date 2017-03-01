package comp3350.budgetapp.application;

import comp3350.budgetapp.presentation.CLI;

public class Main
{
    public static final String dbName="Budget";

    public static void main(String[] args)
    {
        startUp();

        CLI.run();

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
