import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class CT6 {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        String s = System.setProperty("webdriver.gecko.driver", "C:\\Driver_chrome\\geckodriver.exe");
        //options.addArguments("--disable-extensions");
        //options.addArguments("start-maximized");
        //driver.manage().window().maximize();
        //driver.manage().window().fullscreen();
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testConn() throws Exception {
        driver.get("https://www.oui.sncf/");
        driver.findElement(By.id("vsb-origin-train-launch")).click();
        driver.findElement(By.id("vsb-origin-train-launch")).clear();
        driver.findElement(By.id("vsb-origin-train-launch")).sendKeys("Nantes (toutes gares)");
        driver.findElement(By.id("vsb-origin-0")).click();
        driver.findElement(By.id("vsb-destination-train-launch")).click();
        driver.findElement(By.id("vsb-destination-train-launch")).clear();
        driver.findElement(By.id("vsb-destination-train-launch")).sendKeys("Paris (toutes gares intramuros)");
        driver.findElement(By.id("vsb-destination-0")).click();

        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='dates et horaires'])[1]/following::div[1]")).click();
        driver.findElement(By.id("train-launch-d-24-01-2020")).click();

        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='voir le mois précedent'])[2]/following::span[1]")).click();
        driver.findElement(By.id("train-launch-d-24-01-2020")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Sans carte de réduction'])[1]/following::span[2]")).click();
        driver.findElement(By.id("passenger_1_train-launch-typos-in-modal-typology")).click();
        new Select(driver.findElement(By.id("passenger_1_train-launch-typos-in-modal-typology"))).selectByVisibleText("12-25 ans");
        driver.findElement(By.id("passenger_1_train-launch-typos-in-modal-typology")).click();
        driver.findElement(By.id("passenger_1_train-launch-typos-in-modal_age")).click();
        driver.findElement(By.id("passenger_1_train-launch-typos-in-modal_age")).clear();
        driver.findElement(By.id("passenger_1_train-launch-typos-in-modal_age")).sendKeys("24");
        driver.findElement(By.id("passenger_1_train-launch-discount-card-type")).click();
        driver.findElement(By.id("passenger_1_train-launch-discount-card-type")).click();
        driver.findElement(By.id("passenger_1_train-launch-fidelity-card-type")).click();
        new Select(driver.findElement(By.id("passenger_1_train-launch-fidelity-card-type"))).selectByVisibleText("Voyageur");
        driver.findElement(By.id("passenger_1_train-launch-fidelity-card-type")).click();
        driver.findElement(By.id("passenger_1_train-launch-fidelity-card-number")).click();
        driver.findElement(By.id("passenger_1_train-launch-fidelity-card-number")).clear();
        driver.findElement(By.id("passenger_1_train-launch-fidelity-card-number")).sendKeys("156685655");
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)=concat('Code avantage ou Bon d', \"'\", 'achat')])[1]/following::span[1]")).click();


        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Horaires seuls'])[1]/following::span[1]")).click();

    }


    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
