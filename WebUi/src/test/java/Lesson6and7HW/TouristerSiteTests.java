package Lesson6and7HW;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Story("Пользователь авторизуется на сайте, заходит на страницу своего аккаунта, выбирает посещенную им страну")
public class TouristerSiteTests {
    WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;
    String login;
    String password;
    NavigationBar navBar;
    AvailableCountries availableCountries;


    @BeforeAll

    static void registerDriver(){

        WebDriverManager.chromedriver().setup();
    }
    @BeforeEach
    @Description("Открыта главная страница сайта www.tourister.ru")
    void setupBrowser(){
        driver = new ChromeDriver();

        driver.get("https://www.tourister.ru/");
        navBar =new NavigationBar(driver);
        availableCountries = new AvailableCountries(driver);

    }
    @Test
    @DisplayName("Добавение посещенных страны или города авторизованным пользователем")
    @Description("Пользователь авторизуется, на странице профиля добавляет посещенную им страну, месяц и год")
    @Severity(SeverityLevel.CRITICAL)
    void signInAndSelectCountryTest() {
        login = "eurosett017";
        password = "xyZ1234$";
        navBar.authorization(login, password);
        navBar.goToMyAccountPage()
                .addVisitedCountry()
                .addCountry("Римини", "Сентябрь", "2013");
    }
    @Test
    @DisplayName("Проверка отображения элемента в выпадающем меню при наведении")
    @Severity(SeverityLevel.NORMAL)
    void findElementTest(){
    navBar.tryTicketsHover();
    }
    @Test
    @DisplayName("Проверка работы кнопки Доступные страны на навигационной панели")
    @Severity(SeverityLevel.NORMAL)
    void goToCountriesAvailablePage() {
        navBar.showCountriesAvailable();

    }
    @Test
    @DisplayName("Тест кнопки поиска на навигационной панели")
    @Severity(SeverityLevel.CRITICAL)
    void searchDataTest(){
        navBar.searchData("геленджик");
    }
    @Test
    @DisplayName("Проверка поиска невалидных данных")
    void searchNonvalidDataTest(){
        navBar.searchNonvalidData("us87yssd");
    }

    @AfterEach
    void tearDown() {

        LogEntries browserLogs = driver.manage().logs().get(LogType.BROWSER);
        List<LogEntry> allLogRows = browserLogs.getAll();
        if (allLogRows.size() > 0 ) {
            allLogRows.forEach(logEntry -> {
                        System.out.println(logEntry.getMessage());

        });

        driver.quit();
    }
}}

