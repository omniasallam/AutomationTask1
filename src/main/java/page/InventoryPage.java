package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {
    private WebDriver driver;
    private By appLogo=By.className("app_logo");
    private By cartIcon=By.className("shopping_cart_link");
    private By inventoryItems=By.className("inventory_item");
    public InventoryPage(WebDriver driver){
        this.driver=driver;
    }
    public String getTitleOfInventory(){
       return driver.findElement(appLogo).getText();
    }
    public boolean getCartIconIsDisplayed(){
        return driver.findElement(cartIcon).isDisplayed();
    }
    public int getNumberOfProduct(){
        return driver.findElements(inventoryItems).size();
    }
}
