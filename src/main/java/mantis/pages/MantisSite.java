package mantis.pages;

import org.openqa.selenium.WebDriver;

public class MantisSite {
    private final WebDriver driver;
    private final LoginPage loginPage;
    private final PasswordPage passwordPage;
    private final MainPage mainPage;
    private final ViewIssuesPage viewIssuesPage;
    private final ReportIssuePage reportIssuesPage;

    public MantisSite(WebDriver driver) {
        this.driver = driver;

        loginPage = new LoginPage(driver);
        passwordPage = new PasswordPage(driver);
        mainPage = new MainPage(driver);
        viewIssuesPage = new ViewIssuesPage(driver);
        reportIssuesPage = new ReportIssuePage(driver);
    }

    public void login(String login, String password) {
        loginPage.login(login);
        passwordPage.login(password);
    }

    public LoginPage getLoginPage() {
        return loginPage;
    }

    public PasswordPage getPasswordPage() {
        return passwordPage;
    }

    public MainPage getMainPage() {
        return mainPage;
    }

    public ViewIssuesPage getViewIssuesPage() {
        return viewIssuesPage;
    }

    public ReportIssuePage getReportIssuesPage() {
        return reportIssuesPage;
    }

    public WebDriver getDriver() {
        return driver;
    }
}
