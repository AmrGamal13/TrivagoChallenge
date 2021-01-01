package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContactFormPage {
	
	WebDriver driver;
	ExtractorData ex;
	WebDriverWait wait;

	public ContactFormPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void fill_contact_form() throws Exception {
		ex = new ExtractorData();
		WebElement message = driver.findElement(By.xpath(ex.Locaters("ContactFormPage", 1)));
		message.sendKeys("impressed about the articles");
		
		WebElement contact_input = driver.findElement(By.xpath(ex.Locaters("ContactFormPage", 2)));
		contact_input.sendKeys("Adel");
		
		WebElement email = driver.findElement(By.id(ex.Locaters("ContactFormPage", 3)));
		email.sendKeys("Adel@gmail.com");
		
		WebElement checkbox = driver.findElement(By.id(ex.Locaters("ContactFormPage", 4)));
		checkbox.click();
		
		WebElement submit_btn = driver.findElement(By.className(ex.Locaters("ContactFormPage", 5)));
		submit_btn.click();
		
	}
	
	public WebElement contact_successMessage() throws Exception {
		ex = new ExtractorData();
		wait = new WebDriverWait(driver, 20);
		WebElement success_message;
		success_message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(ex.Locaters("ContactFormPage", 6))));
		return success_message;
	}

}
