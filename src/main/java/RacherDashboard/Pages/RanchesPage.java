package RacherDashboard.Pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RanchesPage {
	
	private WebDriver driver;
	
	public RanchesPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	private WebElement findElement(By locator)
	{
		return driver.findElement(locator);
	}
	
	private void clickElement(By locator)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		WebElement element = findElement(locator);
		element.click();
	}
	
	private void inputElement(By locator, String text)
	{
		 WebElement input = findElement(locator);
		    input.click(); // Focus on the input field

		    // Clear the text using JavaScriptExecutor
		    JavascriptExecutor js = (JavascriptExecutor) driver;
		    js.executeScript("arguments[0].value='';", input);

		    // Small pause to allow frontend JS to detect change
		    try {
		        Thread.sleep(300);
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    }

		    // Now send new text
		    input.sendKeys(text);
		
	}
	
	

public void verifyToastMessage(WebDriver driver, String text, String expectedText) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    By toastLocator = By.xpath("//*[@id='notistack-snackbar']");
    WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(toastLocator));
    String actualText = toast.getText();
    if (actualText.contains(expectedText)) {
        System.out.println("✅ Toast message of "+text+" verified: " + actualText);
    } else {
        System.out.println("❌ Unexpected toast message of "+text+ ":" + actualText);
    }
}

public void addRanch() throws InterruptedException
{
	{
		
	    clickElement(By.xpath("//button[contains(text(),'Add New Ranch')]"));

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    By drawButton = By.xpath("//div[@class='button-container  pos-right' and @title='Draw Polygons']//h3[contains(text(),'Draw')]");
	    wait.until(ExpectedConditions.visibilityOfElementLocated(drawButton));
	    wait.until(ExpectedConditions.elementToBeClickable(drawButton));
	    clickElement(drawButton);

	    WebElement map = driver.findElement(By.cssSelector("div.leaflet-container"));
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", map);
	    Thread.sleep(1000);  // Ensure the map is fully interactive

	    // Get map size to calculate safe bounds
	    Dimension mapSize = map.getSize();
	    int width = mapSize.getWidth();
	    int height = mapSize.getHeight();

	    System.out.println("Map size: " + width + " x " + height);

	    // Define safe points within the map's dimensions
	    int startX = width / 4;
	    int startY = height / 4;

	    Actions actions = new Actions(driver);

	    actions.moveToElement(map, startX, startY)
	           .click()
	           .moveByOffset(100, 0).click()
	           .moveByOffset(0, 100).click()
	           .moveByOffset(-100, 0).click()
	           .moveByOffset(0, -100).click()
	           .doubleClick() // Finish the polygon if needed
	           .build()
	           .perform();

	    Thread.sleep(2000); 
	}
	
	inputElement(By.xpath("//input[@name='name']"), "Ranch1234"); 
	clickElement(By.xpath("//div[@id='Ranch Ownership']"));
	clickElement(By.xpath("//li[@data-value='PRIVATE']"));
	inputElement(By.xpath("//input[@name='address']"), "Vidyamanyanagar"); 
	inputElement(By.xpath("//input[@name='city']"), "Bengaluru"); 
	inputElement(By.xpath("//input[@name='postalCode']"), "560091"); 
	WebElement scrollable_section = driver.findElement(By.xpath("//div[contains(@class,'MuiStack-root') and contains(@class,'css-')]"));
    JavascriptExecutor js = (JavascriptExecutor) driver;	    
    Thread.sleep(3000);   
    clickElement(By.xpath("//button[@type='submit']"));
    verifyToastMessage(driver,"Add Ranch","Successfully saved");	
    Thread.sleep(3000);
}
	
	public void editRanch() throws InterruptedException
	{
		clickElement(By.xpath("//button[contains(@class, 'MuiIconButton-root') and contains(@class, 'css-n35sr9')]"));
	    By editOption = By.xpath("//li[contains(text(),'Edit')]");
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOfElementLocated(editOption));
	    wait.until(ExpectedConditions.elementToBeClickable(editOption));
	    WebElement element = findElement(editOption);
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	    // No need to manually clear anything here — just call inputElement
	    inputElement(By.xpath("//input[@name='name']"), "Ranch123");    
	    WebElement scrollable_section = driver.findElement(By.xpath("//div[contains(@class,'MuiStack-root') and contains(@class,'css-')]"));
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].scrollIntoView(true);", scrollable_section);
	    Thread.sleep(3000);       
        clickElement(By.xpath("//button[contains(text(),'Save')]"));
        verifyToastMessage(driver, "Edit Ranch", "Successfully saved");  
        Thread.sleep(3000);
        
	}
	
	public void deleteRanch() throws InterruptedException
	{
		// Click on the 3-dot menu
	    clickElement(By.xpath("//button[contains(@class, 'MuiIconButton-root') and contains(@class, 'css-n35sr9')]"));

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[contains(@class,'MuiList-root')]")));

	    // Define Delete option XPath
	    By deleteOption = By.xpath("//li[contains(., 'Delete')]");

	    // Wait for visibility
	    WebElement deleteBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(deleteOption));
	    wait.until(ExpectedConditions.elementToBeClickable(deleteOption));

	    // Use JavaScript to click to avoid ElementClickInterceptedException
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", deleteBtn);
	    Thread.sleep(300); // wait for UI animations
	    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteBtn);

	    Thread.sleep(3000);
	    clickElement(By.xpath("//button[contains(text(),'Yes')]"));
	    Thread.sleep(5000);
	    
	    verifyToastMessage(driver,"Delete Ranch", "Successfully Deleted"); 
	}
}
