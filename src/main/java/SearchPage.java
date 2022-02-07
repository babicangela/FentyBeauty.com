import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import java.util.List;

public class SearchPage extends BasePage{

    @FindBy (xpath = "//*[contains(@class,'header container')]")
    WebElement searchResults;

    @FindBy (xpath = "//*[@class='f fw w1 search-results__grid']")
    WebElement searchResultList;

    public SearchPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public ProductsPage clickOnRoyalIcing(String itemName) {
        logg.info("Clicking on Royal Icing");
        Reporter.log("Clicking on Royal Icing");
        assert isElementPresent(searchResultList): "Error. Search result list not displayed on page";
        List<WebElement> elements = driver.findElements(By.xpath("//*[contains(@class,'p1') and contains(text(),'Royal')]"));
        assert elements.size()!=0 : "Array is empty";
        elements.get(0).click();
        return new ProductsPage(driver);
    }

}
