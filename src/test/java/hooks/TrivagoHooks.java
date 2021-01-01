package hooks;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import tests.ReadProperties;

public class TrivagoHooks {

	public static WebDriver driver;
	ReadProperties read;
	
	@Before
	public void before() throws IOException 
	{
		System.out.println("Before the browser");
		read = new ReadProperties();
		if (read.getPropValues("browserName").equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver =  new ChromeDriver();
		}

		else if (read.getPropValues("browserName").equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver =  new FirefoxDriver();
		}

		else if (read.getPropValues("browserName").equalsIgnoreCase("IE")) {
			WebDriverManager.iedriver().setup();		
			driver =  new InternetExplorerDriver();
		}

		else if (read.getPropValues("browserName").equalsIgnoreCase("chrome-headless")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			options.addArguments("--window-size=1920,1080");
			driver = new ChromeDriver(options);

		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(read.getPropValues("URL"));

	}

	@After (order = 0)
	public void quitBrowser() 
	{
		driver.quit();
	}
	
	@After (order = 1)
	public void tearDown(Scenario scenario) 
	{
		if (scenario.isFailed()) {
			//take a screenshot
			String screenShotName = scenario.getName().replaceAll(" ", "_");
			byte [] sourcePath = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(sourcePath, "image/png", screenShotName);
		}
	}

}
