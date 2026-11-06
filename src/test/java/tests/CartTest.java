package tests;

import Utlis.DataDriven;
import Utlis.WindowManager;
import base.BaseTest;
import com.fasterxml.jackson.databind.JsonNode;
import org.testng.annotations.Test;
import page.CartPage;
import page.InventoryPage;

import java.util.ArrayList;
import java.util.List;

import static javax.swing.UIManager.get;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CartTest extends BaseTest {

    @Test
    public void testCartIsEmpty() {
        var testData = DataDriven.jsonReader();
        loginPage.setUserName(testData.get("validUsername").asText());
        loginPage.setPassword(testData.get("validPassword").asText());
        var inventoryPage = loginPage.clickLoginButton();
        var cartPage = inventoryPage.clickCartIcon();
        assertEquals(cartPage.getNumnerOfCartItems(), 0);
    }

    @Test
    public void testAddThreeProducts() {
        var testData = DataDriven.jsonReader();
        loginPage.setUserName(testData.get("validUsername").asText());
        loginPage.setPassword(testData.get("validPassword").asText());
        var inventoryPage = loginPage.clickLoginButton();
        List<String>expectedProducts= new ArrayList<>();
        for (JsonNode product : testData.get("cartProducts")) {
           // System.out.println(product +" json product");
            String productName=product.asText();
            inventoryPage.addProductToCart(productName);
            expectedProducts.add(productName);
        }

        var cartPage = inventoryPage.clickCartIcon();
       List<String>actualProducts= cartPage.getCartItems();
        assertEquals(
               actualProducts,
               expectedProducts);
    }

    @Test
    public void testRemoveOneProduct(){
        var testData = DataDriven.jsonReader();
        loginPage.setUserName(testData.get("validUsername").asText());
        loginPage.setPassword(testData.get("validPassword").asText());
        var inventoryPage = loginPage.clickLoginButton();
        for (JsonNode product : testData.get("cartProducts")) {
            String productName=product.asText();
            inventoryPage.addProductToCart(productName);
        }
       var cartPage= inventoryPage.clickCartIcon();
        cartPage.removeProduct("Sauce Labs Bolt T-Shirt");
        //System.out.println(cartPage.getCartItems());
        var backToInventoryPage=cartPage.clickContinueShopping();

        assertEquals(backToInventoryPage.getButtonText("Sauce Labs Bolt T-Shirt"),
                "Add to cart");
        assertEquals(backToInventoryPage.getButtonText("Sauce Labs Backpack"),
                "Remove");
        assertEquals(backToInventoryPage.getButtonText("Sauce Labs Onesie"),
                "Remove");
    }
    @Test
    public void testVerifyCartTotalPrice(){
        var testData = DataDriven.jsonReader();
        loginPage.setUserName(testData.get("validUsername").asText());
        loginPage.setPassword(testData.get("validPassword").asText());
        var inventoryPage = loginPage.clickLoginButton();
        double total=0;
        for (JsonNode product : testData.get("cartProducts")) {
            String productName=product.asText();
           double price= inventoryPage.getTotalPrice(productName);
          //  System.out.println(productName + " = " + price);
           total+=price;
            inventoryPage.addProductToCart(productName);
        }
        var cartPage=inventoryPage.clickCartIcon();
        var checkoutPage=cartPage.clickChechOut();
       String title= checkoutPage.getTitleOfCheckOut();
       // System.out.println(title);
        checkoutPage.setFirstName("omnia");
        checkoutPage.setLastName("mohamed");
        checkoutPage.setZipCode("12345");
        checkoutPage.clickButtonContinue();
        double itemsTotal=checkoutPage.getItemTotal();
        assertEquals(itemsTotal,total,0.01);
    }

    @Test
    public void testCheckoutWithAnEmptyCart(){
        var testData = DataDriven.jsonReader();
        loginPage.setUserName(testData.get("validUsername").asText());
        loginPage.setPassword(testData.get("validPassword").asText());
        var inventoryPage = loginPage.clickLoginButton();
       var cartPage= inventoryPage.clickCartIcon();
       cartPage.getCartItems().isEmpty();
      var checkoutPage= cartPage.clickChechOut();
       String title=checkoutPage.getTitleOfCheckOut();
       assertEquals(title,"Checkout: Your Information",
               "Please add product to cart Items");
    }

    @Test
    public void testCartStateAfterLogout(){
        var testData = DataDriven.jsonReader();
        loginPage.setUserName(testData.get("validUsername").asText());
        loginPage.setPassword(testData.get("validPassword").asText());
        var inventoryPage = loginPage.clickLoginButton();
        String firstProduct=testData.get("cartProducts").get(0).asText();
        String secondProduct=testData.get("cartProducts").get(1).asText();
        inventoryPage.addProductToCart(firstProduct);
        inventoryPage.addProductToCart(secondProduct);
       inventoryPage.clickMenuButton();
       var loginPageAfterLogout=inventoryPage.clickButtonLogout();
       assertTrue(driver.getCurrentUrl().contains("saucedemo.com"),
               "You not in LoginPage");
       loginPageAfterLogout.setUserName(testData.get("validUsername").asText());
       loginPageAfterLogout.setPassword(testData.get("validPassword").asText());
      var inventoryAfterLogin= loginPageAfterLogout.clickLoginButton();
       var cartPageAfterLogin=inventoryAfterLogin.clickCartIcon();
      List<String> products=cartPageAfterLogin.getCartItems();
        System.out.println(" Cart after Logout/Login: " + products );
    }

}
