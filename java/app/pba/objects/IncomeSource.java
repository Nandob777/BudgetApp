package app.pba.objects;

/* Needed for Iteration 2
 *
 * Added but not complete
 *  - needs specific types and frequencies
 *
 *  NOTE: similarities to Expense class
 *  	--> Difference is negative/positive affect on savings
 */

public class IncomeSource extends ListItem
{
	private String type;
	private String frequency;

	public IncomeSource(String itemName, double amount, String type, String frequency)
	{
		super(itemName,amount);
		this.type = type;
		this.frequency = frequency;
	}

	public String getType()
	{
		return this.type;
	}

	public String getFrequency()
	{
		return this.frequency;
	}

	@Override
	public String toString()
	{
		return super.toString() + ", Type: "+this.type+", Frequency: "+ this.frequency;
	}

	@Override
	public boolean equals(Object object)
	{
		return super.equals(object);
	}
}
