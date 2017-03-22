package comp3350.budgetapp.tests.business;

import junit.framework.TestCase;
import java.util.ArrayList;
import comp3350.budgetapp.business.Calculate;
import comp3350.budgetapp.objects.WishListItem;

public class CalculateWishListTotalTest extends TestCase
{
    private static ArrayList <WishListItem> list;
    private WishListItem item;

    public void setUp()
    {
        list = new ArrayList <>();
    }


    public void testNullList()
    {
        System.out.println("\nStarting testNullList");

        assertEquals("", Calculate.wishlistTotal(null));

        System.out.println("Finished testNullList");
    }


    public void testEmptyList()
    {
        System.out.println("\nStarting testEmptyList");

        assertNotNull(list);
        assertEquals("", Calculate.wishlistTotal(list));

        System.out.println("Finished testEmptyList");
    }


    public void testValidList()
    {
        System.out.println("\nStarting testValidList");

        item = new WishListItem("iPhone", 500.0);
        list.add(item);
        item = new WishListItem("TV", 600.0);
        list.add(item);
        item = new WishListItem("Laptop", 2000.0);
        list.add(item);

        assertEquals("3100.00", Calculate.wishlistTotal(list));

        System.out.println("Finished testValidList");
    }


    public void testOneItem()
    {
        System.out.println("\nStarting testOneItem");

        item = new WishListItem("iPhone", 500.0);
        list.add(item);
        assertEquals("500.00", Calculate.wishlistTotal(list));

        item = new WishListItem("TV", 7000.0);
        list.set(0,item);
        assertEquals("7000.00", Calculate.wishlistTotal(list));

        item = new WishListItem("Laptop", 2000.0);
        list.set(0,item);
        assertEquals("2000.00", Calculate.wishlistTotal(list));

        item = new WishListItem("MP3 Player", 200.0);
        list.set(0,item);
        assertEquals("200.00", Calculate.wishlistTotal(list));

        item = new WishListItem("Chromebook", 400.0);
        list.set(0,item);
        assertEquals("400.00", Calculate.wishlistTotal(list));

        item = new WishListItem("Backpack", 100.0);
        list.set(0,item);
        assertEquals("100.00", Calculate.wishlistTotal(list));

        System.out.println("Finished testOneItem");
    }


    public void testOddList()
    {
        System.out.println("\nStarting testOddList");

        item = new WishListItem("iPhone", 500.9999999);
        list.add(item);
        item = new WishListItem("TV", 600.9999999);
        list.add(item);
        item = new WishListItem("Laptop", 2000.9999999);
        list.add(item);

        assertEquals("3103.00", Calculate.wishlistTotal(list));

        System.out.println("Finished testOddList");
    }


    public void testBlankPrices()
    {
        System.out.println("\nStarting testBlankPrices");

        item = new WishListItem("iPhone");
        list.add(item);

        item = new WishListItem("TV");
        list.add(item);

        item = new WishListItem("Laptop");
        list.add(item);

        item = new WishListItem("Backpack");
        list.add(item);

        item = new WishListItem("Desk Chair");
        list.add(item);

        assertEquals("", Calculate.wishlistTotal(list));

        System.out.println("Finished testBlankPrices");
    }


    public void testSomeBlankPrices()
    {
        System.out.println("\nStarting testSomeBlankPrices");

        item = new WishListItem("iPhone", 500.0);
        list.add(item);

        item = new WishListItem("TV", 7000.0);
        list.add(item);

        item = new WishListItem("Laptop", 2000.0);
        list.add(item);

        item = new WishListItem("Backpack");
        list.add(item);

        item = new WishListItem("Desk Chair");
        list.add(item);

        assertEquals("9500.00", Calculate.wishlistTotal(list));

        System.out.println("Finished testSomeBlankPrices");
    }


    public void testNullItem()
    {
        System.out.println("\nStarting testNullItem");

        item = new WishListItem("iPhone", 500.0);
        list.add(item);
        list.add(null);
        item = new WishListItem("Laptop", 2000.0);
        list.add(item);

        assertEquals("?", Calculate.wishlistTotal(list));//invalid total

        System.out.println("Finished testNullItem");
    }


    public void testNegativePrices()
    {
        System.out.println("\nStarting testNegativePrices");

        item = new WishListItem("iPhone", -(500.0));
        list.add(item);

        item = new WishListItem("TV", -(7000.0));
        list.add(item);

        item = new WishListItem("Laptop", -(2000.0));
        list.add(item);

        assertEquals("",Calculate.wishlistTotal(list));

        System.out.println("Finished testNegativePrices");
    }


    public void testSomeNegativePrices()
    {
        System.out.println("\nStarting testSomeNegativePrices");

        item = new WishListItem("iPhone", -(500.0));
        list.add(item);

        item = new WishListItem("TV", 7000.0);
        list.add(item);

        item = new WishListItem("Laptop", -(2000.0));
        list.add(item);

        assertEquals("7000.00",Calculate.wishlistTotal(list));

        System.out.println("Finished testSomeNegativePrices");
    }
}
