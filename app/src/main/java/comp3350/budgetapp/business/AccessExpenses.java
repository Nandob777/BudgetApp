package comp3350.budgetapp.business;

import java.util.ArrayList;
import java.util.List;

import comp3350.budgetapp.application.Main;
import comp3350.budgetapp.application.Services;
import comp3350.budgetapp.objects.Expense;
import comp3350.budgetapp.objects.FinancialObjects;
import comp3350.budgetapp.persistence.DataAccess;

public class AccessExpenses
{
    private DataAccess dataAccess;
    private List<FinancialObjects> expenses;
    private Expense expense;

    private String total;
    private int currentItem;

    public AccessExpenses()
    {
        dataAccess = Services.getDataAccess(Main.dbName);
        expenses = null;
        expense = null;
        currentItem = 0;
    }

    public String getExpenses(List<FinancialObjects> expenses)
    {
        expenses.clear();
        return dataAccess.getExpenseSequential(expenses);
    }

    public Expense getSequential()
    {
        String result = null;
        if (expenses == null)
        {
            expenses = new ArrayList<FinancialObjects>();
            result = dataAccess.getExpenseSequential(expenses);
            total = Calculate.calculateTotal(expenses);
            currentItem = 0;
        }
        if (currentItem < expenses.size())
        {
            expense = (Expense) expenses.get(currentItem);
            currentItem++;
        }
        else
        {
            expenses = null;
            expense = null;
            currentItem = 0;
        }
        return expense;
    }

    public Expense getRandom(String itemName)
    {
        expenses = dataAccess.getExpenseRandom(new Expense(itemName));
        currentItem = 0;
        if (currentItem < expenses.size())
        {
            expense = (Expense) expenses.get(currentItem);
            total = Calculate.calculateTotal(expenses);
            currentItem++;
        }
        else
        {
            expenses = null;
            expense = null;
            currentItem = 0;
        }
        return expense;
    }

    public String addExpense(Expense currentExpense)
    {
        return dataAccess.addExpense(currentExpense);
    }

    public String updateExpense(Expense currentExpense)
    {
        return dataAccess.updateExpense(currentExpense);
    }

    public String deleteExpense(Expense currentExpense)
    {
        return dataAccess.deleteExpense(currentExpense);
    }

    public String getTotal()
    {
        return Calculate.calculateTotal(expenses);
    }

}
