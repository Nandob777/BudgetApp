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
import comp3350.budgetapp.objects.IncomeSource;
import comp3350.budgetapp.objects.WishListItem;

public class DataAccessObject implements DataAccess
{
	private Statement st1, st2, st3;
	private Connection c1;
	private ResultSet rs2, rs3, rs4, rs5;

	private String dbName;
	private String dbType;

	private ArrayList<WishListItem> WishListItems;
	private ArrayList<IncomeSource> IncomeSources;
	private ArrayList<Expense> Expenses;

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
			dbType = "HSQL Database Engine In-Memory";
			Class.forName("org.hsqldb.jdbcDriver").newInstance();
			//url = "jdbc:hsqldb:file:" + dbPath; // stored on disk mode
			url = "jdbc:hsqldb:file:database/Budget/";
			c1 = DriverManager.getConnection(url, "SA", "");

			if(c1 == null)
			{
				System.out.println("Issue with Connecting to Database");
			}else{
				System.out.println("Connecting to Database");
			}
			st1 = c1.createStatement();
			st2 = c1.createStatement();
			st3 = c1.createStatement();

			/*** Alternate setups for different DB engines, just given as examples. Don't use them. ***/
			
			/*
			 * // Setup for SQLite. Note that this is undocumented and is not guaranteed to work.
			 * // See also: https://github.com/SQLDroid/SQLDroid
			 * dbType = "SQLite";
			 * Class.forName("SQLite.JDBCDriver").newInstance();
			 * url = "jdbc:sqlite:" + dbPath;
			 * c1 = DriverManager.getConnection(url);     
			 * 
			 * ... create statements
			 */

			/*** The following two work on desktop builds: ***/

			/*
			 * // Setup for Access
			 * dbType = "Access";
			 * Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
			 * url = "jdbc:odbc:Expense";
			 * c1 = DriverManager.getConnection(url,"userid","userpassword");
			 * 
			 * ... create statements
			 */

			/*
			 * //Setup for MySQL
			 * dbType = "MySQL";
			 * Class.forName("com.mysql.jdbc.Driver");
			 * url = "jdbc:mysql://localhost/database01";
			 * c1 = DriverManager.getConnection(url, "root", "");
			 * 
			 * ... create statements
			 */
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
			cmdString = "shutdown compact";
			rs2 = st1.executeQuery(cmdString);
			c1.close();
		}
		catch (Exception e)
		{
			processSQLError(e);
		}
		System.out.println("Closed " +dbType +" database " +dbName);
	}

	public String getWishListItemSequential(List<WishListItem> WishListItemResult)
	{
		WishListItem WishListItem;
		String itemName, itemPrice;
		itemName = EOF;
		itemPrice = EOF;

		result = null;
		try
		{
			cmdString = "Select * from WishListItems";
			rs2 = st1.executeQuery(cmdString);
			//ResultSetMetaData md2 = rs2.getMetaData();

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
				itemName = rs2.getString("ItemName");
				itemPrice = rs2.getString("ItemPrice");
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

	public ArrayList<WishListItem> getWishListItemRandom(WishListItem newWishListItem)
	{
		WishListItem WishListItem;
		String itemName, itemPrice;
		itemName = EOF;
		itemPrice = EOF;
		WishListItems = new ArrayList<WishListItem>();
		try
		{
			cmdString = "Select * from WishListItems where ItemName=" + newWishListItem.getItemName();
			rs3 = st1.executeQuery(cmdString);
			// ResultSetMetaData md2 = rs3.getMetaData();
			rs3.next();
			while (rs3.next())
			{
				itemName = rs2.getString("ItemName");
				itemPrice = rs2.getString("ItemPrice");
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
			values = currentWishListItem.getItemName()
			         +", '" +currentWishListItem.getPrice()
			         +"', '" +currentWishListItem.getDiscount()
			         +"'";
			cmdString = "Insert into WishListItems " +" Values(" +values +")";
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

	public String updateWishListItem(WishListItem currentWishListItem)
	{
		String values;
		String where;

		result = null;
		try
		{
			// Should check for empty values and not update them
			values = "Name='" +currentWishListItem.getItemName()
			         +"', Address='" +currentWishListItem.getPrice()
			         +"'";
			where = "where WishListItemID=" +currentWishListItem.getItemName()
            ;
			cmdString = "Update WishListItems " +" Set " +values +" " +where;
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
			values = currentWishListItem.getItemName();
			cmdString = "Delete from WishListItems where WishListItemID=" +values;
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

	public String getIncomeSourceSequential(List<IncomeSource> IncomeSourceResult)
	{
		IncomeSource IncomeSource;
		String IncomeSourceName, amount;
        IncomeSourceName = EOF;
        amount = EOF;

		result = null;
		try
		{
			cmdString = "Select * from IncomeSources";
			rs5 = st3.executeQuery(cmdString);
			// ResultSetMetaData md5 = rs5.getMetaData();
			while (rs5.next())
			{
                IncomeSourceName = rs5.getString("IncomeSourceName");
				amount = rs5.getString("Amount");
				IncomeSource = new IncomeSource(IncomeSourceName, Double.parseDouble(amount));
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

	public ArrayList<IncomeSource> getIncomeSourceRandom(IncomeSource newIncomeSource)
	{
		IncomeSource IncomeSource;
		String IncomeSourceName, amount;
        IncomeSourceName = EOF;
		amount = EOF;
		IncomeSources = new ArrayList<IncomeSource>();
		try
		{
			cmdString = "Select * from IncomeSources where SourceName='" +newIncomeSource.getSourceName() +"'";
			rs5 = st3.executeQuery(cmdString);
			// ResultSetMetaData md5 = rs5.getMetaData();
			while (rs5.next())
			{
                IncomeSourceName = rs5.getString("IncomeSourceName");
				amount = rs5.getString("Amount");
				IncomeSource = new IncomeSource(IncomeSourceName, Double.parseDouble(amount));
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
			values =  "'" +currentIncomeSource.getSourceName()
			         +"', '" +currentIncomeSource.getAmount()
			         +"'";
			cmdString = "Insert into IncomeSources " +" Values(" +values +")";
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

	public String updateIncomeSource(IncomeSource currentIncomeSource)
	{
		String values;
		String where;

		result = null;
		try
		{
			// Should check for empty values and not update them
			values = "SourceName='" +currentIncomeSource.getSourceName()
			         +"'";
			where = "where SourceName='" +currentIncomeSource.getSourceName() +"'";
			cmdString = "Update IncomeSources " +" Set " +values +" " +where;
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

	public String deleteIncomeSource(IncomeSource currentIncomeSource)
	{
		String values;

		result = null;
		try
		{
			values = currentIncomeSource.getSourceName();
			cmdString = "Delete from IncomeSources where SourceName='" +values +"'";
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

	public String getExpenseSequential(List<Expense> ExpenseResult)
        {
            Expense Expense;
            String name, amount;
            name = EOF;
            amount = EOF;

            result = null;
            try
            {
                cmdString = "Select * from Expenses";
                rs2 = st1.executeQuery(cmdString);
                //ResultSetMetaData md2 = rs2.getMetaData();
            }
            catch (Exception e)
            {
                processSQLError(e);
            }
            try
            {
                while (rs2.next())
                {
                    name = rs2.getString("ItemName");
                    amount = rs2.getString("ItemPrice");
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
        public ArrayList<Expense> getExpenseRandom(Expense newExpense)
        {
            WishListItem WishListItem;
            String itemName, itemPrice;
            itemName = EOF;
            itemPrice = EOF;
            Expenses = new ArrayList<Expense>();
            try
            {
                cmdString = "Select * from Expenses where ItemName=" + newExpense.getName();
                rs3 = st1.executeQuery(cmdString);
                // ResultSetMetaData md2 = rs3.getMetaData();
                while (rs3.next())
                {
                    itemName = rs2.getString("ItemName");
                    itemPrice = rs2.getString("ItemPrice");
                    WishListItem = new WishListItem(itemName,Double.parseDouble(itemPrice));
                    WishListItems.add(WishListItem);
                }
                rs3.close();
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
            values = currentExpense.getName()
                    +", '" +currentExpense.getAmount()
                    +"', '" +currentExpense.getFrequency()
                    +"'";
            cmdString = "Insert into Expenses " +" Values(" +values +")";
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

    public String updateExpense(Expense currentExpense)
    {
        String values;
        String where;

        result = null;
        try
        {
            // Should check for empty values and not update them
            values = "ItemName='" +currentExpense.getName()
                    +"', ItemPrice='" +currentExpense.getAmount()
                    +"'";
            where = "where ItemName=" +currentExpense.getName()
            ;
            cmdString = "Update Expenses " +" Set " +values +" " +where;
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

    public String deleteExpense(Expense currentExpense)
    {
        String values;

        result = null;
        try
        {
            values = currentExpense.getName();
            cmdString = "Delete from Expenses where ItemName=" +values;
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