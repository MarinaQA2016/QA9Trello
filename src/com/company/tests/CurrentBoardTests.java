package com.company.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CurrentBoardTests extends TestBase{
    @BeforeMethod
    public void initTests() throws InterruptedException {
        waitUntilElementIsClickable(By.cssSelector(".text-primary"),40);
        driver.findElement(By.cssSelector(".text-primary")).click();
        //Thread.sleep(3000);
        waitUntilElementIsClickable(By.id("login"),10);
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

        //  go to the 'Boards' tab
        waitUntilElementIsClickable(By.xpath("//a[@data-test-id = 'home-team-boards-tab']"),10);
        driver.findElement(By.xpath("//a[@data-test-id = 'home-team-boards-tab']")).click();
        //Thread.sleep(1000);
        //waitUntilElementIsVisible(By.xpath("//h3[contains(text(),'Your Workspace boards')]"),10);
        waitUntilElementTextIs(By.xpath("//h3"),"Your Workspace boards",10);
        waitUntilElementIsClickable(By.xpath("//a[@class = 'board-tile'][.//div[@title='QA Haifa9']]"),10);
        // open 'QA Haifa9' board
        WebElement qaHaifa9Board = driver.findElement(By.xpath("//a[@class = 'board-tile'][.//div[@title='QA Haifa9']]"));
        qaHaifa9Board.click();
        //Thread.sleep(3000);
        waitUntilElementIsClickable(By.cssSelector(".placeholder"),10);
        waitUntilAllElementsArePresent(By.cssSelector(".js-list-content"),10);

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

        waitUntilElementIsClickable(By.cssSelector(".js-cancel-edit"),5);
        //Thread.sleep(2000);
        WebElement cancelListCreatingButton = driver.findElement(By.cssSelector(".js-cancel-edit"));
        cancelListCreatingButton.click();
        //Thread.sleep(2000);
        waitUntilElementIsInvisible(By.cssSelector(".js-cancel-edit"),5);


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

    @Test
    public void archiveFirstList() throws InterruptedException {
        List<WebElement> collumnsList = driver.findElements(By.cssSelector(".js-list-content"));
        System.out.println("size: " + collumnsList.size());
        if (collumnsList.size() == 0){
            System.out.println("size: " + collumnsList.size());
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
        // -- click on the header menu
        driver.findElement(By.cssSelector(".list-header-extras-menu")).click();
        Thread.sleep(2000);
        // -- click on "Archive menu"
        driver.findElement(By.cssSelector(".js-close-list")).click();
        Thread.sleep(2000);
    }
    @Test
    public void copyFirstList() throws InterruptedException {
        List<WebElement> collumnsList = driver.findElements(By.cssSelector(".js-list-content"));
        if (collumnsList.size() == 0){
            System.out.println("size: " + collumnsList.size());
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
        // -- click on the header menu
        driver.findElement(By.cssSelector(".list-header-extras-menu")).click();
        Thread.sleep(2000);
        // -- click on "Copy menu"
        driver.findElement(By.cssSelector(".js-copy-list")).click();
        Thread.sleep(2000);
        WebElement nameField  = driver.findElement(By.cssSelector(".js-autofocus"));
        nameField.sendKeys("nameChanged");
        driver.findElement(By.cssSelector(".js-submit")).click();
        Thread.sleep(2000);
    }



}
