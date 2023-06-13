package tests;

import core.BaseFunction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import pages.BookingPage;
import pages.FlightsPage;
import pages.modals.CookiesBanner;
import pages.modals.SubscriptionModal;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FlightTest {

    private BaseFunction baseFunction = new BaseFunction();
    private CookiesBanner cookiesBanner = new CookiesBanner(baseFunction);
    private SubscriptionModal subscriptionModal = new SubscriptionModal(baseFunction);
    private FlightsPage flightsPage = new FlightsPage(baseFunction);
    private Logger LOG = LogManager.getLogger();
    private final String FLIGHTS_URL = "https://www.oojo.com/result/NYC-LHE/2023-07-08/business";

    @BeforeEach
    public void beforeTest() {
        baseFunction.goToUrl(FLIGHTS_URL);
        subscriptionModal.closeSubscriptionModal();
        cookiesBanner.acceptCookies();
        flightsPage.waitForProgressBarDisappear();
    }

    @Test
    public void departureDateTest(){
        LOG.info("This test checks whether flight departure date matches with date in search criteria");

        FlightsPage flightsPage = new FlightsPage(baseFunction);
        List<WebElement> flights = flightsPage.getFlightDates();
        String departureDate = flightsPage.getDepartureDate();

        LOG.info("Asserting flight dates with selected date");
        for (WebElement we : flights) {
            assertEquals(departureDate, we.getText(), "Dates are not equal");
        }
    }

    @Test 
    public void flightPriceTest(){
        LOG.info("This test checks whether flight departure date matches with date in search criteria");

        FlightsPage flightsPage = new FlightsPage(baseFunction);
        String bookingPrice = flightsPage.getBookingPrice(1);

        flightsPage.selectFlight(1);
        String preBookPrice = flightsPage.getPreBookPrice();

        LOG.info("Asserting booking and prebook price");
        assertEquals(bookingPrice, preBookPrice, "Prices are not equal");

        BookingPage bookingPage = flightsPage.bookFlight();
        String totalPrice = bookingPage.getTotalPrice();

        LOG.info("Asserting total price with booking and prebook price");
        assertEquals(bookingPrice, totalPrice, "Prices are not equal");
        assertEquals(preBookPrice, totalPrice, "Prices are not equal");
    }

    @AfterEach
    public void closeBrowser() {
        baseFunction.closeBrowser();
    }
}
