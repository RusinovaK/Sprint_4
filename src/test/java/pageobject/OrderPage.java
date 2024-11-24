package pageobject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {

    private WebDriver driver;

    //кнопка куки
    private By cookiesButton = By.xpath(".//button[text()='да все привыкли']");

    //поле "Имя"
    private By nameField = By.xpath(".//input[@placeholder='* Имя']");

    //поле "Фамилия"
    private By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");

    //поле "Адрес"
    private By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");

    //выпадающий список "Станция метро"
    private By metroField = By.xpath(".//input[@placeholder='* Станция метро']");

    //поле "Телефон"
    private By phoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    //кнопка "Далее"
    private By nextButton = By.xpath(".//button[text()='Далее']");

    public OrderPage(WebDriver driver){
        this.driver = driver;
    }

    //заказ самоката

    public void waitWhenNameFieldAppears(){
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOf(driver.findElement(nameField)));
    }

    public void setNameField(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void setSurnameField(String surname) {
        driver.findElement(surnameField).sendKeys(surname);
    }

    public void setAddressField(String address) {
        driver.findElement(addressField).sendKeys(address);
    }

    public void setMetroField(String metro){
        driver.findElement(metroField).sendKeys(metro);
        driver.findElement(By.className("select-search__select")).click();
    }

    public void setPhoneField(String phone) {
        driver.findElement(phoneField).sendKeys(phone);
    }

    public void clickOnCookiesButton(){
        driver.findElement(cookiesButton).click();
    }

    public void clickOnNextButton(){
        driver.findElement(nextButton).click();
    }

    public void fillOutFirstStepOrderForm(String name, String surname, String address, String metro, String phone){
        waitWhenNameFieldAppears();
        setNameField(name);
        setSurnameField(surname);
        setAddressField(address);
        setMetroField(metro);
        setPhoneField(phone);
        clickOnCookiesButton();
        clickOnNextButton();
    }
}
