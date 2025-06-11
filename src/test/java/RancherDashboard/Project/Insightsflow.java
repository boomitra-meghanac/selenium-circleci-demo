package RancherDashboard.Project;

import RacherDashboard.Project.BaseTest;


import java.time.Duration;

import RacherDashboard.Pages.HomePage;
import RacherDashboard.Pages.InsightsPage;
import RacherDashboard.Pages.LoginPage;
import RacherDashboard.Pages.RanchesPage;

public class Insightsflow extends BaseTest{
	
	public static void main(String[] args) throws InterruptedException
	{
		setup();
		driver.get("https://rancher.boomitra.com");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login("meghanachandrakant.26@gmail.com", "Meghsch@nd11");
		
		
		HomePage homePage = new HomePage(driver);
		homePage.performTour();
		homePage.handleBankDetailsPopup();
		
		InsightsPage insightsPage = new InsightsPage(driver);
		insightsPage.selectRanchesInInsights();
		insightsPage.selectPlantHealthNDVI();
		insightsPage.selectSoilMoisture();
		
		
		
//		tearDown();
		
		
	}
	
	

}
