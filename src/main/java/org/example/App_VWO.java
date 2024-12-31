package org.example;

import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.function.Function;

public class App_VWO {

    WebDriver driver;
    ChromeOptions options;

    @BeforeSuite
    public void setUp() {

        options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
    }

    @Test(priority = 1,groups = {"negative","sanity"})
    @Description("TC#1 - Verify the Login page by Invalid Email and Valid Password")
    public void testInvalidLogin() {

        driver.get("https://app.vwo.com");
        driver.findElement(By.id("login-username")).sendKeys("admin@gmail.com");
        driver.findElement(By.id("login-password")).sendKeys("password123");
        driver.findElement(By.xpath("//button[@id='js-login-btn']")).click();

        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchFieldException.class);

        String error = wait.until(driver ->
        {
            WebElement notificationBox = driver.findElement(By.id("js-notification-box-msg"));
            return notificationBox.isDisplayed() ? notificationBox.getText() : null;
        });

        Assert.assertEquals(error, "Your email, password, IP address or location did not match");
    }

    @Test(priority = 2,groups = {"stage" , "positive", "sanity"})
    @Description("TC#2 - Verify the Login page by Valid Email and Valid Password")
    public void testValidLogin(){
        driver.get("https://app.vwo.com");
        driver.findElement(By.id("login-username")).sendKeys("steptointelligence@gmail.com");
        driver.findElement(By.id("login-password")).sendKeys("mohitVWO123");
        driver.findElement(By.xpath("//button[@id='js-login-btn']")).click();

        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(10))
                        .pollingEvery(Duration.ofSeconds(2))
                                .ignoring(NoSuchFieldException.class);

        Boolean isUrlCorrect = wait.until(driver ->
                driver.getCurrentUrl().equals("https://app.vwo.com/#/dashboard")
        );

        // Assert if URL is correct
        Assert.assertTrue(isUrlCorrect, "Login successful. User is navigated to the expected URL.");
        }
    public void tearDown() throws Exception {
        driver.quit();
    }
    }

