import org.testng.Reporter;
import org.testng.annotations.Test;


public class ShadeFinderTest extends BaseTest{

    /**
     Test steps:
      1. Navigate to https://fentybeauty.com/, accept cookies and welcome message
      2. Click on find my shade button
      3. Click on the MEDIUM/LEFT photo(the 5th photo in a row)
      4. Click on BOTH button
      5. Click on RARELY BURNS AND/OR TANS EASILY BUTTON
      6. Click on PURPLE button
      7. Click on GOLDEN OLIVE button
      8. Click on the #300 Warm Golden(the 2nd photo in the row)

     Expected results:
      2. The user is navigated to the Shade finder search result page with the results displayed on the page
      8. Verify that the PRO FILT’R displayed shade number result is #300
      8. Verify the Shade Finder search results page URL
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
            assert shadeFinderPage.proFiltr300result.getText().contains("300"): "Error. Shade number should be 300 but it's NOT";
            assert isCurrentURLEqualTo(StringsPage.SHADE_FINDER_RESUTLS_URL) : "Error. The user is not on Shade Finder results page";


        } finally {

        }
        driver.quit();
    }

}



