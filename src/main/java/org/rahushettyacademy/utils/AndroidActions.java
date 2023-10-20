package org.rahushettyacademy.utils;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.text.WordUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

public class AndroidActions extends AppiumUtils
{
    AndroidDriver driver;

    public AndroidActions(AndroidDriver driver){
        this.driver = driver;
    }

    public void longPressAction(WebElement ele){
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of("elementId", ((RemoteWebElement)ele).getId(),
                "duration",2000));

    }
    public void swipeGesture(WebElement element, String direction){
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of("elementId", ((RemoteWebElement)element).getId(),
                "direction", direction,
                "percent", 0.75));
    }

    public void dragGesture(WebElement element, int endX, int endY){
        ((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement)element).getId(),
                "endX", endX,
                "endY", endY
        ));
    }
    public void scrollToEnd(){
        boolean canScrollMore;
        do {
            canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                    "left", 300, "top", 200, "width", 300, "height", 300,
                    "direction", "down",
                    "percent", 10.0));
        }while(canScrollMore);
    }

    public void scrollUntilElement(String elementText){
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"%s\"));"));
    }

    public void scrollToText(String text){
        String capitalizedText = WordUtils.capitalizeFully(text);
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+capitalizedText+"\"));"));
    }
}
