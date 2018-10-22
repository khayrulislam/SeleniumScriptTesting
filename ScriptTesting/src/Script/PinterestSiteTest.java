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

public class PinterestSiteTest {
	
	WebDriver driver;
	
	public void startTestExecution()  {
		
		driver = initializeWebDriver();
		
		launceSite(Util.PINTEREST_URL);
		signInToPinterest();
		searchSomething();
		visitMenuItem();
		logoutFromPinterest();
		
		stopSite();
	}
		
	private void stopSite() {
		driver.quit();
	}

	private void logoutFromPinterest() {
		
		clickMenu(Util.PINTEREST_MORE_OPTION_XPATH);
		List<WebElement> list = driver.findElements(By.xpath( Util.PINTEREST_LIST_ITEM_XPATH ));
		list.get(list.size()-1).click();
	}
	
	private void visitMenuItem() {
		
		clickMenu(Util.PINTEREST_HOME_XPATH);
		clickMenu(Util.PINTEREST_FOLLOWING_XPATH);
		clickMenu(Util.PINTEREST_BSSE_XPATH);
		clickMenu(Util.PINTEREST_NOTIFICATION_XPATH);
		clickMenu(Util.PINTEREST_SETTINGS_XPATH);
		//TODO change user name
		clickMenu(Util.PINTEREST_HOME_XPATH);	
	}

	private void clickMenu(String menuXPath) {
		
		clickOnButton(getWebElementByXPath(menuXPath));
		waitForPageLoaded();
	}

	private void searchSomething() {

		insertTextInTheTextField(getWebElementByXPath(Util.PINTEREST_SEARCH_BOX_XPATH),Util.PINTEREST_SEARCH_VALUE);
		getWebElementByXPath(Util.PINTEREST_SEARCH_BOX_XPATH).sendKeys(Keys.ENTER);
		waitForPageLoaded();
	}

	private void signInToPinterest() {

		insertTextInTheTextField(getWebElementById(Util.PINTEREST_EMAIL_TF_ID),Util.PINTEREST_EMAIL_TF_VALUE);
		insertTextInTheTextField(getWebElementById(Util.PINTEREST_PASSWORD_TF_ID),Util.PINTEREST_PASSWORD_TF_VALUE);
		clickOnButton(getWebElementByXPath(Util.PINTEREST_CONTINUE_BUTTON_XPATH));
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
