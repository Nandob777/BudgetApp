package comp3350.budgetapp.tests.objects;

import junit.framework.TestCase;

import comp3350.budgetapp.objects.IncomeSource;

public class IncomeSourceTest extends TestCase
{
    public static IncomeSource nullSrc = null;

    private IncomeSource createIncomeSource(String name, int amount)
    {
        IncomeSource src = new IncomeSource(name,amount);

        return src;
    }

    public void testSingleNull()
    {
        System.out.println("\nStarting testSingleNull");
        assertNull(nullSrc);
        System.out.println("Finished testSingleNull");
    }

    public void testSingleInitialized()
    {
        IncomeSource newSrc;
        System.out.println("\nStarting testSingleInitialized");

        newSrc = createIncomeSource("Part-time Sales Associate",11);
        assertNotNull(newSrc);
        assertTrue("Part-time Sales Associate".equals(newSrc.getSourceName()));
        assertTrue(11 == newSrc.getAmount());
        System.out.println("Finished testSingleInitialized");

    }
}
