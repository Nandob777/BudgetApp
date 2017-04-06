package comp3350.budgetapp.objects;

import java.util.Locale;

public class IncomeSource extends FinancialObjects
{
    private String type = "";
    private int frequency;
    private final String DEFAULT_TYPE = "misc";
    private final int DEFAULT_FREQ = 1;

    private final String TYPE_MONTHLY = "monthly";
    private final String TYPE_BWEEKLY = "bi-weekly";
    private final String TYPE_WEEKLY = "weekly";

    private final int FREQ_MONTHLY = 1;
    private final int FREQ_BWEEKLY = 2;
    private final int FREQ_WEEKLY = 4;

    public IncomeSource (String name)
    {
        super(name,0.0);
        setType(DEFAULT_TYPE);
    }

    public IncomeSource (String name, double amount)
    {
        super(name,amount);
        setType(DEFAULT_TYPE);
    }

    public IncomeSource (String name, double amount, String type)
    {
        super(name,amount);
        setType(type);
    }

    public String getType() {
        return type;
    }

    public int getFrequency()
    {
        return frequency;
    }

    public void setType(String type) {
        /*type is the string shown in the app, frequency is how income amount is calculated
         * frequency should not be allowed to change independently --> can only change when
         * type is changed */

        this.type = type;
        if(type.equalsIgnoreCase(TYPE_MONTHLY)){this.frequency = FREQ_MONTHLY;}
        else if(type.equalsIgnoreCase(TYPE_BWEEKLY)){this.frequency = FREQ_BWEEKLY;}
        else if(type.equalsIgnoreCase(TYPE_WEEKLY)){this.frequency = FREQ_WEEKLY;}
        else{ this.frequency = DEFAULT_FREQ;}
    }

    @Override
    public String toString()
    {
        return String.format(Locale.getDefault(), "Income Source Name: %s, Amount: $%.2f, Type: %s, Frequency: %d",
                getName(), getAmount(), type, frequency);
    }

    @Override
    public boolean equals(Object object)
    {
        boolean result;

        result = false;

        if (object instanceof IncomeSource && super.equals(object))
        {
            result = true;
        }
        return result;
    }

}
