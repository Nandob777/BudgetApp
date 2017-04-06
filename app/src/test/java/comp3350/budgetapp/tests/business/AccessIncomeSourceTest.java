package comp3350.budgetapp.tests.business;

import junit.framework.TestCase;

import java.util.ArrayList;

import comp3350.budgetapp.application.*;
import comp3350.budgetapp.tests.persistence.DataAccessStub;
import comp3350.budgetapp.business.AccessIncomeSource;
import comp3350.budgetapp.objects.FinancialObjects;
import comp3350.budgetapp.objects.IncomeSource;

public class AccessIncomeSourceTest extends TestCase
{
    private static String dbName = Main.dbName;

    private static ArrayList<FinancialObjects> list;
    private IncomeSource source;
    private IncomeSource tmpSrc;
    private AccessIncomeSource accessIncomeSource;
    private String result;

    public AccessIncomeSourceTest(String arg0)
    {
        super(arg0);
        list = new ArrayList<FinancialObjects>();
    }

    public void testGetSequentialIncomeSourceValid()
    {
        Services.closeDataAccess();

        System.out.println("\nStarting testGetSequentialIncomeSourceValid");

        Services.createDataAccess(new DataAccessStub(dbName));
        accessIncomeSource = new AccessIncomeSource();

        source = accessIncomeSource.getSequential();
        assertNotNull(source);
        assertEquals("Starbucks", source.getName());

        source = accessIncomeSource.getSequential();
        assertNotNull(source);
        assertEquals("Bartending", source.getName());

        Services.closeDataAccess();

        System.out.println("Finished testGetSequentialIncomeSourceValid");
    }

    public void testGetSequentialIncomeSourceTooMany()
    {
        Services.closeDataAccess();

        System.out.println("\nStarting testGetSequentialIncomeSourceTooMany");

        Services.createDataAccess(new DataAccessStub(dbName));
        accessIncomeSource = new AccessIncomeSource();

        source = accessIncomeSource.getSequential();
        assertNotNull(source);
        assertEquals("Starbucks", source.getName());

        source = accessIncomeSource.getSequential();
        assertNotNull(source);
        assertEquals("Bartending", source.getName());

        source = accessIncomeSource.getSequential();
        assertNull(source);

        Services.closeDataAccess();

        System.out.println("Finished testGetSequentialIncomeSourceTooMany");
    }

    public void testGetSequentialInvalidIncomeSources()
    {
        Services.closeDataAccess();

        System.out.println("\nStarting testGetSequentialInvalidIncomeSources");

        Services.createDataAccess(new DataAccessStub(dbName));
        accessIncomeSource = new AccessIncomeSource();

        source = accessIncomeSource.getSequential();
        assertNotNull(source);
        assertFalse("Application Developer".equals(source.getName()));

        source = accessIncomeSource.getSequential();
        assertNotNull(source);
        assertFalse("Security Analyst".equals(source.getName()));

        Services.closeDataAccess();

        System.out.println("Finished testGetSequentialInvalidIncomeSources");
    }

    public void testGetSequentialMixedInvalidIncomeSources()
    {
        Services.closeDataAccess();

        System.out.println("\nStarting testGetSequentialMixedInvalidIncomeSources");

        Services.createDataAccess(new DataAccessStub(dbName));
        accessIncomeSource = new AccessIncomeSource();

        source = accessIncomeSource.getSequential();
        assertNotNull(source);
        assertEquals("Starbucks", source.getName());

        source = accessIncomeSource.getSequential();
        assertNotNull(source);
        assertFalse("Security Analyst".equals(source.getName()));

        Services.closeDataAccess();

        System.out.println("Finished testGetSequentialMixedInvalidIncomeSources");
    }

    public void testGetRandomIncomeSourceValid()
    {
        Services.closeDataAccess();

        System.out.println("\nStarting testGetRandomIncomeSourceValid");

        Services.createDataAccess(new DataAccessStub(dbName));
        accessIncomeSource = new AccessIncomeSource();

        source = accessIncomeSource.getRandom("Bartending");
        assertNotNull(source);
        assertEquals("Bartending",source.getName());

        source = accessIncomeSource.getRandom("Starbucks");
        assertNotNull(source);
        assertEquals("Starbucks",source.getName());

        Services.closeDataAccess();

        System.out.println("Finished testGetRandomIncomeSourceValid");
    }

    public void testGetRandomIncomeSourceInvalid()
    {
        Services.closeDataAccess();

        System.out.println("\nStarting testGetRandomIncomeSourceInvalid");

        Services.createDataAccess(new DataAccessStub(dbName));
        accessIncomeSource = new AccessIncomeSource();

        source = accessIncomeSource.getRandom("Security Analyst");
        assertNull(source);

        source = accessIncomeSource.getRandom("Application Developer");
        assertNull(source);

        source = accessIncomeSource.getRandom("Personal Assistant");
        assertNull(source);

        Services.closeDataAccess();

        System.out.println("Finished testGetRandomIncomeSourceInvalid");
    }

    public void testGetRandomIncomeSourceMixed()
    {
        Services.closeDataAccess();

        System.out.println("\nStarting testGetRandomIncomeSourceMixed");

        Services.createDataAccess(new DataAccessStub(dbName));
        accessIncomeSource = new AccessIncomeSource();

        source = accessIncomeSource.getRandom("Bartending");
        assertNotNull(source);
        assertEquals("Bartending",source.getName());

        source = accessIncomeSource.getRandom("Security Analyst");
        assertEquals(null,source);

        source = accessIncomeSource.getRandom("Application Developer");
        assertEquals(null,source);

        source = accessIncomeSource.getRandom("Starbucks");
        assertNotNull(source);
        assertEquals("Starbucks",source.getName());

        source = accessIncomeSource.getRandom("Personal Assistant");
        assertEquals(null,source);

        Services.closeDataAccess();

        System.out.println("Finished testGetRandomIncomeSourceMixed");
    }

    public void testAddIncomeSources()
    {
        Services.closeDataAccess();

        System.out.println("\nStarting testAddIncomeSources");

        Services.createDataAccess(new DataAccessStub(dbName));
        accessIncomeSource = new AccessIncomeSource();

        source = new IncomeSource("Security Analyst",1200.0);
        result = accessIncomeSource.addIncomeSource(source);
        tmpSrc = accessIncomeSource.getRandom(source.getName());
        assertNotNull(tmpSrc);
        assertEquals(source,tmpSrc);
        assertEquals(source.getName(),tmpSrc.getName());
        assertEquals(source.getAmount(),tmpSrc.getAmount());
        assertNull(result);

        source = new IncomeSource("Personal Assistant",1000.0);
        result = accessIncomeSource.addIncomeSource(source);
        tmpSrc = accessIncomeSource.getRandom(source.getName());
        assertNotNull(tmpSrc);
        assertEquals(source,tmpSrc);
        assertEquals(source.getName(),tmpSrc.getName());
        assertEquals(source.getAmount(),tmpSrc.getAmount());
        assertNull(result);

        Services.closeDataAccess();

        System.out.println("Finished testAddIncomeSources");
    }

    public void testUpdateValidIncomeSources()
    {
        Services.closeDataAccess();

        System.out.println("\nStarting testUpdateValidIncomeSources");

        Services.createDataAccess(new DataAccessStub(dbName));
        accessIncomeSource = new AccessIncomeSource();

        tmpSrc = accessIncomeSource.getRandom("Bartending");
        assertNotNull(tmpSrc);
        assertEquals("Bartending",tmpSrc.getName());
        assertEquals(700.00,tmpSrc.getAmount());
        source = new IncomeSource("Bartending", 770.00);
        result = accessIncomeSource.updateIncomeSource(source);
        tmpSrc = accessIncomeSource.getRandom(source.getName());
        assertNotNull(tmpSrc);
        assertEquals(source,tmpSrc);
        assertEquals(source.getName(),tmpSrc.getName());
        assertEquals(source.getAmount(),tmpSrc.getAmount());
        assertNull(result);

        tmpSrc = accessIncomeSource.getRandom("Starbucks");
        assertNotNull(tmpSrc);
        assertEquals("Starbucks",tmpSrc.getName());
        assertEquals(800.00,tmpSrc.getAmount());
        source = new IncomeSource("Starbucks", 900.00);
        result = accessIncomeSource.updateIncomeSource(source);
        tmpSrc = accessIncomeSource.getRandom(source.getName());
        assertNotNull(tmpSrc);
        assertEquals(source,tmpSrc);
        assertEquals(source.getName(),tmpSrc.getName());
        assertEquals(source.getAmount(),tmpSrc.getAmount());
        assertNull(result);

        Services.closeDataAccess();

        System.out.println("Finished testUpdateValidIncomeSources");
    }

    public void testUpdateInvalidIncomeSources()
    {
        Services.closeDataAccess();

        System.out.println("\nStarting testUpdateInvalidIncomeSources");

        Services.createDataAccess(new DataAccessStub(dbName));
        accessIncomeSource = new AccessIncomeSource();

        source = new IncomeSource("Security Analyst", 1200.00);
        result = accessIncomeSource.updateIncomeSource(source);
        assertNull(result);

        source = new IncomeSource("Application Developer", 1600.00);
        result = accessIncomeSource.updateIncomeSource(source);
        assertNull(result);

        Services.closeDataAccess();

        System.out.println("Finished testUpdateInvalidIncomeSources");
    }

    public void testDeleteValidItems()
    {
        Services.closeDataAccess();

        System.out.println("\nStarting testDeleteValidItems");

        Services.createDataAccess(new DataAccessStub(dbName));
        accessIncomeSource = new AccessIncomeSource();

        source = accessIncomeSource.getRandom("Bartending");
        result = accessIncomeSource.deleteIncomeSource(source);
        source = accessIncomeSource.getRandom("Bartending");
        assertNull(source);
        assertNull(result);

        source = accessIncomeSource.getRandom("Starbucks");
        result = accessIncomeSource.deleteIncomeSource(source);
        source = accessIncomeSource.getRandom("Starbucks");
        assertNull(source);
        assertNull(result);

        Services.closeDataAccess();

        System.out.println("Finished testDeleteValidItems");
    }

    public void testDeleteInvalidItems()
    {
        Services.closeDataAccess();

        System.out.println("\nStarting testDeleteInvalidItems");

        Services.createDataAccess(new DataAccessStub(dbName));
        accessIncomeSource = new AccessIncomeSource();

        source = new IncomeSource("Security Analyst",1200.0);
        result = accessIncomeSource.deleteIncomeSource(source);
        assertNull(result);

        source = new IncomeSource("Application Developer",1600.0);
        result = accessIncomeSource.deleteIncomeSource(source);
        assertNull(result);

        source = new IncomeSource("Cashier",350.0);
        result = accessIncomeSource.deleteIncomeSource(source);
        assertNull(result);

        source = new IncomeSource("Sales Associate",400.0);
        result = accessIncomeSource.deleteIncomeSource(source);
        assertNull(result);

        Services.closeDataAccess();

        System.out.println("Finished testDeleteInvalidItems");
    }


}
