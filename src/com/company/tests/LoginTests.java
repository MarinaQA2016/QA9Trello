package com.company.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sun.rmi.log.LogInputStream;
import sun.security.util.Password;

public class LoginTests extends TestBase{

    @BeforeMethod
    public void initTests() throws InterruptedException {
        //Thread.sleep(5000);
        // click 'Log in' button
        waitUntilElementIsClickable(By.cssSelector(".text-primary"),40);
        driver.findElement(By.cssSelector(".text-primary")).click();
        //Thread.sleep(3000);
        waitUntilElementIsClickable(By.id("login"),10);

    }



    @Test
    public void negativeLogin() throws InterruptedException {

        // fill in email field
        WebElement emailField = driver.findElement(By.id("user"));
        editField(emailField,"test");
        // fill in password field
        WebElement passwordField = driver.findElement(By.id("password"));
        editField(passwordField,"password");
        // press log in button
        driver.findElement(By.id("login")).click();
        // Output error-message
        waitUntilElementIsVisible(By.cssSelector("p.error-message"),10);


        Assert.assertEquals(driver
                .findElements(By.cssSelector("p.error-message")).get(0).getText(),
                "Your account has been updated. Please check your email to continue using Trello.",
                "The error message is not correct");
    }
    @Test
    public void positiveLogin() throws InterruptedException {
        // fill in email field
        WebElement emailField = driver.findElement(By.id("user"));
        editField(emailField, LOGIN);
        waitUntilElementIsClickable(By.xpath("//input[@value = 'Log in with Atlassian']"),5);
        WebElement loginAsAttl = driver.findElement(By.xpath("//input[@value = 'Log in with Atlassian']"));

        // press 'Log in with Atlassian' button
        loginAsAttl.click();
        waitUntilElementIsClickable(By.id("password"),5);

        // fill in password field
        WebElement passwordField = driver.findElement(By.id("password"));
        editField(passwordField, PASSWORD);

        // press log-in button
        waitUntilElementIsClickable(By.id("login-submit"),5);
        driver.findElement(By.id("login-submit")).click();
        waitUntilElementIsClickable(By.xpath("(//button[@data-test-id='header-boards-menu-button']/span)[2]"),30);

        Assert.assertEquals(driver
                .findElement(By.xpath("(//button[@data-test-id='header-boards-menu-button']/span)[2]")).getText(),"Boards",
        "Name of the button is not 'Boards'");

    }



}
