package comp3350.budgetapp.business;

import java.util.List;

import comp3350.budgetapp.application.Main;
import comp3350.budgetapp.application.Services;
import comp3350.budgetapp.objects.Expense;
import comp3350.budgetapp.objects.Expense;
import comp3350.budgetapp.persistence.DataAccessStub;
import comp3350.budgetapp.business.Calculate;

public class AccessExpenses
{
    private DataAccessStub dataAccess;
    private List<Expense> expenses;
    private Expense expense;
    private Calculate cal;

    private String total;
    private int currentItem;

    public AccessExpenses()
    {
        dataAccess = Services.getDataAccess(Main.dbName);
        expenses = null;
        expense = null;
        currentItem = 0;
        cal = new Calculate();
    }

    public String getExpenses(List<Expense> expenses)
    {
        expenses.clear();
        return dataAccess.getExpenseSequential(expenses);
    }

    public Expense getSequential()
    {
        String result = null;
        if (expenses == null)
        {
            result = dataAccess.getExpenseSequential(expenses);
            total = Calculate.expenseTotal(expenses);
            currentItem = 0;
        }
        if (currentItem < expenses.size())
        {
            expense = expenses.get(currentItem);
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
            expense = expenses.get(currentItem);
            total = Calculate.expenseTotal(expenses);
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

    public String updateExpense(Expense currentItem)
    {
        return dataAccess.updateExpense(currentItem);
    }

    public String deleteExpense(Expense currentItem)
    {
        return dataAccess.deleteExpense(currentItem);
    }

    public String getTotal()
    {
        //System.out.println("TOTAL: " + Calculate.expensesTotal(expenses));
        return cal.expenseTotal(expenses);
    }

}
