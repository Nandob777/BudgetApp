package comp3350.budgetapp.presentation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

import comp3350.budgetapp.business.AccessWishListItems;
import comp3350.budgetapp.objects.WishListItem;

public class CLI  // command-line interface
{
    public static BufferedReader console;
    public static String inputLine;
    public static String[] inputTokens;

    public static WishListItem currentWishListItem;

    public static String wishListItemName;

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
        if (inputTokens[1].equalsIgnoreCase("IncomeSource"))
        {
            //processGetIncomeSource();
        }
        else if (inputTokens[1].equalsIgnoreCase("WishListItem"))
        {
            processGetWishListItem();
        }
//        else if (inputTokens[1].equalsIgnoreCase("something here"))
//        {
//            //processGetSC();
//        }
//        else if (inputTokens[1].equalsIgnoreCase("something more here"))
//        {
//            //processGetCS();
//        }
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
                    wishListItemName = currentWishListItem.getItemName();

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
                wishListItemName = currentWishListItem.getItemName();
                System.out.println(indent +currentWishListItem);
                currentWishListItem = accessWishListItems.getSequential();
            }
        }
    }

}
