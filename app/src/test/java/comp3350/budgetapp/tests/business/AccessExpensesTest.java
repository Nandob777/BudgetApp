package comp3350.budgetapp.tests.business;


import junit.framework.TestCase;

import comp3350.budgetapp.application.Main;
import comp3350.budgetapp.application.Services;
import comp3350.budgetapp.business.AccessExpenses;
import comp3350.budgetapp.objects.Expense;
import comp3350.budgetapp.tests.persistence.DataAccessStub;
import comp3350.budgetapp.tests.TesterMain;

public class AccessExpensesTest extends TestCase {

    private static String dbName = Main.dbName;

    public AccessExpensesTest(String arg0)
    {
        super(arg0);
    }

    public void test1()
    {
        AccessExpenses ae;
        Expense expense;

        Services.closeDataAccess();

        System.out.println("\nStarting test AccessExpensesTest");

        Services.createDataAccess(new DataAccessStub(dbName));

        expense = new Expense("Internet");

        ae = new AccessExpenses();

        expense = ae.getSequential();
        assertNotNull(expense);
        assertEquals("Internet", expense.getName());

        Services.closeDataAccess();

        System.out.println("\nFinished test AccessExpensesTest");

    }

}
