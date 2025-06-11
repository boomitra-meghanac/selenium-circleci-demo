package RacherDashboard.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	
	private WebDriver driver;
	
	private By emailField = By.xpath("//input[@name='email']");
	private By passwordField = By.xpath("//input[@name='password']");
	private By visibilityIcon = By.xpath("//*[contains(@data-testid,'VisibilityOffIcon')]");
	private By understandBtn = By.xpath("//button[contains(text(),'I understand')]");
	private By loginBtn = By.xpath("//button[contains(text(),'Login')]");
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void login(String email, String password) throws InterruptedException
	{
		driver.findElement(emailField).sendKeys(email);
		driver.findElement(passwordField).sendKeys(password);
		Thread.sleep(3000);
		driver.findElement(visibilityIcon).click();
		driver.findElement(understandBtn).click();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        
        driver.findElement(loginBtn).click();
        Thread.sleep(3000);
	}
	
	
	

}
