package com.company.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{


    @Test
    public void negativeLogin() throws InterruptedException {
        // click 'Log in' button
        driver.findElement(By.cssSelector(".text-primary")).click();
        Thread.sleep(3000);
        // fill in email field
        WebElement emailField = driver.findElement(By.id("user"));
        emailField.click();
        emailField.sendKeys("test");
        // fill in password field
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.click();
        passwordField.sendKeys("password");
        // press log in button
        driver.findElement(By.id("login")).click();
        Thread.sleep(5000);
        // Output error-message
        System.out.println("Error-message: " + driver
                .findElements(By.cssSelector("p.error-message")).get(0).getText());
    }
    @Test
    public void positiveLogin() throws InterruptedException {
        // click 'Log in' button
        driver.findElement(By.cssSelector(".text-primary")).click();
        Thread.sleep(3000);
        // fill in email field
        WebElement emailField = driver.findElement(By.id("user"));
        emailField.click();
        emailField.sendKeys("marinaqatest2019@gmail.com");
        Thread.sleep(3000);
        // press 'Log in with Atlassian' button
        driver.findElement(By.id("login")).click();
        Thread.sleep(3000);
        // fill in password field
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.click();
        passwordField.sendKeys("marinaqa");
        // press log-in button
        driver.findElement(By.id("login-submit")).click();
        Thread.sleep(20000);
        System.out.println("Boards button name: " + driver
                .findElement(By.xpath("(//button[@data-test-id='header-boards-menu-button']/span)[2]")).getText());
    }



}
