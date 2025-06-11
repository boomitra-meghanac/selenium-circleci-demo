package RacherDashboard.Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class QuestionnairePage {

private WebDriver driver;
	
	public QuestionnairePage(WebDriver driver)
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
	
	private void inputElement(By locator, String text) throws InterruptedException
	{
		 WebElement input = findElement(locator);
		    input.click(); // Focus on the input field
		    
		    JavascriptExecutor js = (JavascriptExecutor) driver;
		    js.executeScript("arguments[0].value='';", input);

		    Thread.sleep(300);

		    // Now send new text
		    input.sendKeys(text);
		
	}
	
	public void enterQuestionnaireDetails() throws InterruptedException
	{
		Thread.sleep(3000);
		clickElement(By.xpath("//button[contains(normalize-space(text()),'Questionnaire')]"));
		inputElement(By.xpath("//input[@name='rainfall']"),"200");
		inputElement(By.xpath("//input[@name='minTemp']"),"30");
		inputElement(By.xpath("//input[@name='maxTemp']"),"40");
		inputElement(By.xpath("//input[@name='sustainablePractices']"),"5");
		
		WebElement scrollable_section = driver.findElement(By.xpath("//div[contains(@class,'MuiGrid-root') and contains(@class,'MuiGrid-container') and contains(@class,'css-1d3bbye')]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;", scrollable_section);

	    
		clickElement(By.xpath("//p[normalize-space()='Other']/ancestor::label//span[contains(@class,'MuiButtonBase-root')]"));

		// Wait for the textarea to become visible
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement descriptionBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name='otherDes']")));

		// Scroll to the textarea if it's in a scrollable section
		js.executeScript("arguments[0].scrollIntoView(true);", descriptionBox);

		// Enter text
		descriptionBox.sendKeys("testing description");

	    Thread.sleep(2000);
	    JavascriptExecutor scrollup = (JavascriptExecutor) driver;
	    scrollup.executeScript("window.scrollBy(0, -window.innerHeight);");
	    
	    clickElement(By.xpath("//button[contains(text(),'Submit')]"));




		
		
	}
	
}
