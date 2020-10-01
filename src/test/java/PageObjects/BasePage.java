package PageObjects;

import Utilities.WebDriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

    //экземпляр браузера
    public static WebDriver browser;
    static {
        try {
            browser = WebDriverSingleton.GetInstance().GetDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static WebDriverWait wait;


    //инициализация элементов страницы
    public BasePage () {

        PageFactory.initElements(browser, this);
    }

    protected void WaitUntilElementVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

}
