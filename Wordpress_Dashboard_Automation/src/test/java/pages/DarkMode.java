package pages;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import drivers.PageDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utilities.GetScreenShot;


public class DarkMode extends LoginPage {

    ExtentTest test;

    public DarkMode(ExtentTest test) {
        super(test);
        PageFactory.initElements(PageDriver.getCurrentDriver(), this);
        this.test = test;
    }

    @FindBy(xpath = "//li[@id='menu-plugins']")
    WebElement PluginRow;

    // Report
    public void passCase(String message) {
        test.pass("<p style=\"color:#85BC63; font-size:13px\"><b>" + message + "</b></p>");
    }

    @SuppressWarnings("unused")
    public void passCaseWithSC(String message, String scName) throws IOException {
        test.pass("<p style=\"color:#85BC63; font-size:13px\"><b>" + message + "</b></p>");
        String screenShotPath = GetScreenShot.capture(PageDriver.getCurrentDriver(), "" + scName + "");
        String dest = System.getProperty("user.dir") + "\\screenshots\\" + "" + scName + ".png";
        test.pass(MediaEntityBuilder.createScreenCaptureFromPath(dest).build());
    }

    // Fail
    @SuppressWarnings("unused")
    public void failCase(String message, String scName) throws IOException {
        test.fail("<p style=\"color:#FF5353; font-size:13px\"><b>" + message + "</b></p>");
        Throwable t = new InterruptedException("Exception");
        test.fail(t);
        String screenShotPath = GetScreenShot.capture(PageDriver.getCurrentDriver(), "" + scName + "");
        String dest = System.getProperty("user.dir") + "\\screenshots\\" + "" + scName + ".png";
        test.fail(MediaEntityBuilder.createScreenCaptureFromPath(dest).build());
        PageDriver.getCurrentDriver().quit();
    }

    public void checkDarkModePluginStatus() throws IOException {

        try {
            test.info("Click Plugin");
            if (PluginRow.isDisplayed()) {
                PluginRow.click();
                passCaseWithSC("You have successfully clicked Plugin", "Plugin_validation");
                timeout(2000);

                // Define multiple XPath expressions
                String xpath1 = "//tr[@class='active']//td[@class='plugin-title column-primary']";
                String xpath2 = "//tr[@class='inactive']//td[@class='plugin-title column-primary']";

                // Find elements using both XPath expressions
                List<WebElement> textElements = driver.findElements(By.xpath(xpath1 + " | " + xpath2));

                // Check if any of the elements were found
                if (!textElements.isEmpty()) {
                    // Get the text from the first found element
                    String actualText = textElements.get(0).getText();

                    // Verify the text
                    String expectedText = "Activate";
                    if (actualText.contains(expectedText)) {
                        passCaseWithSC("DarkMode is Inactive: " + actualText, "DarkMode_status");
                    } else if (actualText.contains("Deactivate")) {
                        passCaseWithSC("DarkMode is Active. Expected: " + actualText, "DarkMode_status");
                    }
                } else {
                    failCase("Neither XPath expression found an element", "DarMode_fail");
                }
            }


        } catch (Exception e) {
            failCase("Click button locator not found", "plugin_fail");
            System.out.println("Plugin locator was not found");
        }
    }

    public void enableAdminDashboardDarkMode() throws IOException {
        try {
            // Navigate to WP Dark Mode in the left sidebar of the dashboard
            test.info("Navigate to WP Dark Mode");
            driver.findElement(By.xpath("//div[contains(text(),'WP Dark Mode')]")).click();
            passCase("Successfully navigated to WP Dark Mode");
            timeout(2000);
        } catch (Exception e) {
            failCase("Failed to navigate to WP Dark Mode", "wp_dark_mode_fail");
            System.out.println("Failed to navigate to WP Dark Mode");
        }

        try {
            // Go to Controls
            test.info("Click Admin Panel Dark Mode");
            driver.findElement(By.xpath("//a[normalize-space()='Admin Panel Dark Mode']")).click();
            passCase("Successfully navigated to Controls");
            timeout(2000);
        } catch (Exception e) {
            failCase("Failed to navigate to Controls", "controls_fail");
            System.out.println("Failed to navigate to Controls");
        }

        try {
            // Find the option for Admin Panel Dark Mode and enable it
            test.info("Enable Admin Panel Dark Mode");
            WebElement darkModeToggle = driver.findElement(By.xpath("//body/div[@id='wpwrap']/div[@id='wpcontent']/div[@id='wpbody']/div[@id='wpbody-content']/div[@id='wp-dark-mode-admin']/div[1]/div[1]/div[1]/div[2]/div[2]/section[1]/div[1]/div[1]/label[1]/div[1]/div[1]"));
            if (!darkModeToggle.isSelected()) {
                darkModeToggle.click();
                passCaseWithSC("Save Changes Option shows to Enable DarkMode", "DarkMode_save_changes");
                timeout(2000);
            } else {
                passCaseWithSC("Admin Panel Dark Mode is already enabled", "Already Enabled");
            }
        } catch (Exception e) {
            failCase("Failed to enable Admin Panel Dark Mode", "dark_mode_fail");
            System.out.println("Failed to enable Admin Panel Dark Mode");
        }
        try {
            // Navigate to WP Dark Mode in the left sidebar of the dashboard
            test.info("Save Changes");
            driver.findElement(By.xpath("//button[normalize-space()='Save Changes']")).click();
            passCaseWithSC("Successfully navigated to WP Dark Mode", "Admin_Panel_DarkMode_Enabled");
            timeout(2000);
        } catch (Exception e) {
            failCase("Failed to Enable DarkMode after saving changes", "Save_Changes_fail");
            System.out.println("Failed to Enable DarkMode after saving changes");
        }


    }

    public void validateAdminDashboardDarkMode() throws IOException {
        try {
            // Find the option for Admin Panel Dark Mode and enable it
            test.info("DarkMode");
            WebElement darkModeToggle = driver.findElement(By.xpath("//span[normalize-space()='Light']"));
            if (!darkModeToggle.isSelected()) {
                darkModeToggle.click();
                passCaseWithSC("Admin Dashboard Dark Mode is enabled", "DarkMode_Validate");
                timeout(2000);
            } else {
                passCaseWithSC("Admin Panel Dark Mode is already enabled", "DarkMode_Validate");
            }
        } catch (Exception e) {
            failCase("Failed to validate Admin Dashboard Dark Mode", "dark_mode_invalidate");
            System.out.println("Failed to validate Admin Dashboard Dark Mode");
        }
    }

}