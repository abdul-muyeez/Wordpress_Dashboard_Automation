**WordPress Dashboard Automation using Selenium**:

---

## WordPress Dashboard Automation Testing using Selenium

### Overview
This project focuses on automating various scenarios within the WordPress dashboard using **Selenium** with **Java**. The automated test suite covers a series of actions such as logging in, installing the WP Dark Mode plugin, enabling dark mode, customizing switch settings, and validating dark mode functionality on both the admin dashboard and frontend.

### Requirements
To execute this test suite, you'll need the following:
- **Selenium WebDriver** (Chrome or Firefox)
- A WordPress installation with admin access
- WP Dark Mode plugin (will be installed automatically if not already present)
- **Java Development Kit (JDK)** installed on your machine
- **Eclipse** or **IntelliJ IDEA** (or any other Java IDE) for running the tests

### How to Run the Test Suite
1. Clone this repository to your local machine.
2. Import the project into your preferred Java IDE (e.g., Eclipse or IntelliJ IDEA).
3. Update the `config.properties` file with your WordPress site URL, admin username, and password.
4. Execute the test suite using the **TestNG** framework.

### Test Scenarios
The test suite automates the following scenarios:

1. **Log in to WordPress Site**
   - Navigate to the WordPress site and log in using the provided credentials.

2. **Check and Install WP Dark Mode Plugin**
   - Verify if the WP Dark Mode plugin is active. If not, the suite will install and activate it.

3. **Enable Admin Dashboard Dark Mode**
   - Navigate to the WP Dark Mode settings and enable Admin Dashboard Dark Mode.

4. **Customize Switch Settings**
   - Change the Floating Switch Style to a non-default option.
   - Set Custom Switch size and scale it to 220.
   - Modify the Floating Switch Position to Left.

5. **Disable Keyboard Shortcut**
   - Disable the Keyboard Shortcut from the Accessibility Settings.

6. **Enable Page-Transition Animation**
   - Enable Page-Transition Animation and select a non-default animation effect.

7. **Validate Dark Mode**
   - Ensure that the dark mode is functioning properly on both the admin dashboard and the frontend.

### Code Structure
The project follows an organized structure with the following packages:

- **drivers**: Contains the `BaseDriver` class responsible for setting up the Selenium WebDriver.
- **pages**: Contains page object classes for interacting with the WordPress dashboard, such as `LoginPage`, `DarkModePage`, and `CustomizationDarkModePage`.
- **tests**: Contains the test classes implementing the various test scenarios, like `LoginTest`.
- **utilities**: Includes utility classes for common methods, extent reporting, and screenshot capturing.

### Page Object Model
This project uses the **Page Object Model (POM)** design pattern to separate page elements and actions from the test logic. Each page object class is responsible for interacting with its respective page elements, improving maintainability and readability.

### TestNG Framework
The **TestNG** framework is used to manage the execution of test cases and provides built-in reporting features.

### GitHub Repository
The project is available on GitHub at the following link:
[GitHub Repository - WordPress Dashboard Automation]([https://github.com/blackbox-ai/wordpress-dashboard-automation](https://github.com/abdul-muyeez/Wordpress_Dashboard_Automation))

> **Note**: Before running the test suite, ensure that you update the `config.properties` file with your WordPress site URL, admin username, and password.

---

Feel free to ask if you need any further assistance or adjustments!
