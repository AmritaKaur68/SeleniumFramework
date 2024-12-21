package SeleniumProject.PageObjects;

import SeleniumProject.AbstractComponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends AbstractComponents {

    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css="[placeholder='Select Country']")
    WebElement country;

    @FindBy(css=".action__submit")
    WebElement submit;

    @FindBy(xpath="//button[contains(@class,'ta-item')][2]")
    WebElement countrySelect;
    By results = By.cssSelector(".ta-results");

    public void SelectCountry(String CountryName){
        Actions a = new Actions(driver);
            a.sendKeys(country,CountryName).build().perform();
            waitForElementToAppear(results);
            countrySelect.click();

    }


    public ConfirmationPage SubmitOrder(){
        submit.click();
        return new ConfirmationPage(driver);
    }

}
