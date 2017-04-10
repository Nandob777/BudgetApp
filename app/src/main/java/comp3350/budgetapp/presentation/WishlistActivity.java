package comp3350.budgetapp.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ListView;
import java.util.Date;

import java.util.ArrayList;

import comp3350.budgetapp.R;
import comp3350.budgetapp.business.AccessExpenses;
import comp3350.budgetapp.business.Calculate;
import comp3350.budgetapp.objects.Expense;
import comp3350.budgetapp.objects.FinancialObjects;
import comp3350.budgetapp.objects.WishListItem;
import comp3350.budgetapp.business.AccessWishListItems;

public class WishlistActivity extends AppCompatActivity {

    private Calculate totalPrice;
    static String total = "Updates on opening Wish List";
    private AccessWishListItems accessWishListItems;
    private AccessExpenses accessExpenses;
    private ArrayList<FinancialObjects> itemList;
    private ArrayAdapter<FinancialObjects> itemArrayAdapter;
    private int selectedItemPosition = -1;
    private TextView viewTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        accessWishListItems = new AccessWishListItems();
        accessExpenses = new AccessExpenses();
        totalPrice = new Calculate();

        itemList = new ArrayList<FinancialObjects>();
        String result = accessWishListItems.getWishListItems(itemList);
        if (result != null)
        {
            Messages.fatalError(this, result);
        }
        else
        {
            itemArrayAdapter = new ArrayAdapter<FinancialObjects>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, itemList)
            {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                    TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                    text1.setText(itemList.get(position).getName());
                    text2.setText(String.format("$ %.2f", itemList.get(position).getAmount()));

                    return view;
                }
            };

            final ListView listView = (ListView)findViewById(R.id.listWishes);
            listView.setAdapter(itemArrayAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Button updateButton = (Button)findViewById(R.id.buttonItemUpdate);
                    Button deleteButton = (Button)findViewById(R.id.buttonItemDelete);

                    if (position == selectedItemPosition) {
                        listView.setItemChecked(position, false);
                        updateButton.setEnabled(false);
                        deleteButton.setEnabled(false);
                        selectedItemPosition = -1;
                    } else {
                        listView.setItemChecked(position, true);
                        updateButton.setEnabled(true);
                        deleteButton.setEnabled(true);
                        selectedItemPosition = position;
                        selectItemAtPosition(position);
                    }
                }
            });

            viewTotal = (TextView)findViewById(R.id.viewTotal);

            viewTotal.setText("$ " + Calculate.calculateTotal(itemList));
            total = Calculate.calculateTotal(itemList);
        }
    }

    public void selectItemAtPosition(int position)
    {
        WishListItem selected = (WishListItem) itemArrayAdapter.getItem(position);

        EditText editName = (EditText)findViewById(R.id.editItemName);
        EditText editPrice = (EditText)findViewById(R.id.editPrice);

        editName.setText(selected.getName());
        editPrice.setText(String.valueOf(selected.getAmount()));
    }

    public void buttonItemAddOnClick(View v)
    {
        EditText editName = (EditText)findViewById(R.id.editItemName);
        EditText editPrice = (EditText)findViewById(R.id.editPrice);
        WishListItem item = createItemFromEditText();

        if(itemList.contains(item) || item.getName().contains("'"))
        {
            Messages.warning(this,"Can't add duplicates of Items");
            return;
        }

        if(editPrice.getText().equals("") && editName.getText().equals(""))
        {
            Messages.warning(this,"Please enter an item");
            return;
        }


        if(item.getAmount() > 100000)
        {
            Messages.warning(this,"PLEASE BE REALISTIC");
            return;
        }

        String result;

        result = validateItemData(item, true);
        if (result == null)
        {
            result = accessWishListItems.addWishListItem(item);
            if (result == null)
            {
                accessWishListItems.getWishListItems(itemList);
                itemArrayAdapter.notifyDataSetChanged();
                int pos = itemList.indexOf(item);
                if (pos >= 0)
                {
                    ListView listView = (ListView) findViewById(R.id.listWishes);
                    listView.setSelection(pos);
                }
            }
            else
            {
                Messages.fatalError(this, result);
            }
        }
        else
        {
            Messages.warning(this, result);
        }

        viewTotal.setText(Calculate.calculateTotal(itemList));
        total = Calculate.calculateTotal(itemList);
        clearFields();
    }

    public void buttonItemDeleteOnClick(View v)
    {
        EditText editPrice = (EditText)findViewById(R.id.editPrice);
        WishListItem item = createItemFromEditText();

        if(!itemList.contains(item))
        {
            Messages.warning(this,"Can't remove Item that isn't in system");
            return;
        }

        if(editPrice.getText().equals(""))
        {
            Messages.warning(this,"No Item selected to be removed");
            return;
        }

        String result;

        result = accessWishListItems.deleteWishListItem(item);

        if(result == null)
        {
            int pos = itemList.indexOf(item);
            if(pos >=0)
            {
                ListView listView = (ListView) findViewById(R.id.listWishes);
                listView.setSelection(pos);
            }
            accessWishListItems.getWishListItems(itemList);
            itemArrayAdapter.notifyDataSetChanged();
        }
        else
        {
            Messages.warning(this, result);
        }
        viewTotal.setText(Calculate.calculateTotal(itemList));
        total = Calculate.calculateTotal(itemList);
        clearFields();
    }

    public void buttonItemPurchasedOnClick(View v)
    {
        WishListItem item = createItemFromEditText();

        if(!itemList.contains(item))
        {
            Messages.warning(this,"Can't purchase Item that isn't in system");
            return;
        }

        accessExpenses = new AccessExpenses();
        accessExpenses.addExpense(new Expense(item.getName(), item.getAmount()));

        this.buttonItemDeleteOnClick(v);
    }

    public void buttonItemUpdateOnClick(View v)
    {
        EditText editPrice = (EditText)findViewById(R.id.editPrice);

        WishListItem item = createItemFromEditText();

        if(!itemList.contains(item))
        {
            Messages.warning(this,"Must add Item before updating it");
            return;
        }

        if(editPrice.getText().equals(""))
        {
            Messages.warning(this,"No Item selected for update");
            return;
        }

        if(item.getAmount() > 100000)
        {
            Messages.warning(this,"PLEASE BE REALISTIC");
            return;
        }

        String result;

        result = validateItemData(item, false);
        if(result == null)
        {
            result = accessWishListItems.updateWishListItem(item);
            if(result == null)
            {
                accessWishListItems.getWishListItems(itemList);
                itemArrayAdapter.notifyDataSetChanged();
                int pos = itemList.indexOf(item);
                if(pos >= 0)
                {
                    ListView listView = (ListView) findViewById(R.id.listWishes);
                    listView.setSelection(pos);
                }
            }
            else
            {
                Messages.fatalError(this, result);
            }
        }
        else
        {
            Messages.fatalError(this, result);
        }
        viewTotal.setText(Calculate.calculateTotal(itemList));
        total = Calculate.calculateTotal(itemList);
        clearFields();
    }


    private WishListItem createItemFromEditText()
    {
        EditText editItemName = (EditText) findViewById(R.id.editItemName);
        EditText editPrice = (EditText) findViewById(R.id.editPrice);
        String itemName = editItemName.getText().toString();
        String priceString = editPrice.getText().toString();

        if(itemName == null || itemName.equals(""))
        {
            itemName = "Misc";
        }

        if(priceString.length() == 0){
            priceString = "0.0";
        }

        Double price = Double.parseDouble(priceString);

        WishListItem item = new WishListItem(itemName, price);

        return item;
    }

    private String validateItemData(WishListItem item, boolean isNewItem)
    {
        if (item.getName().length() == 0)
        {
            return "Item Name required!";
        }

        if (isNewItem && accessWishListItems.getRandom(item.getName()) != null)
        {
            return "Item " + item.getName() + " already exists";
        }

        return null;
    }

    private void clearFields()
    {
        EditText editName = (EditText)findViewById(R.id.editItemName);
        EditText editPrice = (EditText)findViewById(R.id.editPrice);

        editName.setText("");
        editPrice.setText("");
    }

}


