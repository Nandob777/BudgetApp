package comp3350.budgetapp.tests.persistence;

import junit.framework.TestCase;

import java.util.ArrayList;

import comp3350.budgetapp.application.*;
import comp3350.budgetapp.objects.*;
import comp3350.budgetapp.persistence.DataAccess;

public class DataAccessTest extends TestCase {

    private static String dbName = Main.dbName;

    public static WishListItem wish1, wish2;
    public static Expense expense1, expense2;
    public static IncomeSource incomeSource1, incomeSource2;

    public DataAccessTest(String arg0)
    {
        super(arg0);
    }

    public void testDataAccess()
    {
        DataAccess dataAccess;

        Services.closeDataAccess();

        System.out.println("\nStarting Persistence test DataAccess (using stub)");

        // Use the following statement to run with the stub database
        dataAccess = Services.createDataAccess(new DataAccessStub(dbName));

        dataAccessTest();

        System.out.println("Finished Persistence test DataAccess (using stub)");
    }

    public static void dataAccessTest() {
        DataAccess dataAccess;
        dataAccess = Services.getDataAccess(dbName);

        wishListItemTest(dataAccess);
        expenseTest(dataAccess);
        incomeSourceTest(dataAccess);

        Services.closeDataAccess();
    }

    private static void wishListItemTest(DataAccess dataAccess)
    {
        ArrayList<FinancialObjects> wishlist;
        WishListItem wish;

        String result;

        System.out.println("\nStarting wishListItemTest");

        wishlist = new ArrayList<FinancialObjects>();
        result = dataAccess.getWishListItemSequential(wishlist);
        assertNull(result);
        assertNotNull(wishlist);
        assertEquals(4, wishlist.size());

        wish = (WishListItem) wishlist.get(0);
        assertEquals(899.00, wish.getAmount());
        assertEquals("Google Pixel",wish.getName());
        wish = (WishListItem) wishlist.get(1);
        assertEquals(130.00, wish.getAmount());
        assertEquals("Google Home",wish.getName());
        wish = (WishListItem) wishlist.get(2);
        assertEquals(99.00, wish.getAmount());
        assertEquals("Daydream View",wish.getName());
        wish = (WishListItem) wishlist.get(3);
        assertEquals(35.00, wish.getAmount());
        assertEquals("ChromeCast",wish.getName());

        wish1 = new WishListItem("Samsung S9", 499.99);
        dataAccess.addWishListItem(wish1);
        wish2 = new WishListItem("Samsung Watch", 200.99);
        dataAccess.addWishListItem(wish2);

        result = dataAccess.getWishListItemSequential(wishlist);
        assertEquals(6, wishlist.size());

        wish = (WishListItem) wishlist.get(4);
        assertEquals(499.99, wish.getAmount());
        assertEquals("Samsung S9",wish.getName());
        wish = (WishListItem) wishlist.get(5);
        assertEquals(200.99, wish.getAmount());
        assertEquals("Samsung Watch",wish.getName());

        dataAccess.deleteWishListItem(wish1);

        result = dataAccess.getWishListItemSequential(wishlist);
        assertEquals(5,wishlist.size());

        wish2 = new WishListItem("Samsung Watch", 150.99);
        dataAccess.updateWishListItem(wish2);
        assertTrue(wish2.equals(wishlist.get(4)));

        dataAccess.deleteWishListItem(wish2);

        result = dataAccess.getWishListItemSequential(wishlist);
        assertEquals(4,wishlist.size());

        System.out.println("Finished wishListItemTest");

    }

    private static void incomeSourceTest(DataAccess dataAccess)
    {
        ArrayList<FinancialObjects> incomelist;
        IncomeSource income;

        String result;

        System.out.println("\nStarting incomeSourceTest");

        incomelist = new ArrayList<FinancialObjects>();
        result = dataAccess.getIncomeSourceSequential(incomelist);
        assertNull(result);
        assertNotNull(incomelist);
        assertEquals(2, incomelist.size());

        income = (IncomeSource) incomelist.get(0);
        assertEquals("Starbucks", income.getName());
        assertEquals(800.00,income.getAmount());
        assertEquals("Monthly",income.getType());

        income = (IncomeSource) incomelist.get(1);
        assertEquals("Bartending", income.getName());
        assertEquals(700.00,income.getAmount());
        assertEquals("Monthly",income.getType());

        incomeSource1 = new IncomeSource("Web Developer",700.0,"Weekly");
        dataAccess.addIncomeSource(incomeSource1);
        incomeSource2 = new IncomeSource("Teaching Assistant",600.0,"Bi-weekly");
        dataAccess.addIncomeSource(incomeSource2);

        dataAccess.getIncomeSourceSequential(incomelist);
        assertEquals(4,incomelist.size());

        income = (IncomeSource) incomelist.get(2);
        assertEquals(700.0,income.getAmount());
        assertEquals("Weekly",income.getType());
        assertEquals("Web Developer",income.getName());
        income = (IncomeSource) incomelist.get(3);
        assertEquals(600.0,income.getAmount());
        assertEquals("Bi-weekly",income.getType());

        assertEquals("Teaching Assistant",income.getName());

        dataAccess.deleteIncomeSource(incomeSource1);
        dataAccess.getIncomeSourceSequential(incomelist);
        assertEquals(3,incomelist.size());

        incomeSource2 = new IncomeSource("Teaching Assistant",670.0,"Bi-weekly");
        dataAccess.updateIncomeSource(incomeSource2);
        income = (IncomeSource) dataAccess.getIncomeSourceRandom(incomeSource2).get(0);
        assertTrue(incomeSource2.equals(incomelist.get(2)));
        assertEquals(670.0,income.getAmount());
        assertEquals("Bi-weekly",income.getType());

        dataAccess.deleteIncomeSource(incomeSource2);
        dataAccess.getIncomeSourceSequential(incomelist);
        assertEquals(2,incomelist.size());

        System.out.println("Finished incomeSourceTest");

    }

    private static void expenseTest(DataAccess dataAccess)
    {
        ArrayList<FinancialObjects> expenselist;
        Expense expense;

        String result;

        System.out.println("\nStarting expenseTest");

        expenselist = new ArrayList<FinancialObjects>();
        result = dataAccess.getExpenseSequential(expenselist);
        assertNull(result);
        assertNotNull(expenselist);
        assertEquals(2, expenselist.size());

        expense = (Expense) expenselist.get(0);
        assertEquals("Rent", expense.getName());
        assertEquals(500.00,expense.getAmount());
        expense = (Expense) expenselist.get(1);
        assertEquals("Internet", expense.getName());
        assertEquals(120.00,expense.getAmount());

        expense1 = new Expense("Cellphone Bill",60.0);
        dataAccess.addExpense(expense1);
        expense2 = new Expense("Hydro",100.0);
        dataAccess.addExpense(expense2);

        dataAccess.getExpenseSequential(expenselist);
        assertEquals(4,expenselist.size());

        expense = (Expense) expenselist.get(2);
        assertEquals(60.0,expense.getAmount());
        assertEquals("Cellphone Bill",expense.getName());
        expense = (Expense) expenselist.get(3);
        assertEquals(100.0,expense.getAmount());
        assertEquals("Hydro",expense.getName());

        dataAccess.deleteExpense(expense1);
        dataAccess.getExpenseSequential(expenselist);
        assertEquals(3,expenselist.size());

        expense2 = new Expense("Hydro",150.00);
        dataAccess.updateExpense(expense2);
        assertTrue(expense2.equals(expenselist.get(2)));

        dataAccess.deleteExpense(expense2);
        dataAccess.getExpenseSequential(expenselist);
        assertEquals(2,expenselist.size());

        System.out.println("Finished expenseTest");

    }
}
