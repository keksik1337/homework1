package pages.modals;

import core.BaseFunction;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import static org.openqa.selenium.By.*;

public class CookiesBanner {

    private BaseFunction baseFunction;
    private final By ACCEPT_COOKIES = cssSelector("button#onetrust-accept-btn-handler");
    private final By COOKIES_BANNER = cssSelector("div#onetrust-banner-sdk");
    private final By COOKIES_BANNER_TEXT = cssSelector("p#onetrust-policy-text");

    public CookiesBanner(BaseFunction baseFunction) {
        this.baseFunction = baseFunction;
    }

    public void acceptCookies() {
        Assertions.assertTrue(baseFunction.isElementVisible(COOKIES_BANNER));
        Assertions.assertEquals("By clicking “Accept All Cookies”, you agree to the storing of cookies on " +
                        "your device to enhance site navigation, analyze site usage, and assist in our marketing " +
                        "efforts. Cookie Policy", baseFunction.getElement(COOKIES_BANNER_TEXT).getText());
        baseFunction.click(ACCEPT_COOKIES);
    }
}
