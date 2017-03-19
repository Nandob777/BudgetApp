package comp3350.budgetapp.objects;

import java.util.Locale;

public class WishListItem
{
    private String itemName;
    private double price;
    private int priority;
    private int discount;

    public WishListItem(String itemName)
    {
        this.itemName = itemName;
        this.price = 0.0;
        this.priority = 0;
        this.discount = 0;
    }

    public WishListItem(String itemName , double price)
    {
        this.itemName = itemName;
        this.price = price;
        this.priority = 0;
        this.discount = 0;

    }

    public WishListItem(String itemName , double price ,int discount)
    {
        this.itemName = itemName;
        this.price = price * (1-(discount*0.01));
        this.priority = 0;
        this.discount = discount;
    }

    public String getItemName()
    {
        return itemName;
    }

    public double getPrice()
    {

        return price;
    }

    public void setPrice(double price)
    {
        this.price = price * (1-(discount*0.01));
    }

    public void setDiscount(int discount)
    {
        this.discount = discount;
        this.price = this.price * (1-(discount*0.01));
    }

    public int getPriority()
    {
        return priority;
    }

    public void setPriority(int priority)
    {
        this.priority = priority;
    }

    public String toString()
    {
        return String.format(Locale.getDefault(), "Item Name: %s, Current Price with %d%% discount: $%.2f", itemName, discount, price);
    }

    public boolean equals(Object object)
    {
        boolean result;
        WishListItem item;

        result = false;

        if (object instanceof WishListItem)
        {
            item = (WishListItem) object;
            if( ((item.itemName == null) && (itemName == null)) || (item.itemName.equals(itemName)) )
            {
                result = true;
            }
        }
        return result;
    }
}