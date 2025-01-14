package Utilis;

import Base.Hook;
import net.serenitybdd.annotations.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class Actions extends Hook {

    private Wait<WebDriver> waitF;

    public Actions(WebDriver driver) {
        this.driver = driver;
        this.waitF = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofMillis(5))
                .ignoring(Exception.class)
                .ignoring(StaleElementReferenceException.class);
    }

    @Step
    public void clickXpath(String xpath) {
        try {
            // Znajdź element
            WebElement element = driver.findElement(By.xpath(xpath));

            // Sprawdź, czy element jest widoczny i klikalny
            waitF.until(ExpectedConditions.elementToBeClickable(element));

            // Przewiń do elementu
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

            // Kliknij standardowo
            element.click();
            System.out.println("Kliknięto element standardowo: " + xpath);

        } catch (Exception e) {
            System.out.println("Standardowe kliknięcie nie powiodło się dla elementu: " + xpath + ". Próba użycia JavaScript.");

            try {
                // Kliknij za pomocą JavaScript
                WebElement element = driver.findElement(By.xpath(xpath));
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", element);
                System.out.println("Kliknięto element za pomocą JavaScript: " + xpath);

            } catch (Exception jsException) {
                System.out.println("Nie udało się kliknąć elementu nawet za pomocą JavaScript: " + xpath);
                jsException.printStackTrace();
            }
        }
    }

    @Step
    public void sendKeys(String xpath, String text) {
        driver.findElement(By.xpath(xpath)).sendKeys(text);
    }

    @Step
    public void waitForElementVisible(String element) {
        waitF.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
    }

    @Step
    public void waitForElementNotVisible(String element) {
        waitF.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(element)));
    }

    @Step
    public void scrollToElement(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }


   @Step
    public void clickSubmitButton(String buttonXpath) {

        try {
            // Znajdź przycisk
            WebElement button = driver.findElement(By.xpath(buttonXpath));

            // Przesuń do przycisku
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);

            // Kliknij standardowo
            button.click();
            System.out.println("Kliknięto przycisk standardowo.");

        } catch (Exception e) {
            System.out.println("Standardowe kliknięcie nie powiodło się. Próba użycia JavaScript.");

            // Kliknij za pomocą JavaScript
            WebElement button = driver.findElement(By.xpath(buttonXpath));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", button);
            System.out.println("Kliknięto przycisk za pomocą JavaScript.");
        }
    }

    @Step
    public boolean isElementVisible(String xpath) {
        try {
            WebElement element = driver.findElement(By.xpath(xpath));
            boolean displayed = element.isDisplayed();
            return displayed;
        } catch (NoSuchElementException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}


