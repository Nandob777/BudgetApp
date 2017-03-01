package comp3350.budgetapp.business;

import java.util.List;

import comp3350.budgetapp.application.Main;
import comp3350.budgetapp.application.Services;
import comp3350.budgetapp.objects.WishListItem;
import comp3350.budgetapp.persistence.DataAccessStub;
import comp3350.budgetapp.business.Calculate;

public class AccessWishListItems
{
    private DataAccessStub dataAccess;
    private List<WishListItem> wishList;
    private WishListItem wishListItem;

    private String total;
    private int currentItem;

    public AccessWishListItems()
    {
        dataAccess = Services.getDataAccess(Main.dbName);
        wishList = null;
        wishListItem = null;
        currentItem = 0;
    }

    public String getWishListItems(List<WishListItem> wishList)
    {
        wishList.clear();
        return dataAccess.getWishListItemSequential(wishList);
    }

    public WishListItem getSequential()
    {
        String result = null;
        if (wishList == null)
        {
            result = dataAccess.getWishListItemSequential(wishList);
            total = Calculate.wishlistTotal(wishList);
            currentItem = 0;
        }
        if (currentItem < wishList.size())
        {
            wishListItem = wishList.get(currentItem);
            currentItem++;
        }
        else
        {
            wishList = null;
            wishListItem = null;
            currentItem = 0;
        }
        return wishListItem;
    }

    public WishListItem getRandom(String itemName)
    {
        wishList = dataAccess.getWishListItemRandom(new WishListItem(itemName));
        currentItem = 0;
        if (currentItem < wishList.size())
        {
            wishListItem = wishList.get(currentItem);
            total = Calculate.wishlistTotal(wishList);
            currentItem++;
        }
        else
        {
            wishList = null;
            wishListItem = null;
            currentItem = 0;
        }
        return wishListItem;
    }

    public String addWishListItem(WishListItem currentWishListItem)
    {
        return dataAccess.addWishListItem(currentWishListItem);
    }

	public String updateWishListItem(WishListItem currentItem)
	{
		return dataAccess.updateWishListItem(currentItem);
	}

    public String deleteWishListItem(WishListItem currentItem)
    {
        return dataAccess.deleteWishListItem(currentItem);
    }

    public String getTotal()
    {
        System.out.println("TOTAL: " + Calculate.wishlistTotal(wishList
        ));
        return Calculate.wishlistTotal(wishList);
    }
}
