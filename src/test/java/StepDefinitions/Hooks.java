package StepDefinitions;

import Utilities.WebDriverSingleton;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;

public class Hooks {

    //флаг для отслеживания начала выполнения сценариев
    public static boolean isFirstScenario = true;

    //открыть браузер в начале запуска сценариев
    @Before
    public static void OpenBrowser() throws Exception {
       if (isFirstScenario) {
        WebDriverSingleton.GetDriver();
        isFirstScenario = false;
       }
    }


    //в случае ошибки в сценарии - делаем скриншот
    @After
    public void EmbedScreenshot(Scenario scenario) throws Exception {
        if(scenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot) WebDriverSingleton.GetDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png","f");
            } catch (WebDriverException noSupportScreenshot) {
                System.err.println(noSupportScreenshot.getMessage());
            }
        }
    }
}
