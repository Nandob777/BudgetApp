package comp3350.budgetapp.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import comp3350.budgetapp.R;
import comp3350.budgetapp.application.Main;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Main.startUp();

        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Main.shutDown();
    }

    public void addWishlist(View view)
    {
        Intent intent = new Intent(this, WishlistActivity.class);
        startActivity(intent);
    }

    public void addIncomeSource(View view)
    {
        Intent intent = new Intent(this, IncomeActivity.class);
        startActivity(intent);
    }

    public void addExpense(View view)
    {
        Intent intent = new Intent(this, ExpenseActivity.class);
        startActivity(intent);
    }
}
