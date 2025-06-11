package RancherDashboard.Project;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class FirstExample {
    protected static ExtentReports extent;
    private static WebDriver driver;
    
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/mchandrakant/Downloads/chromedriver-mac-arm64 2/chromedriver");
        
        // Initialize ExtentReports
        String reportPath = System.getProperty("user.dir") + "/test-output/SparkReport.html";
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("T2ester", "Your Name");
        extent.setSystemInfo("Environment", "QA");
        
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rancher.boomitra.com");
        
        login("manasi@boomitra.com", "Boomitra@123");
        performTour();
        openAllTabs();
        logout();
        
        driver.quit();
    }
    
    private static WebElement findElement(By locator) {
        return driver.findElement(locator);
    }
    
    private static void clickElement(By locator) throws InterruptedException {
        Thread.sleep(3000); // Adding sleep to wait for elements dynamically
        findElement(locator).click();
    }
    
    private static void sendKeysElement(By locator, String text) {
        findElement(locator).sendKeys(text);
    }
    
    private static void login(String email, String password) throws InterruptedException {
        sendKeysElement(By.xpath("//input[@name='email']"), email);
        sendKeysElement(By.xpath("//input[@name='password']"), password);
        clickElement(By.xpath("//*[contains(@data-testid,'VisibilityOffIcon')]"));
        clickElement(By.xpath("//button[contains(text(),'I understand')]"));
        
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        clickElement(By.xpath("//button[contains(text(),'Login')]"));
        Thread.sleep(8000);
    }
    
    private static void performTour() throws InterruptedException {
        clickElement(By.xpath("//button[@data-tour-elem='right-arrow']"));
        clickElement(By.xpath("//button[@data-tour-elem='right-arrow']"));
        clickElement(By.xpath("//button[@data-tour-elem='right-arrow']"));
        clickElement(By.xpath("//button[@aria-label='Close']"));
    }
    
    
    
    private static void openAllTabs() throws InterruptedException {
        String[] tabs = {"Ranches", "Insights", "Notifications", "Surveys", "Management"};
        for (String tab : tabs) {
            clickElement(By.xpath("//div[contains(text(),'"+ tab + "')]"));
        }
    }
    
    private static void logout() throws InterruptedException {
        clickElement(By.className("svg[@xmlns='http://www.w3.org/2000/svg']"));
    }
}
