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
    String result;


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
        accessItems = new AccessWishListItems();
        item = new WishListItem("iPhone", 500.0);
        list.add(item);
        accessItems.addWishListItem(item);
        accessItems.getWishListItems(list);
        assertSame(accessItems.getRandom(item.getItemName()) , item);
    }


    public void testDelete()
    {
        accessItems = new AccessWishListItems();
        item = new WishListItem("iPhone", 500.0);
        list.add(item);
        accessItems.addWishListItem(item);
        accessItems.deleteWishListItem(item);
        assertNull(accessItems.getWishListItems(list));
    }

}
