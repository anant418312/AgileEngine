package page.Facebook;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.ScreenshotException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.masterthought.cucumber.util.Util;
import utilities.Utilities;

public class PageFacebook {
	public WebDriver driver;
 	public WebDriverWait waiter;
 	JavascriptExecutor js; 
 	Utilities util;
 	
 	public PageFacebook(WebDriver driver) {
		this.driver = driver;
//		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "email")
	WebElement Edt_Username;

	@FindBy(id = "pass")
	WebElement Edt_password;

	@FindBy(xpath = "//input[@value = 'Log In']")
	WebElement Btn_LogIn;

//	@FindBy(xpath = "//div[@class = '_1mf _1mj']")
	@FindBy(xpath = "//*[contains(text(), 's on your mind')]")
	WebElement Edt_Post;

	@FindBy(xpath = "//a[text()='Create']")
	WebElement Lnk_Create; //On HomePage
	
	@FindBy(xpath = "//button[@data-testid = 'react-composer-post-button']")
	WebElement Btn_Share; 

	@FindBy(xpath = "//span[text() = 'Create Post']")
	WebElement Elm_CreatePostTitle;
	
//	@FindBy(xpath = "//div[@class = '_1mf _1mj']")
	@FindBy(xpath = "//*[@data-text = 'true']")
	WebElement Edt_CreatePopUp;
	
	@FindBy(xpath = "//a[@aria-label = 'Story options']")
	List<WebElement> Btn_StoryOptions;
	
	@FindBy(xpath = "//span[text() = 'Edit Post']")
	WebElement Btn_EditPost;
	
	@FindBy(xpath = "//span[text() = 'Delete']")
	WebElement Btn_DeletePost;
	
	@FindBy(xpath = "//button[@type = 'submit' and text() = 'Delete']")
	WebElement Btn_DeleteButtonOnDeletePostPopUp;
	/**
	 * 
	 * @param browser
	 */
	public void browserLauncher(String browser) {
		if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "src\\ThirdParty\\geckodriver.exe");
			FirefoxOptions options = new FirefoxOptions();
			options.setProfile(new FirefoxProfile());
			options.addPreference("dom.webnotifications.enabled", false);
			driver = new FirefoxDriver(options);
			PageFactory.initElements(this.driver, this);
			this.util = new Utilities(driver);
		}
	}

	public void urlLauncher(String url) {
		try {
			driver.get(url);
			System.out.println(driver.getTitle());
			System.out.println("Page was opened!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param username
	 * @param password
	 */
	public void login(String username, String password) {
		waiter = new WebDriverWait(driver, 20);
		Edt_Username.sendKeys(username);
		Edt_password.sendKeys(password);
		Btn_LogIn.click();
		try {
			WebElement element = waiter.until(ExpectedConditions.visibilityOf(Lnk_Create));
			if(element.isDisplayed()) {
				System.out.println("User has landed on the Home page");
				util.screenshot("Login");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Either the page has timed out or the element was not found");
			e.printStackTrace();
		}
	}
	/**
	 * Using Actions class to write the post
	 * @param textToWrite
	 */
	public void createPost(String textToWrite) {
		waiter = new WebDriverWait(driver, 30);
		Edt_Post.click();
		waiter.until(ExpectedConditions.visibilityOf(Elm_CreatePostTitle));
		waiter.until(ExpectedConditions.visibilityOf(Edt_CreatePopUp));
		waiter.until(ExpectedConditions.elementToBeClickable(Edt_CreatePopUp));
		Actions performAct = new Actions(driver);
		performAct.sendKeys(Edt_CreatePopUp, textToWrite).build().perform();
		Btn_Share.click();
		try {
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			if(driver.findElement(By.xpath("//p[text() = '"+textToWrite+"']")).isDisplayed()) {
				System.out.println("Update was posted");
				util.screenshot("New Post");
			}
		} catch (Exception e) {
			System.out.println("The update was not posted. "+e);
		}
	}
	
	public void editPost(String oldText,String textToEdit) {
		try {
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			Btn_StoryOptions.get(0).click();
			Btn_EditPost.click();
			Thread.sleep(2000);
			Actions performAct = new Actions(driver);
			performAct.sendKeys(Edt_CreatePopUp, textToEdit).build().perform();
			Btn_Share.click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			if(driver.findElement(By.xpath("//p[text() = '"+oldText+textToEdit+"']")).isDisplayed()) {
				System.out.println("Post was updated");
				util.screenshot("Post Update");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("The post was not updated. "+e);
		}
	}
	
	public void deletePost() {
		try {
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			Btn_StoryOptions.get(0).click();
			js = ((JavascriptExecutor) driver);
			Thread.sleep(2000);
			js.executeScript("arguments[0].click();", Btn_DeletePost);
			Thread.sleep(2000);
			Btn_DeleteButtonOnDeletePostPopUp.click();
			driver.navigate().refresh();
			System.out.println("Post was deleted");
			util.screenshot("Post Deleted");
			driver.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
