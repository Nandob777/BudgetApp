package comp3350.budgetapp.tests.business;


import junit.framework.TestCase;

import java.util.ArrayList;

import comp3350.budgetapp.application.Main;
import comp3350.budgetapp.application.Services;
import comp3350.budgetapp.business.AccessExpenses;
import comp3350.budgetapp.objects.Expense;
import comp3350.budgetapp.objects.FinancialObjects;
import comp3350.budgetapp.tests.persistence.DataAccessStub;
import comp3350.budgetapp.tests.TesterMain;

public class AccessExpensesTest extends TestCase {

    private static String dbName = Main.dbName;
    private static ArrayList<FinancialObjects> list;
    private Expense expense;
    private Expense tmpExp;
    private AccessExpenses accessExpenses;
    private String result;

    public AccessExpensesTest(String arg0)
    {
        super(arg0);
        list = new ArrayList<FinancialObjects>();
    }

    public void testGetSequentialExpenseValid() {
        Services.closeDataAccess();

        System.out.println("\nStarting testGetSequentialExpenseValid");

        Services.createDataAccess(new DataAccessStub(dbName));
        accessExpenses = new AccessExpenses();

        expense = accessExpenses.getSequential();
        assertNotNull(expense);
        assertEquals("Rent", expense.getName());

        expense = accessExpenses.getSequential();
        assertNotNull(expense);
        assertEquals("Internet", expense.getName());

        Services.closeDataAccess();

        System.out.println("Finished testGetSequentialExpenseValid");
    }

    public void testGetSequentialExpenseTooMany()
    {
        Services.closeDataAccess();

        System.out.println("\nStarting testGetSequentialExpenseTooMany");

        Services.createDataAccess(new DataAccessStub(dbName));
        accessExpenses = new AccessExpenses();

        expense = accessExpenses.getSequential();
        assertNotNull(expense);
        assertEquals("Rent",expense.getName());

        expense = accessExpenses.getSequential();
        assertNotNull(expense);
        assertEquals("Internet",expense.getName());

        expense = accessExpenses.getSequential();
        assertNull(expense);

        Services.closeDataAccess();

        System.out.println("Finished testGetSequentialExpenseTooMany");
    }

    public void testGetSequentialExpenseInvalid()
    {
        Services.closeDataAccess();

        System.out.println("\nStarting testGetSequentialExpenseInvalid");

        Services.createDataAccess(new DataAccessStub(dbName));
        accessExpenses = new AccessExpenses();

        expense = accessExpenses.getSequential();
        assertNotNull(expense);
        assertFalse("Phone Bill".equals(expense.getName()));

        expense = accessExpenses.getSequential();
        assertNotNull(expense);
        assertFalse("Groceries".equals(expense.getName()));

        Services.closeDataAccess();

        System.out.println("Finished testGetSequentialExpenseInvalid");
    }

    public void testGetSequentialExpenseInvalidMixed()
    {
        Services.closeDataAccess();

        System.out.println("\nStarting testGetSequentialExpenseInvalidMixed");

        Services.createDataAccess(new DataAccessStub(dbName));
        accessExpenses = new AccessExpenses();

        expense = accessExpenses.getSequential();
        assertNotNull(expense);
        assertEquals("Rent", expense.getName());

        expense = accessExpenses.getSequential();
        assertNotNull(expense);
        assertFalse("Phone Bill".equals(expense.getName()));

        Services.closeDataAccess();

        System.out.println("Finished testGetSequentialExpenseInvalidMixed");
    }

    public void testGetRandomExpenseValid() {
        Services.closeDataAccess();

        System.out.println("\nStarting testGetRandomExpenseValid");

        Services.createDataAccess(new DataAccessStub(dbName));
        accessExpenses = new AccessExpenses();

        expense = accessExpenses.getRandom("Rent");
        assertNotNull(expense);
        assertEquals("Rent", expense.getName());

        expense = accessExpenses.getRandom("Internet");
        assertNotNull(expense);
        assertEquals("Internet", expense.getName());

        Services.closeDataAccess();

        System.out.println("Finished testGetRandomExpenseValid");
    }

    public void testGetRandomExpenseInvalid() {
        Services.closeDataAccess();

        System.out.println("\nStarting testGetRandomExpenseInvalid");

        Services.createDataAccess(new DataAccessStub(dbName));
        accessExpenses = new AccessExpenses();

        expense = accessExpenses.getRandom("Phone Bill");
        assertNull(expense);

        expense = accessExpenses.getRandom("Groceries");
        assertNull(expense);

        Services.closeDataAccess();

        System.out.println("Finished testGetRandomExpenseInvalid");
    }

    public void testGetRandomExpenseMixed() {
        Services.closeDataAccess();

        System.out.println("\nStarting testGetRandomExpenseMixed");

        Services.createDataAccess(new DataAccessStub(dbName));
        accessExpenses = new AccessExpenses();

        expense = accessExpenses.getRandom("Phone Bill");
        assertNull(expense);

        expense = accessExpenses.getRandom("Rent");
        assertNotNull(expense);
        assertEquals("Rent", expense.getName());

        expense = accessExpenses.getRandom("Groceries");
        assertNull(expense);

        expense = accessExpenses.getRandom("Internet");
        assertNotNull(expense);
        assertEquals("Internet", expense.getName());

        Services.closeDataAccess();

        System.out.println("Finished testGetRandomExpenseMixed");
    }

    public void testAddExpenses() {
        Services.closeDataAccess();

        System.out.println("\nStarting testAddExpenses");

        Services.createDataAccess(new DataAccessStub(dbName));
        accessExpenses = new AccessExpenses();

        expense = new Expense("Phone Bill",60.0);
        result = accessExpenses.addExpense(expense);
        tmpExp = accessExpenses.getRandom(expense.getName());
        assertNotNull(tmpExp);
        assertEquals(expense,tmpExp);
        assertEquals(expense.getName(),tmpExp.getName());
        assertEquals(expense.getAmount(),tmpExp.getAmount());
        assertNull(result);

        expense = new Expense("Electricty Bill",100.0);
        result = accessExpenses.addExpense(expense);
        tmpExp = accessExpenses.getRandom(expense.getName());
        assertNotNull(tmpExp);
        assertEquals(expense,tmpExp);
        assertEquals(expense.getName(),tmpExp.getName());
        assertEquals(expense.getAmount(),tmpExp.getAmount());
        assertNull(result);

        expense = new Expense("Groceries",50.0);
        result = accessExpenses.addExpense(expense);
        tmpExp = accessExpenses.getRandom(expense.getName());
        assertNotNull(tmpExp);
        assertEquals(expense,tmpExp);
        assertEquals(expense.getName(),tmpExp.getName());
        assertEquals(expense.getAmount(),tmpExp.getAmount());
        assertNull(result);

        Services.closeDataAccess();

        System.out.println("Finished testAddExpenses");
    }

    public void testUpdateValidExpenses() {
        Services.closeDataAccess();

        System.out.println("\nStarting testUpdateValidExpenses");

        Services.createDataAccess(new DataAccessStub(dbName));
        accessExpenses = new AccessExpenses();

        tmpExp = accessExpenses.getRandom("Rent");
        assertEquals("Rent",tmpExp.getName());
        assertEquals(500.00,tmpExp.getAmount());
        expense = new Expense("Rent",460.00);
        result = accessExpenses.updateExpense(expense);
        tmpExp = accessExpenses.getRandom(expense.getName());
        assertNotNull(tmpExp);
        assertEquals(expense,tmpExp);
        assertEquals(expense.getName(),tmpExp.getName());
        assertEquals(expense.getAmount(),tmpExp.getAmount());
        assertNull(result);

        tmpExp = accessExpenses.getRandom("Internet");
        assertEquals("Internet",tmpExp.getName());
        assertEquals(120.00,tmpExp.getAmount());
        expense = new Expense("Internet",90.00);
        result = accessExpenses.updateExpense(expense);
        tmpExp = accessExpenses.getRandom(expense.getName());
        assertNotNull(tmpExp);
        assertEquals(expense,tmpExp);
        assertEquals(expense.getName(),tmpExp.getName());
        assertEquals(expense.getAmount(),tmpExp.getAmount());
        assertNull(result);

        Services.closeDataAccess();

        System.out.println("Finished testUpdateValidExpenses");
    }

    public void testUpdateInvalidExpenses() {
        Services.closeDataAccess();

        System.out.println("\nStarting testUpdateInvalidExpenses");

        Services.createDataAccess(new DataAccessStub(dbName));
        accessExpenses = new AccessExpenses();


        expense = new Expense("Phone Bill",60.00);
        result = accessExpenses.updateExpense(expense);
        assertNull(result);

        expense = new Expense("Groceries",50.00);
        result = accessExpenses.updateExpense(expense);
        assertNull(result);

        Services.closeDataAccess();

        System.out.println("Finished testUpdateInvalidExpenses");
    }

    public void testDeleteValidExpenses() {
        Services.closeDataAccess();

        System.out.println("\nStarting testDeleteValidExpenses");

        Services.createDataAccess(new DataAccessStub(dbName));
        accessExpenses = new AccessExpenses();

        tmpExp = accessExpenses.getRandom("Rent");
        result = accessExpenses.deleteExpense(tmpExp);
        tmpExp = accessExpenses.getRandom(tmpExp.getName());
        assertNull(tmpExp);
        assertNull(result);

        tmpExp = accessExpenses.getRandom("Internet");
        result = accessExpenses.deleteExpense(tmpExp);
        tmpExp = accessExpenses.getRandom(tmpExp.getName());
        assertNull(tmpExp);
        assertNull(result);

        Services.closeDataAccess();

        System.out.println("Finished testDeleteValidExpenses");
    }

    public void testDeleteInvalidExpenses() {
        Services.closeDataAccess();

        System.out.println("\nStarting testDeleteInvalidExpenses");

        Services.createDataAccess(new DataAccessStub(dbName));
        accessExpenses = new AccessExpenses();

        expense = new Expense("Phone Bill",60.00);
        result = accessExpenses.deleteExpense(expense);
        assertNull(result);

        expense = new Expense("Groceries",50.00);
        result = accessExpenses.deleteExpense(expense);
        assertNull(result);

        Services.closeDataAccess();

        System.out.println("Finished testDeleteInvalidExpenses");
    }

}
