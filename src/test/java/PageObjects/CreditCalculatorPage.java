package PageObjects;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreditCalculatorPage extends BasePage
{
    public CreditCalculatorPage (WebDriver driver, WebDriverWait wait)
    {
        super(driver,wait);
        PageFactory.initElements(driver, this);
    }

    private String url = "https://www.raiffeisen.ru/retail/consumerloans/calculator/";

    private final String creditCalc = "//div[@id='app']";
    //private final String creditTime = "//div[@class='navbar-cart']//a[@href='/cart']";

    @FindBy(xpath =  creditCalc + "/div/div[1]/div[1]/div[1]/div/div/div[1][./input]")
    @CacheLookup
    private WebElement creditAmountInput;

    @FindBy(xpath =  creditCalc + "/div/div[1]/div[1]/div[1]/div/div/label")
    @CacheLookup
    private WebElement creditAmountLabel;

    @FindBy(xpath = creditCalc + "/div/div[1]/div[1]/div[2]/div[1][./input]")
    @CacheLookup
    private WebElement creditTimeInput;

    @FindBy(xpath = creditCalc + "/div/div[1]/div[2]/div/div[1]/div[@class='legend sum-value']/p[@class='legend__value']")
    @CacheLookup
    private WebElement creditMonthlyPayment;

    @FindBy(xpath = creditCalc + "/div/div[1]/div[2]/div/div[1]/div[@class='legend']/p[@class='legend__value']")
    @CacheLookup
    private WebElement creditRate;

    @FindBy(xpath = creditCalc + "/div/div[1]/div[2]/div/div[2]/button")
    @CacheLookup
    private  WebElement submitButton;

    public String GetUrl()
    {
        return url;
    }

    public WebElement GetCreditTimeInput()
    {
       creditTimeInput.click();
        return creditTimeInput.findElement(By.xpath("./input"));
        //return creditTimeInput;
    }

    public WebElement GetCreditAmountInput() {

        creditAmountInput.click();
        return creditAmountInput.findElement(By.xpath("./input"));
    }

    public String GetCreditTimeValue()
    {
        return creditTimeInput.getText();
    }

    public String GetCreditAmountValue()
    {
        return creditAmountInput.getText();
    }

    public String GetMounthlyPaymentAmountValue() {
        return creditMonthlyPayment.getText();
    }

    public WebElement getCreditAmountLabel() {
        return creditAmountLabel;
    }
}
