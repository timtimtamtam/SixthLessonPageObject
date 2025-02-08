package mantis.tests;

import mantis.pages.MantisSite;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ViewIssuesTests extends BaseTest {

    private MantisSite mantisSite;

    @Test
    public void checkIssuesNumber () throws InterruptedException {
        mantisSite = new MantisSite(driver);
        mantisSite.login("admin", "admin20");
        mantisSite.getMainPage().goToViewIssuesPage();

        mantisSite.getViewIssuesPage().scrollToTableFooter();

        int actualIssuesNumber = mantisSite.getViewIssuesPage().countIssues();
        int expectedIssuesNumber = 50;

        Assertions.assertEquals(expectedIssuesNumber, actualIssuesNumber);

    }

}
