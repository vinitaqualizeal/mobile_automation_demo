package org.rahushettyacademy.TestUtils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.rahushettyacademy.pageObjects.android.FormPage;
import org.rahushettyacademy.utils.AppiumUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class AndroidBaseTest extends AppiumUtils
{
    public AndroidDriver driver;
    public AppiumDriverLocalService  service;
    public FormPage formPage;

    @BeforeClass(alwaysRun = true)
    public void ConfigureAppium() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//org//rahushettyacademy//resources//data.properties");
        prop.load(fis);
        String ipAddress = System.getProperty("ipAddress")!=null? System.getProperty("ipAddress") : prop.getProperty("ipAdress");
        String port = prop.getProperty("port");
        //Starting the server using AppiumServiceBuilder class
//        service = new AppiumServiceBuilder().withAppiumJS(new File("//usr//local//lib//node_modules//appium//build//lib//main.js"))
//                .withIPAddress("127.0.0.1").usingPort(4723).build();
//        service.start();
//        DesiredCapabilities caps = new DesiredCapabilities();
//
//        caps.setCapability("appium:automationName", "UiAutomator2");
//
//        caps.setCapability("platformName", "Android");
//
//        caps.setCapability("appium:app", "//Users//srinivasansakthivel//Documents//Appium_Mobile_Testing_Framewor_Java//src//main//java//resources//General-Store.apk");
//
//        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub/");

//        AndroidDriver driver = new AndroidDriver(remoteUrl, caps);
        service = startAppiumServer(ipAddress, Integer.parseInt(port));
        //Bring all the configuration using options object using UiAutomator2options
        UiAutomator2Options options = new UiAutomator2Options();
//        options.setDeviceName("motorola edge 20 fusion");
        options.setDeviceName(prop.getProperty("Pixel"));
        options.setApp("C:\\Users\\Vinita\\Desktop\\ApiDemos-debug.apk");
        options.setApp(System.getProperty("user.dir")+"//src//main//java//resources//General-Store.apk");
        //Android driver creating object
        driver = new AndroidDriver(service.getUrl(), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        formPage = new FormPage(driver);

    }
//    public void longPressAction(WebElement ele){
//        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of("elementId", ((RemoteWebElement)ele).getId(),
//                "duration",2000));
//
//    }
//    public void swipeGesture(WebElement element, String direction){
//        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of("elementId", ((RemoteWebElement)element).getId(),
//                "direction", direction,
//                "percent", 0.75));
//    }
//
//    public void dragGesture(WebElement element, int endX, int endY){
//    ((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
//            "elementId", ((RemoteWebElement)element).getId(),
//            "endX", endX,
//            "endY", endY
//    ));
//}
//    public void scrollToEnd(){
//        boolean canScrollMore;
//        do {
//            canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
//                    "left", 300, "top", 200, "width", 300, "height", 300,
//                    "direction", "down",
//                    "percent", 10.0));
//        }while(canScrollMore);
//    }
//
//    public void scrollUntilElement(String elementText){
//        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+elementText+"\"));"));
//    }
//
//    public Double getFormattedAmount(String amount){
//        Double price = Double.parseDouble(amount.substring(1));
//        return price;
//    }

    @AfterClass(alwaysRun = true)
    public void tearDown()
    {
        driver.quit();
        service.stop();
    }
}
