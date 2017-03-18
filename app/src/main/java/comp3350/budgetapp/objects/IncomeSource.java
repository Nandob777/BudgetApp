package comp3350.budgetapp.objects;

public class IncomeSource extends FinancialObjects
{
    private String type = "";
    private int frequency;


    public IncomeSource (String name)
    {
        this.name = name;
        this.amount = 0.00;
        this.type = "Misc";
        this.frequency = 1;
    }

    public IncomeSource (String name, double amount)
    {
        this.name = name;
        this.amount = amount;
        this.type = "Misc";
        this.frequency = 1;
    }

    public IncomeSource (String name, int amount, String type)
    {
        this.name = name;
        this.amount = amount;
        this.type = type;
    }

    public double getAmount() {
        return super.getAmount();
    }

    public String getSourceName() {
        return super.getName();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
        if(type.equals("monthly")){this.frequency = 1;}
        if(type.equals("bi-weekly")){this.frequency = 2;}
        if(type.equals("weekly")){this.frequency = 4;}
    }

    public String toString()
    {
        return "Income Source Name: " +this.name +"With Amount " +amount;
    }

    public void setFrequency(int frequency)
    {
        this.frequency = frequency;
    }
}
