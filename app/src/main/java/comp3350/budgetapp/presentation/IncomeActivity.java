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
import comp3350.budgetapp.business.Calculate;
import comp3350.budgetapp.objects.IncomeSource;
import comp3350.budgetapp.business.AccessIncomeSource;

public class IncomeActivity extends Activity {

    private Calculate totalPrice;
    static String total = "Updates on opening Income Source";
    private AccessIncomeSource accessIncomeSources;
    private ArrayList<IncomeSource> incomeList;
    private ArrayAdapter<IncomeSource> itemArrayAdapter;
    private int selectedItemPosition = -1;
    private TextView viewIncomeTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incomesource);

        accessIncomeSources = new AccessIncomeSource();
        totalPrice = new Calculate();

        incomeList = new ArrayList<IncomeSource>();
        String result = accessIncomeSources.getIncomeSources(incomeList);
        if (result != null)
        {
            Messages.fatalError(this, result);
        }
        else
        {
            itemArrayAdapter = new ArrayAdapter<IncomeSource>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, incomeList)
            {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                    TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                    text1.setText(incomeList.get(position).getName());
                    text2.setText(String.format("%.2f", incomeList.get(position).getAmount()));

                    return view;
                }
            };

            final ListView listView = (ListView)findViewById(R.id.incomes);
            listView.setAdapter(itemArrayAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Button updateButton = (Button)findViewById(R.id.buttonIncomeUpdate);
                    Button deleteButton = (Button)findViewById(R.id.buttonIncomeDelete);

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

            //TextView viewIncomeTotal = (TextView)findViewById(R.id.viewIncomeTotal);
            viewIncomeTotal = (TextView)findViewById(R.id.viewIncomeTotal);
            //Fix Total Display
            //viewIncomeTotal.setText(accessIncomeSources.getTotal());
            viewIncomeTotal.setText(totalPrice.incomeTotal(incomeList));
            total = totalPrice.incomeTotal(incomeList);
        }
    }

    public void selectItemAtPosition(int position)
    {
        IncomeSource selected = itemArrayAdapter.getItem(position);

        EditText editName = (EditText)findViewById(R.id.editIncomeName);
        EditText editPrice = (EditText)findViewById(R.id.editIncomeAmount);

        editName.setText(selected.getName());
        editPrice.setText(String.valueOf(selected.getAmount()));
    }

    public void buttonIncomeAddOnClick(View v)
    {
        IncomeSource item = createIncomeFromEditText();

        if(incomeList.contains(item) || item.getName().contains("'"))
        {
            Messages.warning(this,"Can't add duplicates of Income Sources");
            return;
        }
        if(item.getAmount() > 1000000)
        {
            Messages.warning(this,"YOUR BLOOD IS TOO RICH FOR THIS APP");
            return;
        }

        String result;

        result = validateIncomeData(item, true);
        if (result == null)
        {
            result = accessIncomeSources.addIncomeSource(item);
            if (result == null)
            {
                accessIncomeSources.getIncomeSources(incomeList);
                itemArrayAdapter.notifyDataSetChanged();
                int pos = incomeList.indexOf(item);
                if (pos >= 0)
                {
                    ListView listView = (ListView) findViewById(R.id.incomes);
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

        viewIncomeTotal.setText(totalPrice.incomeTotal(incomeList));
        total = totalPrice.incomeTotal(incomeList);
    }

    public void buttonIncomeDeleteOnClick(View v)
    {
        IncomeSource item = createIncomeFromEditText();

        if(!incomeList.contains(item))
        {
            Messages.warning(this,"Must Add Income Source Before Updating It");
            return;
        }
        if(item.getAmount() > 1000000)
        {
            Messages.warning(this,"YOUR BLOOD IS TOO RICH FOR THIS APP");
            return;
        }

        String result;

        result = accessIncomeSources.deleteIncomeSource(item);

        if(result == null)
        {
            int pos = incomeList.indexOf(item);
            if(pos >=0)
            {
                ListView listView = (ListView) findViewById(R.id.incomes);
                listView.setSelection(pos);
            }
            accessIncomeSources.getIncomeSources(incomeList);
            itemArrayAdapter.notifyDataSetChanged();
        }
        else
        {
            Messages.warning(this, result);
        }
        viewIncomeTotal.setText(totalPrice.incomeTotal(incomeList));
        total = totalPrice.incomeTotal(incomeList);
    }

    public void buttonIncomeUpdateOnClick(View v)
    {
        IncomeSource item = createIncomeFromEditText();

        if(!incomeList.contains(item))
        {
            Messages.warning(this,"Can't delete Expense that isn't in system");
            return;
        }

        String result;

        result = validateIncomeData(item, false);
        if(result == null)
        {
            result = accessIncomeSources.updateIncomeSource(item);
            if(result == null)
            {
                accessIncomeSources.getIncomeSources(incomeList);
                itemArrayAdapter.notifyDataSetChanged();
                int pos = incomeList.indexOf(item);
                if(pos >= 0)
                {
                    ListView listView = (ListView) findViewById(R.id.incomes);
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
        viewIncomeTotal.setText(totalPrice.incomeTotal(incomeList));
        total = totalPrice.incomeTotal(incomeList);
    }

    private IncomeSource createIncomeFromEditText()
    {
        EditText editItemName = (EditText) findViewById(R.id.editIncomeName);
        EditText editPrice = (EditText) findViewById(R.id.editIncomeAmount);
        String itemName = editItemName.getText().toString();
        String priceString = editPrice.getText().toString();

        if(itemName == null || itemName.equals(""))
        {
            itemName = "Misc";
        }

        if(priceString.length() == 0){
            priceString = "0.00";
        }

        Double price = Double.parseDouble(priceString);

        IncomeSource item = new IncomeSource(itemName, price);

        return item;
    }

    private String validateIncomeData(IncomeSource item, boolean isNewItem)
    {
        if (item.getName().length() == 0)
        {
            return "Item Name required!";
        }

        if (isNewItem && accessIncomeSources.getRandom(item.getName()) != null)
        {
            return "Item " + item.getName() + " already exists";
        }

        return null;
    }
}


