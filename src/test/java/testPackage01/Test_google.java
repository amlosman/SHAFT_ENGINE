package testPackage01;

import com.shaft.gui.browser.BrowserFactory;
import com.shaft.tools.io.ExcelFileManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import poms.GoogleSearch;
import poms.GoogleSearchResults;

public class Test_google {
    // Declaring webdriver and excelreader instances
    WebDriver driver;
    ExcelFileManager testDataReader;
    // Declaring Page Objects that will be used throughout the test
    GoogleSearch searchObject;
    GoogleSearchResults resultsObject;

    @Test(description = "TC001 - Navigate to URL and Verify page title")
    public void navigateToURLandVerifyPageTitle() {
        searchObject = new GoogleSearch(driver); // initialize a new instance of the page
        searchObject.navigateToURL(); // Navigate to Page URL
        searchObject.assertPageIsOpen(); // Check that the correct page has been opened
        searchObject.verifyPageTitle(testDataReader.getCellData("Expected Page Title")); // Check that page title is
    }

    @Test(priority = 1, description = "TC002 - Search for Query and Assert that the number of results is displayed")
    public void searchForQueryandAssertResultsNumDisplayed() {
        searchObject = new GoogleSearch(driver); // initialize a new instance of the page
        // temp steps for debugging changes to the type function
        searchObject.searchForQuery(testDataReader.getCellData("Search Query 2")); // Perform search for the query that
        // is
        // retrieved from test data
        resultsObject = new GoogleSearchResults(driver); // initialize a new instance of the page
        resultsObject.assertResultsStatsExistsAndIsNotEmpty(); // Check that search results counter holds a value
        // (expected to pass)
        resultsObject.verifyResultsStatsExists(); // Check that search results counter exists (expected to pass)
        // resultsObject.verifyResultsStatsDoesNotExist(); // expected to fail
    }

    // @Test(priority = 2, description = "TC003 - Clicks the next button thrice to
    // make sure that the framework can scroll element into view before clicking
    // it", enabled = true)
    public void clickNextThrice() {
        resultsObject = new GoogleSearchResults(driver); // initialize a new instance of the page
        resultsObject.clickNext(); // Clicks the next button to make sure that the framework can scroll element
        // into
        // view before clicking it
        resultsObject.clickNext(); // Clicks the next button to make sure that the framework can scroll element
        // into
        // view before clicking it
        resultsObject.clickNext(); // Clicks the next button to make sure that the framework can scroll element
        // into
        // view before clicking it

        // resultsObject.assert10ResultsPerPage();
    }

    @BeforeClass // Set-up method, to be run once before the first test
    public void beforeClass() {
        System.setProperty("testDataFilePath", "src/test/resources/TestDataFiles/testSuite01/TestData.xlsx");
        testDataReader = new ExcelFileManager(System.getProperty("testDataFilePath"));
        driver = BrowserFactory.getBrowser();
        driver = BrowserFactory.getBrowser(BrowserFactory.BrowserType.GOOGLE_CHROME);
        driver = BrowserFactory.getBrowser(BrowserFactory.BrowserType.GOOGLE_CHROME, new ChromeOptions().addArguments("--start-maximized").setHeadless(true));
        // BrowserActions.setWindowSize(driver, 3840, 2160);
    }
}