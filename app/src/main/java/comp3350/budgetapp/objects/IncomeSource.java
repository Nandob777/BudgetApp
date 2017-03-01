package comp3350.budgetapp.objects;

public class IncomeSource
{
    private String sourceName = "";
    private int amount = 0;
    private String type = "";

    public IncomeSource (String name, int amount)
    {
        this.sourceName = name;
        this.amount = amount;
        this.type = "Misc";
    }

    public IncomeSource (String name, int amount, String type)
    {
        this.sourceName = name;
        this.amount = amount;
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public String getSourceName() {
        return sourceName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toString()
    {
        return "Income Source Name: " +sourceName +"With Amount " +amount;
    }
}
