package StepDefinitions;

import PageObjects.CreditCalculatorPage;
import Utilities.PropertiesReader;
import cucumber.api.PendingException;
import cucumber.api.java.ru.*;
import org.apache.logging.log4j.LogManager;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.KeyInput;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.Logger;

public class CalculateCreditSteps {

    private WebDriver driver = Hooks.driver;
    private WebDriverWait wait;
    //экземпляр класса страницы с калькулятором
    private CreditCalculatorPage pageToOpen = new CreditCalculatorPage(driver, wait);
    // Инициализация логера
    private static final Logger log = LogManager.getLogger();

    public CalculateCreditSteps() throws Exception {

        PropertiesReader propertiesReader = new PropertiesReader();
        //создаем драйвер с найстройкой таймаута из конфигурационного файла
        this.wait = new WebDriverWait(driver, propertiesReader.getTimeout());
        //уроверь логирования берем из конфигурационного файла
        //log
    }


    // переход по ссылке и проверка что страница загрузилась
    @Дано("^открываем страницу Кредитный калькулятор$")
    public void открываемСтраницуКредитныйКалькулятор()
    {
        try
        {

            log.info("Переход по ссылке " + pageToOpen.GetUrl());
            driver.get(pageToOpen.GetUrl());
        }
        catch (Exception e)
        {
            log.error("Catch Exception!!!", e);
        }
    }

    @Когда("^в поле срок кредита введено значение \"([^\"]*)\"$")
    public void вПолеСрокКредитаВведеноЗначение(String timeValue) {
        try
        {
            log.debug("Вводим значение в поле срок кредита");
            //нужно очищать поле и вводить сразу же данные,т.к есть автозаполнение
            pageToOpen.GetCreditTimeInput().sendKeys(Keys.chord(Keys.CONTROL, "a"), timeValue);
            //переключаемся на другой элемент, чтобы сработал ввод значения
            pageToOpen.getCreditAmountLabel().click();

        }
        catch (Exception e)
        {
            log.error("Catch Exception!!!", e);
        }
    }

    @Когда("^в поле сумма кредита введено значение \"([^\"]*)\"$")
    public void вПолеСуммаКредитаВведеноЗначение(String creditValue) {
        try
        {
            log.debug("Вводим значение в поле сумма кредита");
            //нужно очищать поле и вводить сразу же данные,т.к есть автозаполнение
            pageToOpen.GetCreditAmountInput().sendKeys(Keys.chord(Keys.CONTROL, "a"), creditValue);
            //переключаемся на другой элемент, чтобы сработал ввод значения
            pageToOpen.getCreditAmountLabel().click();
        }
        catch (Exception e)
        {
            log.error("Catch Exception!!!", e);
        }
    }

    @Тогда("^значение поля срок кредита совпадает с \"([^\"]*)\"$")
    public void значениеПоляСрокКредитаСовпадаетС(String expectedCreditTime)
    {
        try
        {
            log.debug("получаем актуальное значение поля срок кредита");
            String actualCreditTime = pageToOpen.GetCreditTimeValue();
            // убираем лишние символы в начале и конце
            actualCreditTime = actualCreditTime.replaceAll("^\\s+|\\s+$","");
            Assert.assertEquals(expectedCreditTime, actualCreditTime);
        }
        catch (Exception e)
        {
            log.error("Catch Exception!!!", e);
        }
    }

    @Тогда("^значение поля сумма кредита совпадает с \"([^\"]*)\"$")
    public void значениеПоляСуммаКредитаСовпадаетС(String expectedCreditAmount)
    {
        try
        {
            log.debug("получаем актуальное значение поля сумма кредита");
            String actualCreditAmountValue = pageToOpen.GetCreditAmountValue();
            // убираем лишние символы
            actualCreditAmountValue = actualCreditAmountValue.replaceAll("\\s+","");
            Assert.assertEquals(expectedCreditAmount, actualCreditAmountValue);
        }
        catch (Exception e)
        {
            log.error("Catch Exception!!!", e);
        }
    }

    @Тогда("^значение поля ежемесячный платеж совпадает с \"([^\"]*)\"$")
    public void значениеПоляЕжемесячныйПлатежСовпадаетС(String expectedMounthlyPaymentAmount)
    {
        try
        {
            log.debug("получаем актуальное значение поля ежемесячный платеж");
            String actualMounthlyPaymentAmount = pageToOpen.GetMounthlyPaymentAmountValue();
            //убираем лишние символы
            actualMounthlyPaymentAmount = actualMounthlyPaymentAmount.replaceAll("\\s*","");
            actualMounthlyPaymentAmount = actualMounthlyPaymentAmount.replaceAll("₽","");
            Assert.assertEquals(expectedMounthlyPaymentAmount, actualMounthlyPaymentAmount);
        }
        catch (Exception e)
        {
            log.error("Catch Exception!!!", e);
        }
    }

}