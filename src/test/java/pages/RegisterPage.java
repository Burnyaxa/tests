package pages;

import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Data
public class RegisterPage {
    private WebDriver driver;

    @FindBy(id = "wpEmail")
    private WebElement email;

    @FindBy(name = "wpName")
    private WebElement name;

    @FindBy(name = "wpPassword")
    private WebElement password;

    @FindBy(css = "#wpRetype")
    private WebElement confirmPassword;

    @FindBy(name = "captchaWord")
    private WebElement captcha;

    @FindBy(xpath = "/html/body/div[3]/div[3]/div[4]/div[1]/div[2]/form/div[1]")
    private WebElement error;

    @FindBy(id = "wpCreateaccount")
    private WebElement submit;


    public RegisterPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void registerUser(String username, String passwordValue, String emailAddress, String captchaValue) throws InterruptedException {
        name.click();
        name.sendKeys(username);

        password.click();
        password.sendKeys(passwordValue);

        confirmPassword.click();
        confirmPassword.sendKeys(passwordValue);

        email.click();
        email.sendKeys(emailAddress);

        captcha.click();
        captcha.sendKeys(captchaValue);

        submit.submit();
    }

}

