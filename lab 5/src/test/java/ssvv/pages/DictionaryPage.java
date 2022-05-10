package ssvv.pages;

import net.thucydides.core.annotations.DefaultUrl;
import net.serenitybdd.core.pages.WebElementFacade;

import net.serenitybdd.core.annotations.findby.FindBy;

import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@DefaultUrl("https://www.eximtur.ro/")
public class DictionaryPage extends PageObject {

    @FindBy(xpath = "/html/body/div[58]/div[1]/div[3]/div[1]/div[1]/div/div[1]/input")
    private WebElementFacade searchTerms;

    @FindBy(xpath = "/html/body/div[58]/div[1]/div[3]/div[1]/div[1]/div/div[2]/a[2]")
    private WebElementFacade lookupButton;

    @FindBy(xpath = "/html/body/div[60]/div/div[2]/div/div[2]/div[1]/div[1]/div/div/a")
    private WebElementFacade navigateToAllHotels;

    @FindBy(xpath = "/html/body/div[60]/div/div[2]/div/div/div[1]/div[2]/div/div/div[1]/div[3]/div[2]/div[5]/div/input")
    private WebElementFacade filterButton;

    public void enter_keywords(String keyword) {
        searchTerms.type(keyword);
    }

    public void lookup_terms() {
        lookupButton.click();
    }

    public void filter_by_destination(String destination, WebDriver webDriver) throws InterruptedException {
        navigateToAllHotels.click();
        get_checkbox_and_click_it(destination, webDriver);
    }

    public void get_checkbox_and_click_it(String destination, WebDriver webDriver) {
        String id = "location~" + destination;

        WebElement webElement = webDriver.findElement(By.id(id));
        JavascriptExecutor executor = (JavascriptExecutor)webDriver;
        executor.executeScript("arguments[0].click();", webElement);
    }
}