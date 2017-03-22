package comp3350.budgetapp.tests.business;


import junit.framework.TestCase;

import org.junit.*;
import java.util.ArrayList;


import comp3350.budgetapp.business.AccessWishListItems;
import comp3350.budgetapp.objects.WishListItem;

public class AccessWishListItemsTest extends TestCase
{
    private static ArrayList<WishListItem> list;
    private WishListItem item;
    private AccessWishListItems accessItems;
    private String result;



    public void testNullList()
    {
        list = null;
        assertNull(list);
    }


    public void testEmptyList()
    {
        list = new ArrayList<>();
        accessItems = new AccessWishListItems();
        assertNull(accessItems.getWishListItems(list));
    }


    public void testValidList()
    {
        list = new ArrayList<>();
        accessItems = new AccessWishListItems();
        item = new WishListItem("iPhone", 500.0);
        list.add(item);
        accessItems.addWishListItem(item);
        accessItems.getWishListItems(list);
        assertSame("iPhone",accessItems.getRandom("iPhone").getItemName());
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
