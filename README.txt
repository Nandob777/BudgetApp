BudgIt is a personal budgeting application that allows users to track their personal financial activity. It creates a financial
summary based on the user's income, expenses, wishlist items, and total savings based on their income and expenses.
This is done by allowing the user to input various financial information and wanted items into the application.

Major Changes:
--------------
Structure – Integration tests between seams were implemented in new integration package
          - Tests were grouped based on integration, unit, and acceptance tests
          - Objects now take advantage of polymorphism (used in Calculate)

Behaviour - Viewing financial summary before opening other pages (Wishlist, Income Sources, Expenses)
            now shows totals [Iteration 2 bug]
          - Drop down menu added in Income Sources (used to pick type)
          - Button added in Wishlist (removes item from Wishlist and add to Expenses if an item was bought)

Location of Database file (used to restore database if needed):
---------------------------------------------------------------
BUDGET.script found in databaseBackUp folder
    - app/databaseBackUp/BUDGET.script

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
        ~ acceptance
            AcceptanceTests
            ExpensesTest
            WishlistTest
            IncomeSourcesTest
            PurchaseHistoryTest
            FinancialSummaryTest

        RunAcceptanceTests
    - comp3350.budgetapp (test)
        ~ tests (Unit Tests):
            AccessWishListItemsTest (business)
            AccessIncomeSourceTest (business)
            AccessExpenseTest (business)
            CalculateTest (business)
            SavingsTest (business)
            BusinessTests

            WishListItemTest (object)
            IncomeSourceTest (object)
            ExpenseTest (object)
            FinancialObjectsTest (object)
            ObjectTests

            DataAccessFactoryTest (persistence)
            DataAccessTest (persistence)
            DataAccessStubTest (persistence)
            PersistenceTests
        ~ RunUnitTests
        ~ TesterMain - starts up main that calls stub database to test
        ~ (Integration Tests)
            BusinessPersistenceSeamTest
            DataAccessHSQLDBTest
            RunIntegrationTests
Resource Files
Gradle Scripts
