package comp3350.budgetapp.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

import comp3350.budgetapp.R;
import comp3350.budgetapp.business.AccessIncomeSource;
import comp3350.budgetapp.business.Calculate;
import comp3350.budgetapp.business.Savings;
import comp3350.budgetapp.objects.FinancialObjects;
import comp3350.budgetapp.objects.IncomeSource;

public class IncomeActivity extends AppCompatActivity {

    private Calculate totalPrice;
    static String total = "Updates on opening Income Source";
    private AccessIncomeSource accessIncomeSources;
    private ArrayList<FinancialObjects> incomeList;
    private ArrayAdapter<FinancialObjects> itemArrayAdapter;
    private int selectedItemPosition = -1;
    private TextView viewIncomeTotal;
    Savings sav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Savings sav = new Savings();

        if(!sav.isPositiveSavings())
        {
            super.setTheme(R.style.AppTheme2);
        }
        setContentView(R.layout.activity_incomesource);

        accessIncomeSources = new AccessIncomeSource();
        totalPrice = new Calculate();
        sav = new Savings();

        incomeList = new ArrayList<FinancialObjects>();
        String result = accessIncomeSources.getIncomeSources(incomeList);
        if (result != null)
        {
            Messages.fatalError(this, result);
        }
        else
        {
            itemArrayAdapter = new ArrayAdapter<FinancialObjects>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, incomeList)
            {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                    TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                    text1.setText(incomeList.get(position).getName());
                    text2.setText(String.format("$ %.2f (%s)", incomeList.get(position).getAmount(), incomeList.get(position).getType()));

                    return view;
                }
            };

            final ListView listView = (ListView)findViewById(R.id.incomes);
            listView.setAdapter(itemArrayAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Button addButton = (Button)findViewById(R.id.buttonIncomeAdd);
                    Button updateButton = (Button)findViewById(R.id.buttonIncomeUpdate);
                    Button deleteButton = (Button)findViewById(R.id.buttonIncomeDelete);

                    if (position == selectedItemPosition) {
                        listView.setItemChecked(position, false);
                        addButton.setEnabled(true);
                        updateButton.setEnabled(false);
                        deleteButton.setEnabled(false);
                        clearFields();
                        selectedItemPosition = -1;
                    } else {
                        listView.setItemChecked(position, true);
                        addButton.setEnabled(false);
                        updateButton.setEnabled(true);
                        deleteButton.setEnabled(true);
                        selectedItemPosition = position;
                        selectItemAtPosition(position);
                    }
                }
            });

            viewIncomeTotal = (TextView)findViewById(R.id.viewIncomeTotal);

            viewIncomeTotal.setText("$ " + Calculate.calculateTotal(incomeList));
            total = Calculate.calculateTotal(incomeList);
            clearFields();

            Spinner spinner = (Spinner) findViewById(R.id.incomes_spinner);
            // Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.incomes_array, android.R.layout.simple_spinner_item);
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Apply the adapter to the spinner
            spinner.setAdapter(adapter);

        }
    }

    @Override
    protected void onResume()
    {
        financialColorChange();
        super.onResume();


    }

    public void financialColorChange()
    {
        Savings sav =  new Savings();
        if(!sav.isPositiveSavings())
        {
            super.setTheme(R.style.AppTheme2);
        }
        else
        {
            super.setTheme(R.style.AppTheme);
        }
    }

    public void selectItemAtPosition(int position)
    {
        IncomeSource selected = (IncomeSource) itemArrayAdapter.getItem(position);

        EditText editName = (EditText)findViewById(R.id.editIncomeName);
        EditText editPrice = (EditText)findViewById(R.id.editIncomeAmount);

        editName.setText(selected.getName());
        editPrice.setText(String.valueOf(selected.getAmount()));

        String compareType = selected.getType();
        Spinner editType = (Spinner) findViewById(R.id.incomes_spinner);

        String[] types = getResources().getStringArray(R.array.incomes_array);
        editType.setSelection(Arrays.asList(types).indexOf(compareType));
    }

    public void buttonIncomeAddOnClick(View v)
    {
        EditText editName = (EditText)findViewById(R.id.editIncomeName);
        EditText editPrice = (EditText)findViewById(R.id.editIncomeAmount);
        IncomeSource item = createIncomeFromEditText();

        if(incomeList.contains(item))
        {
            Messages.warning(this,"Can't add duplicates of Income Sources");
            return;
        }
        if(item.getName().contains("'"))
        {
            Messages.warning(this,"No SQL Injection Allowed");
            return;
        }
        if(editPrice.getText().equals("") && editName.getText().equals(""))
        {
            Messages.warning(this,"Please enter an Income Source");
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

        viewIncomeTotal.setText(Calculate.calculateTotal(incomeList));
        total = Calculate.calculateTotal(incomeList);
        clearFields();
    }

    public void buttonIncomeDeleteOnClick(View v)
    {
        EditText editPrice = (EditText)findViewById(R.id.editIncomeAmount);
        IncomeSource item = createIncomeFromEditText();

        if(!incomeList.contains(item))
        {
            Messages.warning(this,"Can't delete Income Source that isn't in system");
            return;
        }

        if(editPrice.getText().equals(""))
        {
            Messages.warning(this,"No Income Source selected for deletion");
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
        viewIncomeTotal.setText(Calculate.calculateTotal(incomeList));
        total = Calculate.calculateTotal(incomeList);
        clearFields();
    }

    public void buttonIncomeUpdateOnClick(View v)
    {
        EditText editPrice = (EditText)findViewById(R.id.editIncomeAmount);
        IncomeSource item = createIncomeFromEditText();

        if(!incomeList.contains(item))
        {
            Messages.warning(this,"Must add Income Source before updating it");
            return;
        }

        if(editPrice.getText().equals(""))
        {
            Messages.warning(this,"No Income Source selected for update");
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
        viewIncomeTotal.setText(Calculate.calculateTotal(incomeList));
        total = Calculate.calculateTotal(incomeList);
        clearFields();
    }

    private IncomeSource createIncomeFromEditText()
    {
        EditText editItemName = (EditText) findViewById(R.id.editIncomeName);
        EditText editPrice = (EditText) findViewById(R.id.editIncomeAmount);
        Spinner editType = (Spinner) findViewById(R.id.incomes_spinner);
        String itemName = editItemName.getText().toString();
        String priceString = editPrice.getText().toString();
        String type = editType.getSelectedItem().toString();

        if(itemName == null || itemName.equals(""))
        {
            itemName = "Misc";
        }

        if(priceString.length() == 0){
            priceString = "0.00";
        }

        Double price = Double.parseDouble(priceString);

        IncomeSource item = new IncomeSource(itemName, price, type);

        return item;
    }

    private String validateIncomeData(IncomeSource item, boolean isNewItem)
    {
        if (item.getName().length() == 0)
        {
            return "Income Source name required!";
        }

        if (isNewItem && accessIncomeSources.getRandom(item.getName()) != null)
        {
            return "Income Source " + item.getName() + " already exists";
        }

        return null;
    }

    private void clearFields()
    {
        EditText editName = (EditText)findViewById(R.id.editIncomeName);
        EditText editPrice = (EditText)findViewById(R.id.editIncomeAmount);
        Spinner editType = (Spinner) findViewById(R.id.incomes_spinner);
        Button addButton = (Button)findViewById(R.id.buttonIncomeAdd);
        Button deleteButton = (Button)findViewById(R.id.buttonIncomeDelete);
        Button updateButton = (Button)findViewById(R.id.buttonIncomeUpdate);

        editName.setText("");
        editPrice.setText("");
        editType.setSelection(0);
        addButton.setEnabled(true);
        deleteButton.setEnabled(false);
        updateButton.setEnabled(false);
        financialColorChange();

        ListView listView = (ListView) findViewById(R.id.incomes);
        listView.setItemChecked(listView.getCheckedItemPosition(), false);
    }

    public void ClearOnClick(View v)
    {
        clearFields();
    }
}


