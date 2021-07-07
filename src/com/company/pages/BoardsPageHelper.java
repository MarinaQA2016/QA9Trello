package com.company.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BoardsPageHelper extends PageBase{

    public BoardsPageHelper(WebDriver driver){
        this.driver= driver;
    }

    public void waitUntilPageIsLoaded(){
        waitUntilElementIsClickable(By.xpath("(//button[@data-test-id='header-boards-menu-button']/span)[2]"),30);
    }

    public String getBoardsButtonName(){
        return driver
                .findElement(By.xpath("(//button[@data-test-id='header-boards-menu-button']/span)[2]")).getText();
    }

    public void openBoardsMenu() {
        waitUntilElementIsClickable(By.xpath("//a[@data-test-id = 'home-team-boards-tab']"),10);
        driver.findElement(By.xpath("//a[@data-test-id = 'home-team-boards-tab']")).click();
        //waitUntilElementIsVisible(By.xpath("//h3[contains(text(),'Your Workspace boards')]"),10);
        waitUntilElementTextIs(By.xpath("//h3"),"Your Workspace boards",10);

    }
}
