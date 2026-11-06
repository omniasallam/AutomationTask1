package page;

import org.openqa.selenium.WebDriver;

public class FacebookPage {
    private WebDriver driver;
    public FacebookPage(WebDriver driver){
        this.driver=driver;
    }
    public String getUrl(){
        return driver.getCurrentUrl();
    }
}
