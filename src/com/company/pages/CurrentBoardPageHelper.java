package com.company.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CurrentBoardPageHelper extends PageBase {
    String boardName;
    public CurrentBoardPageHelper(WebDriver driver, String boardName){
        this.driver = driver;
        this.boardName = boardName;
    }

    public void openPage() {
        waitUntilElementIsClickable(getLocatorBoardButton(),10);
        // open 'QA Haifa9' board
        WebElement qaHaifa9Board = driver.findElement(getLocatorBoardButton());
        qaHaifa9Board.click();
    }

    public By getLocatorBoardButton(){
        return By.xpath("//a[@class = 'board-tile'][.//div[@title='"+boardName+"']]");
    }

    public void waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(By.cssSelector(".placeholder"),10);
        WebElement addButton = driver.findElement(By.cssSelector(".placeholder"));

        if (addButton.getText().equals("Add another list")) {
            waitUntilAllElementsArePresent(By.cssSelector(".js-list-content"),5);
        }
    }
    public int getListsQuantity() {
        List<WebElement> collumnsList = driver.findElements(By.cssSelector(".js-list-content"));
        return collumnsList.size();
    }
    public int getCardsQuantity() {
        List<WebElement> collumnsList = driver.findElements(By.cssSelector(".list-card-title"));
        return collumnsList.size();
    }

    public void addNewList(String name) {
        int beginListsQuantity = this.getListsQuantity();
        // add new list by 'Add list button'
        WebElement createListButton = driver.findElement(By.cssSelector(".placeholder"));
        createListButton.click();
        // enter name of the list
        WebElement nameListField = driver.findElement(By.cssSelector("input[name='name']"));
        editField(nameListField, name);
        // click 'Add list' button
        WebElement saveListButton = driver.findElement(By.cssSelector(".js-save-edit"));
        saveListButton.click();
        // click 'x' button to cancel new list creating
        waitUntilElementsBecome(By.cssSelector(".js-list-content"),beginListsQuantity+1,10);
        System.out.println("After adding: " + this.getListsQuantity());
        waitUntilElementIsClickable(By.cssSelector(".js-cancel-edit"),5);
        WebElement cancelListCreatingButton = driver.findElement(By.cssSelector(".js-cancel-edit"));
        cancelListCreatingButton.click();
        waitUntilElementIsClickable(By.cssSelector(".placeholder"),5);
    }

    public void addNewCardToFirstList(String name) {
        int beginCards = this.getCardsQuantity();
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
    }

    public void archiveFirstList() {
        int beginLists = this.getListsQuantity();
        // -- click on the header menu
        waitUntilElementIsClickable(By.cssSelector(".list-header-extras-menu"),5);
        driver.findElement(By.cssSelector(".list-header-extras-menu")).click();

        // -- click on "Archive menu"
        waitUntilElementIsClickable(By.cssSelector(".js-close-list"),5);
        driver.findElement(By.cssSelector(".js-close-list")).click();
        waitUntilElementsBecome(By.cssSelector(".js-list-content"),beginLists-1,5);
    }

    public void copyFirstList(String name) {
        int beginLists = this.getListsQuantity();
        // -- click on the header menu
        waitUntilElementIsClickable(By.cssSelector(".list-header-extras-menu"),5);
        driver.findElement(By.cssSelector(".list-header-extras-menu")).click();

        // -- click on "Copy menu"
        waitUntilElementIsClickable(By.cssSelector(".js-copy-list"),10);
        driver.findElement(By.cssSelector(".js-copy-list")).click();

        waitUntilElementIsClickable(By.cssSelector(".js-autofocus"),5);
        driver.findElement(By.cssSelector(".js-autofocus")).sendKeys(name);

        waitUntilElementIsClickable(By.cssSelector(".js-submit"),10);
        //WebElement nameField  = driver.findElement(By.cssSelector(".js-autofocus"));
        //nameField.sendKeys("nameChanged");
        driver.findElement(By.cssSelector(".js-submit")).click();
        waitUntilElementsBecome(By.cssSelector(".js-list-content"),beginLists+1,5);
    }

    public int getNumberOfElementWithName(String nameList) {
        int number = -1;
        int counter = 0;
        for(WebElement element: driver.findElements(By.cssSelector(".list-header-name"))){
            if(element.getText().equals(nameList)){
                number = counter;
            }
            counter++;
        }
        return number;
    }

    public void archiveList(int number) {
        int beginLists = this.getListsQuantity();
        // -- click on the header menu
        waitUntilElementIsClickable(By.cssSelector(".list-header-extras-menu"),5);
        driver.findElements(By.cssSelector(".list-header-extras-menu")).get(number).click();

        // -- click on "Archive menu"
        waitUntilElementIsClickable(By.cssSelector(".js-close-list"),5);
        driver.findElement(By.cssSelector(".js-close-list")).click();
        waitUntilElementsBecome(By.cssSelector(".js-list-content"),beginLists-1,5);
    }
}
