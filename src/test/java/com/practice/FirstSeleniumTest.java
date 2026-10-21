package com.practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FirstSeleniumTest {

    private static final String URL = "https://www.selenium.dev/selenium/web/web-form.html";

    private WebDriver driver;

    // Runs BEFORE every @Test: open a fresh browser
    @BeforeMethod
    public void openBrowser() {
        ChromeOptions options = new ChromeOptions();
        // Run "mvn test -Dheadless=true" to hide the browser window
        if (Boolean.getBoolean("headless")) {
            options.addArguments("--headless=new");
        }
        driver = new ChromeDriver(options);
    }

    @Test
    public void pageTitleIsCorrect() {
        driver.get(URL);
        Assert.assertEquals(driver.getTitle(), "Web form");
    }

    @Test
    public void submitFormShowsMessage() {
        driver.get(URL);

        WebElement textBox = driver.findElement(By.name("my-text"));
        textBox.sendKeys("Akash");

        driver.findElement(By.cssSelector("button[type='submit']")).click();

        WebElement message = driver.findElement(By.id("message"));
        Assert.assertEquals(message.getText(), "Received!");
    }

    // Runs AFTER every @Test, even if it failed: close the browser
    @AfterMethod
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
