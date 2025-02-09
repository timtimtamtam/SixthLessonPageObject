package mantis.utils;

import org.openqa.selenium.*;

public class TestUtils {
    public static void scrollToElement (WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

}
