package pages;

import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Data
public class LoginPage {
    private WebDriver driver;

    @FindBy(id = "wpName1")
    private WebElement name;

    @FindBy(id = "wpPassword1")
    private WebElement password;

    @FindBy(id = "wpLoginAttempt")
    private WebElement submit;

    @FindBy(xpath = "/html/body/div[3]/div[3]/div[4]/div[1]/div[3]/form/div[1]")
    private WebElement error;

    @FindBy(linkText = "Створити обліковий запис")
    private WebElement registration;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void loginUser(String username, String passwd) {
        name.click();
        name.sendKeys(username);
        password.click();
        password.sendKeys(passwd);
        submit.submit();
    }

    public void goToRegisterPage() {
        registration.click();
    }

    public String getTextOfError() {
        return error.getText();
    }
}
