import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
public class ChangeCurrencyTest extends BaseTest {


    /**
     * Sign in with valid email and empty password

      Test steps:
      1. Navigate to https://fentybeauty.com/
      2. Accept cookies and welcome message
      3. Click on flag icon from the header navigation menu
      4. Change shipping country from Serbia to UNITED STATES by clicking "Change your shipping country/region" and choosing United States from the dropdown selection list
      5. Click on "SAVE" button

      Expected result:
      2. Verify that the Serbian flag is displayed on the flag icon
      3. Verify that change shipping country pop up is displayed
      5. Verify that the United States flag is displayed on the flag icon
     */


    @Test
    public void ChangeCountryAndCurrency() {
        driver = openChromeDriver();
        driver.manage().window().setSize(dimension);

        try {

            BasePage basePage = new BasePage(driver);
            basePage
                    .clickOnCookiesAcceptButton()
                    .clickAcceptWelcomeMessage();

            sleep();
            Reporter.log("Verify that the RS flag is displayed on the Flag icon", true);
            assert basePage.serbiaFlag.isDisplayed() : "Error, " + basePage.serbiaFlag + " should be displayed on the flag icon, but its NOT";

            basePage.clickOnFlagIcon();

            sleep();
            Reporter.log("Verify that the Change Shipping Country Popup is displayed", true);
            assert basePage.changeShippingCountryPopUp.isDisplayed() : "Error," + basePage.changeShippingCountryPopUp + "NOT displayed";

            Reporter.log("Changing shipping country from RS to US by clicking Change your shipping country/region and choosing US from the dropdown selection list");
            basePage.changeShippingCountry.findElement(By.xpath("//*[@id='gle_selectedCountry']/option[190]")).click();
            basePage.clickOnSaveButton();

            sleep();
            Reporter.log("Verify that the US flag is displayed on the Flag icon", true);
            assert basePage.unitedStatesFlag.isDisplayed() : "Error, " + basePage.unitedStatesFlag + " should be displayed on flag icon but is NOT";


        } finally {

        }
        driver.quit();
    }

}
