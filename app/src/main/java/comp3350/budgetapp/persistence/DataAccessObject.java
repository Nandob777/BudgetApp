/**
 * This code is not used in the first iteration. It is provided as
 * an example of usage of HSQLDB (for iteration 2).
 */

package comp3350.budgetapp.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import comp3350.budgetapp.objects.Expense;
import comp3350.budgetapp.objects.FinancialObjects;
import comp3350.budgetapp.objects.IncomeSource;
import comp3350.budgetapp.objects.WishListItem;

public class DataAccessObject implements DataAccess
{
	private Statement st1, st2, st3;
	private Connection c1;
	private ResultSet rs2, rs3, rs4, rs5;

	private String dbName;
	private String dbType;

	private ArrayList<FinancialObjects> WishListItems;
	private ArrayList<FinancialObjects> IncomeSources;
	private ArrayList<FinancialObjects> Expenses;

	private String cmdString;
	private int updateCount;
	private String result;
	private static String EOF = "  ";

	public DataAccessObject(String dbName)
	{
		this.dbName = dbName;
	}

	public void open(String dbPath)
	{
		String url;
		try
		{
			// Setup for HSQL
			dbType = "HSQL";
			Class.forName("org.hsqldb.jdbcDriver").newInstance();
			url = "jdbc:hsqldb:file:" + dbPath; // stored on disk mode
			c1 = DriverManager.getConnection(url,"SA","");
			st1 = c1.createStatement();
			st2 = c1.createStatement();
			st3 = c1.createStatement();

		}
		catch (Exception e)
		{
			processSQLError(e);
		}
		System.out.println("Opened " +dbType +" database " +dbPath);
	}

	public void close()
	{
		try
		{	// commit all changes to the database
			cmdString = "SHUTDOWN COMPACT";
			rs2 = st1.executeQuery(cmdString);
			c1.close();
		}
		catch (Exception e)
		{
			processSQLError(e);
		}
		System.out.println("Closed " +dbType +" database " +dbName);
	}

	public String getWishListItemSequential(List<FinancialObjects> WishListItemResult)
	{
		WishListItem WishListItem;
		String itemName, itemPrice;
		itemName = EOF;
		itemPrice = EOF;

		result = null;
		WishListItemResult.clear();
		try
		{
			cmdString = "SELECT * FROM WISHLISTITEMS";
			rs2 = st1.executeQuery(cmdString);

		}
		catch (Exception e)
		{
			processSQLError
					(e);
		}
		try
		{

			while (rs2.next())
			{
				itemName = rs2.getString("ITEMNAME");
				itemPrice = rs2.getString("ITEMPRICE");
				WishListItem = new WishListItem(itemName,Double.parseDouble(itemPrice));
				WishListItemResult.add(WishListItem);
			}
			rs2.close();
		}
		catch (Exception e)
		{
			result = processSQLError(e);
		}

		return result;
	}

	public ArrayList<FinancialObjects> getWishListItemRandom(WishListItem newWishListItem)
	{
		WishListItem WishListItem;
		String itemName, itemPrice;
		itemName = EOF;
		itemPrice = EOF;
		WishListItems = new ArrayList<FinancialObjects>();
		try
		{
			cmdString = "Select * from WISHLISTITEMS where ITEMNAME= '" + newWishListItem.getName()+"'";
			rs3 = st1.executeQuery(cmdString);

			while (rs3.next())
			{
				itemName = rs3.getString("ITEMNAME");
				itemPrice = rs3.getString("ITEMPRICE");
				WishListItem = new WishListItem(itemName,Double.parseDouble(itemPrice));
				WishListItems.add(WishListItem);
			}
			rs3.close();
		} catch (Exception e)
		{
			processSQLError(e);
		}
		return WishListItems;
	}

	public String addWishListItem(WishListItem currentWishListItem)
	{
		String values;

		result = null;
		try
		{
			values = "'"+currentWishListItem.getName()
			         +"'," +currentWishListItem.getAmount();
			cmdString = "INSERT INTO WISHLISTITEMS " +" VALUES(" +values +")";

			updateCount = st1.executeUpdate(cmdString);
			result = checkWarning(st1, updateCount);
		}
		catch (Exception e)
		{
			result = processSQLError(e);
		}
		return result;
	}

	public String updateWishListItem(WishListItem currentWishListItem)
	{
		String values;
		String where;

		result = null;
		try
		{
			// Should check for empty values and not update them
			values = "ITEMNAME= '" +currentWishListItem.getName()
			         +"', ITEMPRICE=" +currentWishListItem.getAmount();
			where = "where ITEMNAME = '" +currentWishListItem.getName()+"'";
            ;
			cmdString = "UPDATE WISHLISTITEMS " +" SET " +values +" " +where;
			//System.out.println(cmdString);
			updateCount = st1.executeUpdate(cmdString);
			result = checkWarning(st1, updateCount);
		}
		catch (Exception e)
		{
			result = processSQLError(e);
		}
		return result;
	}

	public String deleteWishListItem(WishListItem currentWishListItem)
	{
		String values;

		result = null;
		try
		{
			values = currentWishListItem.getName();
			cmdString = "DELETE FROM WISHLISTITEMS WHERE ITEMNAME = '" +values+"'";

			updateCount = st1.executeUpdate(cmdString);
			result = checkWarning(st1, updateCount);
		}
		catch (Exception e)
		{
			result = processSQLError(e);
		}
		return result;
	}

	public String getIncomeSourceSequential(List<FinancialObjects> IncomeSourceResult)
	{
		IncomeSource IncomeSource;
		String IncomeSourceName, amount, type;
        IncomeSourceName = EOF;
        amount = EOF;

		result = null;
		IncomeSourceResult.clear();
		try
		{
			cmdString = "SELECT * FROM INCOMESOURCES";
			rs5 = st3.executeQuery(cmdString);

			while (rs5.next())
			{
                IncomeSourceName = rs5.getString("SOURCENAME");
				amount = rs5.getString("AMOUNT");
				type = rs5.getString("SOURCETYPE");
				IncomeSource = new IncomeSource(IncomeSourceName, Double.parseDouble(amount), type);
				IncomeSourceResult.add(IncomeSource);
			}
			rs5.close();
		}
		catch (Exception e)
		{
			result = processSQLError(e);
		}
		return result;
	}

	public ArrayList<FinancialObjects> getIncomeSourceRandom(IncomeSource newIncomeSource)
	{
		IncomeSource IncomeSource;
		String IncomeSourceName, amount, type;
        IncomeSourceName = EOF;
		amount = EOF;

		IncomeSources = new ArrayList<FinancialObjects>();
		try
		{
			cmdString = "SELECT * FROM INCOMESOURCES WHERE SOURCENAME='" +newIncomeSource.getName() +"'";
			rs5 = st3.executeQuery(cmdString);

			while (rs5.next())
			{
                IncomeSourceName = rs5.getString("SOURCENAME");
				amount = rs5.getString("AMOUNT");
				type = rs5.getString("SOURCETYPE");
				IncomeSource = new IncomeSource(IncomeSourceName, Double.parseDouble(amount), type);
				IncomeSources.add(IncomeSource);
			}
			rs5.close();
		}
		catch (Exception e)
		{
			processSQLError(e);
		}
		return IncomeSources;
	}

	public String addIncomeSource(IncomeSource currentIncomeSource)
	{
		String values;

		result = null;
		try
		{
			values =  "'" +currentIncomeSource.getName()
			         +"'," +currentIncomeSource.getAmount()
					+ ",'" +currentIncomeSource.getType() + "'";

			cmdString = "INSERT INTO INCOMESOURCES " +" Values(" +values +")";

			updateCount = st1.executeUpdate(cmdString);
			result = checkWarning(st1, updateCount);
		}
		catch (Exception e)
		{
			result = processSQLError(e);
		}
		return result;
	}

	public String updateIncomeSource(IncomeSource currentIncomeSource)
	{
		String values;
		String where;

		result = null;
		try
		{
			// Should check for empty values and not update them
			values = "SOURCENAME='" +currentIncomeSource.getName()
					+"', AMOUNT=" +currentIncomeSource.getAmount()
					+", SOURCETYPE='"+ currentIncomeSource.getType() + "'";

			where = "where SOURCENAME='" +currentIncomeSource.getName() +"'";
			cmdString = "UPDATE INCOMESOURCES " +" SET " +values +" " +where;

			updateCount = st1.executeUpdate(cmdString);
			result = checkWarning(st1, updateCount);
		}
		catch (Exception e)
		{
			result = processSQLError(e);
		}
		return result;
	}

	public String deleteIncomeSource(IncomeSource currentIncomeSource)
	{
		String values;

		result = null;
		try
		{
			values = currentIncomeSource.getName();
			cmdString = "DELETE FROM INCOMESOURCES WHERE SOURCENAME='" +values +"'";

			updateCount = st1.executeUpdate(cmdString);
			result = checkWarning(st1, updateCount);
		}
		catch (Exception e)
		{
			result = processSQLError(e);
		}
		return result;
	}

	public String getExpenseSequential(List<FinancialObjects> ExpenseResult)
        {
            Expense Expense;
            String name, amount;
            name = EOF;
            amount = EOF;

            result = null;
			ExpenseResult.clear();
            try
            {
                cmdString = "SELECT * FROM EXPENSES";
                rs2 = st1.executeQuery(cmdString);

            }
            catch (Exception e)
            {
                processSQLError(e);
            }
            try
            {
                while (rs2.next())
                {
                    name = rs2.getString("ITEMNAME");
                    amount = rs2.getString("ITEMPPRICE");
                    Expense = new Expense(name,Double.parseDouble(amount));
                    ExpenseResult.add(Expense);
                }
                rs2.close();
            }
            catch (Exception e)
            {
                result = processSQLError(e);
            }

            return result;
	}
        public ArrayList<FinancialObjects> getExpenseRandom(Expense newExpense)
        {
            Expense Expense;
            String itemName, itemPrice;
            itemName = EOF;
            itemPrice = EOF;
            Expenses = new ArrayList<FinancialObjects>();
            try
            {
                cmdString = "SELECT * FROM EXPENSES WHERE ITEMNAME= '" + newExpense.getName()+"'";
                rs4 = st2.executeQuery(cmdString);

                while (rs4.next())
                {
                    itemName = rs4.getString("ITEMNAME");
                    itemPrice = rs4.getString("ITEMPPRICE");
					Expense = new Expense(itemName,Double.parseDouble(itemPrice));
					Expenses.add(Expense);
                }
                rs4.close();
            } catch (Exception e)
            {
                processSQLError(e);
            }
            return Expenses;
        }

    public String addExpense(Expense currentExpense)
    {
        String values;

        result = null;
        try
        {
            values = "'"+currentExpense.getName()
                    +"', " +currentExpense.getAmount();
            cmdString = "Insert into Expenses " +" Values(" +values +")";

            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }

    public String updateExpense(Expense currentExpense)
    {
        String values;
        String where;

        result = null;
        try
        {
            // Should check for empty values and not update them
            values = "ITEMNAME='" +currentExpense.getName()
                    +"', ITEMPPRICE=" +currentExpense.getAmount();
            where = "where ItemName= '" +currentExpense.getName()+"'"
            ;
            cmdString = "UPDATE EXPENSES " +" SET " +values +" " +where;

            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }

    public String deleteExpense(Expense currentExpense)
    {
        String values;

        result = null;
        try
        {
            values = currentExpense.getName();
            cmdString = "DELETE FROM EXPENSES WHERE ITEMNAME='" +values+"'";

            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }


	public String checkWarning(Statement st, int updateCount)
	{
		String result;

		result = null;
		try
		{
			SQLWarning warning = st.getWarnings();
			if (warning != null)
			{
				result = warning.getMessage();
			}
		}
		catch (Exception e)
		{
			result = processSQLError(e);
		}
		if (updateCount != 1)
		{
			result = "Tuple not inserted correctly.";
		}
		return result;
	}

	public String processSQLError(Exception e)
	{
		String result = "*** SQL Error: " + e.getMessage();

		// Remember, this will NOT be seen by the user!
		e.printStackTrace();
		
		return result;
	}
}
