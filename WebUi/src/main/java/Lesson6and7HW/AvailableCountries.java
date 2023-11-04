package Lesson6and7HW;

import org.openqa.selenium.WebDriver;

public class AvailableCountries extends BaseView{
    public NavigationBar navBar;


    public AvailableCountries(WebDriver driver) {
        super(driver);
        navBar = new NavigationBar(driver);
    }

}
