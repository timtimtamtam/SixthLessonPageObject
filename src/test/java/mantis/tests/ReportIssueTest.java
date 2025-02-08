package mantis.tests;

import mantis.pages.MantisSite;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

public class ReportIssueTest extends BaseTest {

    MantisSite mantisSite;
    SoftAssertions softAssert = new SoftAssertions();
    String issueId;
    boolean isCreationSuccessful;
    String actualCreationResult;
    String actualDeletedResult;
    boolean isIssueDeleted;

    @Test
    public void createdReport() {

        mantisSite = new MantisSite(driver);
        mantisSite.login("admin", "admin20");
        mantisSite.getMainPage().goToReportIssuePage();

        mantisSite.getReportIssuesPage().selectCategory("[All Projects] General");
        mantisSite.getReportIssuesPage().fillRandomSummary();
        mantisSite.getReportIssuesPage().fillRandomDescription();
        mantisSite.getReportIssuesPage().clickSubmit();

        isCreationSuccessful = mantisSite.getReportIssuesPage().isCreationSuccessful();
        actualCreationResult = isCreationSuccessful ? "Тикет создан" : "Ошибка создания тикета";
        softAssert.assertThat(actualCreationResult).isEqualTo("Тикет создан");

        mantisSite.getReportIssuesPage().openNewIssue();
        issueId = mantisSite.getViewIssuesPage().getBugId();
        mantisSite.getViewIssuesPage().deleteIssue();

        mantisSite.getViewIssuesPage().searchIssueById(issueId);
        isIssueDeleted = mantisSite.getViewIssuesPage().isIssueDeletedOrNotFound(issueId);
        actualDeletedResult = isIssueDeleted ? "Тикет удален" : "Ошибка удаления тикета";
        softAssert.assertThat(actualDeletedResult).isEqualTo("Тикет удален");

        softAssert.assertAll();
    }

}
