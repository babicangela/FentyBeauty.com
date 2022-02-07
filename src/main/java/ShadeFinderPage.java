import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.util.List;

public class ShadeFinderPage extends BasePage {

    @FindBy ( xpath = "//*[contains(@class,'h2') and contains(text(),'SH')]" )
    WebElement shadeFinderMessage;

    @FindBy ( xpath = "//*[contains(@class,'el') and contains(text(),'Find')]" )
    WebElement findMyShadeButton;

    @FindBy ( xpath = "//div[@class='sf-landing-quiz__images']" )
    WebElement shadeCollectionList;

    @FindBy ( xpath = "//*[contains(@class, 'options')]" )
    WebElement jewelryToneCollectionList;

    @FindBy (xpath = "//*[contains(text(),'PRO FILT’R')]")
    WebElement proFiltr300result;

    @FindBy ( xpath = "//*[contains(text(),'RARE')]" )
    WebElement rarelyBurnsButton;

    @FindBy (xpath = "//*[contains(text(),'PURPLE')]")
    WebElement purpleButton;

    @FindBy (xpath = "//*[contains(text(),'GOLDEN OLIVE')]")
    WebElement goldenOliveButton;

    @FindBy ( xpath = "//*[@id='main']//div[3]/div[3]//div[1]/div[3]" )
    WebElement mediumShadePhoto;

    @FindBy ( xpath = "//*[@id='main']//div[3]/div[2]/div/div[1]" )
    WebElement warmGolden300;


    //    Konstruktor
    public ShadeFinderPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public ShadeFinderPage clickOnFindMyShadeButton() {
        assert isElementPresent(findMyShadeButton):"Error. Find my shade button not present on page";
//        print("Clicking on FIND MY SHADE");
        logg.info("Clicking on Find my shade button");
        Reporter.log("Clicking on Find my shade button");
        findMyShadeButton.click();
        return this;
    }

    public ShadeFinderPage clickOnSoftMatte255() {
        assert isElementPresent(mediumShadePhoto):"Error. Medium Shade photo not displayed";
//        print("Clicking on Soft Matte 255");
        logg.info("Clicking on Soft Matte 255");
        Reporter.log("Clicking on Soft Matte 255");
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, 4);
        executor.executeScript("arguments[0].click();", mediumShadePhoto);
        return this;
    }

    public ShadeFinderPage clickOnBoth() {
        assert isElementPresent(jewelryToneCollectionList):"Error. Element not present on page";;
//        print("Clicking on BOTH");
        logg.info("Clicking on BOTH");
        Reporter.log("Clicking on BOTH");
        List<WebElement> elements = driver.findElements(By.xpath("//*[contains(text(),'BOTH')]"));
        assert elements.size() != 0 : "Array is empty";
        elements.get(0).click();
        return this;
    }

    public ShadeFinderPage clickOnRarelyBurns() {
        assert isElementPresent(rarelyBurnsButton):"Error. Rarely burns and/or tans easily button not displayed";;
//        print("Clicking on Rarely burn and/or tans easily");
        logg.info("Clicking on Rarely burn and/or tans easily");
        Reporter.log("Clicking on Rarely burn and/or tans easily");
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, 4);
        executor.executeScript("arguments[0].click();", rarelyBurnsButton);
        return this;
    }

    public ShadeFinderPage clickOnPurple() {
        assert isElementPresent(purpleButton):"Error. Purple button not present on page";;
//        print("Clicking on PURPLE");
        logg.info("Clicking on Purple");
        Reporter.log("Clicking on Purple");
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, 4);
        executor.executeScript("arguments[0].click();", purpleButton);
        return this;
    }

    public ShadeFinderPage clickOnGoldenOlive() {
        assert isElementPresent(goldenOliveButton):"Error. GOLDEN OLIVE button not present on page";
//        print("Clicking on GOLDEN OLIVE");
        logg.info("Clicking on GOLDEN OLIVE");
        Reporter.log("Clicking on GOLDEN OLIVE");
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, 4);
        executor.executeScript("arguments[0].click();", goldenOliveButton);
        return this;
    }

    public ShadeFinderPage clickOnWarmGolden300() {
        assert isElementPresent(warmGolden300):"Error. Warm Golden #300 photo not displayed on page";;
//        print("Clicking on Warm Golden #300");
        logg.info("Clicking on  Warm Golden 300");
        Reporter.log("Clicking on Warm Golden 300");
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, 4);
        executor.executeScript("arguments[0].click();", warmGolden300);
        return this;
    }
    public String getShadeNumberResult() {
        logg.info("Getting shade number result");
        Reporter.log("Getting the shade number result");
        return proFiltr300result.getText();
    }

}



