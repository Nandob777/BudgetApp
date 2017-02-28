
package comp3350.budgetapp.business;


import comp3350.budgetapp.persistence.DBMSstub.DBManSys;




 // Created by mardelmaduro on 2017-02-27.


import java.util.List;

import comp3350.budgetapp.application.Main;
import comp3350.budgetapp.application.Services;
import comp3350.budgetapp.objects.WishListItem;
import comp3350.budgetapp.persistence.DBMSstub.DBManSys;

public class AccessWishListItems
{
    private DBManSys dataAccess;
    private List<WishListItem> wishlist;
    private WishListItem wishlistItem;
    private int currentItem;

    public AccessWishListItems()
    {
        dataAccess = (DBManSys) Services.getDataAccess(Main.dbName);
        wishlist = null;
        wishlistItem = null;
        currentItem = 0;
    }

    public String getWishlistItems(List<WishListItem> wishlist)
    {
        wishlist.clear();
        return dataAccess.toString();
    }



    public String insertWishListItem(WishListItem currentItem)
    {
        return dataAccess.addWishListItem(currentItem);
    }

	public String updateWishListItem(WishListItem currentItem)
	{
		return dataAccess.updateWishListItem(currentItem);
	}

    public String deleteWishListItem(WishListItem currentItem)
    {
        return dataAccess.deleteWishListItem(currentItem);
    }

}
