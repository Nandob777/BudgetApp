package comp3350.budgetapp.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import comp3350.budgetapp.tests.business.*;
import comp3350.budgetapp.tests.objects.*;
import comp3350.budgetapp.tests.persistence.*;

public class AllTests extends TestSuite
{
    public static TestSuite suite;
    public static TesterMain main;

    public static Test suite()
    {
        main.startUp();
        suite = new TestSuite("All tests");
        testObjects();
        testBusiness();
        testPersistence();
        main.shutDown();
        return suite;

    }

    private static void testObjects()
    {
        suite.addTestSuite(WishListItemTest.class);
        suite.addTestSuite(IncomeSourceTest.class);
        suite.addTestSuite(ExpenseTest.class);
    }

    private static void testBusiness()
    {
        suite.addTestSuite(AccessWishListItemsTest.class);
        suite.addTestSuite(CalculateWishListTotalTest.class);
        suite.addTestSuite(CalculateIncomeSourceTotalTest.class);
        suite.addTestSuite(CalculateExpenseTotalTest.class);
    }

    private static void testPersistence()
    {
        suite.addTestSuite(DataAccessTest.class);
        suite.addTestSuite(DataAccessStubTest.class);
        suite.addTestSuite(DataAccessFactoryTest.class);
    }
}
