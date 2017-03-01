package comp3350.budgetapp.persistence;

import java.util.ArrayList;
import java.util.List;

import comp3350.budgetapp.application.Main;
import comp3350.budgetapp.objects.WishListItem;

public class DataAccessStub
{
    private String dbName;
    private String dbType = "stub";

    private ArrayList<WishListItem> wishList;

    public DataAccessStub(String dbName)
    {
        this.dbName = dbName;
    }

    public DataAccessStub()
    {
        this(Main.dbName);
    }

    public void open(String dbName)
    {
        WishListItem wishListItem;

        wishList = new ArrayList<WishListItem>();
        wishListItem = new WishListItem("Google Pixel", 899.00);
        wishList.add(wishListItem);
        wishListItem = new WishListItem("Google Home", 130.00);
        wishList.add(wishListItem);
        wishListItem = new WishListItem("Daydream View", 99.00);
        wishList.add(wishListItem);
        wishListItem = new WishListItem("ChromeCast", 35.00);
        wishList.add(wishListItem);

        System.out.println("Opened " + dbType + " database " + dbName);
    }

    public void close()
    {
        System.out.println("Closed " + dbType + " database " + dbName);
    }

    public String getWishListItemSequential(List<WishListItem> wishListItemsResult)
    {
        wishListItemsResult.addAll(wishList);
        return null;
    }

    public ArrayList<WishListItem> getWishListItemRandom(WishListItem currentItem)
    {
        ArrayList<WishListItem> newWishList;
        int index;

        newWishList = new ArrayList<WishListItem>();
        index = wishList.indexOf(currentItem);
        if (index >= 0)
        {
            newWishList.add(wishList.get(index));
        }
        return newWishList;
    }

    public String addWishListItem(WishListItem currentItem)
    {
        wishList.add(currentItem);
        return null;
    }

    public String updateWishListItem(WishListItem currentItem)
    {
        int index;

        index = wishList.indexOf(currentItem);
        if (index >=0)
        {
            wishList.set(index, currentItem);
        }
        return null;
    }

    public String deleteWishListItem(WishListItem currentItem)
    {
        int index;

        index = wishList.indexOf(currentItem);
        if (index >=0)
        {
            wishList.remove(index);
        }
        return null;
    }
}
