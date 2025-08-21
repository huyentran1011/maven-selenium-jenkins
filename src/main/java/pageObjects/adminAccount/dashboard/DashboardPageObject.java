package pageObjects.adminAccount.dashboard;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageObjects.SideBarAndTopBarObject;
import pageUIs.adminAccount.PIM.employee.AddEmployeePageUI;

public class DashboardPageObject extends SideBarAndTopBarObject {
    private WebDriver driver;
    public DashboardPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

}
