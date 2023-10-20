package org.rahushettyacademy.pageObjects.android;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.rahushettyacademy.utils.AndroidActions;

public class FormPage extends AndroidActions {

    AndroidDriver driver;

    public FormPage(AndroidDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
    private WebElement nameField;


    @AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Female']" )
    private WebElement femaleOption;

    @AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Male']" )
    private WebElement maleOption;

    @AndroidFindBy(id = "android:id/text1")
    private WebElement countrySelection;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
    private WebElement shopButton;

    public void setNameField(String name)
    {
        nameField.sendKeys(name);
        driver.hideKeyboard();
    }

    public void setGender(String gender)
    {
        if (gender.toLowerCase().contains("female"))
            femaleOption.click();
        else
            maleOption.click();
    }

    public void setCountrySelection(String countryName)
    {
        countrySelection.click();
        scrollToText(countryName);
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='"+countryName+"']")).click();
    }

    public ProductCatalogue submitFrom()
    {
        shopButton.click();
        return new ProductCatalogue(driver);
    }
}
