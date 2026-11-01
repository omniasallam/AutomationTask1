package tests;

import Utlis.DataDriven;
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
}
