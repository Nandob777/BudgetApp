package comp3350.budgetapp.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import comp3350.budgetapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addWishlist(View view)
    {
        Intent intent = new Intent(this, WishlistActivity.class);
        startActivity(intent);
    }
}
