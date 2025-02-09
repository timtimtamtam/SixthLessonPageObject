package mantis.pages;

import com.github.javafaker.Faker;
import mantis.alerts.CheckAlerts;
import mantis.utils.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ReportIssuePage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Faker faker;

    @FindBy(css = "#category_id")
    WebElement categorySelect;

    @FindBy(css = "#reproducibility")
    WebElement reproducibilitySelect;

    @FindBy(css = "#summary")
    WebElement summaryField;

    @FindBy(css = "#description")
    WebElement descriptionField;

    @FindBy(xpath = "//input[@value= 'Submit Issue']")
    WebElement buttonSubmit;


    public ReportIssuePage(WebDriver driver) {
        this.driver = driver;
        faker = new Faker();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void selectCategory(String category) {
        Select select = new Select(categorySelect);
        select.selectByVisibleText(category);
    }

    public void selectReproducibility(String reproducibility) {
        Select select = new Select(reproducibilitySelect);
        select.selectByVisibleText(reproducibility);
    }

    public void fillSummary(String summary) {
        summaryField.sendKeys(summary);
    }

    public void fillRandomSummary() {
        String randomSummary = faker.lorem().sentence();
        summaryField.sendKeys(randomSummary);
    }

    public void fillDescription(String description) {
        descriptionField.sendKeys(description);
    }

    public void fillRandomDescription() {
        String randomDescription = faker.lorem().sentence();
        descriptionField.sendKeys(randomDescription);
    }

    public void clickSubmit() {
        TestUtils.scrollToElement(driver, buttonSubmit);
        buttonSubmit.click();
    }

    public void createNewIssue(String category) {
        selectCategory(category);
        fillRandomSummary();
        fillRandomDescription();
        clickSubmit();
    }

    public void openCreatedIssue() {
        try {
            WebElement buttonViewNewIssue = wait.until(ExpectedConditions.elementToBeClickable
                    (By.xpath("//div[@class = 'alert alert-success center']//a[contains(@href, 'view.php?id=')]")));
            buttonViewNewIssue.click();
        } catch (TimeoutException e) {
            System.out.println();
        }
    }

    public boolean isCreationSuccessful() {
        return CheckAlerts.alertIsPresentByText("Operation successful.", driver, wait);
    }

}
