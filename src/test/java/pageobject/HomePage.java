package pageobject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class HomePage {

    private WebDriver driver;

    //url главной страницы scooter
    private final String URL_MAIN_PAGE = "https://qa-scooter.praktikum-services.ru/";

    //локатор свернутых аккордеонов в разделе "Вопросы о важном"
    private String accordionItemButton = ".//div[@id='accordion__heading-%s']";

    //локатор развернутых аккордеонов в разделе "Вопросы о важном"
    private String accordionItemPanel = ".//div[@id='accordion__panel-%s']";

    //кнопка "Заказать" в шапке страницы
    private By orderButtonInHeader = By.xpath(".//div/button[@class='Button_Button__ra12g']");

    //кнопка "Заказать" в блоке "Как это работает"
    private By orderButtonInHowDoesThisWork = By.xpath(".//div/button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public void openMainPage(){
        driver.get(URL_MAIN_PAGE);
    }
    public void scrollToAccordionItemButton(String index){
        WebElement element = driver.findElement(By.xpath(String.format(accordionItemButton, index)));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }
    public void clickOnAccordionItemButton(String index){
        driver.findElement(By.xpath(String.format(accordionItemButton, index))).click();
    }
    public void waitWhenTextFromAccordionItemPanelAppears(String index){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(String.format(accordionItemPanel ,index)))));
    }
    public void getTextFromAccordionItemPanelAndCheck(String index, String expected){
        String result = driver.findElement(By.xpath(String.format(accordionItemPanel, index))).getText();
        assertEquals(expected, result);
    }

    //заказ самоката
    public void clickOnOrderButtonInHeader(){
        driver.findElement(orderButtonInHeader).click();
    }

    public void scrollToOrderButtonInHowDoesThisWork(){
        WebElement element = driver.findElement(orderButtonInHowDoesThisWork);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void clickOnOrderButtonInHowDoesThisWork(){
        driver.findElement(orderButtonInHowDoesThisWork).click();
    }

}
