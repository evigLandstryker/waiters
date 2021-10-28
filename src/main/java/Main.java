import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/Users/katerinasalamatina/Desktop/task8/waiters/src/main/resources/chromedriver");

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        //Open Rozetka
        driver.get("https://rozetka.com.ua");

        //Clear the field and search "Монитор"
        By searchFieldRozetka = By.cssSelector("input[class^=search]");
        WebElement inputRozetkaSearch = driver.findElement(searchFieldRozetka);
        inputRozetkaSearch.clear();
        inputRozetkaSearch.sendKeys("Монитор");

        //Click button "Search"
        By clickButtonSearchRozetka = By.cssSelector("button[class~=search-form__submit]");
        WebElement searchButtonRozetka = driver.findElement(clickButtonSearchRozetka);
        searchButtonRozetka.click();

        //Open first product
        By firstItem = By.xpath("//span[@class='goods-tile__title']");
        WebElement clickFirstElement = driver.findElement(firstItem);
        clickFirstElement.click();

        //Add explicit waiter to wait page is loaded
        WebDriverWait wait = new WebDriverWait(driver, 5);

        By promotionForWait = By.xpath("//a[@target='_blank']");

        wait.until(ExpectedConditions.visibilityOfElementLocated(promotionForWait));

        //Click "Купить"
        By buttonBuy = By.cssSelector("button[class^=buy-button]");
        WebElement searchButtonBuy = driver.findElement(buttonBuy);
        searchButtonBuy.click();

        //Add explicit waiter to wait cart page is loaded
        By buttonCartReceipt = By.cssSelector("a[class~=cart-receipt__submit]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(buttonCartReceipt));

        //Click "Оформить заказ"
        WebElement buttonCheckout = driver.findElement(buttonCartReceipt);
        buttonCheckout.click();

        //Add explicit waiter to wait cart checkout page is loaded
        By buttonCheckoutSubmit = By.cssSelector("input[class~=checkout-total__submit]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(buttonCheckoutSubmit));



        driver.quit();
    }
}
