import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class SignInPage extends BasePage{

    @FindBy ( id = "input-email-login" )
    WebElement emailInputField;

    @FindBy ( id = "input-password-login" )
    WebElement passwordInputField;

    @FindBy ( xpath = "//button[@class='btn btn--full btn btn--primary']" )
    WebElement signInButton;

    @FindBy ( xpath = "//*[contains(@class,'f2 form') and contains(text(),'*email')]")
    WebElement invalidEmailErrorMessage;

    @FindBy ( xpath = "//*[contains(@class,'f2 form') and contains(text(),'more')]")
    WebElement emptyPasswordFieldErrorMessage;


    // Konstruktor
    public SignInPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public SignInPage enterTextInEmailInputField(String text) {
        assert isElementPresent(emailInputField):"Error. Email input field not present on page";
        logg.info("Entering text in Email Input Field");
        Reporter.log("Entering text in Email Input Field");
        emailInputField.click();
        emailInputField.sendKeys(text);
        return this;
    }

    public SignInPage enterTextInPasswordInputField(String text) {
        assert isElementPresent(passwordInputField):"Error. Password input field not present on page";
        logg.info("Entering text in Password Input field");
        Reporter.log("Entering text in Password Input field");
        passwordInputField.click();
        passwordInputField.sendKeys(text);
        return this;
    }

    public UserAccountPage clickOnSignInButtonSuccess(){
        assert isElementPresent(signInButton):"Error. Sign in button not present on page";
        logg.info("Clicking On sign in button");
        Reporter.log("Clicking On sign in button");
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, 4);
        executor.executeScript("arguments[0].click();", signInButton);
        return new UserAccountPage(driver);

    }
    public SignInPage clickOnSignInButtonAndExpectError(){
        assert isElementPresent(signInButton):"Error. Sign in button not present on page";
        logg.info("Clicking on sign in button");
        Reporter.log("Clicking on sign in button");
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, 4);
        executor.executeScript("arguments[0].click();", signInButton);
        return this;
    }

    public String getInvalidEmailErrorMessageText(){
        logg.info("Getting invalid Email Error Message Text");
        Reporter.log("Getting invalid Email Error Message Text");
        return invalidEmailErrorMessage.getText();
    }

    public boolean isInvalidEmailErrorMessagePresent(){
        logg.info("Is Invalid Email Error Message Present On Page");
        Reporter.log("Is Invalid Email Error Message Present On Page");
        return invalidEmailErrorMessage.isDisplayed();
    }


    public boolean isEmptyPasswordErrorMessagePresent(){
        logg.info("Is Empty Password Error Message present ");
        Reporter.log("Is Empty Password Error Message present ");
        return emptyPasswordFieldErrorMessage.isDisplayed();
    }

    public String getEmptyPasswordErrorMessageText(){
        logg.info("Getting Empty Password Error Message ");
        Reporter.log("Getting Empty Password Error Message ");
        return emptyPasswordFieldErrorMessage.getText();
    }
}
