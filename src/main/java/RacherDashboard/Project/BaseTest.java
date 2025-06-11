package RacherDashboard.Project;

import java.io.File;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	protected static WebDriver driver;
	protected static ExtentReports extent;
	
	public static void setup()
	{
		 try {
		        WebDriverManager.chromedriver().setup();
//		        driver = new ChromeDriver();  // might be failing here

		        ChromeOptions options = ChromeOptionsLoader.loadOptionsFromJson("src/test/resources/chromeOptionsConfig.json");
		        driver = new ChromeDriver(options);

				
				
		        driver.manage().window().maximize();
		        System.out.println("ChromeDriver launched successfully.");
		    } catch (Exception e) {
		        System.err.println("ChromeDriver failed to launch:");
		        e.printStackTrace();
		    }

//		System.setProperty("webdriver.chrome.driver", "/Users/mchandrakant/Downloads/chromedriver-mac-arm64 2/chromedriver");
//		driver=new ChromeDriver();
		driver.manage().window().maximize();
		
		

		
		// Create report directory
        String reportDir = System.getProperty("user.dir") + "/test-output";
        new File(reportDir).mkdirs();

        // Set up ExtentReports
        String reportPath = reportDir + "/SparkReport.html";
        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        extent = new ExtentReports();
        extent.attachReporter(spark);

        extent.setSystemInfo("Senior QA Engineer", "Meghana Chandrakant");
        extent.setSystemInfo("Environment", "Prod");

        // Log a dummy test for validation
        ExtentTest test = extent.createTest("Test Setup Validation");
        test.pass("Test setup and Extent Report initialized successfully.");

        System.out.println("Report will be generated at: " + reportPath);

	}
	
	
	public static void tearDown()
	{
		if(driver!=null)
		{
			driver.quit();
		}
		
		if(extent!=null)
		{
			extent.flush();
		}
		
		
	}
	
	
	
	public static WebDriver getDriver()
	{
		return driver;
	}

}
