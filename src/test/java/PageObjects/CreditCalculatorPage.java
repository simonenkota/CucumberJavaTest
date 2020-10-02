package PageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreditCalculatorPage extends BasePage
{

    //адрес страницы с калькулятором
    private String url = "https://www.raiffeisen.ru/retail/consumerloans/calculator/";

    // инициализация элементов реализована в классе BasePage
    public CreditCalculatorPage () {
        super();
    }

    // //*[@id='app']/../div[./label[contains(text(),'Сумма кредита')]]/div
    //элемент размер кредита
    @FindBy(xpath =  "//div[@data-field='amount.input']")
    private WebElement creditAmountInput;

    //элемент метка размер кредита
    // //*[@id='app']/../label[contains(text(),'Сумма кредита')]
    @FindBy(xpath =  "//div[@data-field='amount']/label")
    private WebElement creditAmountLabel;

    //элемент срок кредита
    // //*[@id='app']/../div[./label[contains(text(),'Срок кредита')]]/div
    @FindBy(xpath = "//div[@data-field='term.input']")
    private WebElement creditTimeInput;

    //элемент ежемесячный платеж
    // //*[@id='app']/..//div[@class='legend sum-value']/p[@class='legend__value']
    @FindBy(xpath = "//div[@data-field='total']/p[@class='legend__value']")
    private WebElement creditMonthlyPayment;

    //элемент ставка по кредиту
    // //*[@id='app']/../div[@class='legend']/p[@class='legend__value']
    @FindBy(xpath = "//div[@data-field='rate']/p[@class='legend__value']")
    private WebElement creditRate;

    //элемент кнопка оформить
    @FindBy(xpath = "*[@id='app']/../button")
    private  WebElement submitButton;

    @Step ("Открываем страницу по адресу")
    //открываем страницу по адресу
    public void OpenCalculatorPage() throws Exception{
        browser.get(url);
    }

    @Step ("Активируем поле ввода срок кредита")
    //получаем доступ к полю ввода срока кредита
    public WebElement GetCreditTimeInput() throws Exception {
       creditTimeInput.click();
        return creditTimeInput.findElement(By.xpath("./input"));
    }

    @Step ("Активируем поле ввода размер кредита")
    //получаем доступ к полю ввода размер кредита
    public WebElement GetCreditAmountInput() throws Exception {
        creditAmountInput.click();
        return creditAmountInput.findElement(By.xpath("./input"));
    }
    //значение элемента срок кредита
    public String GetCreditTimeValue() throws Exception {
        return creditTimeInput.getText();
    }

    //значение элемента размер кредита
    public String GetCreditAmountValue() throws Exception  {
        return creditAmountInput.getText();
    }

    //значение элемента ежемесячный платеж
    public String GetMounthlyPaymentAmountValue() throws Exception {
        return creditMonthlyPayment.getText();
    }

    //элемент метка ежемесячный платеж
    public WebElement GetCreditAmountLabel() throws Exception {
        return creditAmountLabel;
    }

    @Step ("Вводим значение {timeValue} в поле срок кредита")
    //ввод значения в поле срок кредита
    public void SetCreditTimeInput(String timeValue) throws Exception {
       //нужно очищать поле и вводить сразу же данные,т.к есть автозаполнение
       GetCreditTimeInput().sendKeys(Keys.chord(Keys.CONTROL, "a"), timeValue);
       //переключаемся на другой элемент, чтобы сработал ввод значения
       creditAmountLabel.click();
    }

    @Step ("Вводим значение {creditValue} в поле срок кредита")
    //ввод значения в поле размер кредита
    public void SetCreditAmountInput(String creditValue) throws Exception {
        //нужно очищать поле и вводить сразу же данные,т.к есть автозаполнение
        GetCreditAmountInput().sendKeys(Keys.chord(Keys.CONTROL, "a"), creditValue);
        //переключаемся на другой элемент, чтобы сработал ввод значения
        creditAmountLabel.click();
    }

    //адрес страницы
    public String GetUrl() {
        return url;
    }

}
