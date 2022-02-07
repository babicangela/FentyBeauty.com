import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import java.util.List;

public class ProductsPage extends BasePage{

    @FindBy (xpath = "//*[contains(@class,'button') and contains(text(),'to')]")
    WebElement addToCartButton;

    @FindBy (xpath = "//*[contains(@aria-label, 'Swatch selector')]")
    WebElement swatchSelector;

    @FindBy (xpath = "//*[contains(@class,'dropdown-swatch__la') and contains(text(),'')]")
    WebElement shadeNumber;

    @FindBy(xpath = "//*[@aria-label='Swatch selector']")
    WebElement diamondBombShadeList;

    @FindBy(xpath = "//*[@class='dropdown-swatch__label grey-dark']")
    WebElement shadeSelectorDropDown;


    public ProductsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public ProductsPage clickOnAddToCart(){
        assert isElementPresent(addToCartButton): "Add to cart not present on page";
//        print("Clicking Add to cart ");
        logg.info("Adding item to shopping cart");
        Reporter.log("Adding " + StringsPage.ROYAL_ICING + " to shopping cart");
        addToCartButton.click();
        return this;
    }

    public ProductsPage choseDifferentShade() {
        assert isElementPresent(swatchSelector):"Error. Element not present on page";;
//        print("Clicking on shade 300");
        logg.info("Clicking on shade 300");
        Reporter.log("Clicking on shade 300");
        List<WebElement> elements = driver.findElements(By.xpath("//*[contains(@aria-label, '#300')]"));
        assert elements.size() != 0 : "Array is empty";
        elements.get(0).click();
        return this;
    }

    public String getShadeNumber() {
//        print("Getting selected shade number in the Products page");
        logg.info("Getting selected shade number in the Products page");
        Reporter.log("Getting selected shade number in the Products page");
        return shadeNumber.getText();
    }
    public ProductsPage clickOnRoseRave(String itemName) {
        logg.info("Clicking on Rose Rave");
        Reporter.log("Clicking on Rose Rave");
        assert isElementPresent(diamondBombShadeList): "Error. Search result list not displayed on page";
        List<WebElement> elements = driver.findElements(By.xpath("//*[@aria-label='Diamond Bomb All-Over Diamond Veil — Rosé Rave']"));
        assert elements.size()!=0 : "Array is empty";
        elements.get(0).click();
        return new ProductsPage(driver);
    }

}


