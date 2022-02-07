import org.testng.Reporter;
import org.testng.annotations.Test;


public class E2ETest extends BaseTest {


    /**
     * End to end test

     This is the basic E2E test, simulating the real user's experience, from navigating to Home Page over to Check Out Page,
      while executing and validating different tasks in between.

      Test steps:
     1. Navigate to https://fentybeauty.com/
     2. Accept cookies, and welcome message
     3. Click on "Account Page Icon" from header navigation menu
     4. Enter invalid email in email input field and click on password input field
     5. Delete invalid email from email input field
     6. Enter valid email in email input field and click sign in
     7. Enter valid password in password input field and sign in
     8. Click on the Shop page link from the header navigation menu
     9. Click on "Show Filters" button
     10. Click on "Matte" checkboxes and apply filters
     11. Click "Hide" filters
     12. Click on "PRO FILT'R SOFT MATTE LONGWEAR FOUNDATION #185"
     13. Change shade number from #185 to #300 by choosing shade number #300 from the Filter Shade Range
     14. Click "Add to cart" button
     15. Click on Shopping cart icon from the header navigation menu than click on "View Bag" button from the shopping cart "Header Overview Wrapper"
     16. Click on "Checkout" button

     * Expected results:
      3. Verify that the user is on Fenty Beauty Signin Page
      4. Verify that error message "*email is not valid" is displayed
      6. Verify that the error message "*password must be more than 5 characters" is displayed
      7. Verify that the user us on user account page URL.
      7. Verify that the users first name is displayed on the user account page
      8. The user is navigated to the Shop page. Verify that the number of items before filtering is 115 items.
      9. Verify that the "Product Filter Body" with options to choose from is displayed on the left side of the Shop page
      11. Verify that the number of items in Shop page after applying filters is 150 items.
      12. The user is navigated to the "PRO FILT'R SOFT MATTE LONGWEAR FOUNDATION #185" products page
      12. Verify the URL and that the shade number #185 is displayed on page
      13. Verify that the user has navigated from "PRO FILT'R SOFT MATTE LONGWEAR FOUNDATION #185" to "PRO FILT'R SOFT MATTE LONGWEAR FOUNDATION #300" products page URL
      13. Verify that shade number #300 is displayed
      14. Verify that number 1 is displayed in the "Shopping cart badge"
      15. The User has navigated to the Shopping cart page. Verify the Shopping cart page URL
      16. Verify that the user is navigated to the Checkout page URL.
     */


    @Test
    public void EndToEnd() {
        driver = openChromeDriver();
        driver.manage().window().setSize(dimension);

        try {
            BasePage basePage = new BasePage(driver);

            basePage
                    .clickOnCookiesAcceptButton()
                    .clickAcceptWelcomeMessage();

            SignInPage signInPage = basePage.clickOnAccountPageIcon();
            assert isCurrentURLEqualTo(StringsPage.SIGNIN_PAGE_URL) : "Error. The user is not on Sign in page";

            signInPage
                    .enterTextInEmailInputField(StringsPage.INVALID_EMAIL)
                    .passwordInputField.click();
            Reporter.log("Verify that '" + StringsPage.INVALID_EMAIL_LOGIN_MESSAGE + "' message is displayed", true);
            assert signInPage.invalidEmailErrorMessage.getText().contains(StringsPage.INVALID_EMAIL_LOGIN_MESSAGE) : "Error. Error message not displayed";

            Reporter.log("Delete invalid email from email input field");
            signInPage.emailInputField.clear();

            signInPage
                    .enterTextInEmailInputField(StringsPage.VALID_EMAIL)
                    .clickOnSignInButtonAndExpectError();

            Reporter.log("Verify that '" + StringsPage.EMPTY_PASSWORD_MESSAGE + "' message is displayed", true);
            assert isElementPresent(signInPage.emptyPasswordFieldErrorMessage) : "Empty password field error message should be displayed but is NOT";
            assert signInPage.emptyPasswordFieldErrorMessage.getText().contains(StringsPage.EMPTY_PASSWORD_MESSAGE);


            signInPage.enterTextInPasswordInputField(StringsPage.VALID_PASSWORD);
            UserAccountPage userAccountPage = signInPage.clickOnSignInButtonSuccess();
            assert isCurrentURLEqualTo(StringsPage.USER_ACCOUNT_PAGE_URL) : " Error. The user is not on User account page URL";

            Reporter.log("Verify that the users name is displayed on the user account page",true);
            assert userAccountPage.customerFirstName.getText().equals("Andjela") : "Error. Customers first name not present";

            ShopPage shopPage = userAccountPage.clickOnShopButtonLInk();
            Reporter.log("Verify that the number of items before filtering is 115 items", true);
            assert shopPage.shopPageItemNumber.getText().equals("115 ITEMS") : "Error. Shop page item number should be 115, but is not";

            shopPage.clickOnShowFiltersButton();
            assert isElementPresent(shopPage.productFilterBody) : "Error. Product Filter Body not present";

            shopPage.clickOnMatteFilterCheckBox();
            shopPage.clickOnApplyFiltersButton();
            shopPage.clickOnHideFilterButton();
            sleep();

            Reporter.log("Verify that the number of items after filtering is 150 items", true);
            assert shopPage.shopPageItemNumber.getText().equals("150 ITEMS") : "Error. Shop page item number should be 100, but is not";

            ProductsPage productsPage = shopPage.clickOnProFiltrSoftMatte185();

            sleep();
            assert isCurrentURLEqualTo(StringsPage.PRO_FILTR185_PRODUCTS_URL) : "Error. The URL is incorrect.";
            Reporter.log("Verify that the PRO FILT'R shade number #185 is displayed on page", true);
            assert productsPage.shadeNumber.getText().equals("#185") : "Shade number #185 not displayed on page";

            Reporter.log("Change shade number from #185 to #300 by choosing shade number #300 from the Filter Shade Range");
            productsPage.choseDifferentShade();
            sleep();

            assert isCurrentURLEqualTo(StringsPage.PRO_FILTR300_PRODUCTS_URL) : "Error. The URL is incorrect";
            Reporter.log("Verify that the shade number #300 is displayed on the page", true);
            assert productsPage.shadeNumber.getText().equals("#300") : "Shade number #300 not displayed on page";

            productsPage.clickOnAddToCart();
            sleep();
            Reporter.log("Verify that number 1 is displayed in the shopping cart badge", true);
            assert productsPage.shoppingCartBadgeNumber.getText().contains("1") : "Error. Number 1 not displayed on shopping cart badge";

            ShoppingCartPage shoppingCartPage = productsPage.clickOnShoppingCartHeaderButton().clickOnViewBagButton();
//          sleep();
            assert isCurrentURLEqualTo(StringsPage.SHOPPING_CART_PAGE_URL) : "Error, the user is not on the Shopping Cart Page URL";

            sleep();
            CheckoutPage checkoutPage = shoppingCartPage.clickOnCheckoutButton();
            Reporter.log("Verify that the User is navigated to the checkout page URL", true);
            assert isCurrentURLEqualTo(StringsPage.CHECKOUT_PAGE_URL) : "Error. The user is not on Checkout page URL";

        } finally {

        }
        driver.quit();
    }

}



