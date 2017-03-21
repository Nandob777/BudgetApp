package comp3350.budgetapp.persistence;

import java.util.ArrayList;
import java.util.List;

import comp3350.budgetapp.objects.Expense;
import comp3350.budgetapp.objects.IncomeSource;
import comp3350.budgetapp.objects.WishListItem;

public interface DataAccess
{
	void open(String string);

	void close();

	String getWishListItemSequential(List<WishListItem> WishListItemResult);

	ArrayList<WishListItem> getWishListItemRandom(WishListItem WishListItem);

	String addWishListItem(WishListItem WishListItem);

	String updateWishListItem(WishListItem WishListItem);

	String deleteWishListItem(WishListItem WishListItem);

	String getIncomeSourceSequential(List<IncomeSource> IncomeSourceResult);

	ArrayList<IncomeSource> getIncomeSourceRandom(IncomeSource IncomeSource);

	String addIncomeSource(IncomeSource IncomeSource);

	String updateIncomeSource(IncomeSource IncomeSource);

	String deleteIncomeSource(IncomeSource IncomeSource);

	String getExpenseSequential(List<Expense> ExpenseResult);

	ArrayList<Expense> getExpenseRandom(Expense Expense);

	String addExpense(Expense Expense);

	String updateExpense(Expense Expense);

	String deleteExpense(Expense Expense);

}
