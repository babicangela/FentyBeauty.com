import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;
import java.io.File;
import java.io.FileOutputStream;


public class BaseTest {
    WebDriver driver = null;
    public static final Logger log = LogManager.getLogger(BaseTest.class);
    Dimension dimension = new Dimension(1600, 1300);


    public WebDriver openChromeDriver() {
        log.info("Setting up Chrome driver");
        Reporter.log("Setting up Chrome driver");
        ChromeOptions options = new ChromeOptions();
//        options.addArguments(new String[]{"--start-maximized"});
//        options.addArguments(new String[]{"--ignore-certificate-errors"});
//        options.addArguments(new String[]{"--disable-popup-blocking"});
//        options.addArguments(new String[]{"--incognito"});
        ChromeDriver driver = new ChromeDriver(options);
        log.info("Opening Chrome driver");
        Reporter.log("Opening Chrome driver");
        driver.get(StringsPage.HOME_PAGE_URL);
        log.info("Navigating to https://fentybeauty.com/");
        Reporter.log("Navigating to https://fentybeauty.com/ ");
        return driver;
    }

    @AfterMethod
        public void onTestFailure(ITestResult result) throws Exception {
        Reporter.setCurrentTestResult(result);

        File source = new File(System.getProperty("user.dir")+("./screenshots/") +result.getMethod().getMethodName()+System.currentTimeMillis()+".png");

        if (result.getStatus() == ITestResult.FAILURE) {
            Reporter.log("Test failed. Capturing screenshot", true);

            FileOutputStream screenshotStream = new FileOutputStream(source);
            screenshotStream.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
            screenshotStream.close();


            log.info("Saving screenshot of failed test in to testng report");
            Reporter.log(" <a href='"+source.getAbsolutePath()+"'> <img src='"+ source.getAbsolutePath()+"' height='200' width='300'/> </a>  ");
        }
    }

    @AfterSuite
    public void tearDown() {

        driver.quit();
    }

    public boolean isCurrentURLEqualTo(String expectedUrl) {
        log.info("isCurrentURLEqualTo ( " + expectedUrl + " )");
        Reporter.log("isCurrentURLEqualTo ( " + expectedUrl + " )");
        String currentUrl = driver.getCurrentUrl();
        log.info("User is on " + currentUrl);
        Reporter.log("User is on " + currentUrl);
        boolean b = currentUrl.equals(expectedUrl);
        return b;
    }

    public boolean isElementPresent(WebElement element) {
        log.info("isElementPresent");
        Reporter.log("isElementPresent");
        try {
            boolean isPresent;
            if (element.isDisplayed()) isPresent = true;
            else isPresent = false;
            return true;
        } catch (Exception e) {
            log.warn(e.getMessage());
            Reporter.log(e.getMessage());

            log.info("Element is not present on page");
            Reporter.log("Element is not present on page");

            return false;
        }
    }

    public void sleep() {
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            log.warn(e.getMessage());
            Reporter.log(e.getMessage());
        }
    }

    public void waitForElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

//    print method
    public void print(String text) {
        System.out.println(text);
    }
}
