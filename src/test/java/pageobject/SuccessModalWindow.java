package pageobject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class SuccessModalWindow {
    private WebDriver driver;

    //кнопка "Посмотреть статус" в модальном окне "Заказ оформлен"
    private By buttonSeeStatus = By.xpath(".//button[text()='Посмотреть статус']");

    public SuccessModalWindow(WebDriver driver){
        this.driver = driver;
    }

    public void waitWhenButtonSeeStatusAppears(){
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOf(driver.findElement(buttonSeeStatus)));
    }

    public void getTextFromButtonSeeStatusAndCheck(){
        String result = driver.findElement(buttonSeeStatus).getText();
        String expected = "Посмотреть статус";
        assertEquals(expected, result);
    }

}
