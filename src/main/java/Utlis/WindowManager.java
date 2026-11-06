package Utlis;

import org.openqa.selenium.WebDriver;

import java.util.Set;

public class WindowManager {
    private WebDriver driver;
    public WindowManager(WebDriver driver){
        this.driver=driver;
    }
    public void switchTab(String currentUrl){
        Set<String> windowHandles =driver.getWindowHandles();
        for (String windowHandle: windowHandles ){
            System.out.println("THe current Window Handle is" + windowHandle);
            driver.switchTo().window(windowHandle);
            if (driver.getCurrentUrl().equals(currentUrl)){
                break;
            }

        }
    }
}
