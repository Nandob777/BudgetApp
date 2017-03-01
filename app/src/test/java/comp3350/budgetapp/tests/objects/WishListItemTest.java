package comp3350.budgetapp.tests.objects;

import junit.framework.TestCase;

import comp3350.budgetapp.objects.WishListItem;

public class WishListItemTest extends TestCase
{
    public static WishListItem nullItem = null;
    public static final double delta = 0.001;

    private WishListItem createWishListItem(String name, double price)
    {
        WishListItem item = new WishListItem(name, price);
        return item;
    }

    public void testSingleNullItem()
    {
        System.out.println("\nStarting testSingleNullItem");
        assertNull(nullItem);
        System.out.println("Finished testSingleNullItem");
    }

    public void testSingleInitializedItem()
    {
        System.out.println("\nStarting testSingleInitializedItem");

        WishListItem newItem;

        newItem = createWishListItem("iPhone 7+", 900.00);
        assertNotNull(newItem);
        assertTrue("iPhone 7+".equals(newItem.getItemName()));
        assertEquals(900.00,newItem.getPrice(),delta);

        assertFalse("".equals(newItem.getItemName()));
        assertFalse(0 == newItem.getPrice());

        System.out.println("Finished testSingleInitializedItem");
    }

    public void testItemsEqual()
    {
        WishListItem item1 = null;
        WishListItem item2;
        WishListItem item3;

        String itemName = "iPhone 7+";
        double itemPrice = 900.00;

        item1 = createWishListItem(itemName, itemPrice);

        System.out.println("\nStarting testItemsEqual: null v. initialized");
        assertFalse(item1.equals(nullItem));
        System.out.println("Finished testItemsEqual: null v. initialized");

        System.out.println("\nStarting testItemsEqual: initialized v. initialized (Same item)");
        item2 = createWishListItem(itemName, itemPrice);
        assertTrue(item1.equals(item2));
        assertTrue( (item1.getItemName()).equals(item2.getItemName()) );
        assertEquals( (item1.getPrice()), (item2.getPrice()), delta);
        System.out.println("Finished testItemsEqual: initialized v. initialized (Same item)");

        System.out.println("\nStarting testItemsEqual: initialized v. initialized (Diff name)");
        item2 = createWishListItem("iPhone 6s", itemPrice);
        assertFalse( item1.equals(item2) );
        assertFalse( (item1.getItemName()).equals(item2.getItemName()) );
        assertTrue( (item1.getPrice()) == (item2.getPrice()) );
        System.out.println("Finished testItemsEqual: initialized v. initialized (Diff name)");

        System.out.println("\nStarting testItemsEqual: initialized v. initialized (Diff price)");
        item2 = createWishListItem(itemName, 899.00);
        assertTrue( item1.equals(item2) );
        assertTrue( (item1.getItemName()).equals(item2.getItemName()) );
        assertFalse( (item1.getPrice()) == (item2.getPrice()) );
        System.out.println("Finished testItemsEqual: initialized v. initialized (Diff price)");

        System.out.println("\nStarting testItemsEqual: initialized v. initialized (Diff item)");
        item2 = createWishListItem("Headphones", 10.00);
        assertFalse(item1.equals(item2));
        assertFalse( (item1.getItemName()).equals(item2.getItemName()) );
        assertFalse( (item1.getPrice()) == (item2.getPrice()) );
        System.out.println("Finished testItemsEqual: initialized v. initialized (Diff item)");
    }
}
