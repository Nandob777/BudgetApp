package comp3350.budgetapp.objects;

public class WishListItem extends FinancialObjects
{
    private int priority = 0;
    private int discount = 0;


    public WishListItem(String itemName)
    {
        this.name = itemName;
        this.amount = 0.0;
        this.priority = 0;
        this.discount = 0;
    }

    public WishListItem(String itemName , double price)
    {
        this.name = itemName;
        this.amount = price;
        this.priority = 0;
        this.discount = 0;

    }

    public WishListItem(String itemName , double price ,int discount)
    {
        this.name = itemName;

        double discountAmount = (discount*0.01)*this.amount;
        this.amount = price - discountAmount;
        this.priority = 0;
        this.discount = discount;
    }

    public String getItemName()
    {
        return super.getName();
    }
    public double getPrice() {

        return super.getAmount();
    }

    public void setPrice(double price)
    {
        this.amount = price;
    }

    public void setDiscount(int discount) {
        this.discount = discount;

        double discountAmount = (discount*0.01)*this.amount;
        this.amount = this.amount - discountAmount;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String toString()
    {
        return "Item Name: " +name +", Price: " +amount +", Current Discount: " +discount+" % .";
    }

    public boolean equals(Object object)
    {
        return super.equals(object);
    }
}