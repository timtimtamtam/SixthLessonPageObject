package mantis.pages;

import mantis.alerts.CheckAlerts;
import mantis.utils.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ViewIssuesPage {
    private final WebDriver driver;
    private final WebDriverWait wait;


    @FindBy(css = "#buglist tbody tr")
    private List<WebElement> issues;

    @FindBy(css = "#bug_arr_all")
    private WebElement tableFooter;

    @FindBy(xpath = "//input[@name= 'bug_id']")
    private WebElement searchInput;

    public ViewIssuesPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public String getBugIdFromBugPage(){
        WebElement bugId = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//td[@class = 'bug-id']")));
        return bugId.getText();
    }

    public void deleteIssue(){
        WebElement buttonDelete = wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//input[@value='Delete']")));
        buttonDelete.click();

        WebElement buttonConfirmDeletion = wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//input[@value='Delete Issues']")));
        buttonConfirmDeletion.click();
    }

    public void searchIssueById(String issueId) {
        searchInput.sendKeys(issueId, Keys.ENTER);
    }

    public int countIssues() {
        return issues.size();
    }

    public void scrollToTableFooter() {
        TestUtils.scrollToElement(driver, tableFooter);
    }

    public boolean isIssueDeletedOrNotFound(String issueId) {
        searchIssueById(issueId);
        String notFoundAlert = CheckAlerts.issueNotFoundAlert(issueId);
        return CheckAlerts.alertIsPresentByText(notFoundAlert, driver, wait);
    }

}
