package RacherDashboard.Pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ManagementPage {

private WebDriver driver;
	
	public ManagementPage(WebDriver driver)
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

		    // Now send new text
		    input.sendKeys(text);
		
	}
	
	public void selectViewDetailsOfRanch() throws InterruptedException
	{
		clickElement(By.xpath("//div[contains(normalize-space(text()), 'Management')]"));
		clickElement(By.xpath("//button[contains(normalize-space(text()),'View details')]"));
		clickElement(By.xpath("//h6[contains(normalize-space(),'Total no of Paddocks')]/ancestor::div[contains(@class, 'css-wzqf2w')]//button[normalize-space()='Add']"));
		By paddockDrawButton = By.xpath("//div[@class='button-container  pos-right' and @title='Draw Polygons']//h3[contains(text(),'Draw')]");
		clickElement(paddockDrawButton);

		WebElement map = driver.findElement(By.cssSelector("div.leaflet-container"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", map);
		Thread.sleep(1000);

	    
		// Wait for map to render completely
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(map));
		((JavascriptExecutor) driver).executeScript(
		    "arguments[0].scrollIntoView({block: 'center'});", map);
		Thread.sleep(1000);

		// Get map size
		Dimension mapSize = map.getSize();
		int width = mapSize.getWidth();
		int height = mapSize.getHeight();
		System.out.println("Map size: " + width + " x " + height);

		// Calculate safe click offsets (25%-75% zone)
		int safeX1 = (int) (width * 0.25);
		int safeY1 = (int) (height * 0.25);
		int safeX2 = (int) (width * 0.75);
		int safeY2 = (int) (height * 0.75);

		// Perform click-based polygon draw
		Actions actions = new Actions(driver);
		actions.moveToElement(map, safeX1, safeY1).click()
		       .pause(300)
		       .moveToElement(map, safeX2, safeY1).click()
		       .pause(300)
		       .moveToElement(map, safeX2, safeY2).click()
		       .pause(300)
		       .moveToElement(map, safeX1, safeY2).click()
		       .pause(300)
		       .moveToElement(map, safeX1, safeY1).doubleClick()
		       .build()
		       .perform();





	    Thread.sleep(2000);
	}
	
}
