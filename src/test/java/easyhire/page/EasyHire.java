package easyhire.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sun.font.EAttribute;

/**
 * Created by dmitry on 30.5.15.
 * TODO https://mailinator.com/inbox.jsp?to=test3105
 *
 */
public class EasyHire {
    private WebDriver driver;
    //TODO locating elements

    @FindBy(className = "text-center")
    private WebElement dontHaveAccText;

    @FindBy(xpath = "//img[@src='images/logo.png']")
    private WebElement logo;

    @FindBy(xpath = "//input[@name='email']")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement passInput;

    @FindBy(xpath = "//button[contains(@class,'linkedin')]")
    private WebElement linkedInButton;

    @FindBy(xpath = "//a[contains(.,' Forgot Password?')]")
    private WebElement forgotLink;

    @FindBy(xpath = "//a[contains(.,'Sign up')]")
    private WebElement signUpLink;

    //@FindBy(xpath = "//h6[contains(.,'1-click access to your EasyHire.me account')]")
    @FindBy(className = "headerTextOne")
    private WebElement oneClickText;

    @FindBy(xpath = "//button[contains(.,' Log in')]")
    private WebElement login;


    public EasyHire(String startPage, DriverType driverType){
        switch(driverType){
            case CHROME:
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            case OPERA:
                driver = new OperaDriver();
                break;
            case HTML:
                driver = new HtmlUnitDriver();
                break;
            default:
                driver = null;
        }
        driver.get(startPage);
        new WebDriverWait(driver, 30)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,' Log in')]")));
        PageFactory.initElements(driver, this);
    }

    public WebElement getLogo() {
        return logo;
    }

    public WebElement getEmailInput() {
        return emailInput;
    }

    public WebElement getPassInput() {
        return passInput;
    }

    public WebElement getLinkedInButton() {
        return linkedInButton;
    }

    public WebElement getSignUpLink() {
        return signUpLink;
    }

    public WebElement getForgotLink() {
        return forgotLink;
    }

    public WebElement getOneClickText() {
        return oneClickText;
    }

    public WebElement getLogin() {
        return login;
    }
    public WebElement getDontHaveAccText() {
        return dontHaveAccText;
    }
    public EasyHireSignUp navigateToSignUp(){
        signUpLink.click();
        return new EasyHireSignUp(driver);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public enum DriverType{
        CHROME, OPERA, FIREFOX, HTML;
    }
}
