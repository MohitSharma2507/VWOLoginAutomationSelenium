package org.example;

import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Katalon_Cure_Demo {

    WebDriver driver;
    ChromeOptions options;

    @BeforeSuite
    public void setUp() throws Exception {
        options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
    }

    @Test
    @Description("Verify the Overall Positive flow that user should be able to book appointment")
    public void testValidInput() {

        driver.get("https://katalon-demo-cura.herokuapp.com/");
        driver.findElement(By.id("btn-make-appointment")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://katalon-demo-cura.herokuapp.com/profile.php#login");

        driver.findElement(By.id("txt-username")).sendKeys("John Doe");
        driver.findElement(By.id("txt-password")).sendKeys("ThisIsNotAPassword");
        driver.findElement(By.id("btn-login")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://katalon-demo-cura.herokuapp.com/#appointment");

        driver.findElement(By.id("chk_hospotal_readmission")).click();
        driver.findElement(By.id("txt_visit_date")).sendKeys("25/07/2002");
        driver.findElement(By.id("txt_comment")).sendKeys("please make a appointment as soon as possible between 12:00 to 3:00 pm");
        driver.findElement(By.id("btn-book-appointment")).click();

        Assert.assertEquals(driver.getCurrentUrl(),"https://katalon-demo-cura.herokuapp.com/appointment.php#summary");
    }
}
