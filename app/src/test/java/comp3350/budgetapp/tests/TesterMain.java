package comp3350.budgetapp.tests;

/**
 * Created by mardelmaduro on 2017-03-01.
 */

import comp3350.budgetapp.application.Services;
import comp3350.budgetapp.presentation.CLI;


import comp3350.budgetapp.presentation.CLI;

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
