package com.company.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CurrentBoardTests extends TestBase{
    @BeforeMethod
    public void initTests() throws InterruptedException {
        // click 'Log in' button
        driver.findElement(By.cssSelector(".text-primary")).click();
        Thread.sleep(3000);
        // fill in email field
        WebElement emailField = driver.findElement(By.id("user"));
        editField(emailField, "marinaqatest2019@gmail.com");
        Thread.sleep(3000);
        // press 'Log in with Atlassian' button
        driver.findElement(By.id("login")).click();
        Thread.sleep(3000);
        // fill in password field
        WebElement passwordField = driver.findElement(By.id("password"));
        editField(passwordField, "marinaqa");
        // press log-in button
        driver.findElement(By.id("login-submit")).click();
        Thread.sleep(20000);
        //  go to the 'Boards' tab
        driver.findElement(By.xpath("//a[@data-test-id = 'home-team-boards-tab']")).click();
        Thread.sleep(3000);
        // open 'QA Haifa9' board
        driver.findElement(By.xpath("//a[@class = 'board-tile'][.//div[@title='QA Haifa9']]")).click();
        Thread.sleep(3000);

    }


    @Test
    public void newListCreatingTest() throws InterruptedException {

        // press 'Add list button'
        WebElement createListButton = driver.findElement(By.cssSelector(".placeholder"));
        createListButton.click();
        // enter name of the list
        WebElement nameListField = driver.findElement(By.cssSelector("input[name='name']"));
        editField(nameListField, "Test List");
        // click 'Add list' button
        WebElement saveListButton = driver.findElement(By.cssSelector(".js-save-edit"));
        saveListButton.click();
        // click 'x' button to cancel new list creating
        Thread.sleep(2000);
        WebElement cancelListCreatingButton = driver.findElement(By.cssSelector(".js-cancel-edit"));
        cancelListCreatingButton.click();
        Thread.sleep(2000);

    }
    @Test
    public void addNewCardTest() throws InterruptedException {
        // press 'Add a card' ('Add another card')
        WebElement addCardButton = driver.findElement(By.cssSelector(".card-composer-container"));
        addCardButton.click();
        // fill in card title
        WebElement cardTitleField = driver.findElement(By.cssSelector(".js-card-title"));
        editField(cardTitleField, "card title");
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(".js-add-card")).click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector(".js-cancel")).click();
        Thread.sleep(3000);
    }

    private void editField(WebElement field, String value) {
        field.click();
        field.sendKeys(value);
    }
}
