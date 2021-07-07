package com.company.tests;

import com.company.pages.BoardsPageHelper;
import com.company.pages.CurrentBoardPageHelper;
import com.company.pages.HomePageHelper;
import com.company.pages.LoginPageHelper;
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
    HomePageHelper homePage;
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    CurrentBoardPageHelper qaHaifa9Board;

    @BeforeMethod
    public void initTests() throws InterruptedException {
        homePage = new HomePageHelper(driver);
        loginPage = new LoginPageHelper(driver);
        boardsPage = new BoardsPageHelper(driver);
        qaHaifa9Board = new CurrentBoardPageHelper(driver, "QA Haifa9");


        homePage.waitUntilPageIsLoaded();
        loginPage.openPage();
        loginPage.waitUntilPageIsLoaded();
        loginPage.loginAsAttl(LOGIN,PASSWORD);
        boardsPage.waitUntilPageIsLoaded();
        boardsPage.openBoardsMenu();
        qaHaifa9Board.openPage();
        qaHaifa9Board.waitUntilPageIsLoaded();
    }


    @Test
    public void newListCreatingTest()  {
        int beginListsQuantity = qaHaifa9Board.getListsQuantity();
        qaHaifa9Board.addNewList("New");
        int endListsQuantity = qaHaifa9Board.getListsQuantity();
        Assert.assertEquals(endListsQuantity,beginListsQuantity+1,
                "endListsQuantity is not beginListsQuantity+1");
    }


    @Test
    public void addNewCardTest() throws InterruptedException {
        int beginLists = qaHaifa9Board.getListsQuantity();
        if (beginLists == 0){
           qaHaifa9Board.addNewList("ForNewCard");
        }
        int beginCards = qaHaifa9Board.getCardsQuantity();
        qaHaifa9Board.addNewCardToFirstList("card title");
        int endCardsQuantity = qaHaifa9Board.getCardsQuantity();
        Assert.assertEquals(endCardsQuantity,beginCards+1,
                "endCardsQuantity is not beginCards+1");
    }

    @Test
    public void archiveFirstList() {
        int beginLists = qaHaifa9Board.getListsQuantity();
        if (beginLists==0){
            qaHaifa9Board.addNewList("ForNewCard");
            beginLists++;
        }
        qaHaifa9Board.archiveFirstList();
        int endLists = qaHaifa9Board.getListsQuantity();
        Assert.assertEquals(beginLists-1,endLists,
                "beginLists-1 is not endLists");
    }
    @Test
    public void archiveListByName(){
        String nameList = "add";
        int beginLists = qaHaifa9Board.getListsQuantity();
        int number = qaHaifa9Board.getNumberOfElementWithName(nameList);
        if (number==-1){
            qaHaifa9Board.addNewList("add");
            number = beginLists;
            beginLists++;
        }
        qaHaifa9Board.archiveList(number);
        int endLists = qaHaifa9Board.getListsQuantity();
        Assert.assertEquals(beginLists-1,endLists,
                "beginLists-1 is not endLists");
    }

    @Test
    public void copyFirstList() throws InterruptedException {
        int beginLists = qaHaifa9Board.getListsQuantity();
        if (beginLists == 0){
            qaHaifa9Board.addNewList("TestList");
            beginLists++;
        }
        qaHaifa9Board.copyFirstList("Copy");
        int endLists = qaHaifa9Board.getListsQuantity();
        Assert.assertEquals(endLists,beginLists+1, "endLists is not beginLists+1");
    }



}
