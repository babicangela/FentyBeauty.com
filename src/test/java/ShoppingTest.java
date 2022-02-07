import org.testng.Reporter;
import org.testng.annotations.*;


public class ShoppingTest extends BaseTest {


    /**
     * Add and remove item from shopping cart page

      Test steps:
      1. Navigate to https://fentybeauty.com/, accept cookies and welcome message
      2. Click on Shop page link from the header navigation menu
      3. Add to cart "Glossy Posse Bomb Collection"
      4. Add to cart "Match Stix Contour Skinstick:Lunar New Year Edition"
      5. Click on Shopping cart button from the header navigation menu, and then click on View button in the shopping cart overview
      6. Click on remove button

      Expected results:
      2. Verify that the shopping cart is empty
      3. Verify that the number 1 is displayed in the shopping cart badge
      4. Verify that the number 2 is displayed in the shopping cart badge
      5. Verify that the user is navigated to the shopping cart page URL and that the number of items displayed in the shopping cart is 2 items
      6. Verify that the number of items left in the shopping cart is 1 item
      6. Verify that number 1 is displayed on the shopping cart badge
     */

//    TODO NE RADI TEST SREDI

    @Test(priority = 1)
    public void addAndRemoveItemsFromShoppingCartPage() {
        driver = openChromeDriver();

        try {
            BasePage basePage = new BasePage(driver);
            basePage
                    .clickOnCookiesAcceptButton()
                    .clickAcceptWelcomeMessage();

            ShopPage shopPage = basePage.clickOnShopButtonLInk();
            Reporter.log("Verify that the shopping cart is empty",true);
            assert shopPage.getNumberOfItemsInShoppingCartFromBadge().equals("0");

            shopPage
                    .clickAddToCartGlossyPosseBombCollection();

            sleep();
            Reporter.log("Verify that the number 1 is displayed in the shopping cart badge",true);
            assert shopPage.getNumberOfItemsInShoppingCartFromBadge().equals("1");

            shopPage.waitForElement(shopPage.matchStixContourSkinstick);
            shopPage.clickAddToCartMatchStixContourSkindStick();

            sleep();
            Reporter.log("Verify that the number 2 is displayed in the shopping cart badge", true);
            assert shopPage.getNumberOfItemsInShoppingCartFromBadge().equals("2");

            sleep();
            ShoppingCartPage shoppingCartPage = shopPage.clickOnShoppingCartHeaderButton().clickOnViewBagButton();

            assert isCurrentURLEqualTo(StringsPage.SHOPPING_CART_PAGE_URL) : "Error. The user is not on shopping cart page URL";
            Reporter.log("Verify that the number of items displayed in the shopping cart is 2 items", true);
            assert shoppingCartPage.getNumberOfItemsInShoppingCartFromShoppingCart().equals("2 items");

//          sleep();
            shoppingCartPage.clickOnRemoveItemFromCart();
            sleep();

            Reporter.log("Verify that the number of items left in the shopping cart is 1 item",true);
            assert shoppingCartPage.getNumberOfItemsInShoppingCartFromShoppingCart().equals("1 items");
            Reporter.log("Verify that number 1 is displayed on the shopping cart badge", true);
            assert shoppingCartPage.shoppingCartBadgeNumber.getText().equals("1");

        } finally {

            driver.quit();
        }
    }


    /** Filter, Add and Increase The Item Number, and proceed to Checkout
     *
     Test steps:
     1. Navigate to https://fentybeauty.com/, accept cookies and welcome message
     2. Click on Shop page link from the header navigation menu
     3. Click on Show filters button
     4. Click on Natural checkbox
     5. Click on Liquid checkbox
     6. Click Apply filters button
     7. Click Hide filters button
     8. Click on "Eaze Drop Blurring Skin Tint 11"
     9. Click Add to cart
     10. Click on Shopping cart button from the header navigation menu, and then click on View button in the shopping cart overview
     11. Click on the + button
     12. Click on the Checkout button

     Expected results:
     2. Verify that the number of items on Shop page before clicking on apply button is 111
     6. Verify that the number of items on Shop page after clicking on apply button is 25
     8. The user is navigated to the "Eaze Drop Blurring Skin Tint 11" products page. Verify the URL.
     9. Verify that the number 1 is displayed on the shopping bag badge
     10. The user is navigated to the Shopping cart page. Verify the URL
     10. Verify that the number of items in te shopping cart is 1items
     11. Verify that the number of items in shopping cart after clicking on + button is 2items
     12. Verify that the user is on the Checkout page URL, and  Fenty Beauty logo is displayed on page

     */


    @Test(priority = 2)
    public void filterAndAddItemThenIncreaseTheNumberInTheShoppingCart(){
        driver = openChromeDriver();

        try {
            BasePage basePage = new BasePage(driver);
            basePage
                    .clickOnCookiesAcceptButton()
                    .clickAcceptWelcomeMessage();
            ShopPage shopPage = basePage.clickOnShopButtonLInk();
            shopPage
                    .clickOnShowFiltersButton();
            Reporter.log("Verify that the number of items on Shop page before clicking on apply button is 115", true);
            assert shopPage.shopPageItemNumber.getText().contains("115"): "Error. Shop page items number should be 115 but is not";

            shopPage
                    .clickOnNaturalFilterCheckBox()
                    .clickOnLiquidFilterCheckBox()
                    .clickOnApplyFiltersButton()
                    .clickOnHideFilterButton();

            Reporter.log("Verify that the number of items on Shop page after applying selected filters is 75",true);
            sleep();
            assert shopPage.shopPageItemNumber.getText().contains("75"): "Error. Shop page items number should be 75 but is not";


            sleep();
            ProductsPage productsPage = shopPage.clickOnEazeDropBlurringSkinTint11();

            sleep();
            Reporter.log("Verify that the user is navigated to the Eaze Drop Blurring Skin Tint 11 page URL", true);
            assert isCurrentURLEqualTo(StringsPage.PRODUCTS_PAGE_URL):"Error. The user is NOT on Products page URL";

            productsPage
                    .clickOnAddToCart();

            sleep();

            Reporter.log("Verify that the number 1 is displayed on the shopping bag badge after clicking add to cart", true);
            assert isElementPresent(shopPage.shoppingCartBadgeNumber): "Error, shopping cart badge number NOT displayed";
            assert shopPage.shoppingCartBadgeNumber.getText().contains("1");

            sleep();
            ShoppingCartPage shoppingCartPage = shopPage.clickOnShoppingCartHeaderButton().clickOnViewBagButton();

            sleep();
            assert isCurrentURLEqualTo(StringsPage.SHOPPING_CART_PAGE_URL):"Error. The user is NOT on Shopping cart Page URL";

            Reporter.log("Verify that the number of items in the shopping cart is 1 items",true);
            assert shoppingCartPage.isShoppingCartItemsNumberPresent():"Error. The number of items in shopping cart NOT displayed";
            assert shoppingCartPage.shoppingCartItemNumber.getText().contains("1 items");

            shoppingCartPage
                    .clickOnAddMoreButton();
            sleep();
            Reporter.log("Verify that the number of items in shopping cart after clicking add more button is 2 items", true);
            assert shoppingCartPage.shoppingCartItemNumber.getText().contains("2 items");

            CheckoutPage checkoutPage = shoppingCartPage.clickOnCheckoutButton();

            assert isCurrentURLEqualTo(StringsPage.CHECKOUT_PAGE_URL):"Error. The user is NOT on Checkout page URL";
            Reporter.log("Verify that Fenty Beauty logo is displayed on page", true);
            assert isElementPresent(checkoutPage.fentyBeautyLogo):"Error. Fenty Beauty Logo NOT displayed on page";

        }finally{

        }  driver.quit();
    }
}

