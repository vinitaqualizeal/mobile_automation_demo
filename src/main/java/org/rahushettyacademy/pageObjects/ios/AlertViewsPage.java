package org.rahushettyacademy.pageObjects.ios;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.rahushettyacademy.utils.IOSActions;

public class AlertViewsPage extends IOSActions {
    IOSDriver driver;

    public AlertViewsPage(IOSDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

//            driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`label == 'Text Entry'`]")).click();
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`label == 'Text Entry'`]")
    private WebElement textEntryMenu;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeCell")
    private WebElement textEntryField;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`label == 'OK'`]")
    private WebElement okButton;

    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeStaticText' AND value BEGINSWITH[c] 'Confirm'")
    private WebElement confirmCancelMenu;

    @iOSXCUITFindBy(iOSNsPredicate = "label BEGINSWITH[c] 'A message'")
    private WebElement messageText;

    @iOSXCUITFindBy(iOSNsPredicate = "label == 'Confirm'")
    private WebElement confirmButton;


    public void fillTextLabel(String text)
    {
        textEntryMenu.click();
        textEntryField.sendKeys(text);
        okButton.click();
    }

    public String getMessageDisplayed()
    {
        confirmCancelMenu.click();
        return messageText.getText();
    }

    public void clickConfirmButton()
    {
        confirmButton.click();
    }





}
