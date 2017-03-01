package comp3350.budgetapp.tests.business;


import junit.framework.TestCase;

import org.junit.*;
import java.util.ArrayList;

import comp3350.budgetapp.application.Main;
import comp3350.budgetapp.application.Services;
import comp3350.budgetapp.business.AccessWishListItems;
import comp3350.budgetapp.objects.WishListItem;

public class AccessWishListItemsTest extends TestCase
{
    public static ArrayList<WishListItem> list;
    WishListItem item;
    AccessWishListItems accessItems;


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
        accessItems = new AccessWishListItems();
        assertNull(accessItems.getWishListItems(list));
    }

    @Test
    public void testValidList()
    {
        list = new ArrayList<>();
        item = new WishListItem("iPhone", 500.0);
        list.add(item);
        assertTrue(list.contains(item));
    }

    @Test
    public void testValidListof2()
    {
        list = new ArrayList<>();
        item = new WishListItem("iPhone", 500.0);
        list.add(item);
        item = new WishListItem("iPad", 400.0);
        list.add(item);
        assertTrue(list.get(1).equals(item));

    }
}
