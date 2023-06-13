package core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class BaseFunction {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor executor;
    private final Logger LOG = LogManager.getLogger();

    public BaseFunction() {
        System.setProperty("webdriver.chrome.driver", "C:/webdriver/chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        wait = new WebDriverWait(driver, 10, 100);
        executor = (JavascriptExecutor) driver;
    }

    public void goToUrl(String url) {
        LOG.info("Opening url: " + url);
        driver.get(url);
    }

    public void click(By locator) {
        wait.until(elementToBeClickable(locator)).click();
    }

    public WebElement getElement(By locator) {
        return driver.findElement(locator);
    }

    public List<WebElement> getElements(By locator) {
        return driver.findElements(locator);
    }

    public void scrollToElement(WebElement element) {
        executor.executeScript("arguments[0].scrollIntoView({behavior: 'auto', block: 'center', inline: 'center'});", element);
    }

    public boolean isElementVisible(By locator) {
        try {
            wait.until(visibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isElementPresent(By locator) {
        return !getElements(locator).isEmpty();
    }

    public void waitUntilElementDisappears(By locator) {
        wait.until(invisibilityOfElementLocated(locator));
    }

    public void closeBrowser() {
        LOG.info("Closing browser window");
        driver.quit();
    }
}
