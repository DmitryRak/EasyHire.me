package mailinator.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitry on 30.5.15.
 */
public class Mailinator {
    private String mainUrl = "https://mailinator.com/";
    private WebDriver driver;
    private List<WebElement> easyHireEmails;
    //TODO locating elements

    /**
     * @param inboxUrl (e.g. inbox.jsp?to=test3005). test3005 - userName
     * @param driver
     */
    public Mailinator(String inboxUrl, WebDriver driver) {

        this.driver = driver;
        driver.get(mainUrl+inboxUrl);
        new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@id='InboxNameCtrl']")));
        PageFactory.initElements(driver, this);
    }
    public List<WebElement> findRegistrationMessage(){
        easyHireEmails = driver.findElements(By.xpath("//a/div[contains(.,'EasyHire.me Account Activation')]"));
        return easyHireEmails;
    }
    public String getRegistrationMessage(){
        easyHireEmails.get(0).click();
        new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("mailshowdivbody")));
        WebElement iframeMsg = driver.findElement(By.name("rendermail"));
        driver.switchTo().frame(iframeMsg);
        return driver.findElement(By.className("mailview")).getText();
    }
    public WebDriver getDriver() {
        return driver;
    }
}
