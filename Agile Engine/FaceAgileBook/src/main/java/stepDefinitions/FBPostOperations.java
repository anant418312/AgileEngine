package stepDefinitions;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import page.Facebook.PageFacebook;


public class FBPostOperations {

	WebDriver driver;
//	PageFacebook faceAgile = new PageFacebook(driver);
	PageFacebook faceAgile = PageFactory.initElements(driver, PageFacebook.class);
	
	@Given("^\"([^\"]*)\" is opened to launch the \"([^\"]*)\"$")
	public void is_opened_to_launch_the(String BrowserType, String url) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
//		List<Map<String, String>> list = dataFromFeature.asMaps(String.class, String.class);
//		for (Map<String, String> data : dataFromFeature.asMaps(String.class, String.class)) {
			faceAgile.browserLauncher(BrowserType);
			faceAgile.urlLauncher(url);
	}

	@When("^The user logs in \"([^\"]*)\" and \"([^\"]*)\"$")
	public void the_user_logs_in(String username, String password) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		faceAgile.login(username, password);
	}

	@When("^navigates to the test page$")
	public void navigates_to_the_test_page() throws Throwable {

	}

	@When("^create a post with \"([^\"]*)\" and make sure it got posted$")
	public void create_a_post(String textToWrite) throws Throwable {
		faceAgile.createPost(textToWrite);
	}
	
	@When("^edit the post with \"([^\"]*)\" and make sure it got added to the \"([^\"]*)\"$")
	public void edit_the_post_with_and_make_sure_it_got_added_to_the(String PostUpdate, String Post) throws Throwable {
		faceAgile.editPost(Post, PostUpdate);
	}

	@Then("^delete the post$")
	public void delete_the_post() throws Throwable {
		faceAgile.deletePost();
	}
}
