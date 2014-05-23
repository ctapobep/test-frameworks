package example.jbehave.pages;

import org.jbehave.web.selenium.FluentWebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.seleniumhq.selenium.fluent.FluentWebElement;

import static junit.framework.TestCase.assertTrue;

public class SearchPage extends FluentWebDriverPage {
    public SearchPage(WebDriverProvider driverProvider) {
        super(driverProvider);
    }

    public void openMain() {
        get("http://google.com");
    }

    public void enterInSearchBox(String word) {
        input(By.id("gbqfq")).sendKeys(word);
    }

    public void submitSearchForm() {
        button(By.id("gbqfb")).click();
        sleep(1000);//google replaces stuff on its page via JS which makes it impossible to work with elements
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void assertSearchResultsContainText(String word) {
        boolean contains = false;
        for (FluentWebElement link : links(By.cssSelector("a[target='_blank']"))) {
            if (link.getText().toString().contains(word)) {
                contains = true;
                break;
            }
        }
        assertTrue("Couldn't find word " + word + " on page", contains);
    }
}
