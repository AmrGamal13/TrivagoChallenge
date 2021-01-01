package pages;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.ExtractorData;


public class TrivagoMagazinePage {

	WebDriver driver;
	ExtractorData ex;
	WebDriverWait wait;
	JavascriptExecutor js ;


	public TrivagoMagazinePage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickContactLink() throws Exception {
		ex = new ExtractorData();
		WebElement contact_link= driver.findElement(By.xpath(ex.Locaters("TrivagoMagazineHome", 1)));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", contact_link);
		contact_link.click();
	}

	public void clickSearch() throws Exception 
	{    ex = new ExtractorData();
	WebElement searchIcon = driver.findElement(By.xpath(ex.Locaters("TrivagoMagazineHome", 2)));
	searchIcon.click();
	//Thread.sleep(5000);

	}
	public void Searchinsearchbar(String country) throws Exception {
		ex = new ExtractorData();
		WebElement search = driver.findElement(By.cssSelector(ex.Locaters("TrivagoMagazineHome",3)));
		search.sendKeys(country);
		search.sendKeys(Keys.ENTER);
	}


	public String PageTitle() {
		String name =driver.getTitle();
		return name;
	}


	public void subscribeToNewsLetter() throws Exception {
		ex = new ExtractorData();
		WebElement email = driver.findElement(By.xpath(ex.Locaters("TrivagoMagazineHome", 4)));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", email);
		String Email =RandomStringUtils.randomAlphabetic(10);
		System.out.println("Email is ....>" + Email);

		email.sendKeys(Email+"@email.com");

		WebElement inspire_Bttn = driver.findElement(By.xpath(ex.Locaters("TrivagoMagazineHome", 5)));
		inspire_Bttn.click();
	}

	public WebElement Subscrition_confirmation() throws Exception {
		ex = new ExtractorData();
		wait = new WebDriverWait(driver, 20);
		WebElement message;
		message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(ex.Locaters("TrivagoMagazineHome", 6))));
		return message;
	}

	public void click_menu() throws Exception {
		ex = new ExtractorData();
		WebElement menu = driver.findElement(By.xpath(ex.Locaters("TrivagoMagazineHome", 7)));
		menu.click();

	}

	public void click_destination_link() throws Exception {
		ex = new ExtractorData();
		WebElement menu = driver.findElement(By.xpath(ex.Locaters("TrivagoMagazineHome", 8)));
		menu.click();

	}

	public void choose_destination(String desired_destination) throws Exception {
		ex = new ExtractorData();
		List<WebElement> destinations = driver.findElements(By.xpath(ex.Locaters("TrivagoMagazineHome", 9)));
		for (WebElement dest : destinations) {
			String link =dest.getAttribute("href");
			if (link.contains(desired_destination)) {
				dest.click();
				break;
			}

		}
	}

	public List<WebElement> Gethomelinks () throws Exception {
		ex = new ExtractorData();
		List<WebElement> destinations = driver.findElements(By.tagName(ex.Locaters("TrivagoMagazineHome", 10)));
		return destinations;
	}

	public void check_connection(String link) throws IOException {
		URL url = new URL (link);

		HttpURLConnection urlconnect = (HttpURLConnection)url.openConnection();

		urlconnect.setConnectTimeout(10000);
		urlconnect.setReadTimeout(5000);

		urlconnect.connect();
		//System.out.println("Response Message of " +link+ "is ....      " +urlconnect.getResponseMessage());

		try {
			if (urlconnect.getResponseCode()==200) {

				System.out.println("The link " +link+ " working and response code is  "+urlconnect.getResponseCode()+ " " +urlconnect.getResponseMessage());

			} else { 
				System.out.println("The link " +link+ " not working and response code is  " +urlconnect.getResponseCode()+ " " +urlconnect.getResponseMessage()); 

			}
		} 

		catch (java.net.SocketTimeoutException e) {
			System.out.println("Request sent with no response: " +link );
		}
		urlconnect.disconnect();
	}


	public void click_read_more() throws Exception {
		ex = new ExtractorData();
		WebElement read_more_button = driver.findElement(By.className(ex.Locaters("TrivagoMagazineHome", 11)));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", read_more_button);
		read_more_button.click();

	}
	
	public List<String> getOnetrustloaded_params()
	{
		js= ((JavascriptExecutor) driver);
		Object parameters_trustLoaded = js.executeScript("return window.dataLayer[0].OnetrustActiveGroups");
		String trustLoaded_name = parameters_trustLoaded.toString();
		String arr[] = trustLoaded_name.split(",");
		List<String> parameters_listtrust = new ArrayList<String>();
		parameters_listtrust = Arrays.asList(arr);
		return parameters_listtrust;
	}
	
	public List<String> getOptanonloaded_params()
	{
		js= ((JavascriptExecutor) driver);
		Object parameters_optanonLoaded = js.executeScript("return window.dataLayer[1].OptanonActiveGroups");
		String optanonLoaded_name = parameters_optanonLoaded.toString();
		String arr1[] = optanonLoaded_name.split(",");
		List<String> parameters_listoptanon = new ArrayList<String>();
		parameters_listoptanon = Arrays.asList(arr1);
		return parameters_listoptanon;
	}
	
	public List<String> getTrustGroupsupdated_params()
	{
		js= ((JavascriptExecutor) driver);
		Object parameters_Groupsupdated = js.executeScript("return window.dataLayer[2].OnetrustActiveGroups");
		String groupsUpdated_name = parameters_Groupsupdated.toString();
		String arr1[] = groupsUpdated_name.split(",");
		List<String> parameters_listgroup = new ArrayList<String>();
		parameters_listgroup = Arrays.asList(arr1);
		return parameters_listgroup;
	}
	
	public List<String> Expected_params()
	{
		List<String> Expected_parameters_list = new ArrayList<String>();
		Expected_parameters_list.add("C0001");
		Expected_parameters_list.add("C0002");
		Expected_parameters_list.add("C0003");
		Expected_parameters_list.add("C0004");
		return Expected_parameters_list;
	}
	
	public String getContentLoadedEvent() {
		
		Object eventName = js.executeScript("return window.dataLayer[12].event");
		String contentLoadeddEventName = eventName.toString();
		return contentLoadeddEventName;
	}
	
	
	public  Object gethotelIds_params()
	{
		Object hotelIds_Value = js.executeScript("return window.dataLayer[12].hotelIds");
		return hotelIds_Value;
	}
	

	public  String getTargetProperties()
	{
		String code = "target-properties";
		Object targetproperties_Value = js.executeScript("return window.dataLayer[12]['target-properties']");
		String targetproperties = targetproperties_Value.toString();
		return targetproperties;
	}
}
