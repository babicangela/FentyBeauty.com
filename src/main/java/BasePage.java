import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;


import java.io.File;

    public class BasePage {
        WebDriver driver = null;
        Logger logg = LogManager.getLogger(BasePage.class);


        @FindBy ( id = "onetrust-accept-btn-handler" )
        WebElement cookiesAcceptButton;

        @FindBy ( xpath = "//input[@class='backToShop glDefaultBtn']" )
        WebElement welcomeMessageAcceptButton;

        @FindBy ( xpath = "//a[contains(@class,'account n1')]" )
        WebElement accountPageIcon;

        @FindBy (xpath = " //a[contains(@class,'icon-btn--search')]")
        WebElement searchButton;

        @FindBy (xpath = "//*[contains(@class,'n2 js-search-bar')]")
        WebElement searchInputField;

        @FindBy ( xpath = "//button[@class='header-cart-icon js-focus-return h5']" )
        WebElement shoppingCartHeaderButton;

        @FindBy ( className = "header-cart-icon__count" )
        WebElement shoppingCartBadgeNumber;

        @FindBy (xpath = "//*[@id='header']/span//li[1]/a")
        WebElement shopPageLink;

        @FindBy ( xpath = "//*[@class='header-cart__wrapper mha']" )
        WebElement headerCartOverviewWrapper;

        @FindBy ( xpath = "//a[@href='/cart']" )
        WebElement viewBagButton;

        @FindBy (xpath = "//*[@id='header']/span//div[2]/ul/li[4]")
        WebElement shadeFinderPageLink;

        @FindBy (xpath = "//header//div[3]//li[7]")
        WebElement flagButton;

        @FindBy (xpath = "//*[@id='globale_csc_popup']")
        WebElement changeShippingCountryPopUp;

        @FindBy (xpath = "//*[@id='gle_selectedCountry']")
        WebElement changeShippingCountry;

        @FindBy (xpath = "//*[@class='glDefaultBtn saveBtn']")
        WebElement saveButton;

        @FindBy (xpath = "//img[contains(@src, 'rs')]")
        WebElement serbiaFlag;

        @FindBy (xpath = "//img[contains(@src,'us')]")
        WebElement unitedStatesFlag;


        //    Konstruktor
        public BasePage(WebDriver driver){
            this.driver = driver;
            PageFactory.initElements(driver, this);
        }


        public BasePage clickOnCookiesAcceptButton(){
            assert isElementPresent(cookiesAcceptButton): "Error. Accept cookies button not present on page";
//        print("Clicking On Cookies Accept Button");
            logg.info("Clicking on Cookies Accept button");
            Reporter.log("Clicking on Cookies Accept button");
            cookiesAcceptButton.click();
            return this;
        }

        public BasePage clickAcceptWelcomeMessage() {
            assert isElementPresent(welcomeMessageAcceptButton):"Error. Welcome message not present on page";
//        print("Clicking on Accept Welcome Message");
            logg.info("Clicking on Accept Welcome message");
            Reporter.log("Clicking on Accept Welcome Message");
//        waitForElement(welcomeMessageAcceptButton);
            welcomeMessageAcceptButton.click();
            return this;
        }

        public SignInPage clickOnAccountPageIcon(){
            assert isElementPresent(accountPageIcon):"Error. Account page link not present on page";
            logg.info("Clicking On Account page icon");
            Reporter.log("Clicking On Account page icon");
            accountPageIcon.click();
            return new SignInPage(driver);
        }

        public ShopPage clickOnShopButtonLInk(){
            assert isElementPresent(shopPageLink):"Error. Shop page link not present on page";
            logg.info("Clicking on Shop Page Link");
            Reporter.log("Clicking on Shop page link");
            shopPageLink.click();
            return new ShopPage(driver);
        }

        public BasePage clickOnSearchButon(){
            assert isElementPresent(searchButton):"Error. Search button not present on page";
            logg.info("Clicking on Search Icon");
            Reporter.log("Clicking on Search Icon");
            searchButton.click();
            return this;
        }

        public BasePage clickOnShoppingCartHeaderButton() {
            assert isElementPresent(shoppingCartHeaderButton) : "Error. Shopping cart header button not present on page";
            logg.info("Clicking On Shopping Cart Header Button");
            Reporter.log("Clicking on Shopping Cart Header button");
            shoppingCartHeaderButton.click();
            return this;
        }

        public ShoppingCartPage clickOnViewBagButton() {
            assert isElementPresent(viewBagButton) : "Error. View bag button not present on page";
            logg.info("Clicking on View Bag button");
            Reporter.log("Clicking on View Bag button");
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", viewBagButton);
            return new ShoppingCartPage(driver);
        }


        public SearchPage enterTextInSearchInputField(String text){
            assert isElementPresent(searchInputField):"Error. Search input field not present on page";
            logg.info("Entering " + StringsPage.DIAMOND + " in search input field and clicking enter");
            Reporter.log("Entering " +StringsPage.DIAMOND+ " in search input field and clicking enter");
            searchInputField.click();
            searchInputField.sendKeys(text);
            searchInputField.sendKeys(Keys.ENTER);
            return new SearchPage(driver);
        }

        public ShadeFinderPage clickOnShadeFinderLinkPage(){
            assert isElementPresent(shadeFinderPageLink):"Error. Shade Finder link page not present on page";
//        print("Clicking on Shade Finder Page link");
            logg.info("Clicking on Shade Finder page Link");
            Reporter.log("Clicking on Shade Finder page link");
            shadeFinderPageLink.click();
            return new ShadeFinderPage(driver);
        }

        public BasePage clickOnFlagIcon(){
            assert isElementPresent(flagButton):"Error. Flag icon not present on page";
//        print("Clicking on flag button");
            logg.info("Clicking on Flag Icon");
            Reporter.log("Clicking on Flag Icon");
            flagButton.click();
            assert isElementPresent(changeShippingCountry):"Error Change shipping country dropdown not present on page";
//            logg.info("Change shipping country from RS to US by clicking Change your shipping country/region and choosing United States from the dropdown selection list");
//            Reporter.log("Change shipping country from RS to US by clicking Change your shipping country/region");
//        waitForElement(changeShippingCountry);
            return this;
        }


        public BasePage clickOnSaveButton(){
            assert isElementPresent(saveButton):"Error. Save button not present on page";
//        print("Clicking on save icon from the header navigation menu");
            logg.info("Clicking on Save button");
            Reporter.log("Clicking on Save button");
            saveButton.click();
            return this;
        }


        // Print method
        public void print(String text) {
            System.out.println(text);
        }

        //     Helper metode
        public String getTitleText() {
            String titleText = driver.getTitle();
            return titleText;
        }

        public void waitForElement(WebElement element) {
            logg.info("WaitForElement");
            Reporter.log("WaitForElement");
            WebDriverWait wait = new WebDriverWait(driver, 3);
            wait.until(ExpectedConditions.visibilityOf(element));
        }

        public boolean isElementPresent(WebElement element) {
            logg.info("Is Element present");
            Reporter.log("Is Element present");
            try {
                boolean isPresent = element.isDisplayed();
                return true;
            } catch (Exception e) {
                logg.info(e.getMessage());
                Reporter.log(e.getMessage());
                logg.info("Element is not present on page");
                Reporter.log("Element is not present on page");
                return false;
            }
        }
        public BasePage() {
        }

        public boolean isCurrentURLEqualTo(String expectedUrl) {
            logg.info("Is current URL equal to ( " + expectedUrl + " ) ");
            Reporter.log("Is current URL equal to ( " + expectedUrl + " ) ");
            String currentUrl = driver.getCurrentUrl();
            logg.info("User is on " + currentUrl);
            Reporter.log("User is on" + currentUrl);
            boolean b = currentUrl.equals(expectedUrl);
            return b;
        }

        public void sleep(){
            try {
                Thread.sleep(5000);
            }
            catch (Exception e){
                print(e.getMessage());
            }
        }

        public void takeScreenshot(String screenshotName){
            logg.info("Capturing screenshot");
            Reporter.log("Capturing screenshot");
            TakesScreenshot screenshot=(TakesScreenshot)driver;
            File source = screenshot.getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(source, new File("./Screenshots/" + screenshotName + ".png"));
            } catch (Exception e) {
                logg.info("Exception while taking screenshot" + e.getMessage());
                Reporter.log("Exception while taking screenshot" + e.getMessage());
            }
        }

    }

