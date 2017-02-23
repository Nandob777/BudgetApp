package app.pba.objects;

public class ListItem
{
	private String itemName;
	private double amount;

	public ListItem(String itemName, double amount)
	{
		this.itemName = itemName;
		this.amount = amount;
	}

	public ListItem(String itemName)
	{
		this.itemName = itemName;
	}

	public String getItemName()
	{
		return (this.itemName);
	}

	public double getItemAmount()
	{
		return (this.amount);
	}

	public String toString()
	{
		return "Item: " +this.itemName +", Amount: $" +this.amount;
	}

	public boolean equals(Object object)
	{
		boolean result;
		ListItem item;

		result = false;

		if (object instanceof ListItem)
		{
			item = (ListItem) object;
			if (((item.itemName == null) && (itemName == null)) || (item.itemName.equals(this.itemName)))
			{
				result = true;
			}
		}
		return result;
	}
}