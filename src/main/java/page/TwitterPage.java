package page;

import org.openqa.selenium.WebDriver;

public class TwitterPage {
    private WebDriver driver;
    public TwitterPage(WebDriver driver){
        this.driver=driver;
    }

    public String getUrl(){

        return driver.getCurrentUrl();
    }
}
