package comp3350.budgetapp.tests.business;

import junit.framework.TestCase;
import org.junit.*;
import java.util.ArrayList;
import comp3350.budgetapp.business.Calculate;
import comp3350.budgetapp.objects.IncomeSource;

public class CalculateIncomeSourceTotalTest extends TestCase
{
    public static ArrayList <IncomeSource> list;
    IncomeSource item;
    String resultTotal;
    double total;

    @Before
    public void testCreateWishList()
    {
        list = new ArrayList <>();
    }

    @Test
    public void testNullList()
    {
        resultTotal = Calculate.incomeTotal(null);
        assertEquals("",resultTotal);
    }

    @Test
    public void testEmptyList()
    {
        list = new ArrayList<>();
        resultTotal = Calculate.incomeTotal(list);

        assertNotNull(list);
        assertEquals(resultTotal,"");
    }

    @Test
    public void testValidList()
    {
        list = new ArrayList<>();
        item = new IncomeSource("Starbucks", 500.0);
        list.add(item);
        item = new IncomeSource("Application Developer", 1000.0);
        list.add(item);
        item = new IncomeSource("Allowance", 20.0);
        list.add(item);
        resultTotal = Calculate.incomeTotal(list);

        assertNotNull(resultTotal);
        assertEquals("1520.00",resultTotal);
    }

    @Test
    public void testOneItem()
    {
        list = new ArrayList<>();

        item = new IncomeSource("Starbucks", 500.0);
        list.add(item);

        resultTotal = Calculate.incomeTotal(list);
        assertEquals(resultTotal,"500.00");

        item = new IncomeSource("Application Developer", 1000.0);
        list.set(0,item);
        resultTotal = Calculate.incomeTotal(list);
        assertEquals(resultTotal,"1000.00");

        item = new IncomeSource("Contractor", 5000.0);
        list.set(0,item);
        resultTotal = Calculate.incomeTotal(list);
        assertEquals(resultTotal,"5000.00");

        item = new IncomeSource("Sales Associate", 400.0);
        list.set(0,item);
        resultTotal = Calculate.incomeTotal(list);
        assertEquals(resultTotal,"400.00");
    }

    @Test
    public void testOddList()
    {
        list = new ArrayList<>();
        item = new IncomeSource("Starbucks", 399.9999999);
        list.add(item);
        item = new IncomeSource("Contractor", 5999.9999999);
        list.add(item);
        item = new IncomeSource("Allowance", 19.9999999);
        list.add(item);
        resultTotal = Calculate.incomeTotal(list);

        assertNotNull(resultTotal);
        assertEquals("6420.00",resultTotal);
    }

    @Test
    public void testBlankPrices()
    {
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

        resultTotal = Calculate.incomeTotal(list);

        assertNotNull(resultTotal);
        assertTrue(resultTotal.equals(""));
    }

    @Test
    public void testSomeBlankPrices()
    {
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

        resultTotal = Calculate.incomeTotal(list);

        assertNotNull(resultTotal);
        assertEquals("4520.00",resultTotal);
    }

    @Test
    public void testMixedBlankPrices()
    {
        list = new ArrayList<>();

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

        resultTotal = Calculate.incomeTotal(list);

        assertNotNull(resultTotal);
        assertEquals("4520.00",resultTotal);
    }

    @Test
    public void testNullItem()
    {
        list = new ArrayList<>();

        item = new IncomeSource("Starbucks", 500.00);
        list.add(item);

        item = new IncomeSource("Allowance", 20.00);
        list.add(item);

        list.add(null);

        resultTotal = Calculate.incomeTotal(list);

        assertEquals("?",resultTotal);//invalid total
    }

    @Test
    public void testNegativeAmounts()
    {
        list = new ArrayList<>();

        item = new IncomeSource("Starbucks", -(500.00));
        list.add(item);

        item = new IncomeSource("Allowance", -(20.00));
        list.add(item);

        item = new IncomeSource("Blackberry", -(4000.00));
        list.add(item);

        resultTotal = Calculate.incomeTotal(list);

        assertEquals("",resultTotal);
    }

    @Test
    public void testSomeNegativePrices()
    {
        list = new ArrayList<>();

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

        resultTotal = Calculate.incomeTotal(list);

        assertEquals("2600.00",resultTotal);
    }

    @Test
    public void testDifferentFrequencies()
    {
        list = new ArrayList<>();

        item = new IncomeSource("Starbucks", 500.00,"monthly");
        list.add(item);

        item = new IncomeSource("Allowance", 20.00,"bi-weekly");
        list.add(item);

        item = new IncomeSource("Blackberry", 4000.00,"misc");
        list.add(item);

        item = new IncomeSource("H&M", 600.00,"once");
        list.add(item);

        item = new IncomeSource("IBM", 1000.00,"weekly");
        list.add(item);

        item = new IncomeSource("McDonalds", 300.00);
        list.add(item);

        resultTotal = Calculate.incomeTotal(list);

        assertEquals("9440.00",resultTotal);
    }
}

