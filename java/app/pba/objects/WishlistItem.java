package app.pba.objects;

public class WishlistItem extends ListItem
{
	private final String PR_H = "H";
	private final String PR_M = "M";
	private final String PR_L = "L";
	private String priority;

	public WishlistItem(String itemName, double amount, String priority)
	{
		super(itemName,amount);
		this.priority = priority;
	}

	public WishlistItem(String itemName, double amount)
	{
		super(itemName,amount);
	}

	public String getPriority()
	{
		return this.priority;
	}

	@Override
	public String toString()
	{
		return "Wishlist Item: " +super.getItemName() +", Amount: $"+super.getItemAmount()
				+", Priority: "+priority;
	}

	@Override
	public boolean equals(Object object)
	{
		boolean result;
		WishlistItem wlItem;
		
		result = false;

		if (object instanceof WishlistItem)
		{
			wlItem = (WishlistItem) object;

			if( ((wlItem.priority == null) && (this.priority == null)) ||
					( (wlItem.priority != null) && wlItem.priority.equals(this.getItemName())) &&
					(super.equals(object)))
			{
				result = true;
			}
		}
		return result;
	}
}
