 package utilities;



import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Helper {

	
	public static void scroll_to_element(WebElement element, WebDriver driver) {
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}
}
