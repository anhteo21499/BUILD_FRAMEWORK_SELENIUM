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
    public long longTimeOut = 10;

    /**
     * mở một url
     *
     * @param driver
     * @param url
     */
    public void openURL(WebDriver driver, String url) {
        driver.get(url);
    }

    /**
     * lấy đường dẫn hiện tại của trang
     *
     * @param driver
     * @return
     */
    public String getCurrenURL(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    /**
     * lấy title hiện tại của trang
     *
     * @param driver
     * @return
     */
    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    /**
     * lấy mã nguồn hiện tại của trang
     *
     * @param driver
     * @return
     */
    public String getPageSourceCode(WebDriver driver) {
        return driver.getPageSource();
    }

    /**
     * back lại trang trước
     *
     * @param driver
     */
    public void back(WebDriver driver) {
        driver.navigate().back();
    }

    /**
     * tiến lại trang vừa rồi
     *
     * @param driver
     */
    public void forward(WebDriver driver) {
        driver.navigate().forward();
    }

    /**
     * load lại trang hiện tại
     *
     * @param driver
     */
    public void refresh(WebDriver driver) {
        driver.navigate().refresh();
    }

    /**
     * wait đén khi tồn tại một alert trong POM
     *
     * @param driver
     * @param timeWait
     * @return
     */
    public Alert waitForAlertPresence(WebDriver driver, int timeWait) {
        WebDriverWait wait = new WebDriverWait(driver, timeWait);
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    /**
     * accept một màn hình alert
     *
     * @param driver
     * @param timeWait
     */
    public void acceptAlert(WebDriver driver, int timeWait) {
        waitForAlertPresence(driver, timeWait).accept();
    }

    /**
     * chọn huỷ một alert
     *
     * @param driver
     * @param timeWait
     */
    public void cancelAlert(WebDriver driver, int timeWait) {
        waitForAlertPresence(driver, timeWait).dismiss();
    }

    /**
     * gửi dữ liệu đến một alert
     *
     * @param driver
     * @param timeWait
     * @param value
     */
    public void sendKeyToAlert(WebDriver driver, int timeWait, String value) {
        waitForAlertPresence(driver, timeWait).sendKeys(value);
    }

    /**
     * lấy text của alert
     *
     * @param driver
     * @param timeWait
     */
    public void getTextAlert(WebDriver driver, int timeWait) {
        waitForAlertPresence(driver, timeWait).getText();
    }

    /**
     * chuyển qua một màn hình khác bởi title của màn hình đó
     *
     * @param driver
     * @param title
     */
    public void switchToWindowByTitle(WebDriver driver, String title) {
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String id : allWindowIDs) {
            driver.switchTo().window(id);
            String actualTitle = driver.getTitle();
            if (actualTitle.equals(title)) break;
        }
    }

    /**
     * chuyển sang một màn hình khác bởi id của màn hình đó
     *
     * @param driver
     * @param windowID
     */
    public void switchToWindowByID(WebDriver driver, String windowID) {
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String id : allWindowIDs) {
            if (!id.equals(windowID)) {
                driver.switchTo().window(id);
            }
        }
    }

    /**
     * đóng tất cả các tab trừ tab home
     *
     * @param driver
     * @param parentId
     */
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

    /**
     * click vào một element
     *
     * @param driver
     * @param xpath
     */
    public void clickToElement(WebDriver driver, String xpath) {
        getWebElement(driver, xpath).click();
    }

    /**
     * gửi dữ liệu vào một element
     *
     * @param driver
     * @param xpath
     * @param value
     */
    public void sendKeyToElement(WebDriver driver, String xpath, String value) {
        WebElement webElement = getWebElement(driver, xpath);
        webElement.clear();
        webElement.sendKeys(value);
    }

    /**
     * lấy text của một element
     *
     * @param driver
     * @param xpath
     * @return
     */
    public String getTextElement(WebDriver driver, String xpath) {
        return getWebElement(driver, xpath).getText();
    }

    /**
     * chọn một option trong dropdown bởi text của option
     *
     * @param driver
     * @param xpathDropDown
     * @param textItem
     */
    public void selectItemDropDownByText(WebDriver driver, String xpathDropDown, String textItem) {
        Select select = new Select(getWebElement(driver, xpathDropDown));
        select.selectByValue(textItem);
    }

    /**
     * kiểm tra xem dropdown có được chọn nhiều option hay không
     *
     * @param driver
     * @param xpathDropDown
     * @param textItem
     * @return
     */
    public boolean isDropDownMultiple(WebDriver driver, String xpathDropDown, String textItem) {
        Select select = new Select(getWebElement(driver, xpathDropDown));
        return select.isMultiple();
    }

    /**
     * lấy text của phần tử đầu tiên trong dropdown
     *
     * @param driver
     * @param xpathDropDown
     * @return
     */
    public String getTextItemDefaultDropDown(WebDriver driver, String xpathDropDown) {
        Select select = new Select(getWebElement(driver, xpathDropDown));
        return select.getFirstSelectedOption().getText();
    }

    /**
     * chọn một option trong dropdown bằng text của option
     *
     * @param driver
     * @param xpathDropDown      xpath của dropdown
     * @param xpathChildDropDown xpath phần tử con của dropdown
     * @param expectedText       text của phần tử con muốn select
     * @param timeWait           thời gian chờ của element
     */
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

    /**
     * get danh sách element
     *
     * @param driver
     * @param xpathLocator
     * @return
     */
    public List<WebElement> getWebElementList(WebDriver driver, String xpathLocator) {
        return driver.findElements(By.xpath(xpathLocator));
    }


    /**
     * lấy ra một element bằng attribute
     *
     * @param driver
     * @param xpathElement
     * @param attributeName
     * @return
     */
    // lấy ra attribute với text của attribute đó
    public String getElementAttribute(WebDriver driver, String xpathElement, String attributeName) {
        return getWebElement(driver, xpathElement).getAttribute(attributeName);
    }

    /**
     * hàm chuyển đổi mã màu VD: dạng rgba(34,454,124) sang hex dạng #33333
     *
     * @param rgbaColor
     * @return
     */
    public String convertRgbaToHex(String rgbaColor) {
        return Color.fromString(rgbaColor).asHex();
    }

    /**
     * hàm dùng chung để get element
     *
     * @param driver
     * @param xpathLocator
     * @return
     */
    public WebElement getWebElement(WebDriver driver, String xpathLocator) {
        return driver.findElement(By.xpath(xpathLocator));
    }

    public By getByXpath(String xpath) {
        return By.xpath(xpath);
    }

    /**
     * click check default radio button
     *
     * @param driver
     * @param xpath
     */
    public void checkToDefaultCheckBoxRadio(WebDriver driver, String xpath) {
        WebElement element = getWebElement(driver, xpath);
        if (!element.isSelected()) {
            element.click();
        }
    }

    /**
     * bỏ check default radio button
     *
     * @param driver
     * @param xpath
     */
    public void unCheckToDefaultCheckBox(WebDriver driver, String xpath) {
        WebElement element = getWebElement(driver, xpath);
        if (element.isSelected()) {
            element.click();
        }
    }

    /**
     * kiểm tra xem element có đang hiển thị không
     *
     * @param driver
     * @param xpath
     * @return
     */
    public boolean isElementDisplay(WebDriver driver, String xpath) {
        return getWebElement(driver, xpath).isDisplayed();
    }

    /**
     * kiểm tra xem element có cho phép enable không
     *
     * @param driver
     * @param xpath
     * @return
     */
    public boolean isElementEnable(WebDriver driver, String xpath) {
        return getWebElement(driver, xpath).isEnabled();
    }

    /**
     * kiểm tra xem element có đang được chọn không
     *
     * @param driver
     * @param xpath
     * @return
     */
    public boolean isElementSelected(WebDriver driver, String xpath) {
        return getWebElement(driver, xpath).isSelected();
    }

    /**
     * dừng luồng trong khoảng thời gian
     *
     * @param timeSleep
     */
    public void sleepInSecond(int timeSleep) {
        try {
            Thread.sleep(timeSleep);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * chuyển đổi lại frame mặc định của trình duyệt sau khi đã switch vào một frame khác
     *
     * @param driver
     */
    public void switchToDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    /**
     * di chuyển chuột vào một element
     *
     * @param driver
     * @param xpath
     */
    public void hoverMouseElement(WebDriver driver, String xpath) {
        Actions actions = new Actions(driver);
        actions.moveToElement(getWebElement(driver, xpath)).perform();
    }


    // ============================  các hàm liêm quan đến FRAME

    /**
     * chuyển qua một frame khác
     *
     * @param driver
     * @param xpath
     */
    public void switchToFrame(WebDriver driver, String xpath) {
        driver.switchTo().frame(getWebElement(driver, xpath));
    }

    // các hàm liên quan đến Javascript executor

    /**
     * cuộn trang đến cuối trình duyệt
     *
     * @param driver
     */
    public void scrollToBottomPage(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }


    /**
     * làm nổi bật một element
     *
     * @param driver
     * @param locator
     */
    public void highlightElement(WebDriver driver, String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement element = getWebElement(driver, locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
        sleepInSecond(1);
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
    }

    /**
     * click vào một element
     *
     * @param driver
     * @param locator
     */
    public void clickToElementByJS(WebDriver driver, String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locator));
    }

    /**
     * cuộn trang đến một element
     *
     * @param driver
     * @param locator
     */
    public void scrollToElement(WebDriver driver, String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator));
    }

    /**
     * remove một attribute của element trong DOM
     *
     * @param driver
     * @param locator
     * @param attributeRemove
     */
    public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locator));
    }

    /**
     * kiểm tra đã load page thành công hay chưa
     *
     * @param driver
     * @return
     */
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


    /**
     * lấy thông báo lỗi của trình duyệt khi gửi dữ liệu không hợp lệ cho một trường nhập liệu
     *
     * @param driver
     * @param locator
     * @return
     */
    public String getElementValidationMessage(WebDriver driver, String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator));
    }

    /**
     * kiểm tra ảnh đã load lên thành công hay chưa
     *
     * @param driver
     * @param locator
     * @return
     */
    public boolean isImageLoaded(WebDriver driver, String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, locator));
        return status;
    }

    // các hàm liên quan đến wait

    /**
     * đợi cho đến khi element được hiển thị trên trình duyệt
     *
     * @param driver
     * @param xpath
     */
    public void waitForElementVisible(WebDriver driver, String xpath) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(xpath)));
    }

    /**
     * đợi cho một element không còn hiển thị trên trình duyệt
     *
     * @param driver
     * @param xpath
     */
    public void waitForElementInVisible(WebDriver driver, String xpath) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(xpath)));
    }


    /**
     * đợi cho đến khi tất cả các element với xpath được hiển thị
     *
     * @param driver
     * @param xpath
     */
    public void waitForAllElementVisible(WebDriver driver, String xpath) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(xpath)));
    }

    /**
     * đợi cho đến khi tất cả các element với xpath không còn được hiển thị
     *
     * @param driver
     * @param xpath
     */
    public void waitForAllElementInVisible(WebDriver driver, String xpath) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getWebElementList(driver, xpath)));
    }

    /**
     * đợi cho đến khi element có thể click được vào
     *
     * @param driver
     * @param xpath
     */
    public void waitForElementClickAble(WebDriver driver, String xpath) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(xpath)));
    }

}
