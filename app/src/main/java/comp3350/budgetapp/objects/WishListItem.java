package comp3350.budgetapp.objects;

import java.util.Locale;

public class WishListItem extends FinancialObjects
{
    private int priority;
    private int discount;

    public WishListItem(String itemName)
    {
        super(itemName,0.0);
        this.priority = 0;
        this.discount = 0;
    }

    public WishListItem(String itemName , double price)
    {
        super(itemName,price);
        this.priority = 0;
        this.discount = 0;
    }

    public WishListItem(String itemName , double price ,int discount)
    {
        super(itemName,price);
        this.priority = 0;
        this.discount = discount;
        setAmount(super.getAmount());
    }

    public void setAmount(double price)
    {
        super.setAmount( price * (1-((this.discount)*0.01)) );
    }

    public void setDiscount(int discount)
    {
        this.discount = discount;
        setAmount(super.getAmount());
    }

    public int getDiscount() {
        return discount;
    }

    public int getPriority()
    {
        return priority;
    }

    public void setPriority(int priority)
    {
        this.priority = priority;
    }

    @Override
    public String toString()
    {
        return String.format(Locale.getDefault(), "Item Name: %s, Current Price with %d%% discount: $%.2f", getName(), discount, getAmount());
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