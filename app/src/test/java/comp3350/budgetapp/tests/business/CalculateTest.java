package comp3350.budgetapp.tests.business;

import junit.framework.TestCase;
import java.util.ArrayList;
import comp3350.budgetapp.business.Calculate;
import comp3350.budgetapp.objects.*;

public class CalculateTest extends TestCase {
    private static ArrayList<FinancialObjects> list;
    private IncomeSource income;
    private Expense exp;
    private WishListItem wish;

    public void setUp() {
        list = new ArrayList<>();
    }

    public void testNullList() {
        System.out.println("\nStarting testNullList");

        assertEquals("", Calculate.calculateTotal(null));

        System.out.println("Finished testNullList");
    }

    public void testEmptyList() {
        System.out.println("\nStarting testEmptyList");

        assertNotNull(list);
        assertEquals("", Calculate.calculateTotal(list));

        System.out.println("Finished testEmptyList");
    }

    public void testValidIncomeList() {
        System.out.println("\nStarting testValidIncomeList");

        income = new IncomeSource("Starbucks", 500.0);
        list.add(income);
        income = new IncomeSource("Application Developer", 1000.0);
        list.add(income);
        income = new IncomeSource("Allowance", 20.0);
        list.add(income);

        assertEquals("1520.00", Calculate.calculateTotal(list));

        System.out.println("Finished testValidIncomeList");
    }

    public void testOneIncomeSource() {
        System.out.println("\nStarting testOneIncomeSource");

        income = new IncomeSource("Starbucks", 500.0);
        list.add(income);
        assertEquals("500.00", Calculate.calculateTotal(list));

        income = new IncomeSource("Application Developer", 1000.0);
        list.set(0, income);
        assertEquals("1000.00", Calculate.calculateTotal(list));

        income = new IncomeSource("Contractor", 5000.0);
        list.set(0, income);
        assertEquals("5000.00", Calculate.calculateTotal(list));

        income = new IncomeSource("Sales Associate", 400.0);
        list.set(0, income);
        assertEquals("400.00", Calculate.calculateTotal(list));

        System.out.println("Finished testOneIncomeSource");
    }

    public void testOddIncomeSourceList() {
        System.out.println("\nStarting testOddIncomeSourceList");

        income = new IncomeSource("Starbucks", 399.9999999);
        list.add(income);
        income = new IncomeSource("Contractor", 5999.9999999);
        list.add(income);
        income = new IncomeSource("Allowance", 19.9999999);
        list.add(income);

        assertEquals("6420.00", Calculate.calculateTotal(list));

        System.out.println("Finished testOddIncomeSourceList");
    }

    public void testBlankIncomes() {
        System.out.println("\nStarting testBlankIncomes");

        income = new IncomeSource("Starbucks");
        list.add(income);

        income = new IncomeSource("IBM");
        list.add(income);

        income = new IncomeSource("Blackberry");
        list.add(income);

        income = new IncomeSource("H&M");
        list.add(income);

        income = new IncomeSource("Allowance");
        list.add(income);


        assertEquals("0.00", Calculate.calculateTotal(list));

        System.out.println("Finished testBlankIncomes");
    }

    public void testSomeBlankIncomes() {
        System.out.println("\nStarting testSomeBlankIncomes");

        income = new IncomeSource("Starbucks", 500.00);
        list.add(income);

        income = new IncomeSource("Allowance", 20.00);
        list.add(income);

        income = new IncomeSource("Blackberry", 4000.00);
        list.add(income);

        income = new IncomeSource("H&M");
        list.add(income);

        income = new IncomeSource("IBM");
        list.add(income);

        assertEquals("4520.00", Calculate.calculateTotal(list));

        System.out.println("Finished testSomeBlankIncomes");
    }

    public void testMixedBlankIncomes() {
        System.out.println("\nStarting testMixedBlankIncomes");

        income = new IncomeSource("Starbucks", 500.00);
        list.add(income);

        income = new IncomeSource("IBM");
        list.add(income);

        income = new IncomeSource("Blackberry", 4000.00);
        list.add(income);

        income = new IncomeSource("H&M");
        list.add(income);

        income = new IncomeSource("Allowance", 20.00);
        list.add(income);

        assertEquals("4520.00", Calculate.calculateTotal(list));

        System.out.println("Finished testMixedBlankIncomes");
    }

    public void testNullItemInIncomeSourceList() {
        System.out.println("\nStarting testNullItemInIncomeSourceList");

        income = new IncomeSource("Starbucks", 500.00);
        list.add(income);

        income = new IncomeSource("Allowance", 20.00);
        list.add(income);

        list.add(null);

        assertEquals("?", Calculate.calculateTotal(list));//invalid total

        System.out.println("Finished testNullItemInIncomeSourceList");
    }

    public void testNegativeIncomes() {
        System.out.println("\nStarting testNegativeIncomes");

        income = new IncomeSource("Starbucks", -(500.00));
        list.add(income);

        income = new IncomeSource("Allowance", -(20.00));
        list.add(income);

        income = new IncomeSource("Blackberry", -(4000.00));
        list.add(income);

        assertEquals("0.00", Calculate.calculateTotal(list));

        System.out.println("Finished testNegativeIncomes");
    }

    public void testSomeNegativeIncomes() {
        System.out.println("\nStarting testSomeNegativeIncomes");

        income = new IncomeSource("Starbucks", -(500.00));
        list.add(income);

        income = new IncomeSource("H&M", 600.00);
        list.add(income);

        income = new IncomeSource("IBM", 2000.00);
        list.add(income);

        income = new IncomeSource("Allowance", -(20.00));
        list.add(income);

        income = new IncomeSource("Blackberry", -(4000.00));
        list.add(income);

        assertEquals("2600.00", Calculate.calculateTotal(list));

        System.out.println("Finished testSomeNegativeIncomes");
    }

    public void testDifferentIncomeFrequencies() {
        System.out.println("\nStarting testDifferentIncomeFrequencies");

        income = new IncomeSource("Starbucks", 500.00, "monthly");
        list.add(income);

        income = new IncomeSource("Allowance", 20.00, "bi-weekly");
        list.add(income);

        income = new IncomeSource("Blackberry", 4000.00, "misc");
        list.add(income);

        income = new IncomeSource("H&M", 600.00, "once");
        list.add(income);

        income = new IncomeSource("IBM", 1000.00, "weekly");
        list.add(income);

        income = new IncomeSource("McDonalds", 300.00);
        list.add(income);

        assertEquals("9440.00", Calculate.calculateTotal(list));

        System.out.println("Finished testDifferentIncomeFrequencies");
    }

    public void testValidExpenseList()
    {
        System.out.println("\nStarting testValidExpenseList");

        exp = new Expense("Rent", 500.0);
        list.add(exp);
        exp = new Expense("Internet", 120.0);
        list.add(exp);
        exp = new Expense("Phone", 70.0);
        list.add(exp);

        assertEquals("690.00", Calculate.calculateTotal(list));

        System.out.println("Finished testValidExpenseList");
    }

    public void testOneExpense()
    {
        System.out.println("\nStarting testOneExpense");

        exp = new Expense("Rent", 500.0);
        list.add(exp);
        assertEquals("500.00", Calculate.calculateTotal(list));

        exp = new Expense("Internet", 120.0);
        list.set(0,exp);
        assertEquals("120.00", Calculate.calculateTotal(list));

        exp = new Expense("Phone", 70.0);
        list.set(0,exp);
        assertEquals("70.00", Calculate.calculateTotal(list));

        exp = new Expense("Hydro", 150.0);
        list.set(0,exp);
        assertEquals("150.00", Calculate.calculateTotal(list));

        System.out.println("Finished testOneExpense");
    }

    public void testOddExpenseList()
    {
        System.out.println("\nStarting testOddExpenseList");

        exp = new Expense("Rent", 500.9999999);
        list.add(exp);
        exp = new Expense("Internet", 120.9999999);
        list.add(exp);
        exp = new Expense("Phone", 70.9999999);
        list.add(exp);

        assertEquals("693.00", Calculate.calculateTotal(list));

        System.out.println("Finished testOddExpenseList");
    }

    public void testBlankExpenseAmounts()
    {
        System.out.println("\nStarting testBlankExpenseAmounts");

        exp = new Expense("Rent");
        list.add(exp);

        exp = new Expense("Internet");
        list.add(exp);

        exp = new Expense("Food");
        list.add(exp);

        exp = new Expense("Phone");
        list.add(exp);

        exp = new Expense("Hydro");
        list.add(exp);

        assertEquals("0.00", Calculate.calculateTotal(list));

        System.out.println("Finished testBlankExpenseAmounts");
    }

    public void testSomeBlankExpenseAmounts()
    {
        System.out.println("\nStarting testSomeBlankExpenseAmounts");

        exp = new Expense("Rent", 650.0);
        list.add(exp);

        exp = new Expense("Internet", 130.0);
        list.add(exp);

        exp = new Expense("Phone", 75.0);
        list.add(exp);

        exp = new Expense("Hydro");
        list.add(exp);

        exp = new Expense("Food");
        list.add(exp);

        assertEquals("855.00", Calculate.calculateTotal(list));

        System.out.println("Finished testSomeBlankExpenseAmounts");
    }

    public void testListWithSingleNullExpense()
    {
        System.out.println("\nStarting testListWithSingleNullExpense");

        exp = new Expense("Rent", 500.0);
        list.add(exp);
        list.add(null);
        exp = new Expense("Internet", 120.0);
        list.add(exp);

        assertEquals("?", Calculate.calculateTotal(list));//invalid total

        System.out.println("Finished testListWithSingleNullExpense");
    }

    public void testNegativeExpenseAmounts()
    {
        System.out.println("\nStarting testNegativeExpenseAmounts");

        exp = new Expense("Rent", -(500.0));
        list.add(exp);

        exp = new Expense("Internet", -(120.0));
        list.add(exp);

        exp = new Expense("Phone", -(70.0));
        list.add(exp);

        assertEquals("0.00",Calculate.calculateTotal(list));

        System.out.println("Finished testNegativeExpenseAmounts");
    }

    public void testSomeNegativeExpenseAmounts()
    {
        System.out.println("\nStarting testSomeNegativeExpenseAmounts");

        exp = new Expense("Rent", -(9001.0));
        list.add(exp);

        exp = new Expense("Internet", 120.0);
        list.add(exp);

        exp = new Expense("Phone", -(70.0));
        list.add(exp);

        assertEquals("120.00",Calculate.calculateTotal(list));

        System.out.println("Finished testSomeNegativeExpenseAmounts");
    }

    public void testValidWishList()
    {
        System.out.println("\nStarting testValidWishList");

        wish = new WishListItem("iPhone", 500.0);
        list.add(wish);
        wish = new WishListItem("TV", 600.0);
        list.add(wish);
        wish = new WishListItem("Laptop", 2000.0);
        list.add(wish);

        assertEquals("3100.00", Calculate.calculateTotal(list));

        System.out.println("Finished testValidWishList");
    }

    public void testOneWishListItem()
    {
        System.out.println("\nStarting testOneWishListItem");

        wish = new WishListItem("iPhone", 500.0);
        list.add(wish);
        assertEquals("500.00", Calculate.calculateTotal(list));

        wish = new WishListItem("TV", 7000.0);
        list.set(0,wish);
        assertEquals("7000.00", Calculate.calculateTotal(list));

        wish = new WishListItem("Laptop", 2000.0);
        list.set(0,wish);
        assertEquals("2000.00", Calculate.calculateTotal(list));

        wish = new WishListItem("MP3 Player", 200.0);
        list.set(0,wish);
        assertEquals("200.00", Calculate.calculateTotal(list));

        wish = new WishListItem("Chromebook", 400.0);
        list.set(0,wish);
        assertEquals("400.00", Calculate.calculateTotal(list));

        wish = new WishListItem("Backpack", 100.0);
        list.set(0,wish);
        assertEquals("100.00", Calculate.calculateTotal(list));

        System.out.println("Finished testOneWishListItem");
    }

    public void testOddWishList()
    {
        System.out.println("\nStarting testOddWishList");

        wish = new WishListItem("iPhone", 500.9999999);
        list.add(wish);
        wish = new WishListItem("TV", 600.9999999);
        list.add(wish);
        wish = new WishListItem("Laptop", 2000.9999999);
        list.add(wish);

        assertEquals("3103.00", Calculate.calculateTotal(list));

        System.out.println("Finished testOddWishList");
    }

    public void testBlankWishListItemPrices()
    {
        System.out.println("\nStarting testBlankWishListItemPrices");

        wish = new WishListItem("iPhone");
        list.add(wish);

        wish = new WishListItem("TV");
        list.add(wish);

        wish = new WishListItem("Laptop");
        list.add(wish);

        wish = new WishListItem("Backpack");
        list.add(wish);

        wish = new WishListItem("Desk Chair");
        list.add(wish);

        assertEquals("0.00", Calculate.calculateTotal(list));

        System.out.println("Finished testBlankWishListItemPrices");
    }

    public void testSomeBlankWishListItemPrices()
    {
        System.out.println("\nStarting testSomeBlankWishListItemPrices");

        wish = new WishListItem("iPhone", 500.0);
        list.add(wish);

        wish = new WishListItem("TV", 7000.0);
        list.add(wish);

        wish = new WishListItem("Laptop", 2000.0);
        list.add(wish);

        wish = new WishListItem("Backpack");
        list.add(wish);

        wish = new WishListItem("Desk Chair");
        list.add(wish);

        assertEquals("9500.00", Calculate.calculateTotal(list));

        System.out.println("Finished testSomeBlankWishListItemPrices");
    }

    public void testListWithSingleNullWishListItem()
    {
        System.out.println("\nStarting testListWithSingleNullWishListItem");

        wish = new WishListItem("iPhone", 500.0);
        list.add(wish);
        list.add(null);
        wish = new WishListItem("Laptop", 2000.0);
        list.add(wish);

        assertEquals("?", Calculate.calculateTotal(list));//invalid total

        System.out.println("Finished testListWithSingleNullWishListItem");
    }

    public void testNegativeWishListItemPrices()
    {
        System.out.println("\nStarting testNegativeWishListItemPrices");

        wish = new WishListItem("iPhone", -(500.0));
        list.add(wish);

        wish = new WishListItem("TV", -(7000.0));
        list.add(wish);

        wish = new WishListItem("Laptop", -(2000.0));
        list.add(wish);

        assertEquals("0.00",Calculate.calculateTotal(list));

        System.out.println("Finished testNegativeWishListItemPrices");
    }

    public void testSomeNegativeWishListItemPrices()
    {
        System.out.println("\nStarting testSomeNegativeWishListItemPrices");

        wish = new WishListItem("iPhone", -(500.0));
        list.add(wish);

        wish = new WishListItem("TV", 7000.0);
        list.add(wish);

        wish = new WishListItem("Laptop", -(2000.0));
        list.add(wish);

        assertEquals("7000.00",Calculate.calculateTotal(list));

        System.out.println("Finished testSomeNegativeWishListItemPrices");
    }


    public void testListWithAllTypes()
    {
        System.out.println("\nStarting testListWithAllTypes");

        income = new IncomeSource("Starbucks", 500.0);
        list.add(income);
        income = new IncomeSource("Application Developer", 1000.0);
        list.add(income);

        wish = new WishListItem("iPhone", 500.0);
        list.add(wish);
        wish = new WishListItem("TV", 600.0);
        list.add(wish);

        exp = new Expense("Rent", 500.0);
        list.add(exp);
        exp = new Expense("Internet", 120.0);
        list.add(exp);

        assertEquals("?",Calculate.calculateTotal(list));

        System.out.println("Finished testListWithAllTypes");
    }

    public void testListWithAllTypesMixed()
    {
        System.out.println("\nStarting testListWithAllTypesMixed");

        exp = new Expense("Internet", 120.0);
        list.add(exp);

        income = new IncomeSource("Starbucks", 500.0);
        list.add(income);

        exp = new Expense("Rent", 500.0);
        list.add(exp);

        wish = new WishListItem("TV", 600.0);
        list.add(wish);

        income = new IncomeSource("Application Developer", 1000.0);
        list.add(income);

        wish = new WishListItem("iPhone", 500.0);
        list.add(wish);

        assertEquals("?", Calculate.calculateTotal(list));

        System.out.println("Finished testListWithAllTypesMixed");
    }

    public void testListWithAllTypesAndNull()
    {
        System.out.println("\nStarting testListWithAllTypesAndNull");

        exp = new Expense("Internet", 120.0);
        list.add(exp);

        income = new IncomeSource("Starbucks", 500.0);
        list.add(income);

        list.add(null);

        exp = new Expense("Rent", 500.0);
        list.add(exp);

        wish = new WishListItem("TV", 600.0);
        list.add(wish);

        list.add(null);

        income = new IncomeSource("Application Developer", 1000.0);
        list.add(income);

        wish = new WishListItem("iPhone", 500.0);
        list.add(wish);

        assertEquals("?", Calculate.calculateTotal(list));

        System.out.println("Finished testListWithAllTypesAndNull");
    }

    public void testAllNullList()
    {
        System.out.println("\nStarting testAllNullList");

        list.add(null);
        list.add(null);
        list.add(null);
        list.add(null);

        assertEquals("?", Calculate.calculateTotal(list));

        System.out.println("Finished testAllNullList");
    }

}
