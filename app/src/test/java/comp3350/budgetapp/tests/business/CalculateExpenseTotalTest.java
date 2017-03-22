package comp3350.budgetapp.tests.business;

import junit.framework.TestCase;
import java.util.ArrayList;
import comp3350.budgetapp.business.Calculate;
import comp3350.budgetapp.objects.Expense;

public class CalculateExpenseTotalTest extends TestCase {
    private static ArrayList <Expense> list;
    private Expense item;

    public void setUp()
    {
        list = new ArrayList <>();
    }


    public void testNullList()
    {
        System.out.println("\nStarting testNullList");

        assertEquals("", Calculate.expenseTotal(null));

        System.out.println("Finished testNullList");
    }


    public void testEmptyList()
    {
        System.out.println("\nStarting testEmptyList");

        assertNotNull(list);
        assertEquals("", Calculate.expenseTotal(list));

        System.out.println("Finished testEmptyList");
    }


    public void testValidList()
    {
        System.out.println("\nStarting testValidList");

        item = new Expense("Rent", 500.0);
        list.add(item);
        item = new Expense("Internet", 120.0);
        list.add(item);
        item = new Expense("Phone", 70.0);
        list.add(item);

        assertEquals("690.00", Calculate.expenseTotal(list));

        System.out.println("Finished testValidList");
    }


    public void testOneItem()
    {
        System.out.println("\nStarting testOneItem");

        item = new Expense("Rent", 500.0);
        list.add(item);
        assertEquals("500.00", Calculate.expenseTotal(list));

        item = new Expense("Internet", 120.0);
        list.set(0,item);
        assertEquals("120.00", Calculate.expenseTotal(list));

        item = new Expense("Phone", 70.0);
        list.set(0,item);
        assertEquals("70.00", Calculate.expenseTotal(list));

        item = new Expense("Hydro", 150.0);
        list.set(0,item);
        assertEquals("150.00", Calculate.expenseTotal(list));

        System.out.println("Finished testOneItem");
    }


    public void testOddList()
    {
        System.out.println("\nStarting testOddList");

        item = new Expense("Rent", 500.9999999);
        list.add(item);
        item = new Expense("Internet", 120.9999999);
        list.add(item);
        item = new Expense("Phone", 70.9999999);
        list.add(item);

        assertEquals("693.00", Calculate.expenseTotal(list));

        System.out.println("Finished testOddList");
    }


    public void testBlankAmounts()
    {
        System.out.println("\nStarting testBlankAmounts");

        item = new Expense("Rent");
        list.add(item);

        item = new Expense("Internet");
        list.add(item);

        item = new Expense("Food");
        list.add(item);

        item = new Expense("Phone");
        list.add(item);

        item = new Expense("Hydro");
        list.add(item);

        assertEquals("", Calculate.expenseTotal(list));

        System.out.println("Finished testBlankAmounts");
    }


    public void testSomeBlankAmounts()
    {
        System.out.println("\nStarting testSomeBlankAmounts");

        item = new Expense("Rent", 650.0);
        list.add(item);

        item = new Expense("Internet", 130.0);
        list.add(item);

        item = new Expense("Phone", 75.0);
        list.add(item);

        item = new Expense("Hydro");
        list.add(item);

        item = new Expense("Food");
        list.add(item);

        assertEquals("855.00", Calculate.expenseTotal(list));

        System.out.println("Finished testSomeBlankAmounts");
    }


    public void testNullItem()
    {
        System.out.println("\nStarting testNullItem");

        item = new Expense("Rent", 500.0);
        list.add(item);
        list.add(null);
        item = new Expense("Internet", 120.0);
        list.add(item);

        assertEquals("?", Calculate.expenseTotal(list));//invalid total

        System.out.println("Finished testNullItem");
    }


    public void testNegativeAmounts()
    {
        System.out.println("\nStarting testNegativeAmounts");

        item = new Expense("Rent", -(500.0));
        list.add(item);

        item = new Expense("Internet", -(120.0));
        list.add(item);

        item = new Expense("Phone", -(70.0));
        list.add(item);

        assertEquals("",Calculate.expenseTotal(list));

        System.out.println("Finished testNegativeAmounts");
    }


    public void testSomeNegativeAmounts()
    {
        System.out.println("\nStarting testSomeNegativeAmounts");

        item = new Expense("Rent", -(9001.0));
        list.add(item);

        item = new Expense("Internet", 120.0);
        list.add(item);

        item = new Expense("Phone", -(70.0));
        list.add(item);

        assertEquals("120.00",Calculate.expenseTotal(list));

        System.out.println("Finished testSomeNegativeAmounts");
    }

}
