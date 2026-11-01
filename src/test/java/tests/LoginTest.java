package tests;

import Utlis.DataDriven;
import base.BaseTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {
    @Test
    public void testSuccessfulLogin(){
       // loginPage.setUserName("standard_user");
       // loginPage.setPassword("secret_sauce");
     //   var inventoryPage=loginPage.clickLoginButton();
   // assertTrue(inventoryPage.getTitleOfInventory().contains("Swag Labs"),
      //      "You didn`t login");
       var testData= DataDriven.jsonReader();
       loginPage.setUserName(testData.get("validUsername").asText());
       loginPage.setPassword(testData.get("validPassword").asText());
       loginPage.clickLoginButton();
       assertTrue(driver.getCurrentUrl().contains("/inventory.html"),
               "You didn`t login");


    }
    @Test
    public void testInvalidLogin(){
       var testData= DataDriven.jsonReader();
       loginPage.setUserName(testData.get("invalidUsername").asText());
       loginPage.setPassword(testData.get("invalidPassword").asText());
       loginPage.clickLoginButton();
       assertTrue(loginPage.getErrorMessage().contains("Username and password do not match"));
    }
    @Test
    public void testLoginWithoutPassword(){
        var testData= DataDriven.jsonReader();
        loginPage.setUserName(testData.get("validUsername").asText());
        loginPage.setPassword("");
        loginPage.clickLoginButton();
        assertTrue(loginPage.getErrorMessage().contains("Password is required"));
    }

}
