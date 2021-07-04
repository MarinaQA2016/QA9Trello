package com.company.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPageHelper extends PageBase{

    public LoginPageHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage() {
        waitUntilElementIsClickable(By.cssSelector(".text-primary"),40);
        driver.findElement(By.cssSelector(".text-primary")).click();
    }

    public void waitUntilPageIsLoaded(){
        waitUntilElementIsClickable(By.id("login"),10);
    }

    public void loginNotAttl(String login, String password){
        fillInEmailField(login);
        fillInPasswordField(password);
        submitLoginNotAttl();
    }

    public void loginAsAttl(String login, String password){
        fillInEmailField(login);
        pressLoginAsAttlButton();
        fillInPasswordAttl(password);
        submitLoginAttl();
    }
    public String getErrorMessage(){
        waitUntilElementIsVisible(By.cssSelector("p.error-message"),10);
        return driver.findElement(By.cssSelector("p.error-message")).getText();
    }

    public void fillInEmailField(String value) {
        // fill in email field
        WebElement emailField = driver.findElement(By.id("user"));
        editField(emailField,value);
    }

    public void fillInPasswordField(String value) {
        // fill in password field
        WebElement passwordField = driver.findElement(By.id("password"));
        editField(passwordField,value);
    }

    public void submitLoginNotAttl() {
        driver.findElement(By.id("login")).click();
    }

    public void pressLoginAsAttlButton() {
        waitUntilElementIsClickable(By.xpath("//input[@value = 'Log in with Atlassian']"),5);
        WebElement loginAsAttl = driver.findElement(By.xpath("//input[@value = 'Log in with Atlassian']"));
        // press 'Log in with Atlassian' button
        loginAsAttl.click();
    }

    public void fillInPasswordAttl(String value) {
        // fill in password field
        waitUntilElementIsClickable(By.id("password"),5);
        WebElement passwordField = driver.findElement(By.id("password"));
        editField(passwordField, value);
    }

    public void submitLoginAttl() {
        waitUntilElementIsClickable(By.id("login-submit"),5);
        driver.findElement(By.id("login-submit")).click();
    }
}
