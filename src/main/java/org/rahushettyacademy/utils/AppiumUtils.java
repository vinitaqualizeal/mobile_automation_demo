package org.rahushettyacademy.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class AppiumUtils
{
//    AppiumDriver driver;
//
//    public AppiumUtils(AppiumDriver driver)
//    {
//        this.driver = driver;
//
//    }
    public AppiumDriverLocalService service;
    public Double getFormattedAmount(String amount)
    {
        return Double.parseDouble(amount.substring(1));
    }

    public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException
    {
        //System.getProperty("user.dir")+"//src//main//java//org//rahushettyacademy//testData//eCommerace.json")
        String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });
        return data;
    }
    public AppiumDriverLocalService startAppiumServer(String ipAddress, int port)
    {
        //Used this for running in appium 1
//        service = new AppiumServiceBuilder().withAppiumJS(new File("//usr//local//lib//node_modules//appium//build//lib//main.js"))
//                .withIPAddress(ipAddress).usingPort(port).build();
        //Use this when using appium 2
        service = new AppiumServiceBuilder().withAppiumJS(new File("//opt/homebrew/lib/node_modules/appium//build//lib//main.js"))
                .withIPAddress(ipAddress).usingPort(port).build();
        service.start();
        return service;
    }

    public void WaitForElementToAppear(WebElement ele, String eleText, AppiumDriver driver)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.attributeContains(ele,"text", eleText));

    }

    public String getScreenshotPath(String testCaseName, AppiumDriver driver) throws IOException {
        //1. capturing screenshot and place in folder
        File source = driver.getScreenshotAs(OutputType.FILE);
        String destinationFile = System.getProperty("user.dir")+"//reports"+testCaseName+".png";
        FileUtils.copyFile(source, new File(destinationFile));
        return destinationFile;
        //2. extent report pick file and attach to report - this will be done under Listerns class


    }

}
