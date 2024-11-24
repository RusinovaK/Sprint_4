import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import pageobject.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;

@RunWith(Parameterized.class)
public class TestSectionGuestionsAboutImportantThings {
    private WebDriver driver;

    private String index;
    private String expected;

    public TestSectionGuestionsAboutImportantThings(String index, String expected){
        this.index = index;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {"0", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"1", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {"2", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {"3", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {"4", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {"5", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {"6", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {"7", "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
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
    public void checkWhenClickOnAccordionItemButtonOpenCorrectText(){

        HomePage objHomePage = new HomePage(driver);
        objHomePage.openMainPage();
        objHomePage.scrollToAccordionItemButton(index);
        objHomePage.clickOnAccordionItemButton(index);
        objHomePage.waitWhenTextFromAccordionItemPanelAppears(index);
        objHomePage.getTextFromAccordionItemPanelAndCheck(index, expected);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
