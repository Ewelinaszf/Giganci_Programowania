package Base;

import Utilis.Actions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Hook {


    public static WebDriver driver;


    protected Properties config;
    public Actions actions;

    @BeforeEach
    public void setUp() {
        try {
            // Wczytanie konfiguracji z pliku
            config = new Properties();
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\Properties\\Config.properties");
            config.load(fis);

            // Ustawienie ścieżki do chromedrivera
            String driverPath = System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe";
            System.setProperty("webdriver.chrome.driver", driverPath);

            // Inicjalizacja WebDrivera
            ChromeOptions options = new ChromeOptions();
            driver = new ChromeDriver(options);

            // Konfiguracja WebDrivera
            driver.manage().window().maximize();
            int implicitWait = Integer.parseInt(config.getProperty("implicit.wait", "10"));
            driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);

            // Otwieranie strony testowej
            String testSiteUrl = config.getProperty("testsiteurl");
            if (testSiteUrl == null) {
                throw new IllegalArgumentException("Brak klucza 'testsiteurl' w pliku config.properties");
            }
            driver.get(testSiteUrl);
            actions = new Actions(driver);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Błąd podczas inicjalizacji WebDrivera lub konfiguracji");
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
