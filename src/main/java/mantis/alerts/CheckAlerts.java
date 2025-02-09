package mantis.alerts;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckAlerts {

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
