import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends BasePage{

    @FindBy (xpath = "//*[@class='geCheckoutLogo']")
    WebElement fentyBeautyLogo;


    //    Konstruktor
    public CheckoutPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

}
