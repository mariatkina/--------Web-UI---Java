package Lesson6HW;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
    void setupBrowser(){
        driver = new ChromeDriver();

        driver.get("https://www.tourister.ru/");
        navBar =new NavigationBar(driver);
        availableCountries = new AvailableCountries(driver);

    }
    @Test
    @DisplayName("Добавение посещенных страны или города авторизованным пользователем")
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
    void findElementTest(){
    navBar.tryTicketsHover();
    }
    @Test
    @DisplayName("Проверка работы кнопки Доступные страны на навигационной панели")
    void goToCountriesAvailablePage() {
        navBar.showCountriesAvailable();

    }
    @Test
    @DisplayName("Тест кнопки поиска на навигационной панели")
    void searchDataTest(){
        navBar.searchData("геленджик");
    }
    @Test
    @DisplayName("Проверка поиска невалидных данных")
    void searchNonvalidDataTest(){
        navBar.searchNonvalidData("us87yssd");
    }

    @AfterEach
    void tearDown(){
        driver.quit();
    }
}

