package comp3350.budgetapp.tests.business;

import junit.framework.TestCase;
import java.util.List;
import java.util.ArrayList;

import comp3350.budgetapp.business.Calculate;
import comp3350.budgetapp.objects.WishListItem;

public class CalculateWishListTotalTest extends TestCase
{
    List <WishListItem> list;
    WishListItem item;
    String resultTotal;
    double total;

    public void testValidList()
    {
        resultTotal = "";
        list = new ArrayList<WishListItem>();
        item = new WishListItem("iPhone", 500.0);
        list.add(item);
        item = new WishListItem("TV", 600.0);
        list.add(item);
        resultTotal = Calculate.wishlistTotal(list);

        total = 500.0+600.0;

        assertNotNull(resultTotal);
        assertTrue(resultTotal.equals("1100.00"));
    }
}
