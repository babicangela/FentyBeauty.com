import org.testng.Reporter;
import org.testng.annotations.Test;


public class SearchTest extends BaseTest {

    /**
     * Search and add item in the shopping cart

      Test steps:
      1. Navigate to https://fentybeauty.com/
      2. Accept cookies and welcome message
      3. Click on search icon from the header navigation menu
      4. Type in the item name and press enter
      5. Click on Royal Icing from the displayed search result list
      6. Click on Rosé Rave from the Swatch selector list
      7. Click add to cart

      Expected results:
      1. The 'Accept cookies' and 'Welcome message' are displayed on screen
      2. 'Accept cookies' and 'Welcome message' are no longer displayed on screen
      3. Verify that search input field is displayed on page
      4. Verify that the user is on a search result page and the title is "SEARCH RESULTS FOR "HIGHLIGHTER DIAMOND"
      5. Verify that the user is navigated to the Royal Icing products page URL
      6. Verify that Rosé Rave shade name in displayed in the shade selector dropdown list, and that the user is on Rosé Rave products page URL
      7. Verify that the shopping cart overview wrapper containing selected item slides out on the right side of the page
      7. Verify that the number 1 is displayed in the shopping cart badge
     */

    @Test
    public void searchAndAddItemToCart() {
        driver = openChromeDriver();

        try {
            BasePage basePage = new BasePage(driver);
            basePage
                    .clickOnCookiesAcceptButton()
                    .clickAcceptWelcomeMessage()
                    .clickOnSearchButon();
            assert isElementPresent(basePage.searchInputField): "Error. Search input field not displayed on page";

            SearchPage searchPage = basePage.enterTextInSearchInputField(StringsPage.DIAMOND);

            log.info("Verify that the title is SEARCH RESULTS FOR HIGHLIGHTER DIAMOND");
            Reporter.log("Verify that the tittle is SEARCH RESULTS FOR HIGHLIGHTER DIAMOND");
            assert searchPage.searchResults.getText().contains(StringsPage.DIAMOND) : "Error. The tittle text should be " + StringsPage.DIAMOND + " but is " + searchPage.searchResults.getText() + " instead" ;
            assert isCurrentURLEqualTo(StringsPage.SEARCH_PAGE_URL) : "Error. The user should be on " + StringsPage.SEARCH_PAGE_URL + " but is on " + driver.getCurrentUrl() + " instead ";

            sleep();
            ProductsPage productsPage = searchPage.clickOnRoyalIcing(StringsPage.ROYAL_ICING);
            assert isCurrentURLEqualTo(StringsPage.ROYAL_ICING_PAGE_URL) : "Error. The user should be on " + StringsPage.ROYAL_ICING + " but is on " + driver.getCurrentUrl() + " instead";

            productsPage.clickOnRoseRave(StringsPage.ROSE_RAVE);
            sleep();
            assert productsPage.shadeSelectorDropDown.getText().contains(StringsPage.ROSE_RAVE): "Error. Displayed shade name should be" + StringsPage.ROSE_RAVE + " but is" + productsPage.shadeSelectorDropDown.getText();
            assert isCurrentURLEqualTo(StringsPage.ROSE_RAVE_PAGE_URL):"Error. The user should be on " + StringsPage.ROSE_RAVE + " but is on " + driver.getCurrentUrl() + " instead";

            productsPage
                    .clickOnAddToCart();
            assert isElementPresent(productsPage.headerCartOverviewWrapper):"Error. Header cart overview wrapper NOT present on page";

            sleep();
            log.info("Verify that number 1 is displayed in the shopping cart badge");
            Reporter.log("Verify that number 1 is displayed in the shopping cart badge");
            assert productsPage.shoppingCartBadgeNumber.getText().contains("1") : "Error. Number 1 should be displayed on the shopping cart badge number but instead " + productsPage.shoppingCartBadgeNumber.getText() + " is displayed";

        } finally {

        }
        driver.quit();
    }
}
