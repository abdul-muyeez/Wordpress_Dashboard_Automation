package tests;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import java.io.IOException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import drivers.PageDriver;
import pages.DarkMode;
import pages.LoginPage;
import utilities.CommonMethods;
import utilities.ExtentFactory;

public class LoginTest extends CommonMethods{

    ExtentReports reports;
    ExtentTest parentTest;
    ExtentTest childTest;



    @BeforeClass
    public void openurl() throws InterruptedException {

        PageDriver.getCurrentDriver().get(url);
        timeout(5000);
        reports = ExtentFactory.getInstance();
        parentTest = reports.createTest("<p style=\"color:#85BC63; font-size:13px\"><b>WordPress Dashboard</b></p>").assignAuthor("QA Team").assignDevice("Windows");

    }
    @Test (priority = 0)

    public void loginIntoShop() throws IOException {
        childTest = parentTest.createNode("<p style=\"color:#3E96E7; font-size:13px\"><b>Login</b></p>");
        LoginPage loginPage = new LoginPage(childTest);
        loginPage.login();

    }

    @Test (priority = 1)
    public void checkDarkModePluginStatus() throws IOException {
        childTest = parentTest.createNode("<p style=\"color:#3E96E7; font-size:13px\"><b>Check DarkMode Plugin and Activation</b></p>");
        DarkMode dashbiarddarkMode = new DarkMode(childTest);
        dashbiarddarkMode.checkDarkModePluginStatus();
    }

    @Test (priority = 2)
    public void enableAdminDashboardDarkMode() throws IOException {
        childTest = parentTest.createNode("<p style=\"color:#3E96E7; font-size:13px\"><b>Enable Admin Dashboard Dark Mode</b></p>");
        DarkMode darkMode = new DarkMode(childTest);
        darkMode.enableAdminDashboardDarkMode();
    }
    @Test (priority = 3)
    public void validateAdminDashboardDarkMode() throws IOException {
        childTest = parentTest.createNode("<p style=\"color:#3E96E7; font-size:13px\"><b>Validate Admin Dashboard Dark Mode Enabled</b></p>");
        DarkMode darkMode = new DarkMode(childTest);
        darkMode.validateAdminDashboardDarkMode();
    }
    @AfterClass
    public void report() {
        reports.flush();
    }


}

