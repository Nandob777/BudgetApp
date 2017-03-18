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
import comp3350.budgetapp.business.Calculate;
import comp3350.budgetapp.objects.Expense;
import comp3350.budgetapp.objects.WishListItem;
import comp3350.budgetapp.business.AccessExpenses;

public class ExpenseActivity extends Activity {

    private Calculate totalPrice;
    private AccessExpenses accessExpenses;
    private ArrayList<Expense> itemList;
    private ArrayAdapter<Expense> itemArrayAdapter;
    private int selectedItemPosition = -1;
    private TextView viewTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        accessExpenses = new AccessExpenses();
        totalPrice = new Calculate();

        itemList = new ArrayList<Expense>();
        String result = accessExpenses.getExpenses(itemList);
        if (result != null)
        {
            Messages.fatalError(this, result);
        }
        else
        {
            itemArrayAdapter = new ArrayAdapter<Expense>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, itemList)
            {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                    TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                    text1.setText(itemList.get(position).getName());
                    text2.setText(String.format("%.2f", itemList.get(position).getAmount()));

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

            //TextView viewTotal = (TextView)findViewById(R.id.viewTotal);
            viewTotal = (TextView)findViewById(R.id.viewTotal);
            //Fix Total Display
            //viewTotal.setText(accessWishListItems.getTotal());
            viewTotal.setText(totalPrice.expenseTotal(itemList));
        }
    }

    public void selectItemAtPosition(int position)
    {
        Expense selected = itemArrayAdapter.getItem(position);

        EditText editName = (EditText)findViewById(R.id.editItemName);
        EditText editPrice = (EditText)findViewById(R.id.editPrice);

        editName.setText(selected.getName());
        editPrice.setText(String.valueOf(selected.getAmount()));
    }

    public void buttonItemAddOnClick(View v)
    {
        Expense item = createItemFromEditText();
        String result;

        result = validateItemData(item, true);
        if (result == null)
        {
            result = accessExpenses.addExpense(item);
            if (result == null)
            {
                accessExpenses.getExpenses(itemList);
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

        viewTotal.setText(totalPrice.expenseTotal(itemList));
    }

    public void buttonItemDeleteOnClick(View v)
    {
        Expense item = createItemFromEditText();
        String result;

        result = accessExpenses.deleteExpense(item);

        if(result == null)
        {
            int pos = itemList.indexOf(item);
            if(pos >=0)
            {
                ListView listView = (ListView) findViewById(R.id.listWishes);
                listView.setSelection(pos);
            }
            accessExpenses.getExpenses(itemList);
            itemArrayAdapter.notifyDataSetChanged();
        }
        else
        {
            Messages.warning(this, result);
        }
        viewTotal.setText(totalPrice.expenseTotal(itemList));
    }

    public void buttonItemUpdateOnClick(View v)
    {
        Expense item = createItemFromEditText();
        String result;

        result = validateItemData(item, false);
        if(result == null)
        {
            result = accessExpenses.updateExpense(item);
            if(result == null)
            {
                accessExpenses.getExpenses(itemList);
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
        viewTotal.setText(totalPrice.expenseTotal(itemList));
    }

    private Expense createItemFromEditText()
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
            priceString = "0";
        }

        Double price = Double.parseDouble(priceString);

        Expense item = new Expense(itemName, price);

        return item;
    }

    private String validateItemData(Expense item, boolean isNewItem)
    {
        if (item.getName().length() == 0)
        {
            return "Item Name required!";
        }

        if (isNewItem && accessExpenses.getRandom(item.getName()) != null)
        {
            return "Item " + item.getName() + " already exists";
        }

        return null;
    }
}


