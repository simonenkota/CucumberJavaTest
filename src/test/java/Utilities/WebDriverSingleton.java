package Utilities;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class WebDriverSingleton {

    private static WebDriverSingleton instance = null;
    private WebDriver driver;
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
    //создаем новый экземпляр только если он еще не был создан
    public static WebDriverSingleton GetInstance() throws Exception {
        if (instance == null) {
            instance = new WebDriverSingleton();
        }
        return instance;
    }

    //получить ссылку на текущий драйвер
    public WebDriver GetDriver() {
        return driver;
    }

    public WebDriverWait GetWait() {
        return wait;
    }


}
