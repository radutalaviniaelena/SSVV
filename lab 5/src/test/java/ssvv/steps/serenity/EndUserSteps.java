package ssvv.steps.serenity;

import org.openqa.selenium.WebDriver;
import ssvv.pages.DictionaryPage;
import net.thucydides.core.annotations.Step;

public class EndUserSteps {

    DictionaryPage dictionaryPage;

    @Step
    public void enters(String keyword) {
        dictionaryPage.enter_keywords(keyword);
    }

    @Step
    public void starts_search() {
        dictionaryPage.lookup_terms();
    }

    @Step
    public void should_see_definition(String ourDefinition, String searchedDefinition) {
        assert(searchedDefinition.equals(ourDefinition));
    }

    @Step
    public void verify_number_of_hotels(String ourNumber, String filteredNumber) {
        assert(filteredNumber.equals(ourNumber));
    }

    @Step
    public void is_the_home_page() {
        dictionaryPage.open();
    }

    @Step
    public void looks_for(String term) {
        enters(term);
        starts_search();
    }

    @Step
    public void filter_by_destination(String destination, WebDriver webDriver) throws InterruptedException {
        dictionaryPage.filter_by_destination(destination, webDriver);
    }
}