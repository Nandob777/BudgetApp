package comp3350.budgetapp.tests.business;

import junit.framework.TestCase;

import java.util.ArrayList;

import comp3350.budgetapp.application.Main;
import comp3350.budgetapp.application.Services;
import comp3350.budgetapp.business.AccessExpenses;
import comp3350.budgetapp.business.AccessIncomeSource;
import comp3350.budgetapp.business.Calculate;
import comp3350.budgetapp.business.Savings;
import comp3350.budgetapp.objects.Expense;
import comp3350.budgetapp.objects.FinancialObjects;
import comp3350.budgetapp.objects.IncomeSource;
import comp3350.budgetapp.tests.persistence.DataAccessStub;


public class SavingsTest extends TestCase
{
    private static String dbName = Main.dbName;
    private static ArrayList<FinancialObjects> itemList;

    private AccessExpenses accessExpenses;
    private AccessIncomeSource accessIncomeSource;

    private Expense expense;
    private IncomeSource incomeSource;

    private Calculate calculate;
    private Savings savings;

    private String expenseTotal;
    private String incomeTotal;
    private String result;

    private double savingsTotal;

    public void setUp()
    {
        itemList = new ArrayList<FinancialObjects>();
    }

    public void testCalculateSavingsWithDefaultStub()
    {
        Services.closeDataAccess();
        System.out.println("\nStarting testCalculateSavingsWithDefaultStub.");

        Services.createDataAccess(new DataAccessStub(dbName));
        accessExpenses = new AccessExpenses();
        accessIncomeSource = new AccessIncomeSource();

        result = accessExpenses.getExpenses(itemList);
        expenseTotal = calculate.calculateTotal(itemList);
        assertNotNull(expenseTotal);
        assertEquals("620.00", expenseTotal);

        result = accessIncomeSource.getIncomeSources(itemList);
        incomeTotal = calculate.calculateTotal(itemList);
        assertNotNull(incomeTotal);
        assertEquals("1500.00", incomeTotal);

        savings = new Savings();
        assertEquals("$ 880.00", savings.toString());

        Services.closeDataAccess();

        System.out.println("Finished testCalculateSavingsWithDefaultStub.");
    }

    public void testCalculateSavingsWithAdditionalIncomeAndExpense()
    {
        Services.closeDataAccess();
        System.out.println("\nStarting testCalculateSavingsWithAdditionalIncomeAndExpense");

        Services.createDataAccess(new DataAccessStub(dbName));
        accessExpenses = new AccessExpenses();
        accessIncomeSource = new AccessIncomeSource();

        expense = new Expense("Phone Bill", 60.0);
        result = accessExpenses.addExpense(expense);

        result = accessExpenses.getExpenses(itemList);
        expenseTotal = calculate.calculateTotal(itemList);
        assertNotNull(expenseTotal);
        assertEquals("680.00", expenseTotal);


        incomeSource = new IncomeSource("Security Analyst",1200.0,"bi-weekly");
        result = accessIncomeSource.addIncomeSource(incomeSource);

        result = accessIncomeSource.getIncomeSources(itemList);
        incomeTotal = calculate.calculateTotal(itemList);
        assertNotNull(incomeTotal);
        assertEquals("3900.00", incomeTotal);

        savings = new Savings();
        assertEquals("$ 3220.00", savings.toString());

        Services.closeDataAccess();

        System.out.println("Finished testCalculateSavingsWithAdditionalIncomeAndExpense.");
    }

    public void testCalculateSavingsWithDeletedIncomeAndExpense()
    {
        Services.closeDataAccess();
        System.out.println("\nStarting testCalculateSavingsWithDeletedIncomeAndExpense");

        Services.createDataAccess(new DataAccessStub(dbName));
        accessExpenses = new AccessExpenses();
        accessIncomeSource = new AccessIncomeSource();

        expense = accessExpenses.getRandom("Rent");
        result = accessExpenses.deleteExpense(expense);

        result = accessExpenses.getExpenses(itemList);
        expenseTotal = calculate.calculateTotal(itemList);
        assertNotNull(expenseTotal);
        assertEquals("120.00", expenseTotal);


        incomeSource = accessIncomeSource.getRandom("Starbucks");
        result = accessIncomeSource.deleteIncomeSource(incomeSource);

        result = accessIncomeSource.getIncomeSources(itemList);
        incomeTotal = calculate.calculateTotal(itemList);
        assertNotNull(incomeTotal);
        assertEquals("700.00", incomeTotal);

        savings = new Savings();
        assertEquals("$ 580.00", savings.toString());

        Services.closeDataAccess();

        System.out.println("Finished testCalculateSavingsWithDeletedIncomeAndExpense.");
    }

    public void testCalculateSavingsWithAddedIncomeAndDeletedExpense()
    {
        Services.closeDataAccess();
        System.out.println("\nStarting testCalculateSavingsWithAddedIncomeAndDeletedExpense");

        Services.createDataAccess(new DataAccessStub(dbName));
        accessExpenses = new AccessExpenses();
        accessIncomeSource = new AccessIncomeSource();

        expense = accessExpenses.getRandom("Rent");
        result = accessExpenses.deleteExpense(expense);

        result = accessExpenses.getExpenses(itemList);
        expenseTotal = calculate.calculateTotal(itemList);
        assertNotNull(expenseTotal);
        assertEquals("120.00", expenseTotal);


        incomeSource = new IncomeSource("Security Analyst",1200.0,"bi-weekly");
        result = accessIncomeSource.addIncomeSource(incomeSource);

        result = accessIncomeSource.getIncomeSources(itemList);
        incomeTotal = calculate.calculateTotal(itemList);
        assertNotNull(incomeTotal);
        assertEquals("3900.00", incomeTotal);

        savings = new Savings();
        assertEquals("$ 3780.00", savings.toString());

        Services.closeDataAccess();

        System.out.println("Finished testCalculateSavingsWithAddedIncomeAndDeletedExpense.");
    }

    public void testCalculateSavingsWithDeletedIncomeAndAddedExpense()
    {
        Services.closeDataAccess();
        System.out.println("\nStarting testCalculateSavingsWithDeletedIncomeAndAddedExpense");

        Services.createDataAccess(new DataAccessStub(dbName));
        accessExpenses = new AccessExpenses();
        accessIncomeSource = new AccessIncomeSource();

        expense = new Expense("Phone Bill", 60.0);
        result = accessExpenses.addExpense(expense);

        result = accessExpenses.getExpenses(itemList);
        expenseTotal = calculate.calculateTotal(itemList);
        assertNotNull(expenseTotal);
        assertEquals("680.00", expenseTotal);


        incomeSource = accessIncomeSource.getRandom("Starbucks");
        result = accessIncomeSource.deleteIncomeSource(incomeSource);

        result = accessIncomeSource.getIncomeSources(itemList);
        incomeTotal = calculate.calculateTotal(itemList);
        assertNotNull(incomeTotal);
        assertEquals("700.00", incomeTotal);

        savings = new Savings();
        assertEquals("$ 20.00", savings.toString());

        Services.closeDataAccess();

        System.out.println("Finished testCalculateSavingsWithDeletedIncomeAndAddedExpense.");
    }

}
