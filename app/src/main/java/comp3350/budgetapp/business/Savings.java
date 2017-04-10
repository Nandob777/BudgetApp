package comp3350.budgetapp.business;

import java.util.ArrayList;
import java.util.Locale;

import comp3350.budgetapp.objects.FinancialObjects;

public class Savings
{

    private Calculate calculate;
    private double savingsTotal = 0.0;
    private double incomeTotal = 0.0;
    private double expenseTotal = 0.0;
    private String total;

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
        total = calculate.calculateTotal(itemList);
        expenseTotal = Double.parseDouble(total);

        result = accessIncomeSource.getIncomeSources(itemList);
        total = calculate.calculateTotal(itemList);
        incomeTotal = Double.parseDouble(total);

        for(int i =0; i < itemList.size();i++){
            if (itemList.get(i).getType().equalsIgnoreCase("Misc"))
                incomeTotal += itemList.get(i).getAmount();
        }

        savingsTotal = incomeTotal - expenseTotal;
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
