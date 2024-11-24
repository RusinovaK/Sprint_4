import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import pageobject.*;

@RunWith(Parameterized.class)
public class TestOrderAScooter {
    private WebDriver driver;

    private String name;
    private String surname;
    private String address;
    private String metro;
    private String phone;
    private String date;
    private String numberOfDays;
    private String checkbox;
    private String comment;

    public TestOrderAScooter(String name, String surname, String address, String metro, String phone, String date, String numberOfDays, String checkbox, String comment){
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.numberOfDays = numberOfDays;
        this.checkbox = checkbox;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {"Ксения", "Русинова", "г.Москва, ул.Воздвиженка, д.9", "Балтийская", "89123456789", "13.12.2024", "двое суток", "black", "12345"},
                {"Кирилл", "Новиков", "г.Москва, ул.Верхняя Радищевская, д.20", "Спартак", "+79199021221", "11.01.2025", "трое суток", "grey", "54321"}
        };
    }

    @Before
    public void setUp() {
        switch (System.getProperty("browser","chrome")) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
                driver = new ChromeDriver(options);
        }
    }

    @Test
    public void checkOrderAScooterEntryButtonInHeaderPositiveScenario(){

        HomePage objHomePage = new HomePage(driver);
        objHomePage.openMainPage();
        objHomePage.clickOnOrderButtonInHeader();

        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.fillOutFirstStepOrderForm(name, surname, address, metro, phone);

        OrderPageSecondStep objOrderPageSecondStep = new OrderPageSecondStep(driver);
        objOrderPageSecondStep.fillOutSecondStepOrderForm(date, numberOfDays, checkbox, comment);

        OrderModalWindow objOrderModalWindow = new OrderModalWindow(driver);
        objOrderModalWindow.waitWhenYesButtonAppears();
        objOrderModalWindow.clickOnYesButton();

        SuccessModalWindow objSuccessModalWindow = new SuccessModalWindow(driver);
        objSuccessModalWindow.waitWhenTextOrderPlacedAppears();
        objSuccessModalWindow.getTextOrderPlacedAndCheck();
    }

    @Test
    public void checkOrderAScooterEntryButtonInHowDoesThisWorkPositiveScenario(){

        HomePage objHomePage = new HomePage(driver);
        objHomePage.openMainPage();
        objHomePage.scrollToOrderButtonInHowDoesThisWork();
        objHomePage.clickOnOrderButtonInHowDoesThisWork();

        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.fillOutFirstStepOrderForm(name, surname, address, metro, phone);

        OrderPageSecondStep objOrderPageSecondStep = new OrderPageSecondStep(driver);
        objOrderPageSecondStep.fillOutSecondStepOrderForm(date, numberOfDays, checkbox, comment);

        OrderModalWindow objOrderModalWindow = new OrderModalWindow(driver);
        objOrderModalWindow.waitWhenYesButtonAppears();
        objOrderModalWindow.clickOnYesButton();

        SuccessModalWindow objSuccessModalWindow = new SuccessModalWindow(driver);
        objSuccessModalWindow.waitWhenTextOrderPlacedAppears();
        objSuccessModalWindow.getTextOrderPlacedAndCheck();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
