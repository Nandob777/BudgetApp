package comp3350.budgetapp.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ListView;

import java.util.ArrayList;

import comp3350.budgetapp.R;
import comp3350.budgetapp.business.AccessIncomeSource;
import comp3350.budgetapp.business.AccessWishListItems;
import comp3350.budgetapp.business.Calculate;
import comp3350.budgetapp.objects.Expense;
import comp3350.budgetapp.business.AccessExpenses;
import comp3350.budgetapp.objects.WishListItem;

public class SummaryActivity extends Activity{

    private TextView viewIncome;
    private TextView viewExpenses;
    private TextView viewWishTotal;

    private AccessExpenses accessExpenses;
    private AccessWishListItems accessWishList;
    private AccessIncomeSource accessIncome;
    private Calculate totalPrice;


    private ArrayList<WishListItem> itemList;
    private WishListItem currentItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        accessExpenses = new AccessExpenses();
        accessWishList = new AccessWishListItems();
        accessIncome = new AccessIncomeSource();



        viewExpenses = (TextView)findViewById(R.id.viewExpenseTotal);
        //viewExpenses.setText(accessExpenses.getTotal());
        viewExpenses.setText(ExpenseActivity.total);

        viewWishTotal = (TextView)findViewById(R.id.viewTotal);
        //viewWishTotal.setText(accessWishList.getTotal());
        viewWishTotal.setText(WishlistActivity.total);

        viewIncome = (TextView)findViewById(R.id.viewIncomeTotal);
        //viewIncome.setText(accessIncome.getTotal());
        viewIncome.setText(IncomeActivity.total);
    }

}
