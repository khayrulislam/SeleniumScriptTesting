package Script;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import Utils.Util;

public class SlideShareSiteTest {
	
	WebDriver driver;
	
	public void startTestExecution()  {
		
		driver = initializeWebDriver();
		
		launceSite(Util.SLIDESHARE_URL);
		signInToSlideShare();
		searchSomething();
		logoutFromPinterest();
		stopSite();
	}
		
	private void stopSite() {
		driver.quit();
	}

	private void logoutFromPinterest() {
		
		clickOnButton(getWebElementByXPath(Util.SLIDESHARE_PROFILE_ICON_XPATH));
		clickOnButton(getWebElementByXPath(Util.SLIDESHARE_LOGOUT_XPATH));
		
	}
	

	private void searchSomething() {
		
		insertTextInTheTextField(getWebElementById(Util.SLIDESHARE_SEARCH_BOX_ID),Util.SLIDESHARE_SEARCH_VALUE);
		getWebElementById(Util.SLIDESHARE_SEARCH_BOX_ID).sendKeys(Keys.ENTER);
		waitForPageLoaded();
	}

	private void signInToSlideShare() {

		clickOnButton(getWebElementById(Util.SLIDESHARE_LOGIN_LINK_ID));
		
		insertTextInTheTextField(getWebElementById(Util.SLIDESHARE_USERNAME_ID),Util.SLIDESHARE_USERNAME_VALUE);
		insertTextInTheTextField(getWebElementById(Util.SLIDESHARE_PASSWORD_ID),Util.SLIDESHARE_PASSWORD_VALUE);
		clickOnButton(getWebElementById(Util.SLIDESHARE_LOGIN_BUTTON_ID));
		waitForPageLoaded();
	}

	public void waitForPageLoaded() {
	/*	 
		ExpectedCondition<Boolean> expectation = new
		        ExpectedCondition<Boolean>() {
		            public Boolean apply(WebDriver driver) {
		                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
	            }
	        };*/
		try {
		    Thread.sleep(3000);
		    //WebDriverWait wait = new WebDriverWait(driver, 60);
		   // wait.until(expectation);
		} catch (Throwable error) {
		    System.out.println("Timeout waiting for Page Load Request to complete.");
		}
	 }

	private WebElement getWebElementByXPath(String path) {
		return driver.findElement(By.xpath(path));
	}
	
	private void clickOnButton(WebElement webElement) {
		
		Actions action = new Actions(driver);
		action.moveToElement(webElement).click().build().perform();
	}
	
	private WebElement getWebElementById(String id) {
		return driver.findElement(By.id(id));
	}

	private void insertTextInTheTextField(WebElement webElement, String text) {
		
		Actions action = new Actions(driver);
		
		action.moveToElement(webElement)
			.click()
			.sendKeys(text)
			.build()
			.perform();
	}
	
	private void launceSite(String siteURL) {
		driver.get(siteURL);
	}
	
	private WebDriver initializeWebDriver() {

		System.setProperty(Util.WEB_DRIVER_PATH, Util.CHROME_DRIVER_EXE_PATH);
		ChromeOptions option = new ChromeOptions();
		option.addArguments("start-maximized");
		WebDriver driver = new ChromeDriver(option);
		
		//WebDriver driver = new ChromeDriver();  //maximize after start
		//driver.manage().window().maximize();
		return driver;
	}
	
}
