package pages;

import core.BaseFunction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import static org.openqa.selenium.By.*;
import static org.junit.jupiter.api.Assertions.*;

public class BookingPage {
    private BaseFunction baseFunction;
    private final Logger LOG = LogManager.getLogger();
    private final By TOTAL_PRICE = xpath(".//div[@data-qa='_totPrice']");
    private final By BEE_GIF = xpath(".//div[@class='beeGif_beeGif___Ofs7']");

    public BookingPage(BaseFunction baseFunction) {
        this.baseFunction = baseFunction;
    }

    public String getTotalPrice() {
        LOG.info("Getting total booking price");
        waitUntilLoaderDisappears();
        assertTrue(baseFunction.isElementVisible(TOTAL_PRICE), "Total price was not found");
        return baseFunction.getElement(TOTAL_PRICE).getText();
    }

    public void waitUntilLoaderDisappears() {
        LOG.info("Waiting until loader disappears");
        baseFunction.waitUntilElementDisappears(BEE_GIF);
    }
}
