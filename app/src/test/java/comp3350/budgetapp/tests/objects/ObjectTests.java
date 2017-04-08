package comp3350.budgetapp.tests.objects;
import junit.framework.Test;
import junit.framework.TestSuite;

public class ObjectTests
{
    public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("Domain objects tests");
        suite.addTestSuite(WishListItemTest.class);
        suite.addTestSuite(IncomeSourceTest.class);
        suite.addTestSuite(ExpenseTest.class);
        return suite;
    }
}
