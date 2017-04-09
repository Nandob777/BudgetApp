package comp3350.budgetapp.tests.integration;

import junit.framework.TestCase;

import comp3350.budgetapp.application.Main;
import comp3350.budgetapp.application.Services;

import comp3350.budgetapp.business.AccessIncomeSource;
import comp3350.budgetapp.business.AccessWishListItems;
import comp3350.budgetapp.business.AccessExpenses;
import comp3350.budgetapp.business.Calculate;

import comp3350.budgetapp.objects.*;
import comp3350.budgetapp.persistence.*;


public class BusinessPersistenceSeamTest extends TestCase{

    public BusinessPersistenceSeamTest(String arg0)
    {
        super(arg0);
    }

    public void testAccessIncomeSource()
    {
        AccessIncomeSource accessIncomeSource;
        IncomeSource source;
        IncomeSource tmpSrc;
        String result;

        Services.closeDataAccess();

        System.out.println("\nStarting Integration test of AccessIncomeSource to persistence");

        Services.createDataAccess(Main.dbName);

        accessIncomeSource = new AccessIncomeSource();

        //getSequential()
        source = accessIncomeSource.getSequential();
        assertNotNull(source);
        assertEquals("Starbucks", source.getName());
        assertEquals("Monthly",source.getType());

        source = accessIncomeSource.getSequential();
        assertNotNull(source);
        assertEquals("Bartending", source.getName());
        assertFalse("Security Analyst".equals(source.getName()));

        source = accessIncomeSource.getSequential();
        assertNull(source);

        //getRandom()
        source = accessIncomeSource.getRandom("Bartending");
        assertNotNull(source);
        assertEquals("Bartending",source.getName());

        source = accessIncomeSource.getRandom("Starbucks");
        assertNotNull(source);
        assertEquals("Starbucks",source.getName());

        source = accessIncomeSource.getRandom("Security Analyst");
        assertNull(source);

        //addIncomeSource()
        source = new IncomeSource("Security Analyst",1200.0,"Bi-weekly");
        result = accessIncomeSource.addIncomeSource(source);
        tmpSrc = accessIncomeSource.getRandom(source.getName());
        assertNotNull(tmpSrc);
        assertEquals(source,tmpSrc);
        assertEquals(source.getName(),tmpSrc.getName());
        assertEquals(source.getAmount(),tmpSrc.getAmount());
        assertNull(result);

        source = new IncomeSource("Personal Assistant",1000.0,"Bi-weekly");
        result = accessIncomeSource.addIncomeSource(source);
        tmpSrc = accessIncomeSource.getRandom(source.getName());
        assertNotNull(tmpSrc);
        assertEquals(source,tmpSrc);
        assertEquals(source.getName(),tmpSrc.getName());
        assertEquals(source.getAmount(),tmpSrc.getAmount());
        assertNull(result);

        //updateIncomeSource
        tmpSrc = accessIncomeSource.getRandom("Security Analyst");
        assertNotNull(tmpSrc);
        assertEquals("Security Analyst",tmpSrc.getName());
        assertEquals(1200.00,tmpSrc.getAmount());
        source = new IncomeSource("Security Analyst", 1270.00,"Bi-weekly");
        result = accessIncomeSource.updateIncomeSource(source);
        tmpSrc = accessIncomeSource.getRandom(source.getName());
        assertNotNull(tmpSrc);
        assertEquals(source,tmpSrc);
        assertEquals(source.getName(),tmpSrc.getName());
        assertEquals(source.getAmount(),tmpSrc.getAmount());
        assertNull(result);

        tmpSrc = accessIncomeSource.getRandom("Personal Assistant");
        assertNotNull(tmpSrc);
        assertEquals("Personal Assistant",tmpSrc.getName());
        assertEquals(1000.00,tmpSrc.getAmount());
        source = new IncomeSource("Personal Assistant", 1090.00,"Bi-weekly");
        result = accessIncomeSource.updateIncomeSource(source);
        tmpSrc = accessIncomeSource.getRandom(source.getName());
        assertNotNull(tmpSrc);
        assertEquals(source,tmpSrc);
        assertEquals(source.getName(),tmpSrc.getName());
        assertEquals(source.getAmount(),tmpSrc.getAmount());
        assertNull(result);

        source = new IncomeSource("Application Developer", 1600.00,"Weekly");
        result = accessIncomeSource.updateIncomeSource(source);
        assertEquals("Tuple not inserted correctly.",result);

        //deleteIncomeSource()
        source = accessIncomeSource.getRandom("Security Analyst");
        result = accessIncomeSource.deleteIncomeSource(source);
        source = accessIncomeSource.getRandom("Security Analyst");
        assertNull(source);
        assertNull(result);

        source = accessIncomeSource.getRandom("Personal Assistant");
        result = accessIncomeSource.deleteIncomeSource(source);
        source = accessIncomeSource.getRandom("Personal Assistant");
        assertNull(source);
        assertNull(result);

        source = new IncomeSource("Cashier",350.0);
        result = accessIncomeSource.deleteIncomeSource(source);
        assertEquals("Tuple not inserted correctly.",result);

        Services.closeDataAccess();

        System.out.println("Finished Integration test of AccessIncomeSource to persistence");
    }

    public void testAccessExpenses()
    {
        AccessExpenses accessExpenses;
        Expense expense;
        Expense tmpExp;
        String result;

        Services.closeDataAccess();

        System.out.println("\nStarting Integration test of testAccessExpenses to persistence");

        Services.createDataAccess(Main.dbName);
        accessExpenses = new AccessExpenses();

        //getSequential()
        expense = accessExpenses.getSequential();
        assertNotNull(expense);
        assertEquals("Rent", expense.getName());
        assertFalse("Phone Bill".equals(expense.getName()));

        expense = accessExpenses.getSequential();
        assertNotNull(expense);
        assertEquals("Internet", expense.getName());

        expense = accessExpenses.getSequential();
        assertNull(expense);

        //getRandom()
        expense = accessExpenses.getRandom("Rent");
        assertNotNull(expense);
        assertEquals("Rent", expense.getName());

        expense = accessExpenses.getRandom("Internet");
        assertNotNull(expense);
        assertEquals("Internet", expense.getName());

        expense = accessExpenses.getRandom("Groceries");
        assertNull(expense);

        //addExpense()
        expense = new Expense("Electricity Bill",100.0);
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

        //updateExpense()
        tmpExp = accessExpenses.getRandom("Electricity Bill");
        assertEquals("Electricity Bill",tmpExp.getName());
        assertEquals(100.00,tmpExp.getAmount());
        expense = new Expense("Electricity Bill",160.00);
        result = accessExpenses.updateExpense(expense);
        tmpExp = accessExpenses.getRandom(expense.getName());
        assertNotNull(tmpExp);
        assertEquals(expense,tmpExp);
        assertEquals(expense.getName(),tmpExp.getName());
        assertEquals(expense.getAmount(),tmpExp.getAmount());
        assertNull(result);

        tmpExp = accessExpenses.getRandom("Groceries");
        assertEquals("Groceries",tmpExp.getName());
        assertEquals(50.00,tmpExp.getAmount());
        expense = new Expense("Groceries",90.00);
        result = accessExpenses.updateExpense(expense);
        tmpExp = accessExpenses.getRandom(expense.getName());
        assertNotNull(tmpExp);
        assertEquals(expense,tmpExp);
        assertEquals(expense.getName(),tmpExp.getName());
        assertEquals(expense.getAmount(),tmpExp.getAmount());
        assertNull(result);

        expense = new Expense("Phone Bill",60.00);
        result = accessExpenses.updateExpense(expense);
        assertEquals("Tuple not inserted correctly.",result);

        //deleteExpense()
        tmpExp = accessExpenses.getRandom("Electricity Bill");
        result = accessExpenses.deleteExpense(tmpExp);
        tmpExp = accessExpenses.getRandom(tmpExp.getName());
        assertNull(tmpExp);
        assertNull(result);

        tmpExp = accessExpenses.getRandom("Groceries");
        result = accessExpenses.deleteExpense(tmpExp);
        tmpExp = accessExpenses.getRandom(tmpExp.getName());
        assertNull(tmpExp);
        assertNull(result);

        expense = new Expense("Phone Bill",60.00);
        result = accessExpenses.deleteExpense(expense);
        assertEquals("Tuple not inserted correctly.",result);

        Services.closeDataAccess();

        System.out.println("Finished Integration test of testAccessExpenses to persistence");
    }

    public void testAccessWishListItems()
    {
        AccessWishListItems accessItems;
        WishListItem item;
        WishListItem tmpItem;
        String result;

        Services.closeDataAccess();

        System.out.println("\nStarting Integration test of testAccessWishListItems to persistence");

        Services.createDataAccess(Main.dbName);
        accessItems = new AccessWishListItems();

        //getSequential()
        item = accessItems.getSequential();
        assertNotNull(item);
        assertEquals("Google Pixel",item.getName());

        item = accessItems.getSequential();
        assertNotNull(item);
        assertEquals("Google Home",item.getName());
        assertFalse("Google iPhone".equals(item.getName()));

        item = accessItems.getSequential();
        assertNotNull(item);
        assertEquals("Daydream View",item.getName());

        item = accessItems.getSequential();
        assertNotNull(item);
        assertEquals("ChromeCast",item.getName());

        item = accessItems.getSequential();
        assertNull(item);

        //getRandom()
        item = accessItems.getRandom("Google Pixel");
        assertNotNull(item);
        assertEquals("Google Pixel",item.getName());

        item = accessItems.getRandom("Google Home");
        assertNotNull(item);
        assertEquals("Google Home",item.getName());

        item = accessItems.getRandom("Daydream View");
        assertNotNull(item);
        assertEquals("Daydream View",item.getName());

        item = accessItems.getRandom("ChromeCast");
        assertNotNull(item);
        assertEquals("ChromeCast",item.getName());

        item = accessItems.getRandom("Google iPhone");
        assertEquals(null,item);

        //addItem()
        item = new WishListItem("MacBook Pro",1200.0);
        result = accessItems.addWishListItem(item);
        tmpItem = accessItems.getRandom(item.getName());
        assertNotNull(tmpItem);
        assertEquals(item,tmpItem);
        assertEquals(item.getName(),tmpItem.getName());
        assertEquals(item.getAmount(),tmpItem.getAmount());
        assertNull(result);

        item = new WishListItem("Apple iPad",449.0);
        result = accessItems.addWishListItem(item);
        tmpItem = accessItems.getRandom(item.getName());
        assertNotNull(tmpItem);
        assertEquals(item,tmpItem);
        assertEquals(item.getName(),tmpItem.getName());
        assertEquals(item.getAmount(),tmpItem.getAmount());
        assertNull(result);

        //updateItem()
        tmpItem = accessItems.getRandom("MacBook Pro");
        assertEquals("MacBook Pro",tmpItem.getName());
        assertEquals(1200.00,tmpItem.getAmount());
        item = new WishListItem("MacBook Pro",1099.0);
        result = accessItems.updateWishListItem(item);
        tmpItem = accessItems.getRandom(item.getName());
        assertNotNull(tmpItem);
        assertEquals(item,tmpItem);
        assertEquals(item.getName(),tmpItem.getName());
        assertEquals(item.getAmount(),tmpItem.getAmount());
        assertNull(result);

        tmpItem = accessItems.getRandom("Apple iPad");
        assertEquals("Apple iPad",tmpItem.getName());
        assertEquals(449.00,tmpItem.getAmount());
        item = new WishListItem("Apple iPad",99.0);
        result = accessItems.updateWishListItem(item);
        tmpItem = accessItems.getRandom(item.getName());
        assertNotNull(tmpItem);
        assertEquals(item,tmpItem);
        assertEquals(item.getName(),tmpItem.getName());
        assertEquals(item.getAmount(),tmpItem.getAmount());
        assertNull(result);

        item = new WishListItem("Apple Watch",359.0);
        result = accessItems.updateWishListItem(item);
        assertEquals("Tuple not inserted correctly.",result);

        //deleteItem()
        item = accessItems.getRandom("MacBook Pro");
        result = accessItems.deleteWishListItem(item);
        item = accessItems.getRandom("MacBook Pro");
        assertNull(item);
        assertNull(result);

        item = accessItems.getRandom("Apple iPad");
        result = accessItems.deleteWishListItem(item);
        item = accessItems.getRandom("Apple iPad");
        assertNull(item);
        assertNull(result);

        item = new WishListItem("Apple Watch",359.0);
        result = accessItems.deleteWishListItem(item);
        assertEquals("Tuple not inserted correctly.",result);

        Services.closeDataAccess();

        System.out.println("Finished Integration test of testAccessWishListItems to persistence");
    }
}
