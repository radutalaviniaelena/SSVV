package ssvv.features.search;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.Qualifier;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ssvv.steps.serenity.EndUserSteps;

@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom("src/test/resources/FilterTestData.csv")
public class FilterByDestinationDdt {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    public String destination;
    public String numberOfHotels;

    @Qualifier
    public String getQualifier() {
        return destination;
    }

    @Steps
    public EndUserSteps endUser;

    @Test
    public void filter_by_given_destination_should_display_number_of_hotels() throws InterruptedException {
        endUser.is_the_home_page();
        endUser.filter_by_destination(getDestination(), webdriver);

        WebElement webElement = webdriver.findElement(By.xpath("/html/body/div[60]/div/div[2]/div/div/div[2]/div[1]/div/div[1]/p[2]"));
        String searchedDefinition = webElement.getText();

        endUser.verify_number_of_hotels(getNumberOfHotels(), searchedDefinition);
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getNumberOfHotels() {
        return numberOfHotels;
    }

    public void setNumberOfHotels(String numberOfHotels) {
        this.numberOfHotels = numberOfHotels;
    }
}
