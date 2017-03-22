package comp3350.budgetapp.tests.business;

import junit.framework.TestCase;
import java.util.ArrayList;
import comp3350.budgetapp.business.Calculate;
import comp3350.budgetapp.objects.IncomeSource;

public class CalculateIncomeSourceTotalTest extends TestCase {
    private static ArrayList<IncomeSource> list;
    private IncomeSource item;


    public void setUp() {
        list = new ArrayList<>();
    }


    public void testNullList() {
        System.out.println("\nStarting testNullList");

        assertEquals("", Calculate.incomeTotal(null));

        System.out.println("Finished testNullList");
    }


    public void testEmptyList() {
        System.out.println("\nStarting testEmptyList");

        assertNotNull(list);
        assertEquals("", Calculate.incomeTotal(list));

        System.out.println("Finished testEmptyList");
    }


    public void testValidList() {
        System.out.println("\nStarting testValidList");

        item = new IncomeSource("Starbucks", 500.0);
        list.add(item);
        item = new IncomeSource("Application Developer", 1000.0);
        list.add(item);
        item = new IncomeSource("Allowance", 20.0);
        list.add(item);

        assertEquals("1520.00", Calculate.incomeTotal(list));

        System.out.println("Finished testValidList");
    }


    public void testOneItem() {
        System.out.println("\nStarting testOneItem");

        item = new IncomeSource("Starbucks", 500.0);
        list.add(item);
        assertEquals("500.00", Calculate.incomeTotal(list));

        item = new IncomeSource("Application Developer", 1000.0);
        list.set(0, item);
        assertEquals("1000.00", Calculate.incomeTotal(list));

        item = new IncomeSource("Contractor", 5000.0);
        list.set(0, item);
        assertEquals("5000.00", Calculate.incomeTotal(list));

        item = new IncomeSource("Sales Associate", 400.0);
        list.set(0, item);
        assertEquals("400.00", Calculate.incomeTotal(list));

        System.out.println("Finished testOneItem");
    }


    public void testOddList() {
        System.out.println("\nStarting testOddList");

        list = new ArrayList<>();
        item = new IncomeSource("Starbucks", 399.9999999);
        list.add(item);
        item = new IncomeSource("Contractor", 5999.9999999);
        list.add(item);
        item = new IncomeSource("Allowance", 19.9999999);
        list.add(item);

        assertEquals("6420.00", Calculate.incomeTotal(list));

        System.out.println("Finished testOddList");
    }


    public void testBlankIncomes() {
        System.out.println("\nStarting testBlankIncomes");

        list = new ArrayList<>();

        item = new IncomeSource("Starbucks");
        list.add(item);

        item = new IncomeSource("IBM");
        list.add(item);

        item = new IncomeSource("Blackberry");
        list.add(item);

        item = new IncomeSource("H&M");
        list.add(item);

        item = new IncomeSource("Allowance");
        list.add(item);


        assertEquals("", Calculate.incomeTotal(list));

        System.out.println("Finished testBlankIncomes");
    }


    public void testSomeBlankIncomes() {
        System.out.println("\nStarting testSomeBlankIncomes");

        list = new ArrayList<>();

        item = new IncomeSource("Starbucks", 500.00);
        list.add(item);

        item = new IncomeSource("Allowance", 20.00);
        list.add(item);

        item = new IncomeSource("Blackberry", 4000.00);
        list.add(item);

        item = new IncomeSource("H&M");
        list.add(item);

        item = new IncomeSource("IBM");
        list.add(item);

        assertEquals("4520.00", Calculate.incomeTotal(list));

        System.out.println("Finished testSomeBlankIncomes");
    }


    public void testMixedBlankIncomes() {
        System.out.println("\nStarting testMixedBlankIncomes");

        item = new IncomeSource("Starbucks", 500.00);
        list.add(item);

        item = new IncomeSource("IBM");
        list.add(item);

        item = new IncomeSource("Blackberry", 4000.00);
        list.add(item);

        item = new IncomeSource("H&M");
        list.add(item);

        item = new IncomeSource("Allowance", 20.00);
        list.add(item);

        assertEquals("4520.00", Calculate.incomeTotal(list));

        System.out.println("Finished testMixedBlankIncomes");
    }


    public void testNullItem() {
        System.out.println("\nStarting testNullItem");

        item = new IncomeSource("Starbucks", 500.00);
        list.add(item);

        item = new IncomeSource("Allowance", 20.00);
        list.add(item);

        list.add(null);

        assertEquals("?", Calculate.incomeTotal(list));//invalid total

        System.out.println("Finished testNullItem");
    }


    public void testNegativeIncomes() {
        System.out.println("\nStarting testNegativeIncomes");

        item = new IncomeSource("Starbucks", -(500.00));
        list.add(item);

        item = new IncomeSource("Allowance", -(20.00));
        list.add(item);

        item = new IncomeSource("Blackberry", -(4000.00));
        list.add(item);

        assertEquals("", Calculate.incomeTotal(list));

        System.out.println("Finished testNegativeIncomes");
    }


    public void testSomeNegativeIncomes() {
        System.out.println("\nStarting testSomeNegativeIncomes");

        item = new IncomeSource("Starbucks", -(500.00));
        list.add(item);

        item = new IncomeSource("H&M", 600.00);
        list.add(item);

        item = new IncomeSource("IBM", 2000.00);
        list.add(item);

        item = new IncomeSource("Allowance", -(20.00));
        list.add(item);

        item = new IncomeSource("Blackberry", -(4000.00));
        list.add(item);

        assertEquals("2600.00", Calculate.incomeTotal(list));

        System.out.println("Finished testSomeNegativeIncomes");
    }


    public void testDifferentFrequencies() {
        System.out.println("\nStarting testDifferentFrequencies");

        item = new IncomeSource("Starbucks", 500.00, "monthly");
        list.add(item);

        item = new IncomeSource("Allowance", 20.00, "bi-weekly");
        list.add(item);

        item = new IncomeSource("Blackberry", 4000.00, "misc");
        list.add(item);

        item = new IncomeSource("H&M", 600.00, "once");
        list.add(item);

        item = new IncomeSource("IBM", 1000.00, "weekly");
        list.add(item);

        item = new IncomeSource("McDonalds", 300.00);
        list.add(item);

        assertEquals("9440.00", Calculate.incomeTotal(list));

        System.out.println("Finished testDifferentFrequencies");
    }

}