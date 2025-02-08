package mantis.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestUtils {
    public static void scrollToElement (WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static boolean alertIsPresentByText (String textAlert, WebDriver driver, WebDriverWait wait) {

        try {
            WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//p[text()='"+textAlert+"']")
            ));
            return alert.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public static String issueNotFoundAlert (String issueId) {
        String issue = issueId.replaceFirst("^0+", "");
        return "Issue "+issue+" not found.";
    }

}
