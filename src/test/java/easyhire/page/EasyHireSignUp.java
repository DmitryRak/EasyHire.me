package easyhire.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by dmitry on 30.5.15.
 */
public class EasyHireSignUp {
    private WebDriver driver;

    @FindBy(xpath = "//img[@src='images/logo.png']")
    private WebElement logo;

    @FindBy(xpath = "//input[@name='firstName']")
    private WebElement firstNameInput;

    @FindBy(xpath = "//input[@name='lastName']")
    private WebElement lastNameInput;

    @FindBy(xpath = "//input[@name='email']")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement passInput;

    @FindBy(xpath = "//input[@name='company']")
    private WebElement companyInput;

    @FindBy(xpath = "//button[@id='submit_button']")
    private WebElement submitButton;

    @FindBy(xpath = "//button[contains(@class,'linkedin')]")
    private WebElement linkedInButton;

    private WebElement alertText;

    public EasyHireSignUp(WebDriver driver){
        this.driver = driver;
        new WebDriverWait(driver, 30)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='submit_button']")));
        PageFactory.initElements(this.driver, this);
    }

    public WebElement getLogo() {
        return logo;
    }

    public WebElement getFirstNameInput() {
        return firstNameInput;
    }

    public WebElement getLastNameInput() {
        return lastNameInput;
    }

    public WebElement getEmailInput() {
        return emailInput;
    }

    public WebElement getPassInput() {
        return passInput;
    }

    public WebElement getCompanyInput() {
        return companyInput;
    }

    public WebElement getSubmitButton() {
        return submitButton;
    }

    public WebElement getLinkedInButton() {
        return linkedInButton;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebElement getAlertText() {
        new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("alert")));
        alertText = driver.findElement(By.className("alert"));
        return alertText;
    }
}
