package comp3350.budgetapp.business;

import java.util.List;

import comp3350.budgetapp.objects.Expense;
import comp3350.budgetapp.objects.IncomeSource;
import comp3350.budgetapp.objects.WishListItem;

public class Calculate {

    static String wishlistTotal;
    static String incomeTotal;
    static String expenseTotal;

    public static String wishlistTotal(List<WishListItem> elements)
    {

        String invalidTotal;
        WishListItem element;
        boolean found;
        double price;
        double total;
        int count;
        int elementCount;
        int missingPrices;
        int validPrices;

        wishlistTotal = "";
        invalidTotal = "?";
        total = 0.0;
        price = 0.0;
        missingPrices = 0;
        validPrices = 0;

        if( (elements != null) && (elements.size() > 0) )
        {
            for(elementCount = 0; elementCount < elements.size(); elementCount++)
            {
                if( !(elements.get(elementCount) instanceof WishListItem))
                {
                    elementCount = elements.size() + 1;
                    wishlistTotal = invalidTotal;
                }
                else
                {
                    element = (WishListItem) elements.get(elementCount);
                    price = ((WishListItem) element).getPrice();
                    found = false;
                    if (price <= 0.0)
                    {
                        missingPrices++;
                    }
                    else
                    {
                        total += price;
                        validPrices++;
                    }
                }
            }

            if( (missingPrices+validPrices) == elements.size() && validPrices > 0)
            {
                wishlistTotal = String.format("%.2f", (total));
            }
            else if( missingPrices != elements.size())
            {
                wishlistTotal = invalidTotal;
            }
        }

        return wishlistTotal;
    }

    public static String incomeTotal(List<IncomeSource> elements)
    {
       // String incomeTotal;
        String invalidTotal;
        IncomeSource element;
        boolean found;
        double price;
        double total;
        int count;
        int elementCount;
        int missingPrices;
        int validPrices;

        incomeTotal = "";
        invalidTotal = "?";
        total = 0.0;
        price = 0.0;
        missingPrices = 0;
        validPrices = 0;

        if( (elements != null) && (elements.size() > 0) )
        {
            for(elementCount = 0; elementCount < elements.size(); elementCount++)
            {
                if( !(elements.get(elementCount) instanceof IncomeSource))
                {
                    elementCount = elements.size() + 1;
                    incomeTotal = invalidTotal;
                }
                else
                {
                    element = (IncomeSource) elements.get(elementCount);
                    price = ( ((IncomeSource) element).getAmount() ) * ( ((IncomeSource) element).getFrequency() );
                    found = false;
                    if (price <= 0.0)
                    {
                        missingPrices++;
                    }
                    else
                    {
                        total += price;
                        validPrices++;
                    }
                }
            }

            if( (missingPrices+validPrices) == elements.size() && validPrices > 0)
            {
                incomeTotal = String.format("%.2f", (total));
            }
            else if( missingPrices != elements.size())
            {
                incomeTotal = invalidTotal;
            }
        }

        return incomeTotal;
    }


    public static String expenseTotal(List<Expense> elements)
    {
       // String expenseTotal;
        String invalidTotal;
        Expense element;
        boolean found;
        double price;
        double total;
        int count;
        int elementCount;
        int missingPrices;
        int validPrices;

        expenseTotal = "";
        invalidTotal = "?";
        total = 0.0;
        price = 0.0;
        missingPrices = 0;
        validPrices = 0;

        if( (elements != null) && (elements.size() > 0) )
        {
            for(elementCount = 0; elementCount < elements.size(); elementCount++)
            {
                if( !(elements.get(elementCount) instanceof Expense))
                {
                    elementCount = elements.size() + 1;
                    expenseTotal = invalidTotal;
                }
                else
                {
                    element = (Expense) elements.get(elementCount);
                    price = ((Expense) element).getAmount();
                    found = false;
                    if (price <= 0.0)
                    {
                        missingPrices++;
                    }
                    else
                    {
                        total += price;
                        validPrices++;
                    }
                }
            }

            if( (missingPrices+validPrices) == elements.size() && validPrices > 0)
            {
                expenseTotal = String.format("%.2f", (total));
            }
            else if( missingPrices != elements.size())
            {
                expenseTotal = invalidTotal;
            }
        }

        return expenseTotal;
    }

}
