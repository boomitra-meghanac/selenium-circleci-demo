package RancherDashboard.Project;

import RacherDashboard.Project.BaseTest;

import java.time.Duration;

import RacherDashboard.Pages.HomePage;
import RacherDashboard.Pages.LoginPage;
import RacherDashboard.Pages.ManagementPage;
import RacherDashboard.Pages.QuestionnairePage;
import RacherDashboard.Pages.RanchesPage;

public class OverallFlow extends BaseTest{
	
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
//		homePage.openAllTabs();
		homePage.openRanch();
		RanchesPage ranchPage = new RanchesPage(driver);
		ranchPage.addRanch();
//		ranchPage.editRanch();
//		ranchPage.deleteRanch();
		ManagementPage managementPage = new ManagementPage(driver);
		managementPage.selectViewDetailsOfRanch();
		
//		QuestionnairePage questionnairePage = new QuestionnairePage(driver);
//		questionnairePage.enterQuestionnaireDetails();
//		homePage.logout();
		
		
		
		
//		tearDown();
		
		
	}
	
	

}
