package pages.modals;

import core.BaseFunction;
import org.openqa.selenium.By;

public class SubscriptionModal {

    private BaseFunction baseFunction;
    private final By CANCEL_BTN = By.cssSelector("a.underline.pointer");

    public SubscriptionModal(BaseFunction baseFunction) {
        this.baseFunction = baseFunction;
    }

    public void closeSubscriptionModal() {
        baseFunction.click(CANCEL_BTN);
    }
}
