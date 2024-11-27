package pageobject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPageSecondStep {
    private WebDriver driver;

    //поле "Когда привезти самокат"
    private By dateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");

    //выпадающий список "Срок аренды"
    private By rentalPeriodDropdown = By.className("Dropdown-arrow");

    //<количество дней> из выпадающего списка "Срок аренды"
    private String numberOfDaysLocator = ".//div[text()='%s']";

    //чек-бокс с цветом ("черный жемчуг" или "серая безысходность")
    private String checkboxLocator = ".//input[@id='%s']";

    //поле "Комментарий для курьера"
    private By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    //кнопка "Заказать"
    private By orderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");

    public OrderPageSecondStep(WebDriver driver){
        this.driver = driver;
    }

    public void waitWhenDateFieldAppears(){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOf(driver.findElement(dateField)));
    }

    public void setDateField(String date) {
        driver.findElement(dateField).sendKeys(date);
    }

    public void setRentalPeriodField(String numberOfDays) {
        driver.findElement(rentalPeriodDropdown).click();
        driver.findElement(By.xpath(String.format(numberOfDaysLocator, numberOfDays))).click();
    }

    public void clickOnColorSelectionCheckbox(String checkbox) {
        driver.findElement(By.xpath(String.format(checkboxLocator, checkbox))).click();
    }

    public void setCommentField(String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }

    public void clickOnOrderButton(){
        driver.findElement(orderButton).click();
    }

    public void fillOutSecondStepOrderForm(String date, String numberOfDays, String checkbox, String comment){
        waitWhenDateFieldAppears();
        setDateField(date);
        setRentalPeriodField(numberOfDays);
        clickOnColorSelectionCheckbox(checkbox);
        setCommentField(comment);
        clickOnOrderButton();
    }
}
