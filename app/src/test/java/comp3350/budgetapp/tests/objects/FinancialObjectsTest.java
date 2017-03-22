package comp3350.budgetapp.tests.objects;

import junit.framework.TestCase;
import comp3350.budgetapp.objects.FinancialObjects;
import comp3350.budgetapp.objects.IncomeSource;

public class FinancialObjectsTest extends TestCase{

    public static FinancialObjects fo1, fo2, fo3;

    public void testFinancialObjectsEmpty()
    {
        System.out.println("\nStarting testFinancialObjectsNameOnly");

        fo1 = new FinancialObjects();
        assertNotNull(fo1);
        assertEquals("",fo1.getName());
        assertEquals(0.00,fo1.getAmount());
        assertEquals(" : $0.00",fo1.toString());

        fo1.setName("Income Information");
        assertEquals("Income Information",fo1.getName());
        assertEquals("Income Information : $0.00",fo1.toString());

        fo1.setAmount(400.0);
        assertEquals(400.0,fo1.getAmount());
        assertEquals("Income Information : $400.00",fo1.toString());

        System.out.println("Finished testFinancialObjectsNameOnly");
    }

    public void testFinancialObjectsNameAndAmount()
    {
        System.out.println("\nStarting testFinancialObjectsNameAndAmount");

        fo1 = new FinancialObjects("Income Information",400.0);
        assertNotNull(fo1);
        assertEquals("Income Information",fo1.getName());
        assertEquals(400.00,fo1.getAmount());
        assertEquals("Income Information : $400.00",fo1.toString());

        fo1.setName("Wishlist Information");
        assertEquals("Wishlist Information",fo1.getName());
        assertEquals("Wishlist Information : $400.00",fo1.toString());

        fo1.setAmount(199.99);
        assertEquals(199.99,fo1.getAmount());
        assertEquals("Wishlist Information : $199.99",fo1.toString());

        System.out.println("Finished testFinancialObjectsNameAndAmount");
    }

    public void testOddFinancialObjectsAmount()
    {
        System.out.println("\nStarting testOddFinancialObjectsAmount");

        fo1 = new FinancialObjects("Income Information",12.4925000000001);
        assertNotNull(fo1);
        assertEquals("Income Information",fo1.getName());
        assertEquals(12.4925000000001,fo1.getAmount());
        assertEquals("Income Information : $12.49",fo1.toString());

        fo1.setAmount(199.999999999999);
        assertEquals(199.999999999999,fo1.getAmount());
        assertEquals("Income Information : $200.00",fo1.toString());

        fo1.setAmount(199999999.999999999999);
        assertEquals(199999999.999999999999,fo1.getAmount());
        assertEquals("Income Information : $200000000.00",fo1.toString());

        System.out.println("Finished testOddFinancialObjectsAmount");
    }

    public void testEquals()
    {
        System.out.println("\nStarting testEquals");

        fo1 = new IncomeSource("Sales Associate");
        fo2 = new IncomeSource("Web Developer",21.0);
        fo3 = new IncomeSource("Security Analyst",20.0);

        assertTrue(!fo1.equals(fo2));
        assertTrue(!fo2.equals(fo3));
        assertTrue(!fo3.equals(fo1));

        fo2 = new IncomeSource("Sales Associate",11.0);
        fo3 = new IncomeSource("Sales Associate",11.50);

        assertTrue(fo2.equals(fo3));
        assertTrue(fo3.equals(fo1));

        System.out.println("Finished testEquals");
    }
}
