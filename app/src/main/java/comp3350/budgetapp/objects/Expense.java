package comp3350.budgetapp.objects;

import java.util.Locale;

public class Expense extends FinancialObjects {

    public Expense(String name)
    {
        super(name,0.0);
    }

    public Expense(String name, double amount)
    {
        super(name,amount);
    }

    @Override
    public String toString() {
        return String.format(Locale.getDefault(), "Expense Name: %s, Amount: $%.2f", getName(), getAmount());
    }

    @Override
    public boolean equals(Object object)
    {
        boolean result;

        result = false;

        if (object instanceof Expense && super.equals(object))
        {
            result = true;
        }
        return result;
    }
}
