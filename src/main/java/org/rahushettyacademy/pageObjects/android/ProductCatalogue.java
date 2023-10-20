package org.rahushettyacademy.pageObjects.android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.rahushettyacademy.utils.AndroidActions;

import java.util.List;

public class ProductCatalogue extends AndroidActions {

    AndroidDriver driver;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='ADD TO CART']")
    public List<WebElement> addToCart;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
    private WebElement cartButton;

    public ProductCatalogue(AndroidDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void addItemToCardByIndex(int index)
    {
        addToCart.get(index).click();
    }

    public CartPage goToCardPage() throws InterruptedException {
        cartButton.click();
        Thread.sleep(1000);
        return new CartPage(driver);
    }
}
