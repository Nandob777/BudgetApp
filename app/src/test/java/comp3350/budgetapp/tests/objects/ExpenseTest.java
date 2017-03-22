package comp3350.budgetapp.tests.objects;

import junit.framework.TestCase;
import comp3350.budgetapp.objects.Expense;

public class ExpenseTest extends TestCase
{
    public static Expense exp1, exp2, exp3;

    public void testExpenseNameOnly()
    {
        System.out.println("\nStarting testExpenseNameOnly");

        exp1 = new Expense("Cellphone Bill");
        assertNotNull(exp1);
        assertEquals("Cellphone Bill",exp1.getName());
        assertEquals(0.0,exp1.getAmount());
        assertEquals("Expense Name: Cellphone Bill, Amount: $0.00",exp1.toString());

        exp1.setName("Hydro Bill");
        assertNotNull(exp1);
        assertEquals("Hydro Bill",exp1.getName());
        assertEquals(0.0,exp1.getAmount());
        assertEquals("Expense Name: Hydro Bill, Amount: $0.00",exp1.toString());

        exp1.setName("Car Insurance");
        assertNotNull(exp1);
        assertEquals("Car Insurance",exp1.getName());
        assertEquals(0.0,exp1.getAmount());
        assertEquals("Expense Name: Car Insurance, Amount: $0.00",exp1.toString());

        System.out.println("Finished testExpenseNameOnly");
    }

    public void testExpenseNameAndAmountOnly()
    {
        System.out.println("\nStarting testExpenseNameAndAmountOnly");

        exp1 = new Expense("Cellphone Bill",60.0);
        assertNotNull(exp1);
        assertEquals("Cellphone Bill",exp1.getName());
        assertEquals(60.0,exp1.getAmount());
        assertEquals("Expense Name: Cellphone Bill, Amount: $60.00",exp1.toString());

        exp1.setAmount(75.0);
        assertNotNull(exp1);
        assertEquals("Cellphone Bill",exp1.getName());
        assertEquals(75.0,exp1.getAmount());
        assertEquals("Expense Name: Cellphone Bill, Amount: $75.00",exp1.toString());

        exp1.setAmount(95.0);
        assertEquals("Cellphone Bill",exp1.getName());
        assertEquals(95.0,exp1.getAmount());
        assertEquals("Expense Name: Cellphone Bill, Amount: $95.00",exp1.toString());

        System.out.println("Finished testExpenseNameAndAmountOnly");
    }

    public void testOddExpenseAmountsOnly()
    {
        System.out.println("\nStarting testOddExpenseAmountsOnly");

        exp1 = new Expense("Cellphone Bill",59.9999999999999);
        assertEquals(59.9999999999999,exp1.getAmount());
        assertEquals("Expense Name: Cellphone Bill, Amount: $60.00",exp1.toString());

        exp1.setAmount(123456789101112.0000000000001);
        assertEquals(123456789101112.0000000000001,exp1.getAmount());
        assertEquals("Expense Name: Cellphone Bill, Amount: $123456789101112.00",exp1.toString());

        System.out.println("Finished testOddExpenseAmountsOnly");
    }

    public void testEquals()
    {
        System.out.println("\nStarting testEquals");

        exp1 = new Expense("Cellphone Bill");
        exp2 = new Expense("Hydro Bill", 200.0);
        exp3 = new Expense("Cellphone Bill", 59.0);

        assertTrue(exp1.equals(exp3));
        assertTrue(!exp1.equals(exp2));

        System.out.println("Finished testEquals");
    }

}
