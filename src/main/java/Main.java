import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static org.testng.reporters.Files.copyFile;

public class Main {
    WebDriver driver;

    String url = "https://rozetka.com.ua";
    String searchedItem = "Монитор";

    By searchFieldRozetka = By.cssSelector("input[class^=search]");
    By clickButtonSearchRozetka = By.cssSelector("button[class~=search-form__submit]");

    By firstItem = By.xpath("//span[@class='goods-tile__title']");
    By promotionForWait = By.xpath("//a[@target='_blank']");

    By buttonBuy = By.cssSelector("button[class^=buy-button]");
    By buttonCartReceipt = By.cssSelector("a[class~=cart-receipt__submit]");
    By buttonCheckoutSubmit = By.cssSelector("input[class~=checkout-total__submit]");

    @BeforeClass
    public void SetUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/katerinasalamatina/Desktop/task8/waiters/src/main/resources/chromedriver");
    }

    @BeforeMethod
    public void openBrowser () {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test //(retryAnalyzer = Retry.class)
    public void test1 () {

        //Open Rozetka
        driver.get(url);

        //Clear the field and search "Монитор"
        WebElement inputRozetkaSearch = driver.findElement(searchFieldRozetka);
        inputRozetkaSearch.clear();
        inputRozetkaSearch.sendKeys(searchedItem);

        //Click button "Search"
        WebElement searchButtonRozetka = driver.findElement(clickButtonSearchRozetka);
        searchButtonRozetka.click();

        //Open first product
        WebElement clickFirstElement = driver.findElement(firstItem);
        clickFirstElement.click();

        //Add explicit waiter to wait page is loaded
        WebDriverWait wait = new WebDriverWait(driver, 5);

        wait.until(ExpectedConditions.visibilityOfElementLocated(promotionForWait));

        //Click "Купить"
        WebElement searchButtonBuy = driver.findElement(buttonBuy);
        searchButtonBuy.click();

        //Add explicit waiter to wait cart page is loaded
        wait.until(ExpectedConditions.visibilityOfElementLocated(buttonCartReceipt));

        //Click "Оформить заказ"
        WebElement buttonCheckout = driver.findElement(buttonCartReceipt);
        buttonCheckout.click();

        //Add explicit waiter to wait cart checkout page is loaded
        wait.until(ExpectedConditions.visibilityOfElementLocated(buttonCheckoutSubmit));
    }

    /*@AfterMethod(alwaysRun = true)
    public void takeScreenshot (ITestResult testResult) throws IOException {
        if (!testResult.isSuccess()) {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            copyFile(scrFile, new File(testResult.getName() + "[" + LocalDate.now() + "][" + System.currentTimeMillis() + "].png"));
        }
    }*/

    @AfterClass
    public void closeBrowser () {
        driver.quit();
    }
}
