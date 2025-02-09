package mantis.tests;

import mantis.pages.MantisSite;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
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
    public void issueLifecycleTest () {

        mantisSite = new MantisSite(driver);
        mantisSite.login("admin", "admin20");
        mantisSite.getMainPage().goToReportIssuePage();

        mantisSite.getReportIssuesPage().createNewIssue("[All Projects] General");

        isCreationSuccessful = mantisSite.getReportIssuesPage().isCreationSuccessful();
        actualCreationResult = isCreationSuccessful ? "Тикет создан" : "Ошибка создания тикета";
        Assertions.assertEquals("Тикет создан", actualCreationResult);

        mantisSite.getReportIssuesPage().openCreatedIssue();
        issueId = mantisSite.getViewIssuesPage().getBugIdFromBugPage();
        mantisSite.getViewIssuesPage().deleteIssue();

        mantisSite.getViewIssuesPage().searchIssueById(issueId);
        isIssueDeleted = mantisSite.getViewIssuesPage().isIssueDeletedOrNotFound(issueId);
        actualDeletedResult = isIssueDeleted ? "Тикет удален" : "Ошибка удаления тикета";
        Assertions.assertEquals("Тикет удален", actualDeletedResult);
    }

}
