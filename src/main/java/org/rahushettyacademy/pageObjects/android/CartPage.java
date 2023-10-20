package org.rahushettyacademy.pageObjects.android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.rahushettyacademy.utils.AndroidActions;

import java.util.List;

public class CartPage extends AndroidActions {
    AndroidDriver driver;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
    private List<WebElement> productList;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
    private WebElement totalAmount;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
    private WebElement termsButton;

    @AndroidFindBy(id = "android:id/button1")
    private WebElement acceptButton;

    @AndroidFindBy(className = "android.widget.CheckBox")
    private WebElement checkBox;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
    private WebElement proceedButton;

    public CartPage(AndroidDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public List<WebElement> getProductList()
    {
        return productList;
    }

    public double getProductsSum()
    {
        int countProduct = productList.size();
        double totalSum = 0;
        for(int i=0; i<countProduct; i++){
            String amountString = productList.get(i).getText();
            Double amount = getFormattedAmount(amountString);
            totalSum = totalSum + amount;
        }
        return totalSum;
    }

    public Double getTotalAmountDisplayed()
    {
        return getFormattedAmount(totalAmount.getText());
    }

    public void acceptTermsAndCondition()
    {
        longPressAction(termsButton);
        acceptButton.click();
    }

    public void submitOrder()
    {
        checkBox.click();
        proceedButton.click();
    }



}
