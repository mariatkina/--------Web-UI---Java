package Lesson6HW;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class VisitedCountries extends BaseView{
    public NavigationBar navBar;
    @FindBy(xpath = "//a[.='Добавить посещенный город или страну']")
    public WebElement addCountryIveVisited;
    public final String countryOrCityBlock = "whtml";
    public final String matchingPlace = "ui-id-2";
    public final String monthAndYearBlock = "cityselected";
    @FindBy(id = "views")
    public WebElement cityOrCountryName;
    @FindBy(xpath = "//ul[@unselectable='on']//li")
    public List <WebElement> selectRightName;
    @FindBy(id = "date_m")
    public WebElement selectMonth;
    @FindBy(xpath = "//select[@name='date_m']//option")
    public List <WebElement> monthsList;
    @FindBy(id = "date_y")
    public WebElement selectYear;
    @FindBy(xpath = "//select[@name='date_y']//option")
    public List <WebElement> yearsList;
    @FindBy(xpath = "//a[@class='button_green']")
    public WebElement okButton;
    public final String placeIveVisitedAddedId = "hr_check_text";
    @FindBy(id = "hr_check_text")
    public WebElement placeIveVisitedAdded;
    @FindBy(xpath = "//i[@style = 'top: 10px;']")
    public WebElement exitButton;
    @FindBy(xpath = "//div[@class = 'usinfovis1_sub']//a[.='удалить']")
    public WebElement deletePlace;
    public VisitedCountries(WebDriver driver){
        super(driver);
        navBar = new NavigationBar(driver);
    }
    //Выбор посещенных страны или города авторизованным пользователем
    public void addCountry(String cityOrCountry, String month, String year) {
        addCountryIveVisited.click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(countryOrCityBlock)));

        cityOrCountryName.sendKeys(cityOrCountry);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(matchingPlace)));
        selectRightName.stream().findFirst().get().click();

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(monthAndYearBlock)));
        actions.moveToElement(selectMonth).click().perform();

        monthsList.stream().filter(m -> m.getText().equals(month)).findFirst().get().click();
        selectYear.click();

        yearsList.stream().filter(y -> y.getText().equals(year)).findFirst().get().click();
        actions.moveToElement(okButton).click().perform();

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(placeIveVisitedAddedId)));

        Assertions.assertTrue(placeIveVisitedAdded.isDisplayed());
        exitButton.click();
        driver.navigate().refresh();
        deletePlace.click();
        driver.switchTo().alert().accept();
    }
}
