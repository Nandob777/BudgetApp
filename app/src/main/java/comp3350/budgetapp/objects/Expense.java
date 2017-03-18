package comp3350.budgetapp.objects;

/**
 * Created by mardelmaduro on 2017-03-17.
 */

public class Expense extends FinancialObjects {
    private int frequency;
    private int priority;

    public Expense(String name)
    {
        this.name = name;
        this.amount = 0.00;
        this.frequency = 1;
    }

    public Expense(String name, double amount)
    {
        this.name = name;
        this.amount = amount;
        this.frequency = 1;
    }

    public Expense(String name, double amount , int frequency)
    {
        this.name = name;
        this.amount = amount;
        this.frequency = frequency;
    }

    @Override
    public double getAmount() {
        return super.getAmount();
    }

    @Override
    public void setAmount(double amount) {
        super.setAmount(amount);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
