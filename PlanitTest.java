import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class PlanitTest {

    public static void main(String[] args) {

        //Data to be used:
        String CustomerName = "Jon";
        String CustomerEmail = "Jon@exampletest.com";
        String CustomerMessage = "This is my message";

        // Set the path to your ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\SeleniumTesting\\chromedriver-win64\\chromedriver.exe");

        // Create a new instance of the ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Navigate to the Jupiter website
        driver.get("http://jupiter.cloud.planittesting.com");

        // Navigate to the contact page
        WebElement contactLink = driver.findElement(By.id("nav-contact"));
        contactLink.click();

        // Click the submit button without populating mandatory fields
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();

        // Verify error messages
        WebElement errorMessage = driver.findElement(By.className("error"));
        if (errorMessage.isDisplayed()) {
            System.out.println("Error messages are displayed.");
        } else {
            System.out.println("Error messages are not displayed.");
        }

        // Populate mandatory fields
        WebElement forenameInput = driver.findElement(By.xpath("//input[@name='forename']"));
        forenameInput.sendKeys(CustomerName);

        WebElement emailInput = driver.findElement(By.xpath("//input[@name='email']"));
        emailInput.sendKeys(CustomerEmail);

        WebElement messageInput = driver.findElement(By.xpath("//textarea[@name='message']"));
        messageInput.sendKeys(CustomerMessage);

        // Click the submit button again
        submitButton.click();

        // Validate errors are gone
        try {
            errorMessage = driver.findElement(By.className("error"));
            System.out.println("Validation failed: Error messages are still displayed.");
        } catch (Exception e) {
            System.out.println("Validation passed: Error messages are gone.");
        }

        // Close the browser
        driver.quit();
    }
}
