package pom;

import org.openqa.selenium.WebDriver;


public abstract class BasePage {
    protected WebDriver driver;
    private String PATH;

    public BasePage(WebDriver driver, String PATH) {
        this.driver = driver;
        this.PATH = PATH;
    }
}