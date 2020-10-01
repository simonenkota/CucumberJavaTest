package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class CreditCalculatorPage extends BasePage
{

   // инициализация элементов реализована в классе BasePage
    public CreditCalculatorPage () {
        super();
    }
    //адрес страницы с калькулятором
    private String url = "https://www.raiffeisen.ru/retail/consumerloans/calculator/";

    //базовый фрейм калькулятора
    private final String creditCalc = "//div[@id='app']";

    //элемент размер кредита
    @FindBy(xpath =  creditCalc + "/div/div[1]/div[1]/div[1]/div/div/div[1][./input]")
    @CacheLookup
    private WebElement creditAmountInput;

    //элемент метка размер кредита
    @FindBy(xpath =  creditCalc + "/div/div[1]/div[1]/div[1]/div/div/label")
    @CacheLookup
    private WebElement creditAmountLabel;

    //элемент срок кредита
    @FindBy(xpath = creditCalc + "/div/div[1]/div[1]/div[2]/div[1][./input]")
    @CacheLookup
    private WebElement creditTimeInput;

    //элемент ежемесячный платеж
    @FindBy(xpath = creditCalc + "/div/div[1]/div[2]/div/div[1]/div[@class='legend sum-value']/p[@class='legend__value']")
    @CacheLookup
    private WebElement creditMonthlyPayment;

    //элемент ставка по кредиту
    @FindBy(xpath = creditCalc + "/div/div[1]/div[2]/div/div[1]/div[@class='legend']/p[@class='legend__value']")
    @CacheLookup
    private WebElement creditRate;

    //элемент кнопка оформить
    @FindBy(xpath = creditCalc + "/div/div[1]/div[2]/div/div[2]/button")
    @CacheLookup
    private  WebElement submitButton;

    public String GetUrl() {
        return url;
    }

    //открываем страницу по адресу
    public void OpenCalculatorPage(){
        browser.get(url);
    }

    //получаем доступ к полю ввода срока кредита
    public WebElement GetCreditTimeInput() {
       creditTimeInput.click();
        return creditTimeInput.findElement(By.xpath("./input"));
    }

    //получаем доступ к полю ввода размер кредита
    public WebElement GetCreditAmountInput() {
        creditAmountInput.click();
        return creditAmountInput.findElement(By.xpath("./input"));
    }
    //значение элемента срок кредита
    public String GetCreditTimeValue()  {
        return creditTimeInput.getText();
    }

    //значение элемента размер кредита
    public String GetCreditAmountValue()  {
        return creditAmountInput.getText();
    }

    //значение элемента ежемесячный платеж
    public String GetMounthlyPaymentAmountValue() {
        return creditMonthlyPayment.getText();
    }

    //элемент метка ежемесячный платеж
    public WebElement GetCreditAmountLabel() {
        return creditAmountLabel;
    }

    //ввод значения в поле срок кредита
    public void SetCreditTimeInput(String timeValue) {
       //нужно очищать поле и вводить сразу же данные,т.к есть автозаполнение
       GetCreditTimeInput().sendKeys(Keys.chord(Keys.CONTROL, "a"), timeValue);
       //переключаемся на другой элемент, чтобы сработал ввод значения
       creditAmountLabel.click();
    }
    //ввод значения в поле размер кредита
    public void SetCreditAmountInput(String creditValue) {
        //нужно очищать поле и вводить сразу же данные,т.к есть автозаполнение
        GetCreditAmountInput().sendKeys(Keys.chord(Keys.CONTROL, "a"), creditValue);
        //переключаемся на другой элемент, чтобы сработал ввод значения
        GetCreditAmountLabel().click();
    }


}
