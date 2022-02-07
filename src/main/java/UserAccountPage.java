import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class UserAccountPage extends BasePage {

    @FindBy (xpath = "//span[contains(@class,'customer-name') and contains(text(),'Andjela')]")
    WebElement customerFirstName;

    @FindBy ( xpath = "//*[@id='main']//header/p/a" )
    WebElement logOutButton;

//  Konstruktor
    public UserAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public BasePage clickOnLogOutButton() {
        assert isElementPresent(logOutButton):"Error. Log out button not present on page";;
        logg.info("Clicking logOutButton");
        Reporter.log("Clicking logOutButton");
        logOutButton.click();
        return new BasePage(driver);
    }

//    Metoda da li je ime korisnika prisutno
    public boolean isCustomerFirstNamePresentOnUserAccountPage(){
        logg.info("Is Customers First Name present On User Account Page");
        Reporter.log("Is Customers First Name present On User Account Page");
        return customerFirstName.isDisplayed();
    }
    // Metoda da se uhvati ime korisnika na osnovu prethodne metode
    public String getCustomersFirstName(){
        logg.info("Get Customers First Name");
        Reporter.log("Get Customers First Name");
        return customerFirstName.getText();
    }

    public void waitForElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public boolean isEElementPresent(WebElement element) {
        logg.info("isElementPresent");
        Reporter.log("isElementPresent");
        try {
            boolean isPresent = logOutButton.isDisplayed();
            return true;
        } catch (Exception e) {
            logg.info(e.getMessage());
            Reporter.log(e.getMessage());
            logg.info("Element is not present on page");
            Reporter.log("Element is not present on page");
            return false;
        }
    }}




