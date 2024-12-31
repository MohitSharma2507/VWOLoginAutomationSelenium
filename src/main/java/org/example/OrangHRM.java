package org.example;

import com.codoid.products.fillo.Select;
import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class OrangHRM {

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

        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");

        driver.findElement(By.xpath("//a[@class='oxd-main-menu-item active']")).click();
        driver.findElement(By.xpath("//button[normalize-space()='Add']")).click();


//        WebElement dropdownElement = driver.findElement(By.xpath("//select[@id='userRole']"));
//
//        // Use the Select class to select "Admin"
//        Select select = new Select(dropdownElement);
//        select.selectByVisibleText("Admin");
//
//        // Optionally, verify selection
//        String selectedOption = select.getFirstSelectedOption().getText();
//        System.out.println("Selected option: " + selectedOption);
//    }
//}
        WebElement dropdownElement = driver.findElement(By.xpath("//button[normalize-space()='Add']"));
        dropdownElement.click();

        WebElement adminOption = driver.findElement(By.xpath("//button[normalize-space()='Add']//li[text()='Admin']"));
        adminOption.click();

        WebElement selectedOption = driver.findElement(By.xpath("/button[normalize-space()='Add']//span"));
        System.out.println("Selected option: " + selectedOption.getText());

//        driver.findElement(By.xpath("//button[normalize-space()='Add']"));
//        driver.findElement(By.id("txt-password")).sendKeys("ThisIsNotAPassword");
//        driver.findElement(By.id("btn-login")).click();
//        Assert.assertEquals(driver.getCurrentUrl(), "https://katalon-demo-cura.herokuapp.com/#appointment");
//
//        driver.findElement(By.id("chk_hospotal_readmission")).click();
//        driver.findElement(By.id("txt_visit_date")).sendKeys("25/07/2002");
//        driver.findElement(By.id("txt_comment")).sendKeys("please make a appointment as soon as possible between 12:00 to 3:00 pm");
//        driver.findElement(By.id("btn-book-appointment")).click();

        Assert.assertEquals(driver.getCurrentUrl(),"https://katalon-demo-cura.herokuapp.com/appointment.php#summary");
    }
}

