package test;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import pages.AddUser;
import pages.Home;
import pages.LoginUser;

public class DemoTest {
	
	private static WebDriver driver;
	private static ExtentReports extent;
	private static ExtentTest test;
	
	
    private static Logger LOGGER = Logger.getGlobal();
	
	
	@BeforeClass
	public static void setup() {
		
		extent = new ExtentReports("src/test/resources/reports/report1.html", true);
		test = extent.startTest("DemoSite1");;
		
		System.setProperty("webdriver.chrome.driver", 
				"src/test/resources/resources/drivers/chromedriver.exe");
		
		ChromeOptions cOptions = new ChromeOptions();
		cOptions.setHeadless(false);
		
		driver = new ChromeDriver(cOptions);
		driver.manage().window().setSize(new Dimension(1366, 768));
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
	}

	@AfterClass
	public static void tearDown() {
		driver.quit();
		extent.endTest(test);
		extent.flush();
		extent.close();
		
	}
	
	
	@Test
	public void createUser() {
		LOGGER.warning("connecting to demo site");
		Home webpage = PageFactory.initElements(driver, Home.class);
		AddUser user = PageFactory.initElements(driver, AddUser.class);
		LoginUser luser = PageFactory.initElements(driver, LoginUser.class);

		driver.get(Home.URL);
		LOGGER.warning("Clicks add a user link");
		webpage.navUserPage();
		LOGGER.warning("Enter username and info and click save");
		user.createUser("root", "root");
		LOGGER.warning("click login link");
		webpage.navloginPage();
		LOGGER.warning("enter details");
		luser.loginUser("root", "root");
		
		LOGGER.info("Finished");
        boolean expected;
        expected = true;
        Boolean result = driver.getPageSource().contains("Successful");
        System.out.println(result);
        
        assertEquals(expected, result);
		
		
		
		
	}

}
