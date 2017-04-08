package comp3350.budgetapp.tests.objects;

import junit.framework.TestCase;
import comp3350.budgetapp.objects.WishListItem;

public class WishListItemTest extends TestCase
{
    public static WishListItem wish1, wish2, wish3;

    public void testWishListWithItemNameOnly()
    {
        System.out.println("\nStarting testWishListWithItemNameOnly");

        wish1 = new WishListItem("iPhone 9");
        assertNotNull(wish1);
        assertEquals("iPhone 9", wish1.getName());
        assertEquals(0.0, wish1.getAmount());
        assertEquals("Item Name: iPhone 9, Current Price: $0.00", wish1.toString());

        wish1.setAmount(900.23);
        assertEquals(900.23, wish1.getAmount());
        assertEquals("Item Name: iPhone 9, Current Price: $900.23", wish1.toString());

        System.out.println("Finished testWishListWithItemNameOnly");
    }

    public void testWishListWithItemNameAndPriceOnly()
    {
        System.out.println("\nStarting testWishListWithItemNameAndPriceOnly");

        wish1 = new WishListItem("iPhone 10", 1050.65);
        assertNotNull(wish1);
        assertEquals("iPhone 10", wish1.getName());
        assertEquals(1050.65, wish1.getAmount());
        assertEquals("Item Name: iPhone 10, Current Price: $1050.65", wish1.toString());

        wish1.setAmount(1029.99);
        assertEquals(1029.99, wish1.getAmount());
        assertEquals("Item Name: iPhone 10, Current Price: $1029.99", wish1.toString());

        System.out.println("Finished testWishListWithItemNameAndPriceOnly");
    }

    public void testOddWishListWithPriceOnly()
    {
        System.out.println("\nStarting testWishListWithItemNameOnly");

        wish1 = new WishListItem("iPhone 9",7999.00);
        assertNotNull(wish1);
        assertEquals("iPhone 9", wish1.getName());
        assertEquals(7999.00, wish1.getAmount());
        assertEquals("Item Name: iPhone 9, Current Price: $7999.00", wish1.toString());

        wish1.setAmount(123456789012349.9999999999999);
        assertNotNull(wish1);
        assertEquals("iPhone 9", wish1.getName());
        assertEquals(123456789012349.9999999999999, wish1.getAmount());
        assertEquals("Item Name: iPhone 9, Current Price: $123456789012350.00", wish1.toString());

        System.out.println("Finished testWishListWithItemNameOnly");
    }


    public void testEquals()
    {
        System.out.println("\nStarting testEquals");
        wish1 = new WishListItem("Samsung S9", 1499.99);
        wish2 = new WishListItem("Samsung S9", 400.99);
        wish3 = new WishListItem("Google Pixel");

        assertTrue(wish1.equals(wish2));
        assertFalse(wish1.equals(wish3));

        wish2 = new WishListItem(null, 100);
        assertFalse(wish2.equals(wish1));

        System.out.println("Finished testEquals");
    }


}
