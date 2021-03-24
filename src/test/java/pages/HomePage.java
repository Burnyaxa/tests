package pages;

import jdk.jfr.DataAmount;
import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


@Data
public class HomePage {
    private WebDriver driver;

    @FindBy(xpath = "/html/body/div[5]/div[1]/nav/div/ul/li[5]/a")
    private WebElement loginButton;

    @FindBy(xpath = "/html/body/div[5]/div[1]/nav/div/ul/li[4]/a")
    private WebElement registrationButton;

    @FindBy(name = "search")
    private WebElement searchBar;

    @FindBy(css = "#p-lang > div > ul > li.interlanguage-link.interwiki-be > a")
    private WebElement changeLocalization;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void search(String input) {
        searchBar.sendKeys(input);
        searchBar.submit();
    }

    public void goToLogin(){
        loginButton.click();
    }

    public void changeLocalization() {
        changeLocalization.click();
    }

    public void goToRegistration() {
        registrationButton.click();
    }
}
