package pageobject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.CoreMatchers.containsString;
import org.hamcrest.MatcherAssert;

public class SuccessModalWindow {
    private WebDriver driver;

    //текст "Заказ оформлен" в модальном окне
    private By textOrderPlaced = By.xpath(".//div[text()='Заказ оформлен']");

    public SuccessModalWindow(WebDriver driver){
        this.driver = driver;
    }

    public void waitWhenTextOrderPlacedAppears(){
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOf(driver.findElement(textOrderPlaced)));
    }

    public void getTextOrderPlacedAndCheck(){
        String expected = "Заказ оформлен";
        String result = driver.findElement(textOrderPlaced).getText();
        MatcherAssert.assertThat(result, containsString(expected));
    }
}
