package comp3350.budgetapp.tests.business;


import junit.framework.TestCase;

import java.util.ArrayList;

import comp3350.budgetapp.application.*;
import comp3350.budgetapp.tests.persistence.DataAccessStub;
import comp3350.budgetapp.business.AccessWishListItems;
import comp3350.budgetapp.objects.FinancialObjects;
import comp3350.budgetapp.objects.WishListItem;

public class AccessWishListItemsTest extends TestCase
{
    private static ArrayList<FinancialObjects> list;
    private WishListItem item;
    private WishListItem tmpItem;
    private AccessWishListItems accessItems;
    private String result;

    private static String dbName = Main.dbName;

    public AccessWishListItemsTest(String arg0)
    {
        super(arg0);
        list = new ArrayList<FinancialObjects>();
    }

    public void testGetSequentialItemValid()
    {
        Services.closeDataAccess();

        System.out.println("\nStarting testGetSequentialItemValid");

        Services.createDataAccess(new DataAccessStub(dbName));
        accessItems = new AccessWishListItems();

        item = accessItems.getSequential();
        assertNotNull(item);
        assertEquals("Google Pixel",item.getName());

        item = accessItems.getSequential();
        assertNotNull(item);
        assertEquals("Google Home",item.getName());

        item = accessItems.getSequential();
        assertNotNull(item);
        assertEquals("Daydream View",item.getName());

        item = accessItems.getSequential();
        assertNotNull(item);
        assertEquals("ChromeCast",item.getName());

        Services.closeDataAccess();

        System.out.println("Finished testGetSequentialItemValid");
    }

    public void testGetSequentialTooMany()
    {
        Services.closeDataAccess();

        System.out.println("\nStarting testGetSequentialTooMany");

        Services.createDataAccess(new DataAccessStub(dbName));
        accessItems = new AccessWishListItems();

        item = accessItems.getSequential();
        assertNotNull(item);
        assertEquals("Google Pixel",item.getName());

        item = accessItems.getSequential();
        assertNotNull(item);
        assertEquals("Google Home",item.getName());

        item = accessItems.getSequential();
        assertNotNull(item);
        assertEquals("Daydream View",item.getName());

        item = accessItems.getSequential();
        assertNotNull(item);
        assertEquals("ChromeCast",item.getName());

        item = accessItems.getSequential();
        assertNull(item);

        Services.closeDataAccess();

        System.out.println("Finished testGetSequentialTooMany");
    }

    public void testGetSequentialInvalidItems()
    {
        Services.closeDataAccess();

        System.out.println("\nStarting testGetSequentialInvalidItems");

        Services.createDataAccess(new DataAccessStub(dbName));
        accessItems = new AccessWishListItems();

        item = accessItems.getSequential();
        assertNotNull(item);
        assertFalse("Google iPhone".equals(item.getName()));

        item = accessItems.getSequential();
        assertNotNull(item);
        assertFalse("Macbook Water".equals(item.getName()));

        item = accessItems.getSequential();
        assertNotNull(item);
        assertFalse("iPeePod".equals(item.getName()));

        item = accessItems.getSequential();
        assertNotNull(item);
        assertFalse("Nightmare View".equals(item.getName()));

        Services.closeDataAccess();

        System.out.println("Finished testGetSequentialInvalidItems");
    }


    public void testGetSequentialMixedInvalidItems()
    {
        Services.closeDataAccess();

        System.out.println("\nStarting testGetSequentialMixedInvalidItems");

        Services.createDataAccess(new DataAccessStub(dbName));
        accessItems = new AccessWishListItems();

        item = accessItems.getSequential();
        assertNotNull(item);
        assertFalse("Google iPhone".equals(item.getName()));

        item = accessItems.getSequential();
        assertNotNull(item);
        assertEquals("Google Home",item.getName());

        item = accessItems.getSequential();
        assertNotNull(item);
        assertFalse("iPeePod".equals(item.getName()));

        item = accessItems.getSequential();
        assertNotNull(item);
        assertEquals("ChromeCast",item.getName());

        Services.closeDataAccess();

        System.out.println("Finished testGetSequentialMixedInvalidItems");
    }

    public void testGetRandomItemValid()
    {
        Services.closeDataAccess();

        System.out.println("\nStarting testGetRandomItemValid");

        Services.createDataAccess(new DataAccessStub(dbName));
        accessItems = new AccessWishListItems();

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

        Services.closeDataAccess();

        System.out.println("Finished testGetRandomItemValid");
    }

    public void testGetRandomItemInvalid()
    {
        Services.closeDataAccess();

        System.out.println("\nStarting testGetRandomItemInvalid");

        Services.createDataAccess(new DataAccessStub(dbName));
        accessItems = new AccessWishListItems();

        item = accessItems.getRandom("Google iPhone");
        assertEquals(null,item);

        item = accessItems.getRandom("Macbook Water");
        assertEquals(null,item);

        item = accessItems.getRandom("Nightmare View");
        assertEquals(null,item);

        Services.closeDataAccess();

        System.out.println("Finished testGetRandomItemInvalid");
    }

    public void testGetRandomItemMixed()
    {
        Services.closeDataAccess();

        System.out.println("\nStarting testGetRandomItemMixed");

        Services.createDataAccess(new DataAccessStub(dbName));
        accessItems = new AccessWishListItems();

        item = accessItems.getRandom("Google iPhone");
        assertEquals(null,item);

        item = accessItems.getRandom("Google Pixel");
        assertNotNull(item);
        assertEquals("Google Pixel",item.getName());

        item = accessItems.getRandom("Nightmare View");
        assertEquals(null,item);

        item = accessItems.getRandom("Daydream View");
        assertNotNull(item);
        assertEquals("Daydream View",item.getName());

        Services.closeDataAccess();

        System.out.println("Finished testGetRandomItemMixed");
    }

    public void testAddItems()
    {
        Services.closeDataAccess();

        System.out.println("\nStarting testAddItems");

        Services.createDataAccess(new DataAccessStub(dbName));
        accessItems = new AccessWishListItems();

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

        item = new WishListItem("Apple Watch",359.0);
        result = accessItems.addWishListItem(item);
        tmpItem = accessItems.getRandom(item.getName());
        assertNotNull(tmpItem);
        assertEquals(item,tmpItem);
        assertEquals(item.getName(),tmpItem.getName());
        assertEquals(item.getAmount(),tmpItem.getAmount());
        assertNull(result);

        item = new WishListItem("iMac",1399.0);
        result = accessItems.addWishListItem(item);
        tmpItem = accessItems.getRandom(item.getName());
        assertNotNull(tmpItem);
        assertEquals(item,tmpItem);
        assertEquals(item.getName(),tmpItem.getName());
        assertEquals(item.getAmount(),tmpItem.getAmount());
        assertNull(result);

        Services.closeDataAccess();

        System.out.println("Finished testAddItems");
    }

    public void testUpdateValidItems()
    {
        Services.closeDataAccess();

        System.out.println("\nStarting testUpdateValidItems");

        Services.createDataAccess(new DataAccessStub(dbName));
        accessItems = new AccessWishListItems();

        tmpItem = accessItems.getRandom("Google Pixel");
        assertEquals("Google Pixel",tmpItem.getName());
        assertEquals(899.00,tmpItem.getAmount());
        item = new WishListItem("Google Pixel",1099.0);
        result = accessItems.updateWishListItem(item);
        tmpItem = accessItems.getRandom(item.getName());
        assertNotNull(tmpItem);
        assertEquals(item,tmpItem);
        assertEquals(item.getName(),tmpItem.getName());
        assertEquals(item.getAmount(),tmpItem.getAmount());
        assertNull(result);

        tmpItem = accessItems.getRandom("Google Home");
        assertEquals("Google Home",tmpItem.getName());
        assertEquals(130.00,tmpItem.getAmount());
        item = new WishListItem("Google Home",99.0);
        result = accessItems.updateWishListItem(item);
        tmpItem = accessItems.getRandom(item.getName());
        assertNotNull(tmpItem);
        assertEquals(item,tmpItem);
        assertEquals(item.getName(),tmpItem.getName());
        assertEquals(item.getAmount(),tmpItem.getAmount());
        assertNull(result);

        tmpItem = accessItems.getRandom("Daydream View");
        assertEquals("Daydream View",tmpItem.getName());
        assertEquals(99.00,tmpItem.getAmount());
        item = new WishListItem("Daydream View",59.00);
        result = accessItems.updateWishListItem(item);
        tmpItem = accessItems.getRandom(item.getName());
        assertNotNull(tmpItem);
        assertEquals(item,tmpItem);
        assertEquals(item.getName(),tmpItem.getName());
        assertEquals(item.getAmount(),tmpItem.getAmount());
        assertNull(result);

        Services.closeDataAccess();

        System.out.println("Finished testUpdateValidItems");
    }

    public void testUpdateInvalidItems()
    {
        Services.closeDataAccess();

        System.out.println("\nStarting testUpdateInvalidItems");

        Services.createDataAccess(new DataAccessStub(dbName));
        accessItems = new AccessWishListItems();

        item = new WishListItem("Apple iPad",449.0);
        result = accessItems.updateWishListItem(item);
        assertNull(result);

        item = new WishListItem("Apple Watch",359.0);
        result = accessItems.updateWishListItem(item);
        assertNull(result);

        item = new WishListItem("MacBook Pro",1200.0);
        result = accessItems.updateWishListItem(item);
        assertNull(result);

        Services.closeDataAccess();

        System.out.println("Finished testUpdateInvalidItems");
    }

    public void testDeleteValidItems()
    {
        Services.closeDataAccess();

        System.out.println("\nStarting testDeleteValidItems");

        Services.createDataAccess(new DataAccessStub(dbName));
        accessItems = new AccessWishListItems();

        item = accessItems.getRandom("Google Pixel");
        result = accessItems.deleteWishListItem(item);
        item = accessItems.getRandom("Google Pixel");
        assertNull(item);
        assertNull(result);

        item = accessItems.getRandom("Google Home");
        result = accessItems.deleteWishListItem(item);
        item = accessItems.getRandom("Google Home");
        assertNull(item);
        assertNull(result);

        item = accessItems.getRandom("Daydream View");
        result = accessItems.deleteWishListItem(item);
        item = accessItems.getRandom("Daydream View");
        assertNull(item);
        assertNull(result);

        item = accessItems.getRandom("ChromeCast");
        result = accessItems.deleteWishListItem(item);
        item = accessItems.getRandom("ChromeCast");
        assertNull(item);
        assertNull(result);

        Services.closeDataAccess();

        System.out.println("Finished testDeleteValidItems");
    }

    public void testDeleteInvalidItems()
    {
        Services.closeDataAccess();

        System.out.println("\nStarting testDeleteInvalidItems");

        Services.createDataAccess(new DataAccessStub(dbName));
        accessItems = new AccessWishListItems();

        item = new WishListItem("Apple iPad",449.0);
        result = accessItems.deleteWishListItem(item);
        assertNull(result);

        item = new WishListItem("Apple iPhone",899.0);
        result = accessItems.deleteWishListItem(item);
        assertNull(result);

        item = new WishListItem("Apple Watch",359.0);
        result = accessItems.deleteWishListItem(item);
        assertNull(result);

        item = new WishListItem("MacBook Pro",1200.0);
        result = accessItems.deleteWishListItem(item);
        assertNull(result);

        Services.closeDataAccess();

        System.out.println("Finished testDeleteInvalidItems");
    }

}
