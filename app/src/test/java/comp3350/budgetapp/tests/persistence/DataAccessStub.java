package comp3350.budgetapp.tests.persistence;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

//import comp3350.budgetapp.application.Main;
import comp3350.budgetapp.objects.FinancialObjects;
import comp3350.budgetapp.tests.TesterMain;
import comp3350.budgetapp.objects.Expense;
import comp3350.budgetapp.objects.IncomeSource;
import comp3350.budgetapp.objects.WishListItem;
import comp3350.budgetapp.persistence.DataAccess;

public class DataAccessStub implements DataAccess
{
    private String dbName;
    private String dbType = "stub";

    private ArrayList<FinancialObjects> wishList;
    private ArrayList<FinancialObjects> incomes;
    private ArrayList<FinancialObjects> expenses;

    public DataAccessStub(String dbName)
    {
        this.dbName = dbName;
    }

    public DataAccessStub()
    {
        this(TesterMain.dbName);
    }

    public void open(String dbName)
    {
        WishListItem wishListItem;
        Expense expense;
        IncomeSource incomeSource;

        wishList = new ArrayList<FinancialObjects>();
        expenses = new ArrayList<FinancialObjects>();
        incomes = new ArrayList<FinancialObjects>();

        wishListItem = new WishListItem("Google Pixel", 899.00);
        wishList.add(wishListItem);
        wishListItem = new WishListItem("Google Home", 130.00);
        wishList.add(wishListItem);
        wishListItem = new WishListItem("Daydream View", 99.00);
        wishList.add(wishListItem);
        wishListItem = new WishListItem("ChromeCast", 35.00);
        wishList.add(wishListItem);

        expense = new Expense("Rent",500.00);
        expenses.add(expense);
        expense = new Expense("Internet",120.00);
        expenses.add(expense);

        incomeSource = new IncomeSource("Starbucks",800.00,"Monthly");
        incomes.add(incomeSource);
        incomeSource = new IncomeSource("Bartending",700.00,"Monthly");
        incomes.add(incomeSource);



        System.out.println("Opened " + dbType + " database " + dbName);
    }

    public void close()
    {
        System.out.println("Closed " + dbType + " database " + dbName);
    }

    public String getWishListItemSequential(List<FinancialObjects> wishListItemsResult)
    {
        wishListItemsResult.clear();
        wishListItemsResult.addAll(wishList);
        return null;
    }

    public ArrayList<FinancialObjects> getWishListItemRandom(WishListItem currentItem)
    {
        ArrayList<FinancialObjects> newWishList;
        int index;

        newWishList = new ArrayList<FinancialObjects>();
        index = wishList.indexOf(currentItem);
        if (index >= 0)
        {
            newWishList.add(wishList.get(index));
        }
        return newWishList;
    }

    public String addWishListItem(WishListItem currentItem)
    {
        wishList.add(currentItem);
        return null;
    }

    public String updateWishListItem(WishListItem currentItem)
    {
        int index;

        index = wishList.indexOf(currentItem);
        if (index >=0)
        {
            wishList.set(index, currentItem);
        }
        return null;
    }

    public String deleteWishListItem(WishListItem currentItem)
    {
        int index;

        index = wishList.indexOf(currentItem);
        if (index >=0)
        {
            wishList.remove(index);
        }
        return null;
    }
    public String getIncomeSourceSequential(List<FinancialObjects> incomeItemsResult)
    {
        incomeItemsResult.clear();
        incomeItemsResult.addAll(incomes);
        return null;
    }

    public ArrayList<FinancialObjects> getIncomeSourceRandom(IncomeSource currentItem)
    {
        ArrayList<FinancialObjects> newIncomes;

        int index = 0;

        newIncomes = new ArrayList<FinancialObjects>();

        for(index=0;index<incomes.size();index++)
        {
            if(incomes.get(index).getName().equals(currentItem.getName()))
            {
                newIncomes.add(incomes.get(index));
            }

        }
        return newIncomes;
    }

    public String addIncomeSource(IncomeSource currentItem)
    {
        incomes.add(currentItem);
        return null;
    }

    public String updateIncomeSource(IncomeSource currentItem)
    {
        int index = 0;

        for(index=0;index<incomes.size();index++)
        {
            if(incomes.get(index).getName().equals(currentItem.getName()))
            {
                incomes.set(index, currentItem);
            }

        }

        return null;
    }

    public String deleteIncomeSource(IncomeSource currentItem)
    {
        int index = 0;

        for(index=0;index<incomes.size();index++)
        {
            if(incomes.get(index).getName().equals(currentItem.getName()))
            {
                //break;
                incomes.remove(index);
            }

        }

        return null;
    }

    public String getExpenseSequential(List<FinancialObjects> expenseItemsResult)
    {
        expenseItemsResult.clear();
        expenseItemsResult.addAll(expenses);
        return null;
    }

    public ArrayList<FinancialObjects> getExpenseRandom(Expense currentItem)
    {
        ArrayList<FinancialObjects> newExpenses;
        int index = 0;

        newExpenses = new ArrayList<FinancialObjects>();
        index = expenses.indexOf(currentItem);

        for(index=0;index<expenses.size();index++)
        {
            if(expenses.get(index).getName().equals(currentItem.getName()))
            {
                newExpenses.add(expenses.get(index));
            }

        }

        return newExpenses;
    }

    public String addExpense(Expense currentItem)
    {
        expenses.add(currentItem);
        return null;
    }

    public String updateExpense(Expense currentItem)
    {
        int index = 0;

        for(index=0;index<expenses.size();index++)
        {
            if(expenses.get(index).getName().equals(currentItem.getName()))
            {
                break;
            }

        }

        if (index >=0 && index < expenses.size())
        {
            expenses.set(index, currentItem);
        }
        return null;
    }

    public String deleteExpense(Expense currentItem)
    {
        int index = 0;

        for(index=0;index<expenses.size();index++)
        {
            if(expenses.get(index).getName().equals(currentItem.getName()))
            {
                break;
            }

        }

        if (index >=0 && index<expenses.size())
        {
            expenses.remove(index);
        }
        return null;

    }

}
