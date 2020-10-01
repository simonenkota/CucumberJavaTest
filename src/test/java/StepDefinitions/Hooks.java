package StepDefinitions;

import Utilities.WebDriverSingleton;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;

public class Hooks {

    //флаг для отслеживания начала выполнения сценариев
    public static boolean isFirstScenario = true;

    //открываем браузер в начале запуска сценариев
    @Before
    public static void OpenBrowser() throws Exception {
       if (isFirstScenario) {
        WebDriverSingleton.GetInstance().GetDriver();
        isFirstScenario = false;
       }
    }


    //в случае ошибки в сценарии - делаем скриншот
    @After
    public void EmbedScreenshot(Scenario scenario) throws Exception {
        if(scenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot) WebDriverSingleton.GetInstance().GetDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            } catch (WebDriverException noSupportScreenshot) {
                System.err.println(noSupportScreenshot.getMessage());
            }
        }
    }
}
