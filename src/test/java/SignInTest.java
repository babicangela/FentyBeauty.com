import org.testng.Reporter;
import org.testng.annotations.Test;


public class SignInTest extends BaseTest {


/**
 * Sign in with valid credentials

  Test steps:
  1. Navigate to https://fentybeauty.com/
  2. Accept cookies and welcome message
  3. Click on account page icon from the header navigation menu
  4. Enter valid email in email input field
  5. Enter valid password in password input field
  6. Click on login button

 Expected result:
   1. The 'Accept cookies' and 'Welcome message' are displayed on screen
   2. 'Accept cookies' and 'Welcome message' are no longer displayed on screen
   3. Verify that the user is on the Signin page URL
   4. Verify that 'email input field' is displayed on page
   5. Verify that 'password input field' is displayed on page
   6. Verify that the user is signed in and on the user account page
   6. Verify that the users name is displayed on the user account page
 */

    @Test(priority = 1)
    public void SignInWithValidCredentials() {
        driver = openChromeDriver();

        try {
            BasePage basePage = new BasePage(driver);
            basePage
                    .clickOnCookiesAcceptButton()
                     .clickAcceptWelcomeMessage();

            SignInPage signInPage = basePage.clickOnAccountPageIcon();

            assert signInPage.isCurrentURLEqualTo(StringsPage.SIGNIN_PAGE_URL): "Error. The user should be on the " + StringsPage.SIGNIN_PAGE_URL + " but is on " + driver.getCurrentUrl() + " instead";
            signInPage.enterTextInEmailInputField(StringsPage.VALID_EMAIL)
                    .enterTextInPasswordInputField(StringsPage.VALID_PASSWORD);

            UserAccountPage userAccountPage = signInPage.clickOnSignInButtonSuccess();
            assert userAccountPage.isCurrentURLEqualTo(StringsPage.USER_ACCOUNT_PAGE_URL): "Error. The user should be on the " + StringsPage.USER_ACCOUNT_PAGE_URL + " but is on " + driver.getCurrentUrl() + " instead";
            Reporter.log("Verify that the users name is displayed on the user account page",true);
            assert userAccountPage.customerFirstName.getText().equals("Andjela") : "Error. Customers name 'Andjela' not displayed on page, instead " + userAccountPage.customerFirstName.getText() + " is displayed";

        } finally {

            driver.quit();
        }
    }

    /**
     * Sign in with valid credentials and log out

     Test steps:
     1. Navigate to https://fentybeauty.com/
     2. Accept cookies and welcome message
     3. Click on account page icon from the header navigation menu
     4. Enter valid email in email input field
     5. Enter valid password in password input field
     6. Click on login button
     7. Click on logout button on the user account page

     Expected result:
     1. The 'Accept cookies' and 'Welcome message' are displayed on screen
     2. 'Accept cookies' and 'Welcome message' are no longer displayed on screen
     3. Verify that the user is on the Signin page URL
     4. Verify that 'email input field' is displayed on page
     5. Verify that 'password input field' is displayed on page
     6. Verify that the user is signed in and on the user account page
     6. Verify that the users name is displayed on the user account page
     7. Verify that the user is on home page
     */

    @Test(priority = 2)
    public void SignInWithValidCredentialsAndLogOut() {
        driver = openChromeDriver();

        try {
            BasePage basePage = new BasePage(driver);
            basePage
                    .clickOnCookiesAcceptButton()
                    .clickAcceptWelcomeMessage();

            SignInPage signInPage = basePage.clickOnAccountPageIcon();
            assert signInPage.isCurrentURLEqualTo(StringsPage.SIGNIN_PAGE_URL): "Error. The user should be on the " + StringsPage.SIGNIN_PAGE_URL + " but is on " + driver.getCurrentUrl() + " instead";
            signInPage
                    .enterTextInEmailInputField(StringsPage.VALID_EMAIL)
                    .enterTextInPasswordInputField(StringsPage.VALID_PASSWORD);

            UserAccountPage userAccountPage = signInPage.clickOnSignInButtonSuccess();
            sleep();
            assert userAccountPage.isCurrentURLEqualTo(StringsPage.USER_ACCOUNT_PAGE_URL): "Error. The user should be on the " + StringsPage.USER_ACCOUNT_PAGE_URL + " but is on " + driver.getCurrentUrl() + " instead";
            Reporter.log("Verify that the users name is displayed on the user account page",true);
            assert userAccountPage.customerFirstName.getText().equals("Andjela") : "Error. Customers name 'Andjela' not displayed on page, instead " + userAccountPage.customerFirstName.getText() + " is displayed";

            userAccountPage
                    .clickOnLogOutButton();
            assert isCurrentURLEqualTo(StringsPage.HOME_PAGE_URL): "Error. The user should be on the " + StringsPage.HOME_PAGE_URL + " but is on " + driver.getCurrentUrl() + " instead";

        } finally {

            driver.quit();
        }
    }

    /**
     * Sign in with invalid email and valid password

     Test steps:
     1. Navigate to https://fentybeauty.com/
     2. Accept cookies and welcome message
     3. Click on account page icon from the header navigation menu
     4. Enter invalid email in email input field
     5. Enter valid password in password input field
     6. Click on login button

     Expected results:
     1. The 'Accept cookies' and 'Welcome message' are displayed on screen
     2. 'Accept cookies' and 'Welcome message' are no longer displayed on screen
     3. Verify that the user is on the Signin page URL
     4. Verify that 'email input field' is displayed on page
     5. Verify that 'password input field' is displayed on page
     6. Verify that the user is on sign in page and error message containing "*email is not valid" is displayed
     */

    @Test(priority = 3)
    public void SignInWithInvalidEmailAndValidPassword() {
        driver = openChromeDriver();

        try {
            BasePage basePage = new BasePage(driver);
            basePage
                    .clickOnCookiesAcceptButton()
                    .clickAcceptWelcomeMessage();

            SignInPage signInPage = basePage.clickOnAccountPageIcon();
            assert signInPage.isCurrentURLEqualTo(StringsPage.SIGNIN_PAGE_URL): "Error. The user should be on the " + StringsPage.SIGNIN_PAGE_URL + " but is on " + driver.getCurrentUrl() + " instead";
            signInPage
                    .enterTextInEmailInputField(StringsPage.INVALID_EMAIL)
                    .enterTextInPasswordInputField(StringsPage.VALID_PASSWORD);
            signInPage.clickOnSignInButtonAndExpectError();

            Reporter.log("Verify that '" + StringsPage.INVALID_EMAIL_LOGIN_MESSAGE + "' message is displayed", true);
            assert  signInPage.getInvalidEmailErrorMessageText().contains(StringsPage.INVALID_EMAIL_LOGIN_MESSAGE): "Invalid email error message should be displayed but its NOT";

        } finally {

            driver.quit();
        }
    }

    /**
     * Sign in with valid email and empty password

     Test steps:
     1. Navigate to https://fentybeauty.com/
     2. Accept cookies and welcome message
     3. Click on account page icon from the header navigation menu
     4. Enter valid email in email input field
     5. Click on login button

     Expected result:
     1. The 'Accept cookies' and 'Welcome message' are displayed on screen
     2. 'Accept cookies' and 'Welcome message' are no longer displayed on screen
     3. Verify that the user is on the Signin page URL
     4. Verify that 'email input field' is displayed on page
     5. Verify that error message containing "*password must be more than 5 characters" is displayed.

     */

        @Test(priority = 4)
    public void SignInWithValidEmailAndEmptyPassword() {
            driver = openChromeDriver();
        try {
            BasePage basePage = new BasePage(driver);
            basePage
                    .clickOnCookiesAcceptButton()
                    .clickAcceptWelcomeMessage();

            SignInPage signInPage = basePage.clickOnAccountPageIcon();
            assert signInPage.isCurrentURLEqualTo(StringsPage.SIGNIN_PAGE_URL): "Error. The user should be on the " + StringsPage.SIGNIN_PAGE_URL + " but is on " + driver.getCurrentUrl() + " instead";

            signInPage
                    .enterTextInEmailInputField(StringsPage.VALID_EMAIL);
            signInPage.clickOnSignInButtonAndExpectError();

            assert isElementPresent(signInPage.emptyPasswordFieldErrorMessage);
            Reporter.log("Verify that '" + StringsPage.EMPTY_PASSWORD_MESSAGE + "' message is displayed", true);
            assert signInPage.emptyPasswordFieldErrorMessage.getText().contains("*password must be more than 5 characters"): "Empty password field error message NOT displayed";

        } finally {

        }driver.quit();
    }
}
