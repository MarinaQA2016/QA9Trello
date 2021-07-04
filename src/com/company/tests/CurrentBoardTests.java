package com.company.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
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
        waitUntilAllElementsArePresent(By.cssSelector(".js-list-content"),5);

    }


    @Test
    public void newListCreatingTest() throws InterruptedException {
        // ---- Get quantity of the lists-------
        int beginListsQuantity = getListsQuantity();
        System.out.println("Begin: " + beginListsQuantity);

        // add new list by 'Add list button'
        WebElement createListButton = driver.findElement(By.cssSelector(".placeholder"));
        createListButton.click();
        // enter name of the list
        WebElement nameListField = driver.findElement(By.cssSelector("input[name='name']"));
        editField(nameListField, "Test List");
        // click 'Add list' button
        WebElement saveListButton = driver.findElement(By.cssSelector(".js-save-edit"));
        saveListButton.click();
        // click 'x' button to cancel new list creating
        waitUntilElementsBecome(By.cssSelector(".js-list-content"),beginListsQuantity+1,10);
        System.out.println("After adding: " + getListsQuantity());
        waitUntilElementIsClickable(By.cssSelector(".js-cancel-edit"),5);
        WebElement cancelListCreatingButton = driver.findElement(By.cssSelector(".js-cancel-edit"));
        cancelListCreatingButton.click();

        waitUntilElementIsClickable(By.cssSelector(".placeholder"),5);
        int endListsQuantity = getListsQuantity();
        Assert.assertEquals(endListsQuantity,beginListsQuantity+1, "endListsQuantity is not beginListsQuantity+1");

    }

    private int getListsQuantity() {
        List<WebElement> collumnsList = driver.findElements(By.cssSelector(".js-list-content"));
        return collumnsList.size();
    }
    private int getCardsQuantity() {
        List<WebElement> collumnsList = driver.findElements(By.cssSelector(".list-card-title"));
        return collumnsList.size();
    }

    @Test
    public void addNewCardTest() throws InterruptedException {
        int beginLists = getListsQuantity();
        if (beginLists == 0){
            // add new list by 'Add list button'
            WebElement createListButton = driver.findElement(By.cssSelector(".placeholder"));
            createListButton.click();
            // enter name of the list
            WebElement nameListField = driver.findElement(By.cssSelector("input[name='name']"));
            editField(nameListField, "Test List");
            // click 'Add list' button
            WebElement saveListButton = driver.findElement(By.cssSelector(".js-save-edit"));
            saveListButton.click();
            // click 'x' button to cancel new list creating
            waitUntilElementsBecome(By.cssSelector(".js-list-content"),beginLists+1,10);
        }
        int beginCards = getCardsQuantity();

        // press 'Add a card' ('Add another card')
        WebElement addCardButton = driver.findElement(By.cssSelector(".card-composer-container"));
        addCardButton.click();
        // fill in card title
        WebElement cardTitleField = driver.findElement(By.cssSelector(".js-card-title"));
        editField(cardTitleField, "card title");
        driver.findElement(By.cssSelector(".js-add-card")).click();

        waitUntilElementsBecome(By.cssSelector(".list-card-title"),beginCards+1,10);
        waitUntilElementIsClickable(By.cssSelector(".js-cancel"),5);
        driver.findElement(By.cssSelector(".js-cancel")).click();
        int endCardsQuantity = getCardsQuantity();
        Assert.assertEquals(endCardsQuantity,beginCards+1,"endCardsQuantity is not beginCards+1");

    }

    @Test
    public void archiveFirstList() throws InterruptedException {
        int beginLists = getListsQuantity();
        if (beginLists==0){
            // add new list by 'Add list button'
            WebElement createListButton = driver.findElement(By.cssSelector(".placeholder"));
            createListButton.click();
            // enter name of the list
            WebElement nameListField = driver.findElement(By.cssSelector("input[name='name']"));
            editField(nameListField, "Test List");
            // click 'Add list' button
            WebElement saveListButton = driver.findElement(By.cssSelector(".js-save-edit"));
            saveListButton.click();
            // click 'x' button to cancel new list creating
            waitUntilElementsBecome(By.cssSelector(".js-list-content"),beginLists+1,10);
            beginLists++;
        }

        // -- click on the header menu
        waitUntilElementIsClickable(By.cssSelector(".list-header-extras-menu"),5);
        driver.findElement(By.cssSelector(".list-header-extras-menu")).click();

        // -- click on "Archive menu"
        waitUntilElementIsClickable(By.cssSelector(".js-close-list"),5);
        driver.findElement(By.cssSelector(".js-close-list")).click();
        waitUntilElementsBecome(By.cssSelector(".js-list-content"),beginLists-1,5);


        int endLists = getListsQuantity();
        Assert.assertEquals(beginLists-1,endLists, "beginLists-1 is not endLists");
    }
    @Test
    public void copyFirstList() throws InterruptedException {
        //List<WebElement> collumnsList = driver.findElements(By.cssSelector(".js-list-content"));
        int beginLists = getListsQuantity();
        if (beginLists == 0){
            // add new list by 'Add list button'
            WebElement createListButton = driver.findElement(By.cssSelector(".placeholder"));
            createListButton.click();
            // enter name of the list
            WebElement nameListField = driver.findElement(By.cssSelector("input[name='name']"));
            editField(nameListField, "Test List");
            // click 'Add list' button
            WebElement saveListButton = driver.findElement(By.cssSelector(".js-save-edit"));
            saveListButton.click();
            // click 'x' button to cancel new list creating
            waitUntilElementsBecome(By.cssSelector(".js-list-content"),beginLists+1,10);
            waitUntilElementIsClickable(By.cssSelector(".js-cancel-edit"),10);
            WebElement cancelListCreatingButton = driver.findElement(By.cssSelector(".js-cancel-edit"));
            cancelListCreatingButton.click();
            waitUntilElementIsClickable(By.cssSelector(".placeholder"),15);
            beginLists++;
            driver.navigate().refresh();
            waitUntilAllElementsArePresent(By.cssSelector(".js-list-content"),10);
        }



        // -- click on the header menu
        waitUntilElementIsClickable(By.cssSelector(".list-header-extras-menu"),5);
        driver.findElement(By.cssSelector(".list-header-extras-menu")).click();

        // -- click on "Copy menu"
        waitUntilElementIsClickable(By.cssSelector(".js-copy-list"),10);
        driver.findElement(By.cssSelector(".js-copy-list")).click();

        waitUntilElementIsClickable(By.cssSelector(".js-submit"),10);
        //WebElement nameField  = driver.findElement(By.cssSelector(".js-autofocus"));
        //nameField.sendKeys("nameChanged");
        driver.findElement(By.cssSelector(".js-submit")).click();
        waitUntilElementsBecome(By.cssSelector(".js-list-content"),beginLists+1,5);

        int endLists = getListsQuantity();
        Assert.assertEquals(endLists,beginLists+1, "endLists is not beginLists+1");

    }



}
