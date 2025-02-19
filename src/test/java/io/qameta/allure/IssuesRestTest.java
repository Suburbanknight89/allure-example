package io.qameta.allure;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.qameta.allure.Allure.parameter;

@Layer("rest")
@Owner("EZaytsev")
@Feature("Issues")
public class IssuesRestTest {

    private static final String OWNER = "allure-framework";
    private static final String REPO = "allure2";

    private final RestSteps steps = new RestSteps();

    @Story("Create new issue")
    @Microservice("Billing")
    @Tags({@Tag("api"), @Tag("smoke")})
    @JiraIssues({@JiraIssue("HOMEWORK-248")})
    @ParameterizedTest(name = "Create issue via api")
    @ValueSource(strings = {"First Note", "Second Note"})
    public void shouldCreateUserNote(String title) {
        parameter("owner", OWNER);
        parameter("repo", REPO);
        parameter("title", title);

        steps.createIssueWithTitle(OWNER, REPO, title);
        steps.shouldSeeIssueWithTitle(OWNER, REPO, title);
    }

    @Story("Close existing issue")
    @Microservice("Repository")
    @Tags({@Tag("web"), @Tag("regress")})
    @JiraIssues({@JiraIssue("HOMEWORK-248")})
    @ParameterizedTest(name = "Close issue via api")
    @ValueSource(strings = {"First Note", "Second Note"})
    public void shouldDeleteUserNote(String title) {
        parameter("owner", OWNER);
        parameter("repo", REPO);
        parameter("title", title);

        steps.createIssueWithTitle(OWNER, REPO, title);
        steps.closeIssueWithTitle(OWNER, REPO, title);
    }


}
