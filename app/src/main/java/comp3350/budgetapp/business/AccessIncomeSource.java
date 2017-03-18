package comp3350.budgetapp.business;

import java.util.List;

import comp3350.budgetapp.application.Main;
import comp3350.budgetapp.application.Services;
import comp3350.budgetapp.objects.IncomeSource;
import comp3350.budgetapp.persistence.DataAccessStub;
import comp3350.budgetapp.business.Calculate;

public class AccessIncomeSource
{
    private DataAccessStub dataAccess;
    private List<IncomeSource> Incomes;
    private IncomeSource IncomeSource;
    private Calculate cal;

    private String total;
    private int currentItem;

    public AccessIncomeSource()
    {
        dataAccess = Services.getDataAccess(Main.dbName);
        Incomes = null;
        IncomeSource = null;
        currentItem = 0;
        cal = new Calculate();
    }

    public String getIncomeSources(List<IncomeSource> Incomes)
    {
        Incomes.clear();
        return dataAccess.getIncomeSourceSequential(Incomes);
    }

    public IncomeSource getSequential()
    {
        String result = null;
        if (Incomes == null)
        {
            result = dataAccess.getIncomeSourceSequential(Incomes);
            total = Calculate.incomeTotal(Incomes);
            currentItem = 0;
        }
        if (currentItem < Incomes.size())
        {
            IncomeSource = Incomes.get(currentItem);
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
            IncomeSource = Incomes.get(currentItem);
            total = Calculate.incomeTotal(Incomes);
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
        //System.out.println("TOTAL: " + Calculate.IncomesTotal(Incomes));
        return cal.incomeTotal(Incomes);
    }

}
