package comp3350.budgetapp.tests.objects;

import junit.framework.TestCase;
import comp3350.budgetapp.objects.IncomeSource;

public class IncomeSourceTest extends TestCase
{
    public static IncomeSource src1, src2, src3;

    public void testIncomeSourceItemNameOnly()
    {
        System.out.println("\nStarting testIncomeSourceItemNameOnly");

        src1 = new IncomeSource("Sales Associate");
        assertNotNull(src1);
        assertEquals("Sales Associate", src1.getSourceName());
        assertEquals(0.0,src1.getAmount());
        assertEquals("misc",src1.getType());
        assertEquals(1,src1.getFrequency());
        assertEquals("Income Source Name: Sales Associate, Amount: $0.00, Type: misc, Frequency: 1",
                src1.toString());

        src1.setName("Web Developer");
        assertNotNull(src1);
        assertEquals("Web Developer", src1.getSourceName());
        assertEquals(0.0,src1.getAmount());
        assertEquals("misc",src1.getType());
        assertEquals(1,src1.getFrequency());
        assertEquals("Income Source Name: Web Developer, Amount: $0.00, Type: misc, Frequency: 1",
                src1.toString());

        src1.setName("Application Developer");
        assertEquals("Application Developer", src1.getSourceName());
        assertEquals(0.0,src1.getAmount());
        assertEquals("misc",src1.getType());
        assertEquals(1,src1.getFrequency());
        assertEquals("Income Source Name: Application Developer, Amount: $0.00, Type: misc, Frequency: 1",
                src1.toString());

        System.out.println("Finished testIncomeSourceItemNameOnly");
    }

    public void testIncomeSourceItemNameAndAmountOnly()
    {
        System.out.println("\nStarting testIncomeSourceItemNameAndAmountOnly");

        src1 = new IncomeSource("Sales Associate",11.0);
        assertNotNull(src1);
        assertEquals("Sales Associate", src1.getSourceName());
        assertEquals(11.0,src1.getAmount());
        assertEquals("misc",src1.getType());
        assertEquals(1,src1.getFrequency());
        assertEquals("Income Source Name: Sales Associate, Amount: $11.00, Type: misc, Frequency: 1",
                src1.toString());

        src1.setAmount(11.50);
        assertNotNull(src1);
        assertEquals("Sales Associate", src1.getSourceName());
        assertEquals(11.50,src1.getAmount());
        assertEquals("misc",src1.getType());
        assertEquals(1,src1.getFrequency());
        assertEquals("Income Source Name: Sales Associate, Amount: $11.50, Type: misc, Frequency: 1",
                src1.toString());

        src1.setAmount(12.75);
        assertNotNull(src1);
        assertEquals("Sales Associate", src1.getSourceName());
        assertEquals(12.75,src1.getAmount());
        assertEquals("misc",src1.getType());
        assertEquals(1,src1.getFrequency());
        assertEquals("Income Source Name: Sales Associate, Amount: $12.75, Type: misc, Frequency: 1",
                src1.toString());

        System.out.println("Finished testIncomeSourceItemNameAndAmountOnly");
    }

    public void testIncomeSourceItemNameAmountAndType()
    {
        System.out.println("\nStarting testIncomeSourceItemNameAmountAndType");

        src1 = new IncomeSource("Sales Associate",11.0,"monthly");
        assertNotNull(src1);
        assertEquals("Sales Associate", src1.getSourceName());
        assertEquals(11.0,src1.getAmount());
        assertEquals("monthly",src1.getType());
        assertEquals(1,src1.getFrequency());
        assertEquals("Income Source Name: Sales Associate, Amount: $11.00, Type: monthly, Frequency: 1",
                src1.toString());

        src1.setType("bi-weekly");
        assertNotNull(src1);
        assertEquals("Sales Associate", src1.getSourceName());
        assertEquals(11.0,src1.getAmount());
        assertEquals("bi-weekly",src1.getType());
        assertEquals(2,src1.getFrequency());
        assertEquals("Income Source Name: Sales Associate, Amount: $11.00, Type: bi-weekly, Frequency: 2",
                src1.toString());

        src1.setType("weekly");
        assertNotNull(src1);
        assertEquals("Sales Associate", src1.getSourceName());
        assertEquals(11.0,src1.getAmount());
        assertEquals("weekly",src1.getType());
        assertEquals(4,src1.getFrequency());
        assertEquals("Income Source Name: Sales Associate, Amount: $11.00, Type: weekly, Frequency: 4",
                src1.toString());

        System.out.println("Finished testIncomeSourceItemNameAmountAndType");
    }

    public void testOddIncomeSourceAmountsOnly()
    {
        System.out.println("\nStarting testOddIncomeSourceAmountsOnly");

        src1 = new IncomeSource("Sales Associate",12.4925000000001);
        assertNotNull(src1);
        assertEquals("Sales Associate", src1.getSourceName());
        assertEquals(12.4925000000001,src1.getAmount());
        assertEquals("misc",src1.getType());
        assertEquals(1,src1.getFrequency());
        assertEquals("Income Source Name: Sales Associate, Amount: $12.49, Type: misc, Frequency: 1",
                src1.toString());

        src1.setAmount(13.0000000000001);
        assertNotNull(src1);
        assertEquals("Sales Associate", src1.getSourceName());
        assertEquals(13.0000000000001,src1.getAmount());
        assertEquals("misc",src1.getType());
        assertEquals(1,src1.getFrequency());
        assertEquals("Income Source Name: Sales Associate, Amount: $13.00, Type: misc, Frequency: 1",
                src1.toString());

        src1.setAmount(13.9999999999999);
        assertNotNull(src1);
        assertEquals("Sales Associate", src1.getSourceName());
        assertEquals(13.9999999999999,src1.getAmount());
        assertEquals("misc",src1.getType());
        assertEquals(1,src1.getFrequency());
        assertEquals("Income Source Name: Sales Associate, Amount: $14.00, Type: misc, Frequency: 1",
                src1.toString());

        System.out.println("Finished testOddIncomeSourceAmountsOnly");
    }

    public void testOddIncomeSourceType()
    {
        System.out.println("\nStarting testOddIncomeSourceType");

        src1 = new IncomeSource("Sales Associate",11.0,"once");
        assertNotNull(src1);
        assertEquals("Sales Associate", src1.getSourceName());
        assertEquals(11.0,src1.getAmount());
        assertEquals("once",src1.getType());
        assertEquals(1,src1.getFrequency());
        assertEquals("Income Source Name: Sales Associate, Amount: $11.00, Type: once, Frequency: 1",
                src1.toString());

        src1.setType("one off");
        assertNotNull(src1);
        assertEquals("Sales Associate", src1.getSourceName());
        assertEquals(11.0,src1.getAmount());
        assertEquals("one off",src1.getType());
        assertEquals(1,src1.getFrequency());
        assertEquals("Income Source Name: Sales Associate, Amount: $11.00, Type: one off, Frequency: 1",
                src1.toString());

        System.out.println("Finished testOddIncomeSourceType");
    }

    public void testEquals()
    {
        System.out.println("\nStarting testEquals");

        src1 = new IncomeSource("Sales Associate");
        src2 = new IncomeSource("Web Developer",21.0);
        src3 = new IncomeSource("Security Analyst",20.0,"bi-weekly");

        assertTrue(!src1.equals(src2));
        assertTrue(!src2.equals(src3));
        assertTrue(!src3.equals(src1));

        src2 = new IncomeSource("Sales Associate",11.0);
        src3 = new IncomeSource("Sales Associate",11.50,"bi-weekly");

        assertTrue(src2.equals(src3));
        assertTrue(src3.equals(src1));

        System.out.println("Finished testEquals");
    }

}
