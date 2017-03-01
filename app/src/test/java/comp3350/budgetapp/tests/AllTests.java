package comp3350.budgetapp.tests;

/**
 * Created by tinahernando on 2017-02-28.
 */

import junit.framework.Test;
import junit.framework.TestSuite;

import comp3350.budgetapp.tests.objects.WishListItemTest;

public class AllTests extends TestSuite
{
    public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("All tests");
        testObjects();
        //testBusiness();
        return suite;
    }

    private static void testObjects()
    {
        suite.addTestSuite(WishListItemTest.class);
    }
}
