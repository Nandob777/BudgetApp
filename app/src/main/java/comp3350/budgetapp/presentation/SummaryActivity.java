package comp3350.budgetapp.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


import java.util.ArrayList;

import comp3350.budgetapp.R;
import comp3350.budgetapp.business.AccessIncomeSource;
import comp3350.budgetapp.business.AccessWishListItems;
import comp3350.budgetapp.business.AccessExpenses;
import comp3350.budgetapp.business.Savings;
import comp3350.budgetapp.objects.FinancialObjects;

public class SummaryActivity extends Activity{

    private TextView viewIncome;
    private TextView viewExpenses;
    private TextView viewWishTotal;
    private TextView viewSavings;

    private AccessExpenses accessExpenses;
    private AccessWishListItems accessWishList;
    private AccessIncomeSource accessIncome;

    ArrayList<FinancialObjects> itemList;
    double wishListTotal = 0.00;
    double expenseTotal = 0.00;
    double incomeTotal = 0.00;
    Savings savings;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        savings = new Savings();

        accessExpenses = new AccessExpenses();
        accessWishList = new AccessWishListItems();
        accessIncome = new AccessIncomeSource();


        itemList = new ArrayList<FinancialObjects>();
        String result = accessWishList.getWishListItems(itemList);

        for(int i =0; i < itemList.size();i++){
            wishListTotal += itemList.get(i).getAmount();
        }
        result = accessExpenses.getExpenses(itemList);

        for(int i =0; i < itemList.size();i++){
            expenseTotal += itemList.get(i).getAmount();
        }
        result = accessIncome.getIncomeSources(itemList);

        for(int i =0; i < itemList.size();i++){
            incomeTotal += itemList.get(i).getAmount();
        }



        viewExpenses = (TextView)findViewById(R.id.viewExpenseTotal);
        viewExpenses.setText(Double.toString(expenseTotal));

        viewWishTotal = (TextView)findViewById(R.id.viewWishlistTotal);
        viewWishTotal.setText(Double.toString(wishListTotal));

        viewIncome = (TextView)findViewById(R.id.viewIncomeTotal);
        viewIncome.setText(Double.toString(incomeTotal));

        viewSavings = (TextView)findViewById(R.id.viewSavingsTotal);
        viewSavings.setText(savings.toString());
    }



}
