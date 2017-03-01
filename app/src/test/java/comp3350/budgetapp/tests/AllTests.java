package comp3350.budgetapp.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import comp3350.budgetapp.tests.business.CalculateWishListTotalTest;
import comp3350.budgetapp.tests.objects.IncomeSourceTest;
import comp3350.budgetapp.tests.objects.WishListItemTest;

public class AllTests extends TestSuite
{
    public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("All tests");
        testObjects();
        testBusiness();
        return suite;
    }

    private static void testObjects()
    {
        suite.addTestSuite(WishListItemTest.class);
        suite.addTestSuite(IncomeSourceTest.class);
    }

    private static void testBusiness()
    {
        suite.addTestSuite(CalculateWishListTotalTest.class);
    }
}
