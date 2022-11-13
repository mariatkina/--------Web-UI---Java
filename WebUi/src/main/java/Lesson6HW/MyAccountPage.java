package Lesson6HW;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BaseView {
    public NavigationBar navBar;

    @FindBy(xpath = "//a[.='Указать посещенные страны']")
    public WebElement visitedCountriesBttn;

    public MyAccountPage(WebDriver driver) {
        super(driver);
        navBar = new NavigationBar(driver);
    }
    public VisitedCountries addVisitedCountry(){
        visitedCountriesBttn.click();
        return new VisitedCountries(driver);
    }
}
