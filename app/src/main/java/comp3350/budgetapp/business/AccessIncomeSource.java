package comp3350.budgetapp.business;

import java.util.ArrayList;
import java.util.List;

import comp3350.budgetapp.application.Main;
import comp3350.budgetapp.application.Services;
import comp3350.budgetapp.objects.FinancialObjects;
import comp3350.budgetapp.objects.IncomeSource;
import comp3350.budgetapp.persistence.DataAccess;

public class AccessIncomeSource
{
    private DataAccess dataAccess;
    private List<FinancialObjects> Incomes;
    private IncomeSource IncomeSource;

    private String total;
    private int currentItem;

    public AccessIncomeSource()
    {
        dataAccess = Services.getDataAccess(Main.dbName);
        Incomes = null;
        IncomeSource = null;
        currentItem = 0;
    }

    public String getIncomeSources(List<FinancialObjects> Incomes)
    {
        Incomes.clear();
        return dataAccess.getIncomeSourceSequential(Incomes);
    }

    public IncomeSource getSequential()
    {
        String result = null;
        if (Incomes == null)
        {
            Incomes = new ArrayList<FinancialObjects>();
            result = dataAccess.getIncomeSourceSequential(Incomes);
            total = Calculate.calculateTotal(Incomes);
            currentItem = 0;
        }
        if (currentItem < Incomes.size())
        {
            IncomeSource = (IncomeSource) Incomes.get(currentItem);
            currentItem++;
        }
        else
        {
            Incomes = null;
            IncomeSource = null;

            currentItem = 0;
        }
        return IncomeSource;
    }

    public IncomeSource getRandom(String itemName)
    {
        Incomes = dataAccess.getIncomeSourceRandom(new IncomeSource(itemName));
        currentItem = 0;
        if (currentItem < Incomes.size())
        {
            IncomeSource = (IncomeSource) Incomes.get(currentItem);
            total = Calculate.calculateTotal(Incomes);
            currentItem++;
        }
        else
        {
            Incomes = null;
            IncomeSource = null;
            currentItem = 0;
        }
        return IncomeSource;
    }

    public String addIncomeSource(IncomeSource currentIncomeSource)
    {
        return dataAccess.addIncomeSource(currentIncomeSource);
    }

    public String updateIncomeSource(IncomeSource currentItem)
    {
        return dataAccess.updateIncomeSource(currentItem);
    }

    public String deleteIncomeSource(IncomeSource currentItem)
    {
        return dataAccess.deleteIncomeSource(currentItem);
    }

    public String getTotal()
    {
        return Calculate.calculateTotal(Incomes);
    }

}
