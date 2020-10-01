package StepDefinitions;

import PageObjects.CreditCalculatorPage;
import cucumber.api.java.ru.*;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class CalculateCreditSteps {

    // Инициализация логера
    public static Logger LLOG = LogManager.getLogger(CalculateCreditSteps.class.getName());
    //экземпляр класса страницы с калькулятором
    private CreditCalculatorPage calculatorPage;


    // переход по ссылке и проверка что страница загрузилась
    @Дано("^открываем страницу Кредитный калькулятор$")
    public void открываемСтраницуКредитныйКалькулятор() {
        try{
            calculatorPage =  new CreditCalculatorPage();
            LLOG.debug("Переход по ссылке " + calculatorPage.GetUrl());
            calculatorPage.OpenCalculatorPage();
        }
        catch (Exception e) {
            LLOG.error("Catch Exception!!! ", e);
        }
    }

    @Когда("^в поле срок кредита введено значение \"([^\"]*)\"$")
    public void вПолеСрокКредитаВведеноЗначение(String timeValue) {
        try {
            LLOG.debug("Вводим значение в поле срок кредита");
            //нужно очищать поле и вводить сразу же данные,т.к есть автозаполнение
            calculatorPage.GetCreditTimeInput().sendKeys(Keys.chord(Keys.CONTROL, "a"), timeValue);
            //переключаемся на другой элемент, чтобы сработал ввод значения
            calculatorPage.GetCreditAmountLabel().click();

        }
        catch (Exception e){
            LLOG.error("Catch Exception!!! ", e);
        }
    }

    @Когда("^в поле сумма кредита введено значение \"([^\"]*)\"$")
    public void вПолеСуммаКредитаВведеноЗначение(String creditValue) {
        try {
            LLOG.debug("Вводим значение в поле сумма кредита");
            //нужно очищать поле и вводить сразу же данные,т.к есть автозаполнение
            calculatorPage.GetCreditAmountInput().sendKeys(Keys.chord(Keys.CONTROL, "a"), creditValue);
            //переключаемся на другой элемент, чтобы сработал ввод значения
            calculatorPage.GetCreditAmountLabel().click();
        }
        catch (Exception e){
            LLOG.error("Catch Exception!!! ", e);
        }
    }

    @Тогда("^значение поля срок кредита совпадает с \"([^\"]*)\"$")
    public void значениеПоляСрокКредитаСовпадаетС(String expectedCreditTime) {
        try {
            LLOG.debug("получаем актуальное значение поля срок кредита");
            String actualCreditTime = calculatorPage.GetCreditTimeValue();
            // убираем лишние символы в начале и конце
            actualCreditTime = actualCreditTime.replaceAll("^\\s+|\\s+$","");
            Assert.assertEquals(expectedCreditTime, actualCreditTime);
        }
        catch (Exception e){
            LLOG.error("Catch Exception!!! ", e);
        }
    }

    @Тогда("^значение поля сумма кредита совпадает с \"([^\"]*)\"$")
    public void значениеПоляСуммаКредитаСовпадаетС(String expectedCreditAmount) {
        try {
            LLOG.debug("получаем актуальное значение поля сумма кредита");
            String actualCreditAmountValue = calculatorPage.GetCreditAmountValue();
            // убираем лишние символы
            actualCreditAmountValue = actualCreditAmountValue.replaceAll("\\s+","");
            Assert.assertEquals(expectedCreditAmount, actualCreditAmountValue);
        }
        catch (Exception e){
            LLOG.error("Catch Exception!!! ", e);
        }
    }

    @Тогда("^значение поля ежемесячный платеж совпадает с \"([^\"]*)\"$")
    public void значениеПоляЕжемесячныйПлатежСовпадаетС(String expectedMounthlyPaymentAmount) {
        try {
            LLOG.debug("получаем актуальное значение поля ежемесячный платеж");
            String actualMounthlyPaymentAmount = calculatorPage.GetMounthlyPaymentAmountValue();
            //убираем лишние символы
            actualMounthlyPaymentAmount = actualMounthlyPaymentAmount.replaceAll("\\s*","");
            actualMounthlyPaymentAmount = actualMounthlyPaymentAmount.replaceAll("₽","");
            Assert.assertEquals(expectedMounthlyPaymentAmount, actualMounthlyPaymentAmount);
        }
        catch (Exception e) {
            LLOG.error("Catch Exception!!! ", e);
        }
    }

}