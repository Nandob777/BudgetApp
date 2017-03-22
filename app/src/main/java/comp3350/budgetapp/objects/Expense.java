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

    public double getAmount() {
        return super.getAmount();
    }

    public void setAmount(double amount) {
        super.setAmount(amount);
    }

    public String getName() {
        return super.getName();
    }

    public void setName(String name) {
        super.setName(name);
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
