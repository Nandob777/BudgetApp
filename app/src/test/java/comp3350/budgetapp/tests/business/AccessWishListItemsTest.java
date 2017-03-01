package comp3350.budgetapp.tests.business;


import junit.framework.TestCase;

import org.junit.*;
import java.util.ArrayList;

import comp3350.budgetapp.business.AccessWishListItems;
import comp3350.budgetapp.objects.WishListItem;

public class AccessWishListItemsTest extends TestCase
{
    public static ArrayList<WishListItem> list;
    WishListItem item;


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
        //assertNull(AccessWishListItems.getWishListItems(list));
    }

    @Test
    public void testValidList()
    {
        list = new ArrayList<>();
        item = new WishListItem("iPhone", 500.0);


    }
}
