package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckOutPage {
    private WebDriver driver;
    private By title= By.className("title");
    private By inputName= By.id("first-name");
    private By inputLastName=By.id("last-name");
    private By inputZip= By.id("postal-code");
    private By buttonContinue=By.id("continue");
    private By itemTotal=By.className("summary_subtotal_label");
    public CheckOutPage(WebDriver driver){
        this.driver=driver;
    }
    public String getTitleOfCheckOut(){
       return driver.findElement(title).getText();
    }
    public void setFirstName(String firstName){
        driver.findElement(inputName).sendKeys(firstName);
    }
    public void setLastName(String lastName){
        driver.findElement(inputLastName).sendKeys(lastName);
    }
    public void setZipCode(String code){
        driver.findElement(inputZip).sendKeys(code);
    }
    public void clickButtonContinue(){
        driver.findElement(buttonContinue).click();
    }
    public double getItemTotal(){
        String totalItem=driver.findElement(itemTotal).getText();
        return Double.parseDouble(totalItem.replace("Item total: $",""));

    }
}
