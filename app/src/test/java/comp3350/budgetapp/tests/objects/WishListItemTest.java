package comp3350.budgetapp.tests.objects;

import junit.framework.TestCase;
import comp3350.budgetapp.objects.WishListItem;

public class WishListItemTest extends TestCase
{
    public static WishListItem wish1, wish2, wish3;
    public static final double delta = 0.001;


    public void testWishListWithItemNameOnly()
    {
        System.out.println("\nStarting testWishListWithItemNameOnly");

        wish1 = new WishListItem("iPhone 9");
        assertNotNull(wish1);
        assertEquals("iPhone 9", wish1.getItemName());
        assertEquals(0.0, wish1.getPrice());
        assertEquals(0, wish1.getPriority());
        assertEquals("Item Name: iPhone 9, Current Price with 0% discount: $0.00", wish1.toString());

        wish1.setPrice(900.23);
        assertEquals(900.23, wish1.getPrice());
        assertEquals("Item Name: iPhone 9, Current Price with 0% discount: $900.23", wish1.toString());

        wish1.setPriority(3);
        assertEquals(3, wish1.getPriority());

        wish1.setDiscount(20);
        assertEquals(720.1840000000001, wish1.getPrice());
        assertEquals("Item Name: iPhone 9, Current Price with 20% discount: $720.18", wish1.toString());

        System.out.println("Finished testWishListWithItemNameOnly");
    }

    public void testWIshListWithItemNameAndPriceOnly()
    {
        System.out.println("\nStarting testWIshListWithItemNameAndPriceOnly");

        wish1 = new WishListItem("iPhone 10", 1050.65);
        assertNotNull(wish1);
        assertEquals("iPhone 10", wish1.getItemName());
        assertEquals(1050.65, wish1.getPrice());
        assertEquals(0, wish1.getPriority());
        assertEquals("Item Name: iPhone 10, Current Price with 0% discount: $1050.65", wish1.toString());

        wish1.setPrice(1029.99);
        assertEquals(1029.99, wish1.getPrice());
        assertEquals("Item Name: iPhone 10, Current Price with 0% discount: $1029.99", wish1.toString());

        wish1.setDiscount(25);
        assertEquals(772.4925000000001, wish1.getPrice());
        assertEquals("Item Name: iPhone 10, Current Price with 25% discount: $772.49", wish1.toString());

        System.out.println("Finished testWIshListWithItemNameAndPriceOnly");
    }

    public void testWIshListWithItemNamePriceAndDiscount()
    {
        System.out.println("\nStarting testWIshListWithItemNamePriceAndDiscount");

        wish1 = new WishListItem("iPhone 11", 1999.99, 15);
        assertNotNull(wish1);
        assertEquals("iPhone 11", wish1.getItemName());
        assertEquals(1699.9914999999999, wish1.getPrice());
        assertEquals(0, wish1.getPriority());
        assertEquals("Item Name: iPhone 11, Current Price with 15% discount: $1699.99", wish1.toString());

        wish1.setPrice(1599.99);
        assertEquals(1359.9914999999999, wish1.getPrice());
        assertEquals("Item Name: iPhone 11, Current Price with 15% discount: $1359.99", wish1.toString());

        wish1.setDiscount(25);
        assertEquals(1019.9936249999998, wish1.getPrice());
        assertEquals("Item Name: iPhone 11, Current Price with 25% discount: $1019.99", wish1.toString());

        System.out.println("Finished testWIshListWithItemNamePriceAndDiscount");
    }

    public void testEquals()
    {
        System.out.println("\nStarting testEquals");
        wish1 = new WishListItem("Samsung S9", 1499.99, 15);
        wish2 = new WishListItem("Samsung S9", 400.99);
        wish3 = new WishListItem("Google Pixel");

        assertTrue(wish1.equals(wish2));
        assertFalse(wish1.equals(wish3));

        //wish2 = new WishListItem(null);
        //assertFalse(wish1.equals(wish2));

        System.out.println("Finished testEquals");

    }


}
