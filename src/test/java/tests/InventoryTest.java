package tests;

import Utlis.DataDriven;
import Utlis.WindowManager;
import base.BaseTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class InventoryTest extends BaseTest {
        @Test
    public void testInventory(){
           var testData= DataDriven.jsonReader();
            loginPage.setUserName(testData.get("validUsername").asText());
            loginPage.setPassword(testData.get("validPassword").asText());
           var inventoryPage= loginPage.clickLoginButton();
           assertEquals(inventoryPage.getTitleOfInventory(),"Swag Labs",
                   "You didn`t login");
           assertTrue(inventoryPage.getCartIconIsDisplayed());
           assertEquals(inventoryPage.getNumberOfProduct(),6,
                   "The number is not correct");
        }
          @Test
    public void testSocialLink(){
           var testData= DataDriven.jsonReader();
           loginPage.setUserName(testData.get("validUsername").asText());
           loginPage.setPassword(testData.get("validPassword").asText());
          var inventoryPage= loginPage.clickLoginButton();
         //LinkedIn
         var lindinPage=inventoryPage.clickLindIn();
         WindowManager windowManager= new WindowManager(driver);
         windowManager.switchTab("linkedin");
        String urlLindin= lindinPage.getUrl();
        assertTrue(urlLindin.contains("linkedin"));



          }
          @Test
    public void testSocialLink1(){
        var testData= DataDriven.jsonReader();
        loginPage.setUserName(testData.get("validUsername").asText());
        loginPage.setPassword(testData.get("validPassword").asText());
        var inventoryPage= loginPage.clickLoginButton();
        //Facebook
        var facebookPage =inventoryPage.clickFacebook();
        WindowManager windowManager= new WindowManager(driver);
        windowManager.switchTab("facebook");
        String urlFace= facebookPage.getUrl();
        assertTrue(urlFace.contains("facebook"));



    }
    @Test
    public void testSocialLink2(){
        var testData= DataDriven.jsonReader();
        loginPage.setUserName(testData.get("validUsername").asText());
        loginPage.setPassword(testData.get("validPassword").asText());
        var inventoryPage= loginPage.clickLoginButton();
        //x.com
        var  twitterPage=inventoryPage.clickTwitter();
        WindowManager windowManager= new WindowManager(driver);
        windowManager.switchTab("x.com");
        String urlTwitter= twitterPage.getUrl();
        assertTrue(urlTwitter.contains("x.com"));



    }

}
