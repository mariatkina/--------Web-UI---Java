package Lesson3HW;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class IvanovskyTrikTests {
    public static void main(String[] args) throws InterruptedException {
        //1.авторизация пользователя по Email:
        authByEmail();
        //2.добавление товара в корзину:
        addItemsToCart();
        //3.удаление товара из корзины:
        deleteItemsFromCart();
        //4.поиск товара по названию(валидное значение):
        findItems();
        //5.поиск товара по названию(невалидное значение):
        findNotValidItems();

    }
    public static void authByEmail() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.get("https://ivanovskij-trikotazh.ru/");
        driver.manage().window().fullscreen();
        driver.findElement(By.xpath("//a[.='Войти']")).click();
        driver.findElement(By.xpath("//button[.='Email']")).click();
        driver.findElement(By.id("modal-email")).sendKeys("eurosett017@gmail.com");
        driver.findElement(By.id("modal-password")).sendKeys("xyZ1234$");
        driver.findElement(By.xpath("//button[.='Войти']")).click();

        Thread.sleep(5000);
        driver.quit();

    }
    public static void addItemsToCart() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.get("https://ivanovskij-trikotazh.ru/");
        List<WebElement> items = driver.findElements(By.xpath("//a[@class='retailrocket-item-info']"));
        items.stream().findFirst().get().click();
        List<WebElement> sizes = driver.findElements(By.xpath("//label[@class='noselect1']"));
        sizes.stream().findFirst().get().click();
        driver.findElement(By.id("plus")).click();
        driver.findElement(By.id("button-cart")).click();
        Thread.sleep(2);
        driver.get("https://ivanovskij-trikotazh.ru/checkout");
        Thread.sleep(2000);
        driver.quit();
    }
    public static void deleteItemsFromCart() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://ivanovskij-trikotazh.ru/");
        List<WebElement> items = driver.findElements(By.xpath("//a[@class='retailrocket-item-info']"));
        items.stream().findFirst().get().click();
        List<WebElement> sizes = driver.findElements(By.xpath("//label[@class='noselect1']"));
        sizes.stream().findFirst().get().click();
        driver.findElement(By.id("plus")).click();
        driver.findElement(By.id("button-cart")).click();
        Thread.sleep(2);
        driver.get("https://ivanovskij-trikotazh.ru/checkout");
        driver.findElement(By.xpath("//i[@class='fa fa-trash-o']")).click();
        Thread.sleep(2000);
        driver.quit();
    }
    public static void findItems() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.get("https://ivanovskij-trikotazh.ru/");
        driver.findElement(By.id("js-mobileSearchOpen")).click();
        WebElement search = driver.findElement(By.xpath("//input [@name='search']"));
        search.sendKeys("Наволочка");
        search.sendKeys(Keys.ENTER);

        //driver.findElement(By.xpath("//div[@class='button-search']")).click();
        Thread.sleep(2000);
        driver.quit();

    }
    public static void findNotValidItems() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.get("https://ivanovskij-trikotazh.ru/");
        driver.findElement(By.id("js-mobileSearchOpen")).click();
        WebElement search = driver.findElement(By.xpath("//input [@name='search']"));
        search.sendKeys("tjsrtjs");
        search.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        driver.quit();
    }
}
