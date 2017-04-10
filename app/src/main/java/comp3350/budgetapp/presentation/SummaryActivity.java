package comp3350.budgetapp.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


import java.util.ArrayList;

import comp3350.budgetapp.R;
import comp3350.budgetapp.business.AccessIncomeSource;
import comp3350.budgetapp.business.AccessWishListItems;
import comp3350.budgetapp.business.AccessExpenses;
import comp3350.budgetapp.business.Calculate;
import comp3350.budgetapp.business.Savings;
import comp3350.budgetapp.objects.FinancialObjects;

public class SummaryActivity extends AppCompatActivity {

    private TextView viewIncome;
    private TextView viewExpenses;
    private TextView viewWishTotal;
    private TextView viewSavings;

    private AccessExpenses accessExpenses;
    private AccessWishListItems accessWishList;
    private AccessIncomeSource accessIncome;

    ArrayList<FinancialObjects> itemList;
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
        viewWishTotal = (TextView)findViewById(R.id.viewWishlistTotal);
        viewWishTotal.setText("$ " + Calculate.calculateTotal(itemList));

        result = accessExpenses.getExpenses(itemList);
        viewExpenses = (TextView)findViewById(R.id.viewExpenseTotal);
        viewExpenses.setText("$ " + Calculate.calculateTotal(itemList));

        result = accessIncome.getIncomeSources(itemList);
        viewIncome = (TextView)findViewById(R.id.viewIncomeTotal);
        double incomeTotal = Double.parseDouble(Calculate.calculateTotal(itemList));
        for(int i =0; i < itemList.size();i++){
            if (itemList.get(i).getType().equalsIgnoreCase("Misc"))
                incomeTotal += itemList.get(i).getAmount();
        }
        viewIncome.setText(String.format("$ %.2f", incomeTotal));

        viewSavings = (TextView)findViewById(R.id.viewSavingsTotal);
        viewSavings.setText(savings.toString());
    }

}
