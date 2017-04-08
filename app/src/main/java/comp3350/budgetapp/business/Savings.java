package comp3350.budgetapp.business;

import java.util.ArrayList;
import java.util.Locale;

import comp3350.budgetapp.objects.FinancialObjects;

public class Savings
{

    private Calculate calculate;
    private String expenseTotal;
    private String incomeTotal;
    private double savingsTotal = 0.0;

    private AccessExpenses accessExpenses;
    private AccessIncomeSource accessIncomeSource;
    private ArrayList<FinancialObjects> itemList;

    public Savings()
    {
        String result;

        accessExpenses = new AccessExpenses();
        accessIncomeSource = new AccessIncomeSource();

        calculate = new Calculate();
        itemList = new ArrayList<FinancialObjects>();

        result = accessExpenses.getExpenses(itemList);
        expenseTotal = calculate.calculateTotal(itemList);

        result = accessIncomeSource.getIncomeSources(itemList);
        incomeTotal = calculate.calculateTotal(itemList);

        savingsTotal = Double.parseDouble(incomeTotal) - Double.parseDouble(expenseTotal);
    }

    public double getSavingsTotal()
    {
        return savingsTotal;
    }

    public String toString()
    {
        return String.format(Locale.getDefault(), "$ %.2f", getSavingsTotal());
    }

}
