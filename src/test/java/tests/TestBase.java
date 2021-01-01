package tests;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.Helper;

public class TestBase {


	public static WebDriver driver;

	@BeforeMethod
	@Parameters({"browser"})
	public void startdriver( @Optional("chrome") String BrowserName) throws InterruptedException
	{
		if (BrowserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver =  new ChromeDriver();
		}

		else if (BrowserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver =  new FirefoxDriver();
		}

		else if (BrowserName.equalsIgnoreCase("IE")) {
			WebDriverManager.iedriver().setup();		
			driver =  new InternetExplorerDriver();
		}

		else if (BrowserName.equalsIgnoreCase("chrome-headless")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			options.addArguments("--window-size=1920,1080");
			driver = new ChromeDriver(options);

		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.douglas.de/mydouglas/login");
		
	}

	@AfterMethod
	public void stopdriver() {
		driver.quit();
	}


	//taking screenshot when Test case fails and add it to the screenshot folder
	@AfterMethod
	public void ScreenhotFailure(ITestResult result)
	{
		if (result.getStatus()== ITestResult.FAILURE) {
			System.out.println("Failed!");
			System.out.println("Taking Screenshot....");
			Helper.captureScreenShot(driver, result.getName());
		}

	}
}

