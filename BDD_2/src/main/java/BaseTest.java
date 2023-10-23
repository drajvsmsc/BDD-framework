import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    WebDriver driver;


    public WebDriver Intializedriver() throws IOException { //"D:\\chrome downloads\\selenium_career_pro\\src\\test\\java\\com\\framework1\\TestComponents\\browser.properties"


    Properties prop = new Properties();
    FileInputStream fis = new FileInputStream("D:\\chrome downloads\\selenium_career_pro\\src\\test\\java\\com\\framework1\\TestComponents\\browser.properties");
    prop.load(fis);
    String browserName = System.getProperty("browser")!=null?System.getProperty("browser"): prop.getProperty("browser");
    if(browserName.contains("chrome")){
        ChromeOptions options = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        options.addArguments("headless");
//            options.setAcceptInsecureCerts(true);
        System.setProperty("webdriver.chrome.driver", "D:\\Standalone server\\SeleniumCucumberBDD-master\\CucumberJava\\src\\test\\resources\\drivers\\chromedriver.exe");
        driver = new ChromeDriver(options);
        driver.manage().window().setSize(new Dimension(1440,900));
    }
    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    Actions actions = new Actions(driver);
    return driver;
}


   
}