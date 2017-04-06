package comp3350.budgetapp.business;

import java.util.List;

import comp3350.budgetapp.objects.Expense;
import comp3350.budgetapp.objects.FinancialObjects;
import comp3350.budgetapp.objects.IncomeSource;
import comp3350.budgetapp.objects.WishListItem;

public class Calculate {

    static IncomeSource src = new IncomeSource("Empty");
    static Expense exp = new Expense("Empty");
    static WishListItem wish = new WishListItem("Empty");

    final static Class CLASS_INCOME = src.getClass();
    final static Class CLASS_EXP = exp.getClass();
    final static Class CLASS_WISH = wish.getClass();

    public static String calculateTotal(List<FinancialObjects> financialObjectsList)
    {
        /*
         * CHANGES MADE: instead of returning "" (empty string) for all negative prices/blank prices
         *               it returns "0.00"
         */
        String retTotal;
        String invalidTotal;
        FinancialObjects financialObject;
        Class type;
        double price;
        double total;
        int elementCount;
        int missingPrices;
        int validPrices;

        retTotal = "";
        invalidTotal = "?";
        total = 0.0;
        price = 0.0;
        missingPrices = 0;
        validPrices = 0;

        if( (financialObjectsList != null) && (financialObjectsList.size() > 0) )
        {
            type = validateList(financialObjectsList);

            if(type == null)
            {
                retTotal = invalidTotal;
            }
            else if(type.equals(CLASS_INCOME) )
            {
                for(elementCount = 0; elementCount < financialObjectsList.size(); elementCount++) {
                    financialObject = financialObjectsList.get(elementCount);
                    price = ((financialObject).getAmount()) * (((IncomeSource) financialObject).getFrequency());
                    if (price <= 0.0) {
                        missingPrices++;
                    } else {
                        total += price;
                        validPrices++;
                    }
                }
            } else if(type.equals(CLASS_WISH) || type.equals(CLASS_EXP)){
                for(elementCount = 0; elementCount < financialObjectsList.size(); elementCount++) {
                    financialObject = financialObjectsList.get(elementCount);
                    price = ((financialObject).getAmount());
                    if (price <= 0.0) {
                        missingPrices++;
                    } else {
                        total += price;
                        validPrices++;
                    }
                }
            }

            if( (missingPrices+validPrices) == financialObjectsList.size() && validPrices >= 0)
            {
                retTotal = String.format("%.2f", (total));
            }
            else if( missingPrices != financialObjectsList.size())
            {
                retTotal = invalidTotal;
            }
        }

        return retTotal;
    }

    private static Class validateList(List<FinancialObjects> elements)
    {
        /*
         * checks that all elements in the list are the same type of FinancialObject subclass
         * (WishListItem, Expense, IncomeSource)
         * returns one these classes OR null if not valid
         */
        FinancialObjects el;
        int elementCount;
        Class type;
        boolean isSame;

        el = elements.get(0);
        type = null;

        if(el != null) {
            type = el.getClass();
            elementCount = 1;
            isSame = true;

            while (isSame && elementCount < elements.size()) {

                el = elements.get(elementCount);

                if (el == null || !((el.getClass()).equals(type))) {
                    type = null;
                    isSame = false;
                }
                elementCount++;
            }
        }

        return type;
    }

}
