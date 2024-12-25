package SeleniumProject.Tests;

import SeleniumProject.PageObjects.*;
import SeleniumProject.TestComponents.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;


public class SubmitOrderTest extends BaseTest {


    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void submitOrder(HashMap<String, String> input) throws IOException {
        ProductCatalouge productCatalouge = landingPage.loginApplication(input.get("email"), input.get("password"));
        //"Student@gmail.com", "Abc@2024");

        List<WebElement> products = productCatalouge.getProductList();
        productCatalouge.addProductToCart(input.get("product"));
        CartPage cartpage = productCatalouge.goToCartPage();

        boolean match = cartpage.verifyProductDisplay(input.get("product"));
        Assert.assertTrue(match);
        CheckoutPage checkoutpage = cartpage.goToCheckout();
        checkoutpage.SelectCountry("india");
        ConfirmationPage confirmationPage = checkoutpage.SubmitOrder();
        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }

    @Test(dependsOnMethods = {"submitOrder"})
    public void orderHistoryTest() {
        String productName = "ZARA COAT 3";
        ProductCatalouge productCatalogue = landingPage.loginApplication("anshika@gmail.com", "Iamking@000");
        OrderPage ordersPage = productCatalogue.goToOrdersPage();
        Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> data = getJsonDataToMap(
                System.getProperty("user.dir") + "//src//test//java//SeleniumProject//Data//PurchaseOrder.json");
        return new Object[][]{{data.get(0)}, {data.get(1)}};

    }

}
