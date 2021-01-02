package stepDefinitions;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import hooks.TrivagoHooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ContactFormPage;
import pages.SearchResultsPage;
import pages.TrivagoMagazinePage;
import tests.ReadProperties;


public class Steps  {

	TrivagoMagazinePage homeObj = new TrivagoMagazinePage(TrivagoHooks.driver);
	SearchResultsPage resultObj = new SearchResultsPage(TrivagoHooks.driver);
	ContactFormPage contObj = new ContactFormPage(TrivagoHooks.driver);
	ReadProperties read ;

	@Given("I am on the trivago magazine home page")
	public void i_am_on_the_trivago_magazine_home_page() throws IOException {
		read = new ReadProperties();
		Assert.assertTrue(homeObj.PageTitle().contains(read.getPropValues("trivagoMagazineHomeTitle")));
	}

	@When("I click on the search icon")
	public void i_click_on_the_search_icon() throws Exception {
		homeObj.clickSearch();
	}

	@When("I search by {string} country")
	public void i_search_by_country(String name) throws Exception {

		homeObj.Searchinsearchbar(name);
	}

	@Then("I get {string} in the search results")
	public void i_get_in_the_search_results(String name) {
		List <WebElement> results = resultObj.searchResults();
		for (WebElement result : results) {
			String title_name =result.getText();
			Assert.assertTrue(title_name.equalsIgnoreCase(name));
		}
	}



	@When("I click on contact link in the footer")
	public void i_click_on_contact_link_in_the_footer() throws Exception {
		homeObj.clickContactLink();
	}

	@When("I fill the cobtact form")
	public void i_fill_the_cobtact_form() throws Exception {
		String current_tab = TrivagoHooks.driver.getWindowHandle();
		Set<String> windows = TrivagoHooks.driver.getWindowHandles();
		Iterator<String> iterator=windows.iterator();

		while (iterator.hasNext()) {
			String newtab = iterator.next();
			if (!current_tab.equalsIgnoreCase(newtab)) {

				TrivagoHooks.driver.switchTo().window(newtab);
				contObj.fill_contact_form();

			}
		}
	}

	@Then("I get a successfull message")
	public void i_get_a_successfull_message() throws Exception {
		Assert.assertTrue(contObj.contact_successMessage().getText().contains("Message Sent Successfully"));
	}


	@When("I add my email in the subscibe news letter")
	public void i_add_my_email_in_the_subscibe_news_letter() throws Exception {
		homeObj.subscribeToNewsLetter();
	}

	@Then("I get a confirmation message")
	public void i_get_a_confirmation_message() throws Exception {
		//Thread.sleep(6000);
		Assert.assertTrue(homeObj.Subscrition_confirmation().getText().contains("You are now checked-in"));
	}


	@When("I click on the top left menu")
	public void i_click_on_the_top_left_menu() throws Exception {
		homeObj.click_menu();
	}

	@When("I click on the destinations link")
	public void i_click_on_the_destinations_link() throws Exception {
		homeObj.click_destination_link();
	}

	@When("I choose a certain destination")
	public void i_choose_a_certain_destination() throws Exception {
		read = new ReadProperties();
		homeObj.choose_destination(read.getPropValues("destination"));
	}

	@Then("The destinnation page opens successfully")
	public void the_destinnation_page_opens_successfully() throws IOException {
		read = new ReadProperties();
		TrivagoHooks.driver.getCurrentUrl().contains(read.getPropValues("destination"));
	}

	@Then("check home links are working fine")
	public void check_home_links_are_working_fine() throws Exception {

		List<WebElement> homelinks =  homeObj.Gethomelinks();
		homelinks.addAll(TrivagoHooks.driver.findElements(By.tagName("img")));
		System.out.println("number of links is " + homelinks.size());
		for (WebElement link : homelinks) {
			try {
				String href = link.getAttribute("href");
				if (href !=null) {
					System.out.println(href);
					homeObj.check_connection(href);
				}

			} catch (java.net.MalformedURLException e) {
				System.out.println(link+ "That's not a link");
			}

			catch (java.lang.NullPointerException e) {
				System.out.println(link+ "That's not a link");
			}


		}
	}
	
	@When("Click on Read more button")
	public void click_on_read_more_button() throws Exception {
		homeObj.click_read_more();
	}

	@Then("some events are triggered such as {string}, {string}, {string}")
	public void some_events_are_triggered_such_as(String TrustLoaded, String OptanonLoaded, String TrustGroupsLoaded) {
		
		Assert.assertEquals(homeObj.getTrustLoadedEvent(), TrustLoaded);
		Assert.assertEquals(homeObj.getoptanonLoadedEvent(), OptanonLoaded);
		Assert.assertEquals(homeObj.getTrustGroupsLoadedEvent(), TrustGroupsLoaded);	
	}

	@Then("every event includes some parameters")
	public void every_event_includes_some_parameters() {
		
		Assert.assertTrue(homeObj.getOnetrustloaded_params().containsAll(homeObj.Expected_params()));
		Assert.assertTrue(homeObj.getOptanonloaded_params().containsAll(homeObj.Expected_params()));
		Assert.assertTrue(homeObj.getTrustGroupsupdated_params().containsAll(homeObj.Expected_params()));

	}

	@Then("{string} event is triggered")
	public void event_is_triggered(String contentloadedEvent) {
		Assert.assertEquals(homeObj.getContentLoadedEvent(), contentloadedEvent);  
	}

	@Then("hotelIds event is not empty")
	public void hotel_ids_event_is_not_empty() {
		Assert.assertNotNull(homeObj.gethotelIds_params());
	}

	@Then("target-properties has same path in the URL")
	public void target_properties_has_same_path_in_the_url() {
	   String url = TrivagoHooks.driver.getCurrentUrl();
	   Assert.assertTrue(url.contains(homeObj.getTargetProperties()));
	}
}
