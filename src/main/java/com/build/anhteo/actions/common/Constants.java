package com.build.anhteo.actions.common;

import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Constants {
    public String projectPath = System.getProperty("user.dir");
    @Test
    @SneakyThrows
    public void test() {
        System.setProperty("webdriver.chrome.driver", projectPath + "/src/main/resources/browerDriver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://bizbooks.vn/");
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        WebDriverWait explicitWait = new WebDriverWait(driver, 2);

        driver.findElement(By.xpath("//a[contains(text(),'Hướng dẫn mua hàng')]")).click();
//        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[80]//a[1]//span[1]"))).click();
        Thread.sleep(5000);

    }
}
