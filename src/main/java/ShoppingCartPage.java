import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartPage extends BasePage {


    @FindBy ( xpath = "//*[@class='header-cart__remove-confirm-text h4 bold uppercase']" )
    WebElement removeFromCartMessageContainer;

    @FindBy ( xpath = "//*[contains(@class,'') and contains(text(),'Yes')]" )
    WebElement removeFromCartYesButton;

    @FindBy ( xpath = "//*[contains(@class,'cart-grid') and contains(text(),'items')]" )
    WebElement shoppingCartItemNumber;

    @FindBy (xpath = "//*[@id='main']//section/div[1]//button[2]")
    WebElement addMoreButton;

    @FindBy ( xpath = "//*[@id='main']//div[4]/a" )
    WebElement removeItemFromCart;

    @FindBy ( xpath = "//*[@id='main']//section/div[2]//div[3]/button[1]" )
    WebElement checkoutButton;



    // Konstruktor
    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public ShoppingCartPage clickOnRemoveItemFromCart() {
        assert isElementPresent(removeItemFromCart): "Error. Remove button not present on page";
        logg.info("Clicking on Remove from cart");
        Reporter.log("Clicking on Remove from cart");
        removeItemFromCart.click();
        assert isElementPresent(removeFromCartMessageContainer): "Error. Remove from cart message container not displayed";
        logg.info("Clicking on YES button");
        Reporter.log("Clicking on YES button");
        removeFromCartYesButton.click();
        return this;
    }

    public ShoppingCartPage clickOnAddMoreButton(){
        assert isElementPresent(addMoreButton):"Error. Add more button not present on page";;
        logg.debug("clicking on Add more");
        Reporter.log("Clicking on Add more");
        addMoreButton.click();
        return this;
    }

    public boolean isShoppingCartItemsNumberPresent() {
        logg.debug("Is Shopping Cart Items Number present");
        Reporter.log("Is Shopping Cart Items Number present");
        return shoppingCartItemNumber.isDisplayed();
    }

    public String getNumberOfItemsInShoppingCartFromShoppingCart() {
        logg.info("Get number of items from shopping cart badge ");
        Reporter.log("Get number of items from shopping cart badge");
        return shoppingCartItemNumber.getText();
    }

    public CheckoutPage clickOnCheckoutButton() {
        assert isElementPresent(checkoutButton):"Error. Checkout button not present on page";
        logg.info("Clicking on Checkout button");
        Reporter.log("Clicking on Checkout button");
        checkoutButton.click();
        return new CheckoutPage(driver);
    }


}



