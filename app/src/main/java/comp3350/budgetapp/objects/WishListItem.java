package comp3350.budgetapp.objects;

public class WishListItem
{
    private String itemName = "";
    private double price = 0.0;
    private int priority = 0;
    private int discount = 0;

    public WishListItem(String itemName , double price)
    {
        this.itemName = itemName;
        this.price = price;
        this.priority = 0;
        this.discount = 0;

    }

    public WishListItem(String itemName , double price ,int priority)
    {
        this.itemName = itemName;
        this.price = price;
        this.priority = priority;
        this.discount = 0;
    }

    public String getItemName() {
        return itemName;
    }

    public double getPrice() {

        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDiscount(int discount) {
        this.discount = discount;

        double discountAmount = (discount*0.01)*this.price;
        this.price = this.price - discountAmount;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String toString()
    {
        return "Item Name: " +itemName +", Price: " +price +", Current Discount: " +discount+" % .";
    }

    public boolean equals(Object object)
    {
        boolean result;
        WishListItem item;

        result = false;

        if (object instanceof WishListItem)
        {
            item = (WishListItem) object;
            if (((item.itemName == null) && (itemName == null)) || (item.itemName.equals(itemName)))
            {
                result = true;
            }
        }
        return result;
    }
}