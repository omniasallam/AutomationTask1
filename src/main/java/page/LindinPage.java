package page;

import org.openqa.selenium.WebDriver;

public class LindinPage {
    private WebDriver driver;
    public LindinPage(WebDriver driver){
        this.driver=driver;
    }
   public String getUrl(){
         return driver.getCurrentUrl();
   }
}
