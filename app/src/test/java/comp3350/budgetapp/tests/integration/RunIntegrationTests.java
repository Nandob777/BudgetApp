package comp3350.budgetapp.tests.integration;

import junit.framework.Test;
import junit.framework.TestSuite;

public class RunIntegrationTests
{
    public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("Running all integration tests");
        suite.addTestSuite(BusinessPersistenceSeamTest.class);
        suite.addTestSuite(DataAccessHSQLDBTest.class);
        return suite;
    }
}
