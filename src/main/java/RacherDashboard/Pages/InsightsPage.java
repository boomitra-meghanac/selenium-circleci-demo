package RacherDashboard.Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InsightsPage {
private WebDriver driver;
	
	public InsightsPage(WebDriver driver)
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
	
	public void selectRanchesInInsights()
	{
		clickElement(By.xpath("//div[contains(text(),'Insights')]"));
		clickElement(By.xpath("(//p[@class='MuiTypography-root MuiTypography-body1 MuiTypography-noWrap css-1y1xm97'])[2]"));
	}
	
	public void selectPlantHealthNDVI() throws InterruptedException
	{
		clickElement(By.xpath("//p[contains(text(),'Plant Health (NDVI)')]"));
		Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='MuiTypography-root MuiTypography-body1 css-k36ljx']")));		
		Thread.sleep(3000);
		clickElement(By.xpath("//button[//svg[@data-testid='KeyboardBackspaceIcon']]"));
	}
	
	public void selectSoilMoisture() throws InterruptedException
	{
		clickElement(By.xpath("//p[contains(text(),'Soil Moisture']"));
		Thread.sleep(3000);
	}

}
