package comp3350.budgetapp.tests.business;

import junit.framework.TestCase;
import org.junit.*;
import java.util.ArrayList;
import comp3350.budgetapp.business.Calculate;
import comp3350.budgetapp.objects.WishListItem;

public class CalculateWishListTotalTest extends TestCase
{
    public static ArrayList <WishListItem> list;
    WishListItem item;
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
        list = null;
        assertNull(list);
    }

    @Test
    public void testEmptyList()
    {
        list = new ArrayList<>();
        resultTotal = Calculate.wishlistTotal(list);

        assertNotNull(list);
        assertTrue(resultTotal.equals(""));
    }

    @Test
    public void testValidList()
    {
        list = new ArrayList<>();
        item = new WishListItem("iPhone", 500.0);
        list.add(item);
        item = new WishListItem("TV", 600.0);
        list.add(item);
        item = new WishListItem("Laptop", 2000.0);
        list.add(item);
        resultTotal = Calculate.wishlistTotal(list);

        assertNotNull(resultTotal);
        assertTrue(resultTotal.equals("3100.00"));
    }

    @Test
    public void testOneItem()
    {
        list = new ArrayList<>();

        item = new WishListItem("iPhone", 500.0);
        list.add(item);
        resultTotal = Calculate.wishlistTotal(list);
        assertEquals(resultTotal,"500.00");

        item = new WishListItem("TV", 7000.0);
        list.set(0,item);
        resultTotal = Calculate.wishlistTotal(list);
        assertEquals(resultTotal,"7000.00");

        item = new WishListItem("Laptop", 2000.0);
        list.set(0,item);
        resultTotal = Calculate.wishlistTotal(list);
        assertEquals(resultTotal,"2000.00");

        item = new WishListItem("MP3 Player", 200.0);
        list.set(0,item);
        resultTotal = Calculate.wishlistTotal(list);
        assertEquals(resultTotal,"200.00");

        item = new WishListItem("Chromebook", 400.0);
        list.set(0,item);
        resultTotal = Calculate.wishlistTotal(list);
        assertEquals(resultTotal,"400.00");

        item = new WishListItem("Backpack", 100.0);
        list.set(0,item);
        resultTotal = Calculate.wishlistTotal(list);
        assertEquals(resultTotal,"100.00");
    }

    @Test
    public void testOddList()
    {
        list = new ArrayList<>();
        item = new WishListItem("iPhone", 500.9999999);
        list.add(item);
        item = new WishListItem("TV", 600.9999999);
        list.add(item);
        item = new WishListItem("Laptop", 2000.9999999);
        list.add(item);
        resultTotal = Calculate.wishlistTotal(list);

        assertNotNull(resultTotal);
        assertFalse(resultTotal.equals("3100.00"));
    }

    @Test
    public void testNullItem()
    {
        list = new ArrayList<>();
        item = new WishListItem("iPhone", 500.0);
        list.add(item);
        list.add(null);
        item = new WishListItem("Laptop", 2000.0);
        list.add(item);

        resultTotal = Calculate.wishlistTotal(list);

        assertEquals(resultTotal,"?");//invalidTotal
    }
}
