package comp3350.budgetapp.tests.business;

import junit.framework.TestCase;

import comp3350.budgetapp.application.Main;
import comp3350.budgetapp.application.Services;
import comp3350.budgetapp.business.AccessIncomeSource;
import comp3350.budgetapp.objects.IncomeSource;
import comp3350.budgetapp.persistence.DataAccessStub;

public class AccessIncomeSourceTest extends TestCase
{
    private static String dbName = Main.dbName;

    public AccessIncomeSourceTest(String arg0)
    {
        super(arg0);
    }

    public void test1()
    {
        AccessIncomeSource ais;
        IncomeSource incomeSrc;

        Services.closeDataAccess();

        System.out.println("\nStarting test AccessIncomeSourceTest");

        Services.createDataAccess(new DataAccessStub(dbName));

        incomeSrc = new IncomeSource("Starbucks", 800.0);

        ais = new AccessIncomeSource();

        incomeSrc = ais.getSequential();
        assertNotNull(incomeSrc);
        assertEquals("Starbucks", incomeSrc.getName());

        Services.closeDataAccess();

        System.out.println("\nFinished test AccessIncomeSourceTest");

    }
}
