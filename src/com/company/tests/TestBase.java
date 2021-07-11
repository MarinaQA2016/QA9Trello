package com.company.tests;

import com.company.pages.HomePageHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {
    public static String PASSWORD = "marinaqa";
    public static String LOGIN = "marinaqatest2019@gmail.com";
    HomePageHelper homePage;

    WebDriver driver;
    @BeforeMethod
    public void startUp() throws InterruptedException {
        // --- to change language for the browser
        //ChromeOptions options = new ChromeOptions();
        //options.addArguments("lang=" + "rus");
        //options.addArguments("lang=" + "en");
        //driver = new ChromeDriver(options);

        driver = new ChromeDriver();
        driver.get("https://trello.com/");
        homePage = PageFactory.initElements(driver,HomePageHelper.class);
        homePage.waitUntilPageIsLoaded();

    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }



}
