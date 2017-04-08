package comp3350.budgetapp.objects;

import java.util.Locale;

public class WishListItem extends FinancialObjects
{
    public WishListItem(String itemName)
    {
        super(itemName,0.0);
    }

    public WishListItem(String itemName , double price)
    {
        super(itemName,price);
    }

    @Override
    public String toString()
    {
        return String.format(Locale.getDefault(), "Item Name: %s, Current Price: $%.2f", getName(), getAmount());
    }

    @Override
    public boolean equals(Object object)
    {
        boolean result;

        result = false;

        if (object instanceof WishListItem && super.equals(object)) {
            result = true;
        }
        return result;
    }
}