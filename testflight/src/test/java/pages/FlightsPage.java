package pages;

import core.BaseFunction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.openqa.selenium.By.*;

public class FlightsPage {

    private BaseFunction baseFunction;
    private final Logger LOG = LogManager.getLogger();
    private final By PROGRESS_BAR = cssSelector("div#nprogress");
    private final By FLIGHTS = xpath(".//div[@data-qa='pq-option']");
    private final By FLIGHT_DATE = xpath(".//div[@data-qa='pqDateFrom']");
    private final By DEPARTURE_DATE = xpath(".//input[@data-qa='dateFrom']");
    private final By SHOW_MORE_BTN = cssSelector(".qa-moreResultsBtn");
    private final By SAME_FLIGHTS_BTN = cssSelector(".qa-showTripsSamePrice");
    private final By FLIGHT_PRICE = xpath(".//div[@data-qa='pq-price']");
    private final By PREBOOK_PRICE = cssSelector("div.l-h-1.price");
    private final By BOOK_FLIGHT_BTN = cssSelector("button.resultBook_pqResultDetailsBookBtn__DyATi");

    public FlightsPage(BaseFunction baseFunction) {
        this.baseFunction = baseFunction;
    }

    public List<WebElement> getFlightDates() {
        LOG.info("Getting list of flight dates");
        loadAllFlights();
        return baseFunction.getElements(FLIGHT_DATE);
    }

    public List<WebElement> getFlights() {
        LOG.info("Getting list of flights");
        return baseFunction.getElements(FLIGHTS);
    }

    public String getBookingPrice(int i) {
        LOG.info("Getting price for " + (i+1) + " flight from the list");
        List<WebElement> prices = baseFunction.getElements(FLIGHT_PRICE);
        return prices.get(i).getText();
    }

    public String getPreBookPrice() {
        return baseFunction.getElement(PREBOOK_PRICE).getText();
    }

    public void waitForProgressBarDisappear() {
        LOG.info("Waiting for progress bar to disappear");
        baseFunction.waitUntilElementDisappears(PROGRESS_BAR);
    }

    public void loadAllFlights() {
        while (baseFunction.isElementPresent(SHOW_MORE_BTN) == true) {
            WebElement showMore = baseFunction.getElement(SHOW_MORE_BTN);
            baseFunction.scrollToElement(showMore);
            baseFunction.click(SHOW_MORE_BTN);
        }

        while (baseFunction.isElementPresent(SAME_FLIGHTS_BTN) == true) {
            WebElement sameFlights = baseFunction.getElement(SAME_FLIGHTS_BTN);
            baseFunction.scrollToElement(sameFlights);
            baseFunction.click(SAME_FLIGHTS_BTN);
        }
    }

    public void selectFlight(int i) {
        LOG.info("Selecting " + (i+1) + " flight from the list");
        getFlights().get(i).click();
    }

    public BookingPage bookFlight() {
        LOG.info("Booking selected flight");
        baseFunction.click(BOOK_FLIGHT_BTN);
        return new BookingPage(baseFunction);
    }
    public String getDepartureDate() {
        return baseFunction.getElement(DEPARTURE_DATE).getAttribute("value");
    }
}
