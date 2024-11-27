package pageobject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderModalWindow {
    private WebDriver driver;

    //кнопка "Да" в модальном окне "Хотите оформить заказ?"
    private By yesButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");


    public OrderModalWindow(WebDriver driver){
        this.driver = driver;
    }

    public void waitWhenYesButtonAppears(){
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOf(driver.findElement(yesButton)));
    }

    public void clickOnYesButton(){
        driver.findElement(yesButton).click();
    }


}
