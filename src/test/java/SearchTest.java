import com.aventstack.extentreports.model.Report;
import org.testng.Reporter;
import org.testng.annotations.Test;


public class SearchTest extends BaseTest {

    /**
     * Search and add item in the shopping cart

      Test steps:
      1. Navigate to https://fentybeauty.com/
      2. Accept cookies and welcome message
      3. Click on search button from the header navigation menu
      4. Type in the item name and press enter
      5. Click on Royal Icing from the displayed search result list
      6. Click on Rosé Rave from the Swatch selector list
      7. Click add to cart

      Expected results:
      4. Verify that the user is on a search result page and the title is "SEARCH RESULTS FOR "HIGHLIGHTER DIAMOND"
      5. Verify that the user is navigated to the Royal Icing products page URL
      6. Verify that Rosé Rave shade name in displayed in the shade selector dropdown, and that the user is on Rosé Rave products page URL
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

            SearchPage searchPage = basePage.enterTextInSearchInputField(StringsPage.DIAMOND);

            log.info("Verify that the title is SEARCH RESULTS FOR HIGHLIGHTER DIAMOND");
            Reporter.log("Verify that the tittle is SEARCH RESULTS FOR HIGHLIGHTER DIAMOND");
            assert searchPage.searchResults.getText().contains(StringsPage.DIAMOND) : "Error. The tittle text is incorrect";
            assert isCurrentURLEqualTo(StringsPage.SEARCH_PAGE_URL) : "Error. The user is not on search page";

            sleep();
            ProductsPage productsPage = searchPage.clickOnRoyalIcing(StringsPage.ROYAL_ICING);
            assert isCurrentURLEqualTo(StringsPage.ROYAL_ICING_PAGE_URL) : "Error. The user is not on the Royal Icing URL page";

            productsPage.clickOnRoseRave(StringsPage.ROSE_RAVE);
            sleep();
            assert productsPage.shadeSelectorDropDown.getText().contains(StringsPage.ROSE_RAVE): "Error. Displayed name should be Rosé Rave, but is NOT";
            assert isCurrentURLEqualTo(StringsPage.ROSE_RAVE_PAGE_URL):"Error. The user is not on the Rosé Rave URL page";

            productsPage
                    .clickOnAddToCart();

            sleep();
            log.info("Verify that number 1 is displayed in the shopping cart badge");
            Reporter.log("Verify that number 1 is displayed in the shopping cart badge");
            assert productsPage.shoppingCartBadgeNumber.getText().contains("1") : "Error. Number 1 is not displayed on the shopping cart badge";

        } finally {

        }
        driver.quit();
    }
}


