package orangehrm.common;

import commons.BaseTest;
import commons.GlobalConstants;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.LoginPageObject;
import pageObjects.PageGenerator;
import pageObjects.SideBarAndTopBarObject;

import java.util.Set;

public class Login extends BaseTest {
    private WebDriver driver;
    private LoginPageObject loginPage;
    private SideBarAndTopBarObject sideAndTopBar;
    private String browserName;
    public static Set<Cookie> orangeHRMCookies;

    @Parameters("browser")
    @BeforeTest
    public void beforeTest(String browserName){
        driver = getBrowserDriver(browserName);
        this.browserName = browserName.toUpperCase();

    }


    @Test
    public void Login_Success() {
        // Login
        loginPage = PageGenerator.getLoginPage(driver);
        loginPage.enterValueIntoTextboxByNameAttribute(driver, "username", GlobalConstants.ADMIN_USERNAME_LIVE);
        loginPage.enterValueIntoTextboxByNameAttribute(driver, "password", GlobalConstants.ADMIN_PASSWORD_LIVE);
        loginPage.clickOnButtonByText(driver, "Login");

        // Verify login success
        sideAndTopBar = PageGenerator.getSideBarAndTopBar(driver);
        Assert.assertTrue(sideAndTopBar.isProfilePictureDisplayed());

        sideAndTopBar.sleepInSeconds(5);

        // Get all cookies
        orangeHRMCookies = sideAndTopBar.getAllCookies(driver);
    }

    @Test
    public void Login_Fail_UsernameIsNull() {
        // Login
        loginPage = PageGenerator.getLoginPage(driver);
        loginPage.enterValueIntoTextboxByNameAttribute(driver, "username", "");
        loginPage.enterValueIntoTextboxByNameAttribute(driver, "password", GlobalConstants.ADMIN_PASSWORD_LIVE);
        loginPage.clickOnButtonByText(driver, "Login");

        // Verify login fail
        sideAndTopBar = PageGenerator.getSideBarAndTopBar(driver);
        Assert.assertFalse(sideAndTopBar.isProfilePictureDisplayed());

        sideAndTopBar.sleepInSeconds(5);
    }

    @Test
    public void Login_Fail_PasswordIsNull() {
        // Login
        loginPage = PageGenerator.getLoginPage(driver);
        loginPage.enterValueIntoTextboxByNameAttribute(driver, "username", GlobalConstants.ADMIN_USERNAME_LIVE);
        loginPage.enterValueIntoTextboxByNameAttribute(driver, "password", "");
        loginPage.clickOnButtonByText(driver, "Login");

        // Verify login fail
        sideAndTopBar = PageGenerator.getSideBarAndTopBar(driver);
        Assert.assertFalse(sideAndTopBar.isProfilePictureDisplayed());

        sideAndTopBar.sleepInSeconds(5);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass(){
        closeBrowserDriver();
    }
}
