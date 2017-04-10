package comp3350.budgetapp.presentation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

import comp3350.budgetapp.business.AccessExpenses;
import comp3350.budgetapp.business.AccessIncomeSource;
import comp3350.budgetapp.business.AccessWishListItems;
import comp3350.budgetapp.objects.Expense;
import comp3350.budgetapp.objects.IncomeSource;
import comp3350.budgetapp.objects.WishListItem;

public class CLI  // command-line interface
{
    public static BufferedReader console;
    public static String inputLine;
    public static String[] inputTokens;

    public static WishListItem currentWishListItem;
    public static Expense currentExpense;
    public static IncomeSource currentIncomeSource;

    public static String wishListItemName;
    public static String expenseName;
    public static String incomeSourceName;

    public static String indent = "  ";

    public static void run()
    {
        try
        {
            console = new BufferedReader(new InputStreamReader(System.in));
            process();
            console.close();
        }
        catch (IOException ioe)
        {
            System.out.println(ioe.getMessage());
            ioe.printStackTrace();
        }
    }

    public static void process()
    {
        readLine();
        while ((inputLine != null) && (!inputLine.equalsIgnoreCase("exit"))
                && (!inputLine.equalsIgnoreCase("quit"))
                && (!inputLine.equalsIgnoreCase("q"))
                && (!inputLine.equalsIgnoreCase("bye")))
        {	// use cntl-c or exit to exit
            inputTokens = inputLine.split("\\s+");
            parse();
            readLine();
        }
    }

    public static void readLine()
    {
        try
        {
            System.out.print(">");
            inputLine = console.readLine();
        }
        catch (IOException ioe)
        {
            System.out.println(ioe.getMessage());
            ioe.printStackTrace();
        }
    }

    public static void parse()
    {
        if (inputTokens[0].equalsIgnoreCase("get"))
        {
            processGet();
        }
        else
        {
            System.out.println("Invalid command.");
        }
    }

    public static void processGet()
    {
        if (inputTokens[1].equalsIgnoreCase("Expense"))
        {
            processGetExpense();
        }
        else if (inputTokens[1].equalsIgnoreCase("WishListItem"))
        {
            processGetWishListItem();
        }
        else if (inputTokens[1].equalsIgnoreCase("IncomeSource"))
        {
            processGetIncomeSource();
        }
        else
        {
            System.out.println("Invalid data type");
        }
    }


    public static void processGetWishListItem()
    {
        AccessWishListItems accessWishListItems;

        accessWishListItems = new AccessWishListItems();

        if (inputTokens.length > 2)
        {
            if (inputTokens[2].equalsIgnoreCase("orphan"))
            {
                currentWishListItem = accessWishListItems.getSequential();
                while (currentWishListItem != null)
                {
                    wishListItemName = currentWishListItem.getName();

                    currentWishListItem = accessWishListItems.getSequential();
                }
            }
            else
            {
                wishListItemName = (inputTokens[2]).toUpperCase(Locale.getDefault());
                currentWishListItem = accessWishListItems.getRandom(wishListItemName);
                System.out.println(indent +currentWishListItem);
            }
        }
        else
        {
            currentWishListItem = accessWishListItems.getSequential();
            while (currentWishListItem != null)
            {
                wishListItemName = currentWishListItem.getName();
                System.out.println(indent +currentWishListItem);
                currentWishListItem = accessWishListItems.getSequential();
            }
        }
    }

    public static void processGetExpense()
    {
        AccessExpenses accessExpenses;

        accessExpenses = new AccessExpenses();

        if (inputTokens.length > 2)
        {
            if (inputTokens[2].equalsIgnoreCase("orphan"))
            {
                currentExpense= accessExpenses.getSequential();
                while (currentExpense != null)
                {
                    expenseName = currentExpense.getName();

                    currentExpense = accessExpenses.getSequential();
                }
            }
            else
            {
                expenseName = (inputTokens[2]).toUpperCase(Locale.getDefault());
                currentExpense = accessExpenses.getRandom(expenseName);
                System.out.println(indent +currentExpense);
            }
        }
        else
        {
            currentExpense = accessExpenses.getSequential();
            while (currentExpense != null)
            {
                expenseName = currentExpense.getName();
                System.out.println(indent +currentExpense);
                currentExpense = accessExpenses.getSequential();
            }
        }
    }

    public static void processGetIncomeSource()
    {
        AccessIncomeSource accessIncomeSource;

        accessIncomeSource = new AccessIncomeSource();

        if (inputTokens.length > 2)
        {
            if (inputTokens[2].equalsIgnoreCase("orphan"))
            {
                currentIncomeSource= accessIncomeSource.getSequential();
                while (currentIncomeSource != null)
                {
                    incomeSourceName = currentIncomeSource.getName();

                    currentIncomeSource = accessIncomeSource.getSequential();
                }
            }
            else
            {
                incomeSourceName = (inputTokens[2]).toUpperCase(Locale.getDefault());
                currentIncomeSource = accessIncomeSource.getRandom(incomeSourceName);
                System.out.println(indent +currentIncomeSource);
            }
        }
        else
        {
            currentIncomeSource = accessIncomeSource.getSequential();
            while (currentIncomeSource != null)
            {
                incomeSourceName = currentIncomeSource.getName();
                System.out.println(indent +currentIncomeSource);
                currentIncomeSource = accessIncomeSource.getSequential();
            }
        }
    }

}
