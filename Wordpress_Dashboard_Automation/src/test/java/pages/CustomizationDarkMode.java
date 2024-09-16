package pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import drivers.PageDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utilities.GetScreenShot;

import java.io.IOException;
import java.time.Duration;

public class CustomizationDarkMode extends LoginPage {

    ExtentTest test;

    public CustomizationDarkMode(ExtentTest test) {
        super(test);
        PageFactory.initElements(PageDriver.getCurrentDriver(), this);
        this.test = test;
    }

    @FindBy(xpath = "//div[contains(text(),'WP Dark Mode')]")
    WebElement WPDarkMode;

    @FindBy(xpath = "//h4[normalize-space()='Customization']")
    WebElement Customization;

    @FindBy(xpath = "//a[normalize-space()='Switch Settings']")
    WebElement SwitchSettings;

    @FindBy(xpath = "//body/div[@id='wpwrap']/div[@id='wpcontent']/div[@id='wpbody']/div[@id='wpbody-content']/div[@id='wp-dark-mode-admin']/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/section[1]/div[2]/div[1]/div[3]/div[1]/div[2]/div[3]/a[1]")
    WebElement Accessibility;

//    @FindBy(xpath = "//h4[normalize-space()='Advanced']")
//    WebElement Controls;

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


    public void darkModeCustomization() throws IOException {
        try {
            // Navigate to WP Dark Mode in the left sidebar of the dashboard
            test.info("Navigate to WP Dark Mode");
            WPDarkMode.click();
            passCase("Successfully navigated to WP Dark Mode");
            timeout(2000);

            try {
                // Navigate to WP Dark Mode in the left sidebar of the dashboard
                test.info("Navigate to Customize Settings");
                Customization.click();
                passCase("Successfully navigated to Customize Settings");
                timeout(2000);

                try {
                    // Navigate to WP Dark Mode in the left sidebar of the dashboard
                    test.info("Navigate to Switch Settings");
                    SwitchSettings.click();
                    passCase("Successfully navigated to Customize Settings");

                    // Wait for the additional setting checkbox to be clickable
                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2000));
                    WebElement additionalSettingCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//body/div/div/div[@role='main']/div/div/div/div/div/div/div/div/section/div/div/div/div/div[1]/div[2]/div[3]")));
                    additionalSettingCheckbox.click();

                    // Wait for the "Save Changes" button to be clickable
                    WebElement saveChangesButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Save Changes']")));
                    saveChangesButton.click();

                    passCaseWithSC("Successfully selected other switch style", "Switch_Style_Changes_pass");

                    try {
                        JavascriptExecutor js = (JavascriptExecutor) driver;
                        WebElement location = driver.findElement(By.xpath("//div[@*='flex flex-col gap-1 text-base leading-6 text-black font-medium'][normalize-space()='Switch Customization']"));
                        js.executeScript("arguments[0].scrollIntoView(true);", location);
                        timeout(2000);// Navigate to WP Dark Mode in the left sidebar of the dashboard

                        test.info("Customize Floating Switch");
                        wait = new WebDriverWait(driver, Duration.ofSeconds(2000));
                        WebElement customSwitch = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[contains(text(),'Custom')])[1]")));
                        customSwitch.click();
                        timeout(2000);

                        WebElement sizeInputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[2]/div[1]/div[3]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/section[1]/div[2]/div[1]/div[2]/div[4]/div[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[2]/input[1]")));
                        sizeInputField.click();
                        sizeInputField.clear();
                        timeout(2000);

                        // Set the size value to 200 (adjust this value as needed)
                        sizeInputField.sendKeys("200");
                        timeout(2000);

                        saveChangesButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Save Changes']")));
                        saveChangesButton.click();
                        timeout(2000);

                        passCaseWithSC("Successfully customize the switch size in 200", "Save Custom size 200");

                        try {
                            // Navigate to WP Dark Mode in the left sidebar of the dashboard
                            test.info("Floating Switch position to Left");
                            wait = new WebDriverWait(driver, Duration.ofSeconds(2000));
                            WebElement leftButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='Left']")));

                            // Wait for the left button to be clickable
                            // Click on the left button
                            leftButton.click();
                            timeout(2000);
                            passCase("Successfully clicked Switch position to Left");

                            // Save the changes
                            saveChangesButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Save Changes']")));
                            saveChangesButton.click();
                            timeout(2000);
                            passCaseWithSC("Successfully customize Switch position to Left", "Switch position to Left");


                            try {
                                // Navigate to Advanced Settings
                                test.info("Accessibility Settings");
                                Accessibility.click();
                                passCase("Successfully navigated to Accessibility Settings");
                                timeout(2000);


                                // Wait for the settings toggle button to be clickable
                                WebElement settingsToggle = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//body/div[@id='wpwrap']/div[@id='wpcontent']/div[@id='wpbody']/div[@id='wpbody-content']/div[@id='wp-dark-mode-admin']/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[6]/div[1]/label[1]/div[1]/div[1]")));
                                // Click on the toggle button to enable or disable the settings
                                settingsToggle.click();
                                timeout(2000);
                                passCase("Successfully click to keyboard shortcut toggle option");

                                // Save the changes
                                saveChangesButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Save Changes']")));
                                saveChangesButton.click();
                                timeout(2000);
                                passCaseWithSC("Successfully Disable the Keyboard Shortcut", "Disable the Keyboard Shortcut");

                                try {
                                    // Navigate to WP Dark Mode in the left sidebar of the dashboard
                                    test.info("Navigate to siteAnimation");
                                    // Wait for the Settings button to be clickable
                                    driver.findElement(By.xpath("//a[normalize-space()='Site Animation']")).click();
                                    timeout(2000);
                                    passCase("Successfully navigated to siteAnimation");
                                    // Wait for the settings toggle button to be clickable
                                    driver.findElement(By.xpath("//body/div[@id='wpwrap']/div[@id='wpcontent']/div[@id='wpbody']/div[@id='wpbody-content']/div[@id='wp-dark-mode-admin']/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/label[1]/div[1]/div[1]")).click();
                                    // Click on the toggle button to enable or disable the settings
                                    timeout(2000);
                                    passCaseWithSC("Successfully Enable Page Transition Animation", "Enable Page Transition Animation_pass");

                                    driver.findElement(By.xpath("//span[normalize-space()='Pulse']")).click();
                                    // Click on the toggle button to enable or disable the settings
                                    timeout(2000);
                                    passCaseWithSC("Successfully Changed Animation effect from default selections", "Changed Animation effect from default selections_pass");


                                    // Save the changes
                                    saveChangesButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Save Changes']")));
                                    saveChangesButton.click();
                                    timeout(2000);
                                    passCaseWithSC("Successfully Enable Page-Transition Animation” & change the Animation Effect", "Enable Page-Transition Animation” & change the Animation Effect");



                                } catch (Exception e) {
                                    failCase("Failed to Customize Site Animation", "Customize Site Animation_fail");
                                    System.out.println("Failed to Customize Site Animation");
                                }


                            } catch (Exception e) {
                                failCase("Failed to Disable the Keyboard Shortcut", "Disable the Keyboard Shortcut_fail");
                                System.out.println("Failed to Disable the Keyboard Shortcut");
                            }


                        } catch (Exception e) {
                            failCase("Floating Switch position to Left", "Switch position to Left_fail");
                            System.out.println("Failed to navigate to Switch Settings");
                        }


                    } catch (Exception e) {
                        failCase("Failed to navigate to switch size", "switch size_fail");
                        System.out.println("Failed to navigate to switch size");
                    }
                } catch (Exception e) {
                    failCase("Failed to navigate to Switch Settings", "Switch Settings_fail");
                    System.out.println("Failed to navigate to Switch Settings");
                }


        } catch (Exception e) {
            failCase("Failed to navigate to Customize Settings", "Customize Settings_fail");
            System.out.println("Failed to navigate to Customize Settings");
        }

        } catch (Exception e) {
            failCase("Failed to navigate to WP Dark Mode", "wp_dark_mode_fail");
            System.out.println("Failed to navigate to WP Dark Mode");
        }

    }

}