import org.testng.Reporter;
import org.testng.annotations.Test;


public class ShadeFinderTest extends BaseTest{

    /**
     Test steps:
      1. Navigate to https://fentybeauty.com/
      2. Accept cookies and welcome message
      3. Click on SHADE FINDER page link from header navigation menu
      4. Click on find my shade button
      5. Click on the MEDIUM/LEFT photo(the 5th photo in a row)
      6. Click on BOTH button
      7. Click on RARELY BURNS AND/OR TANS EASILY BUTTON
      8. Click on PURPLE button
      9. Click on GOLDEN OLIVE button
      10. Click on the #300 Warm Golden(the 2nd photo in the row)

     Expected results:
      1. The 'Accept cookies' and 'Welcome message' are displayed on screen
      2. 'Accept cookies' and 'Welcome message' are no longer displayed on screen
      3. The user is navigated to the Shade finder URL page, verify that 'FIND MY SHADE' button is present on page
      4. Verify that 'SoftMate255' (5th photo in the displayed row) is displayed in the 'SELECT THE MODEL CLOSEST TO YOUR SKIN TONE TO FIND YOUR SHADE RANGE' options list
      5. Verify that 'WHAT TONE OF JEWELRY DO YOU LOOK BEST IN' selection list is displayed on page
      6. Verify that 'RARELY BURNS AND/OR TANS EASILY' button is displayed in the 'HOW DOES YOUR SKIN REACT TO THE SUN' selection list
      7. Verify that 'PURPLE' button is displayed in the 'HOW DO THE VEINS ON YOUR INNER WRIST APPEAR' selection list
      8. Verify that 'GOLDEN OLIVE' button is displayed in the 'HOW DOES YOUR SKIN APPEAR IN ITS MOST NATURAL STATE' selection list
      9. Verify that '#300 WARM GOLDEN' photo is in the middle of the 'CHOOSE THE MODEL WHOSE SKIN TONE IS CLOSEST TO YOURS' selection list
      10. Verify that the PRO FILT’R displayed shade number result is #300
      10. Verify the Shade Finder search results page URL
     */


    @Test
    public void FindYourShade() {
        driver = openChromeDriver();
        try {

            BasePage basePage = new BasePage(driver);
            basePage
                    .clickOnCookiesAcceptButton()
                    .clickAcceptWelcomeMessage();
            ShadeFinderPage shadeFinderPage = basePage.clickOnShadeFinderLinkPage();

            shadeFinderPage
                    .clickOnFindMyShadeButton()
                    .clickOnSoftMatte255()
                    .clickOnBoth()
                    .clickOnRarelyBurns()
                    .clickOnPurple()
                    .clickOnGoldenOlive();
            sleep();
            shadeFinderPage.clickOnWarmGolden300();

            Reporter.log("Verify that the PRO FILT’R displayed shade number result is #300",true);
            assert shadeFinderPage.proFiltr300result.getText().contains("300"): "Error. Shade number #300 should be displayed on page, but instead" +shadeFinderPage.proFiltr300result.getText() + " is displayed";
            assert isCurrentURLEqualTo(StringsPage.SHADE_FINDER_RESUTLS_URL) : "Error, the user should be on " + StringsPage.SHADE_FINDER_RESUTLS_URL + " but is on " + driver.getCurrentUrl() + " instead";


        } finally {

        }
        driver.quit();
    }

}
