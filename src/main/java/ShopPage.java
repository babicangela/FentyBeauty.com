import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;


public class ShopPage extends BasePage {


    @FindBy ( xpath = "//span[contains(.,'Show Filters')]" )
    WebElement showFiltersButton;

    @FindBy ( xpath = "//span[contains(.,'Hide Filters')]" )
    WebElement hideFiltersButton;

    @FindBy ( xpath = "//span[@class='collection__header__count p1 uppercase grey-medium-dark']" )
    WebElement shopPageItemNumber;

    @FindBy ( xpath = "//div[@class='collection-product-filter__body']" )
    WebElement productFilterBody;

    @FindBy ( xpath = "//span[contains(text(),'Natural')]" )
    WebElement naturalFilterCheckbox;

    @FindBy ( xpath = "//*[@id='main']//div[2]/div[2]//li[1]//input" )
    WebElement mattelFilterCheckbox;

    @FindBy ( xpath = "//span[contains(text(),'Liquid')]" )
    WebElement liquidFilterCheckBox;

    @FindBy ( xpath = "//span[contains(text(),'Apply')]" )
    WebElement applyFiltersButton;

    @FindBy ( xpath = "//*[contains(@class,'product') and contains(text(),'185')]" )
    WebElement proFiltrSoftMatte185e;

    @FindBy ( xpath ="//*[@id='main']//div[21]/div/a[2]" )
    WebElement glossyPosseBombCollection;

    @FindBy ( xpath = "//*[@id='main']//div[10]/div/a[2]" )
    WebElement matchStixContourSkinstick;

    @FindBy ( xpath = "//*[contains(@class,'product') and contains(text(),'11')]" )
    WebElement eazeDropBlurringSkinTint11;


    //    Konstruktor
    public ShopPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public ShopPage clickOnShowFiltersButton() {
        assert isElementPresent(showFiltersButton) : "Error. Show filters not present on page";
        logg.info("Clicking on Show Filters");
        Reporter.log("Clicking on Show Filter");
        showFiltersButton.click();
        return this;
    }

    public ShopPage clickOnNaturalFilterCheckBox() {
        assert isElementPresent(naturalFilterCheckbox) : "Error. Natural filter checkbox not present on page";
        logg.info("Clicking on natural filter checkbox");
        Reporter.log("Clicking on natural filter checkbox");
        naturalFilterCheckbox.click();
        return this;
    }

    public ShopPage clickOnMatteFilterCheckBox() {
        assert isElementPresent(mattelFilterCheckbox) : "Error. Matte filter checkbox not present on page";
        logg.info("Clicking on matte filter checkbox");
        Reporter.log("Clicking on matte filter checkbox");
        mattelFilterCheckbox.click();
        return this;
    }

    public ShopPage clickOnLiquidFilterCheckBox() {
        assert isElementPresent(liquidFilterCheckBox) : "Error. Liquid filter checkbox not present on page";
        logg.info("Clicking on liquid filter checkbox");
        Reporter.log("Clicking on liquid filter checkbox");
        liquidFilterCheckBox.click();
        return this;
    }

    public ShopPage clickOnApplyFiltersButton() {
        assert isElementPresent(applyFiltersButton) : "Error. Apply filters button not present on page";
        logg.info("Clicking on apply filters button");
        Reporter.log("Clicking on apply filters button");
        applyFiltersButton.click();
        return this;
    }

    public ShopPage clickOnHideFilterButton() {
        assert isElementPresent(hideFiltersButton) : "Error. Hide filters button not present on page";
        logg.info("Clicking on Hide Filters Button");
        Reporter.log("Clicking on Hide Filters Button");
        hideFiltersButton.click();
        return this;
    }

    public ProductsPage clickOnEazeDropBlurringSkinTint11() {
        assert isElementPresent(eazeDropBlurringSkinTint11) : "Error. Eaze Drop 11 not present on page";
        logg.info("Clicking on Eaze drop blurring skin tint");
        Reporter.log("Clicking on Eaze drop blurring skin tint");
        waitForElement(eazeDropBlurringSkinTint11);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, 4);
        executor.executeScript("arguments[0].click();", eazeDropBlurringSkinTint11);
        return new ProductsPage(driver);
    }


    public ProductsPage clickOnProFiltrSoftMatte185() {
        assert isElementPresent(proFiltrSoftMatte185e) : "Error. Pro Filt'r Soft Matte 185 not present on page";
        logg.info("Clicking on Pro FIlt'r Soft Matte 185");
        Reporter.log("Clicking on Pro FIlt'r Soft Matte 185");
        proFiltrSoftMatte185e.click();
        return new ProductsPage(driver);
    }

        public ShopPage clickAddToCartGlossyPosseBombCollection() {
            assert isElementPresent(glossyPosseBombCollection) : "Error. Glossy Bomb Collection not present on page";
            logg.info("Clicking add to cart Glossy Posse Bomb Collection");
            Reporter.log("Clicking add to cart Glossy Posse Bomb Collection");
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            WebDriverWait wait = new WebDriverWait(driver, 4);
            executor.executeScript("arguments[0].click();", glossyPosseBombCollection);
            return this;
        }

        public ShopPage clickAddToCartMatchStixContourSkindStick() {
            assert isElementPresent(matchStixContourSkinstick) : "Error. Item is not on page";
            logg.info("Clicking add to cart Resting Peach Face Cream Blush");
            Reporter.log("Clicking add to cart Resting Peach Face Cream Blush");
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", matchStixContourSkinstick);
            return this;
        }


        public boolean isCartBadgeNumberPresent() {
            logg.info("Is cart badge number present");
            Reporter.log("Is cart badge number present");
            try {
                boolean isPresent = shoppingCartBadgeNumber.isDisplayed();
                return true;
            } catch (Exception e) {
                logg.info(e.getMessage());
                Reporter.log(e.getMessage());
                return false;
            }
        }

        // metoda da se uhvati broj u korpi na osnovu prethodne metode
        public String getNumberOfItemsInShoppingCartFromBadge() {
            logg.info("Getting number of items from shopping cart badge ");
            Reporter.log("Getting number of items from shopping cart badge");
            return shoppingCartBadgeNumber.getText();
        }

        public boolean isShopPageItemNumberPresent () {
            logg.info("Is shop page item number present");
            Reporter.log("Is shop page item number present");
            try {
                boolean isPresent = shopPageItemNumber.isDisplayed();
                return true;
            } catch (Exception e) {
                logg.info(e.getMessage());
                Reporter.log(e.getMessage());
                return false;
            }
        }

        public String getNumberOfItemsOnShopPage () {
            logg.info("Getting number of items on shop page");
            Reporter.log("Getting number of items on shop page");
            return shopPageItemNumber.getText();
        }

        public void waitForElement (WebElement element){
            WebDriverWait wait = new WebDriverWait(driver, 3);
            wait.until(ExpectedConditions.visibilityOf(element));
        }
    }











