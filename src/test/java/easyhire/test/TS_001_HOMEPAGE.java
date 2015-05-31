package easyhire.test;

import easyhire.page.EasyHire;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by dmitry on 30.5.15.
 */
public class TS_001_HOMEPAGE {
    EasyHire mainPage;
    String mainUrl = Resources.MAIN_PAGE_URL;
    EasyHire.DriverType driverType = Resources.DRIVER_TYPE;

    @BeforeClass
    public void init(){
        mainPage = new EasyHire(mainUrl, driverType);
    }
    @Test
    public void logoIsExist(){
        String expectedUrl = mainUrl+"images/logo.png";
        WebElement logo = mainPage.getLogo();
        assertThat(logo.isDisplayed()).isEqualTo(true);
        assertThat(logo.getAttribute("src")).isEqualTo(expectedUrl);
    }
    @Test
    public void loginIsDisplayedAndEditable(){
        String inputText = "smth";
        WebElement loginInput = mainPage.getEmailInput();
        mainPage.getEmailInput().sendKeys(inputText);
        assertThat(loginInput.getAttribute("value")).isEqualTo(inputText);
        assertThat(loginInput.isDisplayed()).isEqualTo(true);
    }
    @Test
    public void passwordIsDisplayedAndEditable(){
        String inputText = "pass";
        WebElement passInput = mainPage.getPassInput();
        passInput.sendKeys(inputText);
        assertThat(passInput.isDisplayed()).isEqualTo(true);
        assertThat(passInput.getAttribute("type")).isEqualTo("password");
        assertThat(passInput.getAttribute("value")).isEqualTo("pass");
    }
    @Test
    public void loginWithLinkedIn(){
        WebElement linkedin = mainPage.getLinkedInButton();
        assertThat(linkedin.isDisplayed()).isEqualTo(true);
        assertThat(linkedin.getText()).isEqualTo("Log in with LinkedIn");
    }
    @Test
    public void signUpLinkIsPresent(){
        assertThat(mainPage.getSignUpLink().getAttribute("href")).isEqualTo(mainUrl+"signup");
    }
    @Test
    public void forgotPassLinkIsPresent(){
        assertThat(mainPage.getForgotLink().getAttribute("href")).isEqualTo(mainUrl+"forgot");
    }
    @Test
    public void oneClickTextIsCorrect(){
        String text = mainPage.getOneClickText().getText();
        assertThat(text).isEqualTo("1-click access to your EasyHire.me account");
    }
    @Test
    public void dontHaveAccIsCorrect(){
        String text = mainPage.getDontHaveAccText().getText();
        assertThat(text).isEqualTo("Don't have an account? Sign up  Forgot Password?");
    }
    @AfterClass
    public void tearDown(){
        mainPage.getDriver().quit();
    }
}
