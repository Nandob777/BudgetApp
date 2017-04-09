package comp3350.budgetapp.tests.business;

import junit.framework.Test;
import junit.framework.TestSuite;

public class BusinessTests
{
    public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("Business tests");
        suite.addTestSuite(CalculateTest.class);
        suite.addTestSuite(AccessWishListItemsTest.class);
        suite.addTestSuite(AccessExpensesTest.class);
        suite.addTestSuite(AccessIncomeSourceTest.class);
        suite.addTestSuite(SavingsTest.class);
        return suite;
    }
}
