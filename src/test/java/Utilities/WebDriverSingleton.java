package Utilities;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class WebDriverSingleton {

    private static WebDriver driver = null;
    private WebDriverWait wait;

    private WebDriverSingleton() throws Exception {
        //устанавливаем свойства браузера
        PropertiesReader propertiesReader = new PropertiesReader();
        System.setProperty("webdriver.chrome.driver", propertiesReader.GetDriverPath());
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = (WebDriverWait) new WebDriverWait(driver, propertiesReader.GetTimeout()).withMessage("Element was not found");
        driver.manage().timeouts().implicitlyWait(propertiesReader.GetTimeout(), TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
    //создать новый экземпляр только если он еще не был создан
    //получить ссылку на текущий драйвер
    public static WebDriver GetDriver() throws Exception {
        if (driver == null) {
            new WebDriverSingleton();
        }
        return driver;
    }

    public WebDriverWait GetWait() {
        return wait;
    }


}
