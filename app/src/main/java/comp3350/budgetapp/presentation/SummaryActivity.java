package comp3350.budgetapp.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;



import comp3350.budgetapp.R;
import comp3350.budgetapp.business.AccessIncomeSource;
import comp3350.budgetapp.business.AccessWishListItems;
import comp3350.budgetapp.business.AccessExpenses;

public class SummaryActivity extends Activity{

    private TextView viewIncome;
    private TextView viewExpenses;
    private TextView viewWishTotal;

    private AccessExpenses accessExpenses;
    private AccessWishListItems accessWishList;
    private AccessIncomeSource accessIncome;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        accessExpenses = new AccessExpenses();
        accessWishList = new AccessWishListItems();
        accessIncome = new AccessIncomeSource();



        viewExpenses = (TextView)findViewById(R.id.viewExpenseTotal);
        viewExpenses.setText(ExpenseActivity.total);

        viewWishTotal = (TextView)findViewById(R.id.viewWishlistTotal);
        viewWishTotal.setText(WishlistActivity.total);

        viewIncome = (TextView)findViewById(R.id.viewIncomeTotal);
        viewIncome.setText(IncomeActivity.total);
    }

}
