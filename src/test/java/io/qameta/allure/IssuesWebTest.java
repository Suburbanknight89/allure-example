package io.qameta.allure;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

/**
 * @author eroshenkoam (Artem Eroshenko).
 */
@Layer("web")
@Owner("ZaytsevE")
@Feature("Issues")
public class IssuesWebTest {

    private static final String OWNER = "allure-framework";
    private static final String REPO = "allure2";

    private static final String ISSUE_TITLE = "Some issue title here";

    private final WebSteps steps = new WebSteps();

    @BeforeEach
    public void startDriver() {
        steps.startDriver();
    }

    @Test
    @Microservice("Billing")
    @Story("Create new issue")
    @JiraIssues({@JiraIssue("HOMEWORK-248")})
    @Tags({@Tag("web"), @Tag("critical")})
    @DisplayName("Creating new issue authorized user")
    public void shouldCreateIssue() {
        steps.openIssuesPage(OWNER, REPO);
        steps.createIssueWithTitle(ISSUE_TITLE);
        steps.shouldSeeIssueWithTitle(ISSUE_TITLE);
    }

    @Test
    @Microservice("Repository")
    @Story("Create new issue")
    @Tags({@Tag("web"), @Tag("Stable")})
    @JiraIssues({@JiraIssue("HOMEWORK-248")})
    @DisplayName("Adding note to advertisement")
    public void shouldAddLabelToIssue() {
        steps.openIssuesPage(OWNER, REPO);
        steps.createIssueWithTitle(ISSUE_TITLE);
        steps.shouldSeeIssueWithTitle(ISSUE_TITLE);
    }

    @Test
    @Microservice("Repository")
    @Story("Close existing issue")
    @Tags({@Tag("web"), @Tag("regress")})
    @JiraIssues({@JiraIssue("HOMEWORK-248")})
    @DisplayName("Closing new issue for authorized user")
    public void shouldCloseIssue() {
        steps.openIssuesPage(OWNER, REPO);
        steps.createIssueWithTitle(ISSUE_TITLE);
        steps.closeIssueWithTitle(ISSUE_TITLE);
        steps.shouldNotSeeIssueWithTitle(ISSUE_TITLE);
    }

    @AfterEach
    public void stopDriver() {
        steps.stopDriver();
    }

}