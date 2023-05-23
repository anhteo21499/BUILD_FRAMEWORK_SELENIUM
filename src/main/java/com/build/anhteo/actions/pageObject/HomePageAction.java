package com.build.anhteo.actions.pageObject;

import com.build.anhteo.actions.common.BasePage;
import com.build.anhteo.interfaces.pageUI.HomePageUI;
import org.openqa.selenium.WebDriver;

public class HomePageAction extends BasePage {
    private final WebDriver driver;

    public HomePageAction(WebDriver driver) {
        this.driver = driver;
        this.longTimeOut = 30;

    }
    public void clickFanPageFacebook() {
        waitForElementClickAble(driver, HomePageUI.FANPAGE_FACEBOOK);
        clickToElement(driver, HomePageUI.FANPAGE_FACEBOOK);
    }

    public void switchFacebook() {
        switchToWindowByTitle(driver, HomePageUI.TITLE_FACEBOOK);
        System.out.println(driver.getCurrentUrl());
        clickToElement(driver, HomePageUI.CANCEL_POPUP_CONNECT_FACEBOOK);
    }

    public void closeAllTab(String idParent) {
        closeAllTabWithoutParent(driver, idParent);
    }
}
