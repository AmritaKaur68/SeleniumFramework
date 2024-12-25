package SeleniumProject.Tests;

import SeleniumProject.PageObjects.*;
import SeleniumProject.TestComponents.BaseTest;
import SeleniumProject.TestComponents.Retry;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ErrorValidations extends BaseTest {

    @Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
    public void LoginErrorValidation() throws IOException, InterruptedException {
        landingPage.loginApplication("anshika@gmail.com", "Iamki000");
        Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

    }

    @Test
    public void productErrorValidation() throws IOException, InterruptedException {
        String productName = "IPHONE 13 PRO";
        ProductCatalouge productCatalouge = landingPage.loginApplication("anshika@gmail.com", "Iamking@000");

        List<WebElement> products = productCatalouge.getProductList();
        productCatalouge.addProductToCart(productName);
        CartPage cartpage = productCatalouge.goToCartPage();

        boolean match = cartpage.verifyProductDisplay(productName);
        Assert.assertTrue(match);

    }
}
