package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class InventoryPage {
    private WebDriver driver;
    private By appLogo=By.className("app_logo");
    private By cartIcon=By.className("shopping_cart_link");
    private By inventoryItems=By.className("inventory_item");
    private  By socialLindIn=By.cssSelector("a[href*='linkedin']");
    private By socialFacebook=By.cssSelector("a[href*='facebook']");
    private By socialTwitter=By.cssSelector("a[href*='x.com']");
    private By productName= By.className("inventory_item_name");
    private By buttonCart= By.tagName("button");
    private By totalPrice= By.className("inventory_item_price");
    private By buttonMenu= By.id("react-burger-menu-btn");
    private By logout= By.id("logout_sidebar_link");
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


    public LindinPage clickLindIn(){
        driver.findElement(socialLindIn).click();
        return new LindinPage(driver);
    }
    public FacebookPage clickFacebook(){
        driver.findElement(socialFacebook).click();
        return new FacebookPage(driver);
    }
    public TwitterPage clickTwitter(){
        driver.findElement(socialTwitter).click();
        return new TwitterPage(driver);
    }
 public CartPage clickCartIcon(){
        driver.findElement(cartIcon).click();
        return new CartPage(driver);
 }

    public void addProductToCart( String name ){
        List<WebElement> products =driver.findElements(inventoryItems);
        for (WebElement product : products){
            String nameOfProduct  = product.findElement(productName).getText();
            if (nameOfProduct.equals(name)){
                product.findElement(buttonCart).click();
                break;
            }

        }
    }

    /**
     * method get the text of button to confirm if button remove or add
     * @param name
     * @return
     */
     public  String getButtonText(String name) {
         List<WebElement> products = driver.findElements(inventoryItems);
         for (WebElement product : products) {
             String nameOfProduct = product.findElement(productName).getText();
             if (nameOfProduct.equals(name)) {
                 return product.findElement(buttonCart).getText();
             }
         }
         return "";
     }

     public double getTotalPrice(String name){
        List<WebElement> productsItems= driver.findElements(inventoryItems);
        for (WebElement productItem:productsItems){
            String nameOfProdct=productItem.findElement(productName).getText();
            if (nameOfProdct.equals(name)){
               String price= productItem.findElement(totalPrice).getText();
               return Double.parseDouble(price.replace("$",""));
            }
        }
        return 0;
     }

    /**
     *  method logout
     */
   public void clickMenuButton(){
    driver.findElement(buttonMenu).click();

   }
   public LoginPage clickButtonLogout(){
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       wait.until(ExpectedConditions.visibilityOf(driver.findElement(logout)));
        driver.findElement(logout).click();
        return new LoginPage(driver);
   }

}
