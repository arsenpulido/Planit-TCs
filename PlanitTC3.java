import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class PlanitTC3 {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\SeleniumTesting\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://jupiter.cloud.planittesting.com");

        // Buy 2 Stuffed Frog, 5 Fluffy Bunny, 3 Valentine Bear
        addToCart(driver, "Stuffed Frog", 2);
        addToCart(driver, "Fluffy Bunny", 5);
        addToCart(driver, "Valentine Bear", 3);

        // Go to the cart page
        driver.findElement(By.id("nav-cart")).click();



        // Verify the price and subtotal for each product is correct
        verifyPriceAndSubtotal(driver, "Stuffed Frog", 2);
        verifyPriceAndSubtotal(driver, "Fluffy Bunny", 5);
        verifyPriceAndSubtotal(driver, "Valentine Bear", 3);

        // Verify that total = sum(subtotals)
        verifyTotal(driver);

        // Close the browser
        driver.quit();
    }

    private static void addToCart(WebDriver driver, String productName, int quantity) {
        String xpath = "//li[contains(@class, 'product') and div/h4[@class='product-title ng-binding' and text()='"+ productName + "']]//a[@class='btn btn-success']";
        for (int i = 0; i < quantity; i++) {
            WebElement addToCartButton = driver.findElement(By.xpath(xpath));
            addToCartButton.click();
        }
    }

    private static void verifyPriceAndSubtotal(WebDriver driver, String productName, int quantity) {


        WebElement webTable = driver.findElement(By.xpath("//table[@class='table table-striped cart-items']"));

        List<WebElement> rows = webTable.findElements(By.tagName("tr"));
        int rowsCount = rows.size();
        for(int i = 0;i<rowsCount;i++) {

            List<WebElement> columns = rows.get(i).findElements(By.tagName("td"));
            int columnsCount = columns.size();

            for (int j=0;j<columnsCount;j++){
                String cellText = columns.get(j).getText();
                if(cellText.equals(productName)){
                    String itemSubTotal = columns.get(3).getText();
                    String itemPrice = columns.get(1).getText();

                    System.out.println("Subtotal Value is: " + itemSubTotal);
                    System.out.println("Price is: " + itemPrice);

                    double subTotalOfItems = Double.parseDouble(itemSubTotal.replace("$", ""));
                    double priceOfItems = Double.parseDouble(itemPrice.replace("$", ""));
                    // Verify price and subtotal

                    if (subTotalOfItems == priceOfItems*quantity) {
                        System.out.println("SubTotal is correct.");
                    } else {
                        System.out.println("SubTotal is incorrect.");
                    }
                }

            }
        }
    }

    private static void verifyTotal(WebDriver driver) {

        List<WebElement> subtotalElements = driver.findElements(By.xpath("//td[4]"));
        double sum = 0;
        for (WebElement subtotalElement : subtotalElements) {
            sum += Double.parseDouble(subtotalElement.getText().replace("$", ""));
        }

        WebElement totalElement = driver.findElement(By.xpath("//strong[@class='total ng-binding']"));
        double total = Double.parseDouble(totalElement.getText().replace("Total: ", ""));

        // Additional logic to verify that total = sum(subtotals)
        if (total == sum) {
            System.out.println("Total is correct.");
        } else {
            System.out.println("Total is incorrect.");
        }
    }
}
