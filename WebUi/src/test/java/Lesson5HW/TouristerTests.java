package Lesson5HW;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TouristerTests {
    WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;
    String login = "eurosett017";

    @BeforeAll
    static void registerDriver(){
        WebDriverManager.chromedriver().setup();
    }
    @BeforeEach
    void setupBrowser(){
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(2));
        actions = new Actions(driver);
        driver.get("https://www.tourister.ru/");
    }
    @Test
    @DisplayName("Проверка перехода на страницу пользователя после авторизации на сайте")
    void authTest(){
        driver.findElement(By.xpath("//div[@onclick='openLoginWindow();']")).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("whtml")));
        driver.switchTo().frame(driver.findElement(By.id("login_frame")));
        driver.findElement(By.id("l_login")).sendKeys(login);
        driver.findElement(By.id("l_pass")).sendKeys("xyZ1234$");
        driver.findElement(By.xpath("//a[@class='redbutton ifr']")).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='nt-headuser-avatar']")));
        actions.moveToElement(driver.findElement(By.xpath("//div[@class='nt-headuser-avatar']"))).perform();
        driver.findElement(By.xpath("//a[.='Моя страница']")).click();

       Assertions.assertTrue(driver.getCurrentUrl().contains(login));
    }

    @Test
    @DisplayName("Проверка отображения элемента в выпадающем меню при наведении")
    void findElementTest() {
    actions.moveToElement(driver.findElement(By.xpath("//div[@class='nt-menu-item nt-menu-item-openerfix100 nt-menu-item-nothide nt-menu-item-relative']"))).perform();
    webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='nt-menu-city-old-item']//a[.='ЖД билеты']")));
    actions.moveToElement(driver.findElement(By.xpath("//div[@class='nt-menu-city-old-item']//a[.='ЖД билеты']"))).perform();

    Assertions.assertTrue(driver.findElement(By.xpath("//div[@class='nt-menu-city-old-item']//a[.='ЖД билеты']")).isDisplayed());
    }

    @Test
    @DisplayName("Проверка поиска по сайту c удалением баннера")
    void searchTest () {
        driver.findElement(By.xpath("//span[@class='nt-search-button']")).click();
        WebElement search = driver.findElement(By.id("poisk"));
        search.sendKeys("Египет");
        search.sendKeys(Keys.ENTER);
        ((JavascriptExecutor)driver).executeScript("let element = document.evaluate(\"//div[@class='sk1100_right_adv']\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null)\n" +
                "element.singleNodeValue.remove()");
        actions.scrollToElement(driver.findElement(By.xpath("//div[.='8.']")));

        Assertions.assertTrue(driver.findElement(By.xpath("//div[@class='xpoisk1-1']")).isDisplayed());
    }

    @Test
    @DisplayName("Проверка поиска по сайту при вводе невалидных данных")
    void searchNonvalidDataTest () {
        driver.findElement(By.xpath("//span[@class='nt-search-button']")).click();
        WebElement search = driver.findElement(By.id("poisk"));
        search.sendKeys("tu6tdjyd");
        search.sendKeys(Keys.ENTER);

        Assertions.assertTrue(driver.findElement(By.xpath("//div[@class='not_found']")).isDisplayed());
    }

    @AfterEach
    void tearDown(){
        driver.quit();
    }
}
