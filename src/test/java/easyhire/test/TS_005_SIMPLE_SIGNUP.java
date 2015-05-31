package easyhire.test;

import easyhire.page.EasyHire;
import easyhire.page.EasyHireSignUp;
import mailinator.page.Mailinator;
import mailinator.page.MailinatorMail;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by dmitry on 30.5.15.
 */
public class TS_005_SIMPLE_SIGNUP {
    String userID = Resources.TS_005_UNIQUE_USERID,firstName = "testFirstName", lastName = "testLastName";
    Mailinator mailinator;
    EasyHire mainPage;
    EasyHireSignUp signUpPage;
    String mainUrl = Resources.MAIN_PAGE_URL;
    EasyHire.DriverType driverType = Resources.DRIVER_TYPE;
    String mailinatorToken = Resources.MAILINATOR_TOKEN;

    @BeforeClass
    public void init(){
        mainPage = new EasyHire(mainUrl, driverType);
    }

    @Test
    public void signUp(){
        signUpPage = mainPage.navigateToSignUp();
        signUpPage.getFirstNameInput().sendKeys(firstName);
        signUpPage.getLastNameInput().sendKeys(lastName);
        signUpPage.getCompanyInput().sendKeys("testCompany");
        signUpPage.getEmailInput().sendKeys(userID+"@mailinator.com");
        signUpPage.getPassInput().sendKeys("testPass");
        signUpPage.getSubmitButton().click();

        assertThat(signUpPage.getAlertText().isDisplayed());
        assertThat(signUpPage.getAlertText().getText()).isEqualTo("Thank you for signing up! Please check your email to activate your account.");
    }
    @Test (dependsOnMethods = {"signUp"})
    public void checkEmailAfterRegistration(){
        mailinator = new Mailinator(mailinatorToken);
        ArrayList<MailinatorMail> regEmails = mailinator.getMessagesBySubject(userID, "EasyHire.me Account Activation");

        //TODO activation link os not validated
        assertThat(regEmails).hasSize(1);
        assertThat(regEmails.get(0).getMessageBody().contains(
                "A new EasyHire.me account is created for " + firstName + " " + lastName + " with email-id as " + userID + "@mailinator.com" +
                        "\nPlease activate the account by clicking the following link:"
        ));
    }
    @AfterClass
    public void tearDown(){
        mainPage.getDriver().quit();
        signUpPage.getDriver().quit();
    }
}
