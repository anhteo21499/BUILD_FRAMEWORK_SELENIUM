package com.build.anhteo.actions.common;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

public class BasePage {
    private final long longTimeOut = 30;

    public void openURL(WebDriver driver, String url) {
        driver.get(url);
    }

    public String getCurrenURL(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public String getPageSourceCode(WebDriver driver) {
        return driver.getPageSource();
    }

    public void back(WebDriver driver) {
        driver.navigate().back();
    }

    public void forward(WebDriver driver) {
        driver.navigate().forward();
    }

    public void refresh(WebDriver driver) {
        driver.navigate().refresh();
    }

    public Alert waitForAlertPresence(WebDriver driver, int timeWait) {
        WebDriverWait wait = new WebDriverWait(driver, timeWait);
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    public void acceptAlert(WebDriver driver, int timeWait) {
        waitForAlertPresence(driver, timeWait).accept();
    }

    public void cancelAlert(WebDriver driver, int timeWait) {
        waitForAlertPresence(driver, timeWait).dismiss();
    }

    public void sendKeyToAlert(WebDriver driver, int timeWait, String value) {
        waitForAlertPresence(driver, timeWait).sendKeys(value);
    }

    public void getTextAlert(WebDriver driver, int timeWait) {
        waitForAlertPresence(driver, timeWait).getText();
    }

    public void switchToWindowByTitle(WebDriver driver, String title) {
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String id : allWindowIDs) {
            driver.switchTo().window(id);
            String actualTitle = driver.getTitle();
            if (actualTitle.equals(title)) break;
        }
    }

    public void switchToWindowByID(WebDriver driver, String windowID) {
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String id : allWindowIDs) {
            if (!id.equals(windowID)) {
                driver.switchTo().window(id);
            }
        }
    }

    public void closeAllTabWithoutParent(WebDriver driver, String parentId) {
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String id : allWindowIDs) {
            if (!id.equals(parentId)) {
                driver.switchTo().window(id);
                driver.close();
            }
            driver.switchTo().window(parentId);
        }
    }

    public void clickToElement(WebDriver driver, String xpath) {
        getWebElement(driver, xpath).click();
    }

    public void sendKeyToElement(WebDriver driver, String xpath, String value) {
        WebElement webElement = getWebElement(driver, xpath);
        webElement.clear();
        webElement.sendKeys(value);
    }

    public String getTextElement(WebDriver driver, String xpath) {
        return getWebElement(driver, xpath).getText();
    }

    public void selectItemDropDownByText(WebDriver driver, String xpathDropDown, String textItem) {
        Select select = new Select(getWebElement(driver, xpathDropDown));
        select.selectByValue(textItem);
    }

    public boolean isDropDownMultiple(WebDriver driver, String xpathDropDown, String textItem) {
        Select select = new Select(getWebElement(driver, xpathDropDown));
        return select.isMultiple();
    }

    public String getTextItemDefaultDropDown(WebDriver driver, String xpathDropDown) {
        Select select = new Select(getWebElement(driver, xpathDropDown));
        return select.getFirstSelectedOption().getText();
    }

    public void selectItemDropDownByTextItem(WebDriver driver, String xpathDropDown, String xpathChildDropDown, String expectedText, int timeWait) {
        getWebElement(driver, xpathDropDown).click();
        sleepInSecond(5);
        WebDriverWait explicitWait = new WebDriverWait(driver, timeWait);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        List<WebElement> childElementDropDown = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpathChildDropDown)));
        for (WebElement item : childElementDropDown) {
            if (item.getText().trim().equals(expectedText)) {
                js.executeScript("arguments[0].scrollIntoView(true);", item);
                sleepInSecond(3);
                item.click();
            }
        }

    }

    public List<WebElement> getWebElementList(WebDriver driver, String xpathLocator) {
        return driver.findElements(By.xpath(xpathLocator));
    }


    // lấy ra attribute với text của attribute đó
    public String getElementAttribute(WebDriver driver, String xpathElement, String attributeName) {
        return getWebElement(driver, xpathElement).getAttribute(attributeName);
    }


    public String convertRgbaToHex(String rgbaColor) {
        return Color.fromString(rgbaColor).asHex();
    }

    public WebElement getWebElement(WebDriver driver, String xpathLocator) {
        return driver.findElement(By.xpath(xpathLocator));
    }

    public By getByXpath(String xpath) {
        return By.xpath(xpath);
    }

    public void checkToDefaultCheckBoxRadio(WebDriver driver, String xpath) {
        WebElement element = getWebElement(driver, xpath);
        if (!element.isSelected()) {
            element.click();
        }
    }

    public void unCheckToDefaultCheckBox(WebDriver driver, String xpath) {
        WebElement element = getWebElement(driver, xpath);
        if (element.isSelected()) {
            element.click();
        }
    }

    public boolean isElementDisplay(WebDriver driver, String xpath) {
        return getWebElement(driver, xpath).isDisplayed();
    }

    public boolean isElementEnable(WebDriver driver, String xpath) {
        return getWebElement(driver, xpath).isEnabled();
    }

    public boolean isElementSelected(WebDriver driver, String xpath) {
        return getWebElement(driver, xpath).isSelected();
    }

    public void sleepInSecond(int timeSleep) {
        try {
            Thread.sleep(timeSleep);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void switchToDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    public void hoverMouseElement(WebDriver driver, String xpath) {
        Actions actions = new Actions(driver);
        actions.moveToElement(getWebElement(driver, xpath)).perform();
    }

    // các hàm liêm quan đến FRAME
    public void switchToFrame(WebDriver driver, String xpath) {
        driver.switchTo().frame(getWebElement(driver, xpath));
    }

    // các hàm liên quan đến Javascript executor

    public void scrollToBottomPage(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }


    public void highlightElement(WebDriver driver, String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement element = getWebElement(driver, locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
        sleepInSecond(1);
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
    }

    public void clickToElementByJS(WebDriver driver, String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locator));
    }

    public void scrollToElement(WebDriver driver, String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator));
    }


    public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locator));
    }

    public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

    public String getElementValidationMessage(WebDriver driver, String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator));
    }

    public boolean isImageLoaded(WebDriver driver, String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, locator));
        return status;
    }

    // các hàm liên quan đến wait
    public void waitForElementVisible(WebDriver driver, String xpath) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(xpath)));
    }

    public void waitForElementInVisible(WebDriver driver, String xpath) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(xpath)));
    }


    public void waitForAllElementVisible(WebDriver driver, String xpath) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(xpath)));
    }

    public void waitForAllElementInVisible(WebDriver driver, String xpath) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getWebElementList(driver, xpath)));
    }

    public void waitForElementClickAble(WebDriver driver, String xpath) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(xpath)));
    }
}
