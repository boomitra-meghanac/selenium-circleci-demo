package RacherDashboard.Pages;

import java.time.Duration;
import org.openqa.selenium.TimeoutException;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	
	private WebDriver driver;
	
	
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	
	
	private WebElement findElement(By locator) {
        return driver.findElement(locator);
    }
	
	  private void waitForBackdropToDisappear() {
		  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    try {
		        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("MuiBackdrop-root")));
		    } catch (TimeoutException e) {
		        System.out.println("Backdrop still present or not fully invisible after timeout.");
		    }
	    }
	
	private void clickElement(By locator) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	    Thread.sleep(300);

	    // Wait until clickable
	    wait.until(ExpectedConditions.elementToBeClickable(locator));

	    WebElement element = findElement(locator);

	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

	    Thread.sleep(300); // minor buffer to avoid race conditions

	    element.click();
        
    }
	
	 public void performTour() throws InterruptedException {
	        clickElement(By.xpath("//button[@data-tour-elem='right-arrow']"));
	        clickElement(By.xpath("//button[@data-tour-elem='right-arrow']"));
	        clickElement(By.xpath("//button[@data-tour-elem='right-arrow']"));
	        clickElement(By.xpath("//button[@aria-label='Close']"));
	    }
	 
	 public void handleBankDetailsPopup() {
		 
		 
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        waitForBackdropToDisappear();

	        try {
	            WebElement updateButton = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Update details']"))
	            );

	            updateButton.click();
	            System.out.println("Popup found and 'Update' clicked.");

	        } catch (TimeoutException e) {
	            System.out.println("Popup not found. Proceeding with normal flow.");
	        }
	    }
	 
	 
	
	public void openAllTabs() throws InterruptedException {
        String[] tabs = {"Ranches", "Insights", "Notifications", "Surveys", "Management"};
        for (String tab : tabs) {
            clickElement(By.xpath("//div[contains(text(),'"+ tab + "')]"));
            Thread.sleep(2000);
        }
    }
	
	public void openRanch() throws InterruptedException
	{
		String tab="Ranches";
		clickElement(By.xpath("//div[contains(text(),'Ranches')]"));
	}
	
	public void logout() throws InterruptedException {
		clickElement(By.cssSelector(".iconify.iconify--entypo"));
		clickElement(By.xpath("//li[contains(text(),'Logout')]"));
		clickElement(By.xpath("//button[contains(text(),'Logout')]"));
	}
	
	
	
	

}
