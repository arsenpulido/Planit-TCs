import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class PlanitTC2 {

    public static void main(String[] args) {
        // Set the path to your chromedriver.exe file
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\SeleniumTesting\\chromedriver-win64\\chromedriver.exe");

        // Run the test 5 times
        for (int i = 1; i <= 5; i++) {
            System.out.println("Running Test Iteration: " + i);
            runTestCase();
            System.out.println("Test Iteration " + i + " completed.\n");
        }
    }

    public static void runTestCase() {
        // Initialize the ChromeDriver
        WebDriver driver = new ChromeDriver();
        //Data to be used:
        String CustomerName = "Jon";
        String CustomerEmail = "Jon@exampletest.com";
        String CustomerMessage = "This is my message";
        try {
            // Step 1: Navigate to the home page
            driver.get("https://jupiter.cloud.planittesting.com/#/home");

            // Step 2: Navigate to the contact page
            WebElement contactLink = driver.findElement(By.xpath("//li[@id='nav-contact']"));
            contactLink.click();

            // Step 3: Populate mandatory fields
            WebElement nameInput = driver.findElement(By.id("name"));
            WebElement emailInput = driver.findElement(By.id("email"));
            WebElement messageInput = driver.findElement(By.id("message"));

            nameInput.sendKeys(CustomerName);
            emailInput.sendKeys(CustomerEmail);
            messageInput.sendKeys(CustomerMessage);

            // Step 4: Click submit button
            WebElement submitButton = driver.findElement(By.id("submit"));
            submitButton.click();

            // Step 5: Validate successful submission message
            WebElement successMessage = driver.findElement(By.xpath("//div[@class='alert alert-success']"));
            String actualMessage = successMessage.getText();
            String expectedMessage = "Thanks" + CustomerName + ", we appreciate your feedback.";

            if (actualMessage.equals(expectedMessage)) {
                System.out.println("Test Passed - Successful Submission Message is displayed.");
            } else {
                System.out.println("Test Failed - Expected: " + expectedMessage + ", Actual: " + actualMessage);
            }
        } finally {
            // Close the browser window
            driver.quit();
        }
    }
}
