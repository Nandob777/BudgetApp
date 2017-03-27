package comp3350.budgetapp.tests.persistence;

import junit.framework.TestCase;
import junit.framework.TestResult;

import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

import comp3350.budgetapp.objects.WishListItem;
import comp3350.budgetapp.tests.persistence.DataAccessStub;

public class DataAccessStubTest extends TestCase {
    DataAccessStub db;
    ArrayList<WishListItem> wishList = new ArrayList<WishListItem>();

    public DataAccessStubTest(String arg0)
    {
        super(arg0);
        db = new DataAccessStub("Test");
        db.open("Test");
    }

    public void testGetWishListItemSequential()
    {

        assertNull(db.getWishListItemSequential(wishList));
        assertTrue(wishList.contains(new WishListItem("ChromeCast", 35.00)));
        assertFalse(wishList.contains(new WishListItem("ChromeCastNotExist")));
    }

    public void testGetWishListRandom()
    {
        WishListItem item1 = new WishListItem("ChromeCast", 35.00);
        WishListItem item2 = new WishListItem("ChromeCastNotExist", 35.00);
        WishListItem item3 = new WishListItem("ChromeCast");
        assertTrue(db.getWishListItemRandom(item1).contains(item1));
        assertFalse(db.getWishListItemRandom(item2).contains(item2));
        assertTrue(db.getWishListItemRandom(item3).contains(item3));
        assertTrue(db.getWishListItemRandom(item3).contains(item1));
    }

    public void testAddWishListItem()
    {
        WishListItem item1 = new WishListItem("Chrome", 0.00);
        assertNull(db.addWishListItem(item1));
        assertTrue(db.getWishListItemRandom(item1).contains(item1));
    }

    public void testUpdateWishListItem()
    {
        WishListItem item1 = new WishListItem("ChromeCast", 40.00);
        assertNull(db.updateWishListItem(item1));
        //assertEquals(wishList.get(item1));
    }

}
