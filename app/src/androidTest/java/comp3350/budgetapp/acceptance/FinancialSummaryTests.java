package comp3350.budgetapp.acceptance;

import android.test.ActivityInstrumentationTestCase2;

import comp3350.budgetapp.presentation.MainActivity;

import junit.framework.Assert;

import com.robotium.solo.Solo;

public class FinancialSummaryTests extends ActivityInstrumentationTestCase2<MainActivity> {
    private Solo solo;

    public FinancialSummaryTests()
    {
        super(MainActivity.class);
    }

    public void setUp() throws Exception
    {
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @Override
    public void tearDown() throws Exception
    {
        solo.finishOpenedActivities();
    }

    public void testAccess() // tests whether user can access and see relevent info
    {
        solo.waitForActivity("MainActivity");
        Assert.assertTrue(solo.searchButton("Financial Summary", true)); // only searches visible buttons
        solo.clickOnButton("Financial Summary");
        solo.assertCurrentActivity("Expected activity SummaryActivity", "SummaryActivity");

        Assert.assertTrue(solo.searchText("BudgIt: Summary"));

        Assert.assertTrue(solo.searchText("Total Income this month:"));
        Assert.assertTrue(solo.searchText("Total Expenses this month:"));
        Assert.assertTrue(solo.searchText("Wishlist total cost:"));
        Assert.assertTrue(solo.searchText("Total Savings:")); // This also counts as one of our Big User Stories

        Assert.assertTrue(solo.waitForText("$",4,10000));
    }

    private void getToActivity(){
        solo.waitForActivity("MainActivity");
        solo.clickOnButton("Financial Summary");
    }

}
