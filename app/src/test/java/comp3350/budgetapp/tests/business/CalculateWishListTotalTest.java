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
        resultTotal = Calculate.wishlistTotal(list);

        assertNotNull(resultTotal);
        assertTrue(resultTotal.equals("1100.00"));
    }


}
