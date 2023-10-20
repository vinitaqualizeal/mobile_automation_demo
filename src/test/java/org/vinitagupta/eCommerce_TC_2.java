package org.vinitagupta;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class eCommerce_TC_2 extends BaseClass{
        @Test
        public void FillForm_Error_Validation() {
            //	driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Vinita Gupta");
            driver.hideKeyboard();
            driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
            driver.findElement(By.id("android:id/text1")).click();
            driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
            driver.findElement(By.xpath("//android.widget.TextView[@text='Argentina']")).click();
            driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
            String toastMessage = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
            Assert.assertEquals(toastMessage,"Please enter your name");
        }
}
