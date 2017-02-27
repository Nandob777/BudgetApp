
package comp3350.budgetapp.business;
public class AccessWishListItems
{

}

/*
package comp3350.budgetapp.business;



 * Created by mardelmaduro on 2017-02-27.


import java.util.List;

import comp3350.srsys.application.Main;
import comp3350.srsys.application.Services;
import comp3350.srsys.objects.Course;
import comp3350.srsys.objects.WishListItem;
import comp3350.srsys.persistence.DataAccessStub;

public class AccessWishListItems
{
    private DataAccessStub dataAccess;
    private List<WishListItem> wishlist;
    private WishListItem wishlistItem;
    private int currentItem;
/*
    public AccessWishListItems()
    {
        dataAccess = (DataAccessStub) Services.getDataAccess(Main.dbName);
        wishlist = null;
        wishlistItem = null;
        currentItem = 0;
    }

    public String getWishlistItems(List<WishListItem> wishlist)
    {
        wishlist.clear();
        return dataAccess.getWishlistItemsSequential(wishlist);
    }

    public WishListItem getSequential()
    {
        String result = null;
        if (wishlist == null)
        {
            result = dataAccess.getWishlistItemSequential(wishlist);
            currentItem = 0;
        }
        if (currentItem < wishlist.size())
        {
            wishlistItem = (WishListItem) wishlist.get(currentItem);
            currentItem++;
        }
        else
        {
            wishlist = null;
            wishlistItem = null;
            currentItem = 0;
        }
        return wishlistItem;
    }

    public WishListItem getRandom(String itemName)
    {
        wishlist = dataAccess.getWishListItemRandom(new WishListItem(itemName));
        currentItem = 0;
        if (currentItem < wishlist.size())
        {
            wishlistItem = (WishListItem) wishlist.get(currentItem);
            currentItem++;
        }
        else
        {
            wishlist = null;
            wishlistItem = null;
            currentItem = 0;
        }
        return wishlistItem;
    }

    public String insertWishListItem(WishListItem currentItem)
    {
        return dataAccess.insertWishlistItem(currentItem);
    }

	public String updateCourse(Course currentCourse)
	{
		return dataAccess.updateCourse(currentCourse);
	}

    public String deleteWishListItem(WishListItem currentItem)
    {
        return dataAccess.deleteWishlistItem(currentItem);
    }

}
*/