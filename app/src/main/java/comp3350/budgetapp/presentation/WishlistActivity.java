package comp3350.budgetapp.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import comp3350.budgetapp.R;
import comp3350.budgetapp.objects.WishListItem;
import comp3350.budgetapp.business.AccessWishListItems;

public class WishlistActivity extends AppCompatActivity {

    private AccessWishListItems accessWishListItems;
    private ArrayList<WishListItem> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);
    }

    public void buttonItemAddOnClick(View v)
    {
       WishListItem item = createItemFromEditText();
        String result;


        if(item != null)
        {
            result = accessWishListItems.insertWishListItem(item);

            if(result == null)
            {
                accessWishListItems.getWishlistItems(itemList);
            }
        }else{
            System.out.println("Error");
        }

    }

    public void buttonItemDeleteOnClick(View v)
    {
        WishListItem item = createItemFromEditText();
        String result;

        result = accessWishListItems.deleteWishListItem(item);

        if(result == null)
        {
            int pos = itemList.indexOf(item);
            if(pos >=0)
            {
                ListViewCompat listViewCompat = (ListViewCompat) findViewById(R.id.listWishes);
                listViewCompat.setSelection(pos);
            }
            accessWishListItems.getWishlistItems(itemList);
        }else{
            System.out.println("Error");
        }
    }

    public void buttonItemUpdateOnClick(View v)
    {
        WishListItem item = createItemFromEditText();
        String result;

        if(item != null)
        {
            result = accessWishListItems.updateWishListItem(item);
            if(result == null)
            {
                accessWishListItems.getWishlistItems(itemList);
                int pos = itemList.indexOf(item);
                if(pos >= 0)
                {
                    ListViewCompat listViewCompat = (ListViewCompat) findViewById(R.id.listWishes);
                    listViewCompat.setSelection(pos);
                }
            }else{
                System.out.println("Error");
            }
        }else{
            System.out.println("Error");
        }
    }

    private WishListItem createItemFromEditText()
    {
        EditText editItemName = (EditText) findViewById(R.id.editItemName);
        EditText editPrice = (EditText) findViewById(R.id.editPrice);

        WishListItem item = new WishListItem(editItemName.getText().toString() , Integer.parseInt(editPrice.getText().toString()));
        return item;
    }




}


