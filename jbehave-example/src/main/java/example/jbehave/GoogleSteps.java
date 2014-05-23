package example.jbehave;

import example.jbehave.pages.SearchPage;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;

public class GoogleSteps {
    private final SearchPage search;

    public GoogleSteps(SearchPage search) {
        this.search = search;
    }

    @Given("we search for $word")
    public void search(String word) {
        search.openMain();
        search.enterInSearchBox(word);
        search.submitSearchForm();
    }

    @Then("word $word is found on page")
    public void findWord(String word) {
        search.assertSearchResultsContainText(word);
    }
}
