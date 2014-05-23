package example.jbehave;

import org.jbehave.core.annotations.AfterStory;
import org.jbehave.core.annotations.BeforeStory;
import org.jbehave.web.selenium.WebDriverProvider;

public class TestRunLifecycle {
    private final WebDriverProvider webDriverProvider;

    public TestRunLifecycle(WebDriverProvider webDriverProvider) {
        this.webDriverProvider = webDriverProvider;
    }

    @BeforeStory
    public void initWebDriverForThread() {
        webDriverProvider.initialize();
    }

    @AfterStory
    public void closeWebDriverForThread() {
        webDriverProvider.get().quit();
    }
}