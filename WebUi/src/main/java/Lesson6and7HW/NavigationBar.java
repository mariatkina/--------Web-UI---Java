package Lesson6and7HW;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class NavigationBar extends BaseView {
    AvailableCountries availableCountries;
    @FindBy(xpath = "//div[@class='logo']")
    public WebElement returnToMainPageButton;
    @FindBy(xpath = "//div[@class='nt-menu-item-mainlink']//a[.='Лента']")
    public WebElement feedButton;
    @FindBy(xpath = "//div[@class='nt-menu-item nt-menu-item-openerfix100 nt-menu-item-nothide nt-menu-item-relative']")
    public WebElement ticketsHover;
    public final String trainTicketsXpath= "//div[@class='nt-menu-city-old-item']//a[.='ЖД билеты']";
    @FindBy(xpath = "//div[@class='nt-menu-city-old-item']//a[.='ЖД билеты']")
    public WebElement trainTickets;
    @FindBy(xpath = "//div[@class='nt-menu-item-mainlink']//a[.='Еще']")
    public WebElement elseInformationHover;
    @FindBy(xpath ="//a[.='Доступные страны']")
    public WebElement countriesAvailable;
    @FindBy(xpath ="//a[.='Исследуй Россию']")
    public WebElement exploreRussia;
    @FindBy(xpath ="//a[.='Кэшбэк']")
    public WebElement cashbackHover;
    @FindBy(xpath ="//a[@class='cashback_head']")
    public List<WebElement> cashback;
    @FindBy(xpath ="//span[@class='nt-search-button']")
    public WebElement searchButton;
    @FindBy(id ="poisk")
    public WebElement searchEnterData;
    @FindBy(xpath = "//div[@onclick='openLoginWindow();']")
    public WebElement signInButton;
    @FindBy(id = "login_frame")
    public WebElement loginFrame;
    @FindBy(id = "l_login")
    public WebElement userLogin;
    @FindBy(id = "l_pass")
    public WebElement userPassword;
    @FindBy(xpath = "//a[@class='redbutton ifr']")
    public WebElement authEnterButton;

    private final String authorizedUsersImgLocator = "//div[@class='nt-headuser-avatar']//img";

    @FindBy(xpath = authorizedUsersImgLocator)
    public WebElement authorizedUsersImg;
    public NavigationBar(WebDriver driver) {
        super(driver);

    }
    public void authorization(String login, String password){
        signInButton.click();
        driver.switchTo().frame(loginFrame);
        userLogin.sendKeys(login);
        userPassword.sendKeys(password);
        authEnterButton.click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(authorizedUsersImgLocator)));
        Assertions.assertTrue(authorizedUsersImg.isDisplayed());
    }
    public MyAccountPage goToMyAccountPage(){
        actions.moveToElement(authorizedUsersImg).click().perform();
        return new MyAccountPage(driver);
    }
    public void tryTicketsHover(){
        actions.moveToElement(ticketsHover).perform();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(trainTicketsXpath)));
        Assertions.assertTrue(trainTickets.isDisplayed());
    }
    public AvailableCountries showCountriesAvailable() {

        countriesAvailable.click();

        return new AvailableCountries(driver);

    }
    public void searchNonvalidData(String word){
        searchButton.click();
        searchEnterData.sendKeys(word, Keys.ENTER);
        Assertions.assertTrue(driver.findElement(By.xpath("//div[@class='not_found']")).isDisplayed());

    }
    public void searchData(String word){
        searchButton.click();
        searchEnterData.sendKeys(word, Keys.ENTER);
        Assertions.assertTrue(driver.findElement(By.xpath("//div[@class='xpoisk1-1']")).isDisplayed());

    }

}
