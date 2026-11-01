package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;
    private By userField=By.id("user-name");
    private By passwordField=By.id("password");
    private By buttonLogin= By.id("login-button");
    private  By errorMessage=By.cssSelector("[data-test='error']");
    public LoginPage(WebDriver driver){
        this.driver=driver;
    }
    public void setUserName(String userName){
        driver.findElement(userField).sendKeys(userName);
    }
    public void setPassword(String password){
        driver.findElement(passwordField).sendKeys(password);
    }
    public InventoryPage clickLoginButton(){
        driver.findElement(buttonLogin).click();
        return  new InventoryPage(driver);
    }
    public String getErrorMessage(){
      return   driver.findElement(errorMessage).getText();
    }
}
