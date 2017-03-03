package comp3350.budgetapp.objects;

/**
 * Created by mardelmaduro on 2017-03-03.
 */

public class FinancialObjects {
    private String name = "";
    private double amount = 0.0;

    public FinancialObjects(String name, int amount)
    {
        this.name = name;
        this.amount = amount;
    }

    public double getAmount()
    {
        return this.amount;
    }

    public String getName()
    {
        return this.name;
    }


}

