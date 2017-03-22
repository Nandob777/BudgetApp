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

    public void testWishListWithItemNameAndPriceOnly()
    {
        System.out.println("\nStarting testWishListWithItemNameAndPriceOnly");

        wish1 = new WishListItem("iPhone 10", 1050.65);
        assertNotNull(wish1);
        assertEquals("iPhone 10", wish1.getItemName());
        assertEquals(1050.65, wish1.getPrice());
        assertEquals(0, wish1.getPriority());
        assertEquals("Item Name: iPhone 10, Current Price with 0% discount: $1050.65", wish1.toString());

        wish1.setPriority(5);
        assertEquals(5, wish1.getPriority());

        wish1.setPrice(1029.99);
        assertEquals(1029.99, wish1.getPrice());
        assertEquals("Item Name: iPhone 10, Current Price with 0% discount: $1029.99", wish1.toString());

        wish1.setDiscount(25);
        assertEquals(772.4925000000001, wish1.getPrice());
        assertEquals("Item Name: iPhone 10, Current Price with 25% discount: $772.49", wish1.toString());

        System.out.println("Finished testWishListWithItemNameAndPriceOnly");
    }

    public void testWishListWithItemNamePriceAndDiscount()
    {
        System.out.println("\nStarting testWishListWithItemNamePriceAndDiscount");

        wish1 = new WishListItem("iPhone 11", 1999.99, 15);
        assertNotNull(wish1);
        assertEquals("iPhone 11", wish1.getItemName());
        assertEquals(1699.9914999999999, wish1.getPrice());
        assertEquals(0, wish1.getPriority());
        assertEquals("Item Name: iPhone 11, Current Price with 15% discount: $1699.99", wish1.toString());
        assertEquals(15,wish1.getDiscount());

        wish1.setPrice(1599.99);
        assertEquals(1359.9914999999999, wish1.getPrice());
        assertEquals("Item Name: iPhone 11, Current Price with 15% discount: $1359.99", wish1.toString());

        wish1.setDiscount(25);
        assertEquals(1019.9936249999998, wish1.getPrice());
        assertEquals("Item Name: iPhone 11, Current Price with 25% discount: $1019.99", wish1.toString());
        assertEquals(25,wish1.getDiscount());

        System.out.println("Finished testWishListWithItemNamePriceAndDiscount");
    }

    public void testOddWishListWithPriceOnly()
    {
        System.out.println("\nStarting testWishListWithItemNameOnly");

        wish1 = new WishListItem("iPhone 9",7999.00);
        assertNotNull(wish1);
        assertEquals("iPhone 9", wish1.getItemName());
        assertEquals(7999.00, wish1.getPrice());
        assertEquals(0, wish1.getPriority());
        assertEquals("Item Name: iPhone 9, Current Price with 0% discount: $7999.00", wish1.toString());

        wish1.setAmount(123456789012349.9999999999999);
        assertNotNull(wish1);
        assertEquals("iPhone 9", wish1.getItemName());
        assertEquals(123456789012349.9999999999999, wish1.getPrice());
        assertEquals(0, wish1.getPriority());
        assertEquals("Item Name: iPhone 9, Current Price with 0% discount: $123456789012350.00", wish1.toString());

        System.out.println("Finished testWishListWithItemNameOnly");
    }

    public void testOddWishListWithDiscountOnly()
    {
        System.out.println("\nStarting testOddWishListWithDiscountOnly");

        wish1 = new WishListItem("iPhone 11", 1999.99, 15);
        assertNotNull(wish1);
        assertEquals("iPhone 11", wish1.getItemName());
        assertEquals(1699.9914999999999, wish1.getPrice());
        assertEquals(0, wish1.getPriority());
        assertEquals("Item Name: iPhone 11, Current Price with 15% discount: $1699.99", wish1.toString());
        assertEquals(15,wish1.getDiscount());

        wish1.setDiscount(100);
        wish1.setPrice(1999.99);
        assertEquals(0.0, wish1.getPrice());
        assertEquals("Item Name: iPhone 11, Current Price with 100% discount: $0.00", wish1.toString());
        assertEquals(100,wish1.getDiscount());

        wish1.setDiscount(-100);
        wish1.setPrice(1999.99);
        assertEquals(3999.98, wish1.getPrice());
        assertEquals("Item Name: iPhone 11, Current Price with -100% discount: $3999.98", wish1.toString());
        assertEquals(-100,wish1.getDiscount());

        System.out.println("Finished testOddWishListWithDiscountOnly");
    }

    public void testEquals()
    {
        System.out.println("\nStarting testEquals");
        wish1 = new WishListItem("Samsung S9", 1499.99, 15);
        wish2 = new WishListItem("Samsung S9", 400.99);
        wish3 = new WishListItem("Google Pixel");

        assertTrue(wish1.equals(wish2));
        assertFalse(wish1.equals(wish3));

        wish2 = new WishListItem(null, 100);
        assertFalse(wish2.equals(wish1));

        System.out.println("Finished testEquals");
    }


}
