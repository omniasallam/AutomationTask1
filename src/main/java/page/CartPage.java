package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class CartPage {
    private WebDriver driver;
    private By cartItems = By.className("cart_item");
    private By productName = By.className("inventory_item_name");
    private By removeButton = By.tagName("button");
    private By continueShoppingButton = By.id("continue-shopping");
    private By checkOutButton= By.id("checkout");

    public CartPage(WebDriver driver) {

        this.driver = driver;
    }

    public int getNumnerOfCartItems() {

        return driver.findElements(cartItems).size();
    }


    public List<String> getCartItems() {
        List<String> products = new ArrayList<>();
        List<WebElement> items = driver.findElements(cartItems);
        for (WebElement item : items) {
            String nameItem = item.findElement(productName).getText();
            products.add(nameItem);
        }
        return products;
    }

    /**
     * method related remove
     * @param name
     */
    public void removeProduct(String name){
        List<WebElement> items = driver.findElements(cartItems);
        for (WebElement item:items){
            String itemName=  item.findElement(productName).getText();
            if (itemName.equals(name)){
                item.findElement(removeButton).click();
                break;
            }
        }
    }

    /**
     * method related to  back
     * @return
     */
    public InventoryPage clickContinueShopping(){
        driver.findElement(continueShoppingButton).click();
        return new InventoryPage(driver);
    }

    public CheckOutPage clickChechOut(){
        driver.findElement(checkOutButton).click();
        return new CheckOutPage(driver);
    }

}
