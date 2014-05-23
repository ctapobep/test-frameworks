package example.jbehave;

import example.jbehave.pages.SearchPage;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.web.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.List;

import static java.util.Arrays.asList;
import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;

/**
 * In real projects you will use jbehave+selenium integration much heavier and won't create steps manually.
 */
public class IncludeAllStories extends JUnitStories {
    @Override
    public InjectableStepsFactory stepsFactory() {
        if (System.getProperty("REMOTE_WEBDRIVER_URL") == null) {
            System.setProperty("REMOTE_WEBDRIVER_URL", "http://localhost:4444/wd/hub");
        }
        RemoteWebDriverProvider driverProvider = driverProvider();
        return new InstanceStepsFactory(configuration(),
                new GoogleSteps(new SearchPage(driverProvider)), new TestRunLifecycle(driverProvider));
    }

    @Override
    protected List<String> storyPaths() {
        return new StoryFinder().findPaths(codeLocationFromClass(this.getClass()).getFile(),
                asList("**/*.story"), null);
    }

    @Override
    public Configuration configuration() {
        return new MostUsefulConfiguration()
                .useStoryReporterBuilder(new StoryReporterBuilder().withFormats(Format.HTML, Format.ANSI_CONSOLE)
                        .withFailureTrace(true));
    }

    private RemoteWebDriverProvider driverProvider() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("firefox");
        capabilities.setVersion("24");
        return new RemoteWebDriverProvider(capabilities);
    }
}
