package easyhire.test;

import easyhire.page.EasyHire;
import easyhire.page.EasyHireSignUp;
import mailinator.page.Mailinator;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by dmitry on 30.5.15.
 */
public class TS_005_SIMPLE_SIGNUP {
    String userID = "test1",firstName = "testFirstName", lastName = "testLastName";
    Mailinator mailinator;
    EasyHire mainPage;
    EasyHireSignUp signUpPage;
    String mainUrl = Resources.mainPageUrl;

    @BeforeClass
    public void init(){
        mainPage = new EasyHire(mainUrl, EasyHire.DriverType.CHROME);
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
    }
    @Test (dependsOnMethods = {"signUp"})
    public void checkEmailAfterRegistration(){
        mailinator = new Mailinator("inbox.jsp?to="+userID,signUpPage.getDriver());
        List <WebElement> regEmails = mailinator.findRegistrationMessage();
        assertThat(regEmails).hasSize(1);
        //TODO activation link os not validated
        assertThat(mailinator.getRegistrationMessage()).contains(
                        "A new EasyHire.me account is created for "+firstName+" "+lastName+" with email-id as "+userID+"@mailinator.com"+
        "\nPlease activate the account by clicking the following link:"
        );
    }
    @AfterClass
    public void tearDown(){
        mailinator.getDriver().quit();
        mainPage.getDriver().quit();
        signUpPage.getDriver().quit();
    }
}
