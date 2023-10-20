package org.rahushettyacademy.TestUtils;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.rahushettyacademy.pageObjects.ios.HomePage;
import org.rahushettyacademy.utils.AppiumUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class IOSBaseTest extends AppiumUtils
{
    public IOSDriver driver;
    public AppiumDriverLocalService service;
    public HomePage homePage;

    @BeforeClass
    public void ConfigureAppium() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//org//rahushettyacademy//resources//data.properties");
        prop.load(fis);
        String ipAddress = prop.getProperty("ipAddress");
        String port = prop.getProperty("port");
        //Starting the server using AppiumServiceBuilder class
//        service = new AppiumServiceBuilder().withAppiumJS(new File("//usr//local//lib//node_modules//appium//build//lib//main.js"))
//                .withIPAddress("127.0.0.1").usingPort(4723).build();
//        service.start();
        service = startAppiumServer(ipAddress, Integer.parseInt(port));
        //Bring all the configuration using options object using Xcuitest
        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName("iPhone 11 Pro");
//        d.setCapability("xcodeOrgId","2732JASXTA");
//        d.setCapability("xcodeSigningId","iPhone Developer");
//        d.setCapability("udid","00008110-00094D8A2600401E");
//        d.setCapability("updateWDABundleId","xxxxxxx");
        options.setApp("//Users//srinivasansakthivel//Desktop//UIKitCatalog.app");
//        options.setApp("//Users//srinivasansakthivel//Downloads//Automation_Java_Project_Srini//src//test//java//resources//TestApp 3.app");
        options.setPlatformVersion("13.7");
        //Appium > Webdriver Agent > iOS Apps
        options.setWdaLaunchTimeout(Duration.ofSeconds(2000));
        driver = new IOSDriver(service.getUrl(), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        homePage = new HomePage(driver);

    }

    @AfterClass
    public void tearDown()
    {
        driver.quit();
        service.stop();
    }
}
