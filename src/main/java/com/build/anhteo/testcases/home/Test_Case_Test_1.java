package com.build.anhteo.testcases.home;

import com.build.anhteo.actions.pageObject.HomePageAction;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class Test_Case_Test_1 {
    WebDriver driver;
    HomePageAction homePageAction;
    @BeforeClass
    public void beforeTest() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/browerDriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        homePageAction = new HomePageAction(driver);
    }

    @Test
    public void Test_1() {
        String parentId = driver.getWindowHandle();
        driver.get("https://demo.nopcommerce.com");
        homePageAction.clickFanPageFacebook();
        homePageAction.switchFacebook();
        homePageAction.closeAllTab(parentId);
    }

    @AfterClass
    public void afterClass() {
        homePageAction.sleepInSecond(5);
        driver.close();
    }
}
