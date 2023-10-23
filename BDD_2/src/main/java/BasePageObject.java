

//import java.security.spec.InvalidkeySpecException; import java.security.spec.KeySpec;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import org.openqa.selenium.By;

import java.util.List;
import java.util.function.Function;

public class BasePageObject {
    private final JavascriptExecutor javascriptExecutor;
    private static final int DEFAULT_IMPLICIT_WAIT = 0;

    private FluentWait<WebDriver> fluentWait;
    private WebDriver driver;
    private static String winID = "";
    private Duration pollingInterval = Duration.ofMillis(1000);
    private Duration fluentWaitDuration = Duration.ofSeconds(1000);
    private static final String SET_INPUT = "Set input: ";
    private static final String CLICK_ACTION = "Click Action";
    private static final String DROPDOWN = "Selected value from dropdown: ";
    private static final String SET_INPUT_COMMAND = "arguments[0].value='';";
    private static final String CLICK_COMMAND = "arguments(0).click();";
    private static final String JS_DISPLAY_COMMAND = "arguments[0].style.display='block';";
    private static final String CLICK = "Click: ";
    private static final String UNICODE_FORMAT = "UTF8";
    public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
    private KeySpec ks;

    private SecretKeyFactory skf;


    private Cipher cipher;


    byte[] arrayBytes;


    private String myEncryptionkey;


    private String myEncryptionScheme;


    SecretKey key;


    public BasePageObject(WebDriver webDriver) {
        this.driver = webDriver;
        this.fluentWait = (new FluentWait(this.driver)).withTimeout(this.fluentWaitDuration).pollingEvery(this.pollingInterval).ignoring(StaleElementReferenceException.class);
        this.javascriptExecutor = (JavascriptExecutor) this.driver;
        PageFactory.initElements(this.driver, this);
    }

    public BasePageObject() {
        this.javascriptExecutor = null;
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public void get(String URL) {
        this.driver.get(URL);
    }

    public void navigateTo(String url){
        this.driver.navigate().to(url);
    }
    public void navigateBack(){
        this.driver.navigate().back();
    }
    public void navigateForward(){
        this.driver.navigate().forward();
    }
    public void navigateRefresh(){
        this.driver.navigate().refresh();
    }
    public void close(){
        this.driver.close();
    }
    public void quit(){
        this.driver.quit();
    }
    public void setImplicitWait(int seconds) {
        this.driver.manage().timeouts().implicitlyWait((long)seconds, java.util.concurrent.TimeUnit.SECONDS);
    }
    public void setWindowSize(int width, int height) {
        this.driver.manage().window().setSize(new org.openqa.selenium.Dimension(width, height));
    }
    public void maximizeWindow() {
        this.driver.manage().window().maximize();
    }
    public void switchToWindow(String windowID) {
        this.driver.switchTo().window(windowID);
    }
    public void switchToDefaultContent() {
        this.driver.switchTo().defaultContent();
    }
    protected List<WebElement> getElements(final String locator) {
        return (List)this.fluentWait.until((Function)input -> {
            return this.driver.findElements(By.xpath(locator));
        });
    } 
    protected List<WebElement> getElements(By by) {
        return (List)this.fluentWait.until((Function)input -> {
            return this.driver.findElements(by);
        });
    } 
    protected WebElement getElement(final String locator) {
        return (WebElement)this.fluentWait.until((Function)input -> {
            return this.driver.findElement(By.xpath(locator));
        });
    }
    protected WebElement getElement(By by) {
        return (WebElement)this.fluentWait.until((Function)input -> {
            return this.driver.findElement(by);
        });
    }
    protected boolean isElementOnPage(final String locator){
        this.setImplicitWait(0);
        boolean flag = !this.getElements(locator).isEmpty();
        this.setImplicitWait(DEFAULT_IMPLICIT_WAIT);
        return flag;
    }
    protected boolean isElementOnPage(By by){
        this.setImplicitWait(0);
        boolean flag = !this.getElements(by).isEmpty();
        this.setImplicitWait(DEFAULT_IMPLICIT_WAIT);
        return flag;
    }
    protected boolean isEnabled(By by){
        List<WebElement> elements = this.getElements(by);
        if(elements.size() > 0){
            return elements.get(0).isEnabled();
        
    }
        return false;}

protected boolean isEnabled(String locator){
    List<WebElement> elements = this.getElements(locator);
    if(elements.size() > 0){
        return elements.get(0).isEnabled();
    
}
return false;}

protected boolean isDisplayed(By by){
    List<WebElement> elements = this.getElements(by);
    if(elements.size() > 0){
        return elements.get(0).isDisplayed();
    
}
return false;}

protected boolean isDisplayed(String locator){
    List<WebElement> elements = this.getElements(locator);
    if(elements.size() > 0){
        return elements.get(0).isDisplayed();
    
}
return false;}

protected boolean isSelected(String locator){
    List<WebElement> elements = this.getElements(locator);
    if(elements.size() > 0){
        return elements.get(0).isSelected();}
        return false;}
        
protected boolean isSelected(By by){
    List<WebElement> elements = this.getElements(by);
    if(elements.size() > 0){
        return elements.get(0).isSelected();}
        return false;}
protected int getElementsSize(By by){
    return this.isElementOnPage(by)?this.getElements(by).size():0;}
        
protected int getElementsSize(String locator){
    return this.isElementOnPage(locator)?this.getElements(locator).size():0;}
    
    protected void setInputValue(String locator, String value, boolean clearFirst) {
       WebElement element = this.getElement(locator);
       if(clearFirst) {
           element.clear();
       }
       element.sendKeys(new CharSequence[]{value});
    }
    protected void setInputValue(By by, String value, boolean clearFirst) {
        WebElement element = this.getElement(by);
        if(clearFirst) {
            element.clear();
        }
        element.sendKeys(new CharSequence[]{value});
    }
    protected void setInputValue(String locator, String value) {
        WebElement element = this.getElement(locator);
        element.clear();
        element.sendKeys(new CharSequence[]{value});
        }
    protected void setInputValue(By by, String value) {
            WebElement element = this.getElement(by);
            element.clear();
            element.sendKeys(new CharSequence[]{value});
        }
    protected void setInputValueJs(By by, String value,boolean clearFirst) {
        WebElement element = this.getElement(by);
        if(clearFirst) {
          this.javascriptExecutor.executeScript(String.format("arguments[0].value='%s';",value), new Object[]{element})
        }
    }
    protected void setInputValueJs(String locator, String value,boolean clearFirst) {
        WebElement element = this.getElement(locator);
        if(clearFirst) {
          this.javascriptExecutor.executeScript(String.format("arguments[0].value='%s';",value), new Object[]{element})
        }
    }
    protected void setInputValueJs(String locator, String value){
        WebElement element = this.getElement(locator);
        element.clear();
        this.javascriptExecutor.executeScript(String.format("arguments[0].value='%s';",value), new Object[]{element});
    }
    protected void setInputValueJs(By by, String value){
        WebElement element = this.getElement(by);
        element.clear();
        this.javascriptExecutor.executeScript(String.format("arguments[0].value='%s';",value), new Object[]{element});
    }
    protected void clearElement(String locator){
        this.getElement(locator).clear();}
    
    protected void clearElement(By by){
        this.getElement(by).clear();}

    protected String getText(String locator){
        return this.getElement(locator).getText();}
        
    protected String getText(By by){
        return this.getElement(by).getText();}

    protected String getAttribute(String locator, String attribute){
        return this.getElement(locator).getAttribute(attribute);}
        
    protected String getAttribute(By by, String attribute){
        return this.getElement(by).getAttribute(attribute);}

    protected String getCssValue(String locator, String cssValue){
        return this.getElement(locator).getCssValue(cssValue);}
        
    protected String getCssValue(By by, String cssValue){  
        return this.getElement(by).getCssValue(cssValue);}
        
    protected void clickElementJs(String locator){
        this.javascriptExecutor.executeScript(String.format("arguments[0].click();",new Object[]{this.getElement(locator)}));   
    }
    protected void clickElementJs(By by){
        this.javascriptExecutor.executeScript(String.format("arguments[0].click();",new Object[]{this.getElement(by)}));   
    }
    protected void makeElementVisibleAndClick(String locator){
        this.javascriptExecutor.executeScript(String.format("arguments[0].style.visibility='visible';",new Object[]{this.getElement(locator)}));
        this.javascriptExecutor.executeScript(String.format("arguments[0].click();",new Object[]{this.getElement(locator)}));
    }
    protected void makeElementVisibleAndClick(By by){
        this.javascriptExecutor.executeScript(String.format("arguments[0].style.visibility='visible';",new Object[]{this.getElement(by)}));
        this.javascriptExecutor.executeScript(String.format("arguments[0].click();",new Object[]{this.getElement(by)}));
    }
    protected void ClickElement(String locator){
        this.getElement(locator).click();}
        
    protected void ClickElement(By by){
        this.getElement(by).click();}

    protected void shiftFocusAway(String Locator){
        this.getElement(Locator).sendKeys(new CharSequence[]{Keys.TAB});
    }
    protected void shiftFocusAway(By by){
        this.getElement(by).sendKeys(new CharSequence[]{Keys.TAB});
    }
    protected void shiftFocusAway(String Locator, int times){
        for(int i = 0; i < times; i++){
            this.shiftFocusAway(Locator);
        }
    }
    protected void shiftFocusAway(By by, int times){
        for(int i = 0; i < times; i++){
            this.shiftFocusAway(by);
        }
    }
    protected String getPageSource(){
        return this.driver.getPageSource();
    }

    protected boolean closeWindow() throws Exception{
        return this.closeWindow(this.driver.getWindowHandle());
    }

    private boolean closeWindow(String windowHandle) {
        try {
            this.driver.switchTo().window(windowHandle);
            this.driver.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    protected void scrollIntoView(String locator){
        this.javascriptExecutor.executeScript(String.format("arguments[0].scrollIntoView();",new Object[]{this.getElement(locator)}));
    }
    protected void scrollIntoView(By by){
        this.javascriptExecutor.executeScript(String.format("arguments[0].scrollIntoView();",new Object[]{this.getElement(by)}));
    }

    public boolean isWindowExists(String WindowsId){
        return this.driver.getWindowHandles().contains(WindowsId);
    }
   public void dropDown(String xPath,String value){
       Select select = new Select(this.getElement(xPath));
       select.selectByVisibleText(value);
   }
   public void dropDown(By by,String value){
       Select select = new Select(this.getElement(by));
       select.selectByVisibleText(value);
   }
   
   public void setExplicitWaitVisible(String locator,long seconds){
       WebDriverWait wait = new WebDriverWait(this.driver,seconds);
       wait.until(ExpectedConditions.visibilityOf(this.getElement(locator)));
   }
   public void setExplicitWaitVisible(By by,long seconds){
       WebDriverWait wait = new WebDriverWait(this.driver,seconds);
       wait.until(ExpectedConditions.visibilityOf(this.getElement(by)));
   }
   public void setExplicitWaitclickable(String locator,long seconds){
       WebDriverWait wait = new WebDriverWait(this.driver,seconds);
       wait.until(ExpectedConditions.elementToBeClickable(this.getElement(locator)));
   }
   public void setExplicitWaitclickable(By by,long seconds){
       WebDriverWait wait = new WebDriverWait(this.driver,seconds);
       wait.until(ExpectedConditions.elementToBeClickable(this.getElement(by)));
   }
   public void selectMatOption(String locator,String option){
       WebElement element = this.getElement(locator);
       WebElement optionElement = element.findElement(By.xpath(String.format("//mat-option[text()='%s']",option)));
       optionElement.click();
   }
   public void selectMatOption(By by,String option){
       WebElement element = this.getElement(by);
       WebElement optionElement = element.findElement(By.xpath(String.format("//mat-option[text()='%s']",option)));
       optionElement.click();
   }
   public void selectMatOption(String locator,int option){
       WebElement element = this.getElement(locator);
       WebElement optionElement = element.findElements(By.xpath("//mat-option")).get(option);
       optionElement.click();
   }
   public void selectMatOption(By by,int option){
       WebElement element = this.getElement(by);
       WebElement optionElement = element.findElements(By.xpath("//mat-option")).get(option);
       optionElement.click();
   }
   //hover mouse 
   public void hoverMouse(String locator){
       Actions action = new Actions(this.driver);
       action.moveToElement(this.getElement(locator)).perform();
   }
   public void hoverMouse(By by){
       Actions action = new Actions(this.driver);
       action.moveToElement(this.getElement(by)).perform();
   }
   //scrolling down
   public void scrollDown(){
       JavascriptExecutor js = (JavascriptExecutor) this.driver;
       js.executeScript("window.scrollBy(0,1000)");
   }
  

   
}
    


 

     
    












