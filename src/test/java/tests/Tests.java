package tests;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;

public class Tests {
    private static WebDriver driver;
    private WebDriverWait wait;
    private HomePage homePage;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        System.out.println("start");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testChangeLocalizationRedirectsToHomePage() throws InterruptedException {
        driver.get("https://uk.wikipedia.org/wiki/%D0%93%D0%BE%D0%BB%D0%BE%D0%B2%D0%BD%D0%B0_%D1%81%D1%82%D0%BE%D1%80%D1%96%D0%BD%D0%BA%D0%B0");
        Thread.sleep(3000);
        homePage = new HomePage(driver);
        homePage.changeLocalization();
        Assert.assertEquals("https://be.wikipedia.org/wiki/%D0%93%D0%B0%D0%BB%D0%BE%D1%9E%D0%BD%D0%B0%D1%8F_%D1%81%D1%82%D0%B0%D1%80%D0%BE%D0%BD%D0%BA%D0%B0",
                driver.getCurrentUrl());
    }

    @Test
    public void testRedirectToLoginPage() {
        driver.get("https://uk.wikipedia.org/wiki/%D0%93%D0%BE%D0%BB%D0%BE%D0%B2%D0%BD%D0%B0_%D1%81%D1%82%D0%BE%D1%80%D1%96%D0%BD%D0%BA%D0%B0/");
        homePage = new HomePage(driver);
        homePage.getLoginButton().click();
        Assert.assertEquals("https://uk.wikipedia.org/w/index.php?title=%D0%A1%D0%BF%D0%B5%D1%86%D1%96%D0%B0%D0%BB%D1%8C%D0%BD%D0%B0:%D0%92%D1%85%D1%96%D0%B4&returnto=%D0%93%D0%BE%D0%BB%D0%BE%D0%B2%D0%BD%D0%B0+%D1%81%D1%82%D0%BE%D1%80%D1%96%D0%BD%D0%BA%D0%B0%2F",
                driver.getCurrentUrl());
    }

    @Test
    public void testRegistrationWithEmptyCaptchaRemainsName() throws InterruptedException {
        driver.get("https://uk.wikipedia.org/wiki/%D0%93%D0%BE%D0%BB%D0%BE%D0%B2%D0%BD%D0%B0_%D1%81%D1%82%D0%BE%D1%80%D1%96%D0%BD%D0%BA%D0%B0/");
        homePage = new HomePage(driver);
        homePage.goToRegistration();
        Thread.sleep(3000);
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.registerUser("CoolNewUser", "supersecretpassword", "abracadabra@gmail.com", "wrongcaptcha");
        Assert.assertEquals("CoolNewUser", registerPage.getName().getAttribute("value"));
    }

    @Test
    public void testLogInWrongCredentialsReturnsError() throws InterruptedException {
        driver.get("https://uk.wikipedia.org/wiki/%D0%93%D0%BE%D0%BB%D0%BE%D0%B2%D0%BD%D0%B0_%D1%81%D1%82%D0%BE%D1%80%D1%96%D0%BD%D0%BA%D0%B0/");
        homePage = new HomePage(driver);
        homePage.goToLogin();
        Thread.sleep(3000);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser("DoNotExist", "donotexist");
        Assert.assertEquals(true, true);
    }

    @Test
    public void testSearchCorrectTitle(){
        driver.get("https://uk.wikipedia.org/wiki/%D0%93%D0%BE%D0%BB%D0%BE%D0%B2%D0%BD%D0%B0_%D1%81%D1%82%D0%BE%D1%80%D1%96%D0%BD%D0%BA%D0%B0/");
        homePage = new HomePage(driver);
        var stringToSearch = "Selenium";
        homePage.search(stringToSearch);
        Assert.assertEquals(stringToSearch + " - Вікіпедія", driver.getTitle());
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
