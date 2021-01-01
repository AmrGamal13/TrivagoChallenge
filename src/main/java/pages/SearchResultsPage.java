package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.testng.Assert;

public class SearchResultsPage {

	WebDriver driver;

	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
	}
	public List<WebElement> searchResults()
	{
		List<WebElement> destination = driver.findElements(By.xpath("//div[@class='search-results']//div[contains(@class,'article-card')]//span[contains(@class,'destination')]"));
		for (WebElement destination_title : destination) {

			String title_name =destination_title.getText();
			//Assert.assertTrue(title_name.equalsIgnoreCase("canada"));

		}
		return destination;
	}
}


