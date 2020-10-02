import Utilities.WebDriverSingleton;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "StepDefinitions",
        tags = "@positive",
        plugin = {
                "io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm",
                "progress",
                "summary"
        }

)
public class TestRunner {

    //закрываем браузер после всех сценариев
    @AfterClass
    public static void CleanUp () throws Exception {
        WebDriverSingleton.GetDriver().quit();
    }
}