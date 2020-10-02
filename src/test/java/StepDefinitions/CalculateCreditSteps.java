package StepDefinitions;

import PageObjects.CreditCalculatorPage;
import io.cucumber.java.ru.*;
import org.junit.Assert;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class CalculateCreditSteps {

    // Инициализация логера
    public static Logger LLOG = LogManager.getLogger(CalculateCreditSteps.class.getName());
    //экземпляр класса страницы с калькулятором
    private CreditCalculatorPage calculatorPage;


    // переход по ссылке и проверка что страница загрузилась
    @Дано("^открываем страницу Кредитный калькулятор$")
    public void OpenPageCreditCacl() throws Exception {
        try{
            calculatorPage =  new CreditCalculatorPage();

            LLOG.debug("Переход по ссылке " + calculatorPage.GetUrl());
            calculatorPage.OpenCalculatorPage();
        }
        catch (Exception e) {
            LLOG.error("Catch Exception!!! ", e.getMessage());
        }
    }
    @Когда("^в поле срок кредита введено значение \"([^\"]*)\"$")
    public void SetCreditTimeValue(String timeValue) throws Exception {
        try {
            LLOG.debug("Вводим значение в поле срок кредита");
            calculatorPage.SetCreditTimeInput(timeValue);
        }
        catch (Exception e){
            LLOG.error("Catch Exception!!! ", e.getMessage());
        }
    }
    @Когда("^в поле сумма кредита введено значение \"([^\"]*)\"$")
    public void SetCreditAmountValue(String creditValue) throws Exception {
        try {
            LLOG.debug("Вводим значение в поле сумма кредита");
            calculatorPage.SetCreditAmountInput(creditValue);
        }
        catch (Exception e){
            LLOG.error("Catch Exception!!! ", e.getMessage());
        }
    }
    @Тогда("^значение поля срок кредита совпадает с \"([^\"]*)\"$")
    public void VerifyCreditTimeValue(String expectedCreditTime) throws Exception {
        try {
            LLOG.debug("получаем актуальное значение поля срок кредита");
            String actualCreditTime = calculatorPage.GetCreditTimeValue();
            // убираем лишние символы в начале и конце
            actualCreditTime = actualCreditTime.replaceAll("^\\s+|\\s+$","");
            Assert.assertEquals("Ожидаемое значение срока кредита отличается от актуального ",expectedCreditTime,actualCreditTime);
        }
        catch (Exception e){
            LLOG.error("Catch Exception!!! ", e.getMessage());
        }
    }
    @Тогда("^значение поля сумма кредита совпадает с \"([^\"]*)\"$")
    public void VerifyCreditAmountValue(String expectedCreditAmount) throws Exception {
        try {
            LLOG.debug("получаем актуальное значение поля сумма кредита");
            String actualCreditAmountValue = calculatorPage.GetCreditAmountValue();
            // убираем лишние символы
            actualCreditAmountValue = actualCreditAmountValue.replaceAll("\\s+","");
            Assert.assertEquals("Ожидаемое значение суммы кредита отличается от актуального  ", expectedCreditAmount, actualCreditAmountValue);
        }
        catch (Exception e){
            LLOG.error("Catch Exception!!! ", e.getMessage());
        }
    }

    @Тогда("^значение поля ежемесячный платеж совпадает с \"([^\"]*)\"$")
    public void VerifyCreditMounthlyPaymentValue(String expectedMounthlyPaymentAmount) throws Exception {
        try {
            LLOG.debug("получаем актуальное значение поля ежемесячный платеж");
            String actualMounthlyPaymentAmount = calculatorPage.GetMounthlyPaymentAmountValue();
            //убираем лишние символы
            actualMounthlyPaymentAmount = actualMounthlyPaymentAmount.replaceAll("\\s*","");
            actualMounthlyPaymentAmount = actualMounthlyPaymentAmount.replaceAll("₽","");
            Assert.assertEquals("Ожидаемое значение ежемесячного платежа отличается от актуального",expectedMounthlyPaymentAmount, actualMounthlyPaymentAmount);
        }
        catch (Exception e) {
            LLOG.error("Catch Exception!!! ", e.getMessage());
        }
    }

}