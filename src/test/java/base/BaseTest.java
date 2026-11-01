package base;

import Utlis.DataDriven;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import page.LoginPage;

public class BaseTest {
    protected WebDriver driver;
    protected LoginPage loginPage;

    @BeforeClass
    public void setUp(){
        driver= new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        loginPage= new LoginPage(driver);
        driver.manage().window().maximize();
    }
    @AfterClass
    public  void tearDown(){
        driver.quit();
    }
}
