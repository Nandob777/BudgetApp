package comp3350.budgetapp.business;

import java.util.List;
import comp3350.budgetapp.objects.WishListItem;

public class Calculate {

    public static String wishlistTotal(List<WishListItem> elements)
    {
        String wishlistTotal;
        String invalidTotal;
        WishListItem element;
        boolean found;
        double price;
        double total;
        int count;
        int elementCount;
        int missingPrices;
        int validPrices;

        wishlistTotal = "";
        invalidTotal = "?";
        total = 0.0;
        price = 0.0;
        missingPrices = 0;
        validPrices = 0;

        if( (elements != null) && (elements.size() > 0) )
        {
            for(elementCount = 0; elementCount < elements.size(); elementCount++)
            {
                if( !(elements.get(elementCount) instanceof WishListItem))
                {
                    elementCount = elements.size() + 1;
                    wishlistTotal = invalidTotal;
                }
                else
                {
                    element = (WishListItem) elements.get(elementCount);
                    price = ((WishListItem) element).getPrice();
                    found = false;
                    if (price == 0.0)
                    {
                        missingPrices++;
                    }
                    else
                    {
                        total += price;
                        validPrices++;
                    }
                }
            }

            if( (missingPrices+validPrices) == elements.size() && validPrices > 0)
            {
                wishlistTotal = String.format("%.2f", (total));
            }
            else if( missingPrices != elements.size())
            {
                wishlistTotal = invalidTotal;
            }
        }

        return wishlistTotal;
    }
}
