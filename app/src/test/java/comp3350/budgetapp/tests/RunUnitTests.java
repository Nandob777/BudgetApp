package comp3350.budgetapp.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import comp3350.budgetapp.tests.business.*;
import comp3350.budgetapp.tests.objects.*;
import comp3350.budgetapp.tests.persistence.*;

public class RunUnitTests extends TestSuite
{
    public static TestSuite suite;
    public static TesterMain main;

    public static Test suite()
    {
        main.startUp();
        suite = new TestSuite("Running all unit tests");
        testObjects();
        testBusiness();
        testPersistence();
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
        suite.addTestSuite(CalculateTest.class);
        suite.addTestSuite(AccessWishListItemsTest.class);
        suite.addTestSuite(AccessExpensesTest.class);
        suite.addTestSuite(AccessIncomeSourceTest.class);
        suite.addTestSuite(SavingsTest.class);
    }

    private static void testPersistence()
    {
        suite.addTestSuite(DataAccessTest.class);
        suite.addTestSuite(DataAccessStubTest.class);
        suite.addTestSuite(DataAccessFactoryTest.class);
    }
}
