BudgIt is an application that allows the user to track their total expenses, total income, total cost of items
they want to purchase, and total savings. This is done by allowing the user to input various financial information
and wanted items into the application. The application then calculates the totals and

Major Changes:
--------------
Structure – Integration tests between seams were implemented in new integration packages
          - Tests were grouped based on integration, unit, and acceptance tests
          - Objects now take advantage of polymorphism (used in Calculate)

Behaviour - Viewing financial summary before opening other pages (Wishlist, Income Sources, Expenses)
            now shows totals [Iteration 2 bug]
          - Drop down menu added in Income Sources (used to pick type)
          - Button added in Wishlist (removes item from Wishlist and add to Expenses if an item was bought)

Unresolved Issues:
------------------
*************************************************************
*************************************************************

*************************************************************
*************************************************************
*************????????? ARE THERE ANY ????????? **************
*************************************************************
*************************************************************

*************************************************************
*************************************************************

CONTENTS OF SUBMISSION:
----------------------
Manifest:
    - AndroidManifeset.xml

Packages:
    - comp3350.budgetapp
        ~ application: Main, Services
        ~ business:
            AccessWishListItems - add/update/delete item from the databasee
            AccessIncomeSourceItems - add/update/delete item from the databasee
            AccessExpenseItems - add/update/delete item from the databasee
            Calculate - finds totals of financial objects
            Savings - finds total savings for user basaed on income and expenses
        ~ objects:
            FinancialObjects - parent class
            WishListItems - class inherits parent
            IncomeSource - class inherits parent
            Expense - class inherits parent
        ~ persistence:
            DataAccess - interface that uses HSQLDB
            DataAccessObject - implements DataAccess
            DataAccessFactory - used to switch between stub and real DB
        ~ presentation:
            MainActivity
            WishlistActivity
            IncomeSourceActivity
            ExpenseActivity
            SummaryActivity
            CLI - command line interface
            Messages - used for error interaction within the application
    - comp3350.budgetapp (androidTest)
*************************************************************
*************************************************************

*************************************************************
*************************************************************
*************~ ACCEPTANCE TESTS SHOULD BE HERE **************
*************************************************************
*************************************************************

*************************************************************
*************************************************************
    - comp3350.budgetapp (test)
        ~ tests (Unit Tests):
            AccessWishListItemsTest (business)
            AccessIncomeSourceTest (business)
            AccessExpenseTest (business)
            CalculateTest (business)
            SavingsTest (business)
            WishListItemTest (object)
            IncomeSourceTest (object)
            ExpenseTest (object)
            FinancialObjectsTest (object)
            DataAccessFactoryTest (persistence)
            DataAccessTest (persistence)
            DataAccessStubTest (persistence)
        ~ RunUnitTests
        ~ TesterMain - starts up main that calls stub database to test
        ~ (Integration Tests)
            BusinessPersistenceSeamTest
            DataAccessHSQLDBTest
            RunIntegrationTests
Resource Files
Gradle Scripts
